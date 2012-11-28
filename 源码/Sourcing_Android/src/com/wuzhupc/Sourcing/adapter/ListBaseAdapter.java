package com.wuzhupc.Sourcing.adapter;

import java.util.List;

import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.vo.NewsVO;
import com.wuzhupc.services.ImageService;
import com.wuzhupc.utils.ImageUtil;
import com.wuzhupc.utils.StringUtil;
import com.wuzhupc.widget.MoreButton;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListBaseAdapter extends BaseAdapter
{

	private Context mContext;
	private List<?> mList;

	private ImageService imageService; // 缩略图下载service

	public ListBaseAdapter(Context ctx, List<?> list)
	{
		this.mContext = ctx;
		this.mList = list;

		imageService = new ImageService(mContext);
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
		} else if (obj instanceof NewsVO)
		{
			NewsVO vo = (NewsVO) obj;
			// 头条(且只有第一条才显示成头条样式)
			if (position == 0 && vo.isHeadline())
			{
				view = LayoutInflater.from(mContext).inflate(R.layout.listitem_base_headline, null);
			} else
			// if (vo.getNewsType() == Constants.NEWS_TYPE_NORMAL)
			{
				view = LayoutInflater.from(mContext).inflate(R.layout.listitem_base, null);
			}
			TextView title_tv = (TextView) view.findViewById(R.id.listitem_base_title_tv);// lbi_title
			TextView summary_tv = (TextView) view.findViewById(R.id.listitem_base_summary_tv);// lbi_content_tv
			ImageView titlePic_iv = (ImageView) view.findViewById(R.id.listitem_base_iv);// lbi_image_iv
			title_tv.setText(vo.getTitle());
			summary_tv.setText(vo.getNewssummary());

			// 头条(且只有第一条才显示成头条样式)
			if (position == 0 && vo.isHeadline())
			{
				// 头条时，加载中图
				imageService.setThumbnail(titlePic_iv, vo.getTitlepic(), null, ImageUtil.IMAGE_DEFMEDIUMWIDTH,
						ImageUtil.IMAGE_DEFMEDIUMHEIGHT);
			} else if (!StringUtil.isEmpty(vo.getTitlepic_small()))
			{
				// 非头条时，有小图载入小图
				imageService.setThumbnail(titlePic_iv, vo.getTitlepic_small(), null, ImageUtil.IMAGE_DEFSMALLWIDTH,
						ImageUtil.IMAGE_DEFSMALLHEIGHT);
			} else if (!StringUtil.isEmpty(vo.getTitlepic()))
			{
				// 非头条时，无小图但有大图载入在图
				imageService.setThumbnail(titlePic_iv, vo.getTitlepic(), null, ImageUtil.IMAGE_DEFSMALLWIDTH,
						ImageUtil.IMAGE_DEFSMALLHEIGHT);
			} else
			{
				titlePic_iv.setVisibility(View.GONE);
			}
		}
		return view;
	}

}
