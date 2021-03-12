package com.example.nikeshop.View

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.ScrollView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codesgood.views.JustifiedTextView
import com.example.nikeshop.R
import com.example.nikeshop.View.CoustomView.CustomTextView
import com.example.nikeshop.`interface`.Utility
import com.example.nikeshop.adapter.RecyclerCommentAdapter
import com.example.nikeshop.dataClass.DataComments
import com.example.nikeshop.dataClass.DataProduct
import com.example.nikeshop.dataClass.DataResponse
import com.example.nikeshop.dataClass.MSG
import com.example.nikeshop.net.ApiService
import com.example.nikeshop.net.CountryPresenterListener
import com.example.nikeshop.utitlity.PicassoUtility
import com.google.android.material.snackbar.Snackbar
import com.valdesekamdem.library.mdtoast.MDToast
import kotlinx.android.synthetic.main.activity_category.view.*
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.dialog_send_comment.view.*
import org.jetbrains.anko.toast
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("ViewConstructor")
class ViewDetailActivity(
    contextInstance: Context,
    private val utility: Utility
) : FrameLayout(contextInstance), KoinComponent {


    private val imgBack: AppCompatImageView
    private val imgFavourite: AppCompatImageView
    private val txtTitle: AppCompatTextView

    private val txtName: AppCompatTextView
    private val txtDesc: JustifiedTextView
    private val txtPrice: AppCompatTextView
    private val txtDiscount: CustomTextView
    private val txtToman: CustomTextView
    private val imagePrev: AppCompatImageView
    private val commentsRecycler: RecyclerView
    private val btnAddToCard: AppCompatButton
    private val btnAddComment: AppCompatTextView
    private val progress: ProgressBar
    private val rootScrollView: ScrollView
    private val root: RelativeLayout
    private val imgNoComment:AppCompatImageView
    private val txtNoComment:AppCompatTextView

    private val picassoUtility: PicassoUtility by inject()

    init {

        val mainView = inflate(context, R.layout.activity_detail, this)

        imgBack = mainView.image_back_detail_activity
        imgFavourite = mainView.image_favourite_detail_Activity
        txtTitle = mainView.title_detail_activity

        txtName = mainView.product_name_detail_Activity
        txtDesc = mainView.product_description_detail_Activity
        txtPrice = mainView.product_price_detail_Activity
        txtDiscount = mainView.product_discount_shop_recycler
        txtToman = mainView.toman
        imagePrev = mainView.product_image_detail_Activity
        commentsRecycler = mainView.comment_recycler_detail_Activity
        btnAddToCard = mainView.btn_add_to_card_detail_Activity
        btnAddComment = mainView.add_comment_detail_Activity
        progress = mainView.progress_detail_activity
        rootScrollView = mainView.scroll_view_detail_Activity
        root = mainView.root
        imgNoComment=mainView.img_no_comment_detail_Activity
        txtNoComment=mainView.txt_no_comment_detail_Activity

    }

    fun onClickHandler(id: Int, apiService: ApiService, email: String) {
        imgBack.setOnClickListener {
            utility.onFinished()
        }


        btnAddToCard.setOnClickListener {
            apiService.getApi().addToCart(email, id)
                .enqueue(object : Callback<MSG> {
                    override fun onResponse(call: Call<MSG>, response: Response<MSG>) {
                        val data = response.body()

                        if (data != null) {
                            showCustomToastSuccess("با موفقیت به سبد خرید افزوده شد")
                        } else
                            Log.i("Error_Add_to_card", "data for adding to cart is null")

                    }

                    override fun onFailure(call: Call<MSG>, t: Throwable) {
                        Log.e("TEST", t.message.toString())
                        showCustomToastError("خطا در دریافت اطلاعات از سرور")
                    }

                })
        }

        imgFavourite.setOnClickListener {
            apiService.getApi().addToFavourite(email, id)
                .enqueue(object : Callback<DataResponse> {
                    override fun onResponse(
                        call: Call<DataResponse>,
                        response: Response<DataResponse>
                    ) {
                        val data = response.body()

                        if (data != null) {
                            if(data.error_msg == "به علاقه مندی ها افزوده شد") {
                                showCustomToastSuccess(data.error_msg)
                                setFavourite()
                            }else if(data.error_msg == "از لیست علاقه مندی ها حذف شد"){
                                showCustomToastWarning(data.error_msg)
                                notFavourite()
                            }
                        } else
                            Log.i("Error_Add_to_card", "data for adding to favourite is null")
                    }

                    override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                        showCustomToastError(t.message.toString())
                    }
                })
        }

        btnAddComment.setOnClickListener {
            showDialog(apiService,id, email)
        }
    }

    fun setTitle(title: String) {
        txtTitle.text = title
    }

    fun setDataComments(data: List<DataComments>) {

        commentsRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)

        commentsRecycler.adapter = RecyclerCommentAdapter(data)
    }

    fun setUpViews(data: DataProduct) {

        picassoUtility.setImage(data.imgAddress, imagePrev)
        txtName.text = data.title
        txtDesc.text = data.description

        if (data.discount == 1) {
            txtDiscount.visibility = View.VISIBLE
            txtToman.visibility = View.VISIBLE
            txtDiscount.setCustomText(data.price)
            txtPrice.text = data.priceDiscount
        } else {
            txtDiscount.visibility = View.GONE
            txtToman.visibility = View.GONE
            txtPrice.text = data.price
        }


    }

    fun setFavourite() {
        imgFavourite.setImageResource(R.drawable.ic_favorite)
    }

    fun notFavourite() {
        imgFavourite.setImageResource(R.drawable.ic_like)
    }

    fun showToast(text: String) {
        context.toast(text)
    }

    fun showCustomToastSuccess(text:String){
        val toast:MDToast = MDToast.makeText(context,text,MDToast.LENGTH_SHORT,MDToast.TYPE_SUCCESS)
        toast.show()
    }

    fun showCustomToastError(text:String){
        val toast:MDToast = MDToast.makeText(context,text,MDToast.LENGTH_SHORT,MDToast.TYPE_ERROR)
        toast.show()
    }

    fun showCustomToastWarning(text:String){
        val toast:MDToast = MDToast.makeText(context,text,MDToast.LENGTH_SHORT,MDToast.TYPE_WARNING)
        toast.show()
    }
    fun showSnackBar(title: String) {
        progress.visibility = View.INVISIBLE

        val snack = Snackbar.make(
            root,
            title,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.setActionTextColor(Color.RED)
        snack.setAction("تلاش مجدد") {
            snack.dismiss()
            utility.onRefreshed()
            showProgress()
        }
        snack.show()

    }

    private fun showDialog(apiService: ApiService,id: Int,email:String){


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
                                showCustomToastSuccess(data.error_msg)
                            else
                                showToast("داده خالی است")

                            mAlertDialog.dismiss()
                        }

                        override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                            showCustomToastError(t.message.toString())
                            mAlertDialog.dismiss()
                        }
                    })
            }
        }
    }

    fun hideProgress() {
        progress.visibility = View.INVISIBLE
        rootScrollView.visibility = View.VISIBLE
        btnAddToCard.visibility = View.VISIBLE
    }

    private fun showProgress() {
        progress.visibility = View.VISIBLE
        rootScrollView.visibility = View.INVISIBLE
        btnAddToCard.visibility = View.INVISIBLE
    }

    fun hideNoComment(){
        txtNoComment.visibility=INVISIBLE
        imgNoComment.visibility=INVISIBLE
        commentsRecycler.visibility= VISIBLE
    }

    fun showNoComment(){
        txtNoComment.visibility=VISIBLE
        imgNoComment.visibility=VISIBLE
        commentsRecycler.visibility= INVISIBLE
    }
}