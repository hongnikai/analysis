package com.lc.util;

import com.lc.file.SwithVi;

import java.text.DecimalFormat;
import java.util.*;
/**
 *    description：读取map的vi key的值
 ** @author LC
 *  创建时间：2018-10-30 下午16：47
 */
@SuppressWarnings("all")
public class CompareMap {



    public static String compareMapVMax2(Map<String,String> map){
        String middle=null;
        int i;
        int j;
        List<String> list = new ArrayList<String>();

        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();

            if(entry.getKey().contains("V")){
//          System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                list.add(entry.getValue());
            }
        }

        //对集合进行排序  从小到大
        Collections.sort(list);
        middle = list.get(list.size()-1);
        //最大值与倍率做乘法

        return middle;
    }



    /**
     *    description：读取key 为v系列的value最大值
     ** @author LC
     *  创建时间：2018-10-30 下午16：47
     */
    public static String compareMapVMax(Map<String,String> map,String data){
        String middle=null;
        int i;
        int j;
        List<String> list = new ArrayList<String>();

        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();

            if(entry.getKey().contains("v")){
 //               System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                list.add(entry.getValue());
            }
        }

        //对集合进行排序  从小到大
        Collections.sort(list);
        middle = list.get(list.size()-1);
        //最大值与倍率做乘法

        return   String.valueOf(
                StringUtil.exchangeDoubleIntoString(
                        StringUtil.exchangeStringIntoDouble(middle)*StringUtil.exchangeStringIntoDouble(data)
                                                    )
                                );
    }


    public static String compareMapVMin2(Map<String,String> map){
        String middle=null;
        List<String> list = new ArrayList<String>();

        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();

            if(entry.getKey().contains("V")){
                //               System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                list.add(entry.getValue());
            }
        }
        //对集合进行排序  从小到大
        Collections.sort(list);
        middle = list.get(0);
        //最小值与倍率做乘法
        return middle;
    }

    public static String compareMapVAverage2(Map<String,String> map){

        List<String> list = new ArrayList<String>();

        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();

            if(entry.getKey().contains("V")){
                list.add(entry.getValue());
            }
        }
        double d=0;
        for(int i=0;i<list.size();i++){

            d += Double.parseDouble(list.get(i));

        }

        DecimalFormat df = new DecimalFormat("#.00");
        String str = df.format(d/list.size());

        return str;
    }



    /**
     *    description：读取key 为v系列的value最小值
     ** @author LC
     *  创建时间：2018-10-30 下午16：47
     */
    public static String compareMapVMin(Map<String,String> map,String data){
        String middle=null;
        List<String> list = new ArrayList<String>();

        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();

            if(entry.getKey().contains("v")){
                //               System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                list.add(entry.getValue());
            }
        }
        //对集合进行排序  从小到大
        Collections.sort(list);
        middle = list.get(0);
        //最小值与倍率做乘法

        return  String.valueOf(
                StringUtil.exchangeDoubleIntoString(
                        StringUtil.exchangeStringIntoDouble(middle)*StringUtil.exchangeStringIntoDouble(data)
                                                    )
                                );

    }


    /**
     *    description：读取key 为v系列的value平均值 Average   结果保留两位小数
     ** @author LC
     *  创建时间：2018-10-30 下午16：47
     */
    public static String compareMapVAverage(Map<String,String> map,String data){
        List<String> list = new ArrayList<String>();

        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();

            if(entry.getKey().contains("v")){
                list.add(entry.getValue());
            }
        }
        double d=0;
        for(int i=0;i<list.size();i++){

            d += Double.parseDouble(list.get(i));

        }

        DecimalFormat df = new DecimalFormat("#.00");
        String str = df.format(d/list.size());

        return  String.valueOf(
                StringUtil.exchangeDoubleIntoString(
                        StringUtil.exchangeStringIntoDouble(str)*StringUtil.exchangeStringIntoDouble(data)
                                                     )
                                );
    }


    Map<String,String> middle= new HashMap<String, String>();
    public  Map<String, String> setV(String time,String str){
        middle.put(time,str);
        return middle;
    }

    public Map<String, String> getV(){
        return middle;
    }

    public static String getMapMax2(Map<String,String> map){
        String middle=null;
        int i;
        int j;
        List<String> list = new ArrayList<String>();

        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();

            if(entry.getKey().contains("V")){
//          System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                list.add(entry.getValue());
            }
        }
        //对集合进行排序  从小到大
        Collections.sort(list);
        middle = list.get(list.size()-1);
        String key="";

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if(middle.equals(entry.getValue())){
                key=entry.getKey();
            }
        }
//        System.out.println(key);
        return key;
    }


    /**
     *    description：获取map的最大值的对应key
     ** @author LC
     *  创建时间：2018-10-30 下午19：47
     */
    public String getMapMax(Map<String,String> map){

        int i=0;
        int j=0;
        String middle=null;

        List<String> list = new ArrayList<String>();

        Iterator<Map.Entry<String, String>> entries = this.getV().entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            list.add(entry.getValue());
        }
        //对集合进行排序  从小到大
        Collections.sort(list);
        middle = list.get(list.size()-1);

        String key="";

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if(middle.equals(entry.getValue())){
                key=entry.getKey();
            }
        }

        return key;
    }

    public static String getMapMin2(Map<String,String> map){
        String middle=null;
        int i;
        int j;
        List<String> list = new ArrayList<String>();

        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();

            if(entry.getKey().contains("V")){
//          System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                list.add(entry.getValue());
            }
        }
        //对集合进行排序  从小到大
        Collections.sort(list);
        middle = list.get(0);
        String key="";

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if(middle.equals(entry.getValue())){
                key=entry.getKey();
            }
        }
//        System.out.println(key);
        return key;
    }


    /**
     *    description：获取map的最小值的对应key
     ** @author LC
     *  创建时间：2018-10-30 下午19：47
     */
    public String getMapMin(Map<String,String> map){

        int i=0;
        int j=0;
        String middle=null;

        List<String> list = new ArrayList<String>();

        Iterator<Map.Entry<String, String>> entries = this.getV().entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            list.add(entry.getValue());
        }
        //对集合进行排序  从小到大
        Collections.sort(list);
        middle = list.get(0);

        String key="";

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if(middle.equals(entry.getValue())){
                key=entry.getKey();
            }
        }

        return key;
    }


    /**
     *  description：获取map的最小值的对应key
     ** @author LC
     *  传入      Vi map  类型
     *  return    最大值value
     *
     *
     *  创建时间：2018-12-06 上午11：10
     */
    public static String compareMapMaxValue(List<String> list){
        Collections.sort(list);
        return list.get(list.size()-1);
    }

    /**
     *  description：获取list的最小值
     ** @author LC
     *  return    最小值value
     *  创建时间：2018-12-06 上午11：10
     */
    public static String compareMapMinValue(List<String> list){
        Collections.sort(list);
        return list.get(0);
    }

    /**
     *  description：获取list的平均值
     ** @author LC
     *  return    平均值value
     *  创建时间：2018-12-06 上午11：10
     */
    public static String compareMapAvageValue(List<String> list){

        Double dd =0.00;
        for (int i=0;i<list.size();i++){
              dd+=StringUtil.exchangeStringIntoDouble(list.get(i));
        }
            Double a=dd/list.size();
        return a.toString();
    }

    /**
     *  description：获取Map的最大值对应的key
     ** @author LC
     *  return    最小值value
     *  创建时间：2018-12-06 上午11：10
     */
    public String getMapMaxKey(Map<String,String> map){

        List<String> list = new ArrayList<String>();

        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            list.add(entry.getValue());
        }

        //集合从大到小排列
        Collections.sort(list);
        //返回最后一个 即为最大值
        String max_val =list.get(list.size()-1);

        String key="";

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if(max_val.equals(entry.getValue())){
                key=entry.getKey();
            }
        }
        return key;
    }

    /**
     *  description：获取Map的最小值对应的key
     ** @author LC
     *  return    最小值value
     *  创建时间：2018-12-06 上午11：10
     */
    public String getMapMinKey(Map<String,String> map){

        List<String> list = new ArrayList<String>();

        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            list.add(entry.getValue());
        }
        //集合从大到小排列
        Collections.sort(list);
        //返回最后一个 即为最大值
        String max_val =list.get(0);

        String key="";

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if(max_val.equals(entry.getValue())){
                key=entry.getKey();
            }
        }
        return key;
    }





    public static void main(String[] args) {

        Map<String,String> map =new HashMap<String, String>();


    }

}
