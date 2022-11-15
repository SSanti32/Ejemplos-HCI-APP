package ar.edu.itba.example.api.util

import android.content.Context
import android.content.SharedPreferences
import ar.edu.itba.example.api.R

class SessionManager (context: Context) {
    // esto seria como el local storage de web
    private var preferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        // el contexto depende de donde estemos, por momentos puede
        // ser la aplicacion, la activity u otro tipo de contexto

    fun loadAuthToken(): String? {
        return preferences.getString(AUTH_TOKEN, null)
    }

    fun removeAuthToken() {
        val editor = preferences.edit()
        editor.remove(AUTH_TOKEN)
        editor.apply()
    }

    fun saveAuthToken(token: String) {
        val editor = preferences.edit()
        editor.putString(AUTH_TOKEN, token)
        editor.apply()
    }

    companion object {
        const val PREFERENCES_NAME = "my_app"
        const val AUTH_TOKEN = "auth_token" // una key
    }
}