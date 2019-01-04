package com.canopydevelopers.canopyauth

import android.content.Context
import android.content.SharedPreferences

class AuthConfig(context: Context) {
    private val sharedPreferences:SharedPreferences = context.getSharedPreferences("com.canopydevelopers.canopyauth",Context.MODE_PRIVATE)
    fun writeloginstatus(status:Boolean): Boolean {
        val editor = sharedPreferences.edit()
        editor.putBoolean("loginstatus",status)
        return editor.commit()
    }
    fun readloginstatus(): Boolean {
        return sharedPreferences.getBoolean("loginstatus",false)
    }

}