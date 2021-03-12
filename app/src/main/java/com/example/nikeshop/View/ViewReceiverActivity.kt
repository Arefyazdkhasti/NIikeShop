package com.example.nikeshop.View

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.nikeshop.MainActivity
import com.example.nikeshop.R
import com.example.nikeshop.`interface`.Utility
import com.example.nikeshop.dataClass.DataReceiver
import com.example.nikeshop.dataClass.DataResponse
import com.example.nikeshop.enumration.TypePayment
import com.example.nikeshop.net.ApiService
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.textfield.TextInputEditText
import com.valdesekamdem.library.mdtoast.MDToast
import kotlinx.android.synthetic.main.activity_reciever.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

@SuppressLint("ViewConstructor")
class ViewReceiverActivity(
    contextInstance: Context,
    private val utility: Utility
) : FrameLayout(contextInstance) {

    private var dataAlreadySet = false

    private val imgBack: AppCompatImageView

    private val edtName: TextInputEditText
    private val edtPostalCode: TextInputEditText
    private val edtNumber: TextInputEditText
    private val edtAddress: TextInputEditText

    private val txtTotalPrice: AppCompatTextView
    private val txtFinalPrice: AppCompatTextView
    private val txtDeliveryPrice: AppCompatTextView

    private val toggleGroup: MaterialButtonToggleGroup

    private val btnPay: MaterialButton
    private val btnSendData: MaterialButton
    private val progressSendData: ProgressBar
    private val progressFinishPayment: ProgressBar

    init {
        val mainView = inflate(context, R.layout.activity_reciever, this)

        imgBack = mainView.image_back_receiver_activity

        edtName = mainView.edt_name_and_family_receiver_activity
        edtPostalCode = mainView.edt_postal_code_receiver_activity
        edtNumber = mainView.edt_number_number_receiver_activity
        edtAddress = mainView.edt_address_receiver_activity

        txtTotalPrice = mainView.total_price_receiver_activity
        txtFinalPrice = mainView.final_price_receiver_activity
        txtDeliveryPrice = mainView.delivery_cost_receiver_activity

        toggleGroup = mainView.toggleButton_receiver_activity
        toggleGroup.check(R.id.btn_internet_pay_receiver_activity)

        btnPay = mainView.btn_pay_receiver_activity
        btnSendData = mainView.upload_receiver_data_receiver_activity
        progressSendData = mainView.progress_bar_send_data_receiver_activity
        progressFinishPayment=mainView.progress_bar_finish_pay_receiver_activity

    }

    fun onBackClickHandler() {
        imgBack.setOnClickListener {
            utility.onFinished()
        }
    }

    fun setPrice(total: Int, final: Int) {
        txtTotalPrice.text = total.toString()
        txtDeliveryPrice.text = "رایگان"
        txtFinalPrice.text = final.toString()
    }

    fun getPayment(): TypePayment {

        return when (toggleGroup.checkedButtonId) {
            R.id.btn_internet_pay_receiver_activity -> TypePayment.ONLINE_PAY
            R.id.btn_home_pay_receiver_activity -> TypePayment.HOME_PAY
            else -> TypePayment.ONLINE_PAY
        }

    }

    //pass to the payment plan and do the buying
    fun getFinalPrice(): String {
        return txtFinalPrice.text.toString()
    }

    fun onClickHandler(email: String, apiService: ApiService) {
        btnSendData.setOnClickListener {
            if (!dataAlreadySet) {
                if (testField()) {

                    val name = edtName.text.toString()
                    val postalCode = edtPostalCode.text.toString()
                    val number = edtNumber.text.toString()
                    val address = edtAddress.text.toString()

                    showSendDataProgress()
                    
                    apiService.getApi().sendReceiverData(
                        email,
                        name,
                        postalCode,
                        number,
                        address
                    ).enqueue(object : Callback<DataReceiver> {
                        override fun onResponse(
                            call: Call<DataReceiver>,
                            response: Response<DataReceiver>
                        ) {
                            val data = response.body()

                            if (data != null) {
                                Log.i("RECEIVER_DATA", "اطلاعات دریافت کننده ثبت شد!")
                                showToastSuccess("اطلاعات دریافت کننده ثبت شد!")
                                hideSendDataProgress()
                                //TODO finish payment!
                            } else {
                                Log.i("RECEIVER_DATA", "خطا در ثبت اطلاعات دریافت کننده!")
                                hideSendDataProgress()
                            }
                        }

                        override fun onFailure(call: Call<DataReceiver>, t: Throwable) {
                            showToast(t.message.toString())
                            Log.i(
                                "RECEIVER_DATA",
                                " ${t.message.toString()}خطا در ثبت اطلاعات دریافت کننده! "
                            )
                            hideSendDataProgress()
                        }
                    })
                }
            }
        }
    }

    fun finishBasket(email: String, apiService: ApiService, total: Int) {

        btnPay.setOnClickListener {
            showFinishProgress()
            val uniqueID = UUID.randomUUID().toString()

            apiService.getApi().finishBasket(email, uniqueID)
                .enqueue(object : Callback<DataResponse> {
                    override fun onResponse(
                        call: Call<DataResponse>,
                        response: Response<DataResponse>
                    ) {
                        val data = response.body()

                        if (data != null) {
                            showToastSuccess(data.error_msg)
                            hideFinishProgress()

                            sendTotalCost(total.toString(), uniqueID, apiService)

                            context.startActivity<MainActivity>()
                        } else {
                            showToast("خطا در تکمیل فرآیند خرید")
                            Log.e("FINISH_BASKET", "خطا در ارسال اطلاعات خرید")
                            hideFinishProgress()
                        }
                    }

                    override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                        showToast("خطا در ارسال اطلاعات به سرور")
                        Log.e("FINISH_BASKET", t.message.toString())
                        hideFinishProgress()
                    }
                })
        }
    }

    private fun sendTotalCost(total: String, basket_id: String, apiService: ApiService) {
        apiService.getApi().setCartTotalCost(basket_id, "رایگان", total)
            .enqueue(object : Callback<DataResponse> {
                override fun onResponse(
                    call: Call<DataResponse>,
                    response: Response<DataResponse>
                ) {
                    val d = response.body()

                    if (d != null) {
                        Log.i("SEND_PRICE", d.error_msg)
                    } else {
                        Log.e("SEND_PRICE", "خطا در ارسال قیمت")
                    }
                }

                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                    Log.e("SEND_PRICE", t.message.toString())

                }

            })
    }

    private fun testField(): Boolean {
        if (edtName.text.toString().isEmpty() ||
            edtPostalCode.text.toString().isEmpty() ||
            edtNumber.text.toString().isEmpty() ||
            edtAddress.text.toString().isEmpty()
        ) {
            showToast("لطفا همه فیلد ها را وارد کنید")
            return false
        }
        return true
    }

    fun setDataInEdt(name: String, postalCode: String, number: String, address: String) {
        edtName.setText(name)
        edtPostalCode.setText(postalCode)
        edtNumber.setText(number)
        edtAddress.setText(address)
    }

    fun editEnable() {
        //TODO let user edit receiver data
        edtName.isEnabled = false
        edtPostalCode.isEnabled = false
        edtNumber.isEnabled = false
        edtAddress.isEnabled = false
        dataAlreadySet = true
        btnSendData.isEnabled = false
    }

    fun showToast(text: String) {
        context.toast(text)
    }

    fun showToastSuccess(text: String) {
        MDToast.makeText(context, text, MDToast.TYPE_SUCCESS).show()
    }

    fun showSendDataProgress() {
        progressSendData.visibility = VISIBLE
    }

    fun hideSendDataProgress() {
        progressSendData.visibility = INVISIBLE
    }

    fun showFinishProgress() {
        progressFinishPayment.visibility = VISIBLE
    }

    fun hideFinishProgress() {
        progressFinishPayment.visibility = INVISIBLE
    }
}