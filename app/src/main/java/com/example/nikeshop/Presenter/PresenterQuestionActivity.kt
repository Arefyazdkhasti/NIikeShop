package com.example.nikeshop.Presenter

import com.example.nikeshop.Model.ModelQuestionActivity
import com.example.nikeshop.View.ViewQuestionActivity
import com.example.nikeshop.`interface`.BaseLifeCycle
import com.example.nikeshop.dataClass.DataQuestion
import com.example.nikeshop.net.CountryPresenterListener

class PresenterQuestionActivity(
    private val view: ViewQuestionActivity,
    private val model: ModelQuestionActivity
) : BaseLifeCycle {

    override fun onCreate() {
        onClick()
        setDataRecyclerAsApi()
    }

    private fun onClick() {
        view.onClickHandler()
    }

    private fun setDataRecyclerAsApi(){
        model.getDataQuestion(object :CountryPresenterListener<List<DataQuestion>>{
            override fun onResponse(data: List<DataQuestion>) {
                view.initRecycler(data)
                view.hideProgress()
            }

            override fun onFailure(error: String) {
                view.showToast(error)
                view.hideProgress()
            }

        })
    }

    override fun onDestroy() {

    }
}