package com.wuzhupc.Sourcing;

import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * ������
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-22 ����3:32:01
 */
public class HomeActivity extends BaseActivity implements OnGestureListener
{
	/**
	 * ����ʶ��
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
