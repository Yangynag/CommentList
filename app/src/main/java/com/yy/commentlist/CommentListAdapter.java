package com.yy.commentlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yy.data.DataBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommentListAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    public List<DataBean> dataBeanList;
    Context context;
    LayoutInflater mLayoutInflater;
    int layId;
    View view;

    public CommentListAdapter(Context context, int layId) {
        this.context = context;
        this.layId = layId;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<DataBean> dataBeanList) {
        convertData(dataBeanList);
    }

    private void convertData(List<DataBean> dataBeanList) {
        int size = dataBeanList.size();
        for (int i = 0; i < size; i++) {
            int singleCommentSize = dataBeanList.get(i).commentList.size();
            DataBean.InnerData innerData = new DataBean.InnerData();
            innerData.text = "展示更多";
            innerData.operation = 1;
            innerData.color = context.getResources().getColor(R.color.blue);
            dataBeanList.get(i).commentList.add(innerData);
        }
        this.dataBeanList = dataBeanList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = mLayoutInflater.inflate(layId, parent, false);
        return new MyDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final MyDataViewHolder myDataViewHolder = (MyDataViewHolder) holder;
        final List<DataBean.InnerData> commentsList = dataBeanList.get(position).commentList;
        final int size = commentsList.size();
        final int showSize = dataBeanList.get(position).showSize;
        for (int i = 0; i < showSize; i++) {
            TextView textView = new TextView(context);
            textView.setText(commentsList.get(i).text);
            textView.setTextColor(commentsList.get(i).color);
            myDataViewHolder.commentViewGroup.addView(textView, i);
        }

        final TextView textView = new TextView(context);
        textView.setText(commentsList.get(size - 1).text);
        textView.setTextColor(commentsList.get(size - 1).color);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (commentsList.get(size - 1).operation == 1) {
                    if (myDataViewHolder.viewStub.getParent() != null){
                        myDataViewHolder.viewStub.inflate();
                        myDataViewHolder.lrInvisible = myDataViewHolder.itemView.findViewById(R.id.lrcontent1);
                        for (int j = showSize; j < size - 1; j++) {
                            TextView textView = new TextView(context);
                            textView.setText(commentsList.get(j).text);
                            textView.setTextColor(commentsList.get(j).color);
                            myDataViewHolder.lrInvisible.addView(textView);
                        }
                    }

                    dataBeanList.get(position).commentList.get(size - 1).operation = 2;
                    dataBeanList.get(position).commentList.get(size - 1).text = "隐藏";
                    myDataViewHolder.lrInvisible.setVisibility(View.VISIBLE);
                } else {
                    dataBeanList.get(position).commentList.get(size - 1).operation = 1;
                    dataBeanList.get(position).commentList.get(size - 1).text = "显示更多";
                    myDataViewHolder.lrInvisible.setVisibility(View.GONE);
                }
                textView.setText(dataBeanList.get(position).commentList.get(size - 1).text);

            }
        });
        myDataViewHolder.commentViewGroup.addView(textView);
    }

    @Override
    public int getItemCount() {
        return dataBeanList == null ? 0 : dataBeanList.size();
    }

    @Override
    public void onClick(View v) {

    }

    class MyDataViewHolder extends RecyclerView.ViewHolder {
        LinearLayout commentViewGroup;
        ViewStub viewStub;
        LinearLayout lrInvisible;

        public MyDataViewHolder(@NonNull View itemView) {
            super(itemView);
            commentViewGroup = itemView.findViewById(R.id.lrcontent);
            viewStub = itemView.findViewById(R.id.viewstub);

        }
    }
}
