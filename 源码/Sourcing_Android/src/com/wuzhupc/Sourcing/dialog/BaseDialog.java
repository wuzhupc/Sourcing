package com.wuzhupc.Sourcing.dialog;

import com.wuzhupc.Sourcing.R;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-18 下午11:14:47
 */
public class BaseDialog extends Dialog
{
    public Context mContext;
    
    public TextView mtv_title;
    public TextView mtv_message;
    public Button mbtn_left;
    public Button mbtn_right;
    public Button mbtn_center;
    
    public static final int BTN_TYPE_LEFT = 0;
    public static final int BTN_TYPE_CENTER = 1;
    public static final int BTN_TYPE_RIGHT = 2;
    
    public static final int DIALOG_TYPE_ONEBTN = 1;
    public static final int DIALOG_TYPE_TWOBTN = 2;
    public static final int DIALOG_TYPE_THREEBTN = 3;
    
    public DialogInterface.OnClickListener onDialogOneBtnClickListener;
    public DialogInterface.OnClickListener onDialogTwoBtnClickListener;
    public DialogInterface.OnClickListener onDialogThreeBtnClickListener;

    public BaseDialog(Context context)
    {
        super(context, R.style.Theme_WuzhuDialog_NoFrame);
        this.mContext = context;
        setContentView(R.layout.dialog_base);
        initContentView();
        setCanceledOnTouchOutside(false);
    }
    
    /**
     * 
     * @param context
     * @param dialog_type 对话框类型
     */
    public BaseDialog(Context context,int dialog_type)
    {
        super(context, R.style.Theme_WuzhuDialog_NoFrame);
        this.mContext = context;
        setContentView(R.layout.dialog_base);
        initContentView();
        switch (dialog_type)
		{
		case DIALOG_TYPE_ONEBTN:
	        (findViewById(R.id.dialog_footview_spaceone_ll)).setVisibility(View.GONE);
	        (findViewById(R.id.dialog_footview_spacetwo_ll)).setVisibility(View.GONE);
	        mbtn_center.setVisibility(View.GONE);
	        mbtn_right.setVisibility(View.GONE);
			break;
		case DIALOG_TYPE_THREEBTN:
	        (findViewById(R.id.dialog_footview_spaceone_ll)).setVisibility(View.VISIBLE);
	        (findViewById(R.id.dialog_footview_spacetwo_ll)).setVisibility(View.VISIBLE);
	        mbtn_center.setVisibility(View.VISIBLE);
	        mbtn_right.setVisibility(View.VISIBLE);
			break;
		default:
	        (findViewById(R.id.dialog_footview_spaceone_ll)).setVisibility(View.VISIBLE);
	        (findViewById(R.id.dialog_footview_spacetwo_ll)).setVisibility(View.GONE);
	        mbtn_center.setVisibility(View.GONE);
	        mbtn_right.setVisibility(View.VISIBLE);
			break;
		}       
        setCanceledOnTouchOutside(false);
    }
    
    public void initContentView()
    {
        mtv_title = (TextView) findViewById(R.id.dialog_title);
        mtv_message = (TextView) findViewById(R.id.dialog_message);
        mbtn_left = (Button) findViewById(R.id.dialog_footview_btn_left);        
        mbtn_center = (Button) findViewById(R.id.dialog_footview_btn_center);
        mbtn_right = (Button) findViewById(R.id.dialog_footview_btn_right);
        mbtn_left.setOnClickListener(onClickListener);
        mbtn_right.setOnClickListener(onClickListener);
        mbtn_center.setOnClickListener(onClickListener);
    }
    
    public void setHasTitle(boolean hastitle)
    {
    	findViewById(R.id.dialog_title_ll).setVisibility(hastitle?View.VISIBLE:View.GONE);
    }
    
    @Override
    public void setTitle(CharSequence title)
    {
        mtv_title.setText(title);
    }
    
    @Override
    public void setTitle(int titleId)
    {
        mtv_title.setText(titleId);
    }
    
    public void setMessage(CharSequence text)
    {
    	mtv_message.setText(text);
    }
    
    public void setMessage(int textId)
    {
    	mtv_message.setText(textId);
    }
    
    public void setBtnText(int btn_type, int textid)
    {
        switch (btn_type)
        {
        case BTN_TYPE_LEFT:
            mbtn_left.setText(textid);
        break;

        case BTN_TYPE_RIGHT:
            mbtn_right.setText(textid);
        break;

        case BTN_TYPE_CENTER:
            mbtn_center.setText(textid);
        break;
        }
    }
    
    public void setBtnText(int btn_type, String text)
    {
        switch (btn_type)
        {
        case BTN_TYPE_LEFT:
            mbtn_left.setText(text);
        break;

        case BTN_TYPE_RIGHT:
            mbtn_right.setText(text);
        break;

        case BTN_TYPE_CENTER:
            mbtn_center.setText(text);
        break;
        }
    }
    
    public void setOnDialogClickListener(DialogInterface.OnClickListener onDialogClickListener,int btn_type)
    {
    	switch (btn_type)
		{
		case BTN_TYPE_LEFT:
			this.onDialogOneBtnClickListener = onDialogClickListener;
			break;
		case BTN_TYPE_RIGHT:
			this.onDialogThreeBtnClickListener= onDialogClickListener;
			break;
		case BTN_TYPE_CENTER:
			this.onDialogTwoBtnClickListener = onDialogClickListener;
			break;
		}
    }
    
    private void confirm(int btn_type)
    {
        if (btn_type==BTN_TYPE_LEFT && onDialogOneBtnClickListener != null)
        {
        	onDialogOneBtnClickListener.onClick(this, R.id.dialog_footview_btn_left);
        	return;
        }
        if (btn_type==BTN_TYPE_RIGHT && onDialogThreeBtnClickListener != null)
        {
        	onDialogThreeBtnClickListener.onClick(this, R.id.dialog_footview_btn_right);
        	return;
        }
        if (btn_type==BTN_TYPE_CENTER && onDialogTwoBtnClickListener != null)
        {
        	onDialogTwoBtnClickListener.onClick(this, R.id.dialog_footview_btn_center);
        	return;
        }
        super.dismiss();
    }
    
    
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
            case R.id.dialog_footview_btn_left:
                confirm(BTN_TYPE_LEFT);
            break;

            case R.id.dialog_footview_btn_right:
                confirm(BTN_TYPE_RIGHT);
            break;

            case R.id.dialog_footview_btn_center:
                confirm(BTN_TYPE_CENTER);
            break;
            }
        }
    };
}
