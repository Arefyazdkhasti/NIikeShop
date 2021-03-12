package com.example.nikeshop.utitlity

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import ss.com.bannerslider.ImageLoadingService

class ImageLoadingService : ImageLoadingService, KoinComponent {

    private val picassoUtility: PicassoUtility by inject()

    override fun loadImage(url: String?, imageView: ImageView?) {
        if (url != null && imageView!=null)
            picassoUtility.setImageForSlider(url, imageView)
    }

    override fun loadImage(resource: Int, imageView: ImageView?) {
        if (imageView!=null)
        picassoUtility.setImageInt(resource,imageView)
    }

    override fun loadImage(
        url: String?,
        placeHolder: Int,
        errorDrawable: Int,
        imageView: ImageView?
    ) {
        if (url != null && imageView!=null)
        picassoUtility.setImageForSlider(url, imageView)
    }
}