package com.xiaoma.frame.utils.network;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.client.params.CookiePolicy;

import com.xiaoma.frame.utils.sdcard.FileUtil;

/**
 * @Title: HttpDownloader.java
 * @Package com.xiaoma.frame.utils.network
 * @author XiaoMa
 */
public class HttpDownloader
{
    private URL url = null;
    
    public static final String UTF_8 = "UTF-8";
    
    public static final String DESC = "descend";
    
    public static final String ASC = "ascend";
    
    private final static int TIMEOUT_CONNECTION = 10000;
    
    private final static int TIMEOUT_SOCKET = 10000;
    
    private final static int RETRY_TIME = 3;
    
    /**
     * Download file from a specific Url and save as fileName to SDCard
     * 
     * @param
     * @return -1 for failure 0 for success 1 file existence
     */
    public int downFile(String urlStr, String fullPathName)
    {
        InputStream inputStream = null;
        try
        {
            if (FileUtil.isFileExist(fullPathName))
            {
                return 1;
            }
            else
            {
                inputStream = getInputStreamFromUrl(urlStr);
                File resultFile = FileUtil.writeFromInput(fullPathName, inputStream);
                if (resultFile == null)
                {
                    return -1;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
        finally
        {
            try
            {
                inputStream.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return 0;
    }
    
    public InputStream getInputStreamFromUrl(String urlStr)
        throws MalformedURLException, IOException
    {
        url = new URL(urlStr);
        HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
        InputStream inputStream = urlConn.getInputStream();
        return inputStream;
    }
    
    /**
     * <Get a HttpClient Object>
     * 
     * @return
     */
    public static HttpClient getHttpClient()
    {
        HttpClient httpClient = new HttpClient();
        // 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
        httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
        // 设置 默认的超时重试处理策略
        httpClient.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        // 设置 连接超时时间
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(TIMEOUT_CONNECTION);
        // 设置 连接超时时间
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(TIMEOUT_SOCKET);
        // 设置 字符集
        httpClient.getParams().setContentCharset(UTF_8);
        return httpClient;
    }
    
    private static GetMethod getHttpGet(String url, String cookie, String userAgent)
    {
        GetMethod httpGet = new GetMethod(url);
        // 设置 请求超时时间
        httpGet.getParams().setSoTimeout(TIMEOUT_SOCKET);
        httpGet.setRequestHeader("Host", ServerUrls.HOST);
        httpGet.setRequestHeader("Connection", "Keep-Alive");
        httpGet.setRequestHeader("Cookie", cookie);
        httpGet.setRequestHeader("User-Agent", userAgent);
        return httpGet;
    }
    
    private static PostMethod getHttpPost(String url, String cookie, String userAgent)
    {
        PostMethod httpPost = new PostMethod(url);
        // 设置 请求超时时间
        httpPost.getParams().setSoTimeout(TIMEOUT_SOCKET);
        httpPost.setRequestHeader("Host", ServerUrls.HOST);
        httpPost.setRequestHeader("Connection", "Keep-Alive");
        httpPost.setRequestHeader("Cookie", cookie);
        httpPost.setRequestHeader("User-Agent", userAgent);
        return httpPost;
    }
    
    /**
     * <Assem a access url>
     * 
     * @param url
     * @param params
     * @return
     */
    @SuppressWarnings("unused")
    public static String makeUriString(String sUrl, Map<String, Object> params)
    {
        StringBuilder url = new StringBuilder(sUrl);
        if (url.indexOf("?") < 0)
            url.append('?');
        
        for (String name : params.keySet())
        {
            url.append('&');
            url.append(name);
            url.append('=');
            url.append(String.valueOf(params.get(name)));
            // URLEncoder
            // url.append(URLEncoder.encode(String.valueOf(params.get(name)), UTF_8));
        }
        return url.toString().replace("?&", "?");
    }
    
}
