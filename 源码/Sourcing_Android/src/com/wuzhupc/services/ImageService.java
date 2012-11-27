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
	 * 加载失败
	 */
	public static final int CINT_LOAD_FAILURE=0;
	/**
	 * 加载成功－网络加载
	 */
	public static final int CINT_LOAD_SUCESS_LOCAL=1;
	/**
	 * 加载成功－网络加载
	 */
	public static final int CINT_LOAD_SUCESS_NETWORK=2;
	
	ImageTask imageTask;

	private Context mContext;
	public ImageService(Context ctx)
	{
		this.mContext = ctx;
	}

	/**
	 * 设置TNND缩略图
	 * 
	 * @param iv
	 * @param imageUrl
	 * @param drawable
	 *            加载失败时默认显示图片
	 */
	public void setThumbnail(final ImageView iv, String imageUrl,
			final Drawable drawable)
	{
		setThumbnail(iv, imageUrl, drawable, 0, 0);
	}

	/**
	 * 设置图像
	 * 
	 * @param iv
	 * @param bm
	 * @param defdrawable
	 * @param lp
	 *            iv的样式
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
	 * 设置TNND缩略图
	 * 
	 * @param iv
	 * @param imageUrl
	 * @param drawable
	 *            加载失败时默认显示图片
	 * @param maxwidth
	 *            最大宽度,设置值小于等于0时忽略
	 * @param maxheight
	 *            　最大高度,设置值小于等于0时忽略
	 */
	public void setThumbnail(final ImageView iv, String imageUrl,
			final Drawable drawable, final int maxwidth, final int maxheight)
	{
		setThumbnail(iv, imageUrl, drawable, maxwidth, maxheight,
				ScaleType.CENTER);//_CROP
	}

	/**
	 * 设置TNND缩略图
	 * 
	 * @param iv
	 * @param imageUrl
	 * @param drawable
	 *            加载失败时默认显示图片
	 * @param maxwidth
	 *            最大宽度,设置值小于等于0时忽略
	 * @param maxheight
	 *            　最大高度,设置值小于等于0时忽略
	 */
	public void setThumbnail(final ImageView iv, String imageUrl,
			final Drawable drawable, final int maxwidth, final int maxheight,LayoutParams lastlp)
	{
		setThumbnail(iv, imageUrl, drawable, maxwidth, maxheight,
				ScaleType.CENTER_CROP,lastlp,true);
	}

	/**
	 * 设置TNND缩略图
	 * 
	 * @param iv
	 * @param imageUrl
	 * @param drawable
	 *            加载失败时默认显示图片
	 * @param maxwidth
	 *            最大宽度,设置值小于等于0时忽略
	 * @param maxheight
	 *            　最大高度,设置值小于等于0时忽略
	 */
	public void setThumbnail(final ImageView iv, String imageUrl,
			final Drawable drawable, final int maxwidth, final int maxheight,
			final ScaleType st)
	{
		setThumbnail(iv, imageUrl, drawable, maxwidth, maxheight, st, null,true);
	}

	/**
	 * 设置TNND缩略图
	 * 
	 * @param iv
	 * @param imageUrl
	 * @param drawable
	 *            加载失败时默认显示图片
	 * @param maxwidth
	 *            最大宽度,设置值小于等于0时忽略
	 * @param maxheight
	 *            　最大高度,设置值小于等于0时忽略
	 * @param adjustViewBounds 图像是否自动对齐控件边框，如果是ScaleType 是MATRIX 需要设置为false
	 */
	public void setThumbnail(final ImageView iv, String imageUrl,
			final Drawable drawable, final int maxwidth, final int maxheight,
			final ScaleType st,final Boolean adjustViewBounds)
	{
		setThumbnail(iv, imageUrl, drawable, maxwidth, maxheight, st, null,adjustViewBounds);
	}

	/**
	 * 设置TNND缩略图
	 * 
	 * @param iv
	 * @param imageUrl
	 * @param drawable
	 *            加载失败时默认显示图片
	 * @param maxwidth
	 *            最大宽度,设置值小于等于0时忽略
	 * @param maxheight
	 *            　最大高度,设置值小于等于0时忽略
	 * @param lastlp
	 *            最终imageview 显示的布局样式
	 */
	public void setThumbnail(final ImageView iv, String imageUrl,
			final Drawable drawable, final int maxwidth, final int maxheight,
			final ScaleType st, final LayoutParams lastlp,final Boolean adjustViewBounds)
	{
		setThumbnail(iv, imageUrl, drawable, maxwidth, maxheight, st, lastlp,adjustViewBounds,null);
	}

	/**
	 * 设置TNND缩略图
	 * 
	 * @param iv
	 * @param imageUrl
	 * @param drawable
	 *            加载失败时默认显示图片
	 * @param maxwidth
	 *            最大宽度,设置值小于等于0时忽略
	 * @param maxheight
	 *            　最大高度,设置值小于等于0时忽略
	 * @param lastlp
	 *            最终imageview 显示的布局样式
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
		{ // 文件已经存在
			Bitmap bitmap = ImageUtil.getBitmapFromFile(filePath, maxwidth,
					maxheight);
			setImage(iv, bitmap, drawable, st,lastlp,adjustViewBounds);
			if(imageLoadeReceiver!=null)
				imageLoadeReceiver.loadCompleted(CINT_LOAD_SUCESS_LOCAL);
			return;
		}

		// 不存在，启动线程下载图片
		iv.setScaleType(ScaleType.CENTER);
		iv.setImageResource(R.drawable.progress_micro); // 设置加载动画
		final AnimationDrawable ad = (AnimationDrawable) iv.getDrawable();

		FileUtil.isExistFolder(localFile.getParent());

		imageTask = new ImageTask(mContext, imageUrl, filePath,
				new IImageDownloader()
				{
					@Override
					public void downloadComplete(File file)
					{ // 下载结果
						if (file == null || !file.exists())
						{ // 下载失败，图片不存在
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
		{ // 设置图片数据读取进度接口
					@Override
					public void progressUpdate(int progress)
					{
						if (progress == -1)
						{ // 从服务端开始获取数据，启动动画效果
							new Handler().post(new Runnable()
							{
								@Override
								public void run()
								{
									ad.start();
								}
							});
						} else if (progress >= 100)
						{ // 数据获取完成，停止动画
							ad.stop();
						}
					}
				});
		
		imageTask.execute(); // 启动异步线程
	}
	
	/**
	 * 取消一步线程下载
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
	 * 加载完成回调接口
	 */
	public interface IImageServiceReceiver
	{
		/**
		 * 加载完成
		 * @param result　加载结果，值为CINT_LOAD_FAILURE CINT_LOAD_SUCESS_LOCAL CINT_LOAD_SUCESS_NETWORK
		 */
		public void loadCompleted(int result);
	}
}
