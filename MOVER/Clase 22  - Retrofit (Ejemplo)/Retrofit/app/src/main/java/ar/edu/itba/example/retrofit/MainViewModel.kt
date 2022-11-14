package ar.edu.itba.example.retrofit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.example.retrofit.data.network.RetrofitClient
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var uiState by mutableStateOf(MainUiState())
        private set

    private var fetchJob: Job? = null

    fun dismissMessage() {
        uiState = uiState.copy(message = null)
    }

    fun fetchUsers(page: Int) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.getAllUsers(page)
            }.onSuccess { response ->
                uiState = uiState.copy(
                    users = response.body(),
                    isLoading = false
                )
            }.onFailure { e ->
                // Handle the error and notify the UI when appropriate.
                uiState = uiState.copy(
                    message = e.message,
                    isLoading = false)
            }
        }
    }
}