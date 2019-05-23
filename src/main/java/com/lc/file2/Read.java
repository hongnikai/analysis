package com.lc.file2;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class Read {
    String taskInformation=null;
    Map<String,String> map =new HashMap<String, String>();

    public String readFile(List<String> list){

        for (int i=0;i<list.size();i++){

            String aaa=list.get(i);
            if (aaa.contains("TaskInformation")){
                taskInformation=ReadTXT.txt2String(aaa).split("#")[1];

            }else if(aaa.contains("DistributionTransformer")){
                 ReadTXT.txt2String(aaa);
            }else if (aaa.contains("Maintransformer")){
                 ReadTXT.txt2String(aaa);
            }else if(aaa.contains("Line")){
                 ReadTXT.txt2String(aaa);
            }

        }
        return "";

    }


}
