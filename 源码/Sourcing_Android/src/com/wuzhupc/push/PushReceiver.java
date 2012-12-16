package com.wuzhupc.push;

import java.util.Date;
import java.util.List;

import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.WelcomeActivity;
import com.wuzhupc.Sourcing.vo.PushVO;
import com.wuzhupc.Sourcing.vo.ResponseVO;
import com.wuzhupc.Sourcing.vo.UserVO;
import com.wuzhupc.services.BaseJsonService.IBaseReceiver;
import com.wuzhupc.services.MobilePushService;
import com.wuzhupc.utils.ActivityUtil;
import com.wuzhupc.utils.AlarmUtil;
import com.wuzhupc.utils.FileUtil;
import com.wuzhupc.utils.NotifyUtil;
import com.wuzhupc.utils.SettingUtil;
import com.wuzhupc.utils.TimeUtil;
import com.wuzhupc.utils.json.JsonParser;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class PushReceiver extends BroadcastReceiver
{

	private static final String TAG = PushReceiver.class.getSimpleName();
	/**
	 * ����
	 */
	private static final Boolean DEBUG = false;

	public static final String CSTR_ACTION_PUSH_RECEIVER = "com.wuzhu.push.pushreceiver";

	private Context mContext;

	@Override
	public void onReceive(Context context, Intent intent)
	{
		this.mContext = context;
		// �жϴ洢���Ƿ����
		if (!FileUtil.hasSDCard())
		{
			AlarmUtil.getPushMsgAlarm(mContext).pendingBroadcastTask(
					new Intent(CSTR_ACTION_PUSH_RECEIVER), -1);

			Log.i(TAG, "������Ϣ���--NOT SD��CARD!");
			return;
		}

		if (!ActivityUtil.isCurAppRunningForeground(mContext))
			actionPushMsg();
		else
		{
			Log.i(TAG, "������Ϣ���--CurAppRunningForeground!");
			AlarmUtil.getPushMsgAlarm(mContext).pendingBroadcastTask(
					new Intent(CSTR_ACTION_PUSH_RECEIVER), -1);
		}
	}

	/**
	 * ��ʱ����������Ϣ
	 */
	private void actionPushMsg()
	{
		String lastCheckTimeSP = SettingUtil.getLastCheckPushMsgTime(mContext);
		// TODO ��ȡ��������������ʱ��
		String lastCheckTime = lastCheckTimeSP;

		// Log.i(TAG, "db time = "+DateFormat.format("yyyy-MM-dd hh:mm:ss",
		// lastCheckTimeDB)+
		// "\n sp time = "+DateFormat.format("yyyy-MM-dd hh:mm:ss",
		// lastCheckTimeSP)+
		// "\n last time = "+DateFormat.format("yyyy-MM-dd hh:mm:ss",
		// lastCheckTime));

		// lastCheckTime = System.currentTimeMillis() - 1000*3600;
		if (DEBUG)
			lastCheckTime = TimeUtil.dateToString(new Date(System
					.currentTimeMillis() - 1000 * 3600));// 60s*60m*1000ms
		UserVO userVO = UserVO.getLastLoginUserInfo();
		if (userVO == null)// �û�δ��¼���򲻻�ȡ������Ϣ
			return;
		MobilePushService service = new MobilePushService(mContext);
		service.getPushInfo(userVO.getUserid(), lastCheckTime,
				new IBaseReceiver()
				{
					@SuppressWarnings("unchecked")
					@Override
					public void receiveCompleted(boolean isSuc, String content)
					{
						int interval = getDefaultInterval();
						if (isSuc)
						{
							ResponseVO respVO = new ResponseVO();
							List<PushVO> pushVOs = (List<PushVO>) JsonParser
									.parseJsonToEntity(content, respVO);

							if (respVO.isSucess())
							{
								SettingUtil.setLastCheckPushMsgTime(mContext,
										TimeUtil.dateToString(new Date()));

								if (pushVOs != null && !pushVOs.isEmpty())
								{
									Intent intent = new Intent(mContext,
											WelcomeActivity.class);
									// intent.putParcelableArrayListExtra("push_msg_list",
									// (ArrayList<? extends Parcelable>)
									// pushVOs);
									int count = pushVOs.size();
									if (count == 1)
									{
										PushVO vo = pushVOs.get(0);
										NotifyUtil.notify(
												mContext,
												intent,
												null,
												mContext.getString(
														R.string.notify_single_msg_detail,
														vo.getPushtype(),
														vo.getTitle()));
									} else
									{
										NotifyUtil.notify(mContext, intent,
												null, mContext.getString(
														R.string.notify_detail,
														count));
									}
								}
							}
						}
						if (interval < AlarmUtil.CINT_DEFAULT_REFRESH_DELAY)
							interval = getDefaultInterval();
						AlarmUtil.getPushMsgAlarm(mContext)
								.pendingBroadcastTask(
										new Intent(CSTR_ACTION_PUSH_RECEIVER),
										DEBUG ? -1 : interval);
					}
				});
	}

	/**
	 * ���ɼ�������
	 * 
	 * @return
	 */
	private int getDefaultInterval()
	{
		return (int) (Math.random() * AlarmUtil.CINT_DEFAULT_REFRESH_DELAY)
				+ AlarmUtil.CINT_DEFAULT_REFRESH_DELAY;
	}
}
