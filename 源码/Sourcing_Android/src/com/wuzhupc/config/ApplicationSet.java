package com.wuzhupc.config;

import com.wuzhupc.Sourcing.vo.UserVO;

import android.app.Application;

/**
 * ȫ�ֱ���
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-19 ����10:23:17
 */
public class ApplicationSet extends Application
{
	/**
	 *  �û���¼��Ϣ
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
