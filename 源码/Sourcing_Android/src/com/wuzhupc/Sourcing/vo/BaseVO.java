package com.wuzhupc.Sourcing.vo;

import java.io.Serializable;

import android.content.Context;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.utils.SerializeUtil;

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
	
	@Override
	public String toString()
	{
		return SerializeUtil.objectSerialzeTOString(this);
	}
	
	/**
	 * ��������HTML����
	 * @return
	 */
	public String getHtmlTitle()
	{
		return "";
	}

	
	/**
	 * ��������HTML�ӱ���
	 * @return
	 */
	public String getHtmlSubTitle()
	{
		return "";
	}
	
	/**
	 * ��������HTML����
	 * @return
	 */
	public String getHtmlContext(BaseActivity activity)
	{
		return "";
	}
	
	/**
	 * ��������Html������ʾ
	 * @return ���ػ�ȡ����������Ϣ
	 */
	public void setHtmlToShow(Context c, final DetailInfoListener detailInfoListener)
	{
		if(detailInfoListener!=null)
			detailInfoListener.onComplete(this);
	}
	
	/**
	 * ���ɷ�����Ϣ���ݲ���
	 */
	public String generateShareText()
	{
		return "";
	}
	
	public interface DetailInfoListener
	{
		public void onComplete(BaseVO baseVO);
		public void onError(String msg);
	}
}
