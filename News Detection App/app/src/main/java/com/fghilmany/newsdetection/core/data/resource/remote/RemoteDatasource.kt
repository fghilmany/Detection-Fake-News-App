package com.fghilmany.newsdetection.core.data.resource.remote

import com.fghilmany.muslimlifecycleapk.core.data.source.remote.network.ApiResponse
import com.fghilmany.newsdetection.core.data.resource.remote.network.ApiService
import com.fghilmany.newsdetection.core.data.resource.remote.response.NewsPredictResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import timber.log.Timber

class RemoteDatasource(
        private val apiService: ApiService?
) {
    suspend fun postNewsDetect(news: String): Flow<ApiResponse<NewsPredictResponse>>{
        return flow {
            try {
                val response = apiService?.predictNews(news)
                if (response != null){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            }catch (e: HttpException){
                emit(ApiResponse.Error(e.toString()))
                Timber.tag("postNewsDetect").e(e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}