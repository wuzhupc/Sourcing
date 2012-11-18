package com.wuzhupc.ui.dialog;

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
    public TextView mtv_content;
    public Button mbtn_left;
    public Button mbtn_right;
    public Button mbtn_center;
    
    public static final int BTN_TYPE_SURE = 0;
    
    public DialogInterface.OnClickListener onDialogClickListener;

    public BaseDialog(Context context)
    {
        super(context, R.style.Theme_WuzhuDialog_NoFrame);
        this.mContext = context;
        setContentView(R.layout.base_dialog);
        initContentView();
    }

    public BaseDialog(Context context, int styleId)
    {
        super(context, styleId);
        this.mContext = context;
    }
    
    public void initContentView()
    {
        mtv_title = (TextView) findViewById(R.id.dialog_title);
        mtv_content = (TextView) findViewById(R.id.simple_dialog_content_tv);
        mtv_content.setVisibility(View.GONE);
        mbtn_left = (Button) findViewById(R.id.btn_dialog_sure);
        mbtn_left.setOnClickListener(onClickListener);
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
    
    public void setContentText(CharSequence text)
    {
        mtv_content.setVisibility(View.VISIBLE);
        mtv_content.setText(text);
    }
    
    public void setContentText(int textId)
    {
        mtv_content.setVisibility(View.VISIBLE);
        mtv_content.setText(textId);
    }
    
    public void setContentTextSize(int size)
    {
        mtv_content.setTextSize(size);
    }
    
    public void setButtonText(int id, String text)
    {
        switch (id)
        {
        case BTN_TYPE_SURE:
            mbtn_left.setText(text);
        break;
        default:
        break;
        }
    }
    
    public void setOnDialogClickListener(DialogInterface.OnClickListener onDialogClickListener)
    {
        this.onDialogClickListener = onDialogClickListener;
    }
    
    public void confirm()
    {
        if (onDialogClickListener != null)
        {
            onDialogClickListener.onClick(this, R.id.btn_dialog_sure);
        }
        else
        {
            super.dismiss();
        }
    }
    
    
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
            case R.id.btn_dialog_sure:
                confirm();
            break;
            
            default:
            break;
            }
        }
    };
}
