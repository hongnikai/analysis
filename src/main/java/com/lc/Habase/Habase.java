package com.lc.Habase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 *    description：模拟habase接口
 ** @author LC
 *  创建时间：2018-10-30 下午16：47
 */
public class Habase {

    public static void toHabase(Map<String,String> map){


        System.out.println("上传Habase接口");

        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        System.out.println("上传Habase接口结束");
    }

    public static void toHabase2(){

        Map<String,String> map1 = new HashMap<String,String>();
        map1.put("集合一1","213123");
        map1.put("集合一2","asdasdasd");
        map1.put("集合一3","12312312312323423sdfsdfsdf");
        Map<String,String> map2 = new HashMap<String,String>();
        map2.put("集合二1","psdpp");
        map2.put("集合二2","qqq");
        Map<String,String> map3 = new HashMap<String,String>();
        map3.put("集合三1","wwww");
        map3.put("集合三2","rrr");



        Map<String,Map<String,String>> map =new HashMap<String,Map<String,String>>();
        map.put("1",map1);
        map.put("2",map2);
        map.put("3",map3);

        for (String key:map.keySet()){
//            for (String key2:map1.keySet()){
                System.out.println("key="+key+"and value="+map.get(key));
//            }
//            System.out.println("key="+key+"and value="+map2.get(key));
        }


        System.out.println(map.size());


    }

    public static void main(String[] args) {


        Habase.toHabase2();

    }

}
