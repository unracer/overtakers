package com.example.overtakers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

public class ViewServices extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);

        ImageButton newsImgBtn = findViewById(R.id.newsImgBtn);
        ImageButton chatsImgBtn = findViewById(R.id.chatsImgBtn);
        ImageButton servicesImgBtn = findViewById(R.id.servicesImgBtn);

        // services
        ImageButton shopImageButton = findViewById(R.id.shopImageButton);

        shopImageButton.setOnClickListener(view -> {
            try {
                startActivity(new Intent(ViewServices.this, ViewShop.class));
            } catch (Exception e) { Log.d("services", String.valueOf(e));}
        });

        // footer
        newsImgBtn.setOnClickListener(view -> {
            try {
                startActivity(new Intent(ViewServices.this, ViewNews.class));
            } catch (Exception e) { Log.d("services", String.valueOf(e));}
        });
        chatsImgBtn.setOnClickListener(view -> {
            try {
                startActivity(new Intent(ViewServices.this, ViewChats.class));
            } catch (Exception e) { Log.d("services", String.valueOf(e));}
        });
        servicesImgBtn.setOnClickListener(view -> {
            try {
                startActivity(new Intent(ViewServices.this, ViewServices.class));
            } catch (Exception e) { Log.d("services", String.valueOf(e));}
        });
    }
}
