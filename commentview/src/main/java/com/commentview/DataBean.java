package com.commentview;

import java.util.ArrayList;
import java.util.List;

public class DataBean {
    public List<InnerData> commentList=new ArrayList<>();
    public static class InnerData {
        //评论内容
        public String text;
        public int operation;
        public int color;
    }
}
