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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;

import com.xiaoma.frame.ApplicationManager;
import com.xiaoma.frame.R;

/**
 * @TODO [The Class File Description]
 * @author XiaoMaGuo ^_^
 * @version [version-code, 2013-5-3]
 * @since [Product/module]
 * 
 *        Wraning :We can throw exception when ours invoke possible have
 *        exception methods
 * 
 */
public class MainActivity extends BaseActivity {
	/**
	 * Overriding methods
	 * 
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
	}

	/**
	 * Overriding methods
	 * 
	 * @param keyCode
	 * @param event
	 * @return
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setIcon(android.R.drawable.ic_dialog_info);
			builder.setTitle("Exit prompt ");
			builder.setPositiveButton("Yes,out of ",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							// ÍË³ö
							ApplicationManager.getAppManager().AppExit(
									MainActivity.this);
						}
					});
			builder.setNegativeButton("No,don't quit ",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
			builder.show();
		}

		return super.onKeyDown(keyCode, event);

	}
}
