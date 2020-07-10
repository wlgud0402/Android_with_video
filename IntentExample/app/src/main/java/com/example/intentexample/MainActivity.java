package com.example.intentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_move;
    private EditText et_test;
    private String str;

    ImageView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_test = findViewById(R.id.et_test);


        btn_move = findViewById(R.id.btn_move);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str = et_test.getText().toString(); // <1> EditText 에 내가 적은 내용을 str 이라는 위에서 String 의 형태로 정의한 변수에 담아줌

                Intent intent = new Intent(MainActivity.this, SubActivity.class);  // <2> 버튼이 클릭되었을때 : MainActivity 에서 SubActivity 로 넘어간다 (intent)

                intent.putExtra("str", str); // <3> 남긴 str 의 내용을 putExtra 에 넣어서 intent 로 다른페이지로 넘어갈때 함께 넘어감

                startActivity(intent); // <4> 액티비티 이동
            }
        });

        test = findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"지형이는 잘생김",Toast.LENGTH_SHORT).show();  //getApplicationContext => MainActivity 를 뜻함 , 여기서 메시지를, 짧거나 길게 띄워서 show()
            }
        });



    }
}
