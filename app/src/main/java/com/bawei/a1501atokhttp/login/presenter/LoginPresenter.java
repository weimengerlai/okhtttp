package com.bawei.a1501atokhttp.login.presenter;

import android.content.Context;

import com.bawei.a1501atokhttp.login.model.LoginHttopInterface;
import com.bawei.a1501atokhttp.login.model.LoginOkhttp;
import com.bawei.a1501atokhttp.login.view.ViewLoginInterface;

/**
 * 作    者：高学军
 * 时    间：2018/3/16
 * 描    述：
 * 修改时间：
 */
public class LoginPresenter implements LoginHttopInterface{

    // 观察者
    ViewLoginInterface viewLoginInterface;
    // 实例化对象
    LoginOkhttp loginOkhttp;

    // 观察者方法的声明
    public void setOnLoginListener(ViewLoginInterface Interface){

        viewLoginInterface = Interface;
    }




    // 调用 view层控件的内容,传递给model层,进行网络请求,
    // 同时负责返回给view层,登陆成功还是失败,view层成功跳转还是失败提示
    public void LoginSubmit(String username, String password, final Context context){

        // 获取内容进行判断
        // 实例化对象调用
        loginOkhttp = new LoginOkhttp();
        loginOkhttp.LoginPost(username,password);

        // 设置监听
        loginOkhttp.setOnLoginListener(this);


    }


    @Override
    public void Success(int userId, String token) {

        if(null != viewLoginInterface){

            // 调用登陆成功的方法
            viewLoginInterface.LginSuccess(userId,token);
        }
    }



    @Override
    public void Fail(String message) {

        if(null != viewLoginInterface){

            // 调用登陆失败的方法
            viewLoginInterface.LoginFail(message);
        }

    }
}
