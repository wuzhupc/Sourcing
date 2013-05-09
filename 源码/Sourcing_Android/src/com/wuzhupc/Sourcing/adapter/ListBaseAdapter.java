package com.wuzhupc.Sourcing.adapter;

import java.util.List;

import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.vo.CompanyVO;
import com.wuzhupc.Sourcing.vo.JobVO;
import com.wuzhupc.Sourcing.vo.NewsVO;
import com.wuzhupc.Sourcing.vo.ProjectVO;
import com.wuzhupc.Sourcing.vo.ResumeVO;
import com.wuzhupc.Sourcing.vo.TrainVO;
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
	
	private boolean misfav;

	public ListBaseAdapter(Context ctx, List<?> list)
	{
		this(ctx, list, false);
	}

	public ListBaseAdapter(Context ctx, List<?> list,boolean isfav)
	{
		this.mContext = ctx;
		this.mList = list;
		this.misfav = isfav;
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
			view = (MoreButton) obj;
			//LayoutInflater.from(mContext).inflate(R.layout.widget_morebutton, null);

	//((MoreButton) obj).setView(view);
		} else if (obj instanceof NewsVO)
		{
			view = getNewsListItem((NewsVO)obj, position);
		} else if (obj instanceof TrainVO)
		{
			view = getTrainListItem((TrainVO)obj);
		} else if (obj instanceof ProjectVO)
		{
			view = getProjectListItem((ProjectVO)obj);
		} else if (obj instanceof JobVO)
		{
			view = getJobListItem((JobVO)obj);
		} else if (obj instanceof ResumeVO)
		{
			view = getResumeListItem((ResumeVO)obj);
		}else if (obj instanceof CompanyVO)
		{
			view = getCompanyListItem((CompanyVO)obj);
		}
		return view;
	}
	
	/**
	 * 职位
	 * @param vo
	 * @return
	 */
	private View getResumeListItem(ResumeVO vo)
	{
		View view = LayoutInflater.from(mContext).inflate(R.layout.listitem_project, null);
		TextView title_tv = (TextView) view.findViewById(R.id.listitem_projectname_tv);
		title_tv.setText(vo.getResumetitle());
		TextView status_tv = (TextView) view.findViewById(R.id.listitem_project_status_tv);
		status_tv.setText(vo.getExpectjob());
		TextView time_tv = (TextView) view.findViewById(R.id.listitem_project_time_tv);
		time_tv.setText(vo.getPublishtime());
		return view;
	}
	
	/**
	 * 职位
	 * @param vo
	 * @return
	 */
	private View getJobListItem(JobVO vo)
	{
		View view = LayoutInflater.from(mContext).inflate(R.layout.listitem_project, null);
		TextView title_tv = (TextView) view.findViewById(R.id.listitem_projectname_tv);
		title_tv.setText(vo.getJob());
		TextView status_tv = (TextView) view.findViewById(R.id.listitem_project_status_tv);
		status_tv.setText(vo.getCompany());
		TextView time_tv = (TextView) view.findViewById(R.id.listitem_project_time_tv);
		time_tv.setText(vo.getPublishtime());
		return view;
	}
	
	/**
	 * 获取项目
	 * @param vo
	 * @return
	 */
	private View getProjectListItem(ProjectVO vo)
	{
		View view = LayoutInflater.from(mContext).inflate(R.layout.listitem_project, null);
		TextView title_tv = (TextView) view.findViewById(R.id.listitem_projectname_tv);
		title_tv.setText(vo.getProjectname());
		TextView status_tv = (TextView) view.findViewById(R.id.listitem_project_status_tv);
		status_tv.setText(vo.getProjectstatus());
		TextView time_tv = (TextView) view.findViewById(R.id.listitem_project_time_tv);
		time_tv.setText(vo.getPublishtime());
		return view;
	}
	
	/**
	 * 获取项目
	 * @param vo
	 * @return
	 */
	private View getCompanyListItem(CompanyVO vo)
	{
		View view = LayoutInflater.from(mContext).inflate(R.layout.listitem_project, null);
		TextView title_tv = (TextView) view.findViewById(R.id.listitem_projectname_tv);
		title_tv.setText(vo.getCompanyname());
		TextView status_tv = (TextView) view.findViewById(R.id.listitem_project_status_tv);
		status_tv.setText("");
		TextView time_tv = (TextView) view.findViewById(R.id.listitem_project_time_tv);
		time_tv.setText(vo.getIndustry());
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
	
	/**
	 * 生成资讯listitem View
	 * @param vo
	 * @param position
	 * @return
	 */
	private View getNewsListItem(NewsVO vo,int position)
	{
		View view =null;
		// 头条(且只有第一条才显示成头条样式)
		if (position == 0 && !misfav && vo.isHeadline()&&!misfav)
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
		if (position == 0 && !misfav && vo.isHeadline())
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
		return view;
	}

}
