package com.fghilmany.newsdetection.di
import com.fghilmany.newsdetection.ui.dashboard.DashboardViewModel
import com.fghilmany.newsdetection.ui.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {SplashViewModel() }
    viewModel {DashboardViewModel(get()) }

}