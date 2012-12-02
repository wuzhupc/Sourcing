package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;

/**
 * ��˽��
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-17 ����8:37:03
 */
public class AuditResultVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 66310021634062178L;
	/**
	 * ��˽�����
	 */
	//private long auditresultid;
	/**
	 * ��˽��,1,true ͨ��,0,false δͨ��
	 */
	private boolean auditresult;
	/**
	 * ��˽������
	 */
	private String auditresultcontent;
	/**
	 * �����Ա
	 */
	private String publisher;
	/**
	 * ��˽��ʱ�䣬��ʽyyyy-MM-dd HH:mm:ss
	 */
	private String publishtime;

	public long getAuditresultid()
	{
		return id;
	}

	public void setAuditresultid(long auditresultid)
	{
		this.id = auditresultid;
	}

	public void setAuditresultid(String auditresultid)
	{
		setAuditresultid(JavaLangUtil.StrToLong(auditresultid,-1l));
	}

	public boolean isAuditresult()
	{
		return auditresult;
	}

	public void setAuditresult(boolean auditresult)
	{
		this.auditresult = auditresult;
	}

	public void setAuditresult(String auditresult)
	{
		this.auditresult = JavaLangUtil.StrToBool(auditresult,false);
	}

	public String getAuditresultcontent()
	{
		return auditresultcontent;
	}

	public void setAuditresultcontent(String auditresultcontent)
	{
		this.auditresultcontent = auditresultcontent;
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
