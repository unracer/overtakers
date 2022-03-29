package com.example.overtakers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/** class for CHAT */

public class ChatActivity extends Activity {

    //xor fields
//    byte[] bufferStr;
//    byte[] bufferKey = "l".getBytes();
//
//    //static
//    int idGetMsg;
//    String funcMsgGet = "msgGet", funcMsgCreate = "msgCreate";
//    String idZero = "0", idOne = "1", angleBracket = " > ";
//    String notifyTitle = "overtake", notifyMsg = "new msg";
//    private final String TAG = "chat";
//    private static final int NOTIFY_ID = 101;
//    private static final String CHANNEL_ID = "overtake";
//
//    //tmp
//    private String time, nickname, msg, host;
//    private int msgLastId = 0, countReceivedMsg;
//
//    //obj
//    LinearLayout llWindowChat;
//    EditText msg_eT, nickname_eT;

//    LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//    DBHelper dbHelper;

//    @SuppressLint({"SimpleDateFormat", "GetInstance", "NewApi"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);

//        llWindowChat = findViewById(R.id.chat_ll);
        EditText msgET = findViewById(R.id.msgET);
        Button newsBtn = findViewById(R.id.newsBtn);
        Button chatBtn = findViewById(R.id.chatBtn);
        Button marketBtn = findViewById(R.id.marketBtn);

//        dbHelper = new DBHelper(this);

        // get host
//        Cursor cursor = dbHelper.getRow(idOne);
//        if (cursor.getCount() > 0) { // check host
//            host = dbHelper.getRow(idOne).getString(2);
//
//            Thread myThready = new Thread(() -> {
//                try {
//                    msgLastId = 0; // load all
//                    getMsg();
//                    Thread.sleep(3000);
//                    while (true) { // background mode yeah xD
//                        getMsg(); // increment logic inside
//                        Thread.sleep(2000); // wait data
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//            myThready.start();
//        } else {
//            Toast toast = Toast.makeText(this, "setup host in settings", Toast.LENGTH_SHORT);
//            toast.show();
//        }

        // handlers
//        msg_eT.setOnEditorActionListener((v, actionId, event) -> {
//            if ( actionId ==  EditorInfo.IME_ACTION_GO) {  // by touch on enter
//                time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
//                nickname = nickname_eT.getText().toString();
//                msg = msg_eT.getText().toString();
////                sendMsg(/*time, nickname, msg*/); // set this to fields
//                msg_eT.setText(null);
//            }
//            return true;
//        });

        newsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(ChatActivity.this, NewsActivity.class);
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
                    Intent intent = new Intent(ChatActivity.this, ChatActivity.class);
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
//                try {
//                    Intent intent = new Intent(ChatActivity.this, ShopActivity.class);
////                news.putExtra("key", value); //Optional parameters
//                    startActivity(intent);
//                } catch (Exception e) {
//                    Log.d("over", String.valueOf(e));
//                }
            }
        });
    }

//    public void msgToTV (String text) {
//        final Button msgBtn = new Button(this);
//        msgBtn.setText(text);
//        llWindowChat.addView(msgBtn, this.lParams);
//
//        // notify user about new msg
//        NotificationCompat.Builder builder =
//                new NotificationCompat.Builder(ChatActivity.this, CHANNEL_ID)
//                        .setSmallIcon(R.drawable.ic_launcher_foreground)
//                        .setContentTitle(notifyTitle)
//                        .setContentText(notifyMsg)
//                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//
//        NotificationManagerCompat notificationManager =
//                NotificationManagerCompat.from(ChatActivity.this);
//        notificationManager.notify(NOTIFY_ID, builder.build());
//    }
//
//    public void getMsg () {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(host)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        ApiRequest Server_ApiRequest = retrofit.create(ApiRequest.class);
//
//        // get all
//        if (msgLastId == 0) {
//            Call<List<CHAT>> getMsg = Server_ApiRequest.msgGet(funcMsgGet, idZero);
//            getMsg.enqueue(new Callback<List<CHAT>>() {
//                @Override
//                public void onResponse(Call<List<CHAT>> call, Response<List<CHAT>> response) {
//                    if (response.isSuccessful()) {
//                        countReceivedMsg = response.body().size();
//                        for (int i = 0; i < countReceivedMsg; i++) {
//                            idGetMsg = (int) response.body().get(i).getId();
////                            time = response.body().get(i).getTime();
//                            nickname = response.body().get(i).getNickname();
//                            msg = response.body().get(i).getMsg();
//
//                            // decrypt and set msg to linearLayout
//                            obfuscateMsg();
//                            msgToTV(nickname + angleBracket + msg);
//                            msgLastId = idGetMsg;
//                        }
//                    } else {
//                        Log.i(TAG, String.valueOf(response.code()));
//                        try {Log.i(TAG, response.errorBody().string());
//                        } catch (IOException e) { e.printStackTrace(); }
//                    }
//                }
//                @Override
//                public void onFailure(Call<List<CHAT>> call, Throwable t) {
//                    Log.i(TAG, String.valueOf(t));
//                }
//            });
//        // get last
//        } else {
//            Log.i(TAG, String.valueOf(msgLastId));
//            Call<List<CHAT>> messages = Server_ApiRequest.msgGet(funcMsgGet, Integer.toString(msgLastId + 1)); // its mean next msg
//            messages.enqueue(new Callback<List<CHAT>>() {
//                @Override
//                public void onResponse(Call<List<CHAT>> call, Response<List<CHAT>> response) {
//                    if (response.isSuccessful()) {
//                        if (response.body().size() > 0) {
//                            long id = response.body().get(0).getId();
////                            time = response.body().get(0).getTime();
//                            nickname = response.body().get(0).getNickname();
//                            msg = response.body().get(0).getMsg();
//
//                            Log.i(TAG, nickname);
//                            if (id != msgLastId) {
//                                // set msg to linearLayout
//                                obfuscateMsg();
//                                msgToTV(nickname + angleBracket + msg);
//                                ++msgLastId;
//                                Log.i(TAG, String.valueOf(msgLastId) + " " + nickname);
//                            }
//                        }
//                    } else {
//                        Log.i(TAG, String.valueOf(response.code()));
//                        try { Log.i(TAG, response.errorBody().string()); }
//                        catch (IOException e) { e.printStackTrace(); }
//                    }
//                }
//                @Override
//                public void onFailure(Call<List<CHAT>> call, Throwable t) {
//                    Log.i(TAG, String.valueOf(t));
//                }
//            });
//        }
//    }
//
//    public void sendMsg (/*String time, String nickname, String msg*/) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(host)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        ApiRequest Server_ApiRequest = retrofit.create(ApiRequest.class);
//
//        obfuscateMsg(); // this change fields
//
//        Call<List<CHAT>> createMsg = Server_ApiRequest.msgCreate(funcMsgCreate, time, nickname, msg);
//        createMsg.enqueue(new Callback<List<CHAT>>() {
//            @Override
//            public void onResponse(Call<List<CHAT>> call, Response<List<CHAT>> response) {
//                if (response.isSuccessful()) {
//                    Log.i(TAG, "success" + String.valueOf(response.body().size()));
//                } else {
//                    Log.i(TAG, String.valueOf(response.code()));
//                    try { Log.i(TAG, "else" + response.errorBody().string()); }
//                    catch (IOException e) { e.printStackTrace(); }
//                }
//            }
//            @Override
//            public void onFailure(Call<List<CHAT>> call, Throwable t) {
//                Log.i(TAG, "Failure " + String.valueOf(t));
//            }
//        });
//    }

//    public String xorObfuscate(/*setup txt, key to byteArray */) {
//        for (int i = 0; i < bufferStr.length; i++) {
//            bufferStr[i] ^= bufferKey[i % bufferKey.length];
//        }
//        return new String(bufferStr);
//    }
//
//    public void obfuscateMsg () {
//        bufferStr = nickname.getBytes();
//        nickname = xorObfuscate();
//
//        bufferStr = msg.getBytes();
//        msg = xorObfuscate();
//    }
}


// tasks #####################
// get msg as POST method
// change charset to iso 8859 5