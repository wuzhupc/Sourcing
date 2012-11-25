package com.wuzhupc.Sourcing.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.content.Context;

import com.wuzhupc.utils.FileUtil;
import com.wuzhupc.utils.JavaLangUtil;
import com.wuzhupc.utils.StringUtil;
import com.wuzhupc.utils.json.JsonParser;

/**
 * ��Ŀ��ϢVO
 */
public class ChannelVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -802998074172715366L;
	/**
	 * ��ĿID
	 */
	private long channelID;	
	/**
	 * ��Ŀ����
	 */
	private String channelName = "";
	/**
	 * ����ĿID
	 */
	private long fatherchannelID;
	
	/**
	 * �Ƿ�ΪĬ����ʾ
	 */
	private int isdefault;
	/**
	 * ����˳��
	 */
	private int sort;
	
	/**
	 * ����
	 */
	private int type;
	
	/**
	 * �����͵���ĿID
	 */
	public static final int CHANNELID_FATHER = 0;
	
	/**
	 * �����ͣ���Ѷ
	 */
	public static final int TYPE_FATHER_NEWS = 1;
	
	/**
	 * �����ͣ��˲�
	 */
	public static final int TYPE_FATHER_PERSON = 2;
	
	/**
	 * �����ͣ��û�
	 */
	public static final int TYPE_FATHER_USER = 3;
	
	/**
	 * �����ͣ�����
	 */
	public static final int TYPE_FATHER_MORE = 4;
	
	/**
	 * ��Ѷ��Ŀ����-����
	 */
	public static final int TYPE_NEWS_NEWEST = 0;
	
	/**
	 * ��Ѷ��Ŀ����-��ҵ
	 */
	public static final int TYPE_NEWS_INDUSTR = 1;
	
	/**
	 * ��Ѷ��Ŀ����-����
	 */
	public static final int TYPE_NEWS_POLICY = 2;
	
	/**
	 * ��Ѷ��Ŀ����-֪ͨ
	 */
	public static final int TYPE_NEWS_NOTIFICATION = 3;
	
	/**
	 * ��Ѷ��Ŀ����-ר��
	 */
	public static final int TYPE_NEWS_EXPERT = 4;
	
	/**
	 * �˲���Ŀ���� -ְλ
	 */
	public static final int TYPE_PERSON_POSITION = 1;
	
	/**
	 * �˲���Ŀ���� -����
	 */
	public static final int TYPE_PERSON_RESUME = 2;
	
	/**
	 * �˲���Ŀ���� -��ѵ����
	 */
	public static final int TYPE_PERSON_TRAIN = 3;
	
	/**
	 * �˲���Ŀ���� -��Ŀ
	 */
	public static final int TYPE_PERSON_PROJECT = 4;
	
	/**
	 * ������Ŀ���ͣ��ղ�
	 */
	public static final int TYPE_MORE_FAV = 1;
	
	/**
	 * ������Ŀ���ͣ�ϵͳ����
	 */
	public static final int TYPE_MORE_SETTING = 2;
	
	/**
	 * ������Ŀ���ͣ��˳�
	 */
	public static final int TYPE_MORE_EXIT = 0;
	
	public int getIsDefault()
	{
		return isdefault;
	}
	
	public void setIsdefault(String value)
	{
		try
		{
			isdefault=Integer.parseInt(value);
		}
		catch(Exception e)
		{
			isdefault=0;
		}
	}
	
	public int getType()
	{
		return type;
	}
	
	public void setType(String value)
	{
		try
		{
			type=Integer.parseInt(value);
		}
		catch(Exception e)
		{
			type=0;
		}
	}
	
	public int getSort()
	{
		return sort;
	}
	
	public void setSort(String value)
	{
		try
		{
			sort=Integer.parseInt(value);
		}
		catch(Exception e)
		{
			sort=0;
		}
	}
	
	public long getChannelID(){
		return this.channelID;
	}
	
	public void setChannelID(String channelID){
		this.channelID = JavaLangUtil.StrToLong(channelID, 0l);
	}
	
	public String getChannelName(){
		return this.channelName;
	}
	
	public void setChannelName(String channelName){
		this.channelName = channelName;
		
	}
	
	public long getFatherchannelID(){
		return this.fatherchannelID;
	}
	
	public void setFatherchannelID(String fatherchannelID){
		this.fatherchannelID = JavaLangUtil.StrToLong(fatherchannelID, 0l);
	}
	
	/**
	 * ��ʼ����Ŀ���ݣ���assets/channelinfo.json�ļ��ж�ȡ
	 * @return ��������ļ������ڻ��߽����쳣����null
	 */
	public static ArrayList<ChannelVO> initChannelsFormAssets(Context context)
	{
		String strChannels = FileUtil.readFileFromAssetsFile(context, "channelinfo.json");
		if(StringUtil.isEmpty(strChannels))
			return null;
		ArrayList<ChannelVO> result = (ArrayList<ChannelVO>) JsonParser.parseJsonToList(strChannels, null);
		return result;
	}
	
	/**
	 * ��ȡ������Ŀ����
	 * @param channelVOs
	 * @return
	 */
	public static ArrayList<ChannelVO> getChannels(ArrayList<ChannelVO> channelVOs,
			int fatherid)
	{
		if(channelVOs==null||channelVOs.isEmpty())
			return null;
		ArrayList<ChannelVO> result = new ArrayList<ChannelVO>();
		for(int i = 0 ;i<channelVOs.size();i++ )
		{
			if(channelVOs.get(i).fatherchannelID==fatherid)
				result.add(channelVOs.get(i));
		}
		if(result.isEmpty())
			return null;
		Collections.sort(result,new SortByChannelSort());
		return result;
	}
	
	
	/**
	 * ��sort�ֶ�����
	 * @author wuzhu email:wuzhupc@gmail.com
	 * @version ����ʱ�䣺2012-11-25 ����10:02:00
	 */
	public static class SortByChannelSort implements Comparator<ChannelVO> 
	{
		@Override
		public int compare(ChannelVO arg0, ChannelVO arg1)
		{
			if(arg0==null||arg1==null)
				return 0;
			return arg0.sort-arg1.sort;
		} 
	}
}