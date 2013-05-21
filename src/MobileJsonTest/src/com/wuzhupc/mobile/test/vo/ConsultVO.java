package com.wuzhupc.mobile.test.vo;

import com.wuzhupc.mobile.test.JavaLangUtil;

/**
 * ��ѯ
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-17 ����8:21:52
 */
public class ConsultVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8311735190721341146L;
	/**
	 * ��ѯ���
	 */
	//private long consultid;
	/**
	 * ��ѯ����
	 */
	private String consultcontent;
	/**
	 * ��ѯʱ�䣬��ʽyyyy-MM-dd HH:mm:ss
	 */
	private String publishtime;
	
	/**
	 * ��ѯ����Ϣ,δ�д���ϢʱΪnull
	 */
	private ConsultResultVO consultResultVO;

	public long getConsultid()
	{
		return id;
	}

	public void setConsultid(long consultid)
	{
		this.id = consultid;
	}

	public void setConsultid(String consultid)
	{
		setConsultid(JavaLangUtil.StrToLong(consultid,-1l));
	}

	public String getConsultcontent()
	{
		return consultcontent;
	}

	public void setConsultcontent(String consultcontent)
	{
		this.consultcontent = consultcontent;
	}

	public String getPublishtime()
	{
		return publishtime;
	}

	public void setPublishtime(String publishtime)
	{
		this.publishtime = publishtime;
	}

	public ConsultResultVO getConsultResultVO()
	{
		return consultResultVO;
	}

	public void setConsultResultVO(ConsultResultVO consultResultVO)
	{
		this.consultResultVO = consultResultVO;
	}
	
}