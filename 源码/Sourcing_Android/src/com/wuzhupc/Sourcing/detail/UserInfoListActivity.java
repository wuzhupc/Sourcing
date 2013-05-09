package com.wuzhupc.Sourcing.detail;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.adapter.UserInfoAdapter;
import com.wuzhupc.Sourcing.vo.BaseVO;
import com.wuzhupc.Sourcing.vo.ResponseVO;
import com.wuzhupc.config.Constants;
import com.wuzhupc.services.BaseJsonService.IBaseReceiver;
import com.wuzhupc.services.MobileUserService;
import com.wuzhupc.utils.json.JsonParser;
import com.wuzhupc.widget.MoreButton;
import com.wuzhupc.widget.refreshview.PullToRefreshListView;

/**
 * �û���Ϣ��չ�б�
 * @author wuzhu
 * ֧�ִ������infotype[CSTR_EXTRA_INFO_TYPE] ��������int [CINT_INFO_TYPE_*]
 */
public class UserInfoListActivity extends BaseActivity
{
	protected static final String TAG = UserInfoListActivity.class.getSimpleName();
	
	public static final String CSTR_EXTRA_INFO_TYPE="infotype";
	public static final int CINT_INFO_TYPE_CONSULT = 1;
	public static final int CINT_INFO_TYPE_AUDIT = 2;
	public static final int CINT_INFO_TYPE_DECLARE = 3;
	public static final int CINT_INFO_TYPE_NOTIFIER = 4;
	
	/**
	 * ��Ϣ����
	 */
	private int mInfoType;
	
	/**
	 * �����б�
	 */
	@SuppressWarnings("rawtypes")
	protected ArrayList mDataList;
	
	private LinearLayout mll_addconsult;

	/**
	 * ���һ����Ϣid
	 */
	protected long mLastInfoId; // 

	/**
	 * �б� adapter
	 */
	protected UserInfoAdapter mAdapter; // 

	/**
	 * ��ʾ���ݵ��б�
	 */
	protected ListView mlv_DataList;

	protected PullToRefreshListView mPullRefreshListView;

	protected MoreButton mMoreButton;
	
	
	@Override
	protected void initDataContent()
	{
		loadInfoData();
	}

	@Override
	protected void initView()
	{
		setContentView(R.layout.activity_userinfolist);
		Intent intent = getIntent();
		mInfoType = intent.getIntExtra(CSTR_EXTRA_INFO_TYPE, CINT_INFO_TYPE_NOTIFIER);
		setTitleText(getTitleText());
		
		mll_addconsult = (LinearLayout)findViewById(R.id.userinfolist_addconsult_ll);
		mll_addconsult.setVisibility(mInfoType==CINT_INFO_TYPE_CONSULT?View.VISIBLE:View.GONE);
		
		mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.userinfolist_datalist_lv);
		// Set a listener to be invoked when the list should be refreshed.
		mPullRefreshListView
				.setOnRefreshListener(new com.wuzhupc.widget.refreshview.PullToRefreshBase.OnRefreshListener()
				{
					@Override
					public void onRefresh()
					{
						// Do work to refresh the list here.
						loadInfoData();
					}
				});
		mlv_DataList = mPullRefreshListView.getRefreshableView();
		mlv_DataList.setOnItemClickListener(getDataListItemClickListener());
		clearDataList();
	}
	
	private String getTitleText()
	{
		switch(mInfoType)
		{
		case  CINT_INFO_TYPE_AUDIT:
			return getString(R.string.userinfolist_audit_title);
		case CINT_INFO_TYPE_CONSULT:
			return getString(R.string.userinfolist_consult_title);
		case CINT_INFO_TYPE_DECLARE:
			return getString(R.string.userinfolist_declare_title);
		default:
			mInfoType = CINT_INFO_TYPE_NOTIFIER;
			return getString(R.string.userinfolist_notifier_title);
		}
	}
	
	/**
	 * ������ѯ
	 * @param v
	 */
	public void onAddConsultClick(View v)
	{
		Intent intent = new Intent(UserInfoListActivity.this, NewConsultActivity.class);
		startActivityForResult(intent, NewConsultActivity.CINT_REQUESTCODE_NEWCONSULT);
	} 
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(requestCode==NewConsultActivity.CINT_REQUESTCODE_NEWCONSULT&&resultCode==RESULT_OK)
		{
			if(mInfoType==CINT_INFO_TYPE_CONSULT)
			{
				loadInfoData();
				return;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * ��ȡ��ʾ����
	 */
	private void loadInfoData()
	{
		MobileUserService service = new MobileUserService(UserInfoListActivity.this);

		if(mPullRefreshListView!=null)
			mPullRefreshListView.setRefreshing(true);//   ����ˢ����ʾ
		switch(mInfoType)
		{
		case  CINT_INFO_TYPE_AUDIT:
			service.getUserAuditInfo(0l, getNewUserInfoReceiver(),true,getResources().getString(R.string.userinfolist_getdata_hit,getTitleText()));
			break;
		case CINT_INFO_TYPE_CONSULT:
			service.getUserConsultInfo(0l, getNewUserInfoReceiver(),true,getResources().getString(R.string.userinfolist_getdata_hit,getTitleText()));
			break;
		case CINT_INFO_TYPE_DECLARE:
			service.getUserDeclareInfo(0l, getNewUserInfoReceiver(),true,getResources().getString(R.string.userinfolist_getdata_hit,getTitleText()));
			break;
		case CINT_INFO_TYPE_NOTIFIER:
			service.getUserNotifierInfo(0l, getNewUserInfoReceiver(),true,getResources().getString(R.string.userinfolist_getdata_hit,getTitleText()));
			break;
		}
	}
	
	/**
	 * ˢ����ϢReceiver
	 * @return
	 */
	private IBaseReceiver getNewUserInfoReceiver()
	{
		return new IBaseReceiver()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void receiveCompleted(boolean isSuc, String content)
			{
				if(mPullRefreshListView!=null)
					 mPullRefreshListView.onRefreshComplete();// ֹͣˢ�°�ť����
				if(!isSuc)
				{
					displayToast(content);
					return;
				}

				ResponseVO respVO = new ResponseVO();
				List<?> list = JsonParser.parseJsonToList(content, respVO);
				if(respVO.getCode()!=ResponseVO.RESPONSE_CODE_SUCESS)
				{
					// ���ķ��ش���
					displayToast(respVO.getMsg());
					return;
				}
				clearDataList();// ������������ʾ
				if (list != null && !list.isEmpty())
				{
					getLastNewsId(list);
					addDataList(list);
					// �ж��Ƿ����б�ײ����ϸ��ఴť
					if (list.size() >= Constants.CINT_PAGE_SIZE)
					{
						mMoreButton.setShowButton(UserInfoListActivity.this
								.getString(R.string.list_item_more_msg));
						mDataList.add(mMoreButton);
					}
				}
				mAdapter.notifyDataSetChanged();
				mlv_DataList.setSelectionAfterHeaderView(); // �б�ѡ�����û�ͷ��
			}
		};
	}

	/**
	 * ���ظ�������
	 */
	private void loadMoreData()
	{
		if(mMoreButton==null)
			return;
		mMoreButton.setShowProgress(UserInfoListActivity.this
				.getString(R.string.list_item_loading_data)); // �л����ఴť״̬ Ϊ ����״̬
		MobileUserService service = new MobileUserService(UserInfoListActivity.this);
		switch(mInfoType)
		{
		case  CINT_INFO_TYPE_AUDIT:
			service.getUserAuditInfo(mLastInfoId, getMoreUserInfoReceiver(),false,"");
			break;
		case CINT_INFO_TYPE_CONSULT:
			service.getUserConsultInfo(mLastInfoId, getMoreUserInfoReceiver(),false,"");
			break;
		case CINT_INFO_TYPE_DECLARE:
			service.getUserDeclareInfo(mLastInfoId, getMoreUserInfoReceiver(),false,"");
			break;
		case CINT_INFO_TYPE_NOTIFIER:
			service.getUserNotifierInfo(mLastInfoId, getMoreUserInfoReceiver(),false,"");
			break;
		}
	}
	
	/**
	 * ���ظ�������Receiver
	 * @return
	 */
	private IBaseReceiver getMoreUserInfoReceiver()
	{
		return new IBaseReceiver()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void receiveCompleted(boolean isSuc, String content)
			{
				if(!isSuc)
				{
					mMoreButton.setShowButton(getString(R.string.list_item_more_msg)); // �л����ఴť״̬
					displayToast(content);
					return;
				}
				ResponseVO respVO = new ResponseVO();
				List<?> list = JsonParser.parseJsonToList(content, respVO);
				if(respVO.getCode()!=ResponseVO.RESPONSE_CODE_SUCESS)
				{
					// ���ķ��ش���
					mMoreButton.setShowButton(getString(R.string.list_item_more_msg)); // �л����ఴť״̬
					displayToast(respVO.getMsg());
					return;
				}
				// ���ķ�������
				if (mDataList.contains(mMoreButton))
				{ // �����������Ƴ����ఴť�����ݻ�ȡ������Ϣ���ж��Ƿ������ӵ��б�ĩβ
					mDataList.remove(mMoreButton);
				}
				if (list != null && !list.isEmpty())
				{
					getLastNewsId(list);
					addDataList(list);
					// �ж��Ƿ����б�ײ����ϸ��ఴť
					if (list.size() >= Constants.CINT_PAGE_SIZE)
					{
						mMoreButton.setShowButton(getString(R.string.list_item_more_msg));
						mDataList.add(mMoreButton);
					}
				}
				mAdapter.notifyDataSetChanged();
			}
		};
	};
	
	/**
	 * �����������
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void clearDataList()
	{	
		if (mMoreButton == null)
			mMoreButton = new MoreButton(UserInfoListActivity.this);
		if(mDataList==null)
		{
			mDataList = new ArrayList();
			// adapter ��ʼ��
			mAdapter = new UserInfoAdapter(UserInfoListActivity.this, mDataList);
			mlv_DataList.setAdapter(mAdapter);
		}
		else
			mDataList.clear();
		//������������ʾ
		mDataList.add(getString(R.string.list_item_no_data));
		mAdapter.notifyDataSetChanged();
	}
	
	/**
	 * ��ȡ��������Listener
	 * 
	 * @return
	 */
	protected OnItemClickListener getDataListItemClickListener()
	{
		OnItemClickListener itemClickListener = new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				position -= mlv_DataList.getHeaderViewsCount(); // ��ȥ�б� header����

				if (position < 0 || position >= mDataList.size())
					return;

				Object obj = mDataList.get(position);
				if (obj == null)
					return;
				if (obj instanceof String)
					return;
				if (obj instanceof MoreButton)
				{
					loadMoreData();
					return;
				}
			}
		};
		return itemClickListener;
	}
	
	/**
	 * �����б�����
	 * @param list
	 */
	@SuppressWarnings({ "unchecked"})
	private void addDataList(List<?> list)
	{
		if(mDataList==null)			
			clearDataList();
		if(list==null||list.isEmpty())
			return;
		if(mDataList.size()==1)
		{
			Object o = mDataList.get(0);
			if(o instanceof String)
				mDataList.clear();
		}
		mDataList.addAll(list);
	}

	/**
	 * ��ȡ�б����һ����Ϣid
	 * @param list
	 */
	private void getLastNewsId(List<?> list)
	{
		if (list == null || list.isEmpty())
			return;
		for (int i = list.size() - 1; i >= 0; i--)
		{
			Object obj = list.get(i);
			if (obj instanceof BaseVO)
			{
				mLastInfoId = ((BaseVO) obj).getId();
				break;
			}
		}
	}
}
