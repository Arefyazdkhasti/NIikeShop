package com.example.nikeshop.Presenter

import android.util.Log
import com.example.nikeshop.Model.ModelAcceptedCommentFragment
import com.example.nikeshop.`interface`.BaseLifeCycle
import com.example.nikeshop.dataClass.DataComments
import com.example.nikeshop.fragments.AcceptedCommentFragment
import com.example.nikeshop.net.CountryPresenterListener

class PresenterAcceptedCommentFragment(
    private val view:AcceptedCommentFragment,
    private val model:ModelAcceptedCommentFragment
) :BaseLifeCycle{
    override fun onCreate() {
        setUpRecycler()
    }

    private fun setUpRecycler(){
        view.showProgress()
        model.getData(object :CountryPresenterListener<List<DataComments>>{
            override fun onResponse(data: List<DataComments>) {
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

        },1)
    }

    override fun onDestroy() {
    }
}