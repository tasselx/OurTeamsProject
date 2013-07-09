/*
 * FileName:  MyDbHelperTest.java
 * CopyRight:  Belong to  <XiaoMaGuo Technologies > own 
 * Description:  <description>
 * Modify By :  XiaoMaGuo ^_^ 
 * Modify Date:   2013-7-9
 * Follow Order No.:  <Follow Order No.>
 * Modify Order No.:  <Modify Order No.>
 * Modify Content:  <modify content >
 */
package com.xiaoma.frame.database;

import java.util.Map;

import android.R.string;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * @TODO [The Class File Description]
 * @author XiaoMaGuo ^_^
 * @version [version-code, 2013-7-9]
 * @since [Product/module]
 */
public class MyDbHelperTest extends DBHelper
{
    
    /**
     * <Default Constructor>
     */
    public MyDbHelperTest(Context context, String name, CursorFactory factory, int version)
    {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }
    
    public synchronized void insertData(Map<String, string> map)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try
        {
            db.execSQL("insert into frametest(name,age, height) values(?,?,?)",
                new Object[] {map.get("name"), map.get("age"), map.get("height")});
            db.setTransactionSuccessful();
        }
        finally
        {
            db.endTransaction();
        }
        db.close();
    }
    
    public synchronized void deleteAllData()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try
        {
            int i = db.delete("frametest", null, null);
            db.setTransactionSuccessful();
        }
        finally
        {
            db.endTransaction();
            db.close();
        }
    }
    
}
