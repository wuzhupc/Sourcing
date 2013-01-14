package com.wuzhupc.Sourcing.vo;

import android.content.Context;

import com.wuzhupc.services.MobileInfoService;
import com.wuzhupc.services.BaseJsonService.IBaseReceiver;
import com.wuzhupc.utils.JavaLangUtil;
import com.wuzhupc.utils.json.JsonParser;

/**
 * 职位信息
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-17 下午6:57:03
 */
public class JobVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4793481217858034470L;

	/**
	 * 职位编号
	 */
	//private long jobid;
	/**
	 * 职位名称
	 */
	private String job;
	/**
	 * 招聘公司名称
	 */
	private String company;
	/**
	 * 发布时间，格式yyyy-MM-dd HH:mm:ss
	 */
	private String publishtime;
	public long getJobid()
	{
		return id;
	}
	public void setJobid(long jobid)
	{
		this.id = jobid;
	}
	public void setJobid(String jobid)
	{
		setJobid(JavaLangUtil.StrToLong(jobid,-1l));
	}
	public String getJob()
	{
		return job;
	}
	public void setJob(String job)
	{
		this.job = job;
	}
	public String getCompany()
	{
		return company;
	}
	public void setCompany(String company)
	{
		this.company = company;
	}
	public String getPublishtime()
	{
		return publishtime;
	}
	public void setPublishtime(String publishtime)
	{
		this.publishtime = publishtime;
	}
	
	/**
	 * 返回详情HTML标题
	 * @return
	 */
	@Override
	public String getHtmlTitle()
	{
		String result = "<br/><div align=\"center\"><font color=\"#111111\" size=\"4pt\"><strong>"
				+getJob()
				+"</strong></font></div><br/>";
		return result;
	}

	/**
	 * 生成分享信息内容部分
	 */
	@Override
	public String generateShareText()
	{
		return getJob();
	}
	
	/**
	 * 返回详情HTML子标题
	 * @return
	 */
	@Override
	public String getHtmlSubTitle()
	{
		String result = "<div align=\"center\"><font color=\"#666666\" size=\"2pt\">"+getPublishtime()+"&nbsp;&nbsp; "+getCompany()+"</font></div><div style=\"height:0;border-bottom:1px solid #f00\"></div>";
		return result;
	}
	
	/**
	 * 设置内容Html内容显示
	 */
	@Override
	public void setHtmlToShow(Context c, final DetailInfoListener detailInfoListener)
	{
		if(detailInfoListener==null)
			return;
		MobileInfoService infoService= new MobileInfoService(c);
		infoService.getJobDetail(getJobid(), new IBaseReceiver()
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
				JobDetailVO mDetailVO = (JobDetailVO)JsonParser.parseJsonToEntity(content, respVO);
				if(respVO.getCode()!=ResponseVO.RESPONSE_CODE_SUCESS)
				{
					detailInfoListener.onError(respVO.getMsg());
					return;
				} 
				if(detailInfoListener!=null)
					detailInfoListener.onComplete(mDetailVO);
			}
		});
	}
}
