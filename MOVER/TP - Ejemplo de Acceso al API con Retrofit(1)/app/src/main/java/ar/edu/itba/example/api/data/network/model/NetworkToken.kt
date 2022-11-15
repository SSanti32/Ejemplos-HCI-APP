package ar.edu.itba.example.api.data.network.model

import com.google.gson.annotations.SerializedName

data class NetworkToken (

    // este es el nombre que aparece en el JSON
    @SerializedName("token")
    var token : String
    // y este es el nombre que le queremos poner
)