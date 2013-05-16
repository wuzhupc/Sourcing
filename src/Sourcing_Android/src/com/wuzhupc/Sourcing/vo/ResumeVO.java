package com.wuzhupc.Sourcing.vo;

import android.content.Context;

import com.wuzhupc.services.MobileInfoService;
import com.wuzhupc.services.BaseJsonService.IBaseReceiver;
import com.wuzhupc.utils.JavaLangUtil;
import com.wuzhupc.utils.json.JsonParser;

/**
 * 简历
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-17 下午7:01:57
 */
public class ResumeVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4251598675905669576L;
	/**
	 * 简历编号
	 */
	//private long resumeid;
	/**
	 * 简历名称
	 */
	private String resumetitle;
	/**
	 * 期望职业
	 */
	private String expectjob;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 发布时间，格式yyyy-MM-dd HH:mm:ss
	 */
	private String publishtime;

	public long getResumeid()
	{
		return id;
	}

	public void setResumeid(long resumeid)
	{
		this.id = resumeid;
	}

	public void setResumeid(String resumeid)
	{
		setResumeid(JavaLangUtil.StrToLong(resumeid,-1l));
	}

	public String getResumetitle()
	{
		return resumetitle;
	}

	public void setResumetitle(String resumetitle)
	{
		this.resumetitle = resumetitle;
	}

	public String getExpectjob()
	{
		return expectjob;
	}

	public void setExpectjob(String expectjob)
	{
		this.expectjob = expectjob;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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
				+getResumetitle()
				+"</strong></font></div><br/>";
		return result;
	}

	/**
	 * 生成分享信息内容部分
	 */
	@Override
	public String generateShareText()
	{
		return getResumetitle();
	}
	
	/**
	 * 返回详情HTML子标题
	 * @return
	 */
	@Override
	public String getHtmlSubTitle()
	{
		String result = "<div align=\"center\"><font color=\"#666666\" size=\"2pt\">"+getPublishtime()+"&nbsp;&nbsp; "+getName()+"</font></div><div style=\"height:0;border-bottom:1px solid #f00\"></div>";
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
		infoService.getResumeDetail(getResumeid(), new IBaseReceiver()
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
				ResumeDetailVO mDetailVO = (ResumeDetailVO)JsonParser.parseJsonToEntity(content, respVO);
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
