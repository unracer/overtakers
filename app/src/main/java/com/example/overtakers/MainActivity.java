package com.example.overtakers;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

public class MainActivity extends Activity {
    Context context;

    String host = "api.host.com",

    // steep of create app: view -> db -> api -> business-logic
    // api naming: Существительные — это хорошо, а глаголы — плохо

    // make json with filed to access
    // post get update delete
    // should have cookie (cred(sha256))
    // that v1

    apiUser =     "http://" +host+ "/user",
    apiUserId =   "http://" +host+ "/user/id",
    apiShop =     "http://" +host+ "/shop",
    apiShopId =   "http://" +host+ "/shop/id",
    apiNews =     "http://" +host+ "/news",
    apiNewsId =   "http://" +host+ "/news/id",
    apiChat =     "http://" +host+ "/chat",
    apiChatId =   "http://" +host+ "/chat/id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        EditText nickNameEditText = findViewById(R.id.nicknameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        Button registrationBtn = findViewById(R.id.registrationBtn);
        HttpPacket httpPacket = new HttpPacket();

        // long pool request for all db
        // just init local db by remote db
        // shouldn't have business-logic

        // load by cred (hash = login + pass by sha256)
        Thread dbUserSync = new Thread(() -> {
            try {
                String lastData = String.valueOf(httpPacket.data);
                httpPacket.httpLong(apiUser, "POST", new JSONObject().put("cred", new DBHelper(context).dbRowGetById("user", 1, new String[] {"cred"})[0]));
                while (true) {
                    if (!lastData.equals(String.valueOf(httpPacket.data))) {
                        new DBHelper(context).dbRowUpdate(
                            "user",
                            Long.parseLong(httpPacket.data.get("id").toString()),
                            new String[] {"login", "cred", "chats", "avatar"},
                            new String[] {
                                    httpPacket.data.get("login").toString(),
                                    httpPacket.data.get("cred").toString(),
                                    httpPacket.data.get("chats").toString(),
                                    httpPacket.data.get("avatar").toString()
                            });
                    }
                }
            } catch (Exception e) { Log.d("main", String.valueOf(e));}
        });

        Thread dbShopSync = new Thread(() -> {
            try {
                String lastData = String.valueOf(httpPacket.data);
                httpPacket.httpLong(apiShop, "GET", new JSONObject().put("cred", new DBHelper(context).dbRowGetById("user", 1, new String[] {"cred"})[0]));
                while (true) {
                    if (!lastData.equals(String.valueOf(httpPacket.data))) {
                        new DBHelper(context).dbRowUpdate(
                                "shop",
                                Long.parseLong(httpPacket.data.get("id").toString()),
                                new String[] {"title", "description", "sale", "price"},
                                new String[] {
                                        httpPacket.data.get("title").toString(),
                                        httpPacket.data.get("description").toString(),
                                        httpPacket.data.get("sale").toString(),
                                        httpPacket.data.get("price").toString()
                                });
                    }
                }
            } catch (Exception e) { Log.d("main", String.valueOf(e));}
        });

        Thread dbNewsSync = new Thread(() -> {
            try {
                String lastData = String.valueOf(httpPacket.data);
                httpPacket.httpLong(apiNews, "GET", new JSONObject().put("cred", new DBHelper(context).dbRowGetById("user", 1, new String[] {"cred"})[0]));
                while (true) {
                    if (!lastData.equals(String.valueOf(httpPacket.data))) {
                        new DBHelper(context).dbRowUpdate(
                                "news",
                                Long.parseLong(httpPacket.data.get("id").toString()),
                                new String[] {"date", "category", "title", "description"},
                                new String[] {
                                        httpPacket.data.get("date").toString(),
                                        httpPacket.data.get("category").toString(),
                                        httpPacket.data.get("title").toString(),
                                        httpPacket.data.get("description").toString()
                                });
                    }
                }
            } catch (Exception e) { Log.d("main", String.valueOf(e));}
        });

        // just create chat tables and when you need output this chat, should by pattern "chat_<name>"
        Thread dbChatSync = new Thread(() -> {
            try {
                String lastData = String.valueOf(httpPacket.data);
                httpPacket.httpLong(apiChatId, "GET", new JSONObject().put("cred", new DBHelper(context).dbRowGetById("user", 1, new String[] {"cred"})[0]));
                while (true) {
                    if (!lastData.equals(String.valueOf(httpPacket.data))) {
                        new DBHelper(context).dbRowUpdate(
                                "chat_" + httpPacket.data.get("name"),
                                Long.parseLong(httpPacket.data.get("id").toString()),
                                new String[] {"name", "message"},
                                new String[] {
                                        httpPacket.data.get("name").toString(),
                                        httpPacket.data.get("message").toString()
                                });
                    }
                }
            } catch (Exception e) { Log.d("main", String.valueOf(e));}
        });

        /*
         * if cred exist - open app
         * else get remoteCred by insertedCred
         *      if found - load remote to local db
         *      else registration - send cred to remote db
         * */

        try {
            DBHelper dbHelper = new DBHelper(MainActivity.this);
            // get valid cred
            if (dbHelper.dbRowGetById("user", 1, new String[]{"login", "cred", "chats", "avatar"}).length > 0) {
                dbUserSync.start();
                dbShopSync.start();
                dbNewsSync.start();
                dbChatSync.start();
                MainActivity.this.startActivity(new Intent(MainActivity.this, ViewChats.class));
            } else {

                // btn event
                registrationBtn.setOnClickListener(view -> {
                    try {

                        // get inserted cred
                        String credentials = String.valueOf(nickNameEditText.getText()) + passwordEditText.getText(); // any pass more 20 symbols

                        // make hash
                        MessageDigest digest = MessageDigest.getInstance("SHA-256");
                        byte[] insertedUserCred; // encodedHash
                        insertedUserCred = digest.digest(credentials.getBytes(StandardCharsets.UTF_8));

                        // get remoteUserCred by insertedUserCred
                        httpPacket.https(apiUser, "POST", new JSONObject().put("cred", insertedUserCred));
                        if (httpPacket.status == 200 && insertedUserCred == httpPacket.data.get("cred")) {
                            // load to local db
                            dbHelper.dbRowCreate(
                                    "user",
                                    new String[]{"login", "cred", "chats", "avatar"},
                                    new String[]{
                                            String.valueOf(httpPacket.data.get("login")),
                                            Arrays.toString(insertedUserCred),
                                            String.valueOf(httpPacket.data.get("chats")),
                                            String.valueOf(httpPacket.data.get("avatar"))}
                            );

                            dbUserSync.start();
                            dbShopSync.start();
                            dbNewsSync.start();
                            dbChatSync.start();

                            MainActivity.this.startActivity(new Intent(MainActivity.this, ViewChats.class));
                        } else {
                            // registration on remote db
                            // send to remote db
                            httpPacket.https(
                                    apiUser,
                                    "POST",
                                    new JSONObject()
                                            .put("login", String.valueOf(nickNameEditText.getText()))
                                            .put("cred", insertedUserCred)
                                            .put("chats", "chat_general")
                                            .put("avatar", "x.gif")
                            );

                            //restart app
                            MainActivity.this.startActivity(new Intent(MainActivity.this, MainActivity.class).addFlags(FLAG_ACTIVITY_NEW_TASK));
                            Runtime.getRuntime().exit(0);
                        }
                    } catch (Exception e) {
                        // for preview other part of application
                        startActivity(new Intent(this, ViewChats.class));
                    }
                });
            }
        } catch (Exception e) {
            // for preview other part of application
            startActivity(new Intent(this, ViewChats.class));
        }
    }

//    public String[] https(String method, String url, String args){
//        try {
//
//            //request
//            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
//            HttpsURLConnection connection = (HttpsURLConnection) new URL(url+args).openConnection(); // getInputStream for long poll
//            connection.setSSLSocketFactory(sslSocketFactory);
//            connection.setRequestMethod(method);
//
//            connection.getResponseCode();
//
//            //response
//            BufferedReader br = new BufferedReader(new InputStreamReader((InputStream) connection.getContent()));
//            String input;
//            String[] data = new String[]{""};
//            while ((input = br.readLine()) != null) { data[0] = data[0] + input; }
//            br.close();
//            return data[0];
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    public String longPollHttps(String method, String url, String args){
//        try {
//            //request
//            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
//            HttpsURLConnection con = (HttpsURLConnection) new URL(url+args).openConnection(); // getInputStream for long poll
//            con.setSSLSocketFactory(sslSocketFactory);
//            con.setRequestMethod(method);
//
//            //response
//            BufferedReader br = new BufferedReader(new InputStreamReader((InputStream) con.getContent()));
//            while (true) {
//                if (br.readLine() != null)
//                    return br.readLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
}

class HttpPacket {

    //response
    int status;
    JSONObject data;

    SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

    void https(String url, String method, JSONObject data){
        try {
            //execute
            HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection(); // getInputStream for long poll
            connection.setSSLSocketFactory(sslSocketFactory);
            connection.setRequestMethod(method);

            switch (method) {
                case "GET": {
                    //response
                    BufferedReader br = new BufferedReader(new InputStreamReader((InputStream) connection.getContent()));
                    String input;
                    String[] response = new String[]{""};
                    while ((input = br.readLine()) != null) {
                        response[0] += input;
                    }
                    br.close();

                    // clear json
                    response[0] = "{" + response[0].split("\\{", 2)[1];

                    this.data = new JSONObject(response[0]);
                }
                case "POST": {
                    connection.addRequestProperty("Accept", "application/json");

                    try(OutputStream os = connection.getOutputStream()) {
                        byte[] input = data.toString().getBytes(StandardCharsets.UTF_8);
                        os.write(input, 0, input.length);
                    }
                }
            }

            this.status = connection.getResponseCode();

        } catch (Exception e) { Log.d("main", String.valueOf(e));}
    }

    void httpLong (String url, String method, JSONObject data){
        try {
            // set fields

            //execute
            HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection(); // getInputStream for long poll
            connection.setSSLSocketFactory(sslSocketFactory);
            connection.setRequestMethod(method);

            this.status = connection.getResponseCode();

            //response
            BufferedReader br = new BufferedReader(new InputStreamReader((InputStream) connection.getContent()));
            String input;
            String[] response = new String[]{""};
            while ((input = br.readLine()) != null) {
                response[0] += input;
            }

            // clear json
            response[0] = "{" + response[0].split("\\{", 2)[1];

            this.data = new JSONObject(response[0]);

            httpLong(url, method, null);

        } catch (Exception e) { Log.d("main", String.valueOf(e));}
    }

}

/*
  logic
  1) main activity for sync db
  2) other activity for load from local db

  todo
  DONE 1 project view
  DEV  2 project dataBase
  DEV  3 sync dataBase
  DEV  4 set linearlayout by database

  test got one field new String[] {"login}
  */