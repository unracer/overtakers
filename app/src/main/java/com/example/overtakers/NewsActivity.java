package com.example.overtakers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NewsActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);

        Button newsBtn = findViewById(R.id.newsBtn);
        Button marketBtn = findViewById(R.id.marketBtn);
        Button chatBtn = findViewById(R.id.chatBtn);
        ScrollView postsScrollView = findViewById(R.id.postsScrollView);

//         request posts
        String[] posts = new String[]{
                "(1) 27.03.2022 19:01\nTitle1\nSomeText1",
                "(2) 27.03.2022 19:01\nTitle2\nSomeText2",
                "(3) 27.03.2022 19:01\nTitle2\nSomeText2",
                "(4) 27.03.2022 19:01\nTitle2\nSomeText2",
                "(5) 27.03.2022 19:01\nTitle2\nSomeText2",
                "(6) 27.03.2022 19:01\nTitle2\nSomeText2",
                "(7) 27.03.2022 19:01\nTitle2\nSomeText2",
                "(8) 27.03.2022 19:01\nTitle2\nSomeText2",
                "(8) 27.03.2022 19:01\nTitle2\nSomeText2",
                "(9) 27.03.2022 19:01\nTitle2\nSomeText2",
                "(10) 27.03.2022 19:01\nTitle2\nSomeText2",
                "(11) 27.03.2022 19:01\nTitle3\nSomeText3"
        };

//         load to scroll view
        try {
//             Create a LinearLayout element
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);

//             Add Buttons
            for (String post: posts) {
                Button button = new Button(this);
                button.setText(post);
                linearLayout.addView(button);
            }

            //                 Add the LinearLayout element to the ScrollView
            postsScrollView.addView(linearLayout);
        } catch (Exception e){}

        newsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(NewsActivity.this, NewsActivity.class);
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
                    Intent intent = new Intent(NewsActivity.this, ChatActivity.class);
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
                    Intent intent = new Intent(NewsActivity.this, ShopActivity.class);
//                news.putExtra("key", value); //Optional parameters
                    startActivity(intent);
                } catch (Exception e) {
                    Log.d("over", String.valueOf(e));
                }
            }
        });
    }
}
