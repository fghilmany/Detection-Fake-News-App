package com.fghilmany.newsdetection.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fghilmany.newsdetection.core.data.DataRepository

class DashboardViewModel (private val dataRepository: DataRepository): ViewModel(){

    private lateinit var news: String

    fun setNews(news: String){
        this.news = news
    }

    fun postNews() = dataRepository.postNewsDetect(news).asLiveData()

}