package com.wuzhupc.services;

import java.io.File;

import com.wuzhupc.Sourcing.R;
import com.wuzhupc.config.Constants;
import com.wuzhupc.utils.FileUtil;
import com.wuzhupc.utils.ImageUtil;
import com.wuzhupc.utils.StringUtil;
import com.wuzhupc.utils.net.ImageTask;
import com.wuzhupc.utils.net.ImageTask.IImageDownloader;
import com.wuzhupc.utils.net.ImageTask.IProgressController;
import com.wuzhupc.widget.imagezoom.ImageViewTouch;
import com.wuzhupc.widget.imagezoom.ImageViewTouchBase;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask.Status;
import android.os.Handler;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ImageService
{
	/**
	 * ����ʧ��
	 */
	public static final int CINT_LOAD_FAILURE=0;
	/**
	 * ���سɹ����������
	 */
	public static final int CINT_LOAD_SUCESS_LOCAL=1;
	/**
	 * ���سɹ����������
	 */
	public static final int CINT_LOAD_SUCESS_NETWORK=2;
	
	ImageTask imageTask;

	private Context mContext;
	public ImageService(Context ctx)
	{
		this.mContext = ctx;
	}

	/**
	 * ����TNND����ͼ
	 * 
	 * @param iv
	 * @param imageUrl
	 * @param drawable
	 *            ����ʧ��ʱĬ����ʾͼƬ
	 */
	public void setThumbnail(final ImageView iv, String imageUrl,
			final Drawable drawable)
	{
		setThumbnail(iv, imageUrl, drawable, 0, 0);
	}

	/**
	 * ����ͼ��
	 * 
	 * @param iv
	 * @param bm
	 * @param defdrawable
	 * @param lp
	 *            iv����ʽ
	 */
	private void setImage(ImageView iv, Bitmap bm, Drawable defdrawable,
			ScaleType st, LayoutParams lp,Boolean adjustViewBounds)
	{
		if (lp != null)
			iv.setLayoutParams(lp);
		if (bm != null)
		{
			if(iv instanceof ImageViewTouch)
				((ImageViewTouch)iv).setImageBitmapReset(bm, true);
			else
			iv.setImageBitmap(bm);
		}
		else 
		{
			if (defdrawable == null)
			{
				defdrawable=mContext.getResources().getDrawable(R.drawable.loading_failed);
			}
			if(iv instanceof ImageViewTouchBase)
			{
				if(iv instanceof ImageViewTouch)
					((ImageViewTouch)iv).setImageBitmapReset(ImageUtil.drawableToBitmap(defdrawable),true);
				else
					iv.setImageBitmap(ImageUtil.drawableToBitmap(defdrawable));
			}
			else
				iv.setImageResource(R.drawable.loading_failed);
		}
		iv.setScaleType(st);
		iv.setAdjustViewBounds(adjustViewBounds);
	}

	/**
	 * ����TNND����ͼ
	 * 
	 * @param iv
	 * @param imageUrl
	 * @param drawable
	 *            ����ʧ��ʱĬ����ʾͼƬ
	 * @param maxwidth
	 *            �����,����ֵС�ڵ���0ʱ����
	 * @param maxheight
	 *            �����߶�,����ֵС�ڵ���0ʱ����
	 */
	public void setThumbnail(final ImageView iv, String imageUrl,
			final Drawable drawable, final int maxwidth, final int maxheight)
	{
		setThumbnail(iv, imageUrl, drawable, maxwidth, maxheight,
				ScaleType.CENTER);//_CROP
	}

	/**
	 * ����TNND����ͼ
	 * 
	 * @param iv
	 * @param imageUrl
	 * @param drawable
	 *            ����ʧ��ʱĬ����ʾͼƬ
	 * @param maxwidth
	 *            �����,����ֵС�ڵ���0ʱ����
	 * @param maxheight
	 *            �����߶�,����ֵС�ڵ���0ʱ����
	 */
	public void setThumbnail(final ImageView iv, String imageUrl,
			final Drawable drawable, final int maxwidth, final int maxheight,LayoutParams lastlp)
	{
		setThumbnail(iv, imageUrl, drawable, maxwidth, maxheight,
				ScaleType.CENTER_CROP,lastlp,true);
	}

	/**
	 * ����TNND����ͼ
	 * 
	 * @param iv
	 * @param imageUrl
	 * @param drawable
	 *            ����ʧ��ʱĬ����ʾͼƬ
	 * @param maxwidth
	 *            �����,����ֵС�ڵ���0ʱ����
	 * @param maxheight
	 *            �����߶�,����ֵС�ڵ���0ʱ����
	 */
	public void setThumbnail(final ImageView iv, String imageUrl,
			final Drawable drawable, final int maxwidth, final int maxheight,
			final ScaleType st)
	{
		setThumbnail(iv, imageUrl, drawable, maxwidth, maxheight, st, null,true);
	}

	/**
	 * ����TNND����ͼ
	 * 
	 * @param iv
	 * @param imageUrl
	 * @param drawable
	 *            ����ʧ��ʱĬ����ʾͼƬ
	 * @param maxwidth
	 *            �����,����ֵС�ڵ���0ʱ����
	 * @param maxheight
	 *            �����߶�,����ֵС�ڵ���0ʱ����
	 * @param adjustViewBounds ͼ���Ƿ��Զ�����ؼ��߿������ScaleType ��MATRIX ��Ҫ����Ϊfalse
	 */
	public void setThumbnail(final ImageView iv, String imageUrl,
			final Drawable drawable, final int maxwidth, final int maxheight,
			final ScaleType st,final Boolean adjustViewBounds)
	{
		setThumbnail(iv, imageUrl, drawable, maxwidth, maxheight, st, null,adjustViewBounds);
	}

	/**
	 * ����TNND����ͼ
	 * 
	 * @param iv
	 * @param imageUrl
	 * @param drawable
	 *            ����ʧ��ʱĬ����ʾͼƬ
	 * @param maxwidth
	 *            �����,����ֵС�ڵ���0ʱ����
	 * @param maxheight
	 *            �����߶�,����ֵС�ڵ���0ʱ����
	 * @param lastlp
	 *            ����imageview ��ʾ�Ĳ�����ʽ
	 */
	public void setThumbnail(final ImageView iv, String imageUrl,
			final Drawable drawable, final int maxwidth, final int maxheight,
			final ScaleType st, final LayoutParams lastlp,final Boolean adjustViewBounds)
	{
		setThumbnail(iv, imageUrl, drawable, maxwidth, maxheight, st, lastlp,adjustViewBounds,null);
	}

	/**
	 * ����TNND����ͼ
	 * 
	 * @param iv
	 * @param imageUrl
	 * @param drawable
	 *            ����ʧ��ʱĬ����ʾͼƬ
	 * @param maxwidth
	 *            �����,����ֵС�ڵ���0ʱ����
	 * @param maxheight
	 *            �����߶�,����ֵС�ڵ���0ʱ����
	 * @param lastlp
	 *            ����imageview ��ʾ�Ĳ�����ʽ
	 */
	public void setThumbnail(final ImageView iv, String imageUrl,
			final Drawable drawable, final int maxwidth, final int maxheight,
			final ScaleType st, final LayoutParams lastlp,final Boolean adjustViewBounds,
			final IImageServiceReceiver imageLoadeReceiver)
	{
		if (StringUtil.isEmpty(imageUrl))
		{
			if(imageLoadeReceiver!=null)
				imageLoadeReceiver.loadCompleted(CINT_LOAD_FAILURE);
			return;
		}

		final String filePath = Constants.CSTR_DATASTOREDIR.concat("images")
				.concat(File.separator).concat(FileUtil.getFileName(imageUrl));

		File localFile = new File(filePath);

		if (localFile.exists())
		{ // �ļ��Ѿ�����
			Bitmap bitmap = ImageUtil.getBitmapFromFile(filePath, maxwidth,
					maxheight);
			setImage(iv, bitmap, drawable, st,lastlp,adjustViewBounds);
			if(imageLoadeReceiver!=null)
				imageLoadeReceiver.loadCompleted(CINT_LOAD_SUCESS_LOCAL);
			return;
		}

		// �����ڣ������߳�����ͼƬ
		iv.setScaleType(ScaleType.CENTER);
		iv.setImageResource(R.drawable.progress_micro); // ���ü��ض���
		final AnimationDrawable ad = (AnimationDrawable) iv.getDrawable();

		FileUtil.isExistFolder(localFile.getParent());

		imageTask = new ImageTask(mContext, imageUrl, filePath,
				new IImageDownloader()
				{
					@Override
					public void downloadComplete(File file)
					{ // ���ؽ��
						if (file == null || !file.exists())
						{ // ����ʧ�ܣ�ͼƬ������
							setImage(iv, null, drawable, st,lastlp,adjustViewBounds);
							if(imageLoadeReceiver!=null)
								imageLoadeReceiver.loadCompleted(CINT_LOAD_FAILURE);
							return;
						}

						Bitmap bitmap = ImageUtil.getBitmapFromFile(filePath,
								maxwidth, maxheight);
						setImage(iv, bitmap, drawable, st,lastlp,adjustViewBounds);
						if(imageLoadeReceiver!=null)
							imageLoadeReceiver.loadCompleted(CINT_LOAD_SUCESS_NETWORK);
					}
				});
		imageTask.setProgressController(new IProgressController()
		{ // ����ͼƬ���ݶ�ȡ���Ƚӿ�
					@Override
					public void progressUpdate(int progress)
					{
						if (progress == -1)
						{ // �ӷ���˿�ʼ��ȡ���ݣ���������Ч��
							new Handler().post(new Runnable()
							{
								@Override
								public void run()
								{
									ad.start();
								}
							});
						} else if (progress >= 100)
						{ // ���ݻ�ȡ��ɣ�ֹͣ����
							ad.stop();
						}
					}
				});
		
		imageTask.execute(); // �����첽�߳�
	}
	
	/**
	 * ȡ��һ���߳�����
	 */
	public void isImageTask(){
		if(imageTask!=null){
			if(imageTask.getStatus()==Status.RUNNING){
				imageTask.setCanCancel(true);
				imageTask.setStop(true);
			}
		}
	}

	/**
	 * ������ɻص��ӿ�
	 */
	public interface IImageServiceReceiver
	{
		/**
		 * �������
		 * @param result�����ؽ����ֵΪCINT_LOAD_FAILURE CINT_LOAD_SUCESS_LOCAL CINT_LOAD_SUCESS_NETWORK
		 */
		public void loadCompleted(int result);
	}
}
