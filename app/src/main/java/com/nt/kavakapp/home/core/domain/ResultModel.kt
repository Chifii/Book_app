package com.nt.kavakapp.home.core.domain

import com.google.gson.annotations.SerializedName

data class BooksModel(
    @SerializedName("books") val books: List<HomeModel>,
    @SerializedName("best_sellers") val best_sellers:  List<String>?
)

data class ResultModel(
    @SerializedName("results") val results: BooksModel?
)
