package com.muz.progress.base;

/**
 * @description  view基类
 * @author  Muz
 * @date  2018/6/14 15:22
 */

public interface BaseView {
    /**
     * 请求失败                   统一处理
     * @param msg    失败信息
     */
    void showErrorMsg(String msg);

    /**
     * 请求失败  加载
     */
    void stateError();
}
