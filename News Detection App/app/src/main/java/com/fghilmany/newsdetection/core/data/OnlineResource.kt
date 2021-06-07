package com.fghilmany.muslimlifecycleapk.core.data

import com.fghilmany.movieapp.core.data.Resource
import com.fghilmany.muslimlifecycleapk.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import timber.log.Timber

abstract class OnlineResource<ResultType> {
    private var result: Flow<Resource<ResultType>> = flow {

        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(apiResponse.body))
            }
            is ApiResponse.Empty -> {}
            is ApiResponse.Error -> {
                onFetchFailed()
                Timber.e(apiResponse.errorMessage)
            }
        }

    }

    protected open fun onFetchFailed() {}

    protected abstract suspend fun createCall(): Flow<ApiResponse<ResultType>>

    fun asFlow(): Flow<Resource<ResultType>> = result
}