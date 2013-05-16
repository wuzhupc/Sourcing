package com.wuzhupc.services;

import com.wuzhupc.Sourcing.R;
import com.wuzhupc.utils.JavaLangUtil;
import com.wuzhupc.utils.json.JsonCreater;

import android.content.Context;

public class ClientJsonService extends BaseJsonService
{

	public ClientJsonService(Context c)
	{
		super(c);
	}

	/**
	 * 检验客户端版本更新信息
	 * @param curClientVer
	 * @param iReceiver
	 */
	public void checkClientUpdate(Long memberid,String curClientVer, IBaseReceiver iReceiver) 
	{
		JsonCreater creater=JsonCreater.startJson();
		creater.setParam("devid",getDevID());
		if (memberid!=null&& memberid != 0 ) 
		{
			creater.setParam("memberid", JavaLangUtil.LongToStr(memberid));
		}
		creater.setParam("clientversion", curClientVer);
		mCommandName = mContext.getString(R.string.cmd_json_check_client_ver);
		String json=creater.createJson(null, mCommandName);
		
		getData(json,iReceiver);
	}

}
