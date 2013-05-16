package com.wuzhupc.utils;

import java.io.File;

import com.wuzhupc.Sourcing.vo.ChannelVO;
import com.wuzhupc.config.Constants;

/**
 * ������Ѷ��Ϣ��
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-29 ����8:29:24
 */
public class CacheUtil
{
	/**
	 * �����¼��Ϣ
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
	 * ��ȡ�����¼��Ϣ
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
