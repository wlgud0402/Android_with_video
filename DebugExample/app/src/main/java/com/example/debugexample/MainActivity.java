package com.example.debugexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String kim = "김지형";

        Log.e("name", "onCreate: "+ "안녕하신가  mr"+ kim); //에러 출력

        Log.d("name", "onCreate: "+ "안녕하신가  mr"+ kim); //debug 출력

        int a = 10;

        Log.e("int", "onCreate: " + a );
    }
}
