package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;


/**
 * 申报信息
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-17 下午8:46:32
 */
public class DeclareVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2279845658915038666L;
	/**
	 * 申报编号
	 */
	//private long declareid;
	/**
	 * 申报内容
	 */
	private String declarecontent;
	/**
	 * 申报时间，格式yyyy-MM-dd HH:mm:ss
	 */
	private String publishtime;
	/**
	 * 申报结果信息,如果无申报结果,则值为null
	 */
	private DeclareResultVO declareResultVO;
	
	public String getDeclareStatus()
	{
		return  declareResultVO==null?"等待处理中":declareResultVO.getDeclareStatus();	
	}
	
	public String getPublishtime()
	{
		return publishtime;
	}
	public void setPublishtime(String publishtime)
	{
		this.publishtime = publishtime;
	}
	public long getDeclareid()
	{
		return id;
	}
	public void setDeclareid(long declareid)
	{
		this.id = declareid;
	}
	public void setDeclareid(String declareid)
	{
		setDeclareid(JavaLangUtil.StrToLong(declareid,-1l));
	}
	public String getDeclarecontent()
	{
		return declarecontent;
	}
	public void setDeclarecontent(String declarecontent)
	{
		this.declarecontent = declarecontent;
	}
	public DeclareResultVO getDeclareResultVO()
	{
		return declareResultVO;
	}
	public void setDeclareResultVO(DeclareResultVO declareResultVO)
	{
		this.declareResultVO = declareResultVO;
	}
	
}
