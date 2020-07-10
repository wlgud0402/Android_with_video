package com.example.sharedexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et_save;
    String shared = "file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_save = (EditText)findViewById(R.id.et_save);

        SharedPreferences sharedPreferences = getSharedPreferences(shared, 0); //shared 값을 가져옴

        String value = sharedPreferences.getString("kim",""); // SharedPreferences 를 통해 가져오는 값은 kim 을 키로 한 값이다. (전에 저장한거)
        et_save.setText(value);


    }

    @Override // ctrl + o 를 눌러서 여러가지 자동완성 함수들 목록중에 onDestory 라는 생명주기 하나를 가져옴
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPreferences = getSharedPreferences(shared, 0); // 위에서 String 형태의 변수 shared 값을 저장
        SharedPreferences.Editor editor = sharedPreferences.edit(); //SharedPreferences 의 Editor 라는 기능을 사용하겠다.
        String value = et_save.getText().toString();
        editor.putString("kim", value); // kim 이라는 키를 가진 value 값을 저장하겠다 (putString 을 통해)
        editor.commit(); // 저장
    }
}
