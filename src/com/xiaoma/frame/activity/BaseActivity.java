package com.xiaoma.frame.activity;

import android.app.Activity;
import android.os.Bundle;

import com.xiaoma.frame.ApplicationManager;

public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ApplicationManager.getAppManager().addActivity(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ApplicationManager.getAppManager().finishActivity(this);
		ApplicationManager.getAppManager().AppExit(this);
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}
