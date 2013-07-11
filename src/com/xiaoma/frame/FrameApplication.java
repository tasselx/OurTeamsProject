/*
 * FileName:  FrameApplication.java
 * CopyRight:  Belong to  <XiaoMaGuo Technologies > own 
 * Description:  <The class is invoked then  our application launch >
 * Modify By :  XiaoMaGuo ^_^ 
 * Modify Date:   2013-5-2
 * Follow Order No.:  <Follow Order No.>
 * Modify Order No.:  <Modify Order No.>
 * Modify Content:  <modify content >
 */
package com.xiaoma.frame;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.xiaoma.frame.utils.AppException;
import com.xiaoma.frame.utils.Utils;
import com.xiaoma.frame.utils.sdcard.SDCardCtrl;

/**
 * @TODO [The class invoke when the app start ]
 * @author XiaoMaGuo ^_^
 * @version [version-code, 2013-5-2]
 * @since [Global / Config ]
 */
public class FrameApplication extends Application
{
    
    private String TAG = "Frame Application ";
    
    public static final int NETTYPE_WIFI = 0x01;
    
    public static final int NETTYPE_CMWAP = 0x02;
    
    public static final int NETTYPE_CMNET = 0x03;
    
    public static SharedPreferences mGlobalPrefs;
    
    public static SharedPreferences appointPrefs;
    
    /**
     * Invoke this method when this application start
     */
    @SuppressLint("NewApi")
    @Override
    public void onCreate()
    {
        super.onCreate();
        
        /** This step in order to monitoring our app */
        if (BuildConfig.DEBUG)
        {
            Utils.enableStrictMode();
        }
        
        /** register app exception factory */
        Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler());
        
        /** init app data file path */
        SDCardCtrl.initPath();
        
        /** init app sharepreferences file */
        initPreferences();
        
        /** init app networkconfig */
        networkConfig();
        
    }
    
    /**
     * <Summary Description>
     */
    public void networkConfig()
    {
        // TODO Auto-generated method stub
        Log.i(TAG, "Current NetworkType = " + getNetworkType());
        Log.i(TAG, "Is isNetworkConnected()  = " + String.valueOf(isNetworkConnected()));
    }
    
    /**
     * <Summary Description>
     */
    public void initPreferences()
    {
        mGlobalPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        getOwnPreferences("sharedpreferences");
    }
    
    public SharedPreferences getOwnPreferences(String name)
    {
        Log.i(TAG, "There create a own appoint name SharedPreferences File");
        appointPrefs = getSharedPreferences(name, MODE_PRIVATE);
        return appointPrefs;
    }
    
    /**
     * Check the network is or not connected
     * 
     * @return
     */
    public boolean isNetworkConnected()
    {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }
    
    /**
     * Get current network type
     * 
     * @return 0£ºNot have network 1£ºWIFI 2£ºWAP 3£ºNET
     */
    public int getNetworkType()
    {
        int netType = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null)
        {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE)
        {
            String extraInfo = networkInfo.getExtraInfo();
            if (!TextUtils.isEmpty(extraInfo))
            {
                if (extraInfo.toLowerCase().equals("cmnet"))
                {
                    netType = NETTYPE_CMNET;
                }
                else
                {
                    netType = NETTYPE_CMWAP;
                }
            }
        }
        else if (nType == ConnectivityManager.TYPE_WIFI)
        {
            netType = NETTYPE_WIFI;
        }
        return netType;
    }
    
    /**
     * Juege Current Version is or not compatible
     * 
     * Compare current sdk version with version 2.3
     * 
     * @param VersionCode
     * @return
     */
    public static boolean isMethodsCompat()
    {
        int currentVersion = Build.VERSION.SDK_INT;
        return currentVersion >= Build.VERSION_CODES.GINGERBREAD;
    }
    
    /**
     * <Get the version code for this app >
     * 
     * @return
     */
    public int getVersionCode()
    {
        try
        {
            String packageName = getPackageName();
            return getPackageManager().getPackageInfo(packageName, 0).versionCode;
        }
        catch (NameNotFoundException e)
        {
            e.printStackTrace();
            return -1;
        }
    }
    
    /**
     * <Get the version name for this app >
     * 
     * @return
     */
    public String getVersionName()
    {
        String packageName = getPackageName();
        try
        {
            return getPackageManager().getPackageInfo(packageName, 0).versionName;
        }
        catch (NameNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Overriding methods
     * 
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
    }
    
    /**
     * Invoke this method when this application will exit.
     */
    @Override
    public void onTerminate()
    {
        super.onTerminate();
    }
}
