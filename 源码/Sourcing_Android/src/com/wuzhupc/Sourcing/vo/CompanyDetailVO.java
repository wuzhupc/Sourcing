/**
* Project:Sourcing
* File:CompanyDetailVO.java
* Copyright 2013 WUZHUPC Co., Ltd. All rights reserved.
*/
package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.utils.WebViewUtil;

/**
 * 企业信息详情
 * @author wuzhu
 * @date 2013-4-20 下午3:55:04
 * @version $id$
 */
public class CompanyDetailVO extends CompanyVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9002877386264444781L;
	
	/**
	 * 公司描述，直接将公司相关的信息返回(html格式)
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
	 * 返回详情HTML内容
	 * @return
	 */
	@Override
	public String getHtmlContext(BaseActivity activity)
	{
		return WebViewUtil.getHtmlContext(activity, companydesc);
	}

}
