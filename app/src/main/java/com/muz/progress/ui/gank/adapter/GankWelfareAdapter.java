package com.muz.progress.ui.gank.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.muz.progress.R;
import com.muz.progress.model.entity.GankItemBean;
import com.muz.progress.util.UIUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/4.
 */

public class GankWelfareAdapter extends RecyclerView.Adapter<GankWelfareAdapter.ViewHolder> {
    private LayoutInflater inflater;
    List<GankItemBean> data;
    Context context;

    public GankWelfareAdapter(Context context, List<GankItemBean> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return Math.round((float) UIUtils.getScreenWidth(context) / (float) data.get(position).getHeight() * 10f);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.adapter_gank_welfare, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (data.get(holder.getAdapterPosition()).getHeight() > 0) {
            ViewGroup.LayoutParams layoutParams = holder.gankWelfareItemImg.getLayoutParams();
            layoutParams.height = data.get(holder.getAdapterPosition()).getHeight();
        }
holder.gankWelfareItemCopyright.setText("by: "+data.get(position).getWho());
        Glide.with(context).load(data.get(position).getUrl()).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<Bitmap>(UIUtils.getScreenWidth(context) / 2, UIUtils.getScreenWidth(context) / 2) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        if (holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                            if (data.get(holder.getAdapterPosition()).getHeight() <= 0) {
                                int width = resource.getWidth();
                                int height = resource.getHeight();
                                int realHeight = (UIUtils.getScreenWidth(context) / 2) * height / width;
                                data.get(holder.getAdapterPosition()).setHeight(realHeight);
                                ViewGroup.LayoutParams lp = holder.gankWelfareItemImg.getLayoutParams();
                                lp.height = realHeight;
                            }
                            holder.gankWelfareItemImg.setImageBitmap(resource);
                        }
                    }
                });
//        Glide.with(context).load(data.get(position).getUrl()).into(holder.gankWelfareItemImg);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.gank_welfare_item_img)
        ImageView gankWelfareItemImg;
        @BindView(R.id.gank_welfare_item_copyright)
        TextView gankWelfareItemCopyright;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
