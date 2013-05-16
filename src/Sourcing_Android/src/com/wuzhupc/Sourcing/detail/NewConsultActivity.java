package com.wuzhupc.Sourcing.detail;

import android.view.View;
import android.widget.EditText;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.vo.ResponseVO;
import com.wuzhupc.services.BaseJsonService.IBaseReceiver;
import com.wuzhupc.services.MobileUserService;
import com.wuzhupc.utils.StringUtil;
import com.wuzhupc.utils.json.JsonParser;

/**
 * ����ѯ��Ϣ
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-12-19 ����11:46:12
 */
public class NewConsultActivity extends BaseActivity
{
	public static final int CINT_REQUESTCODE_NEWCONSULT = 10001;
	
	private EditText met_content;
	
	@Override
	protected void initDataContent()
	{
	}

	@Override
	protected void initView()
	{
		setContentView(R.layout.activity_newconsult);
		setResult(RESULT_CANCELED);
		met_content = (EditText)findViewById(R.id.newconsult_conent_et);
	}

	/**
	 * �ύ��ѯ��Ϣ
	 * @param v
	 */
	public void onSendClick(View v)
	{
		if(StringUtil.isEmpty(met_content.getText().toString()))
		{
			displayToast(R.string.newconsult_content_hit);
			met_content.requestFocus();
			return;
		}
		MobileUserService service = new MobileUserService(NewConsultActivity.this);
		service.sendUserConsult(met_content.getText().toString(),new IBaseReceiver()
		{
			@Override
			public void receiveCompleted(boolean isSuc, String content)
			{
				if(!isSuc)
				{
					displayToast(getResources().getString(R.string.newconsult_sendconsult_errorhit, content));
					return;
				}
				ResponseVO respVo = JsonParser.parseJsonToResponse(content);
				if(respVo.isSucess())
				{
					displayToast(R.string.newconsult_sendconsult_sucesshit);
					setResult(RESULT_OK);
					finish();
					return;
				}
				displayToast(getResources().getString(R.string.newconsult_sendconsult_errorhit, respVo.getMsg()));				
			}
		},true,getResources().getString(R.string.newconsult_sendconsult_hit));
	}
}
