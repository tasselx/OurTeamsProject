package com.xiaoma.frame;

import java.util.Stack;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;

/**
 * @TODO [The class manager all activitys and services in this Application]
 * @author XiaoMaGuo ^_^
 * @version [version-code, 2013-5-2]
 * @since [Product/module]
 */
public class ApplicationManager
{
    
    private static Stack<Activity> activityStack;
    
    private static ApplicationManager instance;
    
    private static Stack<Service> serviceStack;
    
    private static Object object = new Object();
    
    private ApplicationManager()
    {
    }
    
    public static ApplicationManager getAppManager()
    {
        if (instance == null)
        {
            synchronized (object)
            {
                if (instance == null)
                {
                    instance = new ApplicationManager();
                }
            }
        }
        return instance;
    }
    
    public void addActivity(Activity activity)
    {
        if (activityStack == null)
        {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }
    
    public void addService(Service service)
    {
        if (serviceStack == null)
        {
            serviceStack = new Stack<Service>();
        }
        serviceStack.add(service);
    }
    
    public Activity currentActivity()
    {
        Activity activity = activityStack.lastElement();
        return activity;
    }
    
    public Service currentService()
    {
        Service service = serviceStack.lastElement();
        return service;
    }
    
    public void finishActivity()
    {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }
    
    public void stopService()
    {
        Service service = serviceStack.lastElement();
        stopService(service);
    }
    
    public void finishActivity(Activity activity)
    {
        if (activity != null)
        {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }
    
    public void stopService(Service service)
    {
        if (service != null)
        {
            serviceStack.remove(service);
            service.stopSelf();
            service = null;
        }
    }
    
    public void finishActivity(Class<?> cls)
    {
        for (Activity activity : activityStack)
        {
            if (activity.getClass().equals(cls))
            {
                finishActivity(activity);
            }
        }
    }
    
    public void finishAllActivity()
    {
        for (int i = 0, size = activityStack.size(); i < size; i++)
        {
            if (null != activityStack.get(i))
            {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }
    
    public void stopAllService()
    {
        int serviceCount = serviceStack.size();
        for (int i = 0; i < serviceCount; i++)
        {
            if (null != serviceStack.get(i))
            {
                serviceStack.get(i).stopSelf();
            }
        }
    }
    
    public void AppExit(Context context)
    {
        try
        {
            finishAllActivity();
            stopAllService();
            ActivityManager activityMgr = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
        catch (Exception e)
        {
        }
    }
}