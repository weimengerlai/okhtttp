package com.bawei.a1501atokhttp.login.presenter;

import android.content.Context;
import android.content.Intent;

import com.bawei.a1501atokhttp.MainActivity;
import com.bawei.a1501atokhttp.Tokhttp1501A;
import com.bawei.a1501atokhttp.login.entity.Login;
import com.bawei.a1501atokhttp.login.model.LoginOkhttp;
import com.bawei.a1501atokhttp.util.oKHttpSingTop;
import com.squareup.okhttp.Request;

import java.io.IOException;

/**
 * 作    者：高学军
 * 时    间：2018/3/16
 * 描    述：
 * 修改时间：
 */
public class LoginPresenter {


    // 调用 view层控件的内容,传递给model层,进行网络请求,
    // 同时负责返回给view层,登陆成功还是失败,view层成功跳转还是失败提示
    public void LoginSubmit(String username, String password, final Context context){

        // 获取内容进行判断
        // 实例化对象调用
        LoginOkhttp loginOkhttp = new LoginOkhttp();
        loginOkhttp.LoginPost(username,password);


        //设置观察者模式实现回调
        //观察者模式
        Tokhttp1501A.oKHttpSingTop.setCallbackM(new oKHttpSingTop.CallbackM() {

            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Object response) {

                Login login = (Login) response;

                //获取状态吗进行判断
                int statue = login.statue;

                switch (statue){

                    case 0:

                        Intent intent = new Intent(context, MainActivity.class);
                        intent.putExtra(MainActivity.USERID,login.userId);
                        intent.putExtra(MainActivity.TOKEN,login.tpken);
                        context.startActivity(intent);


                        break;

                    case 1:

                        // 提示密码不正确
                        Tokhttp1501A.toastUtil.ShowLong(context,login.message);

                        break;
                }



            }
        });

    }



}
