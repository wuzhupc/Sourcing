package com.wuzhupc.utils;

import android.graphics.Color;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class UIUtil
{
    /**
     * ���ñ���Ϊ����
     */
    public static void setTitleTextBold(TextView tv)
    {
    	if(tv!=null)
    		tv.getPaint().setFakeBoldText(true);
    }
    
    public static int lightenColor(int color) {
        final float [] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[1] = 0.1F;
        hsv[2] = 1.0F;
        return Color.HSVToColor(hsv);
    }
    
    public static int darkenColor(int color) {
        final float [] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[1] = 1.0F;
        hsv[2] = 0.5F;
        return Color.HSVToColor(hsv);
    }

    /**
     * Populate the given {@link TextView} with the requested text, formatting
     * through {@link Html#fromHtml(String)} when applicable. Also sets
     * {@link TextView#setMovementMethod} so inline links are handled.
     */
    public static void setTextMaybeHtml(TextView view, String text) {
        if (text.contains("<") && text.contains(">")) {
            view.setText(Html.fromHtml(text));
            view.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            view.setText(text);
        }
    }
}
