package com.bawei.a1501atokhttp.util;

import java.util.Map;

/**
 * 作    者：高学军
 * 时    间：2018/4/25
 * 描    述：
 * 修改时间：
 */
public interface hAgencyInterface {


    // get 网络请求的方法
    public <T> void GetAgency(String url, Map<String,String> map,Class<T> Cls);

    // post 网络请求方法
    public <T> void PostAgency(String url, Map<String,String> map,Class<T> Cls);


}
