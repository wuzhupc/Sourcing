package com.wuzhupc.Sourcing.vo;

import android.content.Context;

import com.wuzhupc.services.MobileInfoService;
import com.wuzhupc.services.BaseJsonService.IBaseReceiver;
import com.wuzhupc.utils.JavaLangUtil;
import com.wuzhupc.utils.json.JsonParser;

/**
 * 项目
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-17 下午7:51:40
 */
public class ProjectVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3697735431556885831L;

	/**
	 * 项目编号
	 */
	//private long projectid;
	/**
	 * 项目名称
	 */
	private String projectname;
	/**
	 * 项目状态
	 */
	private String projectstatus;
	/**
	 * 发布时间，格式yyyy-MM-dd HH:mm:ss
	 */
	private String publishtime;

	public long getProjectid()
	{
		return id;
	}

	public void setProjectid(long projectid)
	{
		this.id = projectid;
	}

	public void setProjectid(String projectid)
	{
		setProjectid(JavaLangUtil.StrToLong(projectid,-1l));
	}

	public String getProjectname()
	{
		return projectname;
	}

	public void setProjectname(String projectname)
	{
		this.projectname = projectname;
	}

	public String getProjectstatus()
	{
		return projectstatus;
	}

	public void setProjectstatus(String projectstatus)
	{
		this.projectstatus = projectstatus;
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
				+getProjectname()
				+"</strong></font></div><br/>";
		return result;
	}

	/**
	 * 生成分享信息内容部分
	 */
	@Override
	public String generateShareText()
	{
		return getProjectname();
	}
	
	/**
	 * 返回详情HTML子标题
	 * @return
	 */
	@Override
	public String getHtmlSubTitle()
	{
		String result = "<div align=\"center\"><font color=\"#666666\" size=\"2pt\">"+getPublishtime()+"&nbsp;&nbsp; "+getProjectstatus()+"</font></div><div style=\"height:0;border-bottom:1px solid #f00\"></div>";
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
		infoService.getProjectDetail(getProjectid(), new IBaseReceiver()
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
				ProjectDetailVO mDetailVO = (ProjectDetailVO)JsonParser.parseJsonToEntity(content, respVO);
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
