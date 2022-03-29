package com.example.overtakers;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/** class for LOGIN/REGISTER
 * also update local database
 * */

/**
 * arch app
 *
 * Main* - for services and control
 * - Activity - login, registration, syn dataBase
 * - Slider - contain all app at one activity using slider
 *
 * Shop* - for buying
 * - Activity - goods
 * - About - description, payment
 * - Basket - current orders
 *
 * Chat* - for chating
 * - Activity - general chat
 * */

/**
 * ideology is saveTraffic
 * */

public class MainActivity extends Activity {

//    // obj
//    Button buy_btn, history_btn, basket_btn, news_btn, community_btn, friends_btn, chat_btn;
//    ImageButton myAcc_iBtn;
//    private DBHelper dbHelper;
//    // db
//    private int pointOFResponse;
//    // goods
//    private String id_shop, title, price, about;
//    // user
//    private String idAcc, nickName, pass;
//    // userData
//    private String id_param, value, param;
//    // order
//    private String /*nickName, */ idGoods, nameGoods, pointMeeting, timeMeeting, progress, dateReg, dateArrival;
//    // static fields
//    private static final String TAG = "MAIN_ACTIVITY";
//    private boolean hostIsEmpty = true, updateFlag = false, dbLoaded = false;
//    private String host = "https://overtakeshop.000webhostapp.com/";

    // define
    String remoteHash, currentHash, host, hostFile;
    String id, title, description;
    boolean hostEmpty;
    DBHelper dbHelper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        EditText nickNameEditText = findViewById(R.id.nicknameEditText);
        EditText passwordEditText = findViewById(R.id.nicknameEditText);
        TextView registrationBtn = findViewById(R.id.registrationBtn);

        //init
        host = "overtakers.000webhostapp.com";
        hostFile = "mobile.php";
//        dbHelper = new DBHelper(MainActivity.this);

        /**
         * if local hash - will update dataBases and start slider
         * else - get remote hash by current
         *
         * if find - will update dataBases and start slider
         * else - registration
         * */


//        try {
//            if (dbHelper.getRow("USER","localHash").getCount() > 0) {
//                updateDB();
//                Intent intent = new Intent(MainActivity.this, MainSlider.class);
//                MainActivity.this.startActivity(intent);
//            }
//        } catch (Exception e) {}

        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // for demonstration skip logic
                Intent intent = new Intent(MainActivity.this, MainSlider.class);
                MainActivity.this.startActivity(intent);

                // get users cred
//                String nickName = String.valueOf(nickNameEditText.getText());
//                String password = String.valueOf(passwordEditText.getText());
//                // generate hash
//                currentHash = nickName + password;
//
//                remoteHash = https("GET", host, hostFile, "hash=" + currentHash).get("hash");
//                // parse
//
//                if (!remoteHash.equals("")) {
//                    updateDB();
//                    Intent intent = new Intent(MainActivity.this, MainSlider.class);
//                    MainActivity.this.startActivity(intent);
//                } else {
//                    registration(nickName, password);
//
//                    //restart app
//                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
//                    MainActivity.this.startActivity(intent);
//                    Runtime.getRuntime().exit(0);
//                }
            }
        });

        // gestures
        //common_layout.setOnTouchListener(new SwipeTouchListener(R.layout.activity_main));

    }

    public void registration(String nickName, String password) {
        // hash by name and pass

        // create remote hash

        // create local hash
    }

    public void updateDB () {
//        try {
//
//            // check host
//            cursor = dbHelper.getRow(dbHelper.TABLE_USER, "host"); // const
//            if (cursor.getCount() > 0) {
//                if (cursor.getString(2).length() < 10) { // add regular for check http://
//                    dbHelper.updateRow(dbHelper.TABLE_USER, "host", host);
//                } else {
//                    host = cursor.getString(2);
//                }
//                hostEmpty = false;
//            } else {
//                showMyToast("set HOST and REBOOT");
//                dbHelper.createRow(dbHelper.TABLE_USER, "host", host);
//                hostEmpty = true;
//            }
//
//            // update db
//            if (!hostEmpty) {
//                HashMap<String, String> request = new HashMap<>();
//
//                // clear tables
//                if (dbHelper.getFull(dbHelper.TABLE_GOODS).getCount() > 0) { // if table is not empty -> clear
//                    dbHelper.deleteAll(DBHelper.TABLE_GOODS);
//                }
//
//                request = https("GET", host, hostFile, "func=goods");
//                // send to db
//
//                request = https("GET", host, hostFile, "func=goods");
//                // send to db
//
//                request = https("GET", host, hostFile, "func=goods");
//                // send to db
//            }
//            cursor.close();
//            dbHelper.close();
//
//        } catch (Exception e) {
//            Log.d("bafi", String.valueOf(e));
//        }
    }

//    public HashMap<String, String> https(String method, String host, String hostFile, String args){
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(host)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        ApiRequest Server_ApiRequest = retrofit.create(ApiRequest.class);
//        Call<List<GOODS>> goods = Server_ApiRequest.goodGet("goodsGet");
//        goods.enqueue(new Callback<List<GOODS>>() {
//            @Override
//            public void onResponse(@Nullable Call<List<GOODS>> call, @Nullable Response<List<GOODS>> response) {
//                if (response.isSuccessful()) {
//                    for (int i=0; i < response.body().size(); i++) { // count row in response
//                        id = Long.toString(response.body().get(i).getIdShop());
//                        title = response.body().get(i).getTitle();
//                        description = response.body().get(i).getPrice();
//                        dbHelper.createRow(dbHelper.TABLE_GOODS, title, description); // write to local db
//                    }
//                    Log.w("https", "SERVER..OK.." + String.valueOf(response.body().size()));
//                } else {
//                    Log.i("https", "SERVER..FAIL.." + response.code());
//                    try { Log.i("https", "error.." + response.errorBody().string()); }
//                    catch (IOException e) { e.printStackTrace(); }
//                }
//            }
//            @Override
//            public void onFailure(@Nullable Call<List<GOODS>> call, @Nullable Throwable t) {
//                Log.i("https", "failure " + t);
//            }
//        });
//
//        return new HashMap<>();
//    }

    /** change this to hashmap */
//    public void requestGoods () {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(host)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        ApiRequest Server_ApiRequest = retrofit.create(ApiRequest.class);
//        Call<List<GOODS>> goods = Server_ApiRequest.goodGet("goodsGet");
//        goods.enqueue(new Callback<List<GOODS>>() {
//            @Override
//            public void onResponse(@Nullable Call<List<GOODS>> call, @Nullable Response<List<GOODS>> response) {
//                if (response.isSuccessful()) {
//                    if (dbHelper.getFullGOODS().getCount() > 0) { // if table is not empty -> clear
//                        dbHelper.deleteAll(DBHelper.TABLE_GOODS);
//                    }
//                    dbHelper.deleteAll(DBHelper.TABLE_GOODS);
//                    pointOFResponse = 0;
//                    while (pointOFResponse < response.body().size()) { // count row in response
//                        id_shop = Long.toString(response.body().get(pointOFResponse).getIdShop());
//                        title = response.body().get(pointOFResponse).getTitle();
//                        price = response.body().get(pointOFResponse).getPrice();
//                        about = response.body().get(pointOFResponse).getAbout();
//                        pointOFResponse++;
//                        dbHelper.createRowGOOD(id_shop, title, price, about); // write to local db
//                    }
//                    Log.w(TAG, "SERVER..OK..goods.." + String.valueOf(response.body().size()));
//                } else {
//                    Log.i(TAG, "SERVER..FAIL..goods.." + response.code());
//                    try { Log.i(TAG, "er..goods.." + response.errorBody().string()); }
//                    catch (IOException e) { e.printStackTrace(); }
//                }
//            }
//            @Override
//            public void onFailure(@Nullable Call<List<GOODS>> call, @Nullable Throwable t) {
//                Log.i(TAG, "failure " + t);
//            }
//        });
//    }
//
//    private void requestUsersData () {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(host)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        ApiRequest Server_ApiRequest = retrofit.create(ApiRequest.class);
//        Call<List<USERSDATA>> res = Server_ApiRequest.usersDataGet("usersDataGet");
//        res.enqueue(new Callback<List<USERSDATA>>() {
//            @Override
//            public void onResponse(@Nullable Call<List<USERSDATA>> call, @Nullable Response<List<USERSDATA>> response) {
//                if (response.isSuccessful()) {
//                    if (dbHelper.getFullUSERSDATA().getCount() > 0) { // if table is not empty -> clear
//                        dbHelper.deleteAll(DBHelper.TABLE_USER_DATA);
//                    }
//                    pointOFResponse = 0;
//                    while (pointOFResponse < response.body().size()) { // count row in response
//                        id_param = Integer.toString(response.body().get(pointOFResponse).getId());
//                        param = response.body().get(pointOFResponse).getParam();
//                        value = response.body().get(pointOFResponse).getValue();
//
//                        if (dbHelper.getRowUserData(Integer.toString(pointOFResponse)).getCount() > 0) { // if exist -> update
//                            dbHelper.updateRowUsersData(id_param, value, param);
//                        } else {
//                            dbHelper.createRowUsersData(Integer.toString(pointOFResponse), value, param); // create
//                        }
//                        pointOFResponse++;
//                    }
//                    Log.w("SERVER..OK..userData..", String.valueOf(response.body().size()));
//                } else {
//                    Log.i(TAG, "SERVER..FAIL..userData.. " + response.code());
//                    try { Log.i(TAG, "er..userData.." + response.errorBody().string());
//                    } catch (IOException e) { e.printStackTrace(); }
//                }
//            }
//            @Override
//            public void onFailure(@Nullable Call<List<USERSDATA>> call, @Nullable Throwable t) {
//                Log.i(TAG, "failure " + t);
//            }
//        });
//    }
//    private void requestOrders () {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(host)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        ApiRequest Server_ApiRequest = retrofit.create(ApiRequest.class);
//        Call<List<ORDERS>> orders = Server_ApiRequest.orderGet("orderGet", idAcc);
//        orders.enqueue(new Callback<List<ORDERS>>() {
//            @Override
//            public void onResponse(@Nullable Call<List<ORDERS>> call, @Nullable Response<List<ORDERS>> response) {
//                if (response.isSuccessful()) {
//                    if (dbHelper.getFull().getCount() > 0) { // if table is not empty -> clear
//                        dbHelper.deleteAll(DBHelper.TABLE_ORDERS);
//                    }
//                    dbHelper.deleteAll(DBHelper.TABLE_ORDERS);
//                    pointOFResponse = 0;
//                    while (pointOFResponse < response.body().size()) { // count row in response
//                        idAcc = Long.toString(response.body().get(pointOFResponse).getIdAcc());
//                        nickName = response.body().get(pointOFResponse).getNickName();
//                        idGoods = response.body().get(pointOFResponse).getIdGood();
//                        nameGoods = response.body().get(pointOFResponse).getNameGood();
//                        pointMeeting = response.body().get(pointOFResponse).getPointOfMeeting();
//                        timeMeeting = response.body().get(pointOFResponse).getTimeOfMeeting();
//                        progress = response.body().get(pointOFResponse).getProgress();
//                        dateReg = response.body().get(pointOFResponse).getDataReg();
//                        dateArrival = response.body().get(pointOFResponse).getDateArrival();
//
////                        dbHelper.createRowORDER(idAcc, nickName, idGoods, nameGoods, pointMeeting, timeMeeting, progress, dateReg, dateArrival);
//                        pointOFResponse++;
//                    }
//                    Log.w("SERVER..OK..orders..", String.valueOf(response.body().size()));
//                    dbLoaded = true;
//                } else {
//                    Log.i(TAG, "SERVER..FAIL..orders.." + response.code());
//                    try { Log.i(TAG, "er..order.." + response.errorBody().string());
//                    } catch (IOException e) { e.printStackTrace(); }
//                }
//            }
//            @Override
//            public void onFailure(@Nullable Call<List<ORDERS>> call, @Nullable Throwable t) {
//                Log.i(TAG, "failure " + t);
//            }
//        });
//    }

    private void showMyToast (String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
        toast.show();
    }
}

/**
 * ?? INFO
 * have 4 connection with server at mainActivity.class, windowChat.class, registration.class, mainAccount.class
 *
 * xx CURRENT ERRORS
 * registration order cant post request
 * https://medium.com/@normanaspx/android-retrofit-post-5c84d62ec24d
 *
 * ++ CURRENT TASKS
 * DONE 1 windowChat add connect with www
 * DONE 2 mainActivity check url and add date of update and connect with www
 * ADD 3 registration add connect with www
 * DONE 4 Buy_choseCategory add dataBase connect
 * DONE 5 Buy_aboutProduct add dataBase connect
 * DONE 6 MainSettings
 * ADD 7 registration_order add payment (10% from price) // seller is not offended, and buyer will endure
 * ADD 8 Main_activity add swipe (left - active app, bottom - roll up, right - app)
 * ADD 9 Main_activity rewrite update local db (remove clearing db)
 * */