package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;


/**
 * 用户审核信息
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-17 下午8:46:32
 */
public class AuditVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -51781556677540751L;
	/**
	 * 提交审核编号
	 */
	//private long auditid;
	/**
	 * 提交审核内容
	 */
	private String auditcontent;
	/**
	 * 提交审核时间，格式yyyy-MM-dd HH:mm:ss
	 */
	private String publishtime;
	/**
	 * 审核结果信息,如果未审核或无审核结果,则值为null
	 */
	private AuditResultVO auditResultVO;
	public long getAuditid()
	{
		return id;
	}
	public void setAuditid(long auditid)
	{
		this.id = auditid;
	}
	public void setAuditid(String auditid)
	{
		setAuditid(JavaLangUtil.StrToLong(auditid,-1l));
	}
	public String getAuditcontent()
	{
		return auditcontent;
	}
	public void setAuditcontent(String auditcontent)
	{
		this.auditcontent = auditcontent;
	}
	public String getPublishtime()
	{
		return publishtime;
	}
	public void setPublishtime(String publishtime)
	{
		this.publishtime = publishtime;
	}
	public AuditResultVO getAuditResultVO()
	{
		return auditResultVO;
	}
	public void setAuditResultVO(AuditResultVO auditResultVO)
	{
		this.auditResultVO = auditResultVO;
	}
	
	/**
	 * 审核状态
	 * @return
	 */
	public String getAuditStatus()
	{
		return  auditResultVO==null?"等待审核中":auditResultVO.getAuditStatus();
	}
	
}
