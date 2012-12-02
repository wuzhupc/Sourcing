package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;

/**
 * 项目
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-17 下午7:51:40
 */
public class ProjectVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3697735431556885831L;

	/**
	 * 项目编号
	 */
	//private long projectid;
	/**
	 * 项目名称
	 */
	private String projectname;
	/**
	 * 项目状态
	 */
	private String projectstatus;
	/**
	 * 发布时间，格式yyyy-MM-dd HH:mm:ss
	 */
	private String publishtime;

	public long getProjectid()
	{
		return id;
	}

	public void setProjectid(long projectid)
	{
		this.id = projectid;
	}

	public void setProjectid(String projectid)
	{
		setProjectid(JavaLangUtil.StrToLong(projectid,-1l));
	}

	public String getProjectname()
	{
		return projectname;
	}

	public void setProjectname(String projectname)
	{
		this.projectname = projectname;
	}

	public String getProjectstatus()
	{
		return projectstatus;
	}

	public void setProjectstatus(String projectstatus)
	{
		this.projectstatus = projectstatus;
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
