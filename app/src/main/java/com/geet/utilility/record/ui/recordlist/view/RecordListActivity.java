package com.geet.utilility.record.ui.recordlist.view;


import android.arch.lifecycle.Observer;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.geet.utilility.record.R;
import com.geet.utilility.record.data.model.Record;
import com.geet.utilility.record.ui.addrecord.view.AddRecordActivity;
import com.geet.utilility.record.ui.base.BaseActivity;
import com.geet.utilility.record.ui.base.BasePresenter;
import com.geet.utilility.record.ui.recordlist.presenter.RecordListPresenter;
import com.geet.utilility.record.widget.EmptyRecyclerView;

import java.util.List;

public class RecordListActivity extends BaseActivity implements IRecordListView, View.OnClickListener {

    //region Member variables
    private static final String TAG = RecordListActivity.class.getSimpleName();
    private RecordListPresenter mRecordListPresenter;
    private RecordListAdapter mRecordListAdapter;
    //endregion

    //region View variables
    SwipeRefreshLayout mSwipeRefreshContainer;
    ProgressBar mRecordListProgressBar;
    EmptyRecyclerView mRecordListRecyclerView;
    View mRecordListEmptyView;
    FloatingActionButton mAddFab;
    //endregion

    //region Activity Lifecycle methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);

        //Presenter initialisation
        initializePresenter();

        //view initialisation
        mSwipeRefreshContainer = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_container);
        mRecordListProgressBar = (ProgressBar) findViewById(R.id.record_list_progressbar);
        mRecordListRecyclerView = (EmptyRecyclerView) findViewById(R.id.record_list);
        mRecordListEmptyView = findViewById(R.id.empty_view);
        mAddFab = (FloatingActionButton) findViewById(R.id.fab_add);

        //RecyclerView setup
        mRecordListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecordListRecyclerView.setEmptyViewWithImage(mRecordListEmptyView, R.string.no_record_found, R.drawable.vector_no_record_found_24dp, R.string.click_on_add);
        mRecordListAdapter = new RecordListAdapter();
        mRecordListRecyclerView.setAdapter(mRecordListAdapter);

        //adding Click Listener to fab button
        mAddFab.setOnClickListener(this);

        mRecordListRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 && mAddFab.isShown()) {
                    mAddFab.hide();
                } else {
                    mAddFab.show();
                }
            }
        });
        mSwipeRefreshContainer.setColorSchemeResources(R.color.colorPrimary);

        mSwipeRefreshContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
             mRecordListPresenter.loadRecordList(true);
            }
        });

        getPresenter().getAllRecords().observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object obj) {
                mRecordListAdapter.updateList((List<Record>) obj);
            }
        });
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

    //region Overriding IRecordList methods
    @Override
    public void showRecordList(List<Record> records) {
        //wondering if this in need still
    }

    @Override
    public void showProgressBar(boolean value) {
        mSwipeRefreshContainer.setRefreshing(value && mRecordListAdapter.getItemCount() != 0);
        mRecordListProgressBar.setVisibility((value && mRecordListAdapter.getItemCount() == 0) ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showErrorMessage() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.dialog_error_title))
                .setMessage(R.string.dialog_error_message)
                .setNeutralButton(R.string.dialog_action_ok, null);
        alertDialog.create().show();
    }
    //endregion

    //region Overriding IView methods
    @Override
    public void initializePresenter() {
        mRecordListPresenter = new RecordListPresenter(RecordListActivity.this);
    }

    @Override
    public RecordListPresenter getPresenter() {
        return mRecordListPresenter;
    }
    //endregion
}
