package com.example.listexample01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView)findViewById(R.id.list);

        List<String> data = new ArrayList<>(); //저장할 자료형을 만들어줌 <String> 의 형태로 , ArrayList : 배열안에 String 의 형태 List 를 넣겠다.

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data); // ListView 와 내가 만든 ListArray 를 연결시켜주는 Adapter 설정 (중간다리)
                                                 // this : MainActivity, 안드로이드에서 기본으로 제공하는 리스트 폼 , date (위에서 내가 선언한 data)

        list.setAdapter(adapter); // adapter 를 list 에 세팅

        data.add("지형드로이드");
        data.add("안드로이드마스터");
        data.add("노벨상");


        adapter.notifyDataSetChanged(); // 저장완료료







    }
}