package com.example.nikeshop.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeshop.R
import com.example.nikeshop.activities.DetailActivity
import com.example.nikeshop.dataClass.DataResponse
import com.example.nikeshop.dataClass.DataYourComment
import com.example.nikeshop.net.ApiService
import com.example.nikeshop.utitlity.PicassoUtility
import com.valdesekamdem.library.mdtoast.MDToast
import kotlinx.android.synthetic.main.item_your_comment.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecyclerYourCommentAdapter(
    private val data: List<DataYourComment>,
    private val context: Context?
) : RecyclerView.Adapter<RecyclerYourCommentAdapter.YourCommentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YourCommentsViewHolder =
        YourCommentsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_your_comment,
                    parent,
                    false
                )
        )

    override fun onBindViewHolder(holder: YourCommentsViewHolder, position: Int) {
        holder.setData(data[position])
    }

    override fun getItemCount(): Int = data.size


    inner class YourCommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        KoinComponent {

        private val apiService: ApiService by inject()
        private val picassoUtility: PicassoUtility by inject()

        private val imgProduct = itemView.item_your_comment_image
        private val txtTitle = itemView.item_your_comment_title
        private val txtTime = itemView.item_your_comment_time
        private val txtText = itemView.item_your_comment_content
        private val txtProductName = itemView.item_your_comment_product_name
        private val imgDelete = itemView.item_your_comment_delete

        fun setData(data: DataYourComment) {

            picassoUtility.setImage(data.imgAddress, imgProduct)
            txtTitle.text = data.title
            txtTime.text = data.created_at
            txtText.text = data.content
            txtProductName.text = data.product_name
            imgDelete.setOnClickListener {
                showDeleteDialog(data.product_name, data.author, data.product_id)
            }

            imgProduct.setOnClickListener {
                context?.startActivity<DetailActivity>(
                    RecyclerItemProductAdapter.KEY_ID to data.product_id,
                    RecyclerItemProductAdapter.KEY_NAME to data.product_name
                )
            }

        }

        private fun showDeleteDialog(name: String, author: String, product_id: Int) {
            val builder = AlertDialog.Builder(context)
            builder.setMessage("آیا مایل به حذف نظرتان هستید؟")
                .setCancelable(false)
                .setPositiveButton("بله") { dialog, id ->
                    apiService.getApi().removerComment(author, product_id)
                        .enqueue(object : Callback<DataResponse> {
                            override fun onResponse(
                                call: Call<DataResponse>,
                                response: Response<DataResponse>
                            ) {
                                val dataResponse = response.body()

                                if (dataResponse != null) {
                                    context?.toast("نظر شما حذف خواهد شد!")
                                } else {
                                    context?.toast("خطایی به وجود آمده است!")
                                }
                            }

                            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                                context?.toast("خطایی به وجود آمده است بعدا تلاش کنید")
                            }
                        })
                    dialog.dismiss()
                }
                .setNegativeButton("بیخیال") { dialog, id ->
                    // Dismiss the dialog
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }

    }

}