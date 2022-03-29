package com.example.overtakers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/** class for slider of NEWS/CHAT/SHOP */

public class MainSlider extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_slider);

        Button newsBtn = findViewById(R.id.newsBtn);
        Button chatBtn = findViewById(R.id.chatBtn);
        Button marketBtn = findViewById(R.id.marketBtn);

        /** MENU
         * this code will be remove when add slider to MainSlider.class
         * */
        newsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MainSlider.this, NewsActivity.class);
                    //                news.putExtra("key", value); //Optional parameters
                    startActivity(intent);
                } catch (Exception e) {
                    Log.d("over", String.valueOf(e));
                }
            }
        });
        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MainSlider.this, ChatActivity.class);
//                news.putExtra("key", value); //Optional parameters
                    startActivity(intent);
                } catch (Exception e) {
                    Log.d("over", String.valueOf(e));
                }
            }
        });
        marketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MainSlider.this, ShopActivity.class);
//                news.putExtra("key", value); //Optional parameters
                    startActivity(intent);
                } catch (Exception e) {
                    Log.d("over", String.valueOf(e));
                }
            }
        });
    }
}

//class SwipeTouchListener implements View.OnTouchListener{
//    private Activity activity;
//    private static int MIN_DISTANCE;
//    private float downX;
//    private float downY;
//    private static final String LOG_TAG = "SwipeTouchListener";
//
//    public SwipeTouchListener(Activity _activity) {
//        activity = _activity;
//        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
//        MIN_DISTANCE = (int) (120.0f * dm.densityDpi / 160.0f + 0.5);
//    }
//
//    private void onRightToLeftSwipe() {
//        Log.i(LOG_TAG, "Справа налево!");
//    }
//
//    private void onLeftToRightSwipe() { Log.i(LOG_TAG, "Слева направо!"); }
//
//    private void onTopToBottomSwipe() {
//        Log.i(LOG_TAG, "Сверху вниз!");
//    }
//
//    private void onBottomToTopSwipe() {
//        Log.i(LOG_TAG, "Снизу вверх!");
//    }
//
//    @Override
//    public boolean onTouch(View view, MotionEvent motionEvent) {
//        return false;
//    }
//}
