package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;

/**
 * ֪ͨ����
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-17 ����8:56:09
 */
public class NotifierVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8813295725140035628L;
	/**
	 * ֪ͨ���ѱ��
	 */
	//private long notifierid;
	/**
	 * ֪ͨ��������
	 */
	private String notifiercontent;
	/**
	 * ֪ͨ���ѷ�����
	 */
	private String publisher;
	/**
	 * ֪ͨ����ʱ�䣬��ʽyyyy-MM-dd HH:mm:ss
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
