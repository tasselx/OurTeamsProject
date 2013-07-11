/*
 * FileName:  DBFactory.java
 * CopyRight:  Belong to  <XiaoMaGuo Technologies > own 
 * Description:  <description>
 * Modify By :  XiaoMaGuo ^_^ 
 * Modify Date:   2013-7-9
 * Follow Order No.:  <Follow Order No.>
 * Modify Order No.:  <Modify Order No.>
 * Modify Content:  <modify content >
 */
package com.xiaoma.frame.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.xiaoma.frame.R;
import com.xiaoma.frame.utils.sdcard.SDCardCtrl;

/**
 * @TODO [The Class File Description]
 * @author XiaoMaGuo ^_^
 * @version [version-code, 2013-7-9]
 * @since [Product/module]
 */
public class DBFactory
{
    
    private static DBFactory dbInstance = null;
    
    private static Context dbContext = null;
    
    /* The Database version* */
    public static final int DATABASE_VERSION = 1;
    
    /* The New Database name* */
    public static final String DATABASE_NAME = "xiaomaframe" + DATABASE_VERSION + ".db";
    
    /* The old Database name* */
    public static final String OLD_DATABASE_NAME = "xiaomaframe" + String.valueOf(DATABASE_VERSION - 1) + ".db";
    
    protected SQLiteDatabase database = null;
    
    public DBFactory(Context context)
    {
        if (database == null)
        {
            database = openDatabase(context);
        }
        else
        {
            if (!database.isOpen())
                openDatabase(context);
        }
    }
    
    /**
     * 
     * <Open the path /raw/. db file>
     * 
     * @param context
     * @return
     */
    public static synchronized SQLiteDatabase openDatabase(final Context context)
    {
        try
        {
            File dir = new File(SDCardCtrl.DATABASEPATH);
            File dbFile = new File(dir.getAbsolutePath() + "/" + DATABASE_NAME);
            File oldFile = new File(dir.getAbsolutePath() + "/" + OLD_DATABASE_NAME);
            if (!dir.exists())
            {
                dir.mkdirs();
            }
            if (oldFile.exists())
            {
                oldFile.delete();
            }
            
            // If the local db file isn't exist,Then copy it to FrameApplication.DATABASEPATH path
            if (!dbFile.exists())
            {
                
                // The xiaomaframe.db is used to test
                InputStream is = context.getResources().openRawResource(R.raw.xiaomaframe);
                FileOutputStream fos = new FileOutputStream(dir.getAbsolutePath() + "/" + DATABASE_NAME);
                byte[] buffer = new byte[2048];
                int count = 0;
                while ((count = is.read(buffer)) > 0)
                {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }
            SQLiteDatabase database =
                SQLiteDatabase.openOrCreateDatabase(dir.getAbsolutePath() + "/" + DATABASE_NAME, null);
            
            return database;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
}
