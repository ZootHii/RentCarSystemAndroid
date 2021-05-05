package com.zoothii.rent_car_system_android.ui.cars_detail


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zoothii.rent_car_system_android.R
import com.zoothii.rent_car_system_android.adapter.CarsDetailAdapter
import com.zoothii.rent_car_system_android.databinding.FragmentCarsDetailBinding
import com.zoothii.rent_car_system_android.model.CarDetail
import com.zoothii.rent_car_system_android.ui.activity.RentalActivity
import com.zoothii.rent_car_system_android.util.Constant.Companion.SORT_BY_DAILY_PRICE
import com.zoothii.rent_car_system_android.util.Constant.Companion.SORT_BY_MODEL_YEAR
import com.zoothii.rent_car_system_android.util.Helper
import com.zoothii.rent_car_system_android.view_model.CarViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarsDetailFragment : Fragment(R.layout.fragment_cars_detail) {

    private val carViewModel: CarViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var carCardAdapter: CarsDetailAdapter
    private lateinit var carDetailList: ArrayList<CarDetail>
    private lateinit var fragmentRoot: View
    private lateinit var toolbar: Toolbar
    private lateinit var progressBar: ContentLoadingProgressBar
    private val titleToolbar = "Cars"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentCarsDetailBinding = FragmentCarsDetailBinding.bind(view)

        fragmentCarsDetailBinding.apply {
            fragmentRoot = root
            progressBar = progressBarCarsDetail
            recyclerView = recyclerViewCarsDetail.apply {
                carCardAdapter =
                    CarsDetailAdapter(fragmentCarsDetailBinding.root.context) { carDetail, carsDetailList ->
                        val intent = Intent(activity, RentalActivity::class.java)
                        Helper.data = carDetail
                        startActivity(intent)

                    }
                adapter = carCardAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true) // TODO for more optimized recyclerview
            }
            toolbar = toolbarCarsDetail.apply {
                title = titleToolbar
                inflateMenu(R.menu.action_bar_cars_details_menu)
                (menu.findItem(R.id.action_search).actionView as SearchView).apply {
                    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            return false
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            carCardAdapter.filter.filter(newText)
                            return true
                        }
                    })
                }
            }
        }

        // TODO I can sort my cars in backend then subscribe by the order choice but I am not sure
        Helper.progressBarShow(progressBar, true)
        carViewModel.getAllCarsDetailsWithPreviewFirstImage().observe(
            viewLifecycleOwner
        ) { responseCarDetailData ->
            if (responseCarDetailData.success) {

                carDetailList = responseCarDetailData.data.toCollection(ArrayList())

                toolbar.setOnMenuItemClickListener {
                    if (it.itemId == R.id.action_sort_by_daily_price) {
                        carCardAdapter.sortCarsBy(SORT_BY_DAILY_PRICE)
                    } else if (it.itemId == R.id.action_sort_by_model_year) {
                        carCardAdapter.sortCarsBy(SORT_BY_MODEL_YEAR)
                    }
                    true
                }

                carCardAdapter.setCarDetails(carDetailList)
                Helper.progressBarShow(progressBar, false)

            } else {
                Log.d("Message", responseCarDetailData.message.toString())
                Log.d("Success", responseCarDetailData.success.toString())
            }
        }
    }
}

