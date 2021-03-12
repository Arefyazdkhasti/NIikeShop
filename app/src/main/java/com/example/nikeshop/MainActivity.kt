package com.example.nikeshop

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nikeshop.Model.ModelMainActivity
import com.example.nikeshop.Presenter.PresenterMainActivity
import com.example.nikeshop.View.ViewMainActivity
import com.example.nikeshop.`interface`.SetFragment
import com.example.nikeshop.`interface`.Utility
import com.example.nikeshop.activities.LoginActivity
import com.example.nikeshop.dataClass.DataResponse
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), SetFragment, Utility {

    private lateinit var presenter: PresenterMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkLogin()

        val view = ViewMainActivity(this, this, this)
        val model = ModelMainActivity(this)
        setContentView(view)

        presenter = PresenterMainActivity(view, model)
        presenter.onCreate()

    }

    public override fun onRestart() {
        super.onRestart()
        presenter.onRefresh()
    }

    override fun onRefreshed() {
        super.onRefreshed()
        presenter.onRefresh()
    }

    public override fun onStart() {
        super.onStart()
        presenter.onRefresh()
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    //TODO when back pressed went to login again . but it should get completely out of the app
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("آیا میخواهید خارج شوید؟")
            .setCancelable(false)
            .setPositiveButton("بله") { dialog, id ->
                finish()
                dialog.dismiss()
            }
            .setNegativeButton("بیخیال") { dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }
    override fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_frame, fragment)
            .commit()
    }

    override fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frame, fragment)
            .commit()
    }

    override fun onFinished() {
        finish()
    }

    private fun checkLogin() {
        val pref = getSharedPreferences(LoginActivity.LOGIN_PREF, Context.MODE_PRIVATE)

        if (!pref.getBoolean(LoginActivity.IS_LOGGED_IN, false))
            startActivity<LoginActivity>()

    }

}