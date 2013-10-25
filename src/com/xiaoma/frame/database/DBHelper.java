/*
 * FileName:  DBHelper.java
 * CopyRight:  Belong to  <XiaoMaGuo Technologies > own 
 * Description:  <description>
 * Modify By :  XiaoMaGuo ^_^ 
 * Modify Date:   2013-7-9
 * Follow Order No.:  <Follow Order No.>
 * Modify Order No.:  <Modify Order No.>
 * Modify Content:  <modify content >
 */
package com.xiaoma.frame.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @TODO [The Class File Description]
 * @author XiaoMaGuo ^_^
 * @version [version-code, 2013-7-9]
 * @since [Product/module]
 */
public class DBHelper extends SQLiteOpenHelper
{
    
    public static final String DATABASE_NAME = "xiaoma.db";
    
    public static final int DATABASE_VERSION = 1;
    
    public static final String CREATE_TABLE =
        "create table if not exists frametest(id integer not null primary key autoincrement,name varchar ,age integer,height float);";
    
    /**
     * <Default Constructor>
     */
    public DBHelper(Context context, String name, CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    
    /**
     * Overriding methods
     * 
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE);
    }
    
    /**
     * Overriding methods
     * 
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + "frametest");
        onCreate(db);
        Log.i("Database", "onUpgrade");
    }
    
    public void updateColumn(SQLiteDatabase db, String oldColumn, String newColumn, String typeColumn)
    {
        try
        {
            db.execSQL("ALTER TABLE " + " TB_NAME " + " CHANGE " + oldColumn + " " + newColumn + " " + typeColumn);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public boolean deleteDatabase(Context context)
    {
        return context.deleteDatabase(DATABASE_NAME);
    }
    
}
