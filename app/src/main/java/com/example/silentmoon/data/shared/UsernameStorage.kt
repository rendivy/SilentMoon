package com.example.silentmoon.data.shared

import android.content.Context
import android.content.SharedPreferences

class UsernameStorage(private var applicationContext: Context) {


    private companion object {
        const val STORAGE_NAME = "userStorage"
        const val USERNAME_KEY = "username"
        const val DEFAULT_USERNAME = "Yuriy"
    }

    private var sharedPreferences =
        applicationContext.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)


    fun saveUsername(username: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(USERNAME_KEY, username)
        editor.apply()
    }

    fun getUsername(): String {
        return sharedPreferences.getString(USERNAME_KEY, "") ?: DEFAULT_USERNAME
    }
}