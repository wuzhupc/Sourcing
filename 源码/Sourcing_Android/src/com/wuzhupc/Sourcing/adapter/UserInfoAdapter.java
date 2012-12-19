package com.wuzhupc.Sourcing.adapter;

import java.util.List;

import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.vo.TrainVO;
import com.wuzhupc.widget.MoreButton;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class UserInfoAdapter extends BaseAdapter
{

	private Context mContext;
	private List<?> mList;

	public UserInfoAdapter(Context ctx, List<?> list)
	{
		this.mContext = ctx;
		this.mList = list;
	}

	@Override
	public int getCount()
	{
		if (mList != null)
		{
			return mList.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position)
	{
		if (mList != null && position >= 0 && position < mList.size())
		{
			return mList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view = null;
		Object obj = mList.get(position);

		if (obj instanceof String)
		{
			view = LayoutInflater.from(mContext).inflate(R.layout.listitem_base_label, null);
			((TextView) view).setText((String) obj);
		} else if (obj instanceof MoreButton)
		{
			view = LayoutInflater.from(mContext).inflate(R.layout.widget_morebutton, null);

			((MoreButton) obj).setView(view);
		} else if (obj instanceof TrainVO)
		{
			//TODO
			view = getTrainListItem((TrainVO)obj);
		} 
		return view;
	}
	
	/**
	 * 获取培训机构列表
	 * @param vo
	 * @return
	 */
	private View getTrainListItem(TrainVO vo)
	{
		View view = LayoutInflater.from(mContext).inflate(R.layout.listitem_train, null);
		TextView title_tv = (TextView) view.findViewById(R.id.listitem_train_tv);
		title_tv.setText(vo.getTrainname());
		return view;
	}

}
