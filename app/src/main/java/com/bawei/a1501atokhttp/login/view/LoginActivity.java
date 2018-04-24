package com.bawei.a1501atokhttp.login.view;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bawei.a1501atokhttp.R;
import com.bawei.a1501atokhttp.Tokhttp1501A;
import com.bawei.a1501atokhttp.base.BaseActivity;
import com.bawei.a1501atokhttp.login.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    // 控件
    private EditText edt_login_username;
    private EditText edt_login_password;
    private Button btn_login_submit;

    // 实例化p层
    LoginPresenter loginPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenter();

        // 调用方法
        initHeader();
        initWiget();
        setWiget();

    }



    @Override
    public void initHeader() {

    }

    @Override
    public void initWiget() {

        edt_login_username = findViewById(R.id.edt_login_username);
        edt_login_password = findViewById(R.id.edt_login_password);
        btn_login_submit = findViewById(R.id.btn_login_submit);

    }

    @Override
    public void setWiget() {

        // 设置监听
        btn_login_submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.btn_login_submit: //点击登陆按钮

                // 主要判断用户名长度不能大于10位,同时用户名不能为空,
                // 密码长度不能小于6位,不能大于18位

                // 调用UI层的逻辑判断
                LoginSubmit();


                break;
        }
    }

    //获取内容,进行逻辑判断
    public void LoginSubmit(){

        String m_szAndroidID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.d("ddddd",m_szAndroidID);

        String username = edt_login_username.getText().toString().trim();
        String password = edt_login_password.getText().toString().trim();

        // 注意这里 view层里面if的写法是不是降低耦合性,不要过多的使用if else 而是多使用if
        if(null == username || username.equals("")){

            Tokhttp1501A.toastUtil.ShowLong(LoginActivity.this,"用户名不能为空");

            return;
        }

        if(username.length()>10){

            Tokhttp1501A.toastUtil.ShowLong(LoginActivity.this,"用户名长度不能大于10位");

            return;
        }


        if(null == password || password.equals("")){

            Tokhttp1501A.toastUtil.ShowLong(LoginActivity.this,"密码不能为空");

            return;
        }

        if(password.length()<6){

            Tokhttp1501A.toastUtil.ShowLong(LoginActivity.this,"密码长度不能小于6位");

            return;
        }

        if(password.length()>12){

            Tokhttp1501A.toastUtil.ShowLong(LoginActivity.this,"密码长度不能大于12位");

            return;
        }


        // 将内容传递给p层,p层帮助我们链接model层,进行数据解析同时返回成功的结果,方便前端进行跳转
        loginPresenter.LoginSubmit(username,password, LoginActivity.this);

    }
}
