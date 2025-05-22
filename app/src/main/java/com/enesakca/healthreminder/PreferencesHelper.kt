package com.enesakca.healthreminder

import android.content.Context



    fun saveRememberMe(context: Context, remember: Boolean) {
        val prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        prefs.edit().putBoolean("remember_me", remember).apply()
    }

    fun isRememberMe(context: Context): Boolean {
        val prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        return prefs.getBoolean("remember_me", false)
    }

    fun clearRememberMe(context: Context) {
        val prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        prefs.edit().putBoolean("remember_me", false).apply()
    }
