package com.bawei.a1501atokhttp.login.model;

import com.bawei.a1501atokhttp.Tokhttp1501A;
import com.bawei.a1501atokhttp.login.entity.Login;
import com.bawei.a1501atokhttp.util.oKHttpSingTop;

import java.util.HashMap;
import java.util.Map;

/**
 * 作    者：高学军
 * 时    间：2018/3/16
 * 描    述：
 * 修改时间：
 */
public class LoginOkhttp {



    // 登陆的网络请求
    public void LoginPost(String name,String pwd){



        Map<String,String> map = new HashMap<String, String>();

        map.put("username",name);
        map.put("password",pwd);


        Tokhttp1501A.oKHttpSingTop.setUrl(Tokhttp1501A.SERVER_LET+Tokhttp1501A.LOGIN,
                map, Login.class, oKHttpSingTop.Methods.PSOT);

    }

}
