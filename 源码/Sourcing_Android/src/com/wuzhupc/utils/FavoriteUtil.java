package com.wuzhupc.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.util.Log;

import com.wuzhupc.Sourcing.vo.BaseVO;
import com.wuzhupc.config.Constants;

/**
 * �ղؼй�����
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-12-2 ����8:22:29
 */
@SuppressWarnings("rawtypes")
public class FavoriteUtil
{
	protected static final String TAG = FavoriteUtil.class.getSimpleName();
	
	/**
	 * �ղؼ��ļ�
	 */
	private static final String CSTR_FAVFILE = Constants.CSTR_DATASTOREDIR+"favinfo.dat";
	
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
	@SuppressWarnings("unchecked")
	private void initFavDataList()
	{
		mDataList = new ArrayList();
		File favfile = new File(CSTR_FAVFILE);
		if(!favfile.exists()) return;
		FileInputStream fIn = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try
		{
			fIn = new FileInputStream(favfile);
			isr = new InputStreamReader(fIn,"UTF-8");
			br = new BufferedReader(isr);
			String temp;
			while ((temp = br.readLine()) != null)
			{
				//�������ж�ȡ���ݡ�����һ����¼
				Object o = SerializeUtil.getObjectFromString(temp);
				//�����л���ȡ�����ַ������뵽mDataList��
				if(o!=null&&o instanceof BaseVO)
					mDataList.add(o);
			}
		} catch (Exception e)
		{
			Log.e(TAG, e.getMessage());
		}
		finally
		{
			try
			{
				if (br != null)
					br.close();
				if (isr != null)
					isr.close();
				if (fIn != null)
					fIn.close();
			} catch (IOException e)
			{
				Log.e(TAG, e.getMessage());
			}
		}		
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
	 * �����ղ�����
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean addFavData(BaseVO obj)
	{
		if(obj==null||mDataList==null||mDataList.isEmpty())
			return false;
		int index = hasFavData(obj);
		if(index!=-1)
			return true;
		//�����ӵ��ղؼ�¼���ڵ�һλ
		mDataList.add(0, obj);
		saveFavDataList();
		return true;
	}
	
	/**
	 * �洢
	 */
	private void saveFavDataList()
	{
		File favfile = new File(CSTR_FAVFILE);
		//TODO
		if(mDataList==null||mDataList.isEmpty())
		{
			//ɾ���ղ��ļ�
			if(!favfile.exists()) return;
			favfile.delete();
			return;
		}
	}
	
	
	
}
