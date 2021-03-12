package com.example.nikeshop.net

import com.example.nikeshop.dataClass.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


class ApiService {

    interface DataApi {

        @GET("getBannerImages.php")
        fun getImagesUrlForBanners(): Call<DataImageUrl>

        @GET("getSliderImages.php")
        fun getSliderImagesUrl(): Call<DataImageUrl>
        @GET("categories.php")

        fun getDataCategory(): Call<List<DataCategory>>

        //get 10 item for home
        @GET("newProducts.php")
        fun getNewProducts(): Call<List<DataProduct>>

        //get 10 item for home
        @GET("topSellingProducts.php")
        fun getTopSellingProducts(): Call<List<DataProduct>>

        //get 10 item for home
        @GET("getDiscountProducts.php")
        fun getDiscountProducts(): Call<List<DataProduct>>

        @GET("getNewProductsByCategory.php")
        fun getDataNewProductsByCategoryId(@Query("id") id: Int): Call<List<DataProduct>>

        @GET("getDataTopSellingByCategory.php")
        fun getDataTopSellingByCategoryId(@Query("id") id: Int): Call<List<DataProduct>>

        @GET("dataCategory.php")
        fun getDataForCategory(@Query("sortBy") sortBy: String): Call<List<DataProduct>>

        @GET("questions.php")
        fun getDataQuestion(): Call<List<DataQuestion>>


        @FormUrlEncoded
        @POST("getProductById.php")
        fun getProductById(@Field("id") id: Int): Call<DataProduct>

        @FormUrlEncoded
        @POST("ShoppingCartSize.php")
        fun getCartSize(@Field("email") email: String): Call<DataResponse>

        @FormUrlEncoded
        @POST("addToCart.php")
        fun addToCart(
            @Field("email") email: String,
            @Field("product") id: Int,
        ): Call<MSG>

        @FormUrlEncoded
        @POST("showCart.php")
        fun showCart(@Field("email") email: String): Call<List<DataCart>>

        @FormUrlEncoded
        @POST("removeProductFromShoppingCart.php")
        fun removeProduct(
            @Field("email") email: String,
            @Field("product_id") product_id: Int
        ): Call<DataResponse>

        @FormUrlEncoded
        @POST("updateCartProductCount.php")
        fun updateProductCountInCart(
            @Field("email") email: String,
            @Field("product_id") product_id: Int,
            @Field("count") count: Int
        ): Call<DataResponse>

        @FormUrlEncoded
        //@POST("addToFavourite.php") //local
        @POST("AddToFavourite.php")
        fun addToFavourite(
            @Field("email") email: String,
            @Field("product") id: Int,
        ): Call<DataResponse>

        @FormUrlEncoded
        @POST("favourites.php")
        fun showFavourite(@Field("email") email: String): Call<List<DataProduct>>

        @FormUrlEncoded
        @POST("removeProductFromFavourite.php")
        fun removeFavourite(
            @Field("email") email: String,
            @Field("product") product_id: Int
        ): Call<DataResponse>

        @FormUrlEncoded
        @POST("isFavourite.php")
        fun isOnYourFavourite(
            @Field("email") email: String,
            @Field("product") product_id: Int
        ): Call<DataResponse>

        @FormUrlEncoded
        @POST("addComment.php")
        fun addComment(
            @Field("title") title: String,
            @Field("author") email: String,
            @Field("product_id") product_id: Int,
            @Field("content") content: String
        ): Call<DataResponse>

        @FormUrlEncoded
        @POST("removeComment.php")
        fun removerComment(
            @Field("email") author: String, @Field("product_id") product_id: Int
        ): Call<DataResponse>

        @FormUrlEncoded
        @POST("getProductComments.php")
        fun getProductComments(
            @Field("product_id") product_id: Int,
        ): Call<List<DataComments>>

        @FormUrlEncoded
        @POST("getUserComments.php")
        fun getUserComments(
            @Field("email") email: String,
            @Field("status") status: Int
        ): Call<List<DataYourComment>>

        @FormUrlEncoded
        @POST("login.php")
        fun userLogin(
            @Field("email") email: String,
            @Field("password") password: String
        ): Call<DataLogin>

        @FormUrlEncoded
        @POST("register.php")
        fun userRegister(
            @Field("email") email: String,
            @Field("username") username: String,
            @Field("password") password: String
        ): Call<DataLogin>

        //TODO how to implement search in MVP
        @GET("SearchAndSort.php")
        fun search(
            @Query("search") name: String,
            @Query("sortBy") sortBy: String
        ): Call<List<DataProduct>>

        @FormUrlEncoded
        @POST("setReceiverData.php")
        fun sendReceiverData(
            @Field("email") user_email:String,
            @Field("nameAndFamily") nameAndFamily:String,
            @Field("postalCode") postalCode:String,
            @Field("number") number:String,
            @Field("address") address:String
        ):Call<DataReceiver>

        @FormUrlEncoded
        @POST("storeBoughtBasket.php")
        fun finishBasket(
            @Field("email") user_email:String,
            @Field("basket_id") basket_id:String
        ):Call<DataResponse>

        @FormUrlEncoded
        @POST("getUserReceiver.php")
        fun getUserReceiver(@Field("email") email: String):Call<DataReceiver>

        @FormUrlEncoded
        @POST("getUserFormerBought.php")
        fun getUserFormerBought(
            @Field("email") email: String,
            @Field("basket_id") basket_id: String
        ):Call<List<DataBoughtDetail>>

        @FormUrlEncoded
        @POST("getUserBasketsID.php")
        fun getUserFormerBasket(
            @Field("email") email: String,
        ):Call<List<DataFormerBasket>>

        @FormUrlEncoded
        @POST("setCartTotalCost.php")
        fun setCartTotalCost(
            @Field("basket_id") basket_id: String,
            @Field("delivery") delivery:String,
            @Field("total") total :String
        ):Call<DataResponse>

        @FormUrlEncoded
        @POST("getBasketPrice.php")
        fun getCartTotalCost(
            @Field("basket_id") basket_id: String,
        ):Call<MSG>


        @FormUrlEncoded
        @POST("getProductWaitingForComment.php")
        fun getProductWaitingForComment(
            @Field("email") email: String,
        ):Call<List<DataProduct>>

    }

   /* fun getApi(): DataApi =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://arefnikeshop.000webhostapp.com/files/")
            .build()
            .create(DataApi::class.java)*/

    fun getApi(): DataApi =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.78.2/NikeShoes/")
            .build()
            .create(DataApi::class.java)


}