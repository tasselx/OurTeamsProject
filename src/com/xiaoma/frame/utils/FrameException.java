package com.xiaoma.frame.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;

import org.apache.http.HttpException;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Looper;
import android.util.Log;

import com.xiaoma.frame.ApplicationManager;
import com.xiaoma.frame.Config;
import com.xiaoma.frame.utils.sdcard.SDCardCtrl;

/**
 * @TODO [The class manager exception message ]
 * @author XiaoMaGuo ^_^
 * @version [version-code, 2013-5-3]
 * @since [Product/module]
 */
@SuppressWarnings("serial")
public class FrameException extends Exception implements UncaughtExceptionHandler
{
    
    public final static byte TYPE_NETWORK = 0x01;
    
    public final static byte TYPE_SOCKET = 0x02;
    
    public final static byte TYPE_HTTP_CODE = 0x03;
    
    public final static byte TYPE_HTTP_ERROR = 0x04;
    
    public final static byte TYPE_XML = 0x05;
    
    public final static byte TYPE_IO = 0x06;
    
    public final static byte TYPE_RUN = 0x07;
    
    private byte type;
    
    private int code;
    
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    
    private FrameException()
    {
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Log.i("KKK", " FrameException()");
    }
    
    private FrameException(byte type, int code, Exception excp)
    {
        super(excp);
        Log.i("KKK", "FrameException(byte type, int code, Exception excp)");
        this.type = type;
        this.code = code;
        if (Config.DEBUG)
        {
            saveErrorLog(excp);
        }
    }
    
    public int getCode()
    {
        return this.code;
    }
    
    public int getType()
    {
        return this.type;
    }
    
    public void saveErrorLog(Exception excp)
    {
        Log.i("KKK", "saveErrorLog = saveErrorLog");
        String errorlog = SDCardCtrl.ERROR_LOGFILE_PATH;
        FileWriter fw = null;
        PrintWriter pw = null;
        try
        {
            if (SDCardCtrl.sdCardIsExist())
            {
                Log.i("KKK", "errorlog = " + errorlog);
                File file = new File(errorlog);
                if (!file.exists())
                {
                    file.mkdirs();
                }
            }
            if (errorlog == "")
            {
                return;
            }
            File logFile = new File(errorlog);
            if (!logFile.exists())
            {
                logFile.createNewFile();
            }
            fw = new FileWriter(logFile, true);
            pw = new PrintWriter(fw);
            pw.println("--------------------" + (new Date().toLocaleString()) + "---------------------");
            excp.printStackTrace(pw);
            pw.close();
            fw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (pw != null)
            {
                pw.close();
            }
            if (fw != null)
            {
                try
                {
                    fw.close();
                }
                catch (IOException e)
                {
                }
            }
        }
        
    }
    
    public static FrameException http(int code)
    {
        return new FrameException(TYPE_HTTP_CODE, code, null);
    }
    
    public static FrameException http(Exception e)
    {
        return new FrameException(TYPE_HTTP_ERROR, 0, e);
    }
    
    public static FrameException socket(Exception e)
    {
        return new FrameException(TYPE_SOCKET, 0, e);
    }
    
    public static FrameException io(Exception e)
    {
        if (e instanceof UnknownHostException || e instanceof ConnectException)
        {
            return new FrameException(TYPE_NETWORK, 0, e);
        }
        else if (e instanceof IOException)
        {
            return new FrameException(TYPE_IO, 0, e);
        }
        return run(e);
    }
    
    public static FrameException xml(Exception e)
    {
        return new FrameException(TYPE_XML, 0, e);
    }
    
    public static FrameException network(Exception e)
    {
        if (e instanceof UnknownHostException || e instanceof ConnectException)
        {
            return new FrameException(TYPE_NETWORK, 0, e);
        }
        else if (e instanceof HttpException)
        {
            return http(e);
        }
        else if (e instanceof SocketException)
        {
            return socket(e);
        }
        return http(e);
    }
    
    public static FrameException run(Exception e)
    {
        return new FrameException(TYPE_RUN, 0, e);
    }
    
    public static FrameException getAppExceptionHandler()
    {
        return new FrameException();
    }
    
    @Override
    public void uncaughtException(Thread thread, Throwable ex)
    {
        
        if (!handleException(ex) && mDefaultHandler != null)
        {
            mDefaultHandler.uncaughtException(thread, ex);
        }
        
    }
    
    private boolean handleException(Throwable ex)
    {
        if (ex == null)
        {
            return false;
        }
        
        final Context context = ApplicationManager.getAppManager().currentActivity();
        
        if (context == null)
        {
            return false;
        }
        
        final String errorReport = getErrorReport(context, ex);
        new Thread()
        {
            public void run()
            {
                Looper.prepare();
                sendAppErrorReport(context, errorReport);
                Looper.loop();
            }
            
        }.start();
        return true;
    }
    
    /**
     * <Get the error message>
     * 
     * @param context
     * @param ex
     * @return
     */
    private String getErrorReport(Context context, Throwable ex)
    {
        StringBuffer exceptionStr = new StringBuffer();
        try
        {
            String packageName = context.getPackageName();
            PackageManager pm = context.getPackageManager();
            PackageInfo packInfo;
            packInfo = pm.getPackageInfo(packageName, 0);
            exceptionStr.append("Version: " + packInfo.versionName + "(" + packInfo.versionCode + ")\n");
            exceptionStr.append("Android: " + android.os.Build.VERSION.RELEASE + "(" + android.os.Build.MODEL + ")\n");
            exceptionStr.append("Exception: " + ex.getMessage() + "\n");
            StackTraceElement[] elements = ex.getStackTrace();
            for (int i = 0; i < elements.length; i++)
            {
                exceptionStr.append(elements[i].toString() + "\n");
            }
        }
        catch (NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return exceptionStr.toString();
    }
    
    /**
     * <Send the error message to application server or appoint to E-mail>
     * 
     * @param cont
     * @param ErrorReport
     */
    public static void sendAppErrorReport(final Context cont, final String ErrorReport)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(cont);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle("App Error Message");
        builder.setMessage("Error Message");
        builder.setPositiveButton("Submit Report", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
                // 发送异常报告
                Intent i = new Intent(Intent.ACTION_SEND);
                // i.setType("text/plain"); //模拟器
                i.setType("message/rfc822"); // 真机
                i.putExtra(Intent.EXTRA_EMAIL, new String[] {"mzh3344258@163.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "XiaoMa App Error Message Report");
                i.putExtra(Intent.EXTRA_TEXT, ErrorReport);
                cont.startActivity(Intent.createChooser(i, "Send Report"));
                // exit
                ApplicationManager.getAppManager().AppExit(cont);
            }
        });
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
                // exit
                ApplicationManager.getAppManager().AppExit(cont);
            }
        });
        builder.show();
    }
}
