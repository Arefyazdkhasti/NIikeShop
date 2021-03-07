package com.example.nikeshop.Presenter

import com.example.nikeshop.Model.ModelDetailActivity
import com.example.nikeshop.View.ViewDetailActivity
import com.example.nikeshop.`interface`.BaseLifeCycle
import com.example.nikeshop.dataClass.DataComments
import com.example.nikeshop.dataClass.DataProduct
import com.example.nikeshop.dataClass.DataResponse
import com.example.nikeshop.net.CountryPresenterListener

class PresenterDetailActivity
    (
    private val view: ViewDetailActivity,
    private val model: ModelDetailActivity
) : BaseLifeCycle,CountryPresenterListener<DataProduct> {

    override fun onCreate() {
        onClick()
        setTitle()
        isFavourite()
        setDataRecycler()
        setDataProduct()
    }

    override fun onRefresh() {
        setDataRecycler()
        setDataProduct()
    }

    private fun onClick() {
        view.onClickHandler(model.getIdAsIntent(),model.getObjectApi(),model.getUserEmail())
    }

    private fun setDataProduct(){
        model.getProductById(this)
    }

    private fun setDataRecycler() {
        model.getCommentsDataRecycler(object : CountryPresenterListener<List<DataComments>> {
            override fun onResponse(data: List<DataComments>) {
                view.setDataComments(data)
            }

            override fun onFailure(error: String) {
                view.showToast(error)
            }

            override fun onEmptyResponse(isEmpty: Boolean) {
                super.onEmptyResponse(isEmpty)
                if (isEmpty){
                    view.showNoComment()
                }else{
                    view.hideNoComment()
                }
            }
        })
    }


    private fun setTitle() {
        view.setTitle(model.getTitleAsIntent())
    }

    private fun isFavourite(){
        model.getIsProductYourFavourite(object :CountryPresenterListener<DataResponse>{
            override fun onResponse(data: DataResponse) {
                if (data.error_msg == "true"){
                    view.setFavourite()
                }else{
                    view.notFavourite()
                }
            }

            override fun onFailure(error: String) {
                view.showToast(error)
            }

        })
    }

    override fun onResponse(data: DataProduct) {
        view.setUpViews(data)
        view.hideProgress()
    }

    override fun onFailure(error: String) {
        view.showSnackBar(error)
    }

    override fun onDestroy() {}
}