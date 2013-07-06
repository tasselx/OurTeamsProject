package com.android.listviewfy;

import java.util.ArrayList;
import android.content.Context; 
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{
	@SuppressWarnings("unused")
	private Context context;
	private ArrayList<Entity> arrayList;
	private LayoutInflater inflater;
	public MyAdapter(Context context,ArrayList<Entity> arrayList){
		inflater = LayoutInflater.from(context);
		this.context = context;
		this.arrayList = arrayList;
	}
	public int getCount() {
		return arrayList.size();
	}

	public Object getItem(int position) {
		return arrayList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}
	
	static class ViewHolder{
		TextView textView1,textView2,textView3;
		
	}
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = null ;
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item, null);
			holder.textView1 = (TextView)convertView.findViewById(R.id.tv_title);
			holder.textView2 = (TextView)convertView.findViewById(R.id.tv_content);
			holder.textView3 = (TextView)convertView.findViewById(R.id.tv_add);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag(); 		
		}
		holder.textView1.setText(arrayList.get(position).getName().trim());
		holder.textView2.setText(arrayList.get(position).getAge()+" ");
		holder.textView3.setText(arrayList.get(position).getAdd().trim());
		return convertView;
	}

}
