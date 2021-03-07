package com.example.nikeshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nikeshop.R
import com.example.nikeshop.activities.DetailActivity
import com.example.nikeshop.dataClass.DataProduct
import com.example.nikeshop.utitlity.PicassoUtility
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product.view.*
import kotlinx.android.synthetic.main.produt_view.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


class RecyclerItemArchiveAdapter(
    private val context: Context,
    private val data: List<DataProduct>) :
    RecyclerView.Adapter<RecyclerItemArchiveAdapter.ItemProductVieHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemProductVieHolder =
        ItemProductVieHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_product_archive_activity,
                    parent, false
                )
        )

    override fun onBindViewHolder(holder: ItemProductVieHolder, position: Int) {
        holder.setData(data[position])
    }

    override fun getItemCount() = data.size


    inner class ItemProductVieHolder(itemView: View) : RecyclerView.ViewHolder(itemView),KoinComponent{

        private val picasso:PicassoUtility by inject()
        private val rootView= itemView.constraint_recycler_product_view
        private val image=itemView.product_image
        private val title=itemView.product_name
        private val price=itemView.product_price
        private val discount=itemView.product_discount


        fun setData(data:DataProduct){

            picasso.setImage(data.imgAddress,image)
            //glide.load(data.imageUrl).placeholder(R.drawable.nike_logo).error(R.drawable.ic_home).into(image)
            title.text =data.title

            if (data.discount == 1){
                discount.visibility = View.VISIBLE
                //use custom view function -> if product has discount set old price on discount textView and make a line on it
                discount.setCustomText(data.price)
                price.text=data.priceDiscount
            }else{
                discount.visibility = View.INVISIBLE
                price.text= data.price
            }

            rootView.setOnClickListener{
                rootView.setOnClickListener {
                    context.startActivity<DetailActivity>(
                        RecyclerItemProductAdapter.KEY_ID to data.id,
                        RecyclerItemProductAdapter.KEY_NAME to data.title
                    )
                }
            }
        }
    }

}