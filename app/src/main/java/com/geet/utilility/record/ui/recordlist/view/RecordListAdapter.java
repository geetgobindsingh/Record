package com.geet.utilility.record.ui.recordlist.view;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.geet.utilility.record.data.model.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geetgobindsingh on 17/06/17.
 */

public class RecordListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Record> recordList;

    public RecordListAdapter() {
        recordList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }
}
