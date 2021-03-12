package com.example.nikeshop.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeshop.R
import com.example.nikeshop.activities.DetailActivity
import com.example.nikeshop.dataClass.DataBoughtDetail
import com.example.nikeshop.dataClass.DataImage
import com.example.nikeshop.dataClass.DataResponse
import com.example.nikeshop.net.ApiService
import com.example.nikeshop.utitlity.PicassoUtility
import com.valdesekamdem.library.mdtoast.MDToast
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.dialog_send_comment.view.*
import kotlinx.android.synthetic.main.item_bought_detail.view.*
import kotlinx.android.synthetic.main.item_img_former_basket.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecyclerItemBoughtDetail(
    private val context: Context,
    private val data: List<DataBoughtDetail>,
) : RecyclerView.Adapter<RecyclerItemBoughtDetail.ItemBoughtDetailVieHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemBoughtDetailVieHolder =
        ItemBoughtDetailVieHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_bought_detail,
                    parent, false
                )
        )

    override fun onBindViewHolder(holder: ItemBoughtDetailVieHolder, position: Int) {
        holder.setData(data[position])
    }

    override fun getItemCount(): Int = data.size


    inner class ItemBoughtDetailVieHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        KoinComponent {

        private val picassoUtility: PicassoUtility by inject()
        private val apiService:ApiService by inject()

        private val img = itemView.img_item_buy_detail
        private val title = itemView.title_item_buy_detail
        private val price = itemView.product_price_item_buy_detail
        private val addCommentBtn = itemView.add_comment_materialButton_item_buy_detail
        private val count = itemView.count_item_buy_detail

        fun setData(data: DataBoughtDetail) {
            picassoUtility.setImage(data.imgAddress,img)
            title.text=data.title
            count.text=data.count.toString()

            //set the price you can buy
            if (data.discount==1){
                price.text=data.priceDiscount
            }else{
                price.text=data.price
            }

            img.setOnClickListener {
                context.startActivity<DetailActivity>(
                    RecyclerItemProductAdapter.KEY_ID to data.product_id,
                    RecyclerItemProductAdapter.KEY_NAME to data.title
                )
            }

            addCommentBtn.setOnClickListener{
                showDialog(apiService,data.product_id,data.user_email)
            }
        }

        private fun showDialog(apiService: ApiService, id: Int, email:String){


            val mDialogView =
                LayoutInflater.from(context).inflate(R.layout.dialog_send_comment, null)

            val mBuilder = AlertDialog.Builder(context)
                .setView(mDialogView)
                .setTitle("ثبت نظر")

            val mAlertDialog = mBuilder.show()

            mDialogView.btn_send_comment_dialog.setOnClickListener {
                mAlertDialog.dismiss()
                val title = mDialogView.edt_comment_title_dialog.text.toString()
                val content = mDialogView.edt_comment_content_dialog.text.toString()
                if (title.isNotEmpty() && content.isNotEmpty()) {
                    apiService.getApi().addComment(title, email, id, content)
                        .enqueue(object : Callback<DataResponse> {
                            override fun onResponse(
                                call: Call<DataResponse>,
                                response: Response<DataResponse>
                            ) {
                                val data = response.body()
                                if (data != null)
                                    MDToast.makeText(context,data.error_msg,MDToast.TYPE_SUCCESS).show()
                                else
                                    context.toast("داده خالی است")

                                mAlertDialog.dismiss()
                            }

                            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                                context.toast(t.message.toString())
                                mAlertDialog.dismiss()
                            }
                        })
                }
            }
        }
    }
}