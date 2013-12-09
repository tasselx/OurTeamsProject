package com.example.adbootdemo;

import com.joyplus.ad.AdSDKManager;
import com.joyplus.ad.AdSDKManager.CUSTOMTYPE;
import com.joyplus.ad.AdSDKManagerException;
import com.joyplus.ad.PublisherId;
import com.joyplus.ad.application.AdBoot;
import com.joyplus.ad.application.AdBootInfo;
import com.joyplus.ad.application.AdBootManager;
import com.joyplus.ad.application.CUSTOMINFO;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
	private AdBoot mAdBoot;
	private AdBootManager mManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			AdSDKManager.Init(this,CUSTOMTYPE.JOYPLUS);
		} catch (AdSDKManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InitResource();
	}
	private void InitResource() {
		// TODO Auto-generated method stub
		PublisherId id          = new PublisherId("ea95829efe675be74f9c0e3408ff8608");
		AdBootInfo  mAdBootInfo = new AdBootInfo();
		CUSTOMINFO  mCUSTOMINFO = new CUSTOMINFO();
		
		mAdBootInfo.SetFirstImage("./mnt/sdcard/textfirstImageAAAA.jpg");
		mAdBootInfo.SetSecondImage("./mnt/sdcard/textSecondImageAAAA.jpg");
		mAdBootInfo.SetBootAnimationZip("./mnt/sdcard/textbootanimationAAAA.zip");
		mCUSTOMINFO.SetDEVICEMUMBER("AMLOGIC8726MX");
		
		mAdBoot  = new AdBoot(mCUSTOMINFO,mAdBootInfo,id);
		mManager = new AdBootManager(this,mAdBoot);
		mManager.RequestAD();		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
