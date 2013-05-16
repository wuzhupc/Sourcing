package com.wuzhupc.Sourcing.vo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.util.Log;

import com.wuzhupc.config.Constants;
import com.wuzhupc.utils.FileUtil;
import com.wuzhupc.utils.JavaLangUtil;

/**
 * 用户
 * 
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-17 下午7:57:52
 */
public class UserVO extends BaseVO
{
	protected static final String TAG = UserVO.class.getSimpleName();
	/**
	 * 
	 */
	private static final long serialVersionUID = -2777041706952712371L;

	/**
	 * 用户类型:个人用户
	 */
	public static final int USER_TYPE_PERSONAL = 1;
	/**
	 * 用户类型:专家用户
	 */
	public static final int USER_TYPE_EXPERT = 2;
	/**
	 * 用户类型:企业用户
	 */
	public static final int USER_TYPE_ENTERPRISE = 3;
	/**
	 * 用户类型:培训机构
	 */
	public static final int USER_TYPE_TRAIN = 4;
	/**
	 * 用户编号
	 */
	// private long userid;
	/**
	 * 用户类型,USER_TYPE_
	 */
	private int usertype;

	/**
	 * 用户登录帐号
	 */
	private String useraccount;
	/**
	 * 用户密码
	 */
	private String password;
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
	/**
	 * 用户总咨询答复信息数,根据用户类型返回
	 */
	private int allconsultcount;
	/**
	 * 用户总审核结果信息数,根据用户类型返回
	 */
	private int allauditcount;
	/**
	 * 用户总申报进度信息数,根据用户类型返回
	 */
	private int alldeclarecount;
	/**
	 * 用户总通知提醒信息数,根据用户类型返回
	 */
	private int allnotifiercount;

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
		setUserid(JavaLangUtil.StrToLong(userid, -1l));
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
		this.usertype = JavaLangUtil.StrToInteger(usertype, USER_TYPE_PERSONAL);
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
		this.consultcount = JavaLangUtil.StrToInteger(consultcount, 0);
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
		this.auditcount = JavaLangUtil.StrToInteger(auditcount, 0);
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
		this.declarecount = JavaLangUtil.StrToInteger(declarecount, 0);
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
		this.notifiercount = JavaLangUtil.StrToInteger(notifiercount, 0);
	}

	public String getUseraccount()
	{
		return useraccount;
	}

	public void setUseraccount(String useraccount)
	{
		this.useraccount = useraccount;
	}

	public int getAllconsultcount()
	{
		return allconsultcount;
	}

	public void setAllconsultcount(int allconsultcount)
	{
		this.allconsultcount = allconsultcount;
	}

	public void setAllconsultcount(String allconsultcount)
	{
		this.allconsultcount = JavaLangUtil.StrToInteger(allconsultcount, 0);
	}

	public int getAllauditcount()
	{
		return allauditcount;
	}

	public void setAllauditcount(int allauditcount)
	{
		this.allauditcount = allauditcount;
	}

	public void setAllauditcount(String allauditcount)
	{
		this.allauditcount = JavaLangUtil.StrToInteger(allauditcount, 0);
	}

	public int getAlldeclarecount()
	{
		return alldeclarecount;
	}

	public void setAlldeclarecount(int alldeclarecount)
	{
		this.alldeclarecount = alldeclarecount;
	}

	public void setAlldeclarecount(String alldeclarecount)
	{
		this.alldeclarecount = JavaLangUtil.StrToInteger(alldeclarecount, 0);
	}

	public int getAllnotifiercount()
	{
		return allnotifiercount;
	}

	public void setAllnotifiercount(int allnotifiercount)
	{
		this.allnotifiercount = allnotifiercount;
	}

	public void setAllnotifiercount(String allnotifiercount)
	{
		this.allnotifiercount = JavaLangUtil.StrToInteger(allnotifiercount, 0);
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * 存储用户登录信息--存储文件路径
	 */
	private static final String CSTR_DATAFILE_USER = Constants.CSTR_DATASTOREDIR
			+ "datafile_u.dat";

	/**
	 * 存储最后登录信息
	 * 
	 * @param vo
	 *            如果为空，则清除之前的登录记录
	 * @return
	 */
	public static boolean saveLoginUserInfo(UserVO vo)
	{
		if (vo == null)
		{
			if (FileUtil.isExistFile(CSTR_DATAFILE_USER))
			{
				return FileUtil.deleteFile(CSTR_DATAFILE_USER);
			}
			return true;
		}
		boolean result = false;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try
		{
			fos = new FileOutputStream(CSTR_DATAFILE_USER);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(vo);
			oos.flush();
			result = true;
		} catch (Exception e)
		{
			Log.e(TAG, e.getMessage());
		} finally
		{
			if (oos != null)
			{
				try
				{
					oos.close();
					oos = null;
				} catch (Exception e2)
				{
					Log.e(TAG, e2.getMessage());
				}
			}
			if (fos != null)
			{
				try
				{
					fos.close();
					fos = null;
				} catch (Exception e2)
				{
					Log.e(TAG, e2.getMessage());
				}
			}
		}
		return result;
	}

	/**
	 * 获取最后登录的信息数据
	 * 
	 * @return
	 */
	public static UserVO getLastLoginUserInfo()
	{
		UserVO vo = null;
		if (FileUtil.isExistFile(CSTR_DATAFILE_USER))
		{
			FileInputStream fis = null;
			ObjectInputStream ois = null;
			try
			{
				fis = new FileInputStream(CSTR_DATAFILE_USER);
				ois = new ObjectInputStream(fis);
				vo = (UserVO) ois.readObject();
			} catch (Exception e)
			{
				Log.e(TAG, e.getMessage());
			} finally
			{
				if (ois != null)
				{
					try
					{
						ois.close();
						ois = null;
					} catch (Exception e2)
					{
						Log.e(TAG, e2.getMessage());
					}
				}
				if (fis != null)
				{
					try
					{
						fis.close();
						fis = null;
					} catch (Exception e2)
					{
						Log.e(TAG, e2.getMessage());
					}
				}
			}
		}
		return vo;
	}

	public String getStrUserType()
	{
		switch (usertype)
		{
			case USER_TYPE_PERSONAL:
				return "个人用户";
			case USER_TYPE_EXPERT:
				return "专家用户";
			case USER_TYPE_ENTERPRISE:
				return "企业用户";
			case USER_TYPE_TRAIN:
				return "培训机构";
			default:
				return "";
		}
	}

	/**
	 * 是否有咨询信息权限
	 * 
	 * @return
	 */
	public boolean hasConsult()
	{
		switch (usertype)
		{
			case USER_TYPE_PERSONAL:
				return true;
			case USER_TYPE_EXPERT:
				return true;
			case USER_TYPE_ENTERPRISE:
				return true;
			case USER_TYPE_TRAIN:
				return true;
			default:
				return true;
		}
	}

	/**
	 * 是否有审核结果权限
	 * 
	 * @return
	 */
	public boolean hasAudit()
	{
		switch (usertype)
		{
			case USER_TYPE_PERSONAL:
				return false;
			case USER_TYPE_EXPERT:
				return true;
			case USER_TYPE_ENTERPRISE:
				return true;
			case USER_TYPE_TRAIN:
				return true;
			default:
				return true;
		}
	}

	/**
	 * 是否有申报进度权限
	 * 
	 * @return
	 */
	public boolean hasDeclare()
	{
		switch (usertype)
		{
			case USER_TYPE_PERSONAL:
				return false;
			case USER_TYPE_EXPERT:
				return false;
			case USER_TYPE_ENTERPRISE:
				return true;
			case USER_TYPE_TRAIN:
				return true;
			default:
				return true;
		}
	}

	/**
	 * 是否有通知提醒权限
	 * 
	 * @return
	 */
	public boolean hasNotifier()
	{
		switch (usertype)
		{
			case USER_TYPE_PERSONAL:
				return true;
			case USER_TYPE_EXPERT:
				return true;
			case USER_TYPE_ENTERPRISE:
				return true;
			case USER_TYPE_TRAIN:
				return true;
			default:
				return true;
		}
	}

}
