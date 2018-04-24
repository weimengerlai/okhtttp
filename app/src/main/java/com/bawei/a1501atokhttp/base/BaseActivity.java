package com.bawei.a1501atokhttp.base;

import android.app.Activity;

/**
 * 作    者：高学军
 * 时    间：2018/3/16
 * 描    述：
 * 修改时间：
 */
public abstract class BaseActivity extends Activity{


    // 初始化头部的方法
    public abstract void initHeader();


    // 初始化控件的方法
    public abstract void initWiget();


    // 设置监听事件的方法
    public abstract void setWiget();

}
