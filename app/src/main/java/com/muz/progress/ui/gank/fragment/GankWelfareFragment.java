package com.muz.progress.ui.gank.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.muz.progress.R;
import com.muz.progress.base.contract.gank.GankWelfareContract;
import com.muz.progress.ui.gank.adapter.GankWelfareAdapter;
import com.muz.progress.app.MyApplication;
import com.muz.progress.base.BaseMvpFragment;
import com.muz.progress.model.entity.GankItemBean;
import com.muz.progress.presenter.gank.GankWelfarePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/7/4.
 */

public class GankWelfareFragment extends BaseMvpFragment<GankWelfarePresenter> implements GankWelfareContract.View {
    @BindView(R.id.gank_welfare_main)
    RecyclerView gankWelfareMain;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    List<GankItemBean> skillData=new ArrayList<>();
    GankWelfareAdapter adapter;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    private static final int SPAN_COUNT = 2;
    boolean isLoadingMore = false;
    int page;


    @Override
    protected void initInject() {
        MyApplication.getInstance().getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_gank_welfare;
    }

    @Override
    protected void initViewAndEvent() {
        adapter=new GankWelfareAdapter(context,skillData);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(SPAN_COUNT,StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        staggeredGridLayoutManager.setItemPrefetchEnabled(false);
        gankWelfareMain.setLayoutManager(staggeredGridLayoutManager);
        gankWelfareMain.setAdapter(adapter);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.welfareData(1);
                page=1;
            }
        });
        //添加监听器
        gankWelfareMain.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int[] visibleItems = staggeredGridLayoutManager.findLastVisibleItemPositions(null);
                int lastItem = Math.max(visibleItems[0],visibleItems[1]);
                if (lastItem > adapter.getItemCount() - 5 && !isLoadingMore && dy > 0 ) {
                    isLoadingMore = true;
                    presenter.welfareData(++page);
                }
            }
        });
    }
    @Override
    public void welfareData(List<GankItemBean> data) {
        if (swipeRefresh.isRefreshing()){
            swipeRefresh.setRefreshing(false);
        }
        if (page==1){
            skillData.clear();
        }
        skillData.addAll(data);
        if (adapter!=null){
        adapter.notifyDataSetChanged();
        }
        isLoadingMore=false;
    }
    @Override
    public void stateError() {
        super.stateError();
        if (swipeRefresh.isRefreshing()){
            swipeRefresh.setRefreshing(false);
        }
        isLoadingMore=false;
    }
}
