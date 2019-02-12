package com.json;

import java.text.DecimalFormat;

/**
 * @Author: huangyunquan
 * @Description:Created on 2019/1/8 15:30.
 * 保留两位小数
 */
public class TextDemo {
    public static void main(String[] args) {
        Double d = 0.998D;

        //1.00
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println(df.format(d));

        //1
        DecimalFormat df2 = new DecimalFormat("#.##");
        System.out.println(df2.format(d));

        //1.00
        System.out.println(String.format("%.2f",d));

        //String.format("%.2f")

    }
}
