package ar.edu.itba.example.api.data.network.api

import ar.edu.itba.example.api.data.network.model.NetworkCredentials
import ar.edu.itba.example.api.data.network.model.NetworkToken
import ar.edu.itba.example.api.data.network.model.NetworkUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiUserService {
    @POST("users/login")
    suspend fun login(@Body credentials: NetworkCredentials): Response<NetworkToken>

    @POST("users/logout")
    suspend fun logout(): Response<Unit>
    // como este no esta devolviendo nada le ponemos Unit dentro de Response
    // es una especie de void

    @GET("users/current")
    suspend fun getCurrentUser(): Response<NetworkUser>
}
