package com.example.newsapp.webview

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.newsapp.core.domain.model.News
import com.example.newsapp.webview.databinding.ActivityWebViewBinding
import com.example.newsapp.webview.di.webViewModule
import com.example.usnews.R
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class WebViewActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_VIEW = "extra_view"
    }

    private lateinit var binding: ActivityWebViewBinding
    private val webViewViewModel: WebViewViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(webViewModule   )

        val detailNews = intent.getParcelableExtra<News>(EXTRA_VIEW)
        setFavNews(detailNews)



        if (detailNews != null) {
            binding.webView.loadUrl(detailNews.url.toString())
            binding.webView.settings.javaScriptEnabled = true
            binding.webView.webViewClient = object : WebViewClient(){}
        }
    }

    private fun setFavNews(detailNews: News?) {
        detailNews?.let {
            var statusFavorite = detailNews.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fabWebview.setOnClickListener {
                statusFavorite = !statusFavorite
                webViewViewModel.setFavoriteNews(detailNews, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabWebview.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fabWebview.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }
}