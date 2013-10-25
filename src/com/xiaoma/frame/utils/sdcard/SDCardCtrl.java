package com.xiaoma.frame.utils.sdcard;

import java.io.File;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

/**
 * @Title: SDCardControlUtil.java
 * @Package com.Frame.frame.utils.sdcard
 * @Description: SDCard control class
 * @author Frame
 */
public class SDCardCtrl
{
    
    /** The log cat Tag */
    public static final String TAG = "SDCheck";
    
    /** The new apk save's path */
    public static String DOWNLOADPATH = "/FrameDownload";
    
    /** ROOT */
    public static String ROOT;
    
    /** ROOTPATH */
    public static String ROOTPATH = "/FramePath";
    
    /** DATABASEPATH */
    public static String DATABASEPATH = "/FrameDatabase";
    
    /** ERRORLOGPATH */
    public static String ERRORLOGPATH = "/FrameErrorLog";
    
    /** ERROR_LOGFILE_NAME */
    public static final String ERROR_LOGFILE_PATH = ERRORLOGPATH + "/errorlog.txt";
    
    @SuppressWarnings("unused")
    private static Context context;
    
    /**
     * 
     * @param context
     */
    @SuppressWarnings("static-access")
    public SDCardCtrl(Context context)
    {
        this.context = context;
    }
    
    /**
     * 
     * @return ROOTPATH
     */
    public static String getCtrlCPath()
    {
        return ROOTPATH;
    }
    
    /**
     * 
     * @return DOWNLOADPATH
     */
    public static String getDownLoadPath()
    {
        return DOWNLOADPATH;
    }
    
    /**
     * 
     * @param foldername
     * @return appoint foldername's folder path
     */
    public static String getPointPath(String foldername)
    {
        if (sdCardIsExist())
        {
            File file = new File(Environment.getExternalStorageDirectory().getPath() + foldername);
            if (file.exists())
            {
                return file.getAbsolutePath();
            }
            else
            {
                return null;
            }
        }
        return null;
    }
    
    /**
     * 
     * @return Is or not exist SD card
     */
    public static boolean sdCardIsExist()
    {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            return false;
        }
        return true;
    }
    
    /**
     * <Build data file for this application >
     */
    public static void initPath()
    {
        
        String ROOT;
        
        /* Judge SDCard is or not Insert */
        if (sdCardIsExist())
        {
            ROOT = Environment.getExternalStorageDirectory().getPath();
        }
        else
        { /* There is no have SD cards */
            Log.i(TAG, "There is haven't SD card!!!");
            ROOT = "/data/data";
        }
        
        if (ROOTPATH.equals("/FramePath"))
        {
            ROOTPATH = ROOT + ROOTPATH;
            DOWNLOADPATH = ROOTPATH + DOWNLOADPATH;
            ERRORLOGPATH = ROOTPATH + ERRORLOGPATH;
            DATABASEPATH = ROOTPATH + DATABASEPATH;
            
        }
        
        File rootPath = new File(ROOTPATH);
        if (!rootPath.exists())
        {
            rootPath.mkdirs();
            Log.i(TAG, "rootPath = " + rootPath);
        }
        
        File downloadPath = new File(DOWNLOADPATH);
        if (!downloadPath.exists())
        {
            downloadPath.mkdirs();
            Log.i(TAG, "downloadPath = " + downloadPath);
        }
        
        File errorlogpath = new File(ERRORLOGPATH);
        if (!errorlogpath.exists())
        {
            errorlogpath.mkdirs();
            Log.i(TAG, "errorlogpath = " + ERRORLOGPATH);
        }
        
        File databasePath = new File(DATABASEPATH);
        if (!databasePath.exists())
        {
            databasePath.mkdirs();
            Log.i(TAG, "databasePath = " + DATABASEPATH);
        }
        
    }
    
    /**
     * Check the SDcard useful space
     * 
     * @return
     */
    public static boolean checkSpace()
    {
        String path = Environment.getExternalStorageDirectory().getPath();
        StatFs statFs = new StatFs(path);
        int blockSize = statFs.getBlockSize() / 1024;
        int blockCount = statFs.getBlockCount();
        int usedBlocks = statFs.getAvailableBlocks() / 1024;
        int totalSpace = blockCount * blockSize / 1024;
        int avaliableSpace = totalSpace - usedBlocks;
        if (avaliableSpace < 64)
        {
            return false;
        }
        return true;
    }
}
