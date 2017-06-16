package com.geet.utilility.record.ui.recordlist.view;


import android.arch.lifecycle.ViewModelProviders;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.geet.utilility.record.R;
import com.geet.utilility.record.ui.addrecord.view.AddRecordActivity;
import com.geet.utilility.record.ui.base.BaseActivity;
import com.geet.utilility.record.ui.recordlist.viewmodel.RecordListViewModel;
import com.geet.utilility.record.widget.EmptyRecyclerView;

public class RecordListActivity extends BaseActivity implements View.OnClickListener {

    //region Member variables
    private static final String TAG = RecordListActivity.class.getSimpleName();
    private RecordListViewModel recordListViewModel;
    //endregion

    //region View variables
    EmptyRecyclerView mRecordRecyclerView;
    View mRecordEmptyView;
    FloatingActionButton mAddFab;
    //endregion

    //region Activity Lifecycle methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);

        //ViewHolder initialisation
        recordListViewModel = ViewModelProviders.of(this).get(RecordListViewModel.class);

        //view initialisation
        mRecordRecyclerView = (EmptyRecyclerView) findViewById(R.id.record_list);
        mRecordEmptyView = findViewById(R.id.empty_view);
        mAddFab = (FloatingActionButton) findViewById(R.id.fab_add);

        //RecyclerView setup
        mRecordRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecordRecyclerView.setEmptyViewWithImage(mRecordEmptyView, R.string.no_record_found, R.drawable.vector_no_record_found_24dp, R.string.click_on_add);
        mRecordRecyclerView.setAdapter(new RecordListAdapter());

        //adding Click Listener to fab button
        mAddFab.setOnClickListener(this);
    }
    //endregion

    //region Overriding Click Listener method
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.fab_add) {
            AddRecordActivity.navigateTo(RecordListActivity.this);
        }
    }
    //endregion
}
