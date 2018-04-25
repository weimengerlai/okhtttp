package com.bawei.a1501atokhttp.util;

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
 * 时    间：2018/4/25
 * 描    述：
 * 修改时间：
 */
public class okHttpAgency extends UIHandler implements hAgencyInterface{

    //声明对象
    CallbackOk callbackM;


    //设置监听的方法

    public void setCallbackM(CallbackOk callbackM) {

        this.callbackM = callbackM;
    }



    @Override
    public <T> void GetAgency(String url, Map<String, String> map, final Class<T> Cls) {


        //创建okHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();

        //创建一个Request

        final Request request = new Request.Builder()
                .url(get(url,map))
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


                getJSon(json, Cls,callbackM);

            }
        });

    }




    @Override
    public <T> void PostAgency(String url, Map<String, String> map, final Class<T> Cls) {


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

                getJSon(json, Cls,callbackM);
            }
        });


    }


}
