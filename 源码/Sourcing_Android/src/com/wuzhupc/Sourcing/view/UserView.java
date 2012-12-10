package com.wuzhupc.Sourcing.view;

import com.wuzhupc.Sourcing.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * 用户信息View
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-12-5 下午9:53:48
 */
public class UserView extends BaseView
{

	public UserView(Context context, long fatherchannelid)
	{
		super(context, fatherchannelid,false,false);
	}

	@Override
	public void loadData(Boolean isfirstload)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reflashData()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initContentView()
	{
		View v = LayoutInflater.from(mContext).inflate(R.layout.view_user, mll_content, false);
		setContentView(v);
	}

	@Override
	public void reflashContentView()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void initNavigation(View v)
	{
		//不用显示子栏目栏
		LinearLayout ll = (LinearLayout)findViewById(R.id.base_subchannel_ll);
		ll.setVisibility(View.GONE);
	}

	@Override
	public void searchAction(EditText et)
	{
		// TODO Auto-generated method stub
		
	}

}
