package com.example.dukaan.sharedpreference

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper {
    companion object {
        private val SHARED_PREFERENCE_KEY = "com.xyz.sharedpreferences"

        fun getSharedPreference(context: Context): SharedPreferences {
            return context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
        }

        /**
         * This method is used to write an integer to the preference
         */
        fun writeIntToPreference(context: Context, key: String?, value: Int) {
            val editor = getSharedPreference(context).edit()
            editor.putInt(key, value)
            editor.apply()
        }

        /**
         * This method is used to String an integer to the preference
         */
        fun writeStringToPreference(context: Context, key: String?, value: String?) {
            val editor = getSharedPreference(context).edit()
            editor.putString(key, value)
            editor.apply()
        }

        /**
         * This method is used to get an String from the preference which is already saved in it
         */
        fun getStringFromPreference(context: Context, key: String?): String? {
            return getSharedPreference(context).getString(key, null)
        }

        /**
         * This method is used to get an integer from the preference which is already saved in it
         */
        fun getIntFromPreference(context: Context, key: String?): Int {
            return getSharedPreference(context).getInt(key, 0)
        }
    }
}