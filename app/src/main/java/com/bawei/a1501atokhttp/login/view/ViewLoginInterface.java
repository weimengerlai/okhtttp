package com.bawei.a1501atokhttp.login.view;

/**
 * 作    者：高学军
 * 时    间：2018/4/24
 * 描    述：
 * 修改时间：
 */
public interface ViewLoginInterface {


    // 登陆成功
    public void LginSuccess(int userId,String token);

    // 登陆失败
    public void LoginFail(String message);
}
