package com.nt.kavakapp.home.core.domain

import com.google.gson.annotations.SerializedName

data class HomeModel(
    @SerializedName("isbn") val isbn: String,
    @SerializedName("title") val title: String?,
    @SerializedName("author") val author: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("genre") val genre: String?,
    @SerializedName("img") val img: String?,
    @SerializedName("imported") val imported: Boolean?,
)