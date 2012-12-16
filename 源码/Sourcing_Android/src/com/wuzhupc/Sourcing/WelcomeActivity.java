package com.wuzhupc.Sourcing;

import java.util.Map;

import com.wuzhupc.Sourcing.dialog.BaseDialog;
import com.wuzhupc.Sourcing.vo.ClientVerVO;
import com.wuzhupc.Sourcing.vo.ResponseVO;
import com.wuzhupc.Sourcing.vo.UserVO;
import com.wuzhupc.services.BaseJsonService.IBaseReceiver;
import com.wuzhupc.services.ClientJsonService;
import com.wuzhupc.services.MobileUserService;
import com.wuzhupc.utils.SettingUtil;
import com.wuzhupc.utils.json.JsonParser;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;
/**
 * �������� ��ʾ���������ӷ������˼�����Ŀ��Ϣ
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-20 ����9:05:36
 * @note ����(״̬Cint_ShowLogo_State)
 * 			.->��ʾlogo(����Cint_ShowLogoComplete_MessageID) 2�� ���� +1
 *          .->��ȡ�汾��Ϣ->�и���,������ʾ�ж��Ƿ���Ҫ����(����������,��ֱ�Ӵ����������,���˳�����.������������汾,���ȡ��������ֱ���˳�) ����+1
 *          	.->�ж��û���¼���(����Cint_UserLogin_MessageID) ����+1
 */
public class WelcomeActivity extends BaseActivity
{
	protected static final String Tag=WelcomeActivity.class.getSimpleName();
	
	/**
	 * ״ֵ̬-��ǰ��ʾlogo
	 */
	private static final int Cint_ShowLogo_State=1;
	/**
	 * ״ֵ̬-��ǰ��ʾ����״̬
	 */
	private static final int Cint_ShowClientUpdate_State=2;
	/**
	 * �ܽ�����ʾ���������
	 */
	private static final int Cint_CanShowHomeMessageNumber = 3;
	/**
	 * Logo��ʾ������ϢID
	 */
	private static final int Cint_CompleteShowLogo_MessageID=101;
	
	/**
	 * ��ʼ������¿ͻ��˰汾��ϢID
	 */
	private static final int Cint_StartCheckClientVer_MessageID = 103;
	
	/**
	 * ��ɼ�����¿ͻ��˰汾��ϢID
	 */
	private static final int Cint_CompleteCheckClientVer_MessageID = 104;

	/**
	 * ��ʼ�û���¼��ϢID
	 */
	private static final int Cint_StartUserLogin_MessageID=105;
	
	/**
	 * ����û���¼��ϢID
	 */
	private static final int Cint_CompleteUserLogin_MessageID=106;
	
	
	/**
	 * ��ǰ״̬
	 */
	private int mNowState;
	
	/**
	 * ��Ϣ��
	 */
	private int mMessageNumber;
	
	/**
	 * �ͻ��˰汾��Ϣ
	 */
	private ClientVerVO mClientVerVO;
	
	/**
	 * ��ʼ��View
	 */
	@Override
	protected void initView()
	{
		//��ʼ��View
		setContentView(R.layout.activity_welcome);
		((TextView)findViewById(R.id.welcome_ver_tv)).setText(String.format(getResources().getString(R.string.welcome_ver), SettingUtil.getClientVersion(this)));		
	}

	@Override
	protected void initDataContent()
	{
		setCatureBackKey(true);
		mNowState = Cint_ShowLogo_State;
		mMessageNumber = 0;
		sendMsg(Cint_CompleteShowLogo_MessageID, 2000);
		sendMsg(Cint_StartCheckClientVer_MessageID, 200);
		sendMsg(Cint_StartUserLogin_MessageID, 500);
		if(!checkCacheFolder())
			return;
	}
	
	/**
	 * ������Ϣ
	 * @param what
	 * @param delay ��ʱ��ã���λms�����Ϊ0��������������Ϣ
	 */
	private void sendMsg(int what,int delay)
	{
		Log.v(Tag, "SendMsg:what="+what);
		Message message = new Message();
		message.what=what;
		if(delay>0)
			mHandler.sendMessageDelayed(message, delay);
		else
			mHandler.sendMessage(message);
	}


	private Handler mHandler = new Handler()
	{
		@Override
		public void handleMessage(Message msg) 
		{
			switch (msg.what)
			{
			case Cint_CompleteShowLogo_MessageID:
				mMessageNumber++;
				break;
			case Cint_StartCheckClientVer_MessageID:
				startGetVersionInfo();
				break;
			case Cint_CompleteCheckClientVer_MessageID:
				//���»�����ʾlogo״̬
				mNowState=Cint_ShowLogo_State;
				mMessageNumber++;
				break;
			case Cint_StartUserLogin_MessageID:
				startUserLogin();
				break;
			case Cint_CompleteUserLogin_MessageID:
				mMessageNumber++;
				break;
			}
			//ֻ����ʾlogo״̬�ҵ�����ֵ���˲����л���������
			if(mNowState==Cint_ShowLogo_State&&mMessageNumber>=Cint_CanShowHomeMessageNumber)
			{
				startHome();
			}
		}
	};

	/**
	 * ������ȡ�汾��Ϣ
	 */
	private void startGetVersionInfo()
	{
		ClientJsonService clientService = new ClientJsonService(this);
		clientService.checkClientUpdate(0l,SettingUtil.getClientVersion(this), new IBaseReceiver() {
			
			@Override
			public void receiveCompleted(boolean isSuc, String content) {
				if (isSuc) 
				{
					ResponseVO respVO = new ResponseVO();
					Map<String, String> value=JsonParser.parseJsonToMap(content,respVO);
					if(value!=null&&!value.isEmpty())
					{
						mClientVerVO = new ClientVerVO();
						mClientVerVO.setClientver(SettingUtil.getClientVersion(WelcomeActivity.this));
						mClientVerVO.setLastver(value.get("lastver"));
						mClientVerVO.setLastverurl(value.get("lastverurl"));
						mClientVerVO.setFilesize(value.get("filesize"));
						mClientVerVO.setForceupdate(value.get("forceupdate"));
						mClientVerVO.setUpdatelog(value.get("updatelog"));
					}
					completeGetVersionInfo(respVO);
				}
				else
				{
					completeGetVersionInfo(new ResponseVO(ResponseVO.RESPONSE_CODE_FAIL,content));
				}
//				else 
//				{
//			        hitCloseApplication(getString(R.string.prompt_network_error),true);
//				}
			}
		});
	}
	private void completeGetVersionInfo(ResponseVO respVO)
	{
		if(!respVO.isSucess()||mClientVerVO==null)
		{
			sendMsg(Cint_CompleteCheckClientVer_MessageID, 0);
			return;
		}
		//�жϰ汾������Ϣ
		if(!mClientVerVO.hasUpdate())
		{
			sendMsg(Cint_CompleteCheckClientVer_MessageID, 0);
			return;
		}
		//��ʾ������Ϣ
		showUpdateDialog();
	}
	
	/**
	 * ��ʾ���¶Ի���
	 */
	private void showUpdateDialog()
	{
		mNowState = Cint_ShowClientUpdate_State;
		BaseDialog dialog = new BaseDialog(WelcomeActivity.this,BaseDialog.DIALOG_TYPE_TWOBTN);
		dialog.setTitle(String.format(this.getResources().getString(mClientVerVO.getForceupdate()?R.string.welcome_update_title_ex:R.string.welcome_update_title),mClientVerVO.getLastver()));
		dialog.setMessage(
				Html.fromHtml(
					String.format(this.getResources().getString(mClientVerVO.getForceupdate()?R.string.welcome_update_content_ex:R.string.welcome_update_content),mClientVerVO.getUpdatelog())));
		dialog.setBtnText(BaseDialog.BTN_TYPE_LEFT, R.string.welcome_update_btn);
		dialog.setOnDialogClickListener(new android.content.DialogInterface.OnClickListener() { 
            @Override 
            public void onClick(DialogInterface dialog, int which) 
            { 
                dialog.dismiss();
                runBrowser(mClientVerVO.getLastverurl());
                System.exit(0);
            } 
        }, BaseDialog.BTN_TYPE_LEFT);
		dialog.setBtnText(BaseDialog.BTN_TYPE_RIGHT,mClientVerVO.getForceupdate()?R.string.dl_btn_quit:R.string.dl_btn_cancel);
		dialog.setOnDialogClickListener(new android.content.DialogInterface.OnClickListener() { 
            @Override 
            public void onClick(DialogInterface dialog, int which) 
            { 
                dialog.dismiss(); 
                if(mClientVerVO.getForceupdate())
                {
                   System.exit(0);
                   return;
                }
                sendMsg(Cint_CompleteCheckClientVer_MessageID, 0);
            } 
        }, BaseDialog.BTN_TYPE_RIGHT);
		dialog.show();
	}
	
	/**
	 * ����û���¼
	 */
	private void startUserLogin()
	{
		//���֮ǰ�Ѿ���¼��
		UserVO vo = getApplicationSet().getUserVO();
		if(vo!=null)
		{
			completeUserLogin();
			return;
		}
		//��ȡ֮ǰ�ĵ�¼��Ϣ
		vo = UserVO.getLastLoginUserInfo();
		if(vo==null)
		{
			completeUserLogin();
			return;
		}
		//Զ���ж�
		MobileUserService service =new MobileUserService(WelcomeActivity.this);
		service.userLogin(vo.getUseraccount(), vo.getPassword(), new IBaseReceiver()
		{
			@Override
			public void receiveCompleted(boolean isSuc, String content)
			{
				if(!isSuc)
				{
					completeUserLogin();
					return;
				}
				ResponseVO responseVO = new ResponseVO();
				UserVO userVO = (UserVO) JsonParser.parseJsonToEntity(content, responseVO);
				if(!responseVO.isSucess()||userVO==null)
				{
					completeUserLogin();
					return;
				}
				getApplicationSet().setUserVO(userVO,true);
				completeUserLogin();
			}
		});	
	}
	
	private void completeUserLogin()
	{
        sendMsg(Cint_CompleteUserLogin_MessageID, 0);
	}
	
	/**
	 * ����������
	 */
	private void startHome()
	{
		//��������������Ϣ
		startPushService();
//		
//		//����������
		Intent intent=new Intent(this, HomeActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//		//intent.putExtra(HomeActivity.CSTR_PARMS_STARTADNEWSURL, mStartLogoNewUrl);
		runIntent(true, intent);
	}
}
