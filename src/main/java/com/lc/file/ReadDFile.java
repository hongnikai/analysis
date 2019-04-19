package com.lc.file;

import com.lc.util.CompareMap;
import com.sgcc.pias.PisHbaseImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  @描述：读取D文件 并解析入Hbase
 *    description：读取固定路径下的D文件
 ** @author LC
 *  创建时间：2018-12-07 下午16：49
 */
@SuppressWarnings("all")
public class ReadDFile {
    String markDay = null;
    String middleDay = null;
    int mark=0;
    Map<String,Map<String,String>> join_map =new HashMap<String, Map<String,String>>();
    PisHbaseImpl pisHbase = new PisHbaseImpl();



    public void readDFile(String taskInformation,String path,String taskId) throws IOException {

        File file = new File(path);
        String early_deviceId = null;
        BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
        String s = null;
        StringBuffer sbuffer= new StringBuffer();

        while((s = br.readLine())!=null) {
            mark++;
            if(s.contains("deviceId")){
                //清空第一行
            }else if(s.split("#")[1].split(",")[7].equals("IA")){
                //清空 DT_LOAD_TYPE  为 IA这一行

            }else{
               //判断是否为同一天数据，将同一天数据放在一起 传入Habase
                //切割  得到了dataDate 数据，例：20181030000000
                String dataDate= s.split("#")[1].split(",")[8];
                String[] a=dataDate.split("");
                //继续切割取前八位，比较是否为同一天
                String day=a[0]+a[1]+a[2]+a[3]+a[4]+a[5]+a[6]+a[7];
                String deviceId = s.split("#")[1].split(",")[0];
                if (mark==2){
                    //赋了初始值
                    markDay=day;
                    //判断是否是同一台设备
                    early_deviceId=deviceId;
                    sbuffer.append(s+"\n");
                }else if(early_deviceId.equals(deviceId)&&markDay.equals(day)){
                    //判断是不是同一个设备的 //判断是不是同一天得数据
                        //如果数据都是同一天的,放在一起
                        sbuffer.append(s+"\n");
                }else{
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
                pisHbase.batchSaveData("xsgl:ELP_LD_DIST_TRANS_MET_LOAD_V","info",join_map);
                join_map.clear();
            }
        }
        br.close();
        compute(taskInformation,sbuffer,taskId);
        pisHbase.batchSaveData("xsgl:ELP_LD_DIST_TRANS_MET_LOAD_V","info",join_map);
    }

        public void compute(String taskInformation,StringBuffer sbuffer,String taskId){

        String str=sbuffer.toString();
        List<String> list = new ArrayList<String>();
        Map<String,String> map =new HashMap<String, String>();
        Map<String,String> map2=new HashMap<String, String>();
        String equip_id = null;
        String SG_CODE= null;
        String rday =null;
        String ROW_KEY=null;
        String dt_load_type=null;
        for (int i=1;i<str.split("#").length;i++){
//取得每条数据不带#符号
//获取每条数据 去除 "," 号
            equip_id=str.split("#")[i].split(",")[0];
            SG_CODE=str.split("#")[i].split(",")[1];
            rday =str.split("#")[i].split(",")[8].substring(0,8);
            dt_load_type=SwithVi.getDtLoadType(str.split("#")[i].split(",")[7]);
                map.put("V"+SwithVi.getVi(str.split("#")[i].split(",")[8].substring(8,12)),str.split("#")[i].split(",")[9]);
                map.put("ROW_KEY",equip_id+rday+dt_load_type);
                map.put("BATCH_ID",equip_id);
                map.put("EQUIP_ID",equip_id);
                map.put("RDAY",rday);
                map.put("DT_LOAD_TYPE",dt_load_type);
                map.put("SG_CODE",SG_CODE);
                map2.put("V"+SwithVi.getVi(str.split("#")[i].split(",")[8].substring(8,12)),
                        str.split("#")[i].split(",")[8]);
        }

//        map.put("PT_RATIO","");
//        map.put("CT_RATIO","");
//        map.put("DATA_POINT","96");
//        map.put("SG_CODE","");
//        map.put("MAXVALUE",CompareMap.compareMapVMax2(map));
//        map.put("MINVALUE",CompareMap.compareMapVMin2(map));
//        map.put("AVGVALUE",CompareMap.compareMapVAverage2(map));
          map.put("MAX_POINT",map2.get(CompareMap.getMapMax2(map)));
          map.put("MIN_POINT",map2.get(CompareMap.getMapMin2(map)));

          //取t 标签里面内容 #4028c42d66b357fe0166b38ae7e90016,0,20181027201112,20181027201112
          map.put("UPDATE_TIME",
                          taskInformation.split("#")[1].split(",")[3].substring(0,4)+"-"+
                          taskInformation.split("#")[1].split(",")[3].substring(4,6)+"-"+
                          taskInformation.split("#")[1].split(",")[3].substring(6,8)+" "+
                          taskInformation.split("#")[1].split(",")[3].substring(8,10)+":"+
                          taskInformation.split("#")[1].split(",")[3].substring(10,12)+":"+
                          taskInformation.split("#")[1].split(",")[3].substring(12,14));

          join_map.put(equip_id+rday+dt_load_type,map);


    }

    public static void main(String[] args) throws IOException {
               ReadDFile readDFile =  new ReadDFile();
        readDFile.readDFile("#4028c42d66b357fe0166b38ae7e90016,0,20181027201112,20181027201112","D://asdasd/12/DistributionTransformer20181207074324.txt","4028c42d66b357fe0166b38ae7e90016");


    }



}




