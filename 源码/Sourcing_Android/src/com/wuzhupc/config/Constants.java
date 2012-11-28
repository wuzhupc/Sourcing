package com.wuzhupc.config;

import java.io.File;

import android.os.Environment;

public class Constants
{

	/**
	 * 缓存数据存储路径,最后包含路径符号/
	 */
	public static final String CSTR_DATASTOREDIR=Environment.getExternalStorageDirectory() +File.separator +"xmsourcing";//+File.separator;
	
	/**
	 * 信息详情保存路径
	 */
	public static final String CSTR_DETAIL_DIR = "detail"+File.separator;


	/** 连接超时设置 /s */
	public static final int CONNECT_TIME_OUT = 5;

	/** 读取超时设置 /s */
	public static final int READ_TIME_OUT = 20;

	/** Get请求方式 */
	public static final int METHOD_GET = 0;

	/** Post请求方式 */
	public static final int METHOD_POST = 1;

	/** 每帧读取的数据流量 */
	public static final int READ_DATA_LENGTH = 1024;
	
	public static final int CINT_PAGE_SIZE = 20;
	
}
