package com.example.overtakers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class ViewChats extends Activity {

    DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chats);

        dbHelper = new DBHelper(this);
        ImageButton chatsImgBtn = findViewById(R.id.chatsImgBtn);
        ImageButton newsImgBtn = findViewById(R.id.newsImgBtn);
        ImageButton servicesImgBtn = findViewById(R.id.servicesImgBtn);
        LinearLayout chatsLinearlayout = findViewById(R.id.servicesLinearlayout);

        newsImgBtn.setOnClickListener(view -> {
            try {
                startActivity(new Intent(ViewChats.this, ViewNews.class));
            } catch (Exception ignored) {}
        });
        chatsImgBtn.setOnClickListener(view -> {
            try {
                startActivity(new Intent(ViewChats.this, ViewChats.class));
            } catch (Exception ignored) {}
        });
        servicesImgBtn.setOnClickListener(view -> {
            try {
                startActivity(new Intent(ViewChats.this, ViewServices.class));
            } catch (Exception ignored) {}
        });

        // load chats
        try {
            chatsLinearlayout.removeAllViews();
            for (String table : dbHelper.dbTablesName()) {
                if (table.split("chat_")[1] != null)
                    newButton(this, chatsLinearlayout, table);
            }
        } catch (Exception ignored) {}
    }

    private void newButton (Context context, LinearLayout linearLayout, String text) {
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        Button btn = new Button(context);
        btn.setText(text);
        btn.setOnClickListener(v -> startActivity(new Intent(this, ViewChat.class).putExtra("text", text)));
        linearLayout.addView(btn, lParams);
    }
}