package com.sherry.volleytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_qq;
    private Button btn_go;
    private TextView tv_conclusion, tv_analysis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }
    private void initView() {
        // 初始化控件
        et_qq =  findViewById(R.id.et_qq);
        btn_go = findViewById(R.id.btn_go);
        btn_go.setOnClickListener(this);

        tv_conclusion = findViewById(R.id.tv_conclusion);
        tv_analysis = findViewById(R.id.tv_analysis);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go:
                if (et_qq == null) {
                    Toast.makeText(MainActivity.this, "都不留个QQ号佛主怎么算嘞？",
                            Toast.LENGTH_LONG).show();
                } else {
                    volleyGet();
                }
                break;
        }
    }

    private void volleyGet() {
        //获取到输入的QQ号
        String qq = et_qq.getText().toString();
        //第三方接口
        String url = "http://japi.juhe.cn/qqevaluate/qq?key=8d9160d4a96f2a6b5316de5b9d14d09d&qq=" + qq;

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        volleyJson(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "失败：" + error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        queue.add(request);
    }

    private void volleyJson(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response); //获得JSONObject对象
            JSONObject object = jsonObject.getJSONObject("result");
            JSONObject object1 = object.getJSONObject("data");

            tv_conclusion.setVisibility(View.VISIBLE);
            tv_analysis.setVisibility(View.VISIBLE);
            tv_conclusion.setText("结果：" + object1.getString("conclusion"));
            tv_analysis.setText("分析：" + object1.getString("analysis"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
