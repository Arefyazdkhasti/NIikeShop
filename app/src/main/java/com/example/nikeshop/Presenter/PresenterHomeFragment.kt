package com.example.nikeshop.Presenter

import com.example.nikeshop.Model.ModelHomeFragment
import com.example.nikeshop.`interface`.BaseLifeCycle
import com.example.nikeshop.dataClass.DataCategory
import com.example.nikeshop.dataClass.DataImageUrl
import com.example.nikeshop.dataClass.DataProduct
import com.example.nikeshop.fragments.HomeFragment
import com.example.nikeshop.net.CountryPresenterListener

class PresenterHomeFragment(
    private val view: HomeFragment,
    private val model: ModelHomeFragment
) : BaseLifeCycle {

    override fun onCreate() {
        setUpGetData()
        setDataRecycler()
    }

    private fun setDataRecycler() {
        //view.setDataNewProductRecycler(model.getDataRecycler())
        //view.setDataTopSellingRecycler(model.getDataRecycler())
        model.getDataNewProducts(object : CountryPresenterListener<List<DataProduct>>{
            override fun onResponse(data: List<DataProduct>) {
                view.setDataNewProductRecycler(data)
            }

            override fun onFailure(error: String) {
                view.showToast(error)
            }

        })

        model.getDataTopSellingProducts(object : CountryPresenterListener<List<DataProduct>>{
            override fun onResponse(data: List<DataProduct>) {
                view.setDataTopSellingRecycler(data)
            }

            override fun onFailure(error: String) {
                view.showToast(error)
            }

        })
    }

    private fun setUpGetData() {

        model.getImageUrlForBanner(object : CountryPresenterListener<DataImageUrl> {
            override fun onResponse(data: DataImageUrl) {
                view.setImageInBanners(data)
            }

            override fun onFailure(error: String) {
                view.showToast(error)
            }

        })
        model.getCategoryDataRecycler(object : CountryPresenterListener<List<DataCategory>> {
            override fun onResponse(data: List<DataCategory>) {
                view.setCategoryRecycler(data)
            }

            override fun onFailure(error: String) {
                view.showToast(error)

            }

        })

    }


    override fun onDestroy() {}

}