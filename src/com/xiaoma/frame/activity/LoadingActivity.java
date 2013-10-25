package com.xiaoma.frame.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.xiaoma.frame.FrameApplication;
import com.xiaoma.frame.Preferences;
import com.xiaoma.frame.R;
import com.xiaoma.frame.utils.FrameException;

public class LoadingActivity extends Activity
{
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final View view = View.inflate(this, R.layout.load_activity, null);
        setContentView(view);
        
        AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
        aa.setDuration(2000);
        view.startAnimation(aa);
        aa.setAnimationListener(new AnimationListener()
        {
            @Override
            public void onAnimationEnd(Animation arg0)
            {
                try
                {
                    redirectTo();
                }
                catch (FrameException e)
                {
                    e.printStackTrace();
                }
            }
            
            @Override
            public void onAnimationRepeat(Animation animation)
            {
            }
            
            @Override
            public void onAnimationStart(Animation animation)
            {
            }
            
        });
    }
    
    /**
     * <Redirect to main page>
     */
    private void redirectTo()
        throws FrameException
    {
        Intent intent = null;
        SharedPreferences mPrefs = FrameApplication.appointPrefs;
        boolean isFirst = Preferences.getIsFirstLaunch(mPrefs);
        
        if (!isFirst)
        {
            Preferences.saveIsFirstLaunch(mPrefs);
            intent = new Intent(this, GuideActivity.class);
        }
        else
        {
            intent = new Intent(this, MainActivity.class);
        }
        
        startActivity(intent);
        finish();
    }
    
    /**
     * Overriding methods
     */
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
    
}