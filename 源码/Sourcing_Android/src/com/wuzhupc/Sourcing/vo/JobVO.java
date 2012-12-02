package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;

/**
 * 职位信息
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-17 下午6:57:03
 */
public class JobVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4793481217858034470L;

	/**
	 * 职位编号
	 */
	//private long jobid;
	/**
	 * 职位名称
	 */
	private String job;
	/**
	 * 招聘公司名称
	 */
	private String company;
	/**
	 * 发布时间，格式yyyy-MM-dd HH:mm:ss
	 */
	private String publishtime;
	public long getJobid()
	{
		return id;
	}
	public void setJobid(long jobid)
	{
		this.id = jobid;
	}
	public void setJobid(String jobid)
	{
		setJobid(JavaLangUtil.StrToLong(jobid,-1l));
	}
	public String getJob()
	{
		return job;
	}
	public void setJob(String job)
	{
		this.job = job;
	}
	public String getCompany()
	{
		return company;
	}
	public void setCompany(String company)
	{
		this.company = company;
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
