package com.yy.commentlist;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class CommentViewGroup extends LinearLayout {
    public CommentViewGroup(Context context) {
        super(context);
        init();
    }

    public CommentViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CommentViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        setOrientation(VERTICAL);
    }

}
