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
 * ��Ѷ����
 * ֧�ִ������basevo[CSTR_EXTRA_NEWSDETAIL_DATA] ��������BaseVO ����
 * ֧�ִ������title[CSTR_EXTRA_NEWSDETAIL_TITLE] ��������String ����
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-12-4 ����9:30:37
 * @note �����������Ҫ�ؽ�BaseVO�еķ�����
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
	 * �������basevo ��������BaseVO ����
	 */
	public static final String CSTR_EXTRA_NEWSDETAIL_DATA = "basevo";
	/**
	 * �������title ��������String ����
	 */
	public static final String CSTR_EXTRA_NEWSDETAIL_TITLE = "title";

	/**
	 * ����ť
	 */
	private ImageView miv_fav;
	
	private WebView mwv_content;
	
	/**
	 * ���������Ϣ
	 */
	private BaseVO mBaseVO;
	
	/**
	 * ���ݴ��������ȡ���ľ�����Ϣ
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
	 * ��ʾ������Ϣ
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
	 * ��ʾ������Ϣ
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
	 * ����webview����
	 * @param html
	 */
	private void setWebViewContent(String html)
	{
		mwv_content.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
	}
	
	/**
	 * �����ղ�ͼ��
	 * @param fav
	 */
	private void setFavImageView(boolean fav)
	{
		miv_fav.setImageResource(fav?R.drawable.ic_faved:R.drawable.ic_fav);
	}

	/**
	 * �ղ�
	 * @param v
	 */
	public void onFavClick(View v)
	{
		if(mBaseVO==null)
			return;
		int index = mFavoriteUtil.hasFavData(mBaseVO);
		if(index==-1)
		{
			//�ղ�
			mFavoriteUtil.addFavData(mBaseVO);
			setFavImageView(true);
			displayToast(R.string.fav_add);
			sendBroadcast(new Intent(FavInfoActivity.CSTR_ACTION_FAV));
			return;
		}
		//ȡ���ղ�
		mFavoriteUtil.removeFavData(index);
		setFavImageView(false);
		displayToast(R.string.fav_remove);
		sendBroadcast(new Intent(FavInfoActivity.CSTR_ACTION_FAV));
	}
	
	/**
	 * ���ɹ�������
	 * @return
	 */
	private String generateShareText()
	{
		String result = "#"+getResources().getString(R.string.app_name)+"#";
		//���ɹ�������
		if(mBaseVO!=null)
		{
			result+=mBaseVO.generateShareText()+" ";
		}
		result +="������Ϣ�����"+getResources().getString(R.string.baseurl);
		return result;
	}

	/**
	 * ����
	 * @param v
	 */
	public void onShareClick(View v)
	{
		Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, generateShareText());
        NewsDetailActivity.this.startActivity(Intent.createChooser(intent, "����ʽ"));
	}
}
