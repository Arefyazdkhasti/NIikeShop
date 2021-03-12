package com.example.nikeshop.View

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeshop.R
import com.example.nikeshop.`interface`.Utility
import com.example.nikeshop.adapter.RecyclerItemProductAdapter
import com.example.nikeshop.dataClass.DataProduct
import com.example.nikeshop.enumration.TypeGetProduct
import com.example.nikeshop.net.ApiService
import kotlinx.android.synthetic.main.activity_category.view.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("ViewConstructor")
class ViewCategoryActivity(contextInstance: Context, private val utility: Utility) :
    FrameLayout(contextInstance) {

    private val apiService = ApiService()

    private val txtTitle: AppCompatTextView
    private val imgBack: AppCompatImageView
    private val recycler: RecyclerView
    private val imgSort: AppCompatImageView
    private val txtSort: AppCompatTextView
    private val progress: ProgressBar
    private val search: SearchView
    var sortBy: String = "همه"
    var name: String = " "
    private var itemChecked = 0

    init {
        val mainView = inflate(context, R.layout.activity_category, this)


        txtTitle = mainView.title_category_activity
        imgBack = mainView.image_back_category_activity
        recycler = mainView.recycler_category_activity
        imgSort = mainView.img_sort_category_activity
        txtSort = mainView.txt_sort_by_category_activity
        progress = mainView.progress_bar_category_activity
        search = mainView.search_view_category_activity

        imgSort.setOnClickListener {
            showSortDialog()
        }
        search()
    }


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
                itemChecked = i
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

    fun onClicked() {
        imgBack.setOnClickListener {
            utility.onFinished()
        }
    }

    /*fun getSearch() {
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                name = query ?: " "
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                name = newText ?: " "
                return false
            }
        })
    }
*/

    private fun search() {
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                val string = query ?: ""
                showProgress()
                apiService.getApi().search(string,sortBy)
                    .enqueue(object : Callback<List<DataProduct>> {
                        override fun onResponse(
                            call: Call<List<DataProduct>>,
                            response: Response<List<DataProduct>>
                        ) {
                            val data = response.body()

                            if (data != null)
                                setDataRecycler(data)
                            hideProgress()
                        }

                        override fun onFailure(call: Call<List<DataProduct>>, t: Throwable) {
                            showToast(t.message.toString())
                            hideProgress()
                        }
                    })
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val string = newText ?: ""
                showProgress()
                apiService.getApi().search(string,sortBy)
                    .enqueue(object : Callback<List<DataProduct>> {
                        override fun onResponse(
                            call: Call<List<DataProduct>>,
                            response: Response<List<DataProduct>>
                        ) {
                            val data = response.body()

                            if (data != null)
                                setDataRecycler(data)
                            hideProgress()

                        }

                        override fun onFailure(call: Call<List<DataProduct>>, t: Throwable) {
                            showToast(t.message.toString())
                            hideProgress()
                        }
                    })
                return false
            }
        })
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