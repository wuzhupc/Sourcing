package com.wuzhupc.utils;

import java.util.ArrayList;

import com.wuzhupc.Sourcing.vo.BaseVO;

/**
 * �ղؼй�����
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-12-2 ����8:22:29
 */
@SuppressWarnings("rawtypes")
public class FavoriteUtil
{
	/**
	 * �����б�
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
	 * ��ʼ���ղ�����
	 */
	private void initFavDataList()
	{
		mDataList = new ArrayList();
		//TODO �������ж�ȡ���ݡ�����һ����¼
		//�����л���ȡ�����ַ������뵽mDataList��
	}
	
	/**
	 * �Ƴ�ָ���������ղ�����
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
	 * �Ƴ�ָ���ղ�����
	 * @param obj
	 */
	public boolean removeFavData(BaseVO obj)
	{
		return removeFavData(hasFavData(obj));
	}
	
	/**
	 * �������ղ�����ֵ�����û�У�����-1
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
	 * �洢
	 */
	private void saveFavDataList()
	{
		//TODO
	}
	
	
	
}
