package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;

/**
 * 咨询
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-17 下午8:21:52
 */
public class ConsultVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8311735190721341146L;
	/**
	 * 咨询编号
	 */
	private long consultid;
	/**
	 * 咨询内容
	 */
	private String consultcontent;
	/**
	 * 咨询时间，格式yyyy-MM-dd HH:mm:ss
	 */
	private String publishtime;
	
	/**
	 * 咨询答复信息,未有答复信息时为null
	 */
	private ConsultResultVO consultResultVO;

	public long getConsultid()
	{
		return consultid;
	}

	public void setConsultid(long consultid)
	{
		this.consultid = consultid;
	}

	public void setConsultid(String consultid)
	{
		this.consultid = JavaLangUtil.StrToLong(consultid,-1l);
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
