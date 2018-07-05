package com.muz.progress.ui.gank.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.muz.progress.R;
import com.muz.progress.model.entity.GankItemBean;

import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/6/21.
 */

public class GankNewestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private LinkedHashMap<String, List<GankItemBean>> hashMap;
private Object[] keys;
private Context context;
    public GankNewestAdapter(Context context, LinkedHashMap<String, List<GankItemBean>> hashMap, Object[] keys) {
        inflater = LayoutInflater.from(context);
        this.hashMap = hashMap;
        this.keys=keys;
        this.context=context;
    }
public void keys(){
        keys=hashMap.keySet().toArray();
}
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.adapter_gank_newest, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).gankNewestClassifyName.setText(keys[position].toString());
        GankNewestItemAdapter adapter=new GankNewestItemAdapter(context,hashMap.get(keys[position].toString()));
        ((ViewHolder)holder).gankNewestClassifyData.setLayoutManager(new LinearLayoutManager(context));
        ((ViewHolder)holder).gankNewestClassifyData.setAdapter(adapter);
//        adapter.setOnItemClickListener(new GankNewestItemAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position, View view) {
////                Intent intent = null;
////                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return hashMap.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Nullable
        @BindView(R.id.gank_newest_classify_ico)
        ImageView gankNewestClassifyIco;
        @Nullable
        @BindView(R.id.gank_newest_classify_name)
        TextView gankNewestClassifyName;
        @Nullable
        @BindView(R.id.gank_newest_classify_data)
        RecyclerView gankNewestClassifyData;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            /**
             * 解决触摸到子条目里的RecyclerView 图片不折叠
             *
             * 待优化处理  这样写会有点不流畅
             */
            gankNewestClassifyData.setNestedScrollingEnabled(false);
        }
    }
}
