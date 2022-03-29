package com.example.overtakers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShopBasket extends Activity {
    private final String TAG = "PRODUCT_BASKET";
    Button home_btn, second_btn, all_btn;
    LinearLayout myBasket_ll;
    LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
    DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_basket);

//        second_btn = findViewById(R.id.second_btn);
//        all_btn = findViewById(R.id.all_btn);
//        home_btn = findViewById(R.id.home_btn);

        myBasket_ll = findViewById(R.id.chat_ll);

        Log.i(TAG, "start activity");

        dbHelper = new DBHelper(this);

        /*
        * OUTPUT
        * */
//        Cursor cursor = dbHelper.getFull("");
//        if (cursor.getCount() > 0) {
//            int id = 0;
//            String id_shop, name, family, dataCard, idGoods, nameGoods, order, pointOfMeeting, timeOfMeeting, progress, dataReg, dateArrival;
//            if (cursor.getCount() > 0) {
//                cursor.moveToFirst();
//                while (!cursor.isAfterLast()) {
//                    // comment - its should not output
////                    id = cursor.getInt(0);
//                    id_shop = cursor.getString(1);
//                    name = cursor.getString(2); // only for checks in next activity
////                    family = cursor.getString(3);
//                    idGoods = cursor.getString(4);
//                    nameGoods = cursor.getString(5);
////                    pointOfMeeting = cursor.getString(6);
//                    timeOfMeeting = cursor.getString(7);
//                    progress = cursor.getString(8);
////                    dataReg = cursor.getString(9);
////                    dateArrival = cursor.getString(10);
//
//                    if (idGoods == null && idGoods.length() > 1000) {idGoods = "id";}
//                    if (nameGoods == null&& idGoods.length() > 30) {nameGoods = "name";}
//                    if (timeOfMeeting == null&& idGoods.length() > 20) {timeOfMeeting = "time";}
//                    if (progress == null&& idGoods.length() > 30) {progress = "progress";}
//
//                    order = idGoods+" "+nameGoods+" "+timeOfMeeting+" "+progress;
//                    setOrder(id_shop, name, order, myBasket_ll);
//                    cursor.moveToNext();
//                }
//            }
//        } else {
//            Toast toast = Toast.makeText(this, "OH SHIT.. are you DON'T have ORDERS?", Toast.LENGTH_LONG);
//            toast.show();
//        }
        /*
        * HANDLERS
        * */
        second_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                second_btn.setText("don't touch");
            }
        });
        all_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all_btn.setText("will be");
            }
        });
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopBasket.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void setOrder (final String idGoods_shop, final String name, final String order, LinearLayout ll) {
        final Button msgBtn = new Button(this);
        msgBtn.setText(order);
        msgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ShopBasket.this, Buy_ProductStatus.class);
//                intent.putExtra("id_goods_shop", idGoods_shop);
//                intent.putExtra("name_goods", name);
//                startActivity(intent);
            }
        });
        ll.addView(msgBtn, lParams);
    }
}