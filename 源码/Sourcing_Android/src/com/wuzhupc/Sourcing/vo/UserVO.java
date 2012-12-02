package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;

/**
 * 用户
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-17 下午7:57:52
 */
public class UserVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2777041706952712371L;

	/**
	 * 用户类型:个人用户
	 */
	public static final int USER_TYPE_PERSONAL = 1 ;
	/**
	 * 用户类型:企业用户
	 */
	public static final int USER_TYPE_ENTERPRISE = 2 ;
	/**
	 * 用户类型:培训机构
	 */
	public static final int USER_TYPE_TRAIN = 3 ;
	/**
	 * 用户编号
	 */
	//private long userid;
	/**
	 * 用户类型,USER_TYPE_
	 */
	private int usertype;
	/**
	 * 用户名称(如果是注册为企业,则返回企业名称,培训机构,则返回培训机构名称)
	 */
	private String username;
	/**
	 * 用户头像
	 */
	private String userphoto;
	/**
	 * 联系方式
	 */
	private String phonenumber;
	/**
	 * 邮箱地址
	 */
	private String email;
	/**
	 * 用户未查看的咨询答复信息数,根据用户类型返回
	 */
	private int consultcount;
	/**
	 * 用户未查看的审核结果信息数,根据用户类型返回
	 */
	private int auditcount;
	/**
	 * 用户未查看的申报进度信息数,根据用户类型返回
	 */
	private int declarecount;
	/**
	 * 用户未查看的通知提醒信息数,根据用户类型返回
	 */
	private int notifiercount;

	public long getUserid()
	{
		return id;
	}

	public void setUserid(long userid)
	{
		this.id = userid;
	}

	public void setUserid(String userid)
	{
		setUserid(JavaLangUtil.StrToLong(userid,-1l));
	}

	public int getUsertype()
	{
		return usertype;
	}

	public void setUsertype(int usertype)
	{
		this.usertype = usertype;
	}

	public void setUsertype(String usertype)
	{
		this.usertype = JavaLangUtil.StrToInteger(usertype,USER_TYPE_PERSONAL);
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getUserphoto()
	{
		return userphoto;
	}

	public void setUserphoto(String userphoto)
	{
		this.userphoto = userphoto;
	}

	public String getPhonenumber()
	{
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber)
	{
		this.phonenumber = phonenumber;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public int getConsultcount()
	{
		return consultcount;
	}

	public void setConsultcount(String consultcount)
	{
		this.consultcount = JavaLangUtil.StrToInteger(consultcount,0);
	}

	public void setConsultcount(int consultcount)
	{
		this.consultcount = consultcount;
	}

	public int getAuditcount()
	{
		return auditcount;
	}

	public void setAuditcount(int auditcount)
	{
		this.auditcount = auditcount;
	}

	public void setAuditcount(String auditcount)
	{
		this.auditcount = JavaLangUtil.StrToInteger(auditcount,0);
	}

	public int getDeclarecount()
	{
		return declarecount;
	}

	public void setDeclarecount(int declarecount)
	{
		this.declarecount = declarecount;
	}

	public void setDeclarecount(String declarecount)
	{
		this.declarecount = JavaLangUtil.StrToInteger(declarecount,0);
	}

	public int getNotifiercount()
	{
		return notifiercount;
	}

	public void setNotifiercount(int notifiercount)
	{
		this.notifiercount = notifiercount;
	}

	public void setNotifiercount(String notifiercount)
	{
		this.notifiercount = JavaLangUtil.StrToInteger(notifiercount,0);
	}
	
}
