package com.wuzhupc.Sourcing.vo;

/**
 * 职位详细信息
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-17 下午7:00:17
 */
public class JobDetailVO extends JobVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7276426982805276938L;

	/**
	 * 职位描述，直接将职位相关的信息返回
	 */
	private String jobdesc;

	public String getJobdesc()
	{
		return jobdesc;
	}

	public void setJobdesc(String jobdesc)
	{
		this.jobdesc = jobdesc;
	}
}
