package com.example.fanzhiyong20200414.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fanzhiyong20200414.R;

/**
 * 流式布局代码
 */
public class FlowLayout extends FrameLayout {

    private int color;
    private float size;

    public FlowLayout(@NonNull Context context) {
        super(context);
    }

    public FlowLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //自定义属性部分
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);
        color = typedArray.getColor(R.styleable.FlowLayout_flow_color, Color.RED);
        size = typedArray.getDimension(R.styleable.FlowLayout_flow_size, 50);

    }

    public FlowLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //自定义属性部分
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);
        color = typedArray.getColor(R.styleable.FlowLayout_flow_color, Color.RED);
        size = typedArray.getDimension(R.styleable.FlowLayout_flow_size, 50);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        //计算控件总宽度
        int sumWidth = 0;
        int width = getWidth();
        int lines = 0;

        //遍历数据
        for (int i = 0; i < getChildCount(); i++) {
            //获取当前下标
            View child = getChildAt(i);

            //判断控件的宽度是否超过了屏幕的宽度
            if (sumWidth+child.getWidth()<width){
                //这是未超出屏幕宽度的情况
                child.layout(sumWidth,lines*child.getHeight()
                  ,sumWidth+child.getWidth(),lines*child.getHeight()+child.getHeight());
                sumWidth+=child.getWidth();
            }else {
                //否则就是超出了屏幕的宽度,需要重新计算宽度
                sumWidth=0;
                lines++;//行数累加
                //布局摆放的位置
                child.layout(sumWidth,lines*child.getHeight()
                        ,sumWidth+child.getWidth(),lines*child.getHeight()+child.getHeight());
                sumWidth+=child.getWidth();
            }
        }


    }

    public void addText(final String text){
        //加载自定义条目布局
        TextView textView = (TextView) View.inflate(getContext(), R.layout.flow_item, null);
        //设置文字
        textView.setText(text);
        //设置颜色
        textView.setTextColor(color);
        //设置字体大小
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,size);
        //设置点击事件
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flowClickener!=null){
                    flowClickener.FlowClick(text);
                }
            }
        });
        //设置布局
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //添加数据
        addView(textView,layoutParams);
    }

    private onFlowClickener flowClickener;

    public void setFlowClickener(onFlowClickener flowClickener) {
        this.flowClickener = flowClickener;
    }

    public interface onFlowClickener{
        void FlowClick(String text);
    }
}
