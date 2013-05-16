package com.wuzhupc.Sourcing.vo;

import android.content.Context;

import com.wuzhupc.services.MobileInfoService;
import com.wuzhupc.services.BaseJsonService.IBaseReceiver;
import com.wuzhupc.utils.JavaLangUtil;
import com.wuzhupc.utils.json.JsonParser;

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
	
	/**
	 * ��������HTML����
	 * @return
	 */
	@Override
	public String getHtmlTitle()
	{
		String result = "<br/><div align=\"center\"><font color=\"#111111\" size=\"4pt\"><strong>"
				+title
				+"</strong></font></div><br/>";
		return result;
	}

	/**
	 * ���ɷ�����Ϣ���ݲ���
	 */
	@Override
	public String generateShareText()
	{
		return getTitle();
	}
	
	/**
	 * ��������HTML�ӱ���
	 * @return
	 */
	@Override
	public String getHtmlSubTitle()
	{
		String result = "<div align=\"center\"><font color=\"#666666\" size=\"2pt\">"+getPublishtime()+"&nbsp;&nbsp; ��Դ:"+getSource()+"</font></div><div style=\"height:0;border-bottom:1px solid #f00\"></div>";
		return result;
	}
	
	/**
	 * ��������Html������ʾ
	 */
	@Override
	public void setHtmlToShow(Context c, final DetailInfoListener detailInfoListener)
	{
		if(detailInfoListener==null)
			return;
		MobileInfoService infoService= new MobileInfoService(c);
		infoService.getNewsDetail(getNewstype(), getNewsid(), new IBaseReceiver()
		{
			@Override
			public void receiveCompleted(boolean isSuc, String content)
			{
				if (!isSuc)
				{
					detailInfoListener.onError(content);
					return;
				}
				ResponseVO respVO = new ResponseVO();
				NewsDetailVO mDetailVO = (NewsDetailVO)JsonParser.parseJsonToEntity(content, respVO);
				if(respVO.getCode()!=ResponseVO.RESPONSE_CODE_SUCESS)
				{
					detailInfoListener.onError(respVO.getMsg());
					return;
				} 
				if(detailInfoListener!=null)
					detailInfoListener.onComplete(mDetailVO);
			}
		});
	}
}
