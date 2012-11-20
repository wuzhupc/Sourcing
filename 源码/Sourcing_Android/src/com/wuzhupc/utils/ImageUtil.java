package com.wuzhupc.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.wuzhupc.config.Constants;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class ImageUtil 
{
	protected static final String Tag=ImageUtil.class.getSimpleName();
	/**
	 * СͼĬ�ϵĸ߶�
	 */
	public static final int IMAGE_DEFSMALLHEIGHT=120;
	/**
	 * СͼĬ�ϵĿ��
	 */
	public static final int IMAGE_DEFSMALLWIDTH=120;
	
	/**
	 * ��ͼĬ�ϵĸ߶�
	 */
	public static final int IMAGE_DEFMEDIUMHEIGHT=480;
	/**
	 * ��ͼĬ�ϵĿ��
	 */
	public static final int IMAGE_DEFMEDIUMWIDTH=480;
	

	/**
	 * ��ͼĬ�ϵĸ߶�
	 */
	public static final int IMAGE_DEFLARGETHEIGHT=1024;
	/**
	 * ��ͼĬ�ϵĿ��
	 */
	public static final int IMAGE_DEFLARGETWIDTH=1024;
	
	/**
	 * ���ͼƬ�ļ���С
	 * @param bmfilename��ԭͼ�ļ���
	 * @return���������ļ���
	 */
	public static String resizeBitmapFile(String bmfilename,int newwidth)
	{
		if(StringUtil.isEmpty(bmfilename))
			return "";
		
		File file=new File(bmfilename);
		if(!file.exists())
			return "";
		Bitmap bm=null;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;// ��ֵ��Ϊtrue��ô��������ʵ�ʵ�bitmap����������ڴ�ռ������ֻ����һЩ����߽���Ϣ��ͼƬ��С��Ϣ,��ȡ��outHeight(ͼƬԭʼ�߶�)��
											// outWidth(ͼƬ��ԭʼ���)
		bm = BitmapFactory.decodeFile(bmfilename,
				options); // ��ʱ����bmΪ��
		// ��ȡ���ͼƬ�Ŀ�͸�
		int be = (int) (options.outWidth / (float) newwidth);
		if (be <= 0)
			be = 1;			
		options.inJustDecodeBounds = false;// ��������ͼƬ
		options.inSampleSize = be;
		bm = BitmapFactory
		.decodeFile(bmfilename, options);
		return saveBitmap(bm,true);
		//return saveBitmap(resizeBitmap(bm, Cint_PhotoRealWidth,true), true);		
	}
	
	/**
	 * ���ͼƬ��С��ֻ�ڱ�ԭͼСʱ�ű����
	 * @param bitmap ԭͼƬ
	 * @param newWidth����ͼƬ�Ŀ��
	 * @param brecycle �Ƿ��ڱ����ɺ����
	 * @return����������ͼ
	 */
	public static Bitmap resizeBitmap(Bitmap bitmap, int newWidth,boolean brecycle) 
	{	 
	    int width = bitmap.getWidth();
	    //���ԭͼ
	    if(newWidth>=width)
	    	return bitmap;
	    int height = bitmap.getHeight();
	    float temp = ((float) height) / ((float) width);
	    int newHeight = (int) ((newWidth) * temp);
	    float scaleWidth = ((float) newWidth) / width;
	    float scaleHeight = ((float) newHeight) / height;
	    Matrix matrix = new Matrix();	    
	// resize the bit map
	    matrix.postScale(scaleWidth, scaleHeight);	    
	// matrix.postRotate(45);
	    Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
	    if(brecycle)
	    	bitmap.recycle();
	    return resizedBitmap;	 
	}
	
	/**
	 * �����趨������͸߷��ػ�ȡͼƬ
	 * @param filename ͼƬ�ļ�����
	 * @param maxwidth �����,����ֵС�ڵ���0ʱ����
	 * @param maxheight�����߶�,����ֵС�ڵ���0ʱ����
	 * @return ͼƬ�����ڻ��ܶ�ȡ������null�����ԭͼ��С�趨�ĸ߻���򷵻�����ͼ�����С�ڸ߻���򷵻�ԭͼ
	 */
	public static Bitmap getBitmapFromFile(String filename,int maxwidth,int maxheight)
	{			
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;// ��ֵ��Ϊtrue��ô��������ʵ�ʵ�bitmap����������ڴ�ռ������ֻ����һЩ����߽���Ϣ��ͼƬ��С��Ϣ,��ȡ��outHeight(ͼƬԭʼ�߶�)��
											// outWidth(ͼƬ��ԭʼ���)
		Bitmap bm = BitmapFactory.decodeFile(filename,options); // ��ʱ����byΪ��
		
		if(options==null||options.outHeight<=0||options.outWidth<=0)
			return null;
		
		int be =1;
		//ͼ���ȴ��������ʱ��������
		if(maxwidth>0&&options.outWidth>maxwidth)
		{
			be=(int)(options.outWidth/(float)maxwidth);
		}
		//ͼ��߶ȴ������߶�ʱ��������
		if(maxheight>0&&options.outHeight>maxheight)
		{
			int tmpbe=(int)(options.outHeight/(float)maxheight);
			//������ű������ڿ�ȣ����Ը߶ȵ����ű���
			if(tmpbe>be)
				be=tmpbe;
		}
		if (be <= 0)
			be = 1;
		options.inJustDecodeBounds = false;// ��������ͼƬ
		options.inSampleSize = be;
		try
		{
			bm = BitmapFactory.decodeFile(filename, options);
		}
		catch(Exception e)
		{
			Log.e(Tag, e.getMessage());
			bm=null;
		}
		return bm;
	}

	/**
	 * �洢ͼƬ�����ش洢�ļ��ɹ�����ļ���
	 * @param bitmap 
	 * @param brecycle �Ƿ��ڴ洢��ɺ����
	 * @return
	 */
	public static String saveBitmap(Bitmap bitmap,boolean brecycle)
	{
		if(bitmap==null)
			return "";
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String tempfilename=format.format(Calendar.getInstance().getTime())+".jpg";
		String filepath=Constants.CSTR_DATASTOREDIR+"images"+File.separator;
		FileOutputStream fos=null;
		if(!FileUtil.isExistFolder(filepath))
			return "";
		try
		{
			File file=new File(filepath,tempfilename);
			fos=new FileOutputStream(file);
			bitmap.compress(CompressFormat.JPEG, 80, fos);
			fos.flush();
			fos.close();
			if(brecycle)
				bitmap.recycle();
		}
		catch(Exception ex)
		{
			return "";
		}
		return filepath+tempfilename;
	}
	
	/**
	 * drawable תΪbitmap
	 * @param drawable
	 * @return
	 */
	public static Bitmap drawableToBitmap(Drawable drawable)
	{
		if(drawable==null)
			return null;
		return ((BitmapDrawable)drawable).getBitmap();
	}
}
