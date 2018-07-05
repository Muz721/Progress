package com.muz.progress.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @description  fragment 基类
 * @author  Muz
 * @date  2018/6/15 14:57
 */

public abstract class BaseFragment extends SupportFragment{
    protected Context context;
    private Unbinder unBinder;
    protected boolean isInited = false;
    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(),null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unBinder= ButterKnife.bind(this,view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        isInited = true;
        initViewAndEvent();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unBinder!=null){
            unBinder.unbind();
        }
    }

    /**
     * 绑定布局
     * @return   布局
     */
    protected abstract int getLayout();

    /**
     * 初始化布局和事件
     */
    protected abstract void initViewAndEvent();
}
