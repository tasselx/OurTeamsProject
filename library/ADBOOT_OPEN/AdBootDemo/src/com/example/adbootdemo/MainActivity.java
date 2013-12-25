package com.example.adbootdemo;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.joyplus.ad.AdSDKManager;
import com.joyplus.ad.AdSDKManager.CUSTOMTYPE;
import com.joyplus.ad.AdSDKManagerException;
import com.joyplus.ad.HttpManager;
import com.joyplus.ad.PhoneManager;
import com.joyplus.ad.PublisherId;
import com.joyplus.ad.Monitor.Monitor;
import com.joyplus.ad.application.AdBoot;
import com.joyplus.ad.application.AdBootInfo;
import com.joyplus.ad.application.AdBootManager;
import com.joyplus.ad.application.CUSTOMINFO;
import com.joyplus.ad.config.Log;
import com.joyplus.ad.data.ADBOOT;
import com.joyplus.ad.data.AdBootRequest;
import com.joyplus.ad.data.AdBootResponseHandler;
import com.joyplus.ad.data.RequestException;
import com.joyplus.ad.data.TRACKINGURL;
import com.joyplus.ad.data.TRACKINGURL.TYPE;
import com.miaozhen.mzmonitor.MZMonitor;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.TextView;
  
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
		TextView a = (TextView) this.findViewById(R.id.textView1);
		a.setText(getMacAddress(this));
	}
	public String getMacAddress(Context context){
		String macAddress = "NO MAC"; 
		WifiManager wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = (null == wifiMgr ? null : wifiMgr
				.getConnectionInfo());
		if (info != null) {
			macAddress = info.getMacAddress();
		}
		return macAddress;
    }  
	private void InitResource() {
		// TODO Auto-generated method stub
		PublisherId id          = new PublisherId("ca24c2c91dff49c0dd432191a2c51a0c");
		AdBootInfo  mAdBootInfo = new AdBootInfo();
		CUSTOMINFO  mCUSTOMINFO = new CUSTOMINFO(); 
		
		mAdBootInfo.SetFirstSource("./mnt/sdcard/JastextFirst.jpg");
		mAdBootInfo.SetSecondSource("./mnt/sdcard/JastextSecond.jpg");
		mAdBootInfo.SetThirdSource("./mnt/sdcard/Jastextboot.zip");
		
		mCUSTOMINFO.SetDEVICEMUMBER("6a818a");     
		mCUSTOMINFO.SetDEVICEMOVEMENT("6a818a");  
		mCUSTOMINFO.SetMAC(PhoneManager.getInstance().GetMac());
		Log.d("Jas","MAC="+PhoneManager.getInstance().GetMac());
		mAdBoot  = new AdBoot(mCUSTOMINFO,mAdBootInfo,id); 
		mManager = new AdBootManager(this,mAdBoot); 
	    mManager.RequestAD(); 	
//		ADBOOT a = null; 
//		try {
//			InputStream is = getAssets().open("demo.xml");
//			 a = this.parse(is);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (RequestException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		if(a != null){
//			Log.d("Jas","a="+a.toString());
//			for(TRACKINGURL url :a.video.trackingurl){
//				Log.d("--------------------url=\n"+url.toString());
//				dd(url);
//			}
//		}
	} 
	private void dd(TRACKINGURL url){
		if(url == null){
			Log.d("Jas","nooo");
			return;
		}
		if(url.Type == TYPE.MIAOZHEN){
			try{
				url.URL=url.URL.replaceAll(Monitor.REPLACE_MAC, "MIAOZHENmac");
				url.URL=url.URL.replaceAll(Monitor.REPLACE_DM, "MIAOZHENdm");
			}catch(NullPointerException e){
				e.printStackTrace();
			}
		}else if(url.Type == TYPE.IRESEARCH){
			url.URL=url.URL.replaceAll(Monitor.REPLACE_MAC, "IRESEARCHmac");
			url.URL=url.URL.replaceAll(Monitor.REPLACE_DM, "IRESEARCHdm");
		}else if(url.Type == TYPE.ADMASTER){
			url.URL=url.URL.replaceAll(Monitor.REPLACE_MAC, "ADMASTERmac");
			url.URL=url.URL.replaceAll(Monitor.REPLACE_DM, "ADMASTERdm");
		}else if(url.Type == TYPE.NIELSEN){ 
			url.URL=url.URL.replaceAll(Monitor.REPLACE_MAC, "NIELSENmac");
			url.URL=url.URL.replaceAll(Monitor.REPLACE_DM, "NIELSENdm");
		}
		Log.d("Jas","nnnnnn======="+url.toString());
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true; 
	}
     
	private void Miaozhen(){
		String m1 = "http://g.mbm.cn.miaozhen.com/x.gif?k=1868&p=82s&ns=[M_ADIP]&ni=[M_IESID]&na=[M_MAC]&rt=2&o=";
		String m2 = "http://g.mbm.cn.miaozhen.com/x.gif?k=1868&p=82t&ns=[M_ADIP]&ni=[M_IESID]&na=[M_MAC]&rt=2&o=";
		String m3 = "http://g.mbm.cn.miaozhen.com/x.gif?k=1868&p=82u&ns=[M_ADIP]&ni=[M_IESID]&na=[M_MAC]&rt=2&o=";
		MZMonitor.retryCachedRequests(this);
		MZMonitor.adTrack(this, m2);  
	} 
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		Miaozhen();
		return super.onKeyUp(keyCode, event);
	}
	ADBOOT parse(InputStream inputStream) throws RequestException {
		// TODO Auto-generated method stub
		if(inputStream == null)return null;
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser(); 
			XMLReader xr = sp.getXMLReader();
			AdBootResponseHandler AdBootHandler = new AdBootResponseHandler();
			xr.setContentHandler(AdBootHandler);
			InputSource src = new InputSource(inputStream);
			src.setEncoding(HttpManager.RESPONSE_ENCODING);
			xr.parse(src);
			return AdBootHandler.getADBOOT();
		} catch (Exception e) {
			throw new RequestException("Cannot parse Response:" 
					+ e.getMessage(), e);
		}
	}
}
