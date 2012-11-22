package com.wuzhupc.widget;

/**
 * 子级View变更监听接口
 * @author wuzhu
 *
 */
public interface OnDisplayerChildChangeListener {

	/**
	 * 显示子级View变动回调函数
	 * @param oldWhichChild 变动前的子级索引
	 * @param whichChild 变动后的子级索引
	 */
	public void onChanged(int oldWhichChild,int whichChild);
}
