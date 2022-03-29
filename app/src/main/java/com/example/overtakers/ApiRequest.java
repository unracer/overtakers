package com.example.overtakers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {

    // abstracted
//    @GET("mobile.php")
//    Call<Map<String, String>> get(
//            @QueryMap Map<String, String>
//    );

    //CHAT
    @GET("overtakeShop.php")
    Call<List<CHAT>> msgGet(
            @Query("func") String func,
            @Query("id") String id
    );
    @GET("overtakeShop.php")
    Call<List<CHAT>> msgCreate(
            @Query("func") String func,
            @Query("time") String time,
            @Query("nickname") String nickname,
            @Query("msg") String msg
    );

    //GOODS
    @GET("overtakeShop.php")
    Call<List<GOODS>> goodGet(
            @Query("func") String func
    );

    //USERS
    // if user is exist - return id_acc
    // else create and return
    @GET("overtakeShop.php")
    Call<List<USERS>> userGet(
            @Query("func") String func,
            @Query("nickname") String nickname,
            @Query("pass") String pass
    );

    //ORDERS
    @GET("overtakeShop.php")  // получение заказов
    Call<List<ORDERS>> orderGet(
            @Query("func") String func,
            @Query("id_acc") String id_acc
    );
    //@FormUrlEncoded
    @GET("overtakeShop.php")
    Call<Void> orderCreate(
            @Query("func") String func,
            @Query("id_acc") String id_acc,
            @Field("nickname") String nickname,
            @Field("id_goods") String id_goods,
            @Field("name_goods") String name_goods,
            @Field("point_meeting") String point_of_meeting,
            @Field("time_meeting") String time_of_meeting,
            @Field("progress") String progress,
            @Field("date_reg") String date_reg,
            @Field("date_arrival") String date_arrival
    );

    @GET("overtakeShop.php")
    Call<List<USERSDATA>> usersDataGet(
            @Query("func") String func
    );
}

/*
* $func = $_GET["func"];

	switch ($func){
		case "goodCreate":
			goodCreate();
			break;
		case "goodGet":
			goodGet();
			break;
		case "msgCreate":
			msgCreate();
			break;
		case "msgGet":
			msgGet();
			break;
		case "orderCreate":
			orderCreate();
			break;
		case "orderGet":
			orderGet();
			break;
		case "userCreate":
			userCreate();
			break;
		case "userGet":
			userGet();
			break;
		case "userUpdate":
			userUpdate();
			break;
		case "serviceGet":
			serviceGet();
			break;
	}
	* */



/**
 * reuse to @GET("group/{id}/users")
 * Call<List<User>> groupList(@Path("id") int groupId, @QueryMap Map<String, String> options);
 *
 *
 * */