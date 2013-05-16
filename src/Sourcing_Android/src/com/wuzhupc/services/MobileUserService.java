package com.wuzhupc.services;

import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.vo.UserVO;
import com.wuzhupc.config.Constants;
import com.wuzhupc.utils.json.JsonCreater;

import android.content.Context;

/**
 * 
 * @author wuzhu
 * @date 2013-5-16 下午5:08:24
 * @version $id$
 */
public class MobileUserService extends BaseJsonService
{
	public MobileUserService(Context c)
	{
		super(c);
	}
	/**
	 * 
	 * @param username
	 * @param password
	 * @param iReceiver
	 */
	public void userLogin(String username,String password, IBaseReceiver iReceiver)
	{
		userLogin(username, password, iReceiver,false,null);
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @param iReceiver
	 * @param showprogress
	 * @param progressshowcontent
	 */
	public void userLogin(String username,String password, IBaseReceiver iReceiver,Boolean showprogress,String progressshowcontent)
	{
		JsonCreater creater=JsonCreater.startJson();
		creater.setParam("devid",getDevID());
		creater.setParam("username", username);
		creater.setParam("password", password);
		mSuffixStr = username;
		mCommandName = mContext.getString(R.string.cmd_json_user_login);
		String json=creater.createJson(null, mCommandName);
		getData(json,iReceiver,showprogress,progressshowcontent);
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @param newpassword
	 * @param iReceiver
	 * @param showprogress
	 * @param progressshowcontent
	 */
	public void changePwd(String username,String password,String newpassword,IBaseReceiver iReceiver,Boolean showprogress,String progressshowcontent)
	{
		JsonCreater creater=JsonCreater.startJson();
		creater.setParam("devid",getDevID());
		creater.setParam("username", username);
		creater.setParam("password", password);
		creater.setParam("newpassword", newpassword);
		mSuffixStr = username;
		mCommandName = mContext.getString(R.string.cmd_json_user_change_pwd);
		String json=creater.createJson(null, mCommandName);
		getData(json,iReceiver,showprogress,progressshowcontent);
	}
	
	/**
	 * 
	 * @param bottomid
	 * @param iReceiver
	 * @param showprogress
	 * @param progressshowcontent
	 * @return
	 */
	public boolean getUserConsultInfo(long bottomid, IBaseReceiver iReceiver,Boolean showprogress,String progressshowcontent)
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
		getData(json,iReceiver,showprogress,progressshowcontent);
		return true;
	}
	
	/**
	 * 
	 * @param content
	 * @param iReceiver
	 * @param showprogress
	 * @param progressshowcontent
	 * @return
	 */
	public boolean sendUserConsult(String content, IBaseReceiver iReceiver,Boolean showprogress,String progressshowcontent)
	{
		UserVO userVO = getUserVO();
		if(userVO==null)
			return false;
		JsonCreater creater=JsonCreater.startJson();
		creater.setParam("devid",getDevID());
		creater.setParam("userid", userVO.getUserid());
		creater.setParam("username", userVO.getUsername());
		creater.setParam("content", content);
		mCommandName = mContext.getString(R.string.cmd_json_send_user_consult);
		String json=creater.createJson(null, mCommandName);
		getData(json,iReceiver,showprogress,progressshowcontent);
		return true;
	}
	
	/**
	 * 
	 * @param bottomid
	 * @param iReceiver
	 * @param showprogress
	 * @param progressshowcontent
	 * @return
	 */
	public boolean getUserAuditInfo(long bottomid, IBaseReceiver iReceiver,Boolean showprogress,String progressshowcontent)
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
		getData(json,iReceiver,showprogress,progressshowcontent);
		return true;
	}
	
	/**
	 * 
	 * @param bottomid
	 * @param iReceiver
	 * @param showprogress
	 * @param progressshowcontent
	 * @return
	 */
	public boolean getUserDeclareInfo(long bottomid, IBaseReceiver iReceiver,Boolean showprogress,String progressshowcontent)
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
		getData(json,iReceiver,showprogress,progressshowcontent);
		return true;
	}
	
	/**
	 * 
	 * @param bottomid
	 * @param iReceiver
	 * @param showprogress
	 * @param progressshowcontent
	 * @return
	 */
	public boolean getUserNotifierInfo(long bottomid, IBaseReceiver iReceiver,Boolean showprogress,String progressshowcontent)
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
		getData(json,iReceiver,showprogress,progressshowcontent);
		return true;
	}
}
