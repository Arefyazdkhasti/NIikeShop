package com.example.nikeshop.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nikeshop.MainActivity
import com.example.nikeshop.Presenter.PresenterRegisterFragment
import com.example.nikeshop.R
import com.example.nikeshop.activities.LoginActivity
import com.example.nikeshop.dataClass.DataLogin
import com.example.nikeshop.net.ApiService
import kotlinx.android.synthetic.main.fragment_register.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterFragment : Fragment() {

    private val presenter: PresenterRegisterFragment by inject()
    private val apiService: ApiService by inject()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_register_fragment.setOnClickListener {

            if (testField()) {

                progressBar_register_fragment.visibility = View.VISIBLE

                apiService.getApi()
                    .userRegister(
                        editText_email_register_fragment.text.toString(),
                        editText_name_register_fragment.text.toString(),
                        editText_password_register_fragment.text.toString()
                    ).enqueue(object : Callback<DataLogin> {
                        override fun onResponse(
                            call: Call<DataLogin>,
                            response: Response<DataLogin>
                        ) {
                            progressBar_register_fragment.visibility = View.INVISIBLE

                            val data = response.body()

                            if (data != null) {
                                if (!data.error) {

                                    //set login pref true
                                    val pref = activity?.getSharedPreferences(
                                        LoginActivity.LOGIN_PREF,
                                        Context.MODE_PRIVATE
                                    )
                                    val editor = pref?.edit()
                                    editor?.putBoolean(LoginActivity.IS_LOGGED_IN, true)
                                    editor?.putString(
                                        LoginActivity.USER_NAME,
                                        editText_name_register_fragment.text.toString()
                                    )
                                    editor?.putString(
                                        LoginActivity.USER_EMAIL,
                                        editText_email_register_fragment.text.toString()
                                    )
                                    editor?.apply()

                                    //start mainActivity
                                    startActivity<MainActivity>()

                                    showToast(data.msg)

                                } else
                                    showToast(data.msg)
                            }
                        }

                        override fun onFailure(call: Call<DataLogin>, t: Throwable) {
                            progressBar_register_fragment.visibility = View.INVISIBLE
                            showToast("خطای اتصال به اینترنت")
                        }

                    })

            }
        }
        presenter.onCreate()
    }


    private fun testField(): Boolean {


        if (editText_email_register_fragment.text.toString()
                .isEmpty() || editText_password_register_fragment.text.toString().isEmpty()
        ) {
            showToast("ایمیل و رمز عبور خود را وارد کنید")
            return false
        }

        return true
    }

    fun showToast(text: String) {
        toast(text)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }


}