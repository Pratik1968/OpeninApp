package com.pratikshekhar.openinapp.database

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class AuthorizationToken(activity: Activity) {
    private  lateinit var sharedPreferences:SharedPreferences
    init {
        sharedPreferences = activity.getSharedPreferences("cache", Context.MODE_PRIVATE)
    }
    fun addToken(token:String){
      with (sharedPreferences.edit()){
            putString("authorizationToken",token)
          apply()
        }

    }
    fun getToken():String{
        return  sharedPreferences.getString("authorizationToken","")?:""
    }
}