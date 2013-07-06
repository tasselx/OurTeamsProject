package com.android.listviewfy;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ListviewFYActivity extends Activity implements OnScrollListener, OnClickListener, OnItemClickListener
{
    /** Called when the activity is first created. */
    private ListView listView;
    
    private Button btn, btn_add, btn_update, btn_delete;
    
    private ProgressBar pb;
    
    // listview底部view
    private View moreView;
    
    private Handler handler;
    
    // 设置最大的数据条数，超过即不加载
    private int MaxDateNum;
    
    // 最后可见条目的索引
    private int lastVisibleIndex;
    
    private MyAdapter myAdapter;
    
    private ArrayList<Entity> arrayList;
    
    private View currentView;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        MaxDateNum = 38;
        listView = (ListView)findViewById(R.id.listview);
        moreView = getLayoutInflater().inflate(R.layout.moredate, null);
        btn = (Button)moreView.findViewById(R.id.bt_load);
        pb = (ProgressBar)moreView.findViewById(R.id.pb);
        
        handler = new Handler();
        arrayList = new ArrayList<Entity>();
        // 用arraylist来装载数据，初始化10条数据
        for (int i = 0; i < 10; i++)
        {
            Entity entity = new Entity("First", i, "F     i     r     s     t ");
            arrayList.add(entity);
        }
        myAdapter = new MyAdapter(this, arrayList);
        // 底部view要放在setAdapter前
        listView.addFooterView(moreView);
        listView.setAdapter(myAdapter);
        
        // 绑定监听器
        listView.setOnScrollListener(this);
        listView.setOnItemClickListener(this);
        btn.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bt_load:
                // 进度条可见
                pb.setVisibility(View.VISIBLE);
                // 按钮不可见
                btn.setVisibility(View.GONE);
                handler.postDelayed(new Runnable()
                {
                    
                    @Override
                    public void run()
                    {
                        // 加载更多数据
                        loadMoreDate();
                        btn.setVisibility(View.VISIBLE);
                        pb.setVisibility(View.GONE);
                        // 通知listview刷新数据
                        myAdapter.notifyDataSetChanged();
                    }
                }, 2000);
                break;
            
            case R.id.add:
                Intent intent1 = new Intent(ListviewFYActivity.this, MessageActivity.class);
                startActivity(intent1);
                break;
            case R.id.update:
                Intent intent2 = new Intent(ListviewFYActivity.this, MessageActivity.class);
                startActivity(intent2);
                break;
            case R.id.delete:
                
                break;
        }
        
    }
    
    private void loadMoreDate()
    {
        int count = myAdapter.getCount();
        if (count + 10 < MaxDateNum)
        {
            // 每次加载10条
            for (int i = count; i < count + 10; i++)
            {
                Entity entity = new Entity("Second", i, "S     e     c     o     n     d");
                arrayList.add(entity);
            }
        }
        else
        {
            // 数据不足10条
            for (int i = count; i < MaxDateNum; i++)
            {
                Entity entity = new Entity("Not enough ten item", i, "Not enough ten item");
                arrayList.add(entity);
            }
        }
    }
    
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
    {
        // 计算最后可见条目的索引
        lastVisibleIndex = firstVisibleItem + visibleItemCount - 1;
        // 所有的条目已经和最大条数相等，则移除底部的View
        if (totalItemCount == MaxDateNum + 1)
        {
            listView.removeFooterView(moreView);
            Toast.makeText(this, "数据全部加载完成，没有更多数据！", Toast.LENGTH_LONG).show();
        }
    }
    
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState)
    {
        // 滑到底部后自动加载，判断listview已经停止滚动并且最后可视的条目等于adapter的条目
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE && lastVisibleIndex == myAdapter.getCount())
        {
            // 当滑到底部时自动加载
            pb.setVisibility(View.VISIBLE);
            btn.setVisibility(View.GONE);
            handler.postDelayed(new Runnable()
            {
                
                @Override
                public void run()
                {
                    loadMoreDate();
                    btn.setVisibility(View.VISIBLE);
                    pb.setVisibility(View.GONE);
                    // 通知listview刷新数据
                    myAdapter.notifyDataSetChanged();
                }
            }, 2000);
        }
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        // currentPosition = position;
        Animation ani_left = AnimationUtils.loadAnimation(this, R.anim.push_left_in);
        Animation ani_leftout = AnimationUtils.loadAnimation(this, R.anim.push_left_out);
        if (currentView == null)
        {
            currentView = view.findViewById(R.id.item_anim);
            currentView.setAnimation(ani_left);
        }
        else
        {
            currentView.setAnimation(ani_leftout);
            currentView.setVisibility(View.GONE);
            currentView = view.findViewById(R.id.item_anim);
            currentView.setAnimation(ani_left);
        }
        
        btn_add = (Button)currentView.findViewById(R.id.add);
        btn_update = (Button)currentView.findViewById(R.id.update);
        btn_delete = (Button)currentView.findViewById(R.id.delete);
        currentView.setVisibility(View.VISIBLE);
        btn_add.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
    }
}