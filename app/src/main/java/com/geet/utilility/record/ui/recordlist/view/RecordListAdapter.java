package com.geet.utilility.record.ui.recordlist.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geet.utilility.record.R;
import com.geet.utilility.record.data.model.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geetgobindsingh on 17/06/17.
 */

public class RecordListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Record> recordList;

    public RecordListAdapter() {
        recordList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_record_item, parent, false);
        return new RecordItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((RecordItemViewHolder) holder).bindData(recordList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return recordList.isEmpty() ? 0 : recordList.size();
    }


    public void updateList(List<Record> recordList) {
        this.recordList = new ArrayList<>(recordList);
        notifyDataSetChanged();
    }
}
