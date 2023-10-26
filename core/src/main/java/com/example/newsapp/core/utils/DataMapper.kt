package com.example.newsapp.core.utils


import com.example.newsapp.core.data.source.local.entity.NewsEntity
import com.example.newsapp.core.data.source.remote.response.NewsResponse
import com.example.newsapp.core.domain.model.News

object DataMapper {
    fun mapResponsesToEntities(input: List<NewsResponse>): List<NewsEntity> {
        val newsList = ArrayList<NewsEntity>()
        input.map {
            val news = it.title?.let { it1 ->
                NewsEntity(
                    title = it1,
                    publishedAt = it.publishedAt,
                    author = it.author,
                    urlToImage = it.urlToImage,
                    description = it.description,
                    url = it.url,
                    content = it.content,
                    isFavorite = false
                )
            }
            if (news != null) {
                newsList.add(news)
            }
        }
        return newsList
    }

    fun mapEntitiesToDomain(input: List<NewsEntity>): List<News> =
        input.map {
            News(
                title = it.title,
                publishedAt = it.publishedAt,
                author = it.author,
                urlToImage = it.urlToImage,
                description = it.description,
                url = it.url,
                content = it.content,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: News) = input.title?.let {
        NewsEntity(
        title = it,
        publishedAt = input.publishedAt,
        author = input.author,
        urlToImage = input.urlToImage,
        description = input.description,
        url = input.url,
        content = input.content,
        isFavorite = input.isFavorite
    )
    }
}