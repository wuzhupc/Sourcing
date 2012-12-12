package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.utils.WebViewUtil;

/**
 * 
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-17 ����7:46:54
 */
public class TrainDetailVO extends TrainVO
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8243877338589212730L;
	
	private String traincontent;

	public String getTraincontent()
	{
		return traincontent;
	}

	/**
	 * ��ѵ��������������Ϣ��ֱ�ӽ���ѵ������ص���Ϣ����
	 * @param traincontent
	 */
	public void setTraincontent(String traincontent)
	{
		this.traincontent = traincontent;
	}
	
	/**
	 * ��������HTML����
	 * @return
	 */
	@Override
	public String getHtmlContext(BaseActivity activity)
	{
		return WebViewUtil.getHtmlContext(activity, traincontent);
	}
}
