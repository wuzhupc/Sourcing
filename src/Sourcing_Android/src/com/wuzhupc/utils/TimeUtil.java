package com.wuzhupc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.text.format.DateUtils;
import android.text.format.Time;

public class TimeUtil {
    public static String formatTimeStampString(Context context, long when) {
        return formatTimeStampString(context, when, false);
    }

    public static String formatTimeStampString(Context context, long when, boolean fullFormat) {
        Time then = new Time();
        then.set(when);
        Time now = new Time();
        now.setToNow();

        // Basic settings for formatDateTime() we want for all cases.
        int format_flags = DateUtils.FORMAT_NO_NOON_MIDNIGHT |
                           DateUtils.FORMAT_ABBREV_ALL |
                           DateUtils.FORMAT_CAP_AMPM;

        // If the message is from a different year, show the date and year.
        if (then.year != now.year) {
            format_flags |= DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_DATE;
        } else if (then.yearDay != now.yearDay) {
            // If it is from a different day than today, show only the date.
            format_flags |= DateUtils.FORMAT_SHOW_DATE;
        } else {
            // Otherwise, if the message is from today, show the time.
            format_flags |= DateUtils.FORMAT_SHOW_TIME;
        }

        // If the caller has asked for full details, make sure to show the date
        // and time no matter what we've determined above (but still make showing
        // the year only happen if it is a different year from today).
        if (fullFormat) {
            format_flags |= (DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_TIME);
        }

        return DateUtils.formatDateTime(context, when, format_flags);
    }
    
    /**
     * ��ʽ����ʾ����(�ԡ����족�������족�������⴦��)
     * @param context
     * @param when
     * @return
     */
    public static String formatDateTimeString(Context context, long when, boolean fullFormat) {
    	Time then = new Time();
        then.set(when);
        Time now = new Time();
        now.setToNow();

        // Basic settings for formatDateTime() we want for all cases.
        int format_flags = DateUtils.FORMAT_NO_NOON_MIDNIGHT |
                           DateUtils.FORMAT_ABBREV_ALL |
                           DateUtils.FORMAT_CAP_AMPM;

        // If the message is from a different year, show the date and year.
        if (then.year != now.year) {
            format_flags |= DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_DATE;
        }
        else if (then.yearDay == now.yearDay-1) {
			return "����";
		}
        else if (then.yearDay == now.yearDay) {
			return "����";
		}
        else {
			format_flags |= DateUtils.FORMAT_SHOW_DATE;
		}

        // If the caller has asked for full details, make sure to show the date
        // and time no matter what we've determined above (but still make showing
        // the year only happen if it is a different year from today).
        if (fullFormat) {
            format_flags |= (DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_TIME);
        }

        return DateUtils.formatDateTime(context, when, format_flags);
    }
	
	/**
	 * �ַ���ת����
	 * @param str �ַ���
	 * @param def Ĭ��ʱ�䣬���ת��ʧ���򷵻�Ĭ��ʱ��
	 */
	public static Date strToDate(String str,Date def)
	{
		return strToDate(str,"yyyy-MM-dd HH:mm:ss",def);
	}
	
	/**
	 * �ַ���ת����
	 * @param str �ַ���
	 * @param formatstr ���ڸ�ʽ���ַ���
	 * @param def Ĭ��ʱ�䣬���ת��ʧ���򷵻�Ĭ��ʱ��
	 */
	public static Date strToDate(String str,String formatstr,Date def)
	{
		if(StringUtil.isEmpty(str))
			return def;
		try
		{
			SimpleDateFormat sdf=new SimpleDateFormat(formatstr);
			return sdf.parse(str);			
		}
		catch (Exception e) {
			return def;
		}		
	}
	
	/**
	 * ����תΪ�ַ���
	 */
	public static String dateToString(Date date) {
		if(date==null)
			date=new Date();
        return dateToString(date,"yyyy-MM-dd HH:mm:ss");
    } 
	
	/**
	 * ����תΪ�ַ���
	 */
	public static String dateToString(Date date,String formatstring) 
	{
		if(formatstring==null||formatstring.equals(""))
			formatstring="yyyy-MM-dd HH:mm:ss";
		
		try
		{
		 SimpleDateFormat sdf = new SimpleDateFormat(formatstring);        
	     return sdf.format(date);
		}
		catch(Exception e)
		{
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        
		     return sdf.format(date);
		}
	}
}
