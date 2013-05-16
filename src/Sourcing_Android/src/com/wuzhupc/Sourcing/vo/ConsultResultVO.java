package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;


/**
 * 咨询答复
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-17 下午8:05:53
 */
public class ConsultResultVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1533848997978545169L;
	/**
	 * 咨询答复编号
	 */
	//private long consultresultid;
	/**
	 * 咨询答复内容
	 */
	private String consultresultcontent;
	/**
	 * 咨询答复人员
	 */
	private String publisher;
	/**
	 * 咨询答复时间，格式yyyy-MM-dd HH:mm:ss
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
