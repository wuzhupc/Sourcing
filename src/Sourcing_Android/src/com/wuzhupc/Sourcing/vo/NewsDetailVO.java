package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.utils.WebViewUtil;

/**
 * 资讯详细信息
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-17 下午12:40:42
 */
public class NewsDetailVO extends NewsVO
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5340215392145408724L;
	/**
	 * 新闻内容
	 */
	private String newscontent;
	public String getNewscontent()
	{
		return newscontent;
	}
	public void setNewscontent(String newscontent)
	{
		this.newscontent = newscontent;
	}
	
	/**
	 * 返回详情HTML内容
	 * @return
	 */
	@Override
	public String getHtmlContext(BaseActivity activity)
	{
		return WebViewUtil.getHtmlContext(activity, newscontent);
	}
}
