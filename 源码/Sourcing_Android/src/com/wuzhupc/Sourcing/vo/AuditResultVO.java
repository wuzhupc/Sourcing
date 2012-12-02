package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;

/**
 * 审核结果
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-17 下午8:37:03
 */
public class AuditResultVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 66310021634062178L;
	/**
	 * 审核结果编号
	 */
	//private long auditresultid;
	/**
	 * 审核结果,1,true 通过,0,false 未通过
	 */
	private boolean auditresult;
	/**
	 * 审核结果内容
	 */
	private String auditresultcontent;
	/**
	 * 审核人员
	 */
	private String publisher;
	/**
	 * 审核结果时间，格式yyyy-MM-dd HH:mm:ss
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
