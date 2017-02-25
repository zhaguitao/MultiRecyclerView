package com.jiang.android.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jiang.android.multirecyclerview.MultiRecyclerView;
import com.jiang.android.multirecyclerview.OtherStateBindImpl;
import com.jiang.android.multirecyclerview.adapter.BaseAdapter;
import com.jiang.android.multirecyclerview.adapter.BaseViewHolder;
import com.jiang.android.multirecyclerview.inter.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<String> mDatas = new ArrayList<>();
    private Handler mHandler  = new Handler();
    private MultiRecyclerView recyclerView;
    private Button empty;
    private Button content;
    private Button loading;
    private Button error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        empty = (Button) findViewById(R.id.empty);
        content = (Button) findViewById(R.id.content);
        loading = (Button) findViewById(R.id.loading);
        error = (Button) findViewById(R.id.error);

        empty.setOnClickListener(this);
        content.setOnClickListener(this);
        loading.setOnClickListener(this);
        error.setOnClickListener(this);
        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        recyclerView = (MultiRecyclerView) findViewById(R.id.recyclerview);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDatas.clear();
                        initData();
                        p=0;
                        refreshLayout.setRefreshing(false);
                        recyclerView.setViewState(MultiRecyclerView.ViewState.CONTENT);

                    }
                },2000);
            }
        });
        recyclerView.setViewState(MultiRecyclerView.ViewState.LOADING);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initData();


                recyclerView.setOtherStateBindListener(new OtherStateBindImpl() {
                    @Override
                    public void onBindView(BaseViewHolder holder, MultiRecyclerView.ViewState currentState) {
                        switch (currentState){
                            case EMPTY:
                                TextView tv = holder.getView(R.id.empty);
                                tv.setText("custom empty text");
                                break;
                            case ERROR:
                                TextView tv2 = holder.getView(R.id.error);
                                tv2.setText("custom error text");
                                break;
                            case LOADING:
                                TextView tv3 = holder.getView(R.id.loading);
                                tv3.setText("custom loading text");
                                break;
                        }
                    }
                });

        recyclerView.setViewState(MultiRecyclerView.ViewState.CONTENT);
                recyclerView.setLoadMoreEnabled(true);
                recyclerView.setAdapter(new BaseAdapter() {
                    @Override
                    public void onBindView(BaseViewHolder holder, int position) {
                        TextView textView = holder.getView(R.id.number);
                        textView.setText(mDatas.get(position));
                    }

                    @Override
                    public int getLayoutID(int position) {
                        return R.layout.item;
                    }

                    @Override
                    public boolean clickable() {
                        return true;
                    }

                    @Override
                    public void onItemClick(View v, int position) {
                        super.onItemClick(v, position);
                        Toast.makeText(MainActivity.this, "you clicked:"+position, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public int getItemCount() {
                        return mDatas.size();
                    }
                });
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,3));
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        recyclerView.loadMoreComplete();
                        if(p == 5){
                            recyclerView.setLoadMoreEnabled(false);

                        }
                    }
                },2000);

            }
        });

            }
        },5000);

    }

    int p = 0;
    private void initData() {
        p++;
        for (int i = 0; i < 30; i++) {
            mDatas.add("第"+i+"条");
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.error:
                recyclerView.setViewState(MultiRecyclerView.ViewState.ERROR);
                break;
            case R.id.content:
                recyclerView.setViewState(MultiRecyclerView.ViewState.CONTENT);
                break;
            case R.id.empty:
                recyclerView.setViewState(MultiRecyclerView.ViewState.EMPTY);
                break;
            case R.id.loading:
                recyclerView.setViewState(MultiRecyclerView.ViewState.LOADING);
                break;


        }
    }
}
