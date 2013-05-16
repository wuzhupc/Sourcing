package com.wuzhupc.Sourcing.detail;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.vo.BaseVO;
import com.wuzhupc.Sourcing.vo.BaseVO.DetailInfoListener;
import com.wuzhupc.utils.FavoriteUtil;
import com.wuzhupc.utils.StringUtil;
import com.wuzhupc.utils.WebViewUtil;
import com.wuzhupc.widget.OnReloadListener;

/**
 * 资讯详情
 * 支持传入参数basevo[CSTR_EXTRA_NEWSDETAIL_DATA] 数据类型BaseVO 数据
 * 支持传入参数title[CSTR_EXTRA_NEWSDETAIL_TITLE] 数据类型String 标题
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-12-4 下午9:30:37
 * @note 传入的数据需要重截BaseVO中的方法：
 * 	generateShareText  
 * 	setHtmlToShow 
 * 	getHtmlContext 
 * 	getHtmlSubTitle 
 *  getHtmlTitle
 */
public class NewsDetailActivity extends BaseActivity
{
	protected static final String TAG = NewsDetailActivity.class.getSimpleName();
	
	/**
	 * 传入参数basevo 数据类型BaseVO 数据
	 */
	public static final String CSTR_EXTRA_NEWSDETAIL_DATA = "basevo";
	/**
	 * 传入参数title 数据类型String 标题
	 */
	public static final String CSTR_EXTRA_NEWSDETAIL_TITLE = "title";

	/**
	 * 共享按钮
	 */
	private ImageView miv_fav;
	
	private WebView mwv_content;
	
	/**
	 * 传入参数信息
	 */
	private BaseVO mBaseVO;
	
	/**
	 * 根据传入参数获取到的具体信息
	 */
	private BaseVO mDetailVO;
	
	public FavoriteUtil mFavoriteUtil;

	@Override
	protected void initView()
	{
		setContentView(R.layout.activity_detail_news);
		//setTitleTextBold();
		miv_fav = (ImageView)findViewById(R.id.detail_tb_fav_iv);
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
		Object o = intent.getSerializableExtra(CSTR_EXTRA_NEWSDETAIL_DATA);
		if(o==null||!(o instanceof BaseVO))
			return;
		mBaseVO = (BaseVO) o;
		setFavImageView(mFavoriteUtil.hasFavData(mBaseVO)!=-1);
		
		String strtitle = intent.getStringExtra(CSTR_EXTRA_NEWSDETAIL_TITLE);
		if(!StringUtil.isEmpty(strtitle))
			setTitleText(strtitle);
	}
	
	@Override
	protected void initDataContent()
	{
		mwv_content.stopLoading();
		if(mBaseVO==null)
			return;
		mBaseVO.setHtmlToShow(NewsDetailActivity.this,new DetailInfoListener()
		{			
			@Override
			public void onComplete(BaseVO baseVO)
			{	
				mDetailVO = baseVO;
				setNewsInfo();
			}

			@Override
			public void onError(String msg)
			{
				setErrorInfo(msg);
				displayToast(msg);
			}
		});		
	}
	
	/**
	 * 显示错误信息
	 * @param msg
	 */
	private void setErrorInfo(String msg)
	{
		if(mBaseVO==null)
			return;
		setWebViewContent(WebViewUtil.getHtmlHead()
				+mBaseVO.getHtmlTitle()
				+mBaseVO.getHtmlSubTitle()
				+WebViewUtil.getHtmlErrorHit(msg, true)
				+WebViewUtil.getHtmlEnd());
	}
	
	/**
	 * 显示新闻信息
	 */
	private void setNewsInfo()
	{
		if(mDetailVO==null)
		{
			setErrorInfo(getResources().getString(R.string.detail_news_content_empty));
			return;
		}
		setWebViewContent(WebViewUtil.getHtmlHead()
				+mDetailVO.getHtmlTitle()
				+mDetailVO.getHtmlSubTitle()
				+mDetailVO.getHtmlContext(NewsDetailActivity.this)
				+WebViewUtil.getHtmlEnd());
	}
	
	/**
	 * 设置webview内容
	 * @param html
	 */
	private void setWebViewContent(String html)
	{
		mwv_content.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
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
	 * 收藏
	 * @param v
	 */
	public void onFavClick(View v)
	{
		if(mBaseVO==null)
			return;
		int index = mFavoriteUtil.hasFavData(mBaseVO);
		if(index==-1)
		{
			//收藏
			mFavoriteUtil.addFavData(mBaseVO);
			setFavImageView(true);
			displayToast(R.string.fav_add);
			sendBroadcast(new Intent(FavInfoActivity.CSTR_ACTION_FAV));
			return;
		}
		//取消收藏
		mFavoriteUtil.removeFavData(index);
		setFavImageView(false);
		displayToast(R.string.fav_remove);
		sendBroadcast(new Intent(FavInfoActivity.CSTR_ACTION_FAV));
	}
	
	/**
	 * 生成共享内容
	 * @return
	 */
	private String generateShareText()
	{
		String result = "#"+getResources().getString(R.string.app_name)+"#";
		//生成共享内容
		if(mBaseVO!=null)
		{
			result+=mBaseVO.generateShareText()+" ";
		}
		result +="更多信息请访问"+getResources().getString(R.string.baseurl);
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
