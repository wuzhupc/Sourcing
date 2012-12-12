package com.wuzhupc.utils;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.detail.ViewImageActivity;
import com.wuzhupc.Sourcing.vo.BaseVO;
import com.wuzhupc.widget.OnReloadListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.LayoutAlgorithm;

/**
 * ����webview��ص�����
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-12-4 ����9:39:33
 */
public class WebViewUtil
{
	/**
	 * �������ӱ�ǩ
	 */
	public static final String CSTR_RELOADLINK = "wuzhupc://reload";
	@SuppressLint("SetJavaScriptEnabled")
	public static void setWebView(final Context c,WebView webview,final OnReloadListener reloadListener)
	{
		if(c==null||webview==null)
			return;
		WebSettings websettings = webview.getSettings();
		// 1��LayoutAlgorithm.NARROW_COLUMNS �� ��Ӧ���ݴ�С
        // 2��LayoutAlgorithm.SINGLE_COLUMN:��Ӧ��Ļ�����ݽ��Զ�����
		websettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		// ����֧��JavaScript
		webview.clearCache(true);
		websettings.setJavaScriptEnabled(true);
		//TODO �������� websettings.setTextSize(SettingUtil.getNewsFontSize(c));
		webview.setWebViewClient(new WebViewClient()
		{
			// ��������ת����
			public boolean shouldOverrideUrlLoading(WebView view,
								final String url)
			{
				//���Ӵ��������¼��ر�ǩ
				if(isreloadlink(url))
				{
					if(reloadListener!=null)
						reloadListener.OnReload();
					return true;
				}
					
				//����վ����ת��
				ProcessSiteLink(c, url);
				return true;		
			}
			@Override
			public void onPageFinished(WebView view, String url)
			{
				super.onPageFinished(view, url);
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon)
			{

				super.onPageStarted(view, url, favicon);
			}
		});
	}
	
	public static void ProcessSiteLink(Context c,String url)
	{
		if(FileUtil.isImageFile(url))
		{
			Intent intent = new Intent(c, ViewImageActivity.class);
			intent.putExtra(ViewImageActivity.CSTR_EXTRA_IMAGE,url);
			c.startActivity(intent);
			return;
		}
		//TODO վ�����Ŵ���
		BaseActivity.runBrowser(url,c);
	}
	
	private static boolean isreloadlink(String url)
	{
		if(StringUtil.isEmpty(url))
			return false;
		return url.equals(CSTR_RELOADLINK);
	}
	
	/**
	 * ����Html��ͷ
	 * 
	 * @return <html><head><meta http-equiv="Content-Type"
	 *         content="text/html;charset=utf-8"> <script type="text/javascript"
	 *         language="javascript"> function fixImage(i,w,h) { var ow =
	 *         i.width;//ͼ�Ŀ�� var oh = i.height; //ͼ�ĸ߶� var rw = w/ow; var rh =
	 *         h/oh; var r = Math.min(rw,rh); if (w ==0 && h == 0) { r = 1;
	 *         }else if (w == 0) { r = rh<1?rh:1; }else if (h == 0) { r =
	 *         rw<1?rw:1; } if (ow!=0 && oh!=0) { i.width = ow * r; i.height =
	 *         oh * r; }else { var __method = this, args = $A(arguments);
	 *         window.setTimeout(function() { fixImage.apply(__method, args); },
	 *         200); } i.onload = function(){} } </script> </head><body>
	 */
	public static String getHtmlHead()
	{
		// ͼ����ʾ��Ļ��ȵ�80%
		String autoloadimgscript = "<script type=\"text/javascript\" language=\"javascript\"> "
				+ "function fixImage(i,w,h){  var cw=document.body.clientWidth; if(cw*0.9<w) w=cw*0.9; var ow = i.width;  "
				+ "var oh = i.height;  var rw = w/ow; var rh = h/oh;  var r = Math.min(rw,rh);  "
				+ "if (w ==0 && h == 0){  r = 1;  }else if (w == 0){  r = rh<1?rh:1;  }else if (h == 0){  r = rw<1?rw:1;  } "
				+ "if (ow!=0 && oh!=0){ i.width = ow * r;  i.height = oh * r;  } "
				+ "else{ var __method = this, args = $A(arguments);  window.setTimeout(function() {  "
				+ " fixImage.apply(__method, args); }, 200); }  i.onload = function(){}  }  </script>";
		return "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\">"
				+ autoloadimgscript + "<style type=\"text/css\">body {background-color:#fcf2e9;}</style></head><body>";
	}
	
	/**
	 * ���������ӱ��ⲿ��
	 * @param vo
	 * @return
	 */
	public static String getHtmlSubTitle(BaseVO vo)
	{
		if(vo==null)
			return "<div style=\"height:0;border-bottom:1px solid #f00\"></div>";
		return vo.getHtmlSubTitle();
	}
	
	/**
	 * ���ش�����ʾ
	 * @param msg ������ʾ��Ϣ
	 * @param hasreload �Ƿ���������ʾ 
	 * @return
	 */
	public static String getHtmlErrorHit(String msg,boolean hasreload)
	{
		String result = "<br/>&nbsp;&nbsp;�ܱ�Ǹ�������ʵ�����"+(StringUtil.isEmpty(msg)?"":"��Ϊ"+msg+"��ԭ���")+"�޷��������ء�";
		if(hasreload)
			result = result+"<a href=\""+CSTR_RELOADLINK+"\">�볥�����¼���</a>";
		return result;
	}
	

	/**
	 * ����HTML���ݣ�Ŀǰ�Ĳ����Ƕ�<img ����onload="fixImage(this,screenwidth,0)"
	 * 
	 * @param context
	 * @return
	 */
	@SuppressLint("DefaultLocale")
	public static String getHtmlContext(Activity activity, String context)
	{
		if (StringUtil.isEmpty(context))
			return context;
		String tmpStartStr = "<img ";
		int index = context.toLowerCase().indexOf(tmpStartStr);
		if (index == -1)
			return context;

		String tmpEndStr = "/>";// "/>"
		int screenwidth = ViewUtil.getScreenWidth(activity) - 10;
		String insertStr = " onload=\"fixImage(this," + screenwidth + ",0)\" ";
		StringBuilder sBuilder = new StringBuilder();
//		String tmpRemoveStartStr = "style=\"";
		String tmpRemoveEndStr = "\"";
		
		String tmpsrc=" src=";
		while (index != -1)
		{
			// ����tmpStartStr֮ǰ���ַ�
			sBuilder.append(context.substring(0, index));
			context = context.substring(index);
			index = context.indexOf(tmpEndStr);
			if (index == -1)
			{
				sBuilder.append(context);
				break;
			}
			String tmp = context.substring(0, index);
			int index_src=tmp.toLowerCase().indexOf(tmpsrc);
			String src="";
			if(index_src!=-1)
			{
				tmp = tmp.substring(index_src+tmpsrc.length()+1);
				int index2 = tmp.indexOf(tmpRemoveEndStr);
				if(index2!=-1)
				{
					src=tmp.substring(0,index2);
				}
				else
					src=tmp;
				if(!StringUtil.isEmpty(src))
				{
					if(src.startsWith(activity.getResources().getString(R.string.baseurl_pro))){
						src=activity.getResources().getString(R.string.baseurl)+src;
					}
					sBuilder.append(" <a href=\""+src+"\"><center><img src=\""+src+"\" "+insertStr+"/></center></a>");
				}
			}
			context = context.substring(index+tmpEndStr.length());
			index = context.toLowerCase().indexOf(tmpStartStr);
		}
		sBuilder.append(context);
		return sBuilder.toString();
	}

	/**
	 * ����Html��β
	 * 
	 * @return
	 */
	public static String getHtmlEnd()
	{
		return "<br/><br/></body></html>";
	}
}
