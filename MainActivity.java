package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // 현재시간을 msec 으로 구한다.
    long now = System.currentTimeMillis();
    // 현재시간을 date 변수에 저장한다.
    Date date = new Date(now);
    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    // nowDate 변수에 값을 저장한다.
    String formatDate = sdfNow.format(date);
    TextView dateNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // 25 ~ 26줄 상단 바 없애기
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
        boolean isImmersiveModeEnabled = ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
        if (isImmersiveModeEnabled) {
            Log.i("Is on?", "Turning immersive mode mode off. ");
        } else {
            Log.i("Is on?", "Turning immersive mode mode on.");
        }
        newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        getWindow().getDecorView().setSystemUiVisibility(newUiOptions); // 27 ~ 39줄 하단 바 없애기

        setContentView(R.layout.activity_main);

        dateNow = (TextView) findViewById(R.id.dateNow);
        dateNow.setText(formatDate);    // TextView 에 현재 시간 문자열 할당

        String text0 = "조국";
        String text1 = "구속";
        String text2 = "백종원";
        String text3 = "탄핵";
        String text4 = "시위";
        String text5 = "티모"; // 42 ~ 47줄 임시로 작성한 키워드

        String keyword[] = new String[6];
        keyword[0] = text0;
        keyword[1] = text1;
        keyword[2] = text2;
        keyword[3] = text3;
        keyword[4] = text4;
        keyword[5] = text5; // 49 ~ 55줄 키워드들로 keyword 배열을 초기화

        float buttonX[] = new float[6];
        float buttonY[] = new float[6];

        String keywordColorGreen[] = new String[10];
        keywordColorGreen[0] = "#ACFA58";
        keywordColorGreen[1] = "#82FA58";
        keywordColorGreen[2] = "#81F79F";
        keywordColorGreen[3] = "#31B404";
        keywordColorGreen[4] = "#01DF3A";
        keywordColorGreen[5] = "#00FF00";
        keywordColorGreen[6] = "#2EFE2E";
        keywordColorGreen[7] = "#BCF5A9";
        keywordColorGreen[8] = "#D8F781";
        keywordColorGreen[9] = "#01710F"; // 57 ~ 67줄 버튼의 색상 배열(초록색 테마)

        final FrameLayout lm = (FrameLayout) findViewById(R.id.fl);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10,10,10,10);

        Random random = new Random(); // 77줄 버튼의 위치를 결정해주는 난수 설정
        Random randomColor = new Random(); // 78줄 버튼의 색상을 결정해주는 난수 설정

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y; // 80 ~ 84줄 디스플레이의 해상도 확인

        for(int i = 0; i < 6; i++){
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);

            int wordColorNumber = randomColor.nextInt(100) % 10; // 89줄 버튼 색상 난수 설정

            final Button btn = new Button(this);


            btn.setId(i + 1);
            btn.setText(keyword[i]);
            btn.setTextColor(Color.parseColor(keywordColorGreen[wordColorNumber]));
            btn.setBackgroundColor(Color.TRANSPARENT);
            btn.setPadding(5,5,5,5);
            btn.setWidth(600);
            btn.setHeight(270);
            btn.setTextSize(random.nextInt(35) + 30); // 91 ~ 99줄 버튼의 아이디, 키워드, 색상, 위치, 크기를 정함
            btn.setLayoutParams(params);

            int countX = 1;
            int countY = 1;
            int x = 0, y = 0;

            if(i > 0) {
                    while (countX != 0) {
                        countX = 0;
                        x = random.nextInt(width) - 600;
                        for (int a = i - 1; a >= 0; a--) {
                            if (x > buttonX[a] - 300 && x < buttonX[a] + 300) {countX++;}
                        }
                    }
                    while (countY != 0) {
                    countY = 0;
                    y = random.nextInt(height) - 270;
                    for (int a = i - 1; a >= 0; a--) {
                        if (y > buttonY[a] - 300 && y < buttonY[a] + 300) {countY++;}
                    }
                }
            }
            else if (i == 0){
                x = random.nextInt(width) - 600;
                y = random.nextInt(height) - 270;
            }

            if(x < 0){
                btn.setX(0);
            }
            else if (x > width - 600){
                btn.setX(width - 600);
            }
            else {
                btn.setX(x);
            }
            if(y < 0){
                btn.setY(0);
            }
            else if (x > height - 270){
                btn.setY(height - 270);
            }
            else {
                btn.setY(y);
            } // 101 ~ 121줄 버튼의 위치 임의로 설정

            buttonX[i] = btn.getX();
            buttonY[i] = btn.getY();

            btn.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view){
                    Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                    startActivity(intent);
                }
            }); // 123 ~ 128줄 버튼 클릭 시 다음 화면으로 넘어가는 기능 부여

            lm.addView(btn);
            lm.addView(ll); // 131 ~ 132줄 Layout과 버튼 보여주기
        }

        Button previousKeyword = (Button) findViewById(R.id.previousKeyword);

        previousKeyword.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), PreviousWC.class);
                startActivity(intent);
            }
        });
    }
}
