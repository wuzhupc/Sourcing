package com.wuzhupc.services;

import android.content.Context;

/**
 * info信息获取
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-28 下午10:13:43
 */
public class MobileInfoService extends BaseJsonService
{
	public MobileInfoService(Context c)
	{
		super(c);
	}
	
	/**
	 * 获取资讯列表
	 * @param newstype 新闻类别，0：最新资讯，1：行业新闻，2：政策，3：通知，4：专家文章
	 * @param bottomnewsid 新闻列表下限的新闻ID，默认0，如果非0时，获取此新闻ID后pagesize条记录数
	 */
	public void getNewsList(int newstype,long bottomnewsid)
	{
		
	}
	
	/**
	 * 3.3.	获取资讯详细信息
	 * @param newstype 新闻类别，0：最新资讯，1：行业新闻，2：政策，3：通知，4：专家文章
	 * @param newsid 新闻ID
	 */
	public void getNewsDetail(int newstype,long newsid)
	{
		
	}
	
	/**
	 * 3.4.	获取资讯评论信息
	 * @param newstype 新闻类别， 1：行业新闻，2：政策，3：通知，4：专家文章
	 * @param newsid 新闻ID
	 * @param bottomcommentid 新闻评论列表下限的新闻ID，默认0，如果非0时，获取此新闻评论ID后pagesize条记录数
	 */
	public void getNewsComment(int newstype,long newsid,int bottomcommentid)
	{
		
	}
	
	/**
	 * 3.5.	发送资讯评论信息
	 * @param newstype 新闻类别， 1：行业新闻，2：政策，3：通知，4：专家文章
	 * @param newsid 新闻ID
	 * @param conetnt 评论内容
	 */
	public void sendNewsComment(int newstype,long newsid,String conetnt)
	{
		
	}
	
	/**
	 * 3.6.	获取职位列表
	 * @param searchkey 搜索关键字，默认为空，非空时需要根据关键字返回进行搜索
	 * @param bottomid 职位列表下限的新闻ID，默认0，如果非0时，获取此新闻ID后pagesize条记录数
	 */
	public void getJobList(String searchkey,long bottomid)
	{
		
	}
	
	/**
	 * 3.7.	获取简历列表
	 * @param searchkey 搜索关键字，默认为空，非空时需要根据关键字返回进行搜索
	 * @param bottomid 简历列表下限的记录ID，默认0，如果非0时，获取此记录ID后pagesize条记录数
	 */
	public void getResumeList(String searchkey,long bottomid)
	{
		
	}
	
	/**
	 * 3.8.	获取培训机构列表
	 * @param searchkey 搜索关键字，默认为空，非空时需要根据关键字返回进行搜索
	 * @param bottomid 列表下限的记录ID，默认0，如果非0时，获取此记录ID后pagesize条记录数
	 */
	public void getTrainList(String searchkey,long bottomid)
	{
		
	}
	
	/**
	 * 3.9.	获取项目列表
	 * @param searchkey 搜索关键字，默认为空，非空时需要根据关键字返回进行搜索
	 * @param bottomid 列表下限的记录ID，默认0，如果非0时，获取此记录ID后pagesize条记录数
	 */
	public void getProjectList(String searchkey,long bottomid)
	{
		
	}
	
	/**
	 * 3.10.	获取职位详情
	 * @param jobid 职位编号
	 */
	public void getJobDetail(long jobid)
	{
		
	}
	/**
	 * 3.11.	获取简历详情
	 * @param resumeid 简历编号
	 */
	public void getResumeDetail(long resumeid)
	{
		
	}
	/**
	 * 3.12.	获取培训机构详情
	 * @param trainid 培训机构编号
	 */
	public void getTrainDetail(long trainid)
	{
		
	}
	/**
	 * 3.13.	获取项目详情
	 * @param projectid 项目编号
	 */
	public void getProjectDetail(long projectid)
	{
		
	}
}
