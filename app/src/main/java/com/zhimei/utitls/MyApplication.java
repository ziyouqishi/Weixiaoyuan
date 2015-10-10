package com.zhimei.utitls;

import android.app.Application;
import android.content.Context;

/**
 * Created by 张佳亮 on 2015/10/7.
 */
public class MyApplication extends Application {
    private static Context context;
    @Override
    public void onCreate(){
        context=getApplicationContext();
    }
    public static Context getContext(){
        return context;}
}
