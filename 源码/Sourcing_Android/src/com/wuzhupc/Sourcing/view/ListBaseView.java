package com.wuzhupc.Sourcing.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.adapter.ListBaseAdapter;
import com.wuzhupc.Sourcing.vo.NewsVO;
import com.wuzhupc.Sourcing.vo.ResponseVO;
import com.wuzhupc.config.Constants;
import com.wuzhupc.services.BaseJsonService.IBaseReceiver;
import com.wuzhupc.services.MobileInfoService;
import com.wuzhupc.utils.CacheUtil;
import com.wuzhupc.utils.SettingUtil;
import com.wuzhupc.utils.StringUtil;
import com.wuzhupc.utils.json.JsonParser;
import com.wuzhupc.widget.MoreButton;
import com.wuzhupc.widget.refreshview.PullToRefreshListView;

/**
 * ��Ѷ��չʾ��Ϣ
 * 
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-28 ����9:13:33
 */
public class ListBaseView extends BaseView
{
	/**
	 * �����б�
	 */
	@SuppressWarnings("rawtypes")
	protected ArrayList mDataList;

	/**
	 * ��ʾ���ݵ��б�
	 */
	protected ListView mlv_DataList;

	protected PullToRefreshListView mPullRefreshListView;

	protected MoreButton mMoreButton; //

	protected ListBaseAdapter mAdapter; // �б� adapter

	protected long mLastNewsId; // ���һ����Ϣid

	/**
	 * ���캯��
	 * 
	 * @param context
	 * @param fatherchannelid
	 *            ��ĿID
	 */
	public ListBaseView(Context context, long fatherchannelid)
	{
		super(context, fatherchannelid);
	}
	
	/**
	 * ��ȡ��ʾ����
	 * @note �������ݿ�������ݣ����������ݣ���ӷ�������ص���������ٽ��м���
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void loadData(Boolean isfirstload)
	{
		mDataList.clear(); // �л�Ƶ��������б�����
		if (mMoreButton == null)
			mMoreButton = new MoreButton();
		//��ȡ���ػ��������
		List<NewsVO> list = null;
		String content = CacheUtil.getCacheContent(getNowChannelInfo());
		if(!StringUtil.isEmpty(content))
			list = (List<NewsVO>) JsonParser.parseJsonToList(content, new ResponseVO());
		if (list != null && !list.isEmpty())
		{
			getLastNewsId(list);
			mDataList.addAll(list);
			// �ж��Ƿ����б�ײ����ϸ��ఴť
			if (list.size() >= Constants.CINT_PAGE_SIZE)
			{
				mMoreButton.setShowButton(mContext
						.getString(R.string.list_item_more_msg));
				mDataList.add(mMoreButton);
			}
			mAdapter.notifyDataSetChanged();
			mlv_DataList.setSelectionAfterHeaderView(); // �б�ѡ�����û�ͷ��
			if (isfirstload)// �״ν���Ƶ�����ӷ���˻�ȡ��������
				loadNewData();
		} else// ���������ݣ��ӷ���˻�ȡ
		{ 
			loadNewData();
		}
	}

	@Override
	public void reflashData()
	{
		loadNewData();
	}

	@Override
	public void initContentView()
	{
		// ��ʼ��View
		View v = LayoutInflater.from(mContext).inflate(R.layout.listitem_base, mll_content, false);
		setContentView(v);

		mPullRefreshListView = (PullToRefreshListView) v.findViewById(R.id.list_base_datalist_lv);
		// Set a listener to be invoked when the list should be refreshed.
		mPullRefreshListView
				.setOnRefreshListener(new com.wuzhupc.widget.refreshview.PullToRefreshBase.OnRefreshListener()
				{
					@Override
					public void onRefresh()
					{
						// Do work to refresh the list here.
						// new GetDataTask().execute();
						loadNewData();
					}
				});
		mlv_DataList = mPullRefreshListView.getRefreshableView();
		mlv_DataList.setOnItemClickListener(getDataListItemClickListener());
		setViewGestureDetector(mlv_DataList);
		// ���ݳ�ʼ��
		mDataList = new ArrayList();
		// adapter ��ʼ��
		mAdapter = new ListBaseAdapter(mContext, mDataList);
		mlv_DataList.setAdapter(mAdapter);
	}

	@Override
	public void reflashContentView()
	{
		// TODO Auto-generated method stub

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
				if (obj instanceof NewsVO)
				{
					runDetailActivity(position);
				}
			}
		};
		return itemClickListener;
	}
	
	/**
	 * ��ת���������
	 * 
	 * @param position
	 */
	protected void runDetailActivity(int position)
	{
		//TODO
	}
	/**
	 * ��ȡ�б����һ������id
	 * @param list
	 */
	private void getLastNewsId(List<?> list)
	{
		if (list == null || list.isEmpty())
			return;
		for (int i = list.size() - 1; i >= 0; i--)
		{
			Object obj = list.get(i);
			if (obj instanceof NewsVO)
			{
				mLastNewsId = ((NewsVO) obj).getNewsid();
				break;
			}
		}
	}
	
	/**
	 * �������б����ȡ �������� ����Ŀ(��ȥ�б�ͷ��β����Ŀ)
	 * 
	 * @param list
	 * @return
	 */
	protected ArrayList<NewsVO> getNewsList(List list)
	{
		ArrayList<NewsVO> ls = new ArrayList<NewsVO>();

		for (Object obj : list)
		{
			if (obj instanceof NewsVO)
			{
				ls.add((NewsVO) obj);
			}
		}

		return ls;
	}
	
	/**
	 * �ӷ���˻�ȡ��������
	 */
	protected void loadNewData()
	{
		MobileInfoService newsService= new MobileInfoService(mContext);
		final long channelId = getNowChannelID();
		if(mPullRefreshListView!=null)
			mPullRefreshListView.setRefreshing(true);//   ����ˢ����ʾ
		keepMainTitleReflashStauts();
		newsService.getNewsList(getNowChannelInfo().getType(), 0l, new IBaseReceiver()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void receiveCompleted(boolean isSuc, String content)
			{
				// ˢ�¹������л�Ƶ���������б������д���
				if(channelId!=getNowChannelID())
					return;
				stopMainTitleReflashStauts();
				if(mPullRefreshListView!=null)
					 mPullRefreshListView.onRefreshComplete();// ֹͣˢ�°�ť����
				if(!isSuc)
				{
					showHint(content);
					return;
				}
				ResponseVO respVO = new ResponseVO();
				List<?> list = JsonParser.parseJsonToList(content, respVO);
				if(respVO.getCode()!=ResponseVO.RESPONSE_CODE_SUCESS)
				{
					// ���ķ��ش���
					showHint(respVO.getMsg());
					return;
				}
				// ���ķ�������
				// �洢���ˢ�µ��б�
				CacheUtil.cacheContent(getNowChannelInfo(), content);
				SettingUtil.setChannelLastUpdateTime(mContext, getNowChannelID(), new Date());
				mDataList.clear(); // ˢ���б�����б�����
				if (list == null || list.isEmpty())// ������������ʾ
					mDataList.add(mContext.getString(R.string.list_item_no_data));
				else
				{
					getLastNewsId(list);
					mDataList.addAll(list);
					// �ж��Ƿ����б�ײ����ϸ��ఴť
					if (list.size() >= Constants.CINT_PAGE_SIZE)
					{
						mMoreButton.setShowButton(mContext
								.getString(R.string.list_item_more_msg));
						mDataList.add(mMoreButton);
					}
				}
				mAdapter.notifyDataSetChanged();
				mlv_DataList.setSelectionAfterHeaderView(); // �б�ѡ�����û�ͷ��
			}
		});
		
	}
	/**
	 * �ӷ���˻�ȡ��������
	 */
	private void loadMoreData()
	{
		if(mMoreButton==null)
			return;
		mMoreButton.setShowProgress(mContext
				.getString(R.string.list_item_loading_data)); // �л����ఴť״̬ Ϊ ����״̬
		
		//�л����������°�ť״̬		
		MobileInfoService newsService= new MobileInfoService(mContext);
		final long channelId = getNowChannelID();
		keepMainTitleReflashStauts();
		newsService.getNewsList(getNowChannelInfo().getType(), mLastNewsId, new IBaseReceiver()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void receiveCompleted(boolean isSuc, String content)
			{
				// ˢ�¹������л�Ƶ���������б������д���
				if(channelId!=getNowChannelID())
					return;
				stopMainTitleReflashStauts();
				if(!isSuc)
				{
					mMoreButton.setShowButton(mContext.getString(R.string.list_item_more_msg)); // �л����ఴť״̬
					showHint(content);
					return;
				}
				ResponseVO respVO = new ResponseVO();
				List<?> list = JsonParser.parseJsonToList(content, respVO);
				if(respVO.getCode()!=ResponseVO.RESPONSE_CODE_SUCESS)
				{
					// ���ķ��ش���
					mMoreButton.setShowButton(mContext.getString(R.string.list_item_more_msg)); // �л����ఴť״̬
					showHint(respVO.getMsg());
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
					mDataList.addAll(list);
					// �ж��Ƿ����б�ײ����ϸ��ఴť
					if (list.size() >= Constants.CINT_PAGE_SIZE)
					{
						mMoreButton.setShowButton(mContext
								.getString(R.string.list_item_more_msg));
						mDataList.add(mMoreButton);
					}
				}
				mAdapter.notifyDataSetChanged();
			}
		});
		
		
	}
}
