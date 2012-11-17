package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;

/**
 * 申报结果
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-17 下午8:37:03
 */
public class DeclareResultVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 596350495022249905L;
	/**
	 * 申报结果编号
	 */
	private long declareresultid;
	/**
	 * 申报结果,1,true 通过,0,false 未通过
	 */
	private boolean declareresult;
	/**
	 * 申报结果内容
	 */
	private String declareresultcontent;
	/**
	 * 申报结果人员
	 */
	private String publisher;
	/**
	 * 申报结果时间，格式yyyy-MM-dd HH:mm:ss
	 */
	private String publishtime;

	public long getDeclareresultid()
	{
		return declareresultid;
	}

	public void setDeclareresultid(long declareresultid)
	{
		this.declareresultid = declareresultid;
	}

	public void setDeclareresultid(String declareresultid)
	{
		this.declareresultid = JavaLangUtil.StrToLong(declareresultid,-1l);
	}

	public boolean isDeclareresult()
	{
		return declareresult;
	}

	public void setDeclareresult(boolean declareresult)
	{
		this.declareresult = declareresult;
	}

	public void setDeclareresult(String declareresult)
	{
		this.declareresult = JavaLangUtil.StrToBool(declareresult,false);
	}

	public String getDeclareresultcontent()
	{
		return declareresultcontent;
	}

	public void setDeclareresultcontent(String declareresultcontent)
	{
		this.declareresultcontent = declareresultcontent;
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
