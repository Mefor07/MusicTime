package com.ray.musictime.model

data class Albums(
    val feed: Feed
)

data class Author(
    val name: String,
    val url: String
)

data class Feed(
    val author: Author,
    val copyright: String,
    val country: String,
    val icon: String,
    val id: String,
    val links: List<Link>,
    val results: List<Result>,
    val title: String,
    val updated: String
)

data class Genre(
    val genreId: String,
    val name: String,
    val url: String
)

data class Link(
    val self: String
)

data class Result(
    val artistId: String,
    val artistName: String,
    val artistUrl: String,
    val artworkUrl100: String,
    val contentAdvisoryRating: String,
    val genres: List<Genre>,
    val id: String,
    val kind: String,
    val name: String,
    val releaseDate: String,
    val url: String
)