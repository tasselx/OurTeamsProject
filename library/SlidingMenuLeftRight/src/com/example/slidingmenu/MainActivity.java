package com.example.slidingmenu;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);
        textView.setBackgroundColor(0x44ff00ff);
        setContentView(textView);
        
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        RightFragment rightFrag = new RightFragment();
        slidingMenu.setSecondaryMenu(R.layout.right_frame);
        transaction.replace(R.id.right_frame, rightFrag);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
