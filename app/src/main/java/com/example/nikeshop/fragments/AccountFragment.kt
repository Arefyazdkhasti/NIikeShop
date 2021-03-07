package com.example.nikeshop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nikeshop.Model.ModelAccountFragment
import com.example.nikeshop.Presenter.PresenterAccountFragment
import com.example.nikeshop.R
import com.example.nikeshop.activities.CommentActivity
import com.example.nikeshop.activities.FavouriteActivity
import com.example.nikeshop.activities.QuestionActivity
import kotlinx.android.synthetic.main.fragment_account.*
import org.jetbrains.anko.startActivity


class AccountFragment : Fragment() {


    private lateinit var presenter: PresenterAccountFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val model = ModelAccountFragment(activity?.applicationContext)

        presenter = PresenterAccountFragment(this, model)
        presenter.onCreate()

        favourite_layout_account_fragment.setOnClickListener {
            context?.startActivity<FavouriteActivity>()
        }
        question_layout_account_fragment.setOnClickListener {
            context?.startActivity<QuestionActivity>()
        }
        comment_layout_account_fragment.setOnClickListener {
            context?.startActivity<CommentActivity>()
        }
    }

    fun setUserData(name: String, email: String) {
        txt_name_account_fragment.text = name
        txt_email_account_fragment.text = email
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}