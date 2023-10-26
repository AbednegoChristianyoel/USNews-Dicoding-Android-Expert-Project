package com.example.newsapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val title: String?,
    val publishedAt: String?,
    val author: String?,
    val urlToImage: String?,
    val description: String?,
    val url: String?,
    val content: String?,
    var isFavorite: Boolean = false
): Parcelable