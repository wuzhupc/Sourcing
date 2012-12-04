package com.wuzhupc.Sourcing.detail;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.vo.NewsDetailVO;
import com.wuzhupc.Sourcing.vo.NewsVO;
import com.wuzhupc.Sourcing.vo.ResponseVO;
import com.wuzhupc.services.BaseJsonService.IBaseReceiver;
import com.wuzhupc.services.MobileInfoService;
import com.wuzhupc.utils.FavoriteUtil;
import com.wuzhupc.utils.WebViewUtil;
import com.wuzhupc.utils.json.JsonParser;
import com.wuzhupc.widget.OnReloadListener;

/**
 * 资讯详情
 * 支持传入参数newsvo 数据类型NewsVO
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-12-4 下午9:30:37
 */
public class NewsDetailActivity extends BaseActivity
{
	protected static final String TAG = NewsDetailActivity.class.getSimpleName();
	
	/**
	 * 传入参数newsvo 数据类型NewsVO
	 */
	public static final String CSTR_EXTRA_NEWSDETAIL_NEWSVO = "newsvo";

	/**
	 * 共享按钮
	 */
	private ImageView miv_fav;
	
	private WebView mwv_content;
	
	/**
	 * 
	 */
	private NewsVO mNewsVO;
	
	/**
	 * 
	 */
	private NewsDetailVO mNewsDetailVO;
	
	public FavoriteUtil mFavoriteUtil;

	@Override
	protected void initView()
	{
		setContentView(R.layout.activity_detail_news);
		setTitleTextBold();
		miv_fav = (ImageView)findViewById(R.id.detail_tb_share_iv);
		mwv_content = (WebView)findViewById(R.id.detail_content_wv);
		WebViewUtil.setWebView(this, mwv_content,new OnReloadListener()
		{	
			@Override
			public void OnReload()
			{
				initDataContent();
			}
		});
		
		mFavoriteUtil = FavoriteUtil.getFavoriteUtil();
		
		Intent intent = getIntent();
		Object o = intent.getSerializableExtra(CSTR_EXTRA_NEWSDETAIL_NEWSVO);
		if(o==null||!(o instanceof NewsVO))
			return;
		mNewsVO = (NewsVO)o;
		setFavImageView(mFavoriteUtil.hasFavData(mNewsVO)!=-1);
	}
	
	@Override
	protected void initDataContent()
	{
		mwv_content.stopLoading();
		if(mNewsVO==null)
		{
			return;
		}

		MobileInfoService infoService= new MobileInfoService(NewsDetailActivity.this);
		infoService.getNewsDetail(mNewsVO.getNewstype(), mNewsVO.getNewsid(), new IBaseReceiver()
		{
			@Override
			public void receiveCompleted(boolean isSuc, String content)
			{
				if (!isSuc)
				{
					setErrorInfo(content);
					displayToast(content);
					return;
				}
				ResponseVO respVO = new ResponseVO();
				mNewsDetailVO = (NewsDetailVO)JsonParser.parseJsonToEntity(content, respVO);
				if(respVO.getCode()!=ResponseVO.RESPONSE_CODE_SUCESS)
				{
					setErrorInfo(respVO.getMsg());
					displayToast(respVO.getMsg());
					return;
				} 
				setNewsInfo();
			}
		});
	}
	
	/**
	 * 
	 * @param msg
	 */
	private void setErrorInfo(String msg)
	{
		//TODO
	}
	
	/**
	 * 
	 */
	private void setNewsInfo()
	{
		//TODO
		if(mNewsDetailVO==null)
		{
			setErrorInfo(getResources().getString(R.string.detail_news_content_empty));
			return;
		}
	}
	
	/**
	 * 设置收藏图标
	 * @param fav
	 */
	private void setFavImageView(boolean fav)
	{
		miv_fav.setImageResource(fav?R.drawable.ic_faved:R.drawable.ic_fav);
	}
	
	/**
	 * 返回
	 * @param v
	 */
	public void onHomeClick(View v)
	{
		this.finish();
	}

	/**
	 * 收藏
	 * @param v
	 */
	public void onFavClick(View v)
	{
		if(mNewsVO==null)
			return;
		int index = mFavoriteUtil.hasFavData(mNewsVO);
		if(index==-1)
		{
			//收藏
			mFavoriteUtil.addFavData(mNewsVO);
			setFavImageView(true);
			displayToast(R.string.fav_add);
			return;
		}
		//取消收藏
		mFavoriteUtil.removeFavData(index);
		setFavImageView(false);
		displayToast(R.string.fav_remove);
	}
	
	/**
	 * 生成共享内容
	 * @return
	 */
	private String generateShareText()
	{
		String result = "";
		//TODO
		
		return result;
	}

	/**
	 * 共享
	 * @param v
	 */
	public void onShareClick(View v)
	{
		Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, generateShareText());
        NewsDetailActivity.this.startActivity(Intent.createChooser(intent, "分享方式"));
	}
}
