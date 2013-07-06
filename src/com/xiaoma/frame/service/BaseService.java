/*
 * FileName:  BaseService.java
 * CopyRight:  Belong to  <XiaoMaGuo Technologies > own 
 * Description:  <description>
 * Modify By :  XiaoMaGuo ^_^ 
 * Modify Date:   2013-5-3
 * Follow Order No.:  <Follow Order No.>
 * Modify Order No.:  <Modify Order No.>
 * Modify Content:  <modify content >
 */
package com.xiaoma.frame.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.xiaoma.frame.ApplicationManager;

/**
 * @TODO [The Class is our's all service BaseService order to Service Manager ]
 * @author XiaoMaGuo ^_^
 * @version [version-code, 2013-5-3]
 * @since [Product/module]
 */
public class BaseService extends Service {

	/**
	 * Overriding methods
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		ApplicationManager.getAppManager().addService(this);
	}

	/**
	 * Overriding methods
	 * 
	 * @param intent
	 * @param startId
	 */
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}

	/**
	 * Overriding methods
	 * 
	 * @param intent
	 * @return
	 */
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Overriding methods
	 * 
	 * @param intent
	 * @param flags
	 * @param startId
	 * @return
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

}
