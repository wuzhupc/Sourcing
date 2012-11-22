package com.wuzhupc.Sourcing;

import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * 主界面
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-22 下午3:32:01
 */
public class HomeActivity extends BaseActivity implements OnGestureListener
{
	/**
	 * 手势识别
	 */
	private GestureDetector mGestureDetector;
	
	@Override
	protected void initView()
	{
		setContentView(R.layout.activity_home);
		//setTitleTextBold();
	}

	@Override
	protected void initActions()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean onDown(MotionEvent arg0)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
