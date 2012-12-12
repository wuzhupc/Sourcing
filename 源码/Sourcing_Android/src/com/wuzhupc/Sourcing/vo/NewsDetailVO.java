package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.utils.WebViewUtil;

/**
 * ��Ѷ��ϸ��Ϣ
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-17 ����12:40:42
 */
public class NewsDetailVO extends NewsVO
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5340215392145408724L;
	/**
	 * ��������
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
	 * ��������HTML����
	 * @return
	 */
	@Override
	public String getHtmlContext(BaseActivity activity)
	{
		return WebViewUtil.getHtmlContext(activity, newscontent);
	}
}
