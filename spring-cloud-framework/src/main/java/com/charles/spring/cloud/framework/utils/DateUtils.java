package com.charles.spring.cloud.framework.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: st-wgsf-zengs
 * @time: 12/4/2019 9:24 AM
 */
public abstract class DateUtils {


    public static final String DEFAULT_DATE_FOMMAT = "yyyy-MM-dd HH:mm:ss";


    public static String getNow() {
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FOMMAT);
        return formatter.format(new Date());
    }

    public static void main(String[] args) {
        System.out.println(getNow());
        System.out.println(ReturnMessage.fail("失败"));
    }

}
