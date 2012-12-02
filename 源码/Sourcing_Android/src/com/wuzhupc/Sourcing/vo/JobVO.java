package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;

/**
 * ְλ��Ϣ
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-17 ����6:57:03
 */
public class JobVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4793481217858034470L;

	/**
	 * ְλ���
	 */
	//private long jobid;
	/**
	 * ְλ����
	 */
	private String job;
	/**
	 * ��Ƹ��˾����
	 */
	private String company;
	/**
	 * ����ʱ�䣬��ʽyyyy-MM-dd HH:mm:ss
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
