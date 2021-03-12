package com.example.nikeshop.utitlity
import android.media.Image
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import com.example.nikeshop.R
import com.squareup.picasso.Picasso
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class PicassoUtility:KoinComponent {

    private val picasso: Picasso by inject()

    fun setImage(url:String,view:AppCompatImageView){
        picasso
            .load(url)
            .placeholder(R.drawable.nike_logo)
            .into(view)
    }

    fun setImageInt(resource:Int,view :ImageView){
        picasso
            .load(resource)
            .placeholder(R.drawable.nike_logo)
            .into(view)
    }

    fun setImageForSlider(url:String,view :ImageView){
        picasso
            .load(url)
            .placeholder(R.drawable.nike_logo)
            .into(view)
    }
}