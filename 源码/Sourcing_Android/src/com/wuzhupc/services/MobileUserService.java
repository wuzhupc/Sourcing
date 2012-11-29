package com.wuzhupc.services;

import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.vo.UserVO;
import com.wuzhupc.config.Constants;
import com.wuzhupc.utils.json.JsonCreater;

import android.content.Context;

/**
 * �û���Ϣservice
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-29 ����04:06:06
 *
 */
public class MobileUserService extends BaseJsonService
{
	public MobileUserService(Context c)
	{
		super(c);
	}
	
	/**
	 * 3.14.	�û���¼
	 * @param username ��¼��
	 * @param password ����
	 */
	public void userLogin(String username,String password, IBaseReceiver iReceiver)
	{
		JsonCreater creater=JsonCreater.startJson();
		creater.setParam("devid",getDevID());
		creater.setParam("username", username);
		creater.setParam("password", password);
		mCommandName = mContext.getString(R.string.cmd_json_user_login);
		String json=creater.createJson(null, mCommandName);
		getData(json,iReceiver);
	}
	
	/**
	 * 3.15.	��ȡ�û���ѯ��Ϣ
	 * @param bottomid �б����޵ļ�¼ID��Ĭ��0�������0ʱ����ȡ�˼�¼ID��pagesize����¼��
	 * @param iReceiver
	 * @return �����Աδ��¼������false
	 */
	public boolean getUserConsultInfo(long bottomid, IBaseReceiver iReceiver)
	{
		UserVO userVO = getUserVO();
		if(userVO==null)
			return false;
		JsonCreater creater=JsonCreater.startJson();
		creater.setParam("devid",getDevID());
		creater.setParam("userid", userVO.getUserid());
		creater.setParam("pagesize", Constants.CINT_PAGE_SIZE);
		creater.setParam("bottomid", bottomid);
		mCommandName = mContext.getString(R.string.cmd_json_get_user_consult_info);
		String json=creater.createJson(null, mCommandName);
		getData(json,iReceiver);
		return true;
	}
	
	/**
	 * 3.16.	�����û���ѯ��Ϣ
	 * @param conetnt ��ѯ����
	 * @param iReceiver
	 * @return �����Աδ��¼������false
	 */
	public boolean sendUserConsult(String conetnt, IBaseReceiver iReceiver)
	{
		UserVO userVO = getUserVO();
		if(userVO==null)
			return false;
		JsonCreater creater=JsonCreater.startJson();
		creater.setParam("devid",getDevID());
		creater.setParam("userid", userVO.getUserid());
		creater.setParam("username", userVO.getUsername());
		creater.setParam("conetnt", conetnt);
		mCommandName = mContext.getString(R.string.cmd_json_send_user_consult);
		String json=creater.createJson(null, mCommandName);
		getData(json,iReceiver);
		return true;
	}
	
	/**
	 * 3.17.	��ȡ�û���˽��
	 * @param bottomid �б����޵ļ�¼ID��Ĭ��0�������0ʱ����ȡ�˼�¼ID��pagesize����¼��
	 * @param iReceiver
	 * @return �����Աδ��¼������false
	 */
	public boolean getUserAuditInfo(long bottomid, IBaseReceiver iReceiver)
	{
		UserVO userVO = getUserVO();
		if(userVO==null)
			return false;
		JsonCreater creater=JsonCreater.startJson();
		creater.setParam("devid",getDevID());
		creater.setParam("userid", userVO.getUserid());
		creater.setParam("pagesize", Constants.CINT_PAGE_SIZE);
		creater.setParam("bottomid", bottomid);
		mCommandName = mContext.getString(R.string.cmd_json_get_user_audit_info);
		String json=creater.createJson(null, mCommandName);
		getData(json,iReceiver);
		return true;
	}
	
	/**
	 * 3.18.	��ȡ�û��걨����
	 * @param bottomid �б����޵ļ�¼ID��Ĭ��0�������0ʱ����ȡ�˼�¼ID��pagesize����¼��
	 * @param iReceiver
	 * @return �����Աδ��¼������false
	 */
	public boolean getUserDeclareInfo(long bottomid, IBaseReceiver iReceiver)
	{
		UserVO userVO = getUserVO();
		if(userVO==null)
			return false;
		JsonCreater creater=JsonCreater.startJson();
		creater.setParam("devid",getDevID());
		creater.setParam("userid", userVO.getUserid());
		creater.setParam("pagesize", Constants.CINT_PAGE_SIZE);
		creater.setParam("bottomid", bottomid);
		mCommandName = mContext.getString(R.string.cmd_json_get_user_declare_info);
		String json=creater.createJson(null, mCommandName);
		getData(json,iReceiver);
		return true;
	}
	
	/**
	 * 3.19.	��ȡ�û�֪ͨ������Ϣ
	 * @param bottomid �б����޵ļ�¼ID��Ĭ��0�������0ʱ����ȡ�˼�¼ID��pagesize����¼��
	 * @param iReceiver
	 * @return �����Աδ��¼������false
	 */
	public boolean getUserNotifierInfo(long bottomid, IBaseReceiver iReceiver)
	{
		UserVO userVO = getUserVO();
		if(userVO==null)
			return false;
		JsonCreater creater=JsonCreater.startJson();
		creater.setParam("devid",getDevID());
		creater.setParam("userid", userVO.getUserid());
		creater.setParam("pagesize", Constants.CINT_PAGE_SIZE);
		creater.setParam("bottomid", bottomid);
		mCommandName = mContext.getString(R.string.cmd_json_get_user_notifier_info);
		String json=creater.createJson(null, mCommandName);
		getData(json,iReceiver);
		return true;
	}
}
