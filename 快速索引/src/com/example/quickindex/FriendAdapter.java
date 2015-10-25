package com.example.quickindex;

import java.util.ArrayList;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class FriendAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<Friend> list;
	public FriendAdapter(Context context,ArrayList<Friend>list){
		super();
		this.context = context;
		this.list = list;
	}
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView=View.inflate(context, R.layout.adapter_friend, null);	
		}
		FriendHolder holder=FriendHolder.getHolder(convertView);
		Friend friend=list.get(position);
		holder.tv_name.setText(friend.getName());
		holder.tv_frist_word.setText(friend.getPinyin());
		//1.获取当前的首字母
		String firstWord=friend.getPinyin().charAt(0)+"";//获取首字母
		//2.获取上一个friend的首字母
		if(position>0){
			String lastWord=list.get(position-1).getPinyin().charAt(0)+"";
			//3.如果当前的首字母和上一个首字母相同，则隐藏当前的tv_first_word
			if(firstWord.equals(lastWord)){
				holder.tv_frist_word.setVisibility(View.GONE);
			}else{
				//不相等
				holder.tv_frist_word.setVisibility(View.VISIBLE);
				holder.tv_frist_word.setText(firstWord);
			}
		}else{
			holder.tv_frist_word.setVisibility(View.VISIBLE);
			holder.tv_frist_word.setText(firstWord);
		}
		return convertView;
	}
	static class FriendHolder{
		TextView tv_frist_word,tv_name;
		public FriendHolder(View convertView){
			tv_frist_word = (TextView) convertView.findViewById(R.id.tv_first_word);
			tv_name = (TextView) convertView.findViewById(R.id.tv_name);
		}
		public static FriendHolder getHolder(View convertView){
			FriendHolder friendHolder=(FriendHolder)convertView.getTag();
			if(friendHolder==null){
				friendHolder=new FriendHolder(convertView);
				convertView.setTag(friendHolder);
			}
			return friendHolder;
		}
	}
	
}

	