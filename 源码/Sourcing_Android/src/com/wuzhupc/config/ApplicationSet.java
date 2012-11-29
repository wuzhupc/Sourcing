package com.wuzhupc.config;

import com.wuzhupc.Sourcing.vo.UserVO;

import android.app.Application;

/**
 * 全局变量
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-19 下午10:23:17
 */
public class ApplicationSet extends Application
{
	/**
	 *  用户登录信息
	 */
	private UserVO mUserVO;

	public UserVO getUserVO()
	{
		return mUserVO;
	}

	public void setUserVO(UserVO mUserVO)
	{
		this.mUserVO = mUserVO;
	} 
}
