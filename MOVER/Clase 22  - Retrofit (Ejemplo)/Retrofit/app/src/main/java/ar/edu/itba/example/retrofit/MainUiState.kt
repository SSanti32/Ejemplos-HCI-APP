package ar.edu.itba.example.retrofit

import ar.edu.itba.example.retrofit.data.network.models.NetworkListUsers

data class MainUiState(
    val users: NetworkListUsers? = null,
    val isLoading: Boolean = false,
    val message: String? = null
)

val MainUiState.hasError: Boolean get() = message != null