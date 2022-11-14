package ar.edu.itba.example.retrofit.data.network.models

import com.google.gson.annotations.SerializedName

data class NetworkListUsers (
    @SerializedName("page")
    var page: Int? = null,
    @SerializedName("per_page")
    var perPage: Int? = null,
    @SerializedName("total")
    var total: Int? = null,
    @SerializedName("total_pages")
    var totalPages : Int? = null,
    @SerializedName("data")
    var data: List<NetworkData> = listOf(),
    @SerializedName("support")
    var support: NetworkSupport? = NetworkSupport()
)