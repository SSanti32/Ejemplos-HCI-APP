package ar.edu.itba.example.api.data.network.api

import android.content.Context
import ar.edu.itba.example.api.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object RetrofitClient {

    @Volatile
    private var instance: Retrofit? = null

    private fun getInstance(context: Context) : Retrofit =
        instance ?: synchronized(this) {
            instance ?: buildRetrofit(context).also { instance = it}
        }

    private fun buildRetrofit(context: Context) : Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .addInterceptor(httpLoggingInterceptor)
            .build()

        // esto sale de la parte de adaptar la Date al Timestamp
        // creo que si tendriamos que adaptar otro tipo de dato
        // lo tendriamos que agregar aca
        val gson = GsonBuilder()
            .registerTypeAdapter(Date::class.java, ApiDateTypeAdapter())
            .create()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            // y en este GsonConverterFactory le tenemos que pasar 
            // este gson para que sepa como adaptar el Date
            .client(okHttpClient)
            .build()
    }

    fun getApiUserService(context: Context) : ApiUserService {
        return getInstance(context).create(ApiUserService::class.java)
    }

    fun getApiSportService(context: Context) : ApiSportService {
        return getInstance(context).create(ApiSportService::class.java)
    }
}
