package com.xiaoma.frame.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;

import com.xiaoma.frame.ApplicationManager;
import com.xiaoma.frame.R;

public class BaseActivity extends Activity
{
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ApplicationManager.getAppManager().addActivity(this);
    }
    
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        ApplicationManager.getAppManager().finishActivity(this);
        ApplicationManager.getAppManager().AppExit(this);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
    
    /**
     * Overriding methods
     * 
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(android.R.drawable.ic_dialog_info);
            builder.setTitle(getString(R.string.exit_prompt));
            builder.setPositiveButton(getString(R.string.no), new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton(getString(R.string.yes), new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.dismiss();
                    // Exit ours app
                    ApplicationManager.getAppManager().AppExit(BaseActivity.this);
                }
            });
            builder.show();
        }
        
        return super.onKeyDown(keyCode, event);
    }
    
}
