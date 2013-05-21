package com.wuzhupc.Sourcing.vo;

import java.util.Date;

import android.content.Context;

import com.wuzhupc.config.Constants;
import com.wuzhupc.services.MobileInfoService;
import com.wuzhupc.services.BaseJsonService.IBaseReceiver;
import com.wuzhupc.utils.FileUtil;
import com.wuzhupc.utils.JavaLangUtil;
import com.wuzhupc.utils.StringUtil;
import com.wuzhupc.utils.TimeUtil;
import com.wuzhupc.utils.json.JsonParser;

/**
 * 资讯信息
 * 
 * @author Administrator
 * 
 */
public class NewsVO extends BaseVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2357502465951410596L;
	/**
	 * 新闻编号
	 */
	// private long newsid;
	/**
	 * 是否是新闻头条，默认0：不是头条，1：头条新闻
	 */
	private boolean headline;
	/**
	 * 新闻类型,1：行业新闻，2：政策，3：通知，4：专家文章
	 */
	private int newstype;
	/**
	 * 新闻标题
	 */
	private String title;
	/**
	 * 题图 URL
	 */
	private String titlepic;
	/**
	 * 题图缩略图URL
	 */
	private String titlepic_small;
	/**
	 * 新闻发表日期，格式yyyy-MM-dd HH:mm:ss
	 */
	private String publishtime;
	/**
	 * 新闻信息摘要
	 */
	private String newssummary;
	/**
	 * 新闻作者
	 */
	private String auther;
	/**
	 * 新闻来源
	 */
	private String source;
	/**
	 * 阅读次数
	 */
	private int readernum;
	/**
	 * 评论总数
	 */
	private int commentnum;

	public long getNewsid() {
		return id;
	}

	public void setNewsid(long newsid) {
		this.id = newsid;
	}

	public void setNewsid(String newsid) {
		setNewsid(JavaLangUtil.StrToLong(newsid, -1l));
	}

	public boolean isHeadline() {
		return headline;
	}

	public void setHeadline(boolean headline) {
		this.headline = headline;
	}

	public void setHeadline(String headline) {
		this.headline = JavaLangUtil.StrToBool(headline, false);
	}

	public int getNewstype() {
		return newstype;
	}

	public void setNewstype(int newstype) {
		this.newstype = newstype;
	}

	public void setNewstype(String newstype) {
		this.newstype = JavaLangUtil.StrToInteger(newstype,
				ChannelVO.getDefaultNewsType());
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitlepic() {
		return titlepic;
	}

	public void setTitlepic(String titlepic) {
		this.titlepic = titlepic;
	}

	public String getTitlepic_small() {
		return titlepic_small;
	}

	public void setTitlepic_small(String titlepic_small) {
		this.titlepic_small = titlepic_small;
	}

	public String getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}

	public String getNewssummary() {
		return newssummary;
	}

	public void setNewssummary(String newssummary) {
		this.newssummary = newssummary;
	}

	public String getAuther() {
		return auther;
	}

	public void setAuther(String auther) {
		this.auther = auther;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getReadernum() {
		return readernum;
	}

	public void setReadernum(int readernum) {
		this.readernum = readernum;
	}

	public void setReadernum(String readernum) {
		this.readernum = JavaLangUtil.StrToInteger(readernum, 0);
	}

	public int getCommentnum() {
		return commentnum;
	}

	public void setCommentnum(int commentnum) {
		this.commentnum = commentnum;
	}

	public void setCommentnum(String commentnum) {
		this.commentnum = JavaLangUtil.StrToInteger(commentnum, 0);
		;
	}

	/**
	 * 返回详情HTML标题
	 * 
	 * @return
	 */
	@Override
	public String getHtmlTitle() {
		String result = "<br/><div align=\"center\"><font color=\"#111111\" size=\"4pt\"><strong>"
				+ title + "</strong></font></div><br/>";
		return result;
	}

	/**
	 * 生成分享信息内容部分
	 */
	@Override
	public String generateShareText() {
		return getTitle();
	}

	/**
	 * 返回详情HTML子标题
	 * 
	 * @return
	 */
	@Override
	public String getHtmlSubTitle() {
		String result = "<div align=\"center\"><font color=\"#666666\" size=\"2pt\">"
				+ getPublishtime()
				+ "&nbsp;&nbsp; 来源:"
				+ getSource()
				+ "</font></div><div style=\"height:0;border-bottom:1px solid #f00\"></div>";
		return result;
	}

	/**
	 * 设置内容Html内容显示
	 */
	@Override
	public void setHtmlToShow(Context c,
			final DetailInfoListener detailInfoListener) {
		if (detailInfoListener == null)
			return;
		//读取缓存
		String cachecontent = FileUtil.readContent(getCacheFileName());
		if(!StringUtil.isEmpty(cachecontent))
		{
			ResponseVO respVO = new ResponseVO();
			NewsDetailVO detailVO = (NewsDetailVO) JsonParser
					.parseJsonToEntity(cachecontent, respVO);
			if (respVO.isSucess())
			{
				detailInfoListener.onComplete(detailVO);
				return;
			}
			
		}
		MobileInfoService infoService = new MobileInfoService(c);
		infoService.getNewsDetail(getNewstype(), getNewsid(),
				new IBaseReceiver() {
					@Override
					public void receiveCompleted(boolean isSuc, String content) {
						if (!isSuc) {
							detailInfoListener.onError(content);
							return;
						}
						ResponseVO respVO = new ResponseVO();
						NewsDetailVO detailVO = (NewsDetailVO) JsonParser
								.parseJsonToEntity(content, respVO);
						if (!respVO.isSucess()) {
							detailInfoListener.onError(respVO.getMsg());
							return;
						}
						FileUtil.saveContent(getCacheFileName(), content);
						detailInfoListener.onComplete(detailVO);
					}
				});
	}

	public String getCacheFileName() {
		return Constants.CSTR_DATASTOREDIR
				+ Constants.CSTR_DETAIL_DIR
				+ "nd"
				+ this.getNewsid()
				+ "_"
				+ TimeUtil.dateToString(
						TimeUtil.strToDate(this.getPublishtime(), new Date()),
						"yyyyMMddHHmmss")
				+".cache";
	}
}
