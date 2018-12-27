package com.yy.activity;

import android.os.Bundle;

import com.yy.commentlist.CommentListAdapter;
import com.yy.commentlist.R;
import com.yy.data.DataBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CommentListActivity extends AppCompatActivity {
    RecyclerView rcv;
    CommentListAdapter commentListAdapter;
    List<DataBean> dataBeanList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list_layout);
        rcv = findViewById(R.id.rcv);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        commentListAdapter = new CommentListAdapter(this, R.layout.item_comment_layout);
        rcv.setAdapter(commentListAdapter);
        initData();
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            DataBean dataBean = new DataBean();
            for (int j = 0; j < 6; j++) {
                DataBean.InnerData innerData = new DataBean.InnerData();
                innerData.text = "测试评论  " + i + "  " + j;
                innerData.operation = 0;
                innerData.color = getResources().getColor(R.color.black);
                dataBean.commentList.add(innerData);
            }
            dataBean.showSize = 3;
            dataBeanList.add(dataBean);
        }
        commentListAdapter.setData(dataBeanList);
        commentListAdapter.notifyDataSetChanged();
    }
}
