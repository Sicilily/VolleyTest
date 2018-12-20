package com.sherry.volleytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseActivity extends AppCompatActivity {

    private Button btn_QQ;
    private Button btn_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        initView();
        initListener();
    }

    private void initView() {
        btn_QQ = findViewById(R.id.btn_QQ);
        btn_phone = findViewById(R.id.btn_phone);
    }

    private void initListener() {
        btn_QQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ChooseActivity.this, PhoneActivity.class);
                startActivity(intent1);
            }
        });
    }
}
