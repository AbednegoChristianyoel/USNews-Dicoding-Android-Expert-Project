package com.example.newsapp.webview.di

import com.example.newsapp.webview.WebViewViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val webViewModule = module {
    viewModel { WebViewViewModel(get()) }
}