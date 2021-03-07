package com.example.nikeshop.View.CoustomView

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeshop.R
import com.example.nikeshop.activities.ArchiveActivity
import com.example.nikeshop.adapter.RecyclerItemProductAdapter
import com.example.nikeshop.dataClass.DataProduct
import com.example.nikeshop.enumration.TypeGetProduct
import kotlinx.android.synthetic.main.produt_view.view.*
import org.jetbrains.anko.startActivity

class ProductView(
    contextInstant: Context,
    attrs:AttributeSet
):FrameLayout(contextInstant,attrs) {

    private val productTitle:AppCompatTextView
    private val productAll:AppCompatTextView
    private val productRecycleView:RecyclerView

    companion object{
        const val TITLE_KEY="title"
        const val TYPE_KEY="type"
    }

    init{
        val view= inflate(context, R.layout.produt_view,this)

        //get attrs
        val typedArray = context.obtainStyledAttributes(attrs,R.styleable.ProductView)
        val title = typedArray.getString(R.styleable.ProductView_title)
        //recycle type array after use
        typedArray.recycle()


        productTitle=view.findViewById(R.id.text_product_title)
        productAll=view.findViewById(R.id.text_product_all)
        productRecycleView=view.findViewById(R.id.product_recyclerView)

        productTitle.text=title

        productRecycleView.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true)


    }


    fun initRecycler(data:List<DataProduct>,type:TypeGetProduct){

        productAll.setOnClickListener{
            context.startActivity<ArchiveActivity>(
                TITLE_KEY to productTitle.text.toString(),
                TYPE_KEY to type
            )
        }

        product_recyclerView.adapter=RecyclerItemProductAdapter(context,data/*,type*/)
    }
}