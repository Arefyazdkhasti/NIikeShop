package com.example.nikeshop.net

import com.example.nikeshop.dataClass.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


class ApiService {

    interface DataApi {

        @GET("getBannerImages.php")
        fun getImagesUrlForSlider(): Call<DataImageUrl>

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


        @GET("ShoppingCartSize")
        fun getCartSize(@Query("email") email: String): Call<DataResponse>

        @FormUrlEncoded
        @POST("getProductById.php")
        fun getProductById(@Field("id") id: Int): Call<DataProduct>

        @FormUrlEncoded
        @POST("addToCart.php")
        fun addToCart(
            @Field("email") email: String,
            @Field("product") id: Int,
        ): Call<MSG>

        @FormUrlEncoded
        @POST("showCart.php")
        fun showCart(@Field("email") email: String): Call<List<DataProduct>>

        @FormUrlEncoded
        @POST("removeProductFromShoppingCart.php")
        fun removeProduct(
            @Field("email") email: String,
            @Field("product_id") product_id: Int
        ): Call<DataResponse>

        @FormUrlEncoded
        @POST("addToFavourite.php")
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
        ) :Call<DataResponse>

        @FormUrlEncoded
        @POST("getProductComments.php")
        fun getProductComments(
            @Field("product_id") product_id: Int,
        ):Call<List<DataComments>>

        @FormUrlEncoded
        @POST("getUserComments.php")
        fun getUserComments(
            @Field("email") email: String,
            @Field("status") status: Int
        ):Call<List<DataComments>>

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
        @GET("search.php")
        fun search(@Query("name") name:String):Call<List<DataProduct>>

    }

    fun getApi(): DataApi =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.78.2/NikeShoes/")
            .build()
            .create(DataApi::class.java)

}