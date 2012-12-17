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
 * �û�
 * 
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-17 ����7:57:52
 */
public class UserVO extends BaseVO
{
	protected static final String TAG = UserVO.class.getSimpleName();
	/**
	 * 
	 */
	private static final long serialVersionUID = -2777041706952712371L;

	/**
	 * �û�����:�����û�
	 */
	public static final int USER_TYPE_PERSONAL = 1;
	/**
	 * �û�����:ר���û�
	 */
	public static final int USER_TYPE_EXPERT = 2;
	/**
	 * �û�����:��ҵ�û�
	 */
	public static final int USER_TYPE_ENTERPRISE = 3;
	/**
	 * �û�����:��ѵ����
	 */
	public static final int USER_TYPE_TRAIN = 4;
	/**
	 * �û����
	 */
	// private long userid;
	/**
	 * �û�����,USER_TYPE_
	 */
	private int usertype;
	
	/**
	 * �û���¼�ʺ�
	 */
	private String useraccount;
	/**
	 * �û�����
	 */
	private String password;
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

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * �洢�û���¼��Ϣ--�洢�ļ�·��
	 */
	private static final String CSTR_DATAFILE_USER = Constants.CSTR_DATASTOREDIR
			+ "datafile_u.dat";
	
	/**
	 * �洢����¼��Ϣ
	 * @param vo ���Ϊ�գ������֮ǰ�ĵ�¼��¼
	 * @return
	 */
	public static boolean saveLoginUserInfo(UserVO vo)
	{
		if(vo==null)
		{
			if (FileUtil.isExistFile(CSTR_DATAFILE_USER))
			{
				return FileUtil.deleteFile(CSTR_DATAFILE_USER);
			}
			return true;
		}
		boolean result =false;
		FileOutputStream fos= null;
		ObjectOutputStream oos = null;
		try
		{
			fos = new FileOutputStream(CSTR_DATAFILE_USER);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(vo);
			oos.flush();
			result=true;
		} catch (Exception e)
		{
			Log.e(TAG, e.getMessage());
		} finally
		{
			if(oos!=null)
			{
				try
				{
					oos.close();
					oos=null;
				} catch (Exception e2)
				{
					Log.e(TAG, e2.getMessage());
				}
			}
			if(fos!=null)
			{
				try
				{
					fos.close();
					fos=null;
				} catch (Exception e2)
				{
					Log.e(TAG, e2.getMessage());
				}
			}
		}
		return result;
	}

	/**
	 * ��ȡ����¼����Ϣ����
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
				vo = (UserVO)ois.readObject();
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
						ois=null;
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
						fis=null;
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
			return "�����û�";
		case USER_TYPE_EXPERT:
			return "ר���û�";
		case USER_TYPE_ENTERPRISE:
			return "��ҵ�û�";
		case USER_TYPE_TRAIN:
			return "�����û�";
		default:
			return "";
		}
	}
}
