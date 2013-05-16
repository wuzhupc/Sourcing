package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;

/**
 * 通知提醒
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-17 下午8:56:09
 */
public class NotifierVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8813295725140035628L;
	/**
	 * 通知提醒编号
	 */
	//private long notifierid;
	/**
	 * 通知提醒内容
	 */
	private String notifiercontent;
	/**
	 * 通知提醒发布者
	 */
	private String publisher;
	/**
	 * 通知提醒时间，格式yyyy-MM-dd HH:mm:ss
	 */
	private String publishtime;
	public long getNotifierid()
	{
		return id;
	}
	public void setNotifierid(long notifierid)
	{
		this.id = notifierid;
	}
	public void setNotifierid(String notifierid)
	{
		setNotifierid(JavaLangUtil.StrToLong(notifierid,-1l));
	}
	public String getNotifiercontent()
	{
		return notifiercontent;
	}
	public void setNotifiercontent(String notifiercontent)
	{
		this.notifiercontent = notifiercontent;
	}
	public String getPublisher()
	{
		return publisher;
	}
	public void setPublisher(String publisher)
	{
		this.publisher = publisher;
	}
	public String getPublishtime()
	{
		return publishtime;
	}
	public void setPublishtime(String publishtime)
	{
		this.publishtime = publishtime;
	}
	
	
}
