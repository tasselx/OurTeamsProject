package com.example.slidingmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

public class BaseActivity extends SlidingFragmentActivity
{
    
    protected Fragment mFrag;
    
    protected SlidingMenu slidingMenu;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        setBehindContentView(R.layout.menu_frame);
        FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
        mFrag = new MenuFragment();
        t.replace(R.id.menu_frame, mFrag);
        t.commit();
        
        slidingMenu = getSlidingMenu();
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        slidingMenu.setShadowWidth(getWindowManager().getDefaultDisplay().getWidth() / 60);
        slidingMenu.setShadowDrawable(R.drawable.shadow);
        slidingMenu.setSecondaryShadowDrawable(R.drawable.right_shadow);
        slidingMenu.setBehindOffset(getWindowManager().getDefaultDisplay().getWidth() / 5);
        slidingMenu.setFadeEnabled(true);
        slidingMenu.setFadeDegree(0.4f);
        slidingMenu.setBehindScrollScale(0);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
    }
    
}
