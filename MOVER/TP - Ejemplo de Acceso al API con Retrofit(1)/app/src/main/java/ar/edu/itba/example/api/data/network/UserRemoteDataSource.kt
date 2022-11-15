package ar.edu.itba.example.api.data.network

import ar.edu.itba.example.api.data.network.api.ApiUserService
import ar.edu.itba.example.api.data.network.model.NetworkCredentials
import ar.edu.itba.example.api.data.network.model.NetworkUser
import ar.edu.itba.example.api.util.SessionManager

// DataSource: xq es un DataSource...
// Remote: xq es parte de la api
// User: xq es para manejar los usuarios
// "Lo logico es tener un DataSource por cada entidad de mi sistema y
// tambien un repositorio por por cada entidad de mi sistema"
class UserRemoteDataSource(
    private val sessionManager: SessionManager,
    private val apiUserService: ApiUserService
) : RemoteDataSource() {

    suspend fun login(username: String, password: String) {
        val response = handleApiResponse {
            apiUserService.login(NetworkCredentials(username, password))
        } // aca esta si hay algun error o exeption
        sessionManager.saveAuthToken(response.token)
    }

    suspend fun logout() {
        handleApiResponse { apiUserService.logout() }
        sessionManager.removeAuthToken()
    }

    suspend fun getCurrentUser() : NetworkUser {
        return handleApiResponse { apiUserService.getCurrentUser() }
    }
}