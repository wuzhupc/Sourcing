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
	 * 资讯类型:行业新闻
	 */
	public static final int NEWS_TYPE_INDUSTRY = 1;
	/**
	 * 资讯类型:政策
	 */
	public static final int NEWS_TYPE_POLICY = 2;
	/**
	 * 资讯类型:通知
	 */
	public static final int NEWS_TYPE_NOTICE = 3;
	/**
	 * 资讯类型:专家文章
	 */
	public static final int NEWS_TYPE_ARTICLE = 4;
	/**
	 * 用户类型:个人用户
	 */
	public static final int USER_TYPE_PERSONAL = 1 ;
	/**
	 * 用户类型:企业用户
	 */
	public static final int USER_TYPE_ENTERPRISE = 2 ;
	/**
	 * 用户类型:培训机构
	 */
	public static final int USER_TYPE_TRAIN = 3 ;
	/**
	 * 推送信息类型:普通资讯信息
	 */
	public static final int PUSH_TYPE_NORMAL = 0;
	/**
	 * 推送信息类型:通知提醒
	 */
	public static final int PUSH_TYPE_NOTIFIER = 1;
	/**
	 * 推送信息类型:审核结果
	 */
	public static final int PUSH_TYPE_AUDIT = 2;
	/**
	 * 推送信息类型:申报进度
	 */
	public static final int PUSH_TYPE_DECLARE = 3;

}
