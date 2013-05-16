package com.wuzhupc.utils;

import java.io.File;

import com.wuzhupc.Sourcing.vo.ChannelVO;
import com.wuzhupc.config.Constants;

/**
 * 缓存资讯信息类
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-29 下午8:29:24
 */
public class CacheUtil
{
	/**
	 * 缓存记录信息
	 * @param vo
	 * @return
	 */
	public static boolean cacheContent(ChannelVO vo,String content)
	{
		if(vo==null||StringUtil.isEmpty(content))
			return false;
		String filename=Constants.CSTR_DATASTOREDIR+Constants.CSTR_DETAIL_DIR
				+vo.getFatherchannelID()+File.separator+vo.getChannelID()+".cache";
		FileUtil.saveContent(filename, content);
		return true;
	}
	
	/**
	 * 获取缓存记录信息
	 * @param vo
	 * @return
	 */
	public static String getCacheContent(ChannelVO vo)
	{
		if(vo==null)
			return "";
		String filename=Constants.CSTR_DATASTOREDIR+Constants.CSTR_DETAIL_DIR
				+vo.getFatherchannelID()+File.separator+vo.getChannelID()+".cache";
		return FileUtil.ReadFile(filename);
	}
}
