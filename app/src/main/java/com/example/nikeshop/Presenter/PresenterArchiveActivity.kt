package com.example.nikeshop.Presenter

import com.example.nikeshop.Model.ModelArchiveActivity
import com.example.nikeshop.View.ViewArchiveActivity
import com.example.nikeshop.`interface`.BaseLifeCycle
import com.example.nikeshop.dataClass.DataProduct
import com.example.nikeshop.enumration.TypeGetProduct
import com.example.nikeshop.net.CountryPresenterListener

class PresenterArchiveActivity(
    private val view: ViewArchiveActivity,
    private val model: ModelArchiveActivity
) : BaseLifeCycle {

    override fun onCreate() {
        onClickBackListener()
        setDataInRecycler()
        setTitleText()
    }

    private fun onClickBackListener() {
        view.onClickHandler()
    }

    private fun setDataInRecycler() {
        when (model.getTypeProductAsIntent()) {

            TypeGetProduct.NEW_PRODUCT -> {

                model.getDataNewProducts(object : CountryPresenterListener<List<DataProduct>> {

                    override fun onResponse(data: List<DataProduct>) {
                        view.setDataInRecyclers(data)
                    }

                    override fun onFailure(error: String) {
                        view.showToast(error)
                    }
                })

            }

            TypeGetProduct.TOP_SELLING_PRODUCT -> {

                model.getDataTopSellingProducts(object : CountryPresenterListener<List<DataProduct>> {

                    override fun onResponse(data: List<DataProduct>) {
                        view.setDataInRecyclers(data)
                    }

                    override fun onFailure(error: String) {
                        view.showToast(error)
                    }
                })

            }
        }
    }


    private fun setTitleText() {
        view.setTitleText(model.getTitleAsIntent())
    }

    override fun onDestroy() {

    }
}