/**
* Project:Sourcing
* File:CompanyDetailVO.java
* Copyright 2013 WUZHUPC Co., Ltd. All rights reserved.
*/
package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.utils.WebViewUtil;

/**
 * ��ҵ��Ϣ����
 * @author wuzhu
 * @date 2013-4-20 ����3:55:04
 * @version $id$
 */
public class CompanyDetailVO extends CompanyVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9002877386264444781L;
	
	/**
	 * ��˾������ֱ�ӽ���˾��ص���Ϣ����(html��ʽ)
	 */
	private String companydesc;

	public String getCompanydesc()
	{
		return companydesc;
	}

	public void setCompanydesc(String companydesc)
	{
		this.companydesc = companydesc;
	}
	
	/**
	 * ��������HTML����
	 * @return
	 */
	@Override
	public String getHtmlContext(BaseActivity activity)
	{
		return WebViewUtil.getHtmlContext(activity, companydesc);
	}

}
