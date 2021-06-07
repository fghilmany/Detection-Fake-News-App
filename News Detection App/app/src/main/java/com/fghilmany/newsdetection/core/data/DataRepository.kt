package com.fghilmany.newsdetection.core.data

import com.fghilmany.movieapp.core.data.Resource
import com.fghilmany.muslimlifecycleapk.core.data.OnlineResource
import com.fghilmany.muslimlifecycleapk.core.data.source.remote.network.ApiResponse
import com.fghilmany.newsdetection.core.data.resource.remote.RemoteDatasource
import com.fghilmany.newsdetection.core.data.resource.remote.response.NewsPredictResponse
import kotlinx.coroutines.flow.Flow
import java.net.IDN

class DataRepository (
        private val remoteDatasource: RemoteDatasource
): IDataRepository {
    override fun postNewsDetect(news: String): Flow<Resource<NewsPredictResponse>> {
        return object : OnlineResource<NewsPredictResponse>(){
            override suspend fun createCall(): Flow<ApiResponse<NewsPredictResponse>> {
                return remoteDatasource.postNewsDetect(news)
            }

        }.asFlow()
    }
}