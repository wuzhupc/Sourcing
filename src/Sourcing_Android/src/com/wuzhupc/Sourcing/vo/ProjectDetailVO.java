package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.utils.WebViewUtil;

/**
 * ��Ŀ����
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-17 ����7:53:44
 */
public class ProjectDetailVO extends ProjectVO
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6102401878309897984L;

	/**
	 * ��Ŀ����������Ϣ��ֱ�ӽ���Ŀ��ص���Ϣ����
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
	 * ��������HTML����
	 * @return
	 */
	@Override
	public String getHtmlContext(BaseActivity activity)
	{
		return WebViewUtil.getHtmlContext(activity, projectcontent);
	}
}
