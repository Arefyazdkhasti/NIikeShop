package com.example.nikeshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeshop.R
import com.example.nikeshop.activities.ArchiveActivity
import com.example.nikeshop.activities.CategoryActivity
import com.example.nikeshop.dataClass.DataCategory
import kotlinx.android.synthetic.main.item_category.view.*
import org.jetbrains.anko.startActivity
import org.koin.standalone.KoinComponent


class RecyclerItemCategoryAdapter(
    private val context: Context?,
    private val data: List<DataCategory>
) : RecyclerView.Adapter<RecyclerItemCategoryAdapter.ItemProductVieHolder>() {

    companion object {
        const val KET_CATEGORY_ID = "id"
        const val KET_CATEGORY_TITLE = "title"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemProductVieHolder =
        ItemProductVieHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_category,
                    parent, false
                )
        )

    override fun onBindViewHolder(holder: ItemProductVieHolder, position: Int) {
        holder.setData(data[position])
    }

    override fun getItemCount() = data.size


    inner class ItemProductVieHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        KoinComponent {

        private val title = itemView.title_category_item


        fun setData(data: DataCategory) {

            title.text = data.title

            title.setOnClickListener {
                context?.startActivity<CategoryActivity>(
                    KET_CATEGORY_ID to data.id,
                    KET_CATEGORY_TITLE to data.title
                )
            }
        }
    }

}