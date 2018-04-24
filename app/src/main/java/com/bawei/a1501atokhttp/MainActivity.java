package com.bawei.a1501atokhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.bawei.a1501atokhttp.entity.Message;
import com.bawei.a1501atokhttp.entity.Person;
import com.bawei.a1501atokhttp.util.oKHttpSingTop;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // 跳转到该页面需要携带的参数
    public static final String TOKEN = "token";
    public static final String USERID = "userId";

    TextView tv_mian;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取参数
        String token = getIntent().getStringExtra(TOKEN);
        int userId = getIntent().getIntExtra(USERID,0);

        tv_mian = (TextView)findViewById(R.id.tv_mian);

//        ok();
//        pOk();
    }


    public void ok(){

        String url = "http://rec.chaojishipin.com/sarrs/rec?";

        Map<String,String> map = new HashMap<>();

        map.put("auid","c8d75a255caa46dab895844364ede6b0");
        map.put("width","720");
        map.put("imei","99000640102804");
        map.put("appid","0");
        map.put("resolution","1280*720");
        map.put("cid","4");
        map.put("city","CN_1_5_1");
        map.put("appv","1.4.3");
        map.put("area","rec_0703");
        map.put("clientos","4.4.4");
        map.put("pl2","00");
        map.put("token","");
        map.put("appfrom","30000_xiaomi");
        map.put("pl1","0");
        map.put("p","0");
        map.put("pl","1000011");
        map.put("pn","1");
        map.put("lc","c8d75a255caa46dab895844364ede6b0");



        Tokhttp1501A.oKHttpSingTop.setUrl(url,map,Message.class, oKHttpSingTop.Methods.GET);

        //观察者模式
        Tokhttp1501A.oKHttpSingTop.setCallbackM(new oKHttpSingTop.CallbackM() {
            @Override
            public void onFailure(Request request, IOException e) {


            }

            @Override
            public void onResponse(Object response) {


                Message person = (Message) response;

                Log.d("person",person.toString());

//                tv_mian.setText(person.toString());

            }
        });

    }

    public void pOk(){

        String url = "http://api.m.panda.tv/ajax_get_live_list_by_cate?";

        Map<String,String> map = new HashMap<>();
        map.put("cate","yzdr");
        map.put("pageno","1");
        map.put("pagenum","10");
        map.put("__version","1.0.1.1300");
        map.put("__plat","android");

        //网络请求
        Tokhttp1501A.oKHttpSingTop.setUrl(url,map, Person.class, oKHttpSingTop.Methods.PSOT);

        //观察者模式
        Tokhttp1501A.oKHttpSingTop.setCallbackM(new oKHttpSingTop.CallbackM() {

            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Object response) {

                Person person = (Person) response;

                tv_mian.setText(person.toString());
            }
        });


    }
}
