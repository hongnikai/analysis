package com.lc.util;

import java.math.BigDecimal;
/**
 *  @描述：String转成Double包装类
 *    description：
 ** @author LC
 */
@SuppressWarnings("all")
public class StringUtil {


    /*
     * String 转换成Double类型
     * */
    public static Double exchangeStringIntoDouble(String data){
        return Double.parseDouble(data);
    }

    /*
    * DOUBLE 转换成String类型
    * */
    public static BigDecimal exchangeDoubleIntoString(Double data){
        BigDecimal bd = new BigDecimal(data);
        return bd.setScale(2, 0);
    }




    public static void main(String[] args) {

//        System.out.println(StringUtil.exchangeDoubleIntoString(1.22));
        BigDecimal bd = new BigDecimal(15.15555);
        System.out.println(bd.setScale(2,0));
        System.out.println(String.valueOf(bd.setScale(2,0)));

    }

}
