package com.gmail.ayteneve93.playstorelinker

import com.google.gson.Gson
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object PlayStoreLinkerApiService {
    private const val BASE_URL = "https://tw7vt4bp0g.execute-api.ap-northeast-2.amazonaws.com"
    private const val STAGE = "goodsper_stage_01"
    private val requestBodyMediaType = MediaType.parse("application/json")
    private val retrofitService =
            Retrofit.Builder()
            .baseUrl("$BASE_URL/$STAGE/")
            .client(OkHttpClient.Builder().apply {
                addInterceptor { interceptorChain: Interceptor.Chain ->
                    interceptorChain.request().let { originalRequest ->
                        interceptorChain.proceed(originalRequest.newBuilder()
                            .header("Content-Type", "application/json")
                            .header("x-amz-docs-region", "ap-northeast-2")
                            .header("x-api-key", "YKD6CCnnOE58FRXDYpOPB8ydsOnGDvEb77SXiROE")
                            .method(originalRequest.method(), originalRequest.body())
                            .build())
                    }
                }
            }.build())
            .addConverterFactory(MoshiConverterFactory.create())

    suspend fun axFetchApplicationInfo(applicationInfoRequestBody : ApplicationInfoData.ApplicationInfoRequestBody) : Response<CommonApiResult<ApplicationInfoData.ApplicationInfoResultBody>> =
            retrofitService
            .build()
            .create(PlayStoreLinkerApiInterface::class.java)
            .axFetchApplicationInfo(RequestBody.create(requestBodyMediaType, Gson().toJson(applicationInfoRequestBody)))

    fun rxFetchApplicationInfo(applicationInfoRequestBody : ApplicationInfoData.ApplicationInfoRequestBody) : Single<CommonApiResult<ApplicationInfoData.ApplicationInfoResultBody>> =
        retrofitService
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(PlayStoreLinkerApiInterface::class.java)
        .rxFetchApplicationInfo(RequestBody.create(requestBodyMediaType, Gson().toJson(applicationInfoRequestBody)))


}