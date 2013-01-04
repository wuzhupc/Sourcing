package com.wuzhupc.Sourcing.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import android.content.Context;

import com.wuzhupc.utils.FileUtil;
import com.wuzhupc.utils.JavaLangUtil;
import com.wuzhupc.utils.SettingUtil;
import com.wuzhupc.utils.StringUtil;
import com.wuzhupc.utils.json.JsonParser;

/**
 * ��Ŀ��ϢVO
 */
public class ChannelVO extends BaseVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -802998074172715366L;
	/**
	 * ��ĿID
	 */
	//private long channelID;	
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
	 * �Ƿ��ǵ�һ������
	 */
	public boolean isFirstLoad=true;
	
	/**
	 * ���һ�θ�������ʱ��
	 */
	private Date lastUpdateDataTime;
	
	/**
	 * ��Ҫ��Щ�û�����,���null����ַ�������ʾû��Ҫ���������Ҫ�����û����ͣ�����;�ָ������û����Ͳο�UserVO
	 */
	private String mustusertypes;
	
	public String getMustusertypes()
	{
		return mustusertypes;
	}

	public void setMustusertypes(String mustusertypes)
	{
		this.mustusertypes = mustusertypes;
	}


	/**
	 * �����͵���ĿID
	 */
	private static final int CHANNELID_FATHER = 0;
	
	/**
	 * �����ͣ���Ѷ
	 */
	private static final int TYPE_FATHER_NEWS = 1;
	
	/**
	 * �����ͣ�����
	 */
	private static final int TYPE_FATHER_PERSON = 2;
	
	/**
	 * �����ͣ��û�
	 */
	private static final int TYPE_FATHER_USER = 3;
	
	/**
	 * �����ͣ�����
	 */
	private static final int TYPE_FATHER_MORE = 4;
	
	/**
	 * ��Ѷ��Ŀ����-����
	 */
	private static final int TYPE_NEWS_NEWEST = 0;
	
	/**
	 * ��Ѷ��Ŀ����-��ҵ
	 */
	private static final int TYPE_NEWS_INDUSTR = 1;
	
	/**
	 * ��Ѷ��Ŀ����-����
	 */
	private static final int TYPE_NEWS_POLICY = 2;
	
	/**
	 * ��Ѷ��Ŀ����-֪ͨ
	 */
	private static final int TYPE_NEWS_NOTIFICATION = 3;
	
	/**
	 * ��Ѷ��Ŀ����-ר��
	 */
	private static final int TYPE_NEWS_EXPERT = 4;
	
	/**
	 * ������Ŀ���� -ְλ
	 */
	private static final int TYPE_PERSON_POSITION = 1;
	
	/**
	 * ������Ŀ���� -����
	 */
	private static final int TYPE_PERSON_RESUME = 2;
	
	/**
	 * ������Ŀ���� -��ѵ����
	 */
	private static final int TYPE_PERSON_TRAIN = 3;
	
	/**
	 * ������Ŀ���� -��Ŀ
	 */
	private static final int TYPE_PERSON_PROJECT = 4;
	
	/**
	 * ������Ŀ���ͣ��ղ�
	 */
	private static final int TYPE_MORE_FAV = 1;
	
	/**
	 * ������Ŀ���ͣ�ϵͳ����
	 */
	private static final int TYPE_MORE_SETTING = 2;
	
	/**
	 * ������Ŀ���ͣ��˳�
	 */
	private static final int TYPE_MORE_EXIT = 0;
	
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
		return id;
	}
	
	public void setChannelID(long channelID)
	{
		id=channelID;
	}
	
	public void setChannelID(String channelID){
		setChannelID(JavaLangUtil.StrToLong(channelID, 0l));
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
	 * ��Ѷ
	 * @return
	 */
	public boolean isNewsChannel()
	{
		return fatherchannelID==CHANNELID_FATHER&&type==TYPE_FATHER_NEWS;
	}
	
	/**
	 * ����
	 * @return
	 */
	public boolean isPersonChannel()
	{
		return fatherchannelID==CHANNELID_FATHER&&type==TYPE_FATHER_PERSON;
	}
	
	/**
	 * �û�
	 * @return
	 */
	public boolean isUserChannel()
	{
		return fatherchannelID==CHANNELID_FATHER&&type==TYPE_FATHER_USER;
	}
	
	/**
	 * ����
	 * @return
	 */
	public boolean isMoreChannel()
	{
		return fatherchannelID==CHANNELID_FATHER&&type==TYPE_FATHER_MORE;
	}

	
	/**
	 *��ȡĬ����Ѷ����
	 * @return
	 */
	public static int getDefaultNewsType()
	{
		return TYPE_NEWS_INDUSTR;
	}
	
	public boolean isFavChannel()
	{
		return fatherchannelID==TYPE_FATHER_MORE&&type==TYPE_MORE_FAV;
	}	
	public boolean isSettingChannel()
	{
		return fatherchannelID==TYPE_FATHER_MORE&&type==TYPE_MORE_SETTING;
	}	
	public boolean isExitChannel()
	{
		return fatherchannelID==TYPE_FATHER_MORE&&type==TYPE_MORE_EXIT;
	}
	
	/**
	 * ������Ŀ���� -ְλ
	 * @return
	 */
	public boolean isPositionChannel()
	{
		return fatherchannelID==TYPE_FATHER_PERSON&&type==TYPE_PERSON_POSITION;
	}
	
	/**
	 * ������Ŀ���� -��ѵ����
	 * @return
	 */
	public boolean isTrainChannel()
	{
		return fatherchannelID==TYPE_FATHER_PERSON&&type==TYPE_PERSON_TRAIN;
	}
	
	/**
	 * ������Ŀ���� -��Ŀ
	 * @return
	 */
	public boolean isProjectChannel()
	{
		return fatherchannelID==TYPE_FATHER_PERSON&&type==TYPE_PERSON_PROJECT;
	}
	
	/**
	 * ������Ŀ���� -����
	 * @return
	 */
	public boolean isResumeChannel()
	{
		return fatherchannelID==TYPE_FATHER_PERSON&&type==TYPE_PERSON_RESUME;
	}
	
	/**
	 * ��ʼ����Ŀ���ݣ���assets/channelinfo.json�ļ��ж�ȡ
	 * @return ��������ļ������ڻ��߽����쳣����null
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<ChannelVO> initChannelsFromAssets(Context context)
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
	public static ArrayList<ChannelVO> getFatherChannels(ArrayList<ChannelVO> channelVOs)
	{
		return getChannels(channelVOs, CHANNELID_FATHER);
	}
	
	/**
	 * ��ȡ������Ŀ����
	 * @param channelVOs
	 * @return
	 */
	public static ArrayList<ChannelVO> getChannels(ArrayList<ChannelVO> channelVOs,
			long fatherid)
	{
		return getChannels(channelVOs, fatherid,null);
	}
	
	/**
	 * ��ȡ������Ŀ����
	 * @param channelVOs
	 * @return
	 */
	public static ArrayList<ChannelVO> getChannels(ArrayList<ChannelVO> channelVOs,
			long fatherid,String usertype)
	{
		if(channelVOs==null||channelVOs.isEmpty())
			return null;
		ArrayList<ChannelVO> result = new ArrayList<ChannelVO>();
		
		for(int i = 0 ;i<channelVOs.size();i++ )
		{
			if(channelVOs.get(i).fatherchannelID==fatherid)
			{
				//�ж��Ƿ�����Ҫ���û�����
				if(!StringUtil.isEmpty(channelVOs.get(i).mustusertypes))
				{
					if(!StringUtil.isEmpty(usertype)&&channelVOs.get(i).mustusertypes.contains(usertype))
						result.add(channelVOs.get(i));
					continue;
				}
				result.add(channelVOs.get(i));
			}
		}
		if(result.isEmpty())
			return null;
		Collections.sort(result,new SortByChannelSort());
		return result;
	}	
	
	public Date getLastUpdateDataTime(Context c)
	{
		if(lastUpdateDataTime==null)
		{
			//��ȡ������
			lastUpdateDataTime = SettingUtil.getChannelLastUpdateTime(c,fatherchannelID, id);
		}
		return lastUpdateDataTime;
	}

	public void setLastUpdateDataTime(Context c,Date lastUpdateDataTime)
	{
		this.lastUpdateDataTime = lastUpdateDataTime;
		//��ȡ������Ϣ
		SettingUtil.setChannelLastUpdateTime(c, fatherchannelID,id, lastUpdateDataTime);
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