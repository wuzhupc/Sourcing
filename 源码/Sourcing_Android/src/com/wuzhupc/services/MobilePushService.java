package com.wuzhupc.services;

import com.wuzhupc.Sourcing.R;
import com.wuzhupc.utils.json.JsonCreater;

import android.content.Context;

/**
 * 推送信息获取service
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-29 下午10:13:43
 * @note 注意 用户注册登录信息要从内容读取
 */
public class MobilePushService extends BaseJsonService
{
	public MobilePushService(Context c)
	{
		super(c);
	}
	
	/**
	 * 3.20.	获取用户推送信息
	 * @param userid
	 * @param lastchecktime
	 * @param iReceiver
	 */
	public void getPushInfo(long userid,String lastchecktime, IBaseReceiver iReceiver)
	{
		JsonCreater creater=JsonCreater.startJson();
		creater.setParam("devid",getDevID());
		if(userid>0l)
			creater.setParam("userid", userid);
		creater.setParam("lastchecktime", lastchecktime);
		mCommandName = mContext.getString(R.string.cmd_json_get_push_info);
		String json=creater.createJson(null, mCommandName);
		getData(json,iReceiver);
	}
}
