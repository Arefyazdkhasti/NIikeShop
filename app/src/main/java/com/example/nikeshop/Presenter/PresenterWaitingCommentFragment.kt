package com.example.nikeshop.Presenter

import com.example.nikeshop.Model.ModelWaitingCommentFragment
import com.example.nikeshop.`interface`.BaseLifeCycle
import com.example.nikeshop.dataClass.DataProduct
import com.example.nikeshop.dataClass.DataYourComment
import com.example.nikeshop.fragments.WaitingCommentFragment
import com.example.nikeshop.net.CountryPresenterListener

class PresenterWaitingCommentFragment(
    private val view:WaitingCommentFragment,
    private val model:ModelWaitingCommentFragment
) :BaseLifeCycle{
    override fun onCreate() {
        setUpRecycler()
    }

    private fun setUpRecycler(){
        view.showProgress()
        model.getData(object :CountryPresenterListener<List<DataProduct>>{
            override fun onResponse(data: List<DataProduct>) {
                view.setUpRecycler(data)
                view.hideProgress()
            }
            override fun onFailure(error: String) {
                view.showCustomToastInfo(error)
                view.hideProgress()
            }

            override fun onEmptyResponse(isEmpty: Boolean) {
                super.onEmptyResponse(isEmpty)
                view.hideProgress()
                if (isEmpty){
                    view.showNoComment()
                }else{
                    view.hideNoComment()
                }
            }
        })
    }

    override fun onDestroy() {
    }
}