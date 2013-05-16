package com.wuzhupc.utils;

import android.app.Activity;
import android.view.Display;
import android.view.WindowManager;

public class ViewUtil
{
	/**
	 * ��ȡ��Ļ��
	 * @param act
	 */
	public static int getScreenWidth(Activity act)
	{
		WindowManager windowManager = act.getWindowManager();  
		Display display = windowManager.getDefaultDisplay();  
		
		return display.getWidth();
	}
	/**
	 * ��ȡ��Ļ��
	 * @param act
	 */
	public static int getScreenHeight(Activity act)
	{
		WindowManager windowManager = act.getWindowManager();  
		Display display = windowManager.getDefaultDisplay();  
		
		return display.getHeight();
	}
	
	/**
	 * ��ȡ�ֻ���Ļ����
	 * @param act 
	 * @return ����ֵ��Ӧ����
	 * -1:������Ļ  0:HVGA 1:QVGA 2:WQVGA 400  3:WQVGA 432 4:WVGA 800 5:WVGA 854 6:VGA
	 */
	public static int getScreenType(Activity act)
	{
		int screenWidth,screenHeight; 
		WindowManager windowManager = act.getWindowManager();  
		Display display = windowManager.getDefaultDisplay();  
		
		if(display.getWidth()>display.getHeight())
		{
			 screenHeight= display.getWidth();  
			 screenWidth= display.getHeight(); 
		}
		else
		{
			screenWidth = display.getWidth();  
			screenHeight = display.getHeight(); 
		}
		if(screenHeight==640&&screenWidth==480)//VGA
			return 6;
		if(screenHeight==854&&screenWidth==480)//WVGA 854
			return 5;
		if(screenHeight==800&&screenWidth==480)//WVGA
			return 4;
		if(screenHeight==432&screenWidth==240)//WQVGA 432
			return 3;
		if(screenHeight==400&screenWidth==240)//WQVGA
			return 2;
		if(screenHeight==320&screenWidth==240)//qvga
			return 1;
		if(screenHeight==480&screenWidth==320)//HVGA
			return 0;
		
		return -1;
	}
}
