/*
 * FileName:  GuidePagerAdapter.java
 * CopyRight:  Belong to  <XiaoMaGuo Technologies > own 
 * Description:  <description>
 * Modify By :  XiaoMaGuo ^_^ 
 * Modify Date:   2013-7-11
 * Follow Order No.:  <Follow Order No.>
 * Modify Order No.:  <Modify Order No.>
 * Modify Content:  <modify content >
 */
package com.xiaoma.frame.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaoma.frame.R;
import com.xiaoma.frame.activity.MainActivity;

/**
 * @TODO [The Class File Description]
 * @author XiaoMaGuo ^_^
 * @version [version-code, 2013-7-11]
 * @since [Product/module]
 */
public class GuidePagerAdapter extends PagerAdapter
{
    
    private List<View> mListViews = null;
    
    private LayoutInflater mInflater = null;
    
    private Context mContext = null;
    
    public GuidePagerAdapter(Context context)
    {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        View viewFour = mInflater.inflate(R.layout.guide_page_four, null);
        mListViews = new ArrayList<View>();
        mListViews.add(mInflater.inflate(R.layout.guide_page_one, null));
        mListViews.add(mInflater.inflate(R.layout.guide_page_two, null));
        mListViews.add(mInflater.inflate(R.layout.guide_page_three, null));
        mListViews.add(viewFour);
        ((TextView)viewFour.findViewById(R.id.clickToMain)).setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                mContext.startActivity(new Intent(mContext, MainActivity.class));
            }
        });
    }
    
    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView(mListViews.get(position));
    }
    
    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        container.addView(mListViews.get(position), 0);
        return mListViews.get(position);
    }
    
    @Override
    public int getCount()
    {
        return mListViews.size();
    }
    
    @Override
    public boolean isViewFromObject(View arg0, Object arg1)
    {
        return arg0 == arg1;
    }
    
}
