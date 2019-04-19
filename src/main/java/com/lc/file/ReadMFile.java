package com.lc.file;

import com.lc.util.CompareMap;
import com.sgcc.pias.PisHbaseImpl;
import org.junit.Test;

import java.io.*;
import java.util.*;

@SuppressWarnings("all")
public class ReadMFile {

//    String markDay = "201810300";
    String markDay = null;
    String middleDay = null;
    int mark=0;
    Map<String,Map<String,String>> join_map =new HashMap<String, Map<String,String>>();

    /**
     ** @author LC
     * taskInformation t标签内容
     * path         解析生成M文件路径
     * taskId       taskId
     */
    public void readMFile(String taskInformation,String path,String taskId) throws IOException {

        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
        String early_deviceId = null;
        String s = null;

        StringBuffer sbuffer= new StringBuffer();
        while((s = br.readLine())!=null) {
            mark++;
            if(s.contains("deviceId")){
                //清空第一行
            }else{
                //判断是否为同一天数据，将同一天数据放在一起 传入Habase

                //切割  得到了dataDate 数据，例：20181030000000
                String dataDate= s.split("#")[1].split(",")[9];
                String[] a=dataDate.split("");
                //继续切割取前八位，比较是否为同一天
                String day=a[0]+a[1]+a[2]+a[3]+a[4]+a[5]+a[6]+a[7];
                String deviceId = s.split("#")[1].split(",")[0];
                if (mark==2){
                    //赋了初始值
                    markDay=day;
                    early_deviceId=deviceId;
                    sbuffer.append(s+"\n");
                }else if (early_deviceId.equals(deviceId)&&markDay.equals(day)){
                    //如果数据都是同一天的,放在一起
                    sbuffer.append(s+"\n");
                }else{
                    //两条数据不是同一天的，将数据进行计算
                    //拆分计算
                    compute(taskInformation,sbuffer,taskId);
                    //sbuffer就得存入Habase
                    markDay=day;
                    sbuffer.setLength(0);
                    sbuffer.append(s+"\n");
                }
            }
            if(mark>100){
                mark=100;
            }
            if(join_map.size()>1000){
                PisHbaseImpl pisHbase = new PisHbaseImpl();
                pisHbase.batchSaveData("xsgl:ELP_LD_MET_LOAD_INDEX_MT","info",join_map);
                join_map.clear();
            }
        }
        br.close();
        compute(taskInformation,sbuffer,taskId);
        PisHbaseImpl pisHbase = new PisHbaseImpl();
        pisHbase.batchSaveData("xsgl:ELP_LD_MET_LOAD_INDEX_MT","info",join_map);

    }

    //每次获得一天的数据量，再拆分计算
    public void compute(String taskInformation,StringBuffer sbuffer,String taskId){

//        System.out.println(sbuffer.toString());
        String str=sbuffer.toString();
        List<String> list = new ArrayList<String>();
        Map<String,String> map =new HashMap<String, String>();
        String measId=null;
        String rday =null;
        String ROW_KEY=null;
        for (int i=1;i<str.split("#").length;i++){
//取得每条数据不带#符号
//获取每条数据 去除 "," 号
                measId= str.split("#")[i].split(",")[6];
                rday = str.split("#")[i].split(",")[9];
                ROW_KEY = measId+rday.split("")[0]
                        +rday.split("")[1]
                        +rday.split("")[2]
                        +rday.split("")[3]
                        +rday.split("")[4]
                        +rday.split("")[5]
                        +rday.split("")[6]
                        +rday.split("")[7];

                int a =SwithVi.getMVi(rday);
                map.put("v"+a,str.split("#")[i].split(",")[10]);
                list.add(str.split("#")[i].split(",")[10]);
//            System.out.println(str.split("#")[i].split(",")[10]);

        }

        //从Vi中选取最大的
        String max_value = CompareMap.compareMapMaxValue(list);
        //从Vi中选取最小的
        String min_value = CompareMap.compareMapMinValue(list);
        //从Vi中计算得到平均值
        String avg_value=CompareMap.compareMapAvageValue(list);

        CompareMap compare = new CompareMap();

        String max_point = compare.getMapMaxKey(map);
        String min_point = compare.getMapMinKey(map);

        map.put("ROW_KEY",ROW_KEY);
        map.put("BATCH_ID",taskId);
        map.put("PID",measId);
        map.put("RDAY",rday);
        map.put("MAXVALUE",max_value);
        map.put("MINVALUE",min_value);
        map.put("AVGVALUE",avg_value);
        map.put("MAX_POINT",max_point);
        map.put("MIN_POINT",min_point);


//        taskInformation   //截取字符串
        map.put("UPDATE_TIME",taskInformation.split("#")[1].split(",")[3].substring(0,4)+"-"+
                taskInformation.split("#")[1].split(",")[3].substring(4,6)+"-"+
                taskInformation.split("#")[1].split(",")[3].substring(6,8)+" "+
                taskInformation.split("#")[1].split(",")[3].substring(8,10)+":"+
                taskInformation.split("#")[1].split(",")[3].substring(10,12)+":"+
                taskInformation.split("#")[1].split(",")[3].substring(12,14));
        map.put("SG_CODE","");
        join_map.put(map.get("ROW_KEY"),map);
    }

    public static void main(String[] args) throws IOException {

        ReadMFile readMFile = new ReadMFile();

      String taskInformation="@taskId,type,planTime,generDate\n" +
                "#4028c42d66b357fe0166b38ae7e90016,0,20181027201112,20181027201112";
        readMFile.readMFile(taskInformation,"E:\\20181205.txt","4028c42d66b357fe0166b38ae7e90016");




    }





}
