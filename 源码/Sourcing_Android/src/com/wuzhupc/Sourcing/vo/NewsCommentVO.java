package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;

/**
 * ��Ѷ������Ϣ
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-17 ����6:45:51
 */
public class NewsCommentVO extends BaseVO
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2465779391020740285L;
	/**
	 * ���ű��
	 */
	//private long newsid;
	/**
	 * ��������,1����ҵ���ţ�2�����ߣ�3��֪ͨ��4��ר������
	 */
	private int newstype;
	/**
	 * ���۱��
	 */
	private long newscommentid;
	/**
	 * ������
	 */
	private String commenter;
	/**
	 * ���۷���ʱ�䣬��ʽyyyy-MM-dd HH:mm:ss
	 */
	private String commenttime;
	/**
	 * ��������
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
