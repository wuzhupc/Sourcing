package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;

/**
 * 简历
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-17 下午7:01:57
 */
public class ResumeVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4251598675905669576L;
	/**
	 * 简历编号
	 */
	//private long resumeid;
	/**
	 * 简历名称
	 */
	private String resumetitle;
	/**
	 * 期望职业
	 */
	private String expectjob;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 发布时间，格式yyyy-MM-dd HH:mm:ss
	 */
	private String publishtime;

	public long getResumeid()
	{
		return id;
	}

	public void setResumeid(long resumeid)
	{
		this.id = resumeid;
	}

	public void setResumeid(String resumeid)
	{
		setResumeid(JavaLangUtil.StrToLong(resumeid,-1l));
	}

	public String getResumetitle()
	{
		return resumetitle;
	}

	public void setResumetitle(String resumetitle)
	{
		this.resumetitle = resumetitle;
	}

	public String getExpectjob()
	{
		return expectjob;
	}

	public void setExpectjob(String expectjob)
	{
		this.expectjob = expectjob;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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
