package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;

/**
 * ��Ѷ��Ϣ
 * @author Administrator
 *
 */
public class NewsVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2357502465951410596L;
	/**
	 * ���ű��
	 */
	//private long newsid;
	/**
	 * �Ƿ�������ͷ����Ĭ��0������ͷ����1��ͷ������
	 */
	private boolean headline;
	/**
	 * ��������,1����ҵ���ţ�2�����ߣ�3��֪ͨ��4��ר������
	 */
	private int newstype;
	/**
	 * ���ű���
	 */
	private String title;
	/**
	 * ��ͼ URL
	 */
	private String titlepic;
	/**
	 * ��ͼ����ͼURL
	 */
	private String titlepic_small;
	/**
	 * ���ŷ������ڣ���ʽyyyy-MM-dd HH:mm:ss
	 */
	private String publishtime;
	/**
	 * ������ϢժҪ
	 */
	private String newssummary;
	/**
	 * ��������
	 */
	private String auther;
	/**
	 * ������Դ
	 */
	private String source;
	/**
	 * �Ķ�����
	 */
	private int readernum;
	/**
	 * ��������
	 */
	private int commentnum;
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
	public boolean isHeadline()
	{
		return headline;
	}
	public void setHeadline(boolean headline)
	{
		this.headline = headline;
	}
	public void setHeadline(String headline)
	{
		this.headline = JavaLangUtil.StrToBool(headline, false);
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
		this.newstype = JavaLangUtil.StrToInteger(newstype, ChannelVO.getDefaultNewsType());
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getTitlepic()
	{
		return titlepic;
	}
	public void setTitlepic(String titlepic)
	{
		this.titlepic = titlepic;
	}
	public String getTitlepic_small()
	{
		return titlepic_small;
	}
	public void setTitlepic_small(String titlepic_small)
	{
		this.titlepic_small = titlepic_small;
	}
	public String getPublishtime()
	{
		return publishtime;
	}
	public void setPublishtime(String publishtime)
	{
		this.publishtime = publishtime;
	}
	public String getNewssummary()
	{
		return newssummary;
	}
	public void setNewssummary(String newssummary)
	{
		this.newssummary = newssummary;
	}
	public String getAuther()
	{
		return auther;
	}
	public void setAuther(String auther)
	{
		this.auther = auther;
	}
	public String getSource()
	{
		return source;
	}
	public void setSource(String source)
	{
		this.source = source;
	}
	public int getReadernum()
	{
		return readernum;
	}
	public void setReadernum(int readernum)
	{
		this.readernum = readernum;
	}
	public void setReadernum(String readernum)
	{
		this.readernum = JavaLangUtil.StrToInteger(readernum,0);
	}
	public int getCommentnum()
	{
		return commentnum;
	}
	public void setCommentnum(int commentnum)
	{
		this.commentnum = commentnum;
	}
	public void setCommentnum(String commentnum)
	{
		this.commentnum = JavaLangUtil.StrToInteger(commentnum,0);;
	}
}
