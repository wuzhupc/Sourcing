package com.wuzhupc.services;

import com.wuzhupc.Sourcing.R;
import com.wuzhupc.utils.json.JsonCreater;

import android.content.Context;

/**
 * ������Ϣ��ȡservice
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-29 ����10:13:43
 * @note ע�� �û�ע���¼��ϢҪ�����ݶ�ȡ
 */
public class MobilePushService extends BaseJsonService
{
	public MobilePushService(Context c)
	{
		super(c);
	}
	
	/**
	 * 3.20.	��ȡ�û�������Ϣ
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
