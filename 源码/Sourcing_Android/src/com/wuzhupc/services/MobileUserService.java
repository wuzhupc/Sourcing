package com.wuzhupc.services;

import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.vo.UserVO;
import com.wuzhupc.config.Constants;
import com.wuzhupc.utils.json.JsonCreater;

import android.content.Context;

/**
 * 用户信息service
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-29 下午04:06:06
 *
 */
public class MobileUserService extends BaseJsonService
{
	public MobileUserService(Context c)
	{
		super(c);
	}
	
	/**
	 * 3.14.	用户登录
	 * @param username 登录名
	 * @param password 密码
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
	 * 3.15.	获取用户咨询信息
	 * @param bottomid 列表下限的记录ID，默认0，如果非0时，获取此记录ID后pagesize条记录数
	 * @param iReceiver
	 * @return 如果成员未登录，返回false
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
	 * 3.16.	发送用户咨询信息
	 * @param conetnt 咨询内容
	 * @param iReceiver
	 * @return 如果成员未登录，返回false
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
	 * 3.17.	获取用户审核结果
	 * @param bottomid 列表下限的记录ID，默认0，如果非0时，获取此记录ID后pagesize条记录数
	 * @param iReceiver
	 * @return 如果成员未登录，返回false
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
	 * 3.18.	获取用户申报进度
	 * @param bottomid 列表下限的记录ID，默认0，如果非0时，获取此记录ID后pagesize条记录数
	 * @param iReceiver
	 * @return 如果成员未登录，返回false
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
	 * 3.19.	获取用户通知提醒信息
	 * @param bottomid 列表下限的记录ID，默认0，如果非0时，获取此记录ID后pagesize条记录数
	 * @param iReceiver
	 * @return 如果成员未登录，返回false
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
