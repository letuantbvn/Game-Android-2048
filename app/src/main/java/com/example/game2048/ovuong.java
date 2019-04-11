package com.example.game2048;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.TextView;

public class ovuong extends android.support.v7.widget.AppCompatTextView {
    public ovuong(Context context) {
        super(context);
    }

    public ovuong(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    public ovuong(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int dai=getMeasuredWidth();
        setMeasuredDimension(dai,dai);
    }
    public void setT(int so){
        if (so< 100){
            setTextSize(40);
        }
        else if (so<1000){
            setTextSize(35);
        }else if (so<10000){
            setTextSize(25);
        }
        else
            if (so>=10000){
                setTextSize(20);
            }
        if (so<8){
            setTextColor(Color.BLACK);
        }else if (so>=8){
            setTextColor(Color.WHITE);
        }
        //mau o bo
        GradientDrawable drawable=(GradientDrawable)this.getBackground();
        drawable.setColor(Datagame.getDatagame().colorr(so));
        setBackground(drawable);
        if (so==0){
            setText(" ");
        }else{
            setText(""+so);

        }
    }
}
