package com.lc.ReadTag;


import com.lc.Habase.Habase;
import com.lc.file.SwithVi;
import com.lc.util.CompareMap;

import java.util.HashMap;
import java.util.Map;

/**
 *    description：根据ReadTag 返回的数据进行处理
 ** @author LC
 *  创建时间：2018-10-30 下午16：47
 */
@SuppressWarnings("all")
public class ReadDTag {


    /**
     *    description：整理了<TaskInformation>标签和<DistributionTransformer>标签内容的解析
     ** @author LC
     */
    CompareMap c=new CompareMap();
//    HbasePowerImpl hbasePower = new HbasePowerImpl();
    public Object getMessageFromTag(String taskInformation, String distributionTransformer){

//         taskInformation="@taskId,type,planTime,generDate\n" +
//                                    "#4028c42d66b357fe0166b38ae7e90016,0,20181027201112,20181027201112";
        String new_taskInformation=taskInformation.split("<TaskInformation>")[1]
                .split("</DistributionTransformer>")[0];




//         distributionTransformer="@deviceId,proviceCode,cityCode,tgno,meterid,name,datatype,dataDate,v\n" +
//                "#16M00000013019471,HN,CS,0100192589,2012000010873366,长沙,P,20180092501500,10.5\n" +
//                "#16M00000013019471,HN,CS,0100192589,2012000010873366,长沙,P,20180925003000,11.4\n" +
//                "#16M00000013019472,HN,CS,0100192589,2012000010873366,长沙,Q,20180925001500,11.1\n" +
//                "#16M00000013019472,HN,CS,0100192589,2012000010873366,长沙,Q,20180925003000,11.2\n" +
//                "#16M00000013019473,HN,CS,0100192589,2012000010873366,长沙,IA,20180925001500,11.0\n" +
//                "#16M00000013019473,HN,CS,0100192589,2012000010873366,长沙,IA,20180925003000,11.8\n";





        String[] splitData = distributionTransformer.split("<DistributionTransformer>")[1]
                .split("</DistributionTransformer>")[0]
                .split("#");




        String[] pis_string= distributionTransformer.split("#");  //第一次切割数组
        String[] exchange_string=null;
        Map<String,String> map=new HashMap<String, String>();
        int i=0;
        int record=1;

        for (i=record;i<splitData.length;i++){

            if (exchange_string!=null){
                String[] compare_str = this.setInString(splitData[i]);//先中转值在做比较
                this.setInStringAndSetMap(splitData[i]);
                if (exchange_string[0].equals(compare_str[0])){
                    //exchange_string上一条跟compare_str下一条比较,如果相等的条件下
                    map.put("v"+SwithVi.getVi(compare_str[7]),compare_str[8]);

                    if(i+1==splitData.length){
                        map.put("MAXVALUE",CompareMap.compareMapVMax(map,compare_str[5]));
                        map.put("MINVALUE",CompareMap.compareMapVMin(map,compare_str[5]));
                        map.put("AVGVALUE",CompareMap.compareMapVAverage(map,compare_str[5]));
                        map.put("MAX_POINT",c.getMapMax(c.getV()));
                        map.put("MIN_POINT",c.getMapMin(c.getV()));
                        map.put("SG_CODE","");

//                        hbasePower.toHbase("xsgl:ELP_LD_DIST_TRANS_MET_LOAD_V",map.get("ROW_KEY"),"info",map);
                            Habase.toHabase(map);
                    }

                }else{

                    map.put("MAXVALUE",CompareMap.compareMapVMax(map,compare_str[5]));
                    map.put("MINVALUE",CompareMap.compareMapVMin(map,compare_str[5]));
                    map.put("AVGVALUE",CompareMap.compareMapVAverage(map,compare_str[5]));
                    map.put("MAX_POINT",c.getMapMax(c.getV()));
                    map.put("MIN_POINT",c.getMapMin(c.getV()));
                    map.put("SG_CODE","");

 //                   hbasePower.toHbase("xsgl:ELP_LD_DIST_TRANS_MET_LOAD_V",map.get("ROW_KEY"),"info",map);
                    Habase.toHabase(map); //调用habase接口
                    map.clear();
                    //新的一条
                    //记录i循环到第几层
                    record=i;

                    map.put("ROW_KEY",compare_str[0]+compare_str[4].substring(0,8)+SwithVi.getDtLoadType(compare_str[6]));
                    map.put("BATCH_ID",new_taskInformation.split("#")[1].split(",")[3].substring(0,8));
                    map.put("EQUIP_ID",compare_str[0]);
                    map.put("RDAY",compare_str[4].substring(0,8));
                    map.put("DT_LOAD_TYPE",SwithVi.getDtLoadType(compare_str[6]));
                    map.put("PT_RATIO","");
                    map.put("CT_RATIO","");
                    map.put("UPDATE_TIME",new_taskInformation.split("#")[1].split(",")[3].substring(8,10)+":"+
                            new_taskInformation.split("#")[1].split(",")[3].substring(10,12)+":"+
                            new_taskInformation.split("#")[1].split(",")[3].substring(12,14));
                    map.put("v"+SwithVi.getVi(compare_str[7]),compare_str[8]);
                    //从taskInformation 标签里取值

                    if(i+1==splitData.length){
                        map.put("MAXVALUE",CompareMap.compareMapVMax(map,compare_str[5]));
                        map.put("MINVALUE",CompareMap.compareMapVMin(map,compare_str[5]));
                        map.put("AVGVALUE",CompareMap.compareMapVAverage(map,compare_str[5]));
                        map.put("MAX_POINT",c.getMapMax(c.getV()));
                        map.put("MIN_POINT",c.getMapMin(c.getV()));
                        map.put("SG_CODE","");
//                        hbasePower.toHbase("xsgl:ELP_LD_DIST_TRANS_MET_LOAD_V",map.get("ROW_KEY"),"info",map);
                        Habase.toHabase(map);
                    }

                }
            }else{

                this.setInStringAndSetMap(splitData[i]);
                System.out.println(splitData[i]);
                String[] secondSplit = this.setInString(splitData[i]);  //第二次切割数组

//          System.out.println("ROW_KEY"+secondSplit[0]+secondSplit[4].substring(0,8)+SwithVi.getDtLoadType(secondSplit[6]));
                map.put("ROW_KEY",secondSplit[0]+secondSplit[4].substring(0,8)+SwithVi.getDtLoadType(secondSplit[6]));
                map.put("BATCH_ID",new_taskInformation.split("#")[1].split(",")[3].substring(0,8));
                map.put("EQUIP_ID",secondSplit[0]);
                map.put("RDAY",secondSplit[4].substring(0,8));
                map.put("DT_LOAD_TYPE",SwithVi.getDtLoadType(secondSplit[6]));
                map.put("PT_RATIO","");
                map.put("CT_RATIO","");
                map.put("UPDATE_TIME",new_taskInformation.split("#")[1].split(",")[3].substring(8,10)+":"+
                        new_taskInformation.split("#")[1].split(",")[3].substring(10,12)+":"+
                        new_taskInformation.split("#")[1].split(",")[3].substring(12,14));


                map.put("v"+SwithVi.getVi(secondSplit[7]),secondSplit[8]);

            }


            exchange_string=this.setInString(splitData[i]);//作为上一条
            this.setInStringAndSetMap(splitData[i]);

        }

        return "调取结束";
    }

    public String[] setInString(String data){

        String[] str= data.split(",");
        CompareMap c=new CompareMap();
        c.setV(str[7],str[8]);
        return  str;
    }

    public Map<String,String> setInStringAndSetMap(String data){
        String[] str= data.split(",");
        return c.setV(str[7],str[8]);
    }






    public static void main(String[] args) {

//        ReadToHbase read =new ReadToHbase();

        ReadDTag readDTag=new ReadDTag();

        readDTag.getMessageFromTag("<TaskInformation>\n" +
                        "@taskId,type,planTime,generDate\n" +
                        "#4028c42d66b357fe0166b38ae7e90016,0,20181027201112,20181027201112\n" +
                        "</TaskInformation>"
                ,

                "<DistributionTransformer>\n" +
                "@deviceId,proviceCode,cityCode,tgno,meterid,name,datatype,dataDate,v\n" +
                "#16M00000013019471,HN,CS,0100192589,2012000010873366,长沙,P,20180925001500,10.5\n" +
                "#16M00000013019471,HN,CS,0100192589,2012000010873366,长沙,P,20180925003000,11.4\n" +
                "#16M00000013019472,HN,CS,0100192589,2012000010873366,长沙,Q,20180925001500,11.1\n" +
                "#16M00000013019472,HN,CS,0100192589,2012000010873366,长沙,Q,20180925003000,11.2\n" +
                "#16M00000013019473,HN,CS,0100192589,2012000010873366,长沙,IA,20180925001500,11.0\n" +
                "#16M00000013019473,HN,CS,0100192589,2012000010873366,长沙,IA,20180925003000,11.8\n" +
                "\n" +
                "\n" +
                "</DistributionTransformer>");

    }





}
