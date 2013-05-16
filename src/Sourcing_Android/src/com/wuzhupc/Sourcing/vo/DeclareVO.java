package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;


/**
 * �걨��Ϣ
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-17 ����8:46:32
 */
public class DeclareVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2279845658915038666L;
	/**
	 * �걨���
	 */
	//private long declareid;
	/**
	 * �걨����
	 */
	private String declarecontent;
	/**
	 * �걨ʱ�䣬��ʽyyyy-MM-dd HH:mm:ss
	 */
	private String publishtime;
	/**
	 * �걨�����Ϣ,������걨���,��ֵΪnull
	 */
	private DeclareResultVO declareResultVO;
	
	public String getDeclareStatus()
	{
		return  declareResultVO==null?"�ȴ�������":declareResultVO.getDeclareStatus();	
	}
	
	public String getPublishtime()
	{
		return publishtime;
	}
	public void setPublishtime(String publishtime)
	{
		this.publishtime = publishtime;
	}
	public long getDeclareid()
	{
		return id;
	}
	public void setDeclareid(long declareid)
	{
		this.id = declareid;
	}
	public void setDeclareid(String declareid)
	{
		setDeclareid(JavaLangUtil.StrToLong(declareid,-1l));
	}
	public String getDeclarecontent()
	{
		return declarecontent;
	}
	public void setDeclarecontent(String declarecontent)
	{
		this.declarecontent = declarecontent;
	}
	public DeclareResultVO getDeclareResultVO()
	{
		return declareResultVO;
	}
	public void setDeclareResultVO(DeclareResultVO declareResultVO)
	{
		this.declareResultVO = declareResultVO;
	}
	
}
