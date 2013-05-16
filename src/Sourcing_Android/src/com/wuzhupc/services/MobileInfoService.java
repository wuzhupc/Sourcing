package com.wuzhupc.services;

import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.vo.UserVO;
import com.wuzhupc.config.Constants;
import com.wuzhupc.utils.JavaLangUtil;
import com.wuzhupc.utils.StringUtil;
import com.wuzhupc.utils.json.JsonCreater;

import android.content.Context;

/**
 * info��Ϣ��ȡservice
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-28 ����10:13:43
 */
public class MobileInfoService extends BaseJsonService
{
	public MobileInfoService(Context c)
	{
		super(c);
	}
	
	/**
	 * ��ȡ��Ѷ�б�
	 * @param newstype �������0��������Ѷ��1����ҵ���ţ�2�����ߣ�3��֪ͨ��4��ר������
	 * @param bottomnewsid �����б����޵�����ID��Ĭ��0�������0ʱ����ȡ������ID��pagesize����¼��
	 * @note ��ȡ�ļ���mobileinfo_getNewsList_response_data_[].json
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
	 * 3.3.	��ȡ��Ѷ��ϸ��Ϣ
	 * @param newstype �������0��������Ѷ��1����ҵ���ţ�2�����ߣ�3��֪ͨ��4��ר������
	 * @param newsid ����ID
	 * @note ��ȡ�ļ���mobileinfo_getNewsDetail_response_data_[newstype]_[newsid].json
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
	 * 3.4.	��ȡ��Ѷ������Ϣ
	 * @param newstype ������� 1����ҵ���ţ�2�����ߣ�3��֪ͨ��4��ר������
	 * @param newsid ����ID
	 * @param bottomcommentid ���������б����޵�����ID��Ĭ��0�������0ʱ����ȡ����������ID��pagesize����¼��
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
	 * 3.5.	������Ѷ������Ϣ
	 * @param newstype ������� 1����ҵ���ţ�2�����ߣ�3��֪ͨ��4��ר������
	 * @param newsid ����ID
	 * @param conetnt ��������
	 * @return �����Աδ��¼������false
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
	 * 3.6.	��ȡְλ�б�
	 * @param searchkey �����ؼ��֣�Ĭ��Ϊ�գ��ǿ�ʱ��Ҫ���ݹؼ��ַ��ؽ�������
	 * @param bottomid ְλ�б����޵�����ID��Ĭ��0�������0ʱ����ȡ������ID��pagesize����¼��
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
	 * 3.7.	��ȡ�����б�
	 * @param searchkey �����ؼ��֣�Ĭ��Ϊ�գ��ǿ�ʱ��Ҫ���ݹؼ��ַ��ؽ�������
	 * @param bottomid �����б����޵ļ�¼ID��Ĭ��0�������0ʱ����ȡ�˼�¼ID��pagesize����¼��
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
	 * 3.8.	��ȡ��ѵ�����б�
	 * @param searchkey �����ؼ��֣�Ĭ��Ϊ�գ��ǿ�ʱ��Ҫ���ݹؼ��ַ��ؽ�������
	 * @param bottomid �б����޵ļ�¼ID��Ĭ��0�������0ʱ����ȡ�˼�¼ID��pagesize����¼��
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
	 * 3.9.	��ȡ��Ŀ�б�
	 * @param searchkey �����ؼ��֣�Ĭ��Ϊ�գ��ǿ�ʱ��Ҫ���ݹؼ��ַ��ؽ�������
	 * @param bottomid �б����޵ļ�¼ID��Ĭ��0�������0ʱ����ȡ�˼�¼ID��pagesize����¼��
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
	 * 3.10.	��ȡְλ����
	 * @param jobid ְλ���
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
	 * 3.11.	��ȡ��������
	 * @param resumeid �������
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
	 * 3.12.	��ȡ��ѵ��������
	 * @param trainid ��ѵ�������
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
	 * 3.13.	��ȡ��Ŀ����
	 * @param projectid ��Ŀ���
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
	 * 3.14.	��ȡ��ҵ�б�
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
	 * 3.15.	��ȡ��ҵ����
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
