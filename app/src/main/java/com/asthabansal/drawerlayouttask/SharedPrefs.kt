package com.asthabansal.drawerlayouttask

import android.content.SharedPreferences
import android.content.Context

class SharedPrefs {
    var sharedPreferences:SharedPreferences?=null
    var editor:SharedPreferences.Editor?=null

    fun init(context: Context){
        if(sharedPreferences==null){
            sharedPreferences = context.getSharedPreferences(
                context.resources.getString(R.string.app_name),
                Context.MODE_PRIVATE)
            editor = sharedPreferences?.edit()
        }
    }
    fun saveName(key:String,value:String){
        editor?.putString(key,value)
        editor?.apply()
        editor?.commit()
    }
    fun getName(key:String):String{
        return sharedPreferences?.getString(key,"astha")?:"astha"
    }

}
