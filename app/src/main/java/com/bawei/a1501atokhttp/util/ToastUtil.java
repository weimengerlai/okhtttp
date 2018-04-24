package com.bawei.a1501atokhttp.util;

import android.content.Context;
import android.widget.Toast;

/**
 * 作    者：高学军
 * 时    间：2018/3/16
 * 描    述：
 * 修改时间：
 */
public class ToastUtil {

    // 注意一般工具类的封装最好采用单例模式,避免同时出现两次toast,
    // 或者同一个对象出现两次调用,会导致奔溃
    // 比如dialog的封装,同样遵循单例模式,因为同时有时候会出现连个以上的dialog,避免这个问题

    // 声明私有的toastUtil
    private static volatile ToastUtil toastUtil;


    // 实例化单例
    public static ToastUtil getInstance(){

        if(null == toastUtil){

            synchronized (ToastUtil.class){

                if(null == toastUtil){

                    // 实例化单例模式的对象
                    toastUtil = new ToastUtil();
                    // 同时实例化toast

                }
            }
        }

        return toastUtil;
    }

    // 长吐司的方法
    public void ShowLong(Context context,String info){

        if(null != context && null != info && !info.equals("")){

            Toast.makeText(context,info,Toast.LENGTH_LONG).show();
        }

    }

    // 短吐司的方法
    public void ShowShort(Context context,String info){

        if(null != context && null != info && !info.equals("")){

            Toast.makeText(context,info,Toast.LENGTH_SHORT).show();
        }

    }


}
