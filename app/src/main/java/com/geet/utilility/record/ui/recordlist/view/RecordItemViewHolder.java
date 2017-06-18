package com.geet.utilility.record.ui.recordlist.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.geet.utilility.record.R;
import com.geet.utilility.record.data.model.Record;

/**
 * Created by geetgobindsingh on 17/06/17.
 */

public class RecordItemViewHolder extends RecyclerView.ViewHolder {

    private TextView whatTextView;
    private TextView whyTextView;
    private TextView timeTakenTextView;

    public RecordItemViewHolder(View itemView) {
        super(itemView);
        whatTextView = (TextView) itemView.findViewById(R.id.record_what);
        whyTextView = (TextView) itemView.findViewById(R.id.record_why);
        timeTakenTextView = (TextView) itemView.findViewById(R.id.record_time_taken);
    }

    public void bindData(Record record, int position) {
        whatTextView.setText(record.getWhat());
        whyTextView.setText(record.getWhy());
        timeTakenTextView.setText(record.getRecordTime());
    }
}
