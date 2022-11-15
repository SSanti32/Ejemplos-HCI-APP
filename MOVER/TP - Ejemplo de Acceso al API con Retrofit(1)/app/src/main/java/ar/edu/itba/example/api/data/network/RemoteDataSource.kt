package ar.edu.itba.example.api.data.network

import ar.edu.itba.example.api.data.network.model.NetworkError
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import java.io.IOException

abstract class RemoteDataSource {

    // <T : Any> --> Al T no le pongo restricciones con el Any
    suspend fun <T : Any> handleApiResponse(
        execute: suspend () -> Response<T>
    ): T {
        try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                return body
            } // hasta aca salio todo joya
            response.errorBody()?.let { // aca llame a la api y me dio error
                val gson = Gson()
                // convertir el string (que es un JSON) al objeto que queremos
                // objetos kotlin para mejorar el manejo de error
                val error = gson.fromJson<NetworkError>(it.string(), object : TypeToken<NetworkError?>() {}.type)
                // el it te da todo el JSON
                throw DataSourceException(error.code, error.description, error.details)
            }
            throw DataSourceException(UNEXPECTED_ERROR_CODE, "Missing error", null)
        } catch (e: DataSourceException) { // aca llame y me dio error la parte de Rotrofit
            throw e
        } catch (e: IOException) {
            throw DataSourceException(CONNECTION_ERROR_CODE, "Connection error", getDetailsFromException(e))
        } catch (e: Exception) {
            throw DataSourceException(UNEXPECTED_ERROR_CODE, "Unexpected error", getDetailsFromException(e))
        }
    }

    private fun getDetailsFromException(e: Exception) : List<String> {
        return if (e.message != null) listOf(e.message!!) else emptyList()
    }

    companion object {
        const val CONNECTION_ERROR_CODE = 98
        const val UNEXPECTED_ERROR_CODE = 99
    }
}