package com.wuzhupc.config;

import java.io.File;

import android.os.Environment;

public class Constants
{

	/**
	 * �������ݴ洢·��,������·������/
	 */
	public static final String CSTR_DATASTOREDIR=Environment.getExternalStorageDirectory() +File.separator +"xmsourcing";//+File.separator;
	
	/**
	 * ��Ϣ���鱣��·��
	 */
	public static final String CSTR_DETAIL_DIR = "detail"+File.separator;


	/** ���ӳ�ʱ���� /s */
	public static final int CONNECT_TIME_OUT = 5;

	/** ��ȡ��ʱ���� /s */
	public static final int READ_TIME_OUT = 20;

	/** Get����ʽ */
	public static final int METHOD_GET = 0;

	/** Post����ʽ */
	public static final int METHOD_POST = 1;

	/** ÿ֡��ȡ���������� */
	public static final int READ_DATA_LENGTH = 1024;
	
	public static final int CINT_PAGE_SIZE = 20;
	
}
