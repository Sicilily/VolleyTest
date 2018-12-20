package com.sherry.volleytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class PhoneActivity extends AppCompatActivity {

    private EditText et_phone;
    private Button btn_yes;
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        initView();
        initListener();
    }

    private void initView() {
        et_phone = findViewById(R.id.et_phone);
        btn_yes = findViewById(R.id.btn_yes);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
    }

    private void initListener() {
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_phone == null) {
                    Toast.makeText(PhoneActivity.this, "号码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    volleyGet();
                }
            }
        });
    }

    private void volleyGet() {
        String phone = et_phone.getText().toString();
        String url = "http://apis.juhe.cn/mobile/get?phone=" + phone + "&key=22a6ba14995ce26dd0002216be51dabb";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                volleyJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PhoneActivity.this, "失败："+error.toString(),
                        Toast.LENGTH_LONG).show();
            }
        });
        queue.add(request);
    }

    private void volleyJson(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject object = jsonObject.getJSONObject("result");

            text1.setVisibility(View.VISIBLE);
            text2.setVisibility(View.VISIBLE);
            text3.setVisibility(View.VISIBLE);

            text1.setText("归属地：" + object.getString("province") + " " + object.getString("city"));
            text2.setText("区号:" + object.getString("areacode"));
            text3.setText("运营商:" + object.getString("company"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
