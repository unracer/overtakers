package com.example.overtakers;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShopAbout extends Activity {
    private String TAG = "PRODUCT_ABOUT";
    Button buy_btn;
    TextView name_Goods_tV, price_Goods_tV, about_Tv;
    DBHelper dbHelper;
    private String id_shop, name_goods, price, about, idGoodsShopFromIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_about);
        Log.i(TAG, "start activity");

//        buy_btn = findViewById(R.id.buy_btn);
//        name_Goods_tV = findViewById(R.id.nameGoods_Tv);
//        price_Goods_tV = findViewById(R.id.price_Tv);
//        about_Tv = findViewById(R.id.about_Tv);

        dbHelper = new DBHelper(this);

        id_shop = getIntent().getStringExtra("id_goods");
        name_goods = getIntent().getStringExtra("name_goods");

        Cursor cursor = null;
        if (id_shop != null) { // load from local BD
//            cursor = dbHelper.getRow(id_shop);
        } else showMyToast("intent == null");

        if (cursor.getCount() > 0 && cursor != null) {
            cursor.moveToFirst();
            id_shop = cursor.getString(1);
            name_goods = cursor.getString(2);
            price = cursor.getString(3);
            about = cursor.getString(4);

            if (!name_goods.equals(" ") && name_goods.length() < 20) {
                name_Goods_tV.setText(name_goods);
            } else name_Goods_tV.setText("nope");
            if (!price.equals(" ") && price.length() < 20) {
                price_Goods_tV.setText(price);
            } else price_Goods_tV.setText("nope");
            if (!about.equals(" ") && about.length() < 700) {
                about_Tv.setText(about);
            } else about_Tv.setText("nope");
        } else {
            showMyToast("opa..pizdec..: NOPE \"about\" product in the db");
        }
        /*
        * HANDLERS
        * */
        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ShopAbout.this, Buy_OrderRegistration.class);
//                intent.putExtra("id_goods", id_shop);
//                intent.putExtra("name_goods", name_goods);
//                startActivity(intent);
            }
        });
    }
    private void showMyToast(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }
}
