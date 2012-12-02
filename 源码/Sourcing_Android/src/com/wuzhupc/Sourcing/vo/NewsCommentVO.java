package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;

/**
 * 资讯评论信息
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-17 下午6:45:51
 */
public class NewsCommentVO extends BaseVO
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2465779391020740285L;
	/**
	 * 新闻编号
	 */
	//private long newsid;
	/**
	 * 新闻类型,1：行业新闻，2：政策，3：通知，4：专家文章
	 */
	private int newstype;
	/**
	 * 评论编号
	 */
	private long newscommentid;
	/**
	 * 评论者
	 */
	private String commenter;
	/**
	 * 评论发表时间，格式yyyy-MM-dd HH:mm:ss
	 */
	private String commenttime;
	/**
	 * 评论内容
	 */
	private String commentcontent;
	public long getNewsid()
	{
		return id;
	}
	public void setNewsid(long newsid)
	{
		this.id = newsid;
	}
	public void setNewsid(String newsid)
	{
		setNewsid(JavaLangUtil.StrToLong(newsid, -1l));
	}
	public int getNewstype()
	{
		return newstype;
	}
	public void setNewstype(int newstype)
	{
		this.newstype = newstype;
	}
	public void setNewstype(String newstype)
	{
		this.newstype = JavaLangUtil.StrToInteger(newstype, ChannelVO.TYPE_NEWS_INDUSTR);
	}
	public long getNewscommentid()
	{
		return newscommentid;
	}
	public void setNewscommentid(long newscommentid)
	{
		this.newscommentid = newscommentid;
	}
	public void setNewscommentid(String newscommentid)
	{
		this.newscommentid = JavaLangUtil.StrToLong(newscommentid,-1l);
	}
	public String getCommenter()
	{
		return commenter;
	}
	public void setCommenter(String commenter)
	{
		this.commenter = commenter;
	}
	public String getCommenttime()
	{
		return commenttime;
	}
	public void setCommenttime(String commenttime)
	{
		this.commenttime = commenttime;
	}
	public String getCommentcontent()
	{
		return commentcontent;
	}
	public void setCommentcontent(String commentcontent)
	{
		this.commentcontent = commentcontent;
	}
	
}
