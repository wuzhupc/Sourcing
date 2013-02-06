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
 * 栏目信息VO
 */
public class ChannelVO extends BaseVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -802998074172715366L;
	/**
	 * 栏目ID
	 */
	//private long channelID;	
	/**
	 * 栏目名称
	 */
	private String channelName = "";
	/**
	 * 父栏目ID
	 */
	private long fatherchannelID;
	
	/**
	 * 是否为默认显示
	 */
	private int isdefault;
	/**
	 * 排列顺序
	 */
	private int sort;
	
	/**
	 * 类型
	 */
	private int type;
	
	/**
	 * 是否是第一次载入
	 */
	public boolean isFirstLoad=true;
	
	/**
	 * 最后一次更新数据时间
	 */
	private Date lastUpdateDataTime;
	
	/**
	 * 需要那些用户类型,如果null或空字符串，表示没有要求，如果是需要多种用户类型，则用;分隔开，用户类型参考UserVO
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
	 * 父类型的栏目ID
	 */
	public static final int CHANNELID_FATHER = 0;
	
	/**
	 * 父类型－资讯
	 */
	public static final int TYPE_FATHER_NEWS = 1;
	
	/**
	 * 父类型－服务
	 */
	public static final int TYPE_FATHER_PERSON = 2;
	
	/**
	 * 父类型－用户
	 */
	public static final int TYPE_FATHER_USER = 3;
	
	/**
	 * 父类型－更多
	 */
	public static final int TYPE_FATHER_MORE = 4;
	
	/**
	 * 资讯栏目类型-最新
	 */
	public static final int TYPE_NEWS_NEWEST = 0;
	
	/**
	 * 资讯栏目类型-行业
	 */
	public static final int TYPE_NEWS_INDUSTR = 1;
	
	/**
	 * 资讯栏目类型-政策
	 */
	public static final int TYPE_NEWS_POLICY = 2;
	
	/**
	 * 资讯栏目类型-通知
	 */
	public static final int TYPE_NEWS_NOTIFICATION = 3;
	
	/**
	 * 资讯栏目类型-专家
	 */
	public static final int TYPE_NEWS_EXPERT = 4;
	
	/**
	 * 服务栏目类型 -职位
	 */
	public static final int TYPE_PERSON_POSITION = 1;
	
	/**
	 * 服务栏目类型 -简历
	 */
	public static final int TYPE_PERSON_RESUME = 2;
	
	/**
	 * 服务栏目类型 -培训机构
	 */
	public static final int TYPE_PERSON_TRAIN = 3;
	
	/**
	 * 服务栏目类型 -项目
	 */
	public static final int TYPE_PERSON_PROJECT = 4;
	
	/**
	 * 更多栏目类型－收藏
	 */
	public static final int TYPE_MORE_FAV = 1;
	
	/**
	 * 更多栏目类型－系统设置
	 */
	public static final int TYPE_MORE_SETTING = 2;
	
	/**
	 * 更多栏目类型－退出
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
	 * 资讯
	 * @return
	 */
	public boolean isNewsChannel()
	{
		return fatherchannelID==CHANNELID_FATHER&&type==TYPE_FATHER_NEWS;
	}
	
	/**
	 * 服务
	 * @return
	 */
	public boolean isPersonChannel()
	{
		return fatherchannelID==CHANNELID_FATHER&&type==TYPE_FATHER_PERSON;
	}
	
	/**
	 * 用户
	 * @return
	 */
	public boolean isUserChannel()
	{
		return fatherchannelID==CHANNELID_FATHER&&type==TYPE_FATHER_USER;
	}
	
	/**
	 * 更多
	 * @return
	 */
	public boolean isMoreChannel()
	{
		return fatherchannelID==CHANNELID_FATHER&&type==TYPE_FATHER_MORE;
	}

	
	/**
	 *获取默认资讯类型
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
	 * 服务栏目类型 -职位
	 * @return
	 */
	public boolean isPositionChannel()
	{
		return fatherchannelID==TYPE_FATHER_PERSON&&type==TYPE_PERSON_POSITION;
	}
	
	/**
	 * 服务栏目类型 -培训机构
	 * @return
	 */
	public boolean isTrainChannel()
	{
		return fatherchannelID==TYPE_FATHER_PERSON&&type==TYPE_PERSON_TRAIN;
	}
	
	/**
	 * 服务栏目类型 -项目
	 * @return
	 */
	public boolean isProjectChannel()
	{
		return fatherchannelID==TYPE_FATHER_PERSON&&type==TYPE_PERSON_PROJECT;
	}
	
	/**
	 * 服务栏目类型 -简历
	 * @return
	 */
	public boolean isResumeChannel()
	{
		return fatherchannelID==TYPE_FATHER_PERSON&&type==TYPE_PERSON_RESUME;
	}
	
	/**
	 * 初始化栏目数据，从assets/channelinfo.json文件中读取
	 * @return 如果数据文件不存在或者解析异常返回null
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
	 * 获取父级栏目内容
	 * @param channelVOs
	 * @return
	 */
	public static ArrayList<ChannelVO> getFatherChannels(ArrayList<ChannelVO> channelVOs)
	{
		return getChannels(channelVOs, CHANNELID_FATHER);
	}
	
	/**
	 * 获取父栏目栏目ID
	 * @param channelVOs
	 * @param type
	 * @return
	 */
	public static long getFatherChannelIDFromType(ArrayList<ChannelVO> channelVOs,int type)
	{
		if(channelVOs==null||channelVOs.isEmpty())
			return -1l;
		for(int i = 0 ;i<channelVOs.size();i++ )
		{
			if(channelVOs.get(i).fatherchannelID==CHANNELID_FATHER)
			{
				if(channelVOs.get(i).type == type)
					return channelVOs.get(i).getChannelID();
			}
		}
		return -1l;
	}
	
	/**
	 * 获取各级栏目内容
	 * @param channelVOs
	 * @return
	 */
	public static ArrayList<ChannelVO> getChannels(ArrayList<ChannelVO> channelVOs,
			long fatherid)
	{
		return getChannels(channelVOs, fatherid,null);
	}
	
	/**
	 * 获取各级栏目内容
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
				//判断是否是需要的用户类型
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
			//读取设置项
			lastUpdateDataTime = SettingUtil.getChannelLastUpdateTime(c,fatherchannelID, id);
		}
		return lastUpdateDataTime;
	}

	public void setLastUpdateDataTime(Context c,Date lastUpdateDataTime)
	{
		this.lastUpdateDataTime = lastUpdateDataTime;
		//存取配置信息
		SettingUtil.setChannelLastUpdateTime(c, fatherchannelID,id, lastUpdateDataTime);
	}


	/**
	 * 按sort字段排序
	 * @author wuzhu email:wuzhupc@gmail.com
	 * @version 创建时间：2012-11-25 下午10:02:00
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