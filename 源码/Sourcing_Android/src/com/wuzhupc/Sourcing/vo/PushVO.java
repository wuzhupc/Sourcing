package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;

/**
 * 
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-17 ����8:58:12
 */
public class PushVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1104767207235439018L;
	/**
	 * ������Ϣ����,0 ��ͨ��Ѷ��Ϣ 1 ֪ͨ���� 2 ��˽�� 3 �걨���� 4,PUSH_TYPE_
	 */
	private int pushtype;
	/**
	 * ������Ϣ����
	 */
	private String title;
	/**
	 * ������Ϣ��ϸ����ID,�����ֻ��˸���������Ϣ���ͽ�һ����ȡ��Ӧ��Ϣ������
	 */
	private long id;
	public int getPushtype()
	{
		return pushtype;
	}
	public void setPushtype(int pushtype)
	{
		this.pushtype = pushtype;
	}
	public void setPushtype(String pushtype)
	{
		this.pushtype = JavaLangUtil.StrToInteger(pushtype, PUSH_TYPE_NORMAL);
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public void setId(String id)
	{
		this.id = JavaLangUtil.StrToLong(id,-1l);
	}
	
}
