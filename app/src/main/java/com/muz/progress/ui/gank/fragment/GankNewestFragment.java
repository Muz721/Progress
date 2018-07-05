package com.muz.progress.ui.gank.fragment;

import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.muz.progress.R;
import com.muz.progress.base.contract.gank.GankNewestContract;
import com.muz.progress.ui.gank.adapter.GankNewestAdapter;
import com.muz.progress.app.MyApplication;
import com.muz.progress.base.BaseMvpFragment;
import com.muz.progress.model.entity.GankItemBean;
import com.muz.progress.presenter.gank.GankNewestPresenter;
import com.muz.progress.util.UIUtils;

import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * @author Muz
 * @description 最新干货
 * @date 2018/7/2 9:57
 */

public class GankNewestFragment extends BaseMvpFragment<GankNewestPresenter> implements GankNewestContract.View {
    @BindView(R.id.gank_newest_data)
    RecyclerView gankNewestData;
    @BindView(R.id.gank_newest_data_layout)
    SwipeRefreshLayout gankNewestDataLayout;
    GankNewestAdapter adapter;
    LinkedHashMap<String, List<GankItemBean>> hashMap;
    Object[] keys;
    @BindView(R.id.gank_newest_img_dim)
    ImageView gankNewestImgDim;
    @BindView(R.id.gank_newest_img_origin)
    ImageView gankNewestImgOrigin;
    @BindView(R.id.gank_newest_img_copyright)
    TextView gankNewestImgCopyright;
    @BindView(R.id.gank_newest_appbar)
    AppBarLayout gankNewestAppbar;

    @Override
    protected void initInject() {
        MyApplication.getInstance().getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_gank_newest;
    }

    @Override
    protected void initViewAndEvent() {
        hashMap = new LinkedHashMap<>();
        keys = hashMap.keySet().toArray();//获取所有键
        adapter = new GankNewestAdapter(context, hashMap, keys);
        gankNewestData.setLayoutManager(new LinearLayoutManager(context));
        gankNewestData.setAdapter(adapter);
        gankNewestAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0){
                    gankNewestDataLayout.setEnabled(true);
                }else {
                    gankNewestDataLayout.setEnabled(false);
                    float rate = (float)(UIUtils.dp2px(context, 256) + verticalOffset * 2) / UIUtils.dp2px(context, 256);
                    if (rate >= 0)
                        gankNewestImgOrigin.setAlpha(rate);
                }
            }
        });
        gankNewestDataLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getDate();
            }
        });
    }

    @Override
    public void stateError() {
        super.stateError();
        if (gankNewestDataLayout.isRefreshing()) {
            gankNewestDataLayout.setRefreshing(false);
        }
    }

    /**
     * 获取最新干货
     *
     * @param hashMap 干货
     */
    @Override
    public void getGankNewestData(LinkedHashMap<String, List<GankItemBean>> hashMap) {
        if (gankNewestDataLayout.isRefreshing()) {
            gankNewestDataLayout.setRefreshing(false);
        }
        this.hashMap.clear();
        this.hashMap.putAll(hashMap);
        adapter.keys();
        adapter.notifyDataSetChanged();


        Glide.with(this).load(hashMap.get("福利").get(0).getUrl()).into(gankNewestImgOrigin);
        Glide.with(context).load(hashMap.get("福利").get(0).getUrl()).bitmapTransform(new BlurTransformation(context)).into(gankNewestImgDim);
        gankNewestImgCopyright.setText("by: "+hashMap.get("福利").get(0).getWho());
    }

}
