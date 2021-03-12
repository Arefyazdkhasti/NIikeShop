package com.example.nikeshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeshop.R
import com.example.nikeshop.dataClass.DataImage
import com.example.nikeshop.utitlity.PicassoUtility
import kotlinx.android.synthetic.main.item_img_former_basket.view.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class RecyclerItemImagesAdapter(
    private val data: List<DataImage>,
) : RecyclerView.Adapter<RecyclerItemImagesAdapter.ItemImageBoughtVieHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemImageBoughtVieHolder =
        ItemImageBoughtVieHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_img_former_basket,
                    parent, false
                )
        )

    override fun onBindViewHolder(holder: ItemImageBoughtVieHolder, position: Int) {
        holder.setData(data[position])
    }

    override fun getItemCount(): Int = data.size


    inner class ItemImageBoughtVieHolder(itemView: View) : RecyclerView.ViewHolder(itemView),KoinComponent {

        private val picassoUtility:PicassoUtility by inject()
        private val img = itemView.img_item_img_former_basket

        fun setData(data: DataImage) {
            picassoUtility.setImage(data.imgAddress,img)
        }
    }
}