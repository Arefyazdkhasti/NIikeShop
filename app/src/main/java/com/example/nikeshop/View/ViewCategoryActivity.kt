package com.example.nikeshop.View

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.ContextMenu
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeshop.R
import com.example.nikeshop.View.CoustomView.ProductView
import com.example.nikeshop.`interface`.Utility
import com.example.nikeshop.adapter.RecyclerItemProductAdapter
import com.example.nikeshop.dataClass.DataProduct
import com.example.nikeshop.enumration.TypeGetProduct
import kotlinx.android.synthetic.main.activity_category.view.*
import org.jetbrains.anko.toast

@SuppressLint("ViewConstructor")
class ViewCategoryActivity(contextInstance: Context, private val utility: Utility) :
    FrameLayout(contextInstance) {

    //  private val root: LinearLayout
    // private val newProduct: ProductView
    // private val topSellingProduct: ProductView
    //  private val imgBanner: AppCompatImageView

    private val txtTitle: AppCompatTextView
    private val imgBack: AppCompatImageView
    private val recycler: RecyclerView
    private val imgSort: AppCompatImageView
    private val txtSort: AppCompatTextView
    private val progress: ProgressBar
    var sortBy: String = "همه"
    private var itemChecked = 0

    init {
        val mainView = inflate(context, R.layout.activity_category, this)

        //root=mainView.root_category_activity
        //newProduct=mainView.new_products_category_fragment
        //topSellingProduct=mainView.top_selling_category_fragment
        //imgBanner=mainView.banner_image_1_category_fragment
        txtTitle = mainView.title_category_activity
        imgBack = mainView.image_back_category_activity
        recycler = mainView.recycler_category_activity
        imgSort = mainView.img_sort_category_activity
        txtSort = mainView.txt_sort_by_category_activity
        progress = mainView.progress_bar_category_activity

        imgSort.setOnClickListener {
            showSortDialog()
        }
    }

    /*fun setDataNewProducts(data: List<DataProduct>) {
        newProduct.initRecycler(data, TypeGetProduct.NEW_PRODUCT)
    }

    fun setDataTopSellingProducts(data: List<DataProduct>) {
        topSellingProduct.initRecycler(data, TypeGetProduct.TOP_SELLING_PRODUCT)
        //when this last data set hide progress
        hideProgress()
    }*/

    fun setDataRecycler(data: List<DataProduct>) {
        recycler.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        val adapter = RecyclerItemProductAdapter(context, data)
        recycler.adapter = adapter
    }

    private fun showSortDialog() {
        imgSort.setOnClickListener {

            val listItems = arrayOf(
                "همه",
                "جدیدترین ها",
                "پرفروش ترین ها",
                "بیشترین تخفیف",
                "گرانترین",
                "ارزانترین"
            )


            val mBuilder = AlertDialog.Builder(context)
            mBuilder.setTitle("مرتب سازی براساس:")
            mBuilder.setSingleChoiceItems(listItems, itemChecked) { dialogInterface, i ->
                txtSort.text = listItems[i]
                itemChecked=i
                sortBy = listItems[i]
                utility.onRefreshed()
                dialogInterface.dismiss()
            }

            mBuilder.setNeutralButton("بیخیال") { dialog, which -> dialog.cancel() }
            val mDialog = mBuilder.create()
            mDialog.show()

        }
        print("sort-> $sortBy")
    }


    fun setTitle(title: String) {
        txtTitle.text = title
    }

    fun onBackClicked() {
        imgBack.setOnClickListener {
            utility.onFinished()
        }
    }

    fun showToast(text: String) {
        context.toast(text)
    }

    fun showProgress() {
        progress.visibility = View.VISIBLE
        recycler.visibility = View.INVISIBLE
    }

    fun hideProgress() {
        progress.visibility = View.INVISIBLE
        recycler.visibility = View.VISIBLE
    }
}