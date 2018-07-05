package com.muz.progress.ui.gank.fragment;

import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.muz.progress.R;
import com.muz.progress.base.contract.gank.GankClassifySkillContract;
import com.muz.progress.ui.gank.adapter.GankNewestItemAdapter;
import com.muz.progress.app.MyApplication;
import com.muz.progress.base.BaseMvpFragment;
import com.muz.progress.model.entity.GankItemBean;
import com.muz.progress.presenter.gank.GankClassifySkillPresenter;
import com.muz.progress.util.UIUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by Administrator on 2018/7/3.
 */

public class GankClassifySkillFragment extends BaseMvpFragment<GankClassifySkillPresenter> implements GankClassifySkillContract.View {
    List<GankItemBean> skillData;
    GankNewestItemAdapter adapter;
    @BindView(R.id.gank_classify_skill_img_dim)
    ImageView gankClassifySkillImgDim;
    @BindView(R.id.gank_classify_skill_img_origin)
    ImageView gankClassifySkillImgOrigin;
    @BindView(R.id.gank_classify_skill_img_copyright)
    TextView gankClassifySkillImgCopyright;
    @BindView(R.id.gank_classify_skill_appbar)
    AppBarLayout gankClassifySkillAppbar;
    @BindView(R.id.gank_classify_skill_img_main)
    RecyclerView gankClassifySkillImgMain;
    @BindView(R.id.gank_classify_skill_refresh)
    SwipeRefreshLayout gankClassifySkillRefresh;
    boolean isLoadingMore = false;
    @Override
    protected void initInject() {
        MyApplication.getInstance().getFragmentComponent().inject(this);
    }

    String classify;
int page;
    @Override
    protected int getLayout() {
        return R.layout.fragment_gank_classify_skill;
    }

    @Override
    protected void initViewAndEvent() {
        Random random=new Random();
        int randomNumber = random.nextInt(12);
        int img[] ={R.drawable.img_1,R.drawable.img_2,R.drawable.img_3,R.drawable.img_4,R.drawable.img_5,R.drawable.img_6,
                R.drawable.img_7,R.drawable.img_8,R.drawable.img_9,R.drawable.img_10,R.drawable.img_11,R.drawable.img_12};
        Glide.with(this).load(img[randomNumber]).into(gankClassifySkillImgOrigin);
        Glide.with(context).load(img[randomNumber]).bitmapTransform(new BlurTransformation(context)).into(gankClassifySkillImgDim);
        gankClassifySkillImgCopyright.setText("by: "+"lifeofpix.com");
        skillData = new ArrayList<>();
        classify = getArguments().getString("gank_classify");
        adapter = new GankNewestItemAdapter(context, skillData);
        gankClassifySkillImgMain.setLayoutManager(new LinearLayoutManager(context));
        gankClassifySkillImgMain.setAdapter(adapter);
        presenter.skillData(classify, 10, 1);
////添加监听器
        gankClassifySkillImgMain.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager) gankClassifySkillImgMain.getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount = gankClassifySkillImgMain.getLayoutManager().getItemCount();
                if (lastVisibleItem >= totalItemCount - 2 && dy > 0) {  //还剩2个Item时加载更多
                    if(!isLoadingMore){
                        isLoadingMore = true;
                        presenter.skillData(classify, 10, ++page);
                    }
                }
            }
        });
        gankClassifySkillAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0){
                    gankClassifySkillRefresh.setEnabled(true);
                }else {
                    gankClassifySkillRefresh.setEnabled(false);
                    float rate = (float)(UIUtils.dp2px(context, 256) + verticalOffset * 2) / UIUtils.dp2px(context, 256);
                    if (rate >= 0)
                        gankClassifySkillImgOrigin.setAlpha(rate);
                }
            }
        });
        gankClassifySkillRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                presenter.skillData(classify, 10, 1);
            }
        });
    }

    @Override
    public void stateError() {
        super.stateError();
        if(gankClassifySkillRefresh.isRefreshing()) {
            gankClassifySkillRefresh.setRefreshing(false);
        }
    }

    @Override
    public void skillData(List<GankItemBean> skillData) {
        if(gankClassifySkillRefresh.isRefreshing()) {
            gankClassifySkillRefresh.setRefreshing(false);
        }
        if (page==1){
        this.skillData.clear();
        }
        this.skillData.addAll(skillData);
        adapter.notifyDataSetChanged();
        isLoadingMore = false;
    }


}
