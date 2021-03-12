package com.example.nikeshop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nikeshop.Presenter.PresenterHomeFragment
import com.example.nikeshop.R
import com.example.nikeshop.adapter.MainSliderAdapter
import com.example.nikeshop.adapter.RecyclerItemCategoryAdapter
import com.example.nikeshop.dataClass.DataCategory
import com.example.nikeshop.dataClass.DataImageUrl
import com.example.nikeshop.dataClass.DataProduct
import com.example.nikeshop.enumration.TypeGetProduct
import com.example.nikeshop.utitlity.ImageLoadingService
import com.example.nikeshop.utitlity.PicassoUtility
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import ss.com.bannerslider.Slider
import ss.com.bannerslider.indicators.IndicatorShape


class HomeFragment : Fragment() {

    private val presenter: PresenterHomeFragment by inject()
    private val picasso: PicassoUtility by inject()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        category_recycler_home_fragment.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true)


        presenter.onCreate()

        showProgressBar()
    }

    /*//for avoiding crash in moving between tabs for many times implement resume
    override fun onResume() {
        super.onResume()
        //presenter.onResume()
    }*/

    //regionData
    fun setDataNewProductRecycler(data: List<DataProduct>) {
        new_products_home_fragment.initRecycler(data,TypeGetProduct.NEW_PRODUCT)
    }

    fun setDataTopSellingRecycler(data:List<DataProduct>){
        top_selling_home_fragment.initRecycler(data,TypeGetProduct.TOP_SELLING_PRODUCT)

        hideProgressBar()
    }

    fun setCategoryRecycler(data:List<DataCategory>){
        category_recycler_home_fragment.adapter=RecyclerItemCategoryAdapter(context,data)
    }


    fun setImageInBanners(data:DataImageUrl){
        picasso.setImage(data.image1,banner_image_1_home_fragment)
        picasso.setImage(data.image2,banner_image_2_home_fragment)
    }

    fun setSlider(){
        //TODO di inject
        val adapter=MainSliderAdapter()
        val imgLoader= ImageLoadingService()
        Slider.init(imgLoader)
        banner_slider_home_fragment.setAdapter(adapter)
        banner_slider_home_fragment.setIndicatorStyle(IndicatorShape.ROUND_SQUARE);
    }

    //endregion

    fun showToast(text:String){
        context?.toast(text)
    }

    private fun showProgressBar(){
        progress_bar_home_fragment.visibility=View.VISIBLE
        root_view_home_fragment.visibility = View.INVISIBLE
    }

    private fun hideProgressBar(){
        progress_bar_home_fragment.visibility=View.INVISIBLE
        root_view_home_fragment.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}