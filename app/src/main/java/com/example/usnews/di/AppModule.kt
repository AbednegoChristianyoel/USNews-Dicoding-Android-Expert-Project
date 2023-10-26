package com.example.usnews.di

import com.example.newsapp.core.domain.usecase.NewsInteractor
import com.example.newsapp.core.domain.usecase.NewsUseCase
import com.example.usnews.detail.DetailNewsViewModel
import com.example.usnews.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<NewsUseCase> { NewsInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailNewsViewModel(get()) }
}