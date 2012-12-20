package com.wuzhupc.mobile.test.vo;

import com.wuzhupc.mobile.test.JavaLangUtil;



/**
 * ×ÉÑ¯´ð¸´
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ´´½¨Ê±¼ä£º2012-11-17 ÏÂÎç8:05:53
 */
public class ConsultResultVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1533848997978545169L;
	/**
	 * ×ÉÑ¯´ð¸´±àºÅ
	 */
	//private long consultresultid;
	/**
	 * ×ÉÑ¯´ð¸´ÄÚÈÝ
	 */
	private String consultresultcontent;
	/**
	 * ×ÉÑ¯´ð¸´ÈËÔ±
	 */
	private String publisher;
	/**
	 * ×ÉÑ¯´ð¸´Ê±¼ä£¬¸ñÊ½yyyy-MM-dd HH:mm:ss
	 */
	private String publishtime;
	public long getConsultresultid()
	{
		return id;
	}
	public void setConsultresultid(long consultresultid)
	{
		this.id = consultresultid;
	}
	public void setConsultresultid(String consultresultid)
	{
		setConsultresultid(JavaLangUtil.StrToLong(consultresultid,-1l));
	}
	public String getConsultresultcontent()
	{
		return consultresultcontent;
	}
	public void setConsultresultcontent(String consultresultcontent)
	{
		this.consultresultcontent = consultresultcontent;
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
