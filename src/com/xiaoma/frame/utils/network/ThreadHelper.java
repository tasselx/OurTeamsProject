/*
 * FileName:  ThreadHelper.java
 * CopyRight:  Belong to  <XiaoMaGuo Technologies > own 
 * Description:  <description>
 * Modify By :  XiaoMaGuo ^_^ 
 * Modify Date:   2013-10-11
 * Follow Order No.:  <Follow Order No.>
 * Modify Order No.:  <Modify Order No.>
 * Modify Content:  <modify content >
 */
package com.xiaoma.frame.utils.network;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.HttpStatus;

import android.os.Handler;
import android.util.Log;

import com.xiaoma.frame.model.RequestResult;

/**
 * @TODO [The Class File Description]
 * @author XiaoMaGuo ^_^
 * @version [version-code, 2013-10-11]
 * @since [Product/module]
 */
public class ThreadHelper implements Runnable
{
    
    private static final String TAG = "ThreadHelper";
    
    private Handler mHandler = null;
    
    private URL mUrl = null;
    
    private String urlString = null;
    
    private File mFile = null;
    
    private RequestCallBack mBack = null;
    
    private RequestResult mRr = null;
    
    public ThreadHelper()
    {
    }
    
    public ThreadHelper(Handler handler, String url, File file)
    {
        mHandler = handler;
        urlString = url;
        mFile = file;
    }
    
    public void addCallBack(RequestCallBack rcb)
    {
        mBack = rcb;
    }
    
    /**
     * Overriding methods
     */
    @Override
    public void run()
    {
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        // Looper.prepare();
        executeBefore();
        executeTruth();
        executeAfter();
        // Looper.loop();
    }
    
    /**
     * <Summary Description>
     */
    private void executeBefore()
    {
        // mHandler.sendEmptyMessage(0);
        // httpClient = HttpDownloader.getHttpClient();
    }
    
    /**
     * <Summary Description>
     */
    private void executeTruth()
    {
        Log.i(TAG, "executeTruth()");
        try
        {
            mUrl = new URL(urlString);
            HttpURLConnection http = (HttpURLConnection)mUrl.openConnection();
            http.setConnectTimeout(5 * 1000);
            http.setRequestMethod(String.valueOf(RequestMethod.GET));
            http.setRequestProperty("Accept",
                "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
            http.setRequestProperty("Accept-Language", "zh-CN");
            http.setRequestProperty("Referer", mUrl.toString());
            http.setRequestProperty("Charset", "UTF-8");
            http.setRequestProperty("User-Agent",
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
            http.setRequestProperty("Connection", "Keep-Alive");
            http.connect();
            
            if (http.getResponseCode() == HttpStatus.SC_OK)
            {
                if (http.getContentLength() <= 0)
                {
                    throw new RuntimeException("Unkown file size ");
                }
                
                printResponseHeader(http);
                InputStream in = http.getInputStream();
                byte[] buffer = new byte[1024];
                int offset = 0;
                RandomAccessFile raf = new RandomAccessFile(mFile, "rwd");
                while ((offset = in.read(buffer, 0, 1024)) != -1)
                {
                    raf.write(buffer, 0, offset);
                }
                raf.close();
                in.close();
                
                mRr = new RequestResult();
                mRr.setPassword(mFile.getPath());
                mRr.setUsername(mFile.getName());
                
            }
            else
            {
                http.disconnect();
                http = null;
                throw new RuntimeException("server no response ");
            }
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * <Summary Description>
     */
    private void executeAfter()
    {
        if (mBack != null)
        {
            mBack.resultCallBack(mRr);
        }
    }
    
    /**
     * Print the http's head
     * 
     * @param http
     */
    public static void printResponseHeader(HttpURLConnection http)
    {
        Map<String, String> header = getHttpResponseHeader(http);
        for (Map.Entry<String, String> entry : header.entrySet())
        {
            String key = entry.getKey() != null ? entry.getKey() + ":" : "";
            Log.i("KKK", key + entry.getValue());
        }
    }
    
    /**
     * Get the http's head
     * 
     * @param http
     * @return
     */
    public static Map<String, String> getHttpResponseHeader(HttpURLConnection http)
    {
        Map<String, String> header = new LinkedHashMap<String, String>();
        for (int i = 0;; i++)
        {
            String mine = http.getHeaderField(i);
            if (mine == null)
                break;
            header.put(http.getHeaderFieldKey(i), mine);
        }
        return header;
    }
}
