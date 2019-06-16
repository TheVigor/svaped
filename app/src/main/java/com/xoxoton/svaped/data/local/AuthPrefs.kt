package com.xoxoton.svaped.data.local

import android.content.Context

class AuthPrefs(context: Context){
    private val PREFS_FILENAME = "auth_prefs"
    private val AUTH_TOKEN = "auth_token"

    private val prefs = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    var authToken: String
        get() = prefs.getString(AUTH_TOKEN, "")!!
        set(value) = prefs.edit().putString(AUTH_TOKEN, value).apply()

    fun isUserLoggedIn() = !authToken.isEmpty()
}