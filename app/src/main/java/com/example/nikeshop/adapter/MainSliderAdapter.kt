package com.example.nikeshop.adapter

import android.util.Log
import com.example.nikeshop.dataClass.DataImageUrl
import com.example.nikeshop.net.ApiService
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder

class MainSliderAdapter : SliderAdapter(),KoinComponent{

    private val apiService:ApiService by inject()

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindImageSlide(position: Int, imageSlideViewHolder: ImageSlideViewHolder?) {
        apiService.getApi().getSliderImagesUrl()
            .enqueue(object : Callback<DataImageUrl> {
                override fun onResponse(
                    call: Call<DataImageUrl>,
                    response: Response<DataImageUrl>
                ) {
                    val data = response.body()

                    if (data != null) {
                        when (position) {
                            0 -> imageSlideViewHolder?.bindImageSlide(data.image1)
                            1 -> imageSlideViewHolder?.bindImageSlide(data.image2)
                            2 -> imageSlideViewHolder?.bindImageSlide(data.image3)
                        }
                    } else
                        Log.e("SLIDER_DATA", "slider data is null")

                }

                override fun onFailure(call: Call<DataImageUrl>, t: Throwable) {
                    Log.e("SLIDER_DATA", "error in getting slider data")

                }

            })

    }


}