package com.android.listviewfy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class MessageActivity extends Activity {
	private EditText et_title,et_content;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.message);
		et_title = (EditText)findViewById(R.id.et_title);
		et_content = (EditText)findViewById(R.id.et_content);
		et_title.setOnTouchListener(new OnTouchListener() {
			

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Toast.makeText(MessageActivity.this, "00000000000000000", 0).show(); 
				return true;
			}
		});
	}
}
