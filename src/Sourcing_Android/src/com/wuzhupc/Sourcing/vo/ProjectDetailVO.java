package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.utils.WebViewUtil;

/**
 * 项目详情
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-17 下午7:53:44
 */
public class ProjectDetailVO extends ProjectVO
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6102401878309897984L;

	/**
	 * 项目介绍内容信息，直接将项目相关的信息返回
	 */
	private String projectcontent;

	public String getProjectcontent()
	{
		return projectcontent;
	}

	public void setProjectcontent(String projectcontent)
	{
		this.projectcontent = projectcontent;
	}
	
	/**
	 * 返回详情HTML内容
	 * @return
	 */
	@Override
	public String getHtmlContext(BaseActivity activity)
	{
		return WebViewUtil.getHtmlContext(activity, projectcontent);
	}
}
