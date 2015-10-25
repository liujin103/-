package com.example.quickindex;

import java.util.ArrayList;
import java.util.Collections;

import com.example.quickindex.QuickIndexBar.OnTouchWordListener;


import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	//定义集合放数据
private ArrayList<Friend>friends=new ArrayList<Friend>();

	private ListView listView;
	private TextView currentWord;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.listView);
		currentWord = (TextView) findViewById(R.id.currentWord);
		QuickIndexBar quickIndexBar=(QuickIndexBar)findViewById(R.id.quickIndexBar);
		quickIndexBar.setOnTouchWordListener(new OnTouchWordListener(){

			@Override
			public void onTouchWord(String word) {
				//Log.e("MainActiivty", word);
				//当点击右边的字母是 对应姓名的字母也显示出来
				//1.找到friends中，首字母为触摸字母的那个position
				for (int i = 0; i < friends.size(); i++) {
					String firstWord=friends.get(i).getPinyin().charAt(0)+"";
					if(firstWord.equals(word)){
						//2.让该position的item放到屏幕顶端，注意只需要将第一个放到顶端
						listView.setSelection(i);
						break;
					}
					
				}
				//在中间显示当前的字母
				showCurrentWord(word);
			}
			
		});
	//1.准备数据
		fillList();
	//2.对集合进行排序
		//排序  需要Friend类实现Comparable重写compareTo
		Collections.sort(friends);
	//3.设置Adapter
		listView.setAdapter(new FriendAdapter(this,friends));
		
		
	
	}
	private Handler handler = new Handler();
	/**
	 * 显示当前的字母
	 * @param word
	 */
	protected void showCurrentWord(String word) {
		currentWord.setText(word);
		currentWord.setVisibility(View.VISIBLE);
		//先取消之前post的消失的任务
				handler.removeCallbacksAndMessages(null);
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						currentWord.setVisibility(View.GONE);
					}
				}, 2000);
			}
		
	

	private void fillList() {
		// 虚拟数据
		friends.add(new Friend("李静静"));
		friends.add(new Friend("张笑笑"));
		friends.add(new Friend("阿三"));
		friends.add(new Friend("阿四"));
		friends.add(new Friend("段誉"));
		friends.add(new Friend("段正淳"));
		friends.add(new Friend("张三丰"));
		friends.add(new Friend("陈坤"));
		friends.add(new Friend("林俊杰1"));
		friends.add(new Friend("陈坤2"));
		friends.add(new Friend("王风"));
		friends.add(new Friend("琳琳"));
		friends.add(new Friend("张四"));
		friends.add(new Friend("林俊杰"));
		friends.add(new Friend("王二"));
		friends.add(new Friend("王二小"));
		friends.add(new Friend("赵四"));
		friends.add(new Friend("杨坤"));
		friends.add(new Friend("赵子龙"));
		friends.add(new Friend("杨阳"));
		friends.add(new Friend("李晓华"));
		friends.add(new Friend("宋江"));
		friends.add(new Friend("宋林林"));
		friends.add(new Friend("李伟3"));
		friends.add(new Friend("毕秀茹"));
		friends.add(new Friend("易婉婷"));
		friends.add(new Friend("丰羽"));
		friends.add(new Friend("刚强"));
		friends.add(new Friend("胡水勤"));
		friends.add(new Friend("陈影影"));
		friends.add(new Friend("王旭颖"));
		friends.add(new Friend("王倩倩"));
		friends.add(new Friend("罗方圆"));
		friends.add(new Friend("靳国良"));
		friends.add(new Friend("戴良才"));
		friends.add(new Friend("谢灿"));
		friends.add(new Friend("刘瑾"));
		friends.add(new Friend("高洁"));
		friends.add(new Friend("杜雪亭"));
		friends.add(new Friend("黄雅莉"));
	

		
		
		
		
		
		
		
		
	}

}
