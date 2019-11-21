/**
 * @author Joyce Hong
 * @email soja0524@gmail.com
 * @created 2019-11-12
 * @desc
 */

package com.junga.wakatracker.retrofit

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface IWakaServer {

    @GET("users/current/durations")
    fun getCodingTime(
        @Query("date") date : String,
        @Query("api_key") string : String

        ): Call<RawResponseData>
}