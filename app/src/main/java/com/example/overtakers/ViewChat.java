package com.example.overtakers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ViewChat extends Activity {
    DBHelper dbHelper;
    String login;
    byte[] obfuscationKey = "hackMeNow".getBytes(StandardCharsets.UTF_8);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        LinearLayout chatLinearlayout = findViewById(R.id.servicesLinearlayout);
        EditText chatEitText = findViewById(R.id.chatEitText);
        ImageButton newsImgBtn = findViewById(R.id.newsImgBtn);
        ImageButton chatsImgBtn = findViewById(R.id.chatsImgBtn);
        ImageButton servicesImgBtn = findViewById(R.id.servicesImgBtn);

        chatLinearlayout.removeAllViews();

        try {
            login = dbHelper.dbRowGetById("user", 1, new String[] {"login"})[0];
        } catch (Exception e ) {
            Log.d("chat", String.valueOf(e));
        }

        // set default for preview if empty db
        if (dbHelper.dbRowGetById("user", 1, new String[]{"cred"}).length < 1) {
            String[] posts = new String[]{
                    "name_message",
                    "name_message",
                    "name_message",
                    "name_message",
                    "name_message",
                    "name_message",
                    "name_message",
                    "name_message",
                    "name_message",
                    "name_message",
                    "name_message",
            };

            for (String post: posts) {
                String chatName = new Intent().getExtras().getString("text");
                dbHelper.dbRowCreate(chatName, new String[] {chatName, "message"}, new String[] {chatName, post.split("_", 2)[1]});
            }
        }

        // load chat
        try {
            Cursor cursor = dbHelper.dbRowAll(new Intent().getExtras().get("text").toString());
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    newButton(this, chatLinearlayout, cursor.getString(1));
                    cursor.moveToNext();
                }
            }
        } catch (Exception ignored) {}

        // handlers
        chatEitText.setOnEditorActionListener((v, actionId, event) -> {
            try {
                String apiChat = "", name, message;
                if (actionId == EditorInfo.IME_ACTION_GO) {  // by touch on enter

                    // obfuscate
                    name = xorObfuscate(login.getBytes(), obfuscationKey);
                    message = xorObfuscate(chatEitText.getText().toString().getBytes(), obfuscationKey);

                    // send
                    HttpPacket httpPacket = new HttpPacket();
                    httpPacket.https(
                            apiChat,
                            "POST",
                            new JSONObject()
                                    .put("cred", dbHelper.dbRowGetById("user", 1, new String[]{"cred"})[0])
                                    .put("message", name + " " + message)
                    );
                    chatEitText.setText(null);
                }
            } catch (Exception ignored) {}
            return true;
        });

        newsImgBtn.setOnClickListener(view -> {
            try {
                startActivity(new Intent(ViewChat.this, ViewNews.class));
            } catch (Exception ignored) {}
        });
        chatsImgBtn.setOnClickListener(view -> {
            try {
                startActivity(new Intent(ViewChat.this, ViewChats.class));
            } catch (Exception ignored) {}
        });
        servicesImgBtn.setOnClickListener(view -> {
            try {
                startActivity(new Intent(ViewChat.this, ViewServices.class));
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

    public String xorObfuscate(byte[] bufferStr, byte[] bufferKey) {
        for (int i = 0; i < bufferStr.length; i++) {
            bufferStr[i] ^= bufferKey[i % bufferKey.length];
        }
        return Arrays.toString(bufferStr);
    }
}


// tasks #####################
// get msg as POST method
// change charset to iso 8859 5