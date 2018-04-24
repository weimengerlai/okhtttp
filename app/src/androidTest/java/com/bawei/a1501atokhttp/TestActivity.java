package com.bawei.a1501atokhttp;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

import com.bawei.a1501atokhttp.login.view.LoginActivity;

/**
 * 作    者：高学军
 * 时    间：2018/3/16
 * 描    述：
 * 修改时间：
 */
public class TestActivity extends ActivityInstrumentationTestCase2<LoginActivity> {

    private Context ctx;
    private LoginActivity loginActivity;

    private EditText edt_login_username;
    private EditText edt_login_password;
    private Button btn_login_submit;


    // 通过 hander 来传递内容,点击登陆
    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){

                case 1:



                    loginActivity = (LoginActivity) msg.obj;

                    edt_login_username = loginActivity.findViewById(R.id.edt_login_username);
                    edt_login_password = loginActivity.findViewById(R.id.edt_login_password);
                    btn_login_submit = loginActivity.findViewById(R.id.btn_login_submit);

                    edt_login_password.setText("1234567");
                    edt_login_username.setText("zhangsan20");

                    loginActivity.onClick(btn_login_submit);

                    //设置休眠
                    assertEquals(btn_login_submit.getText(),"登陆");

                    break;
            }

        }
    };



    public TestActivity() {
        super(LoginActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        ctx = getActivity().getApplicationContext();
        loginActivity = getActivity();

        Intent intent = new Intent(ctx, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);


    }

    public void testStart() {

        // 传递消息到 handler 去更新消息
        Message msg = mHandler.obtainMessage();
        msg.what = 1;
        msg.obj = loginActivity;
        mHandler.sendMessage(msg);


    }
}
