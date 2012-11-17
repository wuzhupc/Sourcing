package com.wuzhupc.Sourcing.vo;

import com.wuzhupc.utils.JavaLangUtil;

/**
 * 客户端版本信息VO
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
	 * 现在版本
	 */
	private String clientver;
	/**
	 * 客户端最新版本号
	 */
	private String lastver;
	/**
	 * 最后客户端版本下载地址
	 */
	private String lastverurl;
	/**
	 * 最新客户端文件大小，单位字节
	 */
	private long filesize;
	/**
	 * 是否需要强制更新(0.否 1.是)
	 */
	private boolean forceupdate;
	/**
	 * 更新说明
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
