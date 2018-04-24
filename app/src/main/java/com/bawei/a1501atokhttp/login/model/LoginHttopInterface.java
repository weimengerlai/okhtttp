package com.bawei.a1501atokhttp.login.model;

/**
 * 作    者：高学军
 * 时    间：2018/4/24
 * 描    述：
 * 修改时间：
 */
public interface LoginHttopInterface {


    // 请求网络成功
    public void Success(int userId,String token);

    // 请求网络失败
    public void Fail(String message);
}
