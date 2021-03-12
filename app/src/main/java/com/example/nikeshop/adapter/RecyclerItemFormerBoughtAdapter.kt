package com.example.nikeshop.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeshop.MainActivity
import com.example.nikeshop.R
import com.example.nikeshop.activities.BoughtDetailActivity
import com.example.nikeshop.dataClass.DataFormerBasket
import com.example.nikeshop.dataClass.MSG
import com.example.nikeshop.net.ApiService
import kotlinx.android.synthetic.main.item_former_basket.view.*
import org.jetbrains.anko.startActivity
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerItemFormerBoughtAdapter(
    private val context: Context,
    private val data: List<DataFormerBasket>,
) : RecyclerView.Adapter<RecyclerItemFormerBoughtAdapter.ItemBoughtVieHolder>() {

    companion object{
        const val KEY_BASKET_ID="basket_id"
        const val KEY_PRICE="price"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemBoughtVieHolder =
        ItemBoughtVieHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_former_basket,
                    parent, false
                )
        )

    override fun onBindViewHolder(holder: ItemBoughtVieHolder, position: Int) {
        holder.setData(data[position])
    }

    override fun getItemCount(): Int = data.size


    inner class ItemBoughtVieHolder(itemView: View) : RecyclerView.ViewHolder(itemView),KoinComponent {

        private val apiService:ApiService by inject()

        private val root=itemView.root_item_former_basket
        private val price = itemView.price_item_former_basket
        private val id = itemView.basket_id_item_former_basket
        private val created = itemView.created_at_item_former_basket
        private val imagesRecycler=itemView.item_img_recycler_item_former_basket


        fun setData(data: DataFormerBasket) {

            id.text = data.basket_id
            created.text = data.created_at

            imagesRecycler.layoutManager=LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,true)
            val adapter= RecyclerItemImagesAdapter(data.imgAddress)
            imagesRecycler.adapter=adapter

            root.setOnClickListener {
                context.startActivity<BoughtDetailActivity>(
                    KEY_BASKET_ID to data.basket_id
                )
            }
            apiService.getApi().getCartTotalCost(data.basket_id)
                .enqueue(object :Callback<MSG>{
                    override fun onResponse(call: Call<MSG>, response: Response<MSG>) {
                        val d=response.body()

                        if (d!=null){
                            price.text=d.msg
                        }else{
                            Log.e("GET_TOTAL_COST","خطا در دریافت قیمت")
                        }
                    }

                    override fun onFailure(call: Call<MSG>, t: Throwable) {
                        Log.e("GET_TOTAL_COST",t.message.toString())
                    }
                })
        }
    }
}