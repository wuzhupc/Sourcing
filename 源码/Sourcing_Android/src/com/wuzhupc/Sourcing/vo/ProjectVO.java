package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;

/**
 * ��Ŀ
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-17 ����7:51:40
 */
public class ProjectVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3697735431556885831L;

	/**
	 * ��Ŀ���
	 */
	//private long projectid;
	/**
	 * ��Ŀ����
	 */
	private String projectname;
	/**
	 * ��Ŀ״̬
	 */
	private String projectstatus;
	/**
	 * ����ʱ�䣬��ʽyyyy-MM-dd HH:mm:ss
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
