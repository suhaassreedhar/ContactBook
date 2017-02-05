package com.example.suhaas.contactbook.ui.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.example.suhaas.contactbook.R;
import com.example.suhaas.contactbook.data.model.Contacts;
import com.example.suhaas.contactbook.ui.base.BaseActivity;
import com.example.suhaas.contactbook.ui.common.ErrorView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements MainMvpView, ContactListAdapter.ClickListener,
        ErrorView.ErrorListener {

    private static final int CONTACT_COUNT = 25;

    @Inject
    ContactListAdapter mContactListAdapter;
    @Inject
    MainPresenter mMainPresenter;

    @BindView(R.id.view_error)
    ErrorView mErrorView;
    @BindView(R.id.progress)
    ProgressBar mProgress;
    @BindView(R.id.rvList)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_to_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        mMainPresenter.attachView(this);

        setSupportActionBar(mToolbar);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_to_refresh);

        mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.colorPrimary);
//        mSwipeRefreshLayout.setColorSchemeResources(R.color.white);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mMainPresenter.getContacts(CONTACT_COUNT);
            }
        });

        mContactListAdapter.setClickListener(this);
        mErrorView = (ErrorView) findViewById(R.id.view_error);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvList);
        mProgress = (ProgressBar) findViewById(R.id.progress);
        if(mRecyclerView != null) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        mRecyclerView.setAdapter(mContactListAdapter);

        mErrorView.setErrorListener(this);

        mMainPresenter.getContacts(CONTACT_COUNT);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }

    @Override
    public void showContacts(List<Contacts> contacts) {
        mContactListAdapter.setContacts(contacts);
        mContactListAdapter.notifyDataSetChanged();

        mRecyclerView.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            if (mRecyclerView.getVisibility() == View.VISIBLE
                    && mContactListAdapter.getItemCount() > 0) {
                mSwipeRefreshLayout.setRefreshing(true);
            } else {
                mProgress.setVisibility(View.VISIBLE);

                mRecyclerView.setVisibility(View.GONE);
                mSwipeRefreshLayout.setVisibility(View.GONE);
            }

            mErrorView.setVisibility(View.GONE);
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
            mProgress.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(Throwable error) {
        mRecyclerView.setVisibility(View.GONE);
        mSwipeRefreshLayout.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
        Timber.e(error, "There was an error retrieving the contacts");
    }

    @Override
    public void onContactClick(Contacts contacts) {
//        startActivity(DetailActivity.getStartIntent(this, contacts));
    }

    @Override
    public void onReloadData() {
        mMainPresenter.getContacts(CONTACT_COUNT);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
