package com.wuzhupc.mobile.test.vo;

import java.io.Serializable;

/**
 * 
 * @author Administrator
 *
 */
public class BaseVO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3185317889045447937L;
	/**
	 * ID 用于标识，所有子类如果自建id时，需要要同时给此赋值
	 */
	protected long id;
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	
	/**
	 * 重载，增加判断：类一样且id一致时返回true
	 */
	@Override
	public boolean equals(Object o)
	{
		if(super.equals(o))
			return true;
		if(o==null||!(o instanceof BaseVO))
			return false;
		return this.getClass().getSimpleName().equals(o.getClass().getSimpleName()) &&this.id==((BaseVO)o).id;
	}
	
	public interface DetailInfoListener
	{
		public void onComplete(BaseVO baseVO);
		public void onError(String msg);
	}
}
