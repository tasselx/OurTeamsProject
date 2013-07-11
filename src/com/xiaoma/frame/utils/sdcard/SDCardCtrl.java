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
     * ����ȡ����
     * 
     * @param context
     */
    @SuppressWarnings("static-access")
    public SDCardCtrl(Context context)
    {
        this.context = context;
    }
    
    /**
     * ��ȡ���̸�·��
     * 
     * @return ���̸�·������·��
     */
    public static String getCtrlCPath()
    {
        return ROOTPATH;
    }
    
    /**
     * ��ȡ����ͼƬ·��
     * 
     * @return ��������ͼƬ�ļ��о���·��
     */
    public static String getDownLoadPath()
    {
        return DOWNLOADPATH;
    }
    
    /**
     * ��ȡָ�����ֵ��ļ���·��
     * 
     * @param foldername Ҫ��ȡ�ļ��е�����
     * @return Ҫ��ȡָ���ļ��еľ���·��
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
     * ���洢���Ƿ����
     * 
     * @return SD���Ƿ����
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
     * ��ʼ��SD��ʹ���ļ�·��
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
     * ���洢���洢�ռ�
     * 
     * @return false(�ռ䲻��) true(��ʹ��)
     */
    public static boolean checkSpace()
    {
        String path = Environment.getExternalStorageDirectory().getPath();
        StatFs statFs = new StatFs(path);
        /** ȡ�ô洢���С */
        int blockSize = statFs.getBlockSize() / 1024;
        /** ȡ�ô洢������ */
        int blockCount = statFs.getBlockCount();
        /** ȡ�ô洢����ʹ�ÿռ� */
        int usedBlocks = statFs.getAvailableBlocks() / 1024;
        /** �ܴ洢�ռ� */
        int totalSpace = blockCount * blockSize / 1024;
        /** ȡ�ÿ��ô洢�ռ� */
        int avaliableSpace = totalSpace - usedBlocks;
        /** ���洢�ռ�С��64Mʱ��ʾ���� */
        if (avaliableSpace < 64)
        {
            return false;
        }
        return true;
    }
}
