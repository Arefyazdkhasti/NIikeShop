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
import com.example.nikeshop.dataClass.DataProduct
import com.example.nikeshop.dataClass.DataResponse
import com.example.nikeshop.dataClass.DataYourComment
import com.example.nikeshop.net.ApiService
import com.example.nikeshop.utitlity.PicassoUtility
import com.valdesekamdem.library.mdtoast.MDToast
import kotlinx.android.synthetic.main.fragment_waiting_comment.view.*
import kotlinx.android.synthetic.main.item_wait_for_comment.view.*
import kotlinx.android.synthetic.main.item_your_comment.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecyclerWaitingForCommentsAdapter(
    private val data: List<DataProduct>,
    private val context: Context?
) : RecyclerView.Adapter<RecyclerWaitingForCommentsAdapter.YourCommentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YourCommentsViewHolder =
        YourCommentsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_wait_for_comment,
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

        private val imgProduct = itemView.item_waiting_for_comment_image
        private val txtTitle = itemView.item_waiting_for_comment_product_name
        private val addComment = itemView.item_waiting_for_comment_add

        fun setData(data: DataProduct) {

            picassoUtility.setImage(data.imgAddress, imgProduct)
            txtTitle.text = data.title


            imgProduct.setOnClickListener {
                context?.startActivity<DetailActivity>(
                    RecyclerItemProductAdapter.KEY_ID to data.id,
                    RecyclerItemProductAdapter.KEY_NAME to data.title
                )
            }

        }


    }

}