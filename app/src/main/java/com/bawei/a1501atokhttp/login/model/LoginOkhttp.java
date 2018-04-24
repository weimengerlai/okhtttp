package com.bawei.a1501atokhttp.login.model;

import com.bawei.a1501atokhttp.Tokhttp1501A;
import com.bawei.a1501atokhttp.login.entity.Login;
import com.bawei.a1501atokhttp.util.oKHttpSingTop;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 作    者：高学军
 * 时    间：2018/3/16
 * 描    述：
 * 修改时间：
 */
public class LoginOkhttp {


    // 观察对象
    LoginHttopInterface loginHttopInterface;



    // 设置监听的方法
    public void setOnLoginListener(LoginHttopInterface loginListener){

            loginHttopInterface = loginListener;
    }



    // 登陆的网络请求
    public void LoginPost(String name,String pwd){


        // 实例化map
        Map<String,String> map = new HashMap<String, String>();

        map.put("username",name);
        map.put("password",pwd);


        // 设置网络请求,无论是 get 还是post
        Tokhttp1501A.oKHttpSingTop.setUrl(Tokhttp1501A.SERVER_LET+Tokhttp1501A.LOGIN,
                map, Login.class, oKHttpSingTop.Methods.PSOT);



        // 设置监听,返回结果,通过进行解析,通过接口的形式返回给p层
        Tokhttp1501A.oKHttpSingTop.setCallbackM(new oKHttpSingTop.CallbackM() {

            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Object response) {

                Login login = (Login) response;

                //获取状态吗进行判断
                int statue = login.statue;

                if(null == loginHttopInterface){

                    return;
                }

                switch (statue){

                    case 0:

                        // 调用登陆成功的方法
                        loginHttopInterface.Success(login.userId,login.tpken);

                        break;

                    case 1:

                        // 调用登陆失败的方法
                        loginHttopInterface.Fail(login.message);


                        break;
                }



            }
        });

    }

}
