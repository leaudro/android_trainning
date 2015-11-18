package com.leaudro.series;

import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by hemobile on 18/11/15.
 */
@SharedPref(SharedPref.Scope.UNIQUE)
public interface MyPrefs {
    long lastUpdate();
}