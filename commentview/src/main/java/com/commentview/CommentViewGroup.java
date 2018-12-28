package com.commentview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
public class CommentViewGroup extends LinearLayout implements View.OnClickListener {
    LayoutInflater mLayoutInflater;
    int showLength;
    Context context;
    String operate1;
    String operate2;
    int operateColor;

    public CommentViewGroup(Context context) {
        super(context);
    }

    ViewStub viewStub;

    public void setViewStub(ViewStub viewStub) {
        this.viewStub = viewStub;
    }

    public CommentViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommentView);
        if (typedArray != null) {
            showLength = typedArray.getInt(R.styleable.CommentView_showLength, 0);
            operateColor = typedArray.getInt(R.styleable.CommentView_operateColor, ContextCompat.getColor(context, R.color.black));
            typedArray.recycle();
        }
        init(context);
    }

    public CommentViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
        operate1 = context.getString(R.string.show_more);
        operate2 = context.getString(R.string.hide);
    }

    public void addData(DataBean dataBean) {
        conVertData(dataBean);
    }

    int cellCommentsSize = 0;
    DataBean dataBean;

    private void conVertData(DataBean dataBean) {
        cellCommentsSize = dataBean == null ? 0 : dataBean.commentList.size();
        if (showLength < cellCommentsSize) {
            operateData = new DataBean.InnerData();
            operateData.text = operate1;
            operateData.operation = 1;
            operateData.color = operateColor;
            dataBean.commentList.add(operateData);
            cellCommentsSize = dataBean.commentList.size();
            this.dataBean = dataBean;
            for (int j = 0; j < showLength; j++) {
                DataBean.InnerData tempData = dataBean.commentList.get(j);
                TextView textView = new TextView(context);
                textView.setText(tempData.text);
                textView.setTextColor(tempData.color);
                addView(textView, j);
            }
            TextView textView = new TextView(context);
            textView.setText(operateData.text);
            textView.setTextColor(operateData.color);
            textView.setOnClickListener(this);
            addView(textView);
        }
    }

    DataBean.InnerData operateData;
    LinearLayout lr = null;

    @Override
    public void onClick(View v) {
        if (viewStub.getParent() != null) {
            viewStub.inflate();
            lr = findViewById(R.id.lr_content);
            for (int j = showLength; j < cellCommentsSize - 1; j++) {
                DataBean.InnerData tempData = dataBean.commentList.get(j);
                TextView textView = new TextView(context);
                textView.setText(tempData.text);
                textView.setTextColor(tempData.color);
                operateData.text = operate2;
                operateData.operation = 0;
                lr.addView(textView);
            }
        } else {
            if (operateData.operation == 1) {
                operateData.text = operate2;
                operateData.operation = 0;
                lr.setVisibility(VISIBLE);
            } else {
                operateData.text = operate1;
                operateData.operation = 1;
                lr.setVisibility(GONE);
            }


        }
        TextView tv = (TextView) v;
        tv.setText(operateData.text);

    }

    public interface operateClickListener {
        void operate(int operateType);
    }
}
