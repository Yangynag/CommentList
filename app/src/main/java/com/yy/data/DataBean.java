package com.yy.data;

import java.util.ArrayList;
import java.util.List;

public class DataBean {
    public int showSize = 3;
    public List<InnerData> commentList=new ArrayList<>();

    public static class InnerData {
        public String text;
        public int operation;
        public int color;
    }
}
