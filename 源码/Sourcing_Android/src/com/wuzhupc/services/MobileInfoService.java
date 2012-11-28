package com.wuzhupc.services;

import android.content.Context;

/**
 * info��Ϣ��ȡ
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-28 ����10:13:43
 */
public class MobileInfoService extends BaseJsonService
{
	public MobileInfoService(Context c)
	{
		super(c);
	}
	
	/**
	 * ��ȡ��Ѷ�б�
	 * @param newstype �������0��������Ѷ��1����ҵ���ţ�2�����ߣ�3��֪ͨ��4��ר������
	 * @param bottomnewsid �����б����޵�����ID��Ĭ��0�������0ʱ����ȡ������ID��pagesize����¼��
	 */
	public void getNewsList(int newstype,long bottomnewsid)
	{
		
	}
	
	/**
	 * 3.3.	��ȡ��Ѷ��ϸ��Ϣ
	 * @param newstype �������0��������Ѷ��1����ҵ���ţ�2�����ߣ�3��֪ͨ��4��ר������
	 * @param newsid ����ID
	 */
	public void getNewsDetail(int newstype,long newsid)
	{
		
	}
	
	/**
	 * 3.4.	��ȡ��Ѷ������Ϣ
	 * @param newstype ������� 1����ҵ���ţ�2�����ߣ�3��֪ͨ��4��ר������
	 * @param newsid ����ID
	 * @param bottomcommentid ���������б����޵�����ID��Ĭ��0�������0ʱ����ȡ����������ID��pagesize����¼��
	 */
	public void getNewsComment(int newstype,long newsid,int bottomcommentid)
	{
		
	}
	
	/**
	 * 3.5.	������Ѷ������Ϣ
	 * @param newstype ������� 1����ҵ���ţ�2�����ߣ�3��֪ͨ��4��ר������
	 * @param newsid ����ID
	 * @param conetnt ��������
	 */
	public void sendNewsComment(int newstype,long newsid,String conetnt)
	{
		
	}
	
	/**
	 * 3.6.	��ȡְλ�б�
	 * @param searchkey �����ؼ��֣�Ĭ��Ϊ�գ��ǿ�ʱ��Ҫ���ݹؼ��ַ��ؽ�������
	 * @param bottomid ְλ�б����޵�����ID��Ĭ��0�������0ʱ����ȡ������ID��pagesize����¼��
	 */
	public void getJobList(String searchkey,long bottomid)
	{
		
	}
	
	/**
	 * 3.7.	��ȡ�����б�
	 * @param searchkey �����ؼ��֣�Ĭ��Ϊ�գ��ǿ�ʱ��Ҫ���ݹؼ��ַ��ؽ�������
	 * @param bottomid �����б����޵ļ�¼ID��Ĭ��0�������0ʱ����ȡ�˼�¼ID��pagesize����¼��
	 */
	public void getResumeList(String searchkey,long bottomid)
	{
		
	}
	
	/**
	 * 3.8.	��ȡ��ѵ�����б�
	 * @param searchkey �����ؼ��֣�Ĭ��Ϊ�գ��ǿ�ʱ��Ҫ���ݹؼ��ַ��ؽ�������
	 * @param bottomid �б����޵ļ�¼ID��Ĭ��0�������0ʱ����ȡ�˼�¼ID��pagesize����¼��
	 */
	public void getTrainList(String searchkey,long bottomid)
	{
		
	}
	
	/**
	 * 3.9.	��ȡ��Ŀ�б�
	 * @param searchkey �����ؼ��֣�Ĭ��Ϊ�գ��ǿ�ʱ��Ҫ���ݹؼ��ַ��ؽ�������
	 * @param bottomid �б����޵ļ�¼ID��Ĭ��0�������0ʱ����ȡ�˼�¼ID��pagesize����¼��
	 */
	public void getProjectList(String searchkey,long bottomid)
	{
		
	}
	
	/**
	 * 3.10.	��ȡְλ����
	 * @param jobid ְλ���
	 */
	public void getJobDetail(long jobid)
	{
		
	}
	/**
	 * 3.11.	��ȡ��������
	 * @param resumeid �������
	 */
	public void getResumeDetail(long resumeid)
	{
		
	}
	/**
	 * 3.12.	��ȡ��ѵ��������
	 * @param trainid ��ѵ�������
	 */
	public void getTrainDetail(long trainid)
	{
		
	}
	/**
	 * 3.13.	��ȡ��Ŀ����
	 * @param projectid ��Ŀ���
	 */
	public void getProjectDetail(long projectid)
	{
		
	}
}
