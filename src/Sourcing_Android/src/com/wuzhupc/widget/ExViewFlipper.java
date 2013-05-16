package com.wuzhupc.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewFlipper;

/**
 * ExViewFlipper　扩展 ViewFlipper类，主要是增加监听View切换事件
 * @author wuzhu
 * @Description 
 */
public class ExViewFlipper extends ViewFlipper {

	/**
	 * 显示子级View变更Listener
	 */
	private OnDisplayerChildChangeListener childChangeListener=null; 
	
	public ExViewFlipper(Context context) {
		super(context);
	}

	public ExViewFlipper(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	 public void setDisplayedChild(int whichChild)
	 {
		int oldWhichChild=getDisplayedChild();
		 super.setDisplayedChild(whichChild);
		 if(childChangeListener!=null)
			 childChangeListener.onChanged(oldWhichChild, whichChild);
	 }
	
	/**
	 * 设置显示子级View变更Listener　
	 * @param listener
	 */
	public void setOnDisplayerChildChangeListener(OnDisplayerChildChangeListener listener)
	{
		childChangeListener=listener;
	}
}
