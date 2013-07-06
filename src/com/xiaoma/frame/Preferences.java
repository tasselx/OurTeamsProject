package com.xiaoma.frame;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @TODO [Common using data save class ]
 * @author XiaoMaGuo ^_^
 * @version [version-code, 2013-5-3]
 * @since [Product/module]
 */
public class Preferences {

	/**
	 * <Save data to SharePreferences file>
	 * 
	 * @param mPrefs
	 * @param key
	 * @param value
	 */
	public static void saveString(SharedPreferences mPrefs, String key,
			String value) {
		Editor editor = mPrefs.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * <Save boolean to sharepreferences file>
	 * 
	 * @param mPrefs
	 * @param key
	 * @param value
	 */
	public static void saveBoolean(SharedPreferences mPrefs, String key,
			Boolean value) {
		Editor editor = mPrefs.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	/**
	 * <Get string data from sharepreferences file>
	 * 
	 * @param mPrefs
	 * @param key
	 * @return
	 */
	public static String getStringDataFromPref(SharedPreferences mPrefs,
			String key) {
		return mPrefs.getString(key, "");
	}

	/**
	 * <Get boolean data from sharepreferences file>
	 * 
	 * @param mPrefs
	 * @param key
	 * @return
	 */
	public static Boolean getBooleanFromPref(SharedPreferences mPrefs,
			String key) {
		return mPrefs.getBoolean(key, false);
	}

}
