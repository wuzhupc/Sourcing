/**
* Project:Sourcing
* File:CompanyVO.java
* Copyright 2013 WUZHUPC Co., Ltd. All rights reserved.
*/
package com.wuzhupc.Sourcing.vo;

import android.content.Context;

import com.wuzhupc.services.MobileInfoService;
import com.wuzhupc.services.BaseJsonService.IBaseReceiver;
import com.wuzhupc.utils.JavaLangUtil;
import com.wuzhupc.utils.json.JsonParser;

/**
 * ��ҵ��Ϣ
 * @author wuzhu
 * @date 2013-4-20 ����3:47:10
 * @version $id$
 */
public class CompanyVO extends BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4693176269500381982L;

	/**
	 * ��ҵ����
	 */
	private String companyname;

	/**
	 * ��ҵ������ҵ
	 */
	private String industry;
	

	/**
	 * ����ʱ�䣬��ʽyyyy-MM-dd HH:mm:ss
	 */
	private String publishtime;

	public long getCompanyid()
	{
		return id;
	}

	public void setCompanyid(long companyid)
	{
		this.id = companyid;
	}

	public void setCompanyid(String companyid)
	{
		setCompanyid(JavaLangUtil.StrToLong(companyid,-1l));
	}
	
	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}

	
	/**
	 * ��������HTML����
	 * @return
	 */
	@Override
	public String getHtmlTitle()
	{
		String result = "<br/><div align=\"center\"><font color=\"#111111\" size=\"4pt\"><strong>"
				+getCompanyname()
				+"</strong></font></div><br/>";
		return result;
	}

	/**
	 * ���ɷ�����Ϣ���ݲ���
	 */
	@Override
	public String generateShareText()
	{
		return getCompanyname();
	}
	
	/**
	 * ��������HTML�ӱ���
	 * @return
	 */
	@Override
	public String getHtmlSubTitle()
	{
		String result = "<div align=\"center\"><font color=\"#666666\" size=\"2pt\">"+getPublishtime()+"&nbsp;&nbsp; "+getIndustry()+"</font></div><div style=\"height:0;border-bottom:1px solid #f00\"></div>";
		return result;
	}
	
	/**
	 * ��������Html������ʾ
	 */
	@Override
	public void setHtmlToShow(Context c, final DetailInfoListener detailInfoListener)
	{
		if(detailInfoListener==null)
			return;
		MobileInfoService infoService= new MobileInfoService(c);
		infoService.getCompanyDetail(getCompanyid(), new IBaseReceiver()
		{
			@Override
			public void receiveCompleted(boolean isSuc, String content)
			{
				if (!isSuc)
				{
					detailInfoListener.onError(content);
					return;
				}
				ResponseVO respVO = new ResponseVO();
				CompanyDetailVO mDetailVO = (CompanyDetailVO)JsonParser.parseJsonToEntity(content, respVO);
				if(respVO.getCode()!=ResponseVO.RESPONSE_CODE_SUCESS)
				{
					detailInfoListener.onError(respVO.getMsg());
					return;
				} 
				detailInfoListener.onComplete(mDetailVO);
			}
		});
	}
}
