package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.utils.WebViewUtil;

/**
 * 简历详情
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-17 下午7:04:15
 */
public class ResumeDetailVO extends ResumeVO
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6990761424865126790L;

	/**
	 * 简历内容，直接将简历相关的信息返回
	 */
	private String resumecontent;

	public String getResumecontent()
	{
		return resumecontent;
	}

	public void setResumecontent(String resumecontent)
	{
		this.resumecontent = resumecontent;
	}
	
	/**
	 * 返回详情HTML内容
	 * @return
	 */
	@Override
	public String getHtmlContext(BaseActivity activity)
	{
		return WebViewUtil.getHtmlContext(activity, resumecontent);
	}
}
