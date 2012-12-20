package com.wuzhupc.Sourcing.adapter;

import java.util.List;

import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.vo.AuditResultVO;
import com.wuzhupc.Sourcing.vo.AuditVO;
import com.wuzhupc.Sourcing.vo.ConsultResultVO;
import com.wuzhupc.Sourcing.vo.ConsultVO;
import com.wuzhupc.Sourcing.vo.DeclareResultVO;
import com.wuzhupc.Sourcing.vo.DeclareVO;
import com.wuzhupc.Sourcing.vo.NotifierVO;
import com.wuzhupc.widget.MoreButton;

import android.content.Context;
import android.text.Html;
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

		if (obj==null || obj instanceof String)
		{
			view = LayoutInflater.from(mContext).inflate(R.layout.listitem_base_label, null);
			((TextView) view).setText((String) obj);
		} else if (obj instanceof MoreButton)
		{
			view = LayoutInflater.from(mContext).inflate(R.layout.widget_morebutton, null);

			((MoreButton) obj).setView(view);
		} else if (obj instanceof ConsultVO)
		{
			view = getConsultListItem((ConsultVO)obj);
		} else if (obj instanceof AuditVO)
		{
			view = getAuditListItem((AuditVO)obj);
		} else if (obj instanceof DeclareVO)
		{
			view = getDeclareListItem((DeclareVO)obj);
		} else if (obj instanceof NotifierVO)
		{
			view = getNotifierListItem((NotifierVO)obj);
		}
		return view;
	}
	/**
	 * 获取通知提醒View
	 * @param vo
	 * @return
	 */
	private View getNotifierListItem(NotifierVO vo)
	{
		View view = LayoutInflater.from(mContext).inflate(R.layout.listitem_notifier, null);
	
		TextView content_tv = (TextView) view.findViewById(R.id.listitem_notifier_tv);
		content_tv.setText(Html.fromHtml(vo.getNotifiercontent()));
		TextView time_tv = (TextView) view.findViewById(R.id.listitem_notifier_time_tv);
		time_tv.setText(vo.getPublishtime());
		TextView status_tv = (TextView)view.findViewById(R.id.listitem_notifier_status_tv);
		status_tv.setText(vo.getPublisher());
		return view;
	}
	
	/**
	 * 获取申报信息View
	 * @param vo
	 * @return
	 */
	private View getDeclareListItem(DeclareVO vo)
	{
		View view = LayoutInflater.from(mContext).inflate(R.layout.listitem_audit, null);
	
		TextView content_tv = (TextView) view.findViewById(R.id.listitem_audit_tv);
		content_tv.setText(Html.fromHtml(vo.getDeclarecontent()));
		TextView time_tv = (TextView) view.findViewById(R.id.listitem_audit_time_tv);
		time_tv.setText(vo.getPublishtime());
		TextView status_tv = (TextView)view.findViewById(R.id.listitem_audit_status_tv);
		status_tv.setText(vo.getDeclareStatus());
		DeclareResultVO resultVO = vo.getDeclareResultVO();
		if(resultVO==null)
		{
			view.findViewById(R.id.listitem_audit_result_ll).setVisibility(View.GONE);
		}
		else
		{
			view.findViewById(R.id.listitem_audit_result_ll).setVisibility(View.VISIBLE);
			TextView result_content_tv = (TextView) view.findViewById(R.id.listitem_audit_result_tv);
			result_content_tv.setText(Html.fromHtml(resultVO.getDeclareresultcontent()));
			TextView result_time_tv = (TextView) view.findViewById(R.id.listitem_audit_result_time_tv);
			result_time_tv.setText(String.format("%s  %s", resultVO.getPublisher(),resultVO.getPublishtime()));
		}
		return view;
	}
	
	/**
	 * 获取审核信息View
	 * @param vo
	 * @return
	 */
	private View getAuditListItem(AuditVO vo)
	{
		View view = LayoutInflater.from(mContext).inflate(R.layout.listitem_audit, null);
	
		TextView content_tv = (TextView) view.findViewById(R.id.listitem_audit_tv);
		content_tv.setText(Html.fromHtml(vo.getAuditcontent()));
		TextView time_tv = (TextView) view.findViewById(R.id.listitem_audit_time_tv);
		time_tv.setText(vo.getPublishtime());
		TextView status_tv = (TextView)view.findViewById(R.id.listitem_audit_status_tv);
		status_tv.setText(vo.getAuditStatus());
		AuditResultVO resultVO = vo.getAuditResultVO();
		if(resultVO==null)
		{
			view.findViewById(R.id.listitem_audit_result_ll).setVisibility(View.GONE);
		}
		else
		{
			view.findViewById(R.id.listitem_audit_result_ll).setVisibility(View.VISIBLE);
			TextView result_content_tv = (TextView) view.findViewById(R.id.listitem_audit_result_tv);
			result_content_tv.setText(Html.fromHtml(resultVO.getAuditresultcontent()));
			TextView result_time_tv = (TextView) view.findViewById(R.id.listitem_audit_result_time_tv);
			result_time_tv.setText(String.format("%s  %s", resultVO.getPublisher(),resultVO.getPublishtime()));
		}
		return view;
	}
	
	/**
	 * 获取咨询信息View
	 * @param vo
	 * @return
	 */
	private View getConsultListItem(ConsultVO vo)
	{
		View view = LayoutInflater.from(mContext).inflate(R.layout.listitem_consult, null);
	
		TextView content_tv = (TextView) view.findViewById(R.id.listitem_consult_tv);
		content_tv.setText(Html.fromHtml(vo.getConsultcontent()));
		TextView time_tv = (TextView) view.findViewById(R.id.listitem_consult_time_tv);
		time_tv.setText(vo.getPublishtime());
		ConsultResultVO resultVO = vo.getConsultResultVO();
		if(resultVO==null)
		{
			view.findViewById(R.id.listitem_consult_result_ll).setVisibility(View.GONE);
		}
		else
		{
			view.findViewById(R.id.listitem_consult_result_ll).setVisibility(View.VISIBLE);
			TextView result_content_tv = (TextView) view.findViewById(R.id.listitem_consult_result_tv);
			result_content_tv.setText(Html.fromHtml(resultVO.getConsultresultcontent()));
			TextView result_time_tv = (TextView) view.findViewById(R.id.listitem_consult_result_time_tv);
			result_time_tv.setText(String.format("%s  %s", resultVO.getPublisher(),resultVO.getPublishtime()));
		}
		return view;
	}

}
