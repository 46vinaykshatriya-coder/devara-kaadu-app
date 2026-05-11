package com.example.devarakaadu.session

import android.content.Context

class SessionManager(
    context: Context
) {

    private val prefs =
        context.getSharedPreferences(
            "devara_session",
            Context.MODE_PRIVATE
        )

    fun saveLogin(
        email: String
    ) {

        prefs.edit()
            .putBoolean(
                "isLoggedIn",
                true
            )
            .putString(
                "email",
                email
            )
            .apply()
    }

    fun isLoggedIn(): Boolean {

        return prefs.getBoolean(
            "isLoggedIn",
            false
        )
    }

    fun getUserEmail(): String {

        return prefs.getString(
            "email",
            ""
        ) ?: ""
    }

    fun logout() {

        prefs.edit().clear().apply()
    }
}