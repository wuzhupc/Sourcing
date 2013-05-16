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
	
	@Override
	public String toString()
	{
		return SerializeUtil.objectSerialzeTOString(this);
	}
	
	/**
	 * 返回详情HTML标题
	 * @return
	 */
	public String getHtmlTitle()
	{
		return "";
	}

	
	/**
	 * 返回详情HTML子标题
	 * @return
	 */
	public String getHtmlSubTitle()
	{
		return "";
	}
	
	/**
	 * 返回详情HTML内容
	 * @return
	 */
	public String getHtmlContext(BaseActivity activity)
	{
		return "";
	}
	
	/**
	 * 设置内容Html内容显示
	 * @return 返回获取到的详情信息
	 */
	public void setHtmlToShow(Context c, final DetailInfoListener detailInfoListener)
	{
		if(detailInfoListener!=null)
			detailInfoListener.onComplete(this);
	}
	
	/**
	 * 生成分享信息内容部分
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
