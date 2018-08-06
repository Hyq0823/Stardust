package com.other.intf;

public class IntfMain{

    public static void main(String[] args) {
        People p1 = new People("小明",12,100);
        People p2 = new People("小王",18,100);
        People p3 = new People("小丽",22,100);


        //网吧--开机
        InternetBar internetBar = new InternetBar();
        internetBar.take(p1);

    }

}
