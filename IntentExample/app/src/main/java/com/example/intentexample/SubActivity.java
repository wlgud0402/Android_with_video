package com.example.intentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    private TextView tv_sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        tv_sub = findViewById(R.id.tv_sub);

        Intent intent = getIntent(); // <5> intent 로 날아온것을 getIntent() 로 받아줌

        String str = intent.getStringExtra("str"); // <6> String 의 형태로 날아온 putExtra 를 getStringExtra 로 똑같이 String 의 형태로 받아줌


        tv_sub.setText(str);  // <7> 받은 str 을 원래 있었던 TextView ( id: tv_sub ) 에 setText 로 내용을 변경해줌
    }
}
