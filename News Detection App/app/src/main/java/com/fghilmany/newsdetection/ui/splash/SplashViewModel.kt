package com.fghilmany.newsdetection.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fghilmany.newsdetection.core.data.DataRepository
import com.fghilmany.newsdetection.helper.EspressoIdlingResource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel() : ViewModel(){

    val liveData: LiveData<SplashState>
        get() = mutableLiveData
    private val mutableLiveData = MutableLiveData<SplashState>()
    init {
        GlobalScope.launch {
            delay(1500)
            mutableLiveData.postValue(SplashState.MainActivity)
            delay(1300)
            mutableLiveData.postValue(SplashState.TextStart)
        }
    }

}

sealed class SplashState {
    object MainActivity : SplashState()
    object TextStart: SplashState()
}