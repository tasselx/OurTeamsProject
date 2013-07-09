/*
 * FileName:  TestDao.java
 * CopyRight:  Belong to  <XiaoMaGuo Technologies > own 
 * Description:  <description>
 * Modify By :  XiaoMaGuo ^_^ 
 * Modify Date:   2013-7-9
 * Follow Order No.:  <Follow Order No.>
 * Modify Order No.:  <Modify Order No.>
 * Modify Content:  <modify content >
 */
package com.xiaoma.frame.dao;

import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.xiaoma.frame.database.DBHelper;

/**
 * @TODO [The Class File Description]
 * @author XiaoMaGuo ^_^
 * @version [version-code, 2013-7-9]
 * @since [Product/module]
 */
public class TestDao extends DBHelper
{
    
    /**
     * <Default Constructor>
     */
    public TestDao(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    public synchronized void save(Map<String, String> map)
    {
        
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try
        {
            
            db.execSQL("insert into frametest(name, age, height) values(?,?,?)",
                new Object[] {map.get("name"), map.get("age"), map.get("height")});
            db.setTransactionSuccessful();
        }
        finally
        {
            db.endTransaction();
        }
        db.close();
    }
    
}
