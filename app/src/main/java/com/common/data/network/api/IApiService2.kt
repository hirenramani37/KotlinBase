package com.common.data.network.api

import com.common.data.network.model.ResponseUser
import com.common.data.network.model.request.ReqLogin
import com.google.gson.GsonBuilder
import com.demo.BuildConfig
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IApiService2 : IBaseService {

    @POST("login")
    suspend fun login(@Body reqLogin: ReqLogin): Response<ResponseUser>

    @GET("dummy")
    suspend fun dummy(): Response<Any>

    companion object {
        fun getService(needEncrypt: Boolean): IApiService2 {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BaseUrl)
                .client(IBaseService.getOkHttpClient())
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder().setLenient().create()
                    )
                )
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(IApiService2::class.java)
        }
    }
}