package com.wuzhupc.Sourcing.detail;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.Sourcing.R;
import com.wuzhupc.services.ImageService;
import com.wuzhupc.utils.ImageUtil;
import com.wuzhupc.utils.StringUtil;
import com.wuzhupc.widget.imagezoom.ImageViewTouch;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

/**
 * 图片查看
 * 
 * @note 必须传入参数image 图片路径 类型：String
 * @author wuzhu
 * 
 */
public class ViewImageActivity extends BaseActivity implements OnClickListener
{
	private String mImagePath;
	private ImageViewTouch mivt_photo;

	public static final String CSTR_EXTRA_IMAGE = "image";

	@Override
	protected void initDataContent()
	{
		ImageService is = new ImageService(this);
		is.setThumbnail(mivt_photo, mImagePath, null, ImageUtil.IMAGE_DEFLARGETWIDTH, ImageUtil.IMAGE_DEFLARGETHEIGHT, ScaleType.MATRIX, false);
	}

	@Override
	protected void initView()
	{
		// 设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_viewimage);
		// 获取Intent
		Intent intent = getIntent();
		// 获取图片路径
		mImagePath = intent.getStringExtra(CSTR_EXTRA_IMAGE);
		mivt_photo = (ImageViewTouch) findViewById(R.id.viewimage_ivt);
		mivt_photo.setScaleType(ScaleType.FIT_CENTER);
		mivt_photo.setExternalDoubleTapListener(new OnDoubleTapListener()
		{
			@Override
			public boolean onSingleTapConfirmed(MotionEvent e)
			{
				return false;
			}

			@Override
			public boolean onDoubleTapEvent(MotionEvent e)
			{
				return false;
			}

			@Override
			public boolean onDoubleTap(MotionEvent e)
			{
				operShow(findViewById(R.id.viewimage_top_rl).getVisibility() != View.VISIBLE);
				return true;
			}
		});
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.viewimage_bottom_save_ib:
			saveImage();
			break;
		case R.id.viewimage_top_back_ib:
			this.finish();
			break;
		case R.id.viewimage_imageoper_left_ib:
			rotateImage(-90);
			break;
		case R.id.viewimage_imageoper_right_ib:
			rotateImage(90);
			break;
		case R.id.viewimage_imageoper_zoomin_ib:
			zoominImage();
			break;
		case R.id.viewimage_imageoper_zoomout_ib:
			zoomoutImage();
			break;
		default:
			break;
		}
	}

	private void zoominImage()
	{
		float nowscale = Math.max(mivt_photo.getScale() * 0.5f, 1f);

		mivt_photo.zoomTo(nowscale, 500);
	}

	private void zoomoutImage()
	{
		float nowscale = Math.min(mivt_photo.getScale() * 2.0f, mivt_photo.getMaxZoom());

		mivt_photo.zoomTo(nowscale, 500);
	}

	/**
	 * 保存图片
	 */
	private void saveImage()
	{
		String picfile = "";
		Bitmap bitmap = mivt_photo.getBitmap();
		if (bitmap != null)
			picfile = ImageUtil.saveBitmap(bitmap, false);
		if (StringUtil.isEmpty(picfile))
			displayToast("图片存储失败!");
		else
			Toast.makeText(this, "图片保存完成，保存位置：" + picfile, Toast.LENGTH_LONG).show();
	}

	/**
	 * 旋转图片
	 * 
	 * @param rotate
	 *            旋转图片角度
	 */
	private Boolean rotateImage(int rotate)
	{
		if (rotate == 0)
			return true;
		Bitmap bitmap = mivt_photo.getBitmap();
		if (bitmap == null)
			return false;

		int bmpW = bitmap.getWidth();
		int bmpH = bitmap.getHeight();

		Matrix mt = new Matrix();
		mt.postRotate(rotate);
		Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bmpW, bmpH, mt, true);

		mivt_photo.setImageBitmapReset(resizeBmp, true);

		bitmap.recycle();

		return true;
	}

	/**
	 * 显示操作区域
	 * 
	 * @param bshow
	 *            true显示 false不显示
	 */
	private void operShow(Boolean bshow)
	{
		findViewById(R.id.viewimage_top_rl).setVisibility(bshow ? View.VISIBLE : View.GONE);
		findViewById(R.id.viewimage_bottom_ll).setVisibility(bshow ? View.VISIBLE : View.GONE);
	}
}
