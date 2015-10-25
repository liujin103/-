package com.example.quickindex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class QuickIndexBar extends View{
	private String[] indexArr = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z" };
//初始画笔 
	private Paint paint;
	//数组

	private int width;
	private float cellHeight;//格子的高度
	public QuickIndexBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public QuickIndexBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public QuickIndexBar(Context context) {
		super(context);
		init();
	}
	/**
	 * 初始化的方法
	 */
	private void init(){
		//获取在dimen中设置的字体
		float textSize=getResources().getDimension(R.dimen.text_size);
		//初始化画笔
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);//设置抗锯齿
		//设置白色字体
		paint.setColor(Color.WHITE);
		//设置字体大小
		paint.setTextSize(textSize);
		//设置文本绘制的起点是底边的中心点
		paint.setTextAlign(Align.CENTER);
	}
	//初始化宽度
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		//总宽度
		width = getMeasuredWidth();
		//格子高度
		cellHeight = (getMeasuredHeight()*1f/indexArr.length);
	}
//把26个字母绘制到view中用onDraw实现
	@Override
	protected void onDraw(Canvas canvas) {
		//依次将26个字母绘制到指定的位置上面
		for (int i = 0; i < indexArr.length; i++) {
			String text=indexArr[i];
			//绘制字体的宽度
			float x=width/2;
			//绘制字体的高度
			//格子高度的一半+字体的高度的一半就是字体+字体个数*格子高度
			float y=cellHeight/2+getTextHeight(text)/2+i*cellHeight;
			canvas.drawText(text,x, y, paint);
	//重新绘制 选中的字母变为灰色
			paint.setColor(i==lastIndex?Color.BLACK:Color.WHITE);
			canvas.drawText(text, x, y, paint);
		}
		
	}
	/**
	 * 获取文本的高度
	 */
	private int getTextHeight(String text) {
	//定义一个矩形对象 得到对象的同时  文字矩形宽的宽高也得到了
		Rect bounds=new Rect();
		//绘制一个矩形
		paint.getTextBounds(text, 0, text.length(), bounds);
		return bounds.height();
	}
	/**
	 *触摸不同的位置返回相应的字母 
	 * */
	private int lastIndex = -1;//上次字母的索引
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			//得到的是对应字母的索引
			int y=(int)event.getY();
			int index=(int)(y/cellHeight);
			if(index!=lastIndex){
				//如果当前的字母索引和上个字母索引不一样，则打印
				if(index>=0 && index<indexArr.length){
					//对index作安全性的检测
					if(listener!=null){
						listener.onTouchWord(indexArr[index]);
						
					}
				}
				
			}
			lastIndex = index;
			break;

		case MotionEvent.ACTION_UP:
			lastIndex = -1;//重置索引
			break;
		}
		invalidate();//引起重绘
		return true;//因为要接受和处理TouchMove
	}
	
	//以下是回调接口模式 非常重要 必须会
		private OnTouchWordListener listener;
	/**
	 * 设置触摸字母的监听(有借口需要定义一个set方法)
	 */
	public void setOnTouchWordListener(OnTouchWordListener listener){
		this.listener = listener;
	}
	/**触摸字母的监听*/
	public interface OnTouchWordListener{
		/**当触摸到字母*/
		void onTouchWord(String word);
		
	}
	

}
