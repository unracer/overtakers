package com.example.overtakers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ViewNews extends Activity {
    DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);

        ImageButton newsImgBtn = findViewById(R.id.newsImgBtn);
        ImageButton servicesImgBtn = findViewById(R.id.servicesImgBtn);
        ImageButton chatsImgBtn = findViewById(R.id.chatsImgBtn);
        LinearLayout newsLinearlayout = findViewById(R.id.servicesLinearlayout);

        newsLinearlayout.removeAllViews();

        // set default for preview if empty db
        if (dbHelper.dbRowGetById("user", 1, new String[]{"cred"}).length < 1) {
            String[] posts = new String[]{
                    "date_category_title_description",
                    "date_category_title_description",
                    "date_category_title_description",
                    "date_category_title_description",
                    "date_category_title_description",
                    "date_category_title_description",
                    "date_category_title_description",
                    "date_category_title_description",
                    "date_category_title_description",
                    "date_category_title_description",
                    "date_category_title_description",
                    "date_category_title_description"
            };

            for (String post: posts) {
                dbHelper.dbRowCreate(
                        "news",
                        new String[] {"date", "category", "title", "description"},
                        new String[] {
                                post.split("_", 2)[0],
                                post.split("_", 2)[1],
                                post.split("_", 2)[2],
                                post.split("_", 2)[3]}
                );
            }
        }

        // load chat
        try {
            Cursor cursor = dbHelper.dbRowAll("news");
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    newButton(
                            this,
                            newsLinearlayout,
                            cursor.getString(1) + "\n" +
                            cursor.getString(2) + "\n" +
                            cursor.getString(3) + "\n" +
                            cursor.getString(4) + "\n"
                    );
                    cursor.moveToNext();
                }
            }
        } catch (Exception ignored) {}

        newsImgBtn.setOnClickListener(view -> {
            try {
                startActivity(new Intent(ViewNews.this, ViewNews.class));
            } catch (Exception ignored) {}
        });
        chatsImgBtn.setOnClickListener(view -> {
            try {
                startActivity(new Intent(ViewNews.this, ViewChats.class));
            } catch (Exception ignored) {}
        });
        servicesImgBtn.setOnClickListener(view -> {
            try {
                startActivity(new Intent(ViewNews.this, ViewServices.class));
            } catch (Exception ignored) {}
        });
    }

    private void newButton (Context context, LinearLayout linearLayout, String text) {
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        Button btn = new Button(context);
        btn.setText(text);
        btn.setOnClickListener(v -> {
            // action list
        });
        linearLayout.addView(btn, lParams);

        // notify user about new msg
        String CHANNEL_ID = "101";
        final int NOTIFY_ID = 101;
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("overtakers")
                        .setContentText("new msg")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFY_ID, builder.build());
    }
}
