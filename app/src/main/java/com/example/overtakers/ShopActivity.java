package com.example.overtakers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShopActivity extends Activity {
    private static final String TAG = "CATEGORY";
    // obj
//    Button home_btn;
//    LinearLayout productList_ll, choseCategory_ll;
//    LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
//    DBHelper dbHelper;
    // tmp fields
    private String id_shop, name, price, about;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_activity);

//        home_btn = findViewById(R.id.home_btn);
//        productList_ll = findViewById(R.id.productList_ll);
//        choseCategory_ll = findViewById(R.id.choseCategory_ll);

        Button newsBtn = findViewById(R.id.newsBtn);
        Button marketBtn = findViewById(R.id.marketBtn);
        Button chatBtn = findViewById(R.id.chatBtn);

//        dbHelper = new DBHelper(this);

        // swap
//        Cursor cursor = dbHelper.getFull();
//        if (cursor.getCount() > 0) {
//            cursor.moveToFirst();
//            while (!cursor.isAfterLast()) {
//                id_shop = cursor.getString(1); // on remote db, but not on local db
//                name = cursor.getString(2);
//                price = cursor.getString(3);
//                about = cursor.getString(4);
//
//                setProductList(id_shop, name, price, about, productList_ll);
//                cursor.moveToNext();
//            }
//        } else { showMyToast("opa..nezhdali.: table is empty"); }

        //setup
//        setNewSortBtn("best_rating", "will be in future", choseCategory_ll);
//        setNewSortBtn("best_price", "will be in future", choseCategory_ll);
//        setNewSortBtn("best_quality", "will be in future", choseCategory_ll);
//        setNewSortBtn("just_amazing", "will be in future", choseCategory_ll);

        newsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(ShopActivity.this, NewsActivity.class);
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
                    Intent intent = new Intent(ShopActivity.this, ChatActivity.class);
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
                    Intent intent = new Intent(ShopActivity.this, ShopActivity.class);
    //                news.putExtra("key", value); //Optional parameters
                    startActivity(intent);
                } catch (Exception e) {
                    Log.d("over", String.valueOf(e));
                }
            }
        });
    }
//    private void setNewSortBtn (String nameBtn, String toastMsg, LinearLayout spaceOfLinearLayout) {
//        final String needToastMsg = toastMsg;
//        final Button btn = new Button(this);
//        btn.setText(nameBtn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // sort
//                showMyToast(needToastMsg);
//            }
//        });
//        spaceOfLinearLayout.addView(btn, this.lParams);
//    }
//    @SuppressLint("SetTextI18n")
//    private void setProductList (final String id_shop, final String nameGoods, final String priceGoods, final String aboutGoods, LinearLayout ll) {
//        final Button btn = new Button(this);
//        btn.setText(id_shop +" "+nameGoods+" "+priceGoods + "$"); // in future add "about"
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ShopActivity.this, ShopAbout.class);
//                intent.putExtra("id_goods", id_shop);
//                startActivity(intent);
//            }
//        });
//        ll.addView(btn, this.lParams);
//    }
    public void showMyToast (String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }
}
