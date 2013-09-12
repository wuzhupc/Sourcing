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
import com.wuzhupc.utils.ShortcutUtil;
import com.wuzhupc.utils.json.JsonParser;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;
/**
 * 启动界面 显示闪屏，并从服务器端加载栏目信息
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-20 下午9:05:36
 * @note 启动(状态Cint_ShowLogo_State)
 * 			.->显示logo(发送Cint_ShowLogoComplete_MessageID) 2秒 计数 +1
 *          .->获取版本信息->有更新,进行提示判断是否需要更新(如果点击更新,则直接打开浏览器下载,并退出程序.如果必须升级版本,点击取消更新则直接退出) 计数+1
 *          	.->判断用户登录情况(发送Cint_UserLogin_MessageID) 计数+1
 */
public class WelcomeActivity extends BaseActivity
{
	protected static final String Tag=WelcomeActivity.class.getSimpleName();
	
	/**
	 * 状态值-当前显示logo
	 */
	private static final int Cint_ShowLogo_State=1;
	/**
	 * 状态值-当前显示更新状态
	 */
	private static final int Cint_ShowClientUpdate_State=2;
	/**
	 * 能进入显示主界面计数
	 */
	private static final int Cint_CanShowHomeMessageNumber = 3;
	/**
	 * Logo显示结束消息ID
	 */
	private static final int Cint_CompleteShowLogo_MessageID=101;
	
	/**
	 * 开始检测最新客户端版本信息ID
	 */
	private static final int Cint_StartCheckClientVer_MessageID = 103;
	
	/**
	 * 完成检测最新客户端版本信息ID
	 */
	private static final int Cint_CompleteCheckClientVer_MessageID = 104;

	/**
	 * 开始用户登录信息ID
	 */
	private static final int Cint_StartUserLogin_MessageID=105;
	
	/**
	 * 完成用户登录信息ID
	 */
	private static final int Cint_CompleteUserLogin_MessageID=106;
	
	
	/**
	 * 当前状态
	 */
	private int mNowState;
	
	/**
	 * 消息数
	 */
	private int mMessageNumber;
	
	/**
	 * 客户端版本信息
	 */
	private ClientVerVO mClientVerVO;
	
	private int mPushCount;
	
	/**
	 * 初始化View
	 */
	@Override
	protected void initView()
	{
		//初始化View
		setContentView(R.layout.activity_welcome);
		((TextView)findViewById(R.id.welcome_ver_tv)).setText(String.format(getResources().getString(R.string.welcome_ver), SettingUtil.getClientVersion(this)));
		Intent intent = getIntent();
		mPushCount = intent.getIntExtra(HomeActivity.CSTR_EXTRA_ACTION_PUSHINFO, 0);

		//增加桌面快捷方式
		if(SettingUtil.isFirstStart(this, true))
		{
			ShortcutUtil.createShortCut(this, R.drawable.ic_launcher, R.string.app_name);
		}
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
	 * 发送消息
	 * @param what
	 * @param delay 延时多久，单位ms，如果为0，则立即发送消息
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


	@SuppressLint("HandlerLeak")
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
				//重新回来显示logo状态
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
			//只有显示logo状态且到计数值到了才能切换到主界面
			if(mNowState==Cint_ShowLogo_State&&mMessageNumber>=Cint_CanShowHomeMessageNumber)
			{
				startHome();
			}
		}
	};

	/**
	 * 启动获取版本信息
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
		//判断版本更新信息
		if(!mClientVerVO.hasUpdate())
		{
			sendMsg(Cint_CompleteCheckClientVer_MessageID, 0);
			return;
		}
		//显示更新信息
		showUpdateDialog();
	}
	
	/**
	 * 显示更新对话框
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
	 * 检查用户登录
	 */
	private void startUserLogin()
	{
		//如果之前已经登录过
		UserVO vo = getApplicationSet().getUserVO();
		if(vo!=null)
		{
			completeUserLogin();
			return;
		}
		//读取之前的登录信息
		vo = UserVO.getLastLoginUserInfo();
		if(vo==null)
		{
			completeUserLogin();
			return;
		}
		//远程判断
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
	 * 启动主窗体
	 */
	private void startHome()
	{
		//启动接收推送信息
		startPushService();
//		
//		//启动主界面
		Intent intent=new Intent(this, HomeActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		intent.putExtra(HomeActivity.CSTR_EXTRA_ACTION_PUSHINFO, mPushCount);
//		//intent.putExtra(HomeActivity.CSTR_PARMS_STARTADNEWSURL, mStartLogoNewUrl);
		runIntent(true, intent);
	}
}
