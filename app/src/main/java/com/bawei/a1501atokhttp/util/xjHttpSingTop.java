package com.bawei.a1501atokhttp.util;

import java.util.Map;

/**
 * 作    者：高学军
 * 时    间：2018/4/25
 * 描    述：
 * 修改时间：
 */
public class xjHttpSingTop implements hAgencyInterface{

    // 单例模式对象
    private static volatile xjHttpSingTop xjHttpSingTop;

    // 在默认构造函数里面实例化对象
    hAgencyInterface hAgencyInterface;

    // 默认构造函数,无参数,默认使用这个代理
    private xjHttpSingTop() {

        hAgencyInterface = new okHttpAgency();
    }

    //有参构造函数 也可以使用别的代理
    private xjHttpSingTop(com.bawei.a1501atokhttp.util.hAgencyInterface hAgencyInterface) {

        this.hAgencyInterface = hAgencyInterface;
    }


    // 单例模式结合代理模式来实现网络请求框架
    public static xjHttpSingTop getInstance(){

        if(null == xjHttpSingTop){

            // 这句话的意思就是锁住在jvm运行的时候的类
            synchronized (xjHttpSingTop.class){

                if(null == xjHttpSingTop){

                    xjHttpSingTop = new xjHttpSingTop();

                    return xjHttpSingTop;
                }
            }
        }


        return xjHttpSingTop;

    }


    // 指定动态的代理的模式
    public static xjHttpSingTop getInstance(hAgencyInterface hAgencyInterface){

        if(null == xjHttpSingTop){

            // 这句话的意思就是锁住在jvm运行的时候的类
            synchronized (xjHttpSingTop.class){

                if(null == xjHttpSingTop){

                    xjHttpSingTop = new xjHttpSingTop(hAgencyInterface);

                    return xjHttpSingTop;
                }
            }
        }


        return xjHttpSingTop;

    }



    @Override
    public <T> void GetAgency(String url, Map<String, String> map, Class<T> Cls) {

        hAgencyInterface.GetAgency(url,map,Cls);

    }

    @Override
    public <T> void PostAgency(String url, Map<String, String> map, Class<T> Cls) {

        hAgencyInterface.PostAgency(url,map,Cls);
    }
}
