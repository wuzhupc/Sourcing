package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.utils.WebViewUtil;

/**
 * ��������
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-17 ����7:04:15
 */
public class ResumeDetailVO extends ResumeVO
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6990761424865126790L;

	/**
	 * �������ݣ�ֱ�ӽ�������ص���Ϣ����
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
	 * ��������HTML����
	 * @return
	 */
	@Override
	public String getHtmlContext(BaseActivity activity)
	{
		return WebViewUtil.getHtmlContext(activity, resumecontent);
	}
}
