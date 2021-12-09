package com.eeyan.mymenty.presentation.main.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.eeyan.mymenty.R

class AppSettings : PreferenceFragmentCompat(){
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}