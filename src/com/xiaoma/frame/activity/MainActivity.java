/*
 * FileName:  MainActivity.java
 * CopyRight:  Belong to  <XiaoMaGuo Technologies > own 
 * Description:  <description>
 * Modify By :  XiaoMaGuo ^_^ 
 * Modify Date:   2013-5-3
 * Follow Order No.:  <Follow Order No.>
 * Modify Order No.:  <Modify Order No.>
 * Modify Content:  <modify content >
 */
package com.xiaoma.frame.activity;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;

import com.xiaoma.frame.R;
import com.xiaoma.frame.dao.TestDao;
import com.xiaoma.frame.model.RequestResult;
import com.xiaoma.frame.utils.network.RequestCallBack;
import com.xiaoma.frame.utils.network.ServerUrls;
import com.xiaoma.frame.utils.network.ThreadHelper;
import com.xiaoma.frame.utils.sdcard.FileUtil;
import com.xiaoma.frame.utils.sdcard.SDCardCtrl;

/**
 * @TODO [The Class File Description]
 * @author XiaoMaGuo ^_^
 * @version [version-code, 2013-5-3]
 * @since [Product/module]
 * 
 *        Wraning :We can throw exception when ours invoke possible have exception methods
 * 
 */
public class MainActivity extends BaseActivity implements RequestCallBack
{
    
    private static final String Tag = "MainActivity";
    
    public Handler mHandler = null;
    
    // private TextView mTV = null;
    
    /**
     * Overriding methods
     * 
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        // Test(); The method used to tested the Exception for Application
        // mTV = (TextView)findViewById(R.id.textView1);
        mHandler = new Handler()
        {
            public void handleMessage(android.os.Message msg)
            {
                switch (msg.what)
                {
                    case 0:
                        Log.i(Tag, "handleMessage before");
                        // mTV.setText("before");
                        break;
                    default:
                        break;
                }
            };
        };
        
        File filePath = new File(SDCardCtrl.DOWNLOADPATH + "/baidu.jpg");
        Log.d("KKK", "The File path = " + filePath.toString());
        
        ThreadHelper th = new ThreadHelper(mHandler, ServerUrls.myTestUrl, filePath);
        th.addCallBack(this);
        Thread thread = new Thread(th);
        // thread.start();
        
        deleteFile();
    }
    
    /**
     * <Summary Description>
     */
    @SuppressWarnings("static-access")
    private static void deleteFile()
    {
        FileUtil fu = new FileUtil();
        fu.deleteFolder(new File(SDCardCtrl.ROOTPATH));
    }
    
    /**
     * <This is Method used to Test SQLiteDatabase handle >
     */
    private void Test()
    {
        
        int a[] = new int[5];
        int length = a.length;
        for (int i = 0; i < length; i++)
        {
            a[i] = i;
            Log.i("KKK", "the a[5] value is = " + a[5]);
        }
        
        new Thread(new Runnable()
        {
            
            @Override
            public void run()
            {
                Looper.prepare();
                TestDao td = new TestDao(MainActivity.this);
                for (int i = 0; i < 50; i++)
                {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("name", "XiaoMaGuo");
                    map.put("age", "23");
                    map.put("height", "180");
                    td.save(map);
                }
                Looper.loop();
            }
        }).start();
        Log.i("KKK", "Test the database");
    }
    
    /**
     * Overriding methods
     * 
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        return super.onKeyDown(keyCode, event);
        
    }
    
    /**
     * Overriding methods
     * 
     * @param rr
     */
    @Override
    public void resultCallBack(RequestResult rr)
    {
        // TODO Auto-generated method stub
        if (rr != null)
        {
            Log.i("KKK", rr.toString());
        }
    }
}
