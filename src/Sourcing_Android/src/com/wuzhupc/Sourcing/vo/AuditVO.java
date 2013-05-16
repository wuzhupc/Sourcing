package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;


/**
 * �û������Ϣ
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-17 ����8:46:32
 */
public class AuditVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -51781556677540751L;
	/**
	 * �ύ��˱��
	 */
	//private long auditid;
	/**
	 * �ύ�������
	 */
	private String auditcontent;
	/**
	 * �ύ���ʱ�䣬��ʽyyyy-MM-dd HH:mm:ss
	 */
	private String publishtime;
	/**
	 * ��˽����Ϣ,���δ��˻�����˽��,��ֵΪnull
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
	 * ���״̬
	 * @return
	 */
	public String getAuditStatus()
	{
		return  auditResultVO==null?"�ȴ������":auditResultVO.getAuditStatus();
	}
	
}
