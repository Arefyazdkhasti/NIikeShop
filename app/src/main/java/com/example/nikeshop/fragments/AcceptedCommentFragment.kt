package com.example.nikeshop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nikeshop.Presenter.PresenterAcceptedCommentFragment
import com.example.nikeshop.R
import com.example.nikeshop.adapter.RecyclerYourCommentAdapter
import com.example.nikeshop.dataClass.DataYourComment
import com.valdesekamdem.library.mdtoast.MDToast
import kotlinx.android.synthetic.main.fragment_accepted_comment.*
import org.jetbrains.anko.support.v4.toast
import org.koin.android.ext.android.inject


class AcceptedCommentFragment : Fragment() {


    private val presenter: PresenterAcceptedCommentFragment by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_accepted_comment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.onCreate()
    }

    fun setUpRecycler(data: List<DataYourComment>) {
        recycler_accepted_comment_fragment.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val adapter = RecyclerYourCommentAdapter(data, context)
        recycler_accepted_comment_fragment.adapter = adapter

    }

    fun hideProgress(){
        progress_accepted_comment_fragment.visibility=View.INVISIBLE
        recycler_accepted_comment_fragment.visibility=View.VISIBLE
    }
    fun showProgress(){
        progress_accepted_comment_fragment.visibility=View.VISIBLE
        recycler_accepted_comment_fragment.visibility=View.INVISIBLE
        txt_no_comment_accepted_comment_fragment.visibility=View.INVISIBLE
        img_no_comment_accepted_comment_fragment.visibility=View.INVISIBLE
    }
    fun hideNoComment(){
        progress_accepted_comment_fragment.visibility=View.INVISIBLE
        recycler_accepted_comment_fragment.visibility=View.VISIBLE
        txt_no_comment_accepted_comment_fragment.visibility=View.INVISIBLE
        img_no_comment_accepted_comment_fragment.visibility=View.INVISIBLE
    }
    fun showNoComment(){
        progress_accepted_comment_fragment.visibility=View.INVISIBLE
        recycler_accepted_comment_fragment.visibility=View.INVISIBLE
        txt_no_comment_accepted_comment_fragment.visibility=View.VISIBLE
        img_no_comment_accepted_comment_fragment.visibility=View.VISIBLE
    }

    fun showToast(text: String) {
        toast(text)
    }

    fun showCustomToastError(text:String){
        val toast: MDToast = MDToast.makeText(context,text, MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR)
        toast.show()
    }
    fun showCustomToastInfo(text:String){
        val toast: MDToast = MDToast.makeText(context,text, MDToast.LENGTH_SHORT, MDToast.TYPE_INFO)
        toast.show()
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }


}