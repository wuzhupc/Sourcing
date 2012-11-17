package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;

/**
 * �ͻ��˰汾��ϢVO
 * @author Administrator
 *
 */
public class ClientVerVO extends BaseVO
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6380397370847413254L;
	
	/**
	 * ���ڰ汾
	 */
	private String clientver;
	/**
	 * �ͻ������°汾��
	 */
	private String lastver;
	/**
	 * ���ͻ��˰汾���ص�ַ
	 */
	private String lastverurl;
	/**
	 * ���¿ͻ����ļ���С����λ�ֽ�
	 */
	private long filesize;
	/**
	 * �Ƿ���Ҫǿ�Ƹ���(0.�� 1.��)
	 */
	private boolean forceupdate;
	/**
	 * ����˵��
	 */
	private String updatelog;
	public String getClientver()
	{
		return clientver;
	}
	public void setClientver(String clientver)
	{
		this.clientver = clientver;
	}
	public String getLastver()
	{
		return lastver;
	}
	public void setLastver(String lastver)
	{
		this.lastver = lastver;
	}
	public String getLastverurl()
	{
		return lastverurl;
	}
	public void setLastverurl(String lastverurl)
	{
		this.lastverurl = lastverurl;
	}
	public long getFilesize()
	{
		return filesize;
	}
	public void setFilesize(long filesize)
	{
		this.filesize = filesize;
	}
	public void setFilesize(String filesize)
	{
		this.filesize = JavaLangUtil.StrToLong(filesize, 0l);
	}
	public boolean getForceupdate()
	{
		return forceupdate;
	}
	public void setForceupdate(boolean forceupdate)
	{
		this.forceupdate = forceupdate;
	}
	public void setForceupdate(String forceupdate)
	{
		this.forceupdate = JavaLangUtil.StrToBool(forceupdate, false);
	}
	public String getUpdatelog()
	{
		return updatelog;
	}
	public void setUpdatelog(String updatelog)
	{
		this.updatelog = updatelog;
	}
}
