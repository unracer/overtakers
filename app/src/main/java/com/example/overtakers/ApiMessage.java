package com.example.overtakers;

class CHAT {
    private long id;
    private String time;
    private String nickname;
    private String msg;

    public long getId () { return this.id; }
    public String getTime () { return this.time; }
    public String getNickname () { return this.nickname; }
    public String getMsg () { return this.msg; }
}

class GOODS {
    private long id_shop;
    private String title;
    private String price;
    private String about;

    public long getIdShop () { return this.id_shop; }
    public String getTitle () { return this.title; }
    public String getPrice () { return this.price; }
    public String getAbout () { return this.about; }
}

class USERS {
    private int id_acc;
    private String nickname;
    private String pass;

    public int getId() { return id_acc; }
    public String getNickName() { return nickname; }
    public String getPass() { return pass; }
}

class USERSDATA {
    private int id;
    private String param;
    private String value;

    public int getId() { return id; }
    public String getParam() { return param; }
    public String getValue() { return value; }
}

class ORDERS {
    private int id_acc;
    private String nickname;
    private String id_goods;
    private String name_goods;
    private String point_meeting;
    private String time_meeting;
    private String progress;
    private String date_reg;
    private String date_arrival;

    public int getIdAcc() { return id_acc; }
    public String getNickName() { return nickname; }
    public String getIdGood() { return id_goods; }
    public String getNameGood() { return name_goods; }
    public String getPointOfMeeting() { return point_meeting; }
    public String getTimeOfMeeting() { return time_meeting; }
    public String getProgress() { return progress; }
    public String getDataReg() { return date_reg; }
    public String getDateArrival() { return date_arrival; }
}