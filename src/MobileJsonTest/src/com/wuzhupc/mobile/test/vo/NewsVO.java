package com.wuzhupc.mobile.test.vo;

import java.io.Serializable;;

public class NewsVO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1710599951087199906L;

	private boolean headline;
	
	private int newstype;
	
	private long newsid;
	
	private String title;
	
	private String titlepic;
	
	private String titlepic_small;
	
	private String newssummary;

	public int getNewstype()
	{
		return newstype;
	}

	public void setNewstype(int newstype)
	{
		this.newstype = newstype;
	}

	public boolean isHeadline()
	{
		return headline;
	}

	public void setHeadline(boolean headline)
	{
		this.headline = headline;
	}

	public long getNewsid()
	{
		return newsid;
	}

	public void setNewsid(long newsid)
	{
		this.newsid = newsid;
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

	public String getNewssummary()
	{
		return newssummary;
	}

	public void setNewssummary(String newssummary)
	{
		this.newssummary = newssummary;
	}
	
}
