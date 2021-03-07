package com.example.nikeshop.test

import com.example.nikeshop.R
import com.example.nikeshop.dataClass.DataCategory
import com.example.nikeshop.dataClass.DataComments
import com.example.nikeshop.dataClass.DataProduct

class DataTest {

    companion object {
        fun getDataProduct(): List<DataProduct> {

            val array = arrayListOf<DataProduct>()

            for (item in 1..10) {
                array.add(
                    DataProduct(
                        item,
                        "$item کفش نایکی مدل ",
                        "https://p.kindpng.com/picc/s/212-2128913_nike-air-max-sneakers-shoe-nike-flywire-nike.png",
                        "1500000",
                        1,
                        "1350000",
                        0f,
                        ""
                    )
                )
            }

            return array
        }

        fun getDataComments(): List<DataComments> {

            val array = arrayListOf<DataComments>()

            for (item in 1..3) {
                array.add(
                    DataComments(
                        item,
                        "عالی و شیک",
                        "عارف یزدخواستی",
                        "دوشنبه 11 اسفند 99",
                        "لورم ایپسوم یا طرحنما به متنی آزمایشی و بیمعنی در صنعت چاپ، صفحهآرایی و طراحی گرافیک گفته میشود. طراح گرافیک از این متن به عنوان عنصری از ترکیب بندی برای پر کردن صفحه و ارایه اولیه شکل ظاهری و کلی طرح سفارش گرفته شده استفاده می نماید، تا از نظر گرافیکی نشانگر چگونگی نوع و اندازه فونت و ظاهر متن باشد.",
                        0
                    )
                )
            }

            return array
        }

        fun getCategory(): List<DataCategory> {
            val array = arrayListOf<DataCategory>()

            for (item in 1..10) {
                array.add(
                    DataCategory(
                        item,
                        "ورزشی$item "
                    )
                )
            }
            return array
        }
    }
}