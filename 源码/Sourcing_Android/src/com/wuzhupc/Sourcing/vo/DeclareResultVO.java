package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;

/**
 * �걨���
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-17 ����8:37:03
 */
public class DeclareResultVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 596350495022249905L;
	/**
	 * �걨������
	 */
	private long declareresultid;
	/**
	 * �걨���,1,true ͨ��,0,false δͨ��
	 */
	private boolean declareresult;
	/**
	 * �걨�������
	 */
	private String declareresultcontent;
	/**
	 * �걨�����Ա
	 */
	private String publisher;
	/**
	 * �걨���ʱ�䣬��ʽyyyy-MM-dd HH:mm:ss
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
