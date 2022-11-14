package ar.edu.itba.example.retrofit.data.network.models

import com.google.gson.annotations.SerializedName

data class NetworkSupport (
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("text")
    var text: String? = null
)