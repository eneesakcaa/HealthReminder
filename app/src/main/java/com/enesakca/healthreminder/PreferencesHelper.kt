package com.enesakca.healthreminder

import android.content.Context

class PreferencesHelper(context: Context) {

    private val sharedPref = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    var rememberMe: Boolean
        get() = sharedPref.getBoolean("remember_me", true)
        set(value) = sharedPref.edit().putBoolean("remember_me", value).apply()

    var currentUserId: Int
        get() = sharedPref.getInt("current_user_id", -1)
        set(value) = sharedPref.edit().putInt("current_user_id", value).apply()
}
