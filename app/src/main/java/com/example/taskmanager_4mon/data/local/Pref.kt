package com.example.taskmanager_4mon.data.local

import android.content.Context

class Pref(private val context: Context) {

    private val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun onShowed(): Boolean {
        return pref.getBoolean(SHOWED_KEY, false)
    }

    fun onBoardingShow() {
        pref.edit().putBoolean(SHOWED_KEY, true).apply()
    }

    fun saveText(text: String) {
        pref.edit().putString(TEXT_KEY, text).apply()
    }

    fun getText(): String? {
        return pref.getString(TEXT_KEY, "")
    }

    fun saveImageUri(imageUri: String){
        pref.edit().putString(IMAGE_URI_KEY, imageUri).apply()
    }

    fun getImageUri(): String? {
        return pref.getString(IMAGE_URI_KEY, "")
    }

    companion object {
        const val IMAGE_URI_KEY = "image_uri.key"
        const val PREF_NAME = "pref.name"
        const val SHOWED_KEY = "showed.key"
        const val TEXT_KEY = "text.key"
    }

}