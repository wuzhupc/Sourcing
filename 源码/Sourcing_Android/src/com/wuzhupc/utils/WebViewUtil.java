package com.wuzhupc.utils;

import com.wuzhupc.Sourcing.R;
import com.wuzhupc.widget.OnReloadListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.LayoutAlgorithm;

/**
 * 处理webview相关的内容
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-12-4 下午9:39:33
 */
public class WebViewUtil
{
	/**
	 * 重载链接标签
	 */
	public static final String CSTR_RELOADLINK = "wuzhupc://reload";
	public static void setWebView(Context c,WebView webview,final OnReloadListener reloadListener)
	{
		if(c==null||webview==null)
			return;
		WebSettings websettings = webview.getSettings();
		// 1、LayoutAlgorithm.NARROW_COLUMNS ： 适应内容大小
        // 2、LayoutAlgorithm.SINGLE_COLUMN:适应屏幕，内容将自动缩放
		websettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		// 设置支持JavaScript
		webview.clearCache(true);
		websettings.setJavaScriptEnabled(true);
		//TODO 设置字体 websettings.setTextSize(SettingUtil.getNewsFontSize(c));
		webview.setWebViewClient(new WebViewClient()
		{
			// 解决点击跳转问题
			public boolean shouldOverrideUrlLoading(WebView view,
								final String url)
			{
				//增加处理点击重新加载标签
				if(isreloadlink(url))
				{
					if(reloadListener!=null)
						reloadListener.OnReload();
					return true;
				}
					
				//TODO 处理站内跳转等
				//SitelinkJsonService.ProcessSiteLink(devid, memberid, url,c);
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
	
	private static boolean isreloadlink(String url)
	{
		if(StringUtil.isEmpty(url))
			return false;
		return url.equals(CSTR_RELOADLINK);
			
	}
	
	/**
	 * 返回Html开头
	 * 
	 * @return <html><head><meta http-equiv="Content-Type"
	 *         content="text/html;charset=utf-8"> <script type="text/javascript"
	 *         language="javascript"> function fixImage(i,w,h) { var ow =
	 *         i.width;//图的宽度 var oh = i.height; //图的高度 var rw = w/ow; var rh =
	 *         h/oh; var r = Math.min(rw,rh); if (w ==0 && h == 0) { r = 1;
	 *         }else if (w == 0) { r = rh<1?rh:1; }else if (h == 0) { r =
	 *         rw<1?rw:1; } if (ow!=0 && oh!=0) { i.width = ow * r; i.height =
	 *         oh * r; }else { var __method = this, args = $A(arguments);
	 *         window.setTimeout(function() { fixImage.apply(__method, args); },
	 *         200); } i.onload = function(){} } </script> </head><body>
	 */
	public static String getHtmlHead()
	{
		// 图像显示屏幕宽度的80%
		String autoloadimgscript = "<script type=\"text/javascript\" language=\"javascript\"> "
				+ "function fixImage(i,w,h){  var cw=document.body.clientWidth; if(cw*0.9<w) w=cw*0.9; var ow = i.width;  "
				+ "var oh = i.height;  var rw = w/ow; var rh = h/oh;  var r = Math.min(rw,rh);  "
				+ "if (w ==0 && h == 0){  r = 1;  }else if (w == 0){  r = rh<1?rh:1;  }else if (h == 0){  r = rw<1?rw:1;  } "
				+ "if (ow!=0 && oh!=0){ i.width = ow * r;  i.height = oh * r;  } "
				+ "else{ var __method = this, args = $A(arguments);  window.setTimeout(function() {  "
				+ " fixImage.apply(__method, args); }, 200); }  i.onload = function(){}  }  </script>";
		return "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\">"
				+ autoloadimgscript + "</head><body>";
	}

	/**
	 * 返回HTML内容，目前的操作是对<img 增加onload="fixImage(this,screenwidth,0)"
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
			// 增加tmpStartStr之前的字符
			sBuilder.append(context.substring(0, index));
			context = context.substring(index);
			index = context.indexOf(tmpEndStr);
			if (index == -1)
			{
				sBuilder.append(context);
				break;
			}
			String tmp = context.substring(0, index);
			int index_src=tmp.indexOf(tmpsrc);
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
					if(src.startsWith(activity.getResources().getString(R.string.image_servername))){
						src=activity.getResources().getString(R.string.image_linkpre)+src;
					}
					sBuilder.append(" <a href=\""+src+"\"><center><img src=\""+src+"\" "+insertStr+"/></center></a>");
				}
			}
			context = context.substring(index+tmpEndStr.length());
			index = context.toLowerCase().indexOf(tmpStartStr);
		}
//		while (index != -1)
//		{
//			// 增加tmpStartStr之前的字符
//			sBuilder.append(context.substring(0, index));
//			context = context.substring(index);
//			index = context.indexOf(tmpEndStr);
//			if (index == -1)
//			{
//				sBuilder.append(context);
//				break;
//			}
//			String tmp = context.substring(0, index);
//			// 去除移除字符
//			int index2 = tmp.indexOf(tmpRemoveStartStr);
//			if (index2 != -1)
//			{
//				sBuilder.append(tmp.substring(0, index2));
//				tmp = tmp.substring(index2);
//				index2 = tmp.indexOf(tmpRemoveEndStr);
//				if (index2 != -1 && index2 != tmp.length() - 1)
//				{
//					sBuilder.append(index2 + 1);
//				}
//
//			} else
//				sBuilder.append(tmp); // 增加tmpStartStr之后到tmpEndStr的字符
//			// 增加insertStr
//			sBuilder.append(insertStr);
//			//
//			context = context.substring(index);
//			index = context.toLowerCase().indexOf(tmpStartStr);
//		}
		sBuilder.append(context);
		return sBuilder.toString();
	}

	/**
	 * 返回Html结尾
	 * 
	 * @return
	 */
	public static String getHtmlEnd()
	{
		return "</body></html>";
	}
}
