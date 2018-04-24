package com.bawei.a1501atokhttp;

import android.app.Application;

import com.bawei.a1501atokhttp.util.ToastUtil;
import com.bawei.a1501atokhttp.util.oKHttpSingTop;


/**
 * 作    者：高学军
 * 时    间：17/1/9
 * 描    述：
 * 修改时间：
 */
public class Tokhttp1501A extends Application{


    // 测试服务器的地址
    public static final String SERVER_LET = "http://47.93.240.29:8888/jd/";

    // 登陆的地址
    public static final String LOGIN = "login";


    public static oKHttpSingTop oKHttpSingTop;
    // 在应用初始化的时候声明toast对象
    public static ToastUtil toastUtil;

    @Override
    public void onCreate() {
        super.onCreate();

        // 实例化单例对象
        oKHttpSingTop = com.bawei.a1501atokhttp.util.oKHttpSingTop.getInstance();
        toastUtil = ToastUtil.getInstance();
    }
}
