package com.wuzhupc.Sourcing.vo;

import java.io.Serializable;

/**
 * 
 * @author Administrator
 *
 */
public class BaseVO implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ��Ѷ����:��ҵ����
	 */
	public static final int NEWS_TYPE_INDUSTRY = 1;
	/**
	 * ��Ѷ����:����
	 */
	public static final int NEWS_TYPE_POLICY = 2;
	/**
	 * ��Ѷ����:֪ͨ
	 */
	public static final int NEWS_TYPE_NOTICE = 3;
	/**
	 * ��Ѷ����:ר������
	 */
	public static final int NEWS_TYPE_ARTICLE = 4;
	/**
	 * �û�����:�����û�
	 */
	public static final int USER_TYPE_PERSONAL = 1 ;
	/**
	 * �û�����:��ҵ�û�
	 */
	public static final int USER_TYPE_ENTERPRISE = 2 ;
	/**
	 * �û�����:��ѵ����
	 */
	public static final int USER_TYPE_TRAIN = 3 ;
	/**
	 * ������Ϣ����:��ͨ��Ѷ��Ϣ
	 */
	public static final int PUSH_TYPE_NORMAL = 0;
	/**
	 * ������Ϣ����:֪ͨ����
	 */
	public static final int PUSH_TYPE_NOTIFIER = 1;
	/**
	 * ������Ϣ����:��˽��
	 */
	public static final int PUSH_TYPE_AUDIT = 2;
	/**
	 * ������Ϣ����:�걨����
	 */
	public static final int PUSH_TYPE_DECLARE = 3;

}
