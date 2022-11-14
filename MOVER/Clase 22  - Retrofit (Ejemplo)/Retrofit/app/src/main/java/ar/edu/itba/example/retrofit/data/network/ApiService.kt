package ar.edu.itba.example.retrofit.data.network

import ar.edu.itba.example.retrofit.data.network.models.NetworkListUsers
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/users")
    suspend fun getAllUsers(@Query("page")page: Int): Response<NetworkListUsers>
}