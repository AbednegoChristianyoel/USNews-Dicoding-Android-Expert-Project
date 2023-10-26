package com.example.usnews.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.newsapp.core.domain.model.News
import com.example.usnews.R
import com.example.usnews.databinding.ActivityDetailNewsBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailNewsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_VIEW = "extra_view"

    }

    private val detailNewsViewModel: DetailNewsViewModel by viewModel()
    private lateinit var binding: ActivityDetailNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailNews = intent.getParcelableExtra<News>(EXTRA_DATA)
        showDetailNews(detailNews)

        binding.btnWebView.setOnClickListener {
            val uri = Uri.parse("newsapp://webview")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.putExtra(EXTRA_VIEW, detailNews)
            startActivity(intent)
        }

    }

    private fun showDetailNews(detailNews: News?) {
        detailNews?.let {
            binding.tvJudulArtikel.text = detailNews.title
            binding.tvDetailDescription.text = detailNews.description
            Glide.with(this)
                .load(detailNews.urlToImage)
                .into(binding.ivArtikel)

            var statusFavorite = detailNews.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailNewsViewModel.setFavoriteNews(detailNews, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }
}