package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;

/**
 * ��ѵ����
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-17 ����7:45:06
 */
public class TrainVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9193391814832088770L;
	/**
	 * ��ѵ�������
	 */
	//private long trainid;
	/**
	 * ��ѵ��������
	 */
	private String trainname;
	public long getTrainid()
	{
		return id;
	}
	public void setTrainid(long trainid)
	{
		this.id = trainid;
	}
	public void setTrainid(String trainid)
	{
		setTrainid(JavaLangUtil.StrToLong(trainid,-1l));
	}
	public String getTrainname()
	{
		return trainname;
	}
	public void setTrainname(String trainname)
	{
		this.trainname = trainname;
	}
}
