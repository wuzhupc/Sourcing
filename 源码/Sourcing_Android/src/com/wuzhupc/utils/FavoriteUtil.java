package com.wuzhupc.utils;

import java.util.ArrayList;

import com.wuzhupc.Sourcing.vo.BaseVO;

/**
 * 收藏夹工具类
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-12-2 下午8:22:29
 */
@SuppressWarnings("rawtypes")
public class FavoriteUtil
{
	/**
	 * 数据列表
	 */
	private ArrayList mDataList;

	public ArrayList getDataList()
	{
		return mDataList;
	}
	
	public  FavoriteUtil()
	{
		initFavDataList();
	}
	
	/**
	 * 初始化收藏数据
	 */
	private void initFavDataList()
	{
		mDataList = new ArrayList();
		//TODO 从数据中读取内容　１行一条记录
		//反序列化读取到的字符串加入到mDataList中
	}
	
	/**
	 * 移除指定索引的收藏数据
	 * @param index
	 */
	public boolean removeFavData(int index)
	{
		if(index<0||mDataList==null||mDataList.isEmpty()||index>=mDataList.size())
			return false;
		mDataList.remove(index);
		saveFavDataList();
		return true;
	}
	
	/**
	 * 移除指定收藏数据
	 * @param obj
	 */
	public boolean removeFavData(BaseVO obj)
	{
		return removeFavData(hasFavData(obj));
	}
	
	/**
	 * 返回是收藏索引值，如果没有，返回-1
	 * @param obj
	 * @return
	 */
	public int hasFavData(BaseVO obj)
	{
		if(obj==null||mDataList==null||mDataList.isEmpty())
			return -1;
		for(int i=0;i<mDataList.size();i++)
		{
			if(obj.equals(mDataList.get(i)))
				return i;
		}
		return -1;
	}
	
	/**
	 * 存储
	 */
	private void saveFavDataList()
	{
		//TODO
	}
	
	
	
}
