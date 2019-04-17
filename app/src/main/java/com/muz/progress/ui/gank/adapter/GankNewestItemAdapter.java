package com.muz.progress.ui.gank.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.muz.progress.R;
import com.muz.progress.ui.gank.activity.WebActivity;
import com.muz.progress.model.entity.GankItemBean;
import com.muz.progress.util.TimesUtils;

import android.support.v7.widget.CardView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/6/25.
 */

public class GankNewestItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    public List<GankItemBean> list;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public GankNewestItemAdapter(Context context, List<GankItemBean> list) {
        inflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.adapter_gank_newset_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolder) holder).gankNewestItemTitle.setText(list.get(position).getDesc());
        ((ViewHolder) holder).gankNewestItemDesc.setText(list.get(position).getDesc());
        ((ViewHolder) holder).gankNewestProvider.setText(list.get(position).getWho());
        ((ViewHolder) holder).gankNewestItemTime.setText(TimesUtils.timeFormatAlter(list.get(position).getPublishedAt()));
        if (list.get(position).getImages() != null && list.get(position).getImages().length > 0) {
            GankNewestItemGifImgAdapter adapter = new GankNewestItemGifImgAdapter(context, list.get(position).getImages());
            ((ViewHolder) holder).gankNewestItemGif.setLayoutManager(new GridLayoutManager(context, 3));
            ((ViewHolder) holder).gankNewestItemGif.setAdapter(adapter);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(onItemClickListener != null) {
                CardView cv = (CardView) v.findViewById(R.id.gank_newest_item_cardView);
//                ActivityOptionsCompat compat = ActivityOptionsCompat.
//                        makeSceneTransitionAnimation((Activity) context, cv, "shareView");
//                ActivityOptionsCompat compat = ActivityOptionsCompat.
//                        makeScaleUpAnimation(cv, cv.getWidth() / 2, cv.getHeight() / 2, 0, 0);

//                Postcard postcard =
                        ARouter.getInstance().build("/gank/activity/webActivity")
                        .withString("title", list.get(position).getDesc())
                        .withString("url", list.get(position).getUrl())
                        .navigation();
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    postcard.withOptionsCompat(compat);
//                }
//                postcard.navigation();

//                    onItemClickListener.onItemClick(holder.getAdapterPosition(),cv);
            }
//            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.gank_newest_item_title)
        TextView gankNewestItemTitle;
        @BindView(R.id.gank_newest_item_desc)
        TextView gankNewestItemDesc;
        @BindView(R.id.gank_newest_provider)
        TextView gankNewestProvider;
        @BindView(R.id.gank_newest_item_time)
        TextView gankNewestItemTime;
        @BindView(R.id.gank_newest_item_container)
        LinearLayout gankNewestItemContainer;
        @BindView(R.id.gank_newest_item_gif)
        RecyclerView gankNewestItemGif;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }
}
