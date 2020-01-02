package com.gmail.ayteneve93.playstorelinker

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class CommonApiResult<R>(
    @field:Json(name = "statusCode") val statusCode: Int,
    @field:Json(name = "msg") val msg: String,
    @field:Json(name = "body") val body : R
)

object ApplicationInfoData {
    @Keep
    data class ApplicationInfoRequestBody(
        val applicationId: String
    )

    @Keep
    data class ApplicationInfoResultBody(
        @field:Json(name = "title") val title: String,
        @field:Json(name = "description") val description : String,
        @field:Json(name = "descriptionHTML") val descriptionHTML : String,
        @field:Json(name = "summary") val summary : String,
        @field:Json(name = "installs") val installs : String,
        @field:Json(name = "minInstalls") val minInstalls : Int,
        @field:Json(name = "score") val score : Int,
        @field:Json(name = "scoreText") val scoreText : String,
        @field:Json(name = "ratings") val ratings : Int,
        @field:Json(name = "reviews") val reviews : Int,
        /**
         * 기타 내용들은 추후에 추가...
         */
        @field:Json(name = "version") val version : String

    )
}