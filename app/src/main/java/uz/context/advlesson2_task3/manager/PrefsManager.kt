package uz.context.advlesson2_task3.manager

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import uz.context.advlesson2_task3.model.Model

class PrefsManager private constructor(context: Context) {
    private val sharedPreferences: SharedPreferences? =
        context.getSharedPreferences("MyPref", Context.MODE_PRIVATE)

    companion object {
        var prefsManager: PrefsManager? = null
        fun getInstance(context: Context): PrefsManager? {
            if (prefsManager == null) {
                prefsManager = PrefsManager(context)
            }
            return prefsManager!!
        }
    }

    fun setData(key: String?, model: Model) {
        val prefsEditor = sharedPreferences!!.edit()
        val gson = Gson()
        val json = gson.toJson(model)
        prefsEditor.putString(key, json)
        prefsEditor.apply()
    }

    fun getData(key: String?,context: Context): Model? {
        val appSharedPrefs = PreferenceManager
            .getDefaultSharedPreferences(context)
        val gson = Gson()
        val json = appSharedPrefs.getString(key, "")
        return gson.fromJson(json, Model::class.java)
    }
}