package com.bawei.a1501atokhttp.util;

import com.squareup.okhttp.Request;

import java.io.IOException;

/**
 * 作    者：高学军
 * 时    间：2018/4/25
 * 描    述：
 * 修改时间：
 */
public interface CallbackOk {


    public void onFailure(Request request, IOException e);

    public void onResponse(Object response);
}
