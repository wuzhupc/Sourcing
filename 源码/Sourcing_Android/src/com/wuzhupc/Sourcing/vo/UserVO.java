package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;

/**
 * �û�
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-17 ����7:57:52
 */
public class UserVO extends BaseVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2777041706952712371L;

	/**
	 * �û�����:�����û�
	 */
	public static final int USER_TYPE_PERSONAL = 1 ;
	/**
	 * �û�����:��ҵ�û�
	 */
	public static final int USER_TYPE_ENTERPRISE = 2 ;
	/**
	 * �û�����:��ѵ����
	 */
	public static final int USER_TYPE_TRAIN = 3 ;
	/**
	 * �û����
	 */
	//private long userid;
	/**
	 * �û�����,USER_TYPE_
	 */
	private int usertype;
	/**
	 * �û�����(�����ע��Ϊ��ҵ,�򷵻���ҵ����,��ѵ����,�򷵻���ѵ��������)
	 */
	private String username;
	/**
	 * �û�ͷ��
	 */
	private String userphoto;
	/**
	 * ��ϵ��ʽ
	 */
	private String phonenumber;
	/**
	 * �����ַ
	 */
	private String email;
	/**
	 * �û�δ�鿴����ѯ����Ϣ��,�����û����ͷ���
	 */
	private int consultcount;
	/**
	 * �û�δ�鿴����˽����Ϣ��,�����û����ͷ���
	 */
	private int auditcount;
	/**
	 * �û�δ�鿴���걨������Ϣ��,�����û����ͷ���
	 */
	private int declarecount;
	/**
	 * �û�δ�鿴��֪ͨ������Ϣ��,�����û����ͷ���
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
