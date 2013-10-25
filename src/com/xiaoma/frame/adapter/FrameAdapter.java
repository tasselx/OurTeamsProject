/*
 * FileName:  FrameAdapter.java
 * CopyRight:  Belong to  <XiaoMaGuo Technologies > own 
 * Description:  <description>
 * Modify By :  XiaoMaGuo ^_^ 
 * Modify Date:   2013-5-3
 * Follow Order No.:  <Follow Order No.>
 * Modify Order No.:  <Modify Order No.>
 * Modify Content:  <modify content >
 */
package com.xiaoma.frame.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoma.frame.R;

/**
 * @TODO [The class file use to common data adapter ]
 * @author XiaoMaGuo ^_^
 * @version [version-code, 2013-5-3]
 * @since [Product/module]
 */
public class FrameAdapter extends BaseAdapter
{
    
    @SuppressWarnings("unused")
    private Context mContext = null;
    
    private LayoutInflater mInflater = null;
    
    /**
     * <Default Constructor>
     */
    public FrameAdapter(Context context)
    {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }
    
    /**
     * Overriding methods
     * 
     * @return
     */
    @Override
    public int getCount()
    {
        return 0;
    }
    
    /**
     * Overriding methods
     * 
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * Overriding methods
     * 
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position)
    {
        return position;
    }
    
    /**
     * Overriding methods
     * 
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @SuppressWarnings("null")
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = null;
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.load_activity, null);
            holder.text = (TextView)convertView.findViewById(0);
            holder.icon = (ImageView)convertView.findViewById(0);
            convertView.setTag(holder);
        }
        else
        {
            convertView.getTag();
        }
        
        holder.text.setText("");
        holder.icon.setImageResource(0);
        return convertView;
    }
    
    static class ViewHolder
    {
        
        TextView text = null;
        
        ImageView icon = null;
        
    }
    
}
