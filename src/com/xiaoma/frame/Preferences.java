package com.xiaoma.frame;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @TODO [Common using data save class ]
 * @author XiaoMaGuo ^_^
 * @version [version-code, 2013-5-3]
 * @since [Product/module]
 */
public class Preferences
{
    
    /* Is or first launch* */
    private static final String IS_FIRST_LAUNCH = "isFirst";
    
    /**
     * <Save data to SharePreferences file>
     * 
     * @param mPrefs
     * @param key
     * @param value
     */
    public static void saveString(SharedPreferences mPrefs, String key, String value)
    {
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
    public static void saveBoolean(SharedPreferences mPrefs, String key, Boolean value)
    {
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
    public static String getStringDataFromPref(SharedPreferences mPrefs, String key)
    {
        return mPrefs.getString(key, "");
    }
    
    /**
     * <Get boolean data from sharepreferences file>
     * 
     * @param mPrefs
     * @param key
     * @return
     */
    public static boolean getBooleanFromPref(SharedPreferences mPrefs, String key)
    {
        return mPrefs.getBoolean(key, false);
    }
    
    /**
     * <Get is or not first launch app >
     * 
     * @param mPrefs
     * @param key
     * @return
     */
    public static boolean getIsFirstLaunch(SharedPreferences mPrefs)
    {
        return mPrefs.getBoolean(IS_FIRST_LAUNCH, false);
    }
    
    /**
     * <Save is or not first launch app >
     * 
     * @param mPrefs
     * @param key
     * @return
     */
    public static void saveIsFirstLaunch(SharedPreferences mPrefs)
    {
        Editor editor = mPrefs.edit();
        editor.putBoolean(IS_FIRST_LAUNCH, true);
        editor.commit();
    }
    
}
