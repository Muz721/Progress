package com.muz.progress.model.htttp;


import android.content.Context;
import android.util.Log;

import io.reactivex.functions.Consumer;

/**
 * @description  统一处理错误
 * @author  Muz
 * @date  2018/6/12 18:01
 */

public class Error  implements Consumer<Throwable> {
    public  Error(Context context){

    }
    @Override
    public void accept(Throwable throwable) throws Exception {
        Log.e("TAG","error message:"+throwable.getMessage());
        if(throwable instanceof Fault){
            Fault fault = (Fault) throwable;
            if(fault.getErrorCode() == 404){
                //错误处理
            }else if(fault.getErrorCode() == 500){
                //错误处理
            }else if(fault.getErrorCode() == 501){
                //错误处理
            }
        }
    }
}
