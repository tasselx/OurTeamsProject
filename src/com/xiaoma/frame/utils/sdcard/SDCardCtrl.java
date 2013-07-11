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
    
    public static String DATABASEPATH = "/FrameDatabase";
    
    public static String ERRORLOGPATH = "/FrameErrorLog";
    
    @SuppressWarnings("unused")
    private static Context context;
    
    /**
     * 构造取环境
     * 
     * @param context
     */
    @SuppressWarnings("static-access")
    public SDCardCtrl(Context context)
    {
        this.context = context;
    }
    
    /**
     * 获取工程根路径
     * 
     * @return 工程根路径绝对路径
     */
    public static String getCtrlCPath()
    {
        return ROOTPATH;
    }
    
    /**
     * 获取下载图片路径
     * 
     * @return 工程下载图片文件夹绝对路径
     */
    public static String getDownLoadPath()
    {
        return DOWNLOADPATH;
    }
    
    /**
     * 获取指定名字的文件夹路径
     * 
     * @param foldername 要获取文件夹的名字
     * @return 要获取指定文件夹的绝对路径
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
     * 检测存储卡是否插入
     * 
     * @return SD卡是否存在
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
     * 初始化SD卡使用文件路径
     */
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
            Log.v("MKDIR", "No SD card!!!");
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
            Log.d("Init rootPath", "rootPath = " + rootPath);
        }
        
        File downloadPath = new File(DOWNLOADPATH);
        if (!downloadPath.exists())
        {
            downloadPath.mkdirs();
            Log.d("Init downloadPath", "downloadPath = " + downloadPath);
        }
        
        File errorlogpath = new File(ERRORLOGPATH);
        if (!errorlogpath.exists())
        {
            errorlogpath.mkdirs();
            Log.d("Init errorlogpath", "errorlogpath = " + ERRORLOGPATH);
        }
        
        File databasePath = new File(DATABASEPATH);
        if (!databasePath.exists())
        {
            databasePath.mkdirs();
            Log.d("Init databasePath", "databasePath = " + DATABASEPATH);
        }
        
    }
    
    /**
     * 检测存储卡存储空间
     * 
     * @return false(空间不足) true(可使用)
     */
    public static boolean checkSpace()
    {
        String path = Environment.getExternalStorageDirectory().getPath();
        StatFs statFs = new StatFs(path);
        /** 取得存储块大小 */
        int blockSize = statFs.getBlockSize() / 1024;
        /** 取得存储块总数 */
        int blockCount = statFs.getBlockCount();
        /** 取得存储块已使用空间 */
        int usedBlocks = statFs.getAvailableBlocks() / 1024;
        /** 总存储空间 */
        int totalSpace = blockCount * blockSize / 1024;
        /** 取得可用存储空间 */
        int avaliableSpace = totalSpace - usedBlocks;
        /** 当存储空间小于64M时提示不足 */
        if (avaliableSpace < 64)
        {
            return false;
        }
        return true;
    }
}
