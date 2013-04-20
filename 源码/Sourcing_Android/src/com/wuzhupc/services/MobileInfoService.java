package com.wuzhupc.services;

import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.vo.UserVO;
import com.wuzhupc.config.Constants;
import com.wuzhupc.utils.JavaLangUtil;
import com.wuzhupc.utils.StringUtil;
import com.wuzhupc.utils.json.JsonCreater;

import android.content.Context;

/**
 * info信息获取service
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
	 * @note 读取文件名mobileinfo_getNewsList_response_data_[].json
	 */
	public void getNewsList(int newstype,long bottomnewsid, IBaseReceiver iReceiver)
	{
		JsonCreater creater=JsonCreater.startJson();
		creater.setParam("devid",getDevID());
		creater.setParam("newstype", newstype);
		creater.setParam("pagesize", Constants.CINT_PAGE_SIZE);
		creater.setParam("bottomnewsid", bottomnewsid);
		mCommandName = mContext.getString(R.string.cmd_json_get_news_list);
		mSuffixStr=String.valueOf(newstype);
		String json=creater.createJson(null, mCommandName);
		getData(json,iReceiver);
	}
	
	/**
	 * 3.3.	获取资讯详细信息
	 * @param newstype 新闻类别，0：最新资讯，1：行业新闻，2：政策，3：通知，4：专家文章
	 * @param newsid 新闻ID
	 * @note 读取文件名mobileinfo_getNewsDetail_response_data_[newstype]_[newsid].json
	 */
	public void getNewsDetail(int newstype,long newsid, IBaseReceiver iReceiver)
	{
		JsonCreater creater=JsonCreater.startJson();
		creater.setParam("devid",getDevID());
		creater.setParam("newstype", newstype);
		creater.setParam("newsid", newsid);
		mCommandName = mContext.getString(R.string.cmd_json_get_news_detail);
		mSuffixStr=String.valueOf(newstype)+"_"+JavaLangUtil.LongToStr(newsid);
		String json=creater.createJson(null, mCommandName);
		getData(json,iReceiver);
	}
	
	/**
	 * 3.4.	获取资讯评论信息
	 * @param newstype 新闻类别， 1：行业新闻，2：政策，3：通知，4：专家文章
	 * @param newsid 新闻ID
	 * @param bottomcommentid 新闻评论列表下限的新闻ID，默认0，如果非0时，获取此新闻评论ID后pagesize条记录数
	 */
	public void getNewsComment(int newstype,long newsid,int bottomcommentid, IBaseReceiver iReceiver)
	{
		JsonCreater creater=JsonCreater.startJson();
		creater.setParam("devid",getDevID());
		creater.setParam("newstype", newstype);
		creater.setParam("newsid", newsid);
		creater.setParam("pagesize", Constants.CINT_PAGE_SIZE);
		creater.setParam("bottomcommentid", bottomcommentid);
		mCommandName = mContext.getString(R.string.cmd_json_get_news_comment);
		String json=creater.createJson(null, mCommandName);
		getData(json,iReceiver);
	}
	
	/**
	 * 3.5.	发送资讯评论信息
	 * @param newstype 新闻类别， 1：行业新闻，2：政策，3：通知，4：专家文章
	 * @param newsid 新闻ID
	 * @param conetnt 评论内容
	 * @return 如果成员未登录，返回false
	 */
	public boolean sendNewsComment(int newstype,long newsid,String conetnt, IBaseReceiver iReceiver)
	{
		UserVO userVO = getUserVO();
		if(userVO==null)
			return false;
		JsonCreater creater=JsonCreater.startJson();
		creater.setParam("devid",getDevID());
		creater.setParam("newstype", newstype);
		creater.setParam("newsid", newsid);
		creater.setParam("userid", userVO.getUserid());
		creater.setParam("username", userVO.getUsername());
		creater.setParam("conetnt", conetnt);
		mCommandName = mContext.getString(R.string.cmd_json_send_news_commnet);
		String json=creater.createJson(null, mCommandName);
		getData(json,iReceiver);
		return true;
	}
	
	/**
	 * 3.6.	获取职位列表
	 * @param searchkey 搜索关键字，默认为空，非空时需要根据关键字返回进行搜索
	 * @param bottomid 职位列表下限的新闻ID，默认0，如果非0时，获取此新闻ID后pagesize条记录数
	 */
	public void getJobList(String searchkey,long bottomid, IBaseReceiver iReceiver)
	{
		JsonCreater creater=JsonCreater.startJson();
		creater.setParam("devid",getDevID());
		if(!StringUtil.isEmpty(searchkey))
			creater.setParam("searchkey", searchkey);
		creater.setParam("pagesize", Constants.CINT_PAGE_SIZE);
		creater.setParam("bottomid", bottomid);
		mCommandName = mContext.getString(R.string.cmd_json_get_job_list);
		String json=creater.createJson(null, mCommandName);
		getData(json,iReceiver);
	}
	
	/**
	 * 3.7.	获取简历列表
	 * @param searchkey 搜索关键字，默认为空，非空时需要根据关键字返回进行搜索
	 * @param bottomid 简历列表下限的记录ID，默认0，如果非0时，获取此记录ID后pagesize条记录数
	 */
	public void getResumeList(String searchkey,long bottomid, IBaseReceiver iReceiver)
	{
		JsonCreater creater=JsonCreater.startJson();
		creater.setParam("devid",getDevID());
		if(!StringUtil.isEmpty(searchkey))
			creater.setParam("searchkey", searchkey);
		creater.setParam("pagesize", Constants.CINT_PAGE_SIZE);
		creater.setParam("bottomid", bottomid);
		mCommandName = mContext.getString(R.string.cmd_json_get_resume_list);
		String json=creater.createJson(null, mCommandName);
		getData(json,iReceiver);
	}
	
	/**
	 * 3.8.	获取培训机构列表
	 * @param searchkey 搜索关键字，默认为空，非空时需要根据关键字返回进行搜索
	 * @param bottomid 列表下限的记录ID，默认0，如果非0时，获取此记录ID后pagesize条记录数
	 */
	public void getTrainList(String searchkey,long bottomid, IBaseReceiver iReceiver)
	{
		JsonCreater creater=JsonCreater.startJson();
		creater.setParam("devid",getDevID());
		if(!StringUtil.isEmpty(searchkey))
			creater.setParam("searchkey", searchkey);
		creater.setParam("pagesize", Constants.CINT_PAGE_SIZE);
		creater.setParam("bottomid", bottomid);
		mCommandName = mContext.getString(R.string.cmd_json_get_train_list);
		String json=creater.createJson(null, mCommandName);
		getData(json,iReceiver);
	}
	
	/**
	 * 3.9.	获取项目列表
	 * @param searchkey 搜索关键字，默认为空，非空时需要根据关键字返回进行搜索
	 * @param bottomid 列表下限的记录ID，默认0，如果非0时，获取此记录ID后pagesize条记录数
	 */
	public void getProjectList(String searchkey,long bottomid, IBaseReceiver iReceiver)
	{
		JsonCreater creater=JsonCreater.startJson();
		creater.setParam("devid",getDevID());
		if(!StringUtil.isEmpty(searchkey))
			creater.setParam("searchkey", searchkey);
		creater.setParam("pagesize", Constants.CINT_PAGE_SIZE);
		creater.setParam("bottomid", bottomid);
		mCommandName = mContext.getString(R.string.cmd_json_get_project_list);
		String json=creater.createJson(null, mCommandName);
		getData(json,iReceiver);
	}
	
	/**
	 * 3.10.	获取职位详情
	 * @param jobid 职位编号
	 */
	public void getJobDetail(long jobid, IBaseReceiver iReceiver)
	{
		JsonCreater creater=JsonCreater.startJson();
		creater.setParam("devid",getDevID());
		creater.setParam("jobid", jobid);
		mCommandName = mContext.getString(R.string.cmd_json_get_job_detail);
		String json=creater.createJson(null, mCommandName);
		getData(json,iReceiver);
	}
	/**
	 * 3.11.	获取简历详情
	 * @param resumeid 简历编号
	 */
	public void getResumeDetail(long resumeid, IBaseReceiver iReceiver)
	{
		JsonCreater creater=JsonCreater.startJson();
		creater.setParam("devid",getDevID());
		creater.setParam("resumeid", resumeid);
		mCommandName = mContext.getString(R.string.cmd_json_get_resume_detail);
		String json=creater.createJson(null, mCommandName);
		getData(json,iReceiver);
	}
	/**
	 * 3.12.	获取培训机构详情
	 * @param trainid 培训机构编号
	 */
	public void getTrainDetail(long trainid, IBaseReceiver iReceiver)
	{
		JsonCreater creater=JsonCreater.startJson();
		creater.setParam("devid",getDevID());
		creater.setParam("trainid", trainid);
		mCommandName = mContext.getString(R.string.cmd_json_get_train_detail);
		String json=creater.createJson(null, mCommandName);
		getData(json,iReceiver);
	}
	/**
	 * 3.13.	获取项目详情
	 * @param projectid 项目编号
	 */
	public void getProjectDetail(long projectid, IBaseReceiver iReceiver)
	{
		JsonCreater creater=JsonCreater.startJson();
		creater.setParam("devid",getDevID());
		creater.setParam("projectid", projectid);
		mCommandName = mContext.getString(R.string.cmd_json_get_project_detail);
		String json=creater.createJson(null, mCommandName);
		getData(json,iReceiver);
	}
	/**
	 * 3.14.	获取企业列表
	 * @param searchkey
	 * @param bottomid
	 * @param iReceiver
	 */
	public void getCompanyList(String searchkey,long bottomid, IBaseReceiver iReceiver)
	{
		JsonCreater creater=JsonCreater.startJson();
		creater.setParam("devid",getDevID());
		if(!StringUtil.isEmpty(searchkey))
			creater.setParam("searchkey", searchkey);
		creater.setParam("pagesize", Constants.CINT_PAGE_SIZE);
		creater.setParam("bottomid", bottomid);
		mCommandName = mContext.getString(R.string.cmd_json_get_company_list);
		String json=creater.createJson(null, mCommandName);
		getData(json,iReceiver);
	}

	/**
	 * 3.15.	获取企业详情
	 * @param companyid
	 * @param iReceiver
	 */
	public void getCompanyDetail(long companyid, IBaseReceiver iReceiver)
	{
		JsonCreater creater=JsonCreater.startJson();
		creater.setParam("devid",getDevID());
		creater.setParam("companyid", companyid);
		mCommandName = mContext.getString(R.string.cmd_json_get_company_detail);
		String json=creater.createJson(null, mCommandName);
		getData(json,iReceiver);
	}
}
