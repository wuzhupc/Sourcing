package com.wuzhupc.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewFlipper;

/**
 * ExViewFlipper����չ ViewFlipper�࣬��Ҫ�����Ӽ���View�л��¼�
 * @author wuzhu
 * @Description 
 */
public class ExViewFlipper extends ViewFlipper {

	/**
	 * ��ʾ�Ӽ�View���Listener
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
	 * ������ʾ�Ӽ�View���Listener��
	 * @param listener
	 */
	public void setOnDisplayerChildChangeListener(OnDisplayerChildChangeListener listener)
	{
		childChangeListener=listener;
	}
}
