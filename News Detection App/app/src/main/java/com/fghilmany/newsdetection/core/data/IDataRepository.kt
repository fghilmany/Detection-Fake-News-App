package com.fghilmany.newsdetection.core.data

import com.fghilmany.movieapp.core.data.Resource
import com.fghilmany.newsdetection.core.data.resource.remote.response.NewsPredictResponse
import kotlinx.coroutines.flow.Flow

interface IDataRepository {

    fun postNewsDetect(news: String): Flow<Resource<NewsPredictResponse>>

}
