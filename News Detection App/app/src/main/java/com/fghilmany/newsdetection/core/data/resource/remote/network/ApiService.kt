package com.fghilmany.newsdetection.core.data.resource.remote.network

import com.fghilmany.newsdetection.core.data.resource.remote.response.NewsPredictResponse
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("predict")
    suspend fun predictNews(
        @Field("news") news: String
    ): NewsPredictResponse

}