package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;

/**
 * 培训机构
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-17 下午7:45:06
 */
public class TrainVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9193391814832088770L;
	/**
	 * 培训机构编号
	 */
	//private long trainid;
	/**
	 * 培训机构名称
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
