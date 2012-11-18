package com.wuzhupc.config;

import java.io.File;

import android.os.Environment;

public class Constants
{

	/**
	 * 缓存数据存储路径,最后包含路径符号/
	 */
	public static final String CSTR_DATASTOREDIR=Environment.getExternalStorageDirectory() +File.separator +"xmsourcing"+File.separator;
	
	/**
	 * 信息详情保存路径
	 */
	public static final String CSTR_DETAIL_DIR = "detail"+File.separator;

}
