package com.foo.umbrella.ui

/**
 * Created by vincentvillalta on 9/19/17.
 */
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.*
import com.foo.umbrella.R
import com.foo.umbrella.utilities.showToast

class SettingsFragment: PreferenceFragment(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
        findPreference(getString(R.string.settings_zip_key)).onPreferenceChangeListener = validZipListener()
    }

    /**
     * Attatch a listener for the value change, this will prevent
     * a wrong/bad/invalid zip is set on the preferences
     */
    private fun validZipListener(): Preference.OnPreferenceChangeListener {
        return Preference.OnPreferenceChangeListener { pref, newValue ->
            checkValidZipCode(newValue as String)
        }
    }


    /**
     * Check if the input value has 5 digits, if not we should not store the value
     * For performance the app is not checking the validation of the Zip code
     * just the structure, the API is checking the zip code
     * If necesary could be implemented a local database with the valid zips for the USA
     */
    fun checkValidZipCode(zip: String): Boolean {
        if (zip.length > 4 && zip.length < 6){
            return true
        }
        else{
            this.context.showToast("Plase enter a valid zip code")
            return false
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        updatePreference(key)
    }


        override fun onStart() {
        super.onStart()
        setUpPreference(preferenceScreen)
    }

    private fun setUpPreference(preferenceGroup: PreferenceGroup) {
        for (j in 0 until preferenceGroup.preferenceCount) {
            val preference = preferenceGroup.getPreference(j)
            if (preference is PreferenceGroup) {
                setUpPreference(preference)
            } else {
                updatePreference(preference.key)
            }
        }
    }

    private fun updatePreference(key: String) {
        val preference = findPreference(key)
        if (preference is ListPreference) {
            preference.setSummary(preference.entry)
        }

        if (preference is EditTextPreference) {
            preference.setSummary(preference.text)
        }
    }
}
