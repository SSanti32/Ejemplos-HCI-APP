package ar.edu.itba.example.api.data.network.api

import ar.edu.itba.example.api.data.network.model.NetworkPagedContent
import ar.edu.itba.example.api.data.network.model.NetworkSport
import retrofit2.Response
import retrofit2.http.*

interface ApiSportService {

    @GET("sports")
    suspend fun getSports() : Response<NetworkPagedContent<NetworkSport>>

    @POST("sports")
    suspend fun addSport(@Body sport: NetworkSport) : Response<NetworkSport>
    // en este caso el sport va en el Body de la solicitud

    @GET("sports/{sportId}")
    suspend fun getSport(@Path("sportId") sportId: Int) : Response<NetworkSport>
    // cuando le tenemos que pasar algo en la url, ponemos: 
    // @Path("lo_mismo_que_esta_en_el_GET")
    // esto es por si queremos cambiarle el nombre para no tener que poner 
    // el mismo que tenemos dentro del @Path, ejemplo:
    // @GET("sports/{sportId}")
    // suspend fun getSport(@Path("sportId") idSport: Int) : Response<NetworkSport>

    @PUT("sports/{sportId}")
    suspend fun modifySport(@Path("sportId") sportId: Int, @Body sport: NetworkSport) : Response<NetworkSport>

    @DELETE("sports/{sportId}")
    suspend fun deleteSport(@Path("sportId") sportId: Int) : Response<Unit>
}