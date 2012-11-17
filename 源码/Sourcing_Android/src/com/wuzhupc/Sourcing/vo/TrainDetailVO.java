package com.wuzhupc.Sourcing.vo;

/**
 * 
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-17 下午7:46:54
 */
public class TrainDetailVO extends TrainVO
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8243877338589212730L;
	
	private String traincontent;

	public String getTraincontent()
	{
		return traincontent;
	}

	/**
	 * 培训机构介绍内容信息，直接将培训机构相关的信息返回
	 * @param traincontent
	 */
	public void setTraincontent(String traincontent)
	{
		this.traincontent = traincontent;
	}

}
