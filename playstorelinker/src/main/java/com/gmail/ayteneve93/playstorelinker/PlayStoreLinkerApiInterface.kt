package com.gmail.ayteneve93.playstorelinker

import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

internal interface PlayStoreLinkerApiInterface {

    @POST("mobile/playStoreLinker")
    suspend fun axFetchApplicationInfo(@Body requestBody : RequestBody) : Response<CommonApiResult<ApplicationInfoData.ApplicationInfoResultBody>>
    @POST("mobile/playStoreLinker")
    fun rxFetchApplicationInfo(@Body requestBody : RequestBody): Single<CommonApiResult<ApplicationInfoData.ApplicationInfoResultBody>>

}