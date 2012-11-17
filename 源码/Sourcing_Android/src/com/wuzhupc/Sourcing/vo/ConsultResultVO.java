package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;


/**
 * ��ѯ��
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-17 ����8:05:53
 */
public class ConsultResultVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1533848997978545169L;
	/**
	 * ��ѯ�𸴱��
	 */
	private long consultresultid;
	/**
	 * ��ѯ������
	 */
	private String consultresultcontent;
	/**
	 * ��ѯ����Ա
	 */
	private String publisher;
	/**
	 * ��ѯ��ʱ�䣬��ʽyyyy-MM-dd HH:mm:ss
	 */
	private String publishtime;
	public long getConsultresultid()
	{
		return consultresultid;
	}
	public void setConsultresultid(long consultresultid)
	{
		this.consultresultid = consultresultid;
	}
	public void setConsultresultid(String consultresultid)
	{
		this.consultresultid = JavaLangUtil.StrToLong(consultresultid,-1l);
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
