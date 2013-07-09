/*
 * FileName:  MyDBFactoryTest.java
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

/**
 * @TODO [The Class File Description]
 * @author XiaoMaGuo ^_^
 * @version [version-code, 2013-7-9]
 * @since [Product/module]
 */
public class MyDBFactoryTest extends DBFactory
{
    
    /**
     * <Default Constructor>
     */
    public MyDBFactoryTest(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
    }
    
    public void insertData()
    {
        
        database.beginTransaction();
        try
        {
            database.insert(null, null, null);
            database.setTransactionSuccessful();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            database.endTransaction();
        }
        database.close();
        
    }
    
    public synchronized void deleteAllData()
    {
        database.beginTransaction();
        try
        {
            int i = database.delete("frametest", null, null);
            database.setTransactionSuccessful();
        }
        finally
        {
            database.endTransaction();
            database.close();
        }
    }
    
}
