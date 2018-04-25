package com.bawei.a1501atokhttp.util;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.util.Iterator;
import java.util.Map;

/**
 * 作    者：高学军
 * 时    间：2018/4/25
 * 描    述：
 * 修改时间：
 */
public abstract class UIHandler {


    //获取主线程 handler
    private Handler handler;
    //解析对象
    private Gson gson;


    // 使用代码块,优先于主函数执行
    {

        handler = new Handler(Looper.getMainLooper());
        gson = new Gson();
    }




    public void MainThread(final Object response, final CallbackOk callbackM) {

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
    * GSON 解析
    *
    * */

    public <T> void getJSon(String json, Class<T> Cls,CallbackOk callbackM) {

        T t1 = gson.fromJson(json, Cls);

        MainThread(t1,callbackM);

    }

     /*
    * get  url 拼接方法
    */

    public  String get(String url,Map<String,String> map){

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


        return url;
    }


}
