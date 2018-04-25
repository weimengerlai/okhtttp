package com.bawei.a1501atokhttp.util;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * 作    者：高学军
 * 时    间：17/1/9
 * 描    述：
 * 修改时间：
 */
public class oKHttpSingTop {

    //声明对象
    CallbackM callbackM;


    //声明对象 私有化
    // 单例模式的对象用于都是私有的,避免外部对象进行访问,
    // 使用双锁机制来确保线程安全
    // 俩把锁分别为: volatile , synchronized
    // 无论是 get 还是 post 封装的要求就是 只传入 url 和 map集合,map里面装的是我们请求的键值对
    // 网络请求成功以后,一般我们进行json解析,所有我会要求开发使用泛型T 将json解析封装
    // 因为网络请求封装完成以后,我们需要将解析好的数据返回给页面,所以我们看开发在这里有没有使用观察者模式模式
    // 观察者设计模式:  1: 首先我们生命一个interface,里面放我们需要的方法
    // 如果封装合格我会看看是不是在程序入口实例化的网络请求对象,程序入口也就是application
    private static volatile oKHttpSingTop oKHttpSingTop;

    //获取主线程 handler
    private Handler handler;
    //解析对象
    private Gson gson;

    //构造函数 私有化
    private oKHttpSingTop() {

        handler = new Handler(Looper.getMainLooper());
        gson = new Gson();

    }

    // 提供公共方法
    public static oKHttpSingTop getInstance() {

        if (null == oKHttpSingTop) {

            synchronized (com.bawei.a1501atokhttp.util.oKHttpSingTop.class) {

                if (null == oKHttpSingTop) {

                    oKHttpSingTop = new oKHttpSingTop();
                }
            }
        }

        return oKHttpSingTop;
    }


    /*
    * 拼接 url的方法
    *
    */

    public <T> void setUrl(String url,Map<String,String> map,Class<T> Cls,Methods methods){


        switch (methods){

            case GET: //get 方法

                get(url,map,Cls);

                break;
            case PSOT: // post方法

                Post(url,map,Cls);

                break;

        }


    }


    /*
    * get 方法
    */

    public <T> void get(String url,Map<String,String> map,Class<T> Cls){

        int i=0;

        // 遍历
        Iterator<String> iterator = map.keySet().iterator();

        while (iterator.hasNext()){

            String key  = iterator.next();
            String value = map.get(key);



            if(i == 0){

                url +=key+"="+value;

            }else {

                url += "&" + key+"="+value;
            }

            i++;

        }


        geturl(url,Cls);
    }


    // get  请求
    public <T> void geturl(String url, final Class<T> Cls) {

        //创建okHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();

        //创建一个Request

        final Request request = new Request.Builder()
                .url(url)
                .build();

        ////new call
        Call call = okHttpClient.newCall(request);

        //回调接口

        call.enqueue(new Callback() {


            @Override
            public void onFailure(Request request, IOException e) {

                if (null != callbackM) {
                    callbackM.onFailure(request, e);
                }
            }

            @Override
            public void onResponse(Response response) throws IOException {

                String json = response.body().string();


                getJSon(json, Cls);

            }
        });


    }


    /*
    * GSON 解析
    *
    * */

    public <T> void getJSon(String json, Class<T> Cls) {

        T t1 = gson.fromJson(json, Cls);

        MainThread(t1);

    }


    public void MainThread(final Object response) {

        handler.post(new Runnable() {
            @Override
            public void run() {

                if (null != callbackM) {
                    callbackM.onResponse(response);
                }
            }
        });
    }


    /*
    *  post 方法
    *  url 请求网络的地址
    *  map post 请求的参数
    *
    */

    public <T> void Post(String url, Map<String,String> map, final Class<T> Cls){


        //得到 client 对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //得到 body
        FormEncodingBuilder builder = new FormEncodingBuilder();
        //遍历
        Iterator<String> iterator = map.keySet().iterator();
        //加入到 builder 对象

        while (iterator.hasNext()){

            String key = iterator.next();
            String values = map.get(key);

            //加入到 builder 里面
            builder.add(key,values);

        }

        // 得到 request

        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();


        //得到 call

        Call call = okHttpClient.newCall(request);


        //接口回调

        call.enqueue(new Callback() {


            @Override
            public void onFailure(Request request, IOException e) {

                if (null != callbackM) {
                    callbackM.onFailure(request, e);
                }
            }

            @Override
            public void onResponse(Response response) throws IOException {

                String json = response.body().string();

                getJSon(json, Cls);
            }
        });


    }




    //设置监听的方法

    public void setCallbackM(CallbackM callbackM) {
        this.callbackM = callbackM;
    }




    //观察者模式
    public interface CallbackM {

        public void onFailure(Request request, IOException e);

        public void onResponse(Object response);
    }



    /*
    *  定义枚举类型
    *  GET PSOT DOWN
    *  关键字段 enum
    *
    */

    public enum Methods{

        GET,PSOT,DOWN
    }
}
