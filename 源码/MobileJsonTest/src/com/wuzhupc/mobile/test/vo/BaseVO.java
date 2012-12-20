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
	 * ID ���ڱ�ʶ��������������Խ�idʱ����ҪҪͬʱ���˸�ֵ
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
	 * ���أ������жϣ���һ����idһ��ʱ����true
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
