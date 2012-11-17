package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;

/**
 * 
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-17 下午8:58:12
 */
public class PushVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1104767207235439018L;
	/**
	 * 推送信息类型,0 普通资讯信息 1 通知提醒 2 审核结果 3 申报进度 4,PUSH_TYPE_
	 */
	private int pushtype;
	/**
	 * 推送信息标题
	 */
	private String title;
	/**
	 * 推送信息详细内容ID,用于手机端根据推送信息类型进一步获取相应信息的详情
	 */
	private long id;
	public int getPushtype()
	{
		return pushtype;
	}
	public void setPushtype(int pushtype)
	{
		this.pushtype = pushtype;
	}
	public void setPushtype(String pushtype)
	{
		this.pushtype = JavaLangUtil.StrToInteger(pushtype, PUSH_TYPE_NORMAL);
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public void setId(String id)
	{
		this.id = JavaLangUtil.StrToLong(id,-1l);
	}
	
}
