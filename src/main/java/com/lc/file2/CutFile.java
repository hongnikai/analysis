package com.lc.file2;

import com.lc.file.ReadToHbase;
import com.lc.file.WriteFileLocal;
import com.lc.file.removeFile;
import com.lc.util.TimeUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class CutFile {

    public String taskInformation=null;
    public String distributionTransformer =null;

    List<String> list =new ArrayList<String>();


    ReadToHbase readToHbase=new ReadToHbase();


    public List<String> txtReadAndWrite(File file, String path,String new_path){
        StringBuilder result = new StringBuilder();

        String str=TimeUtil.getSystemTimeFormartNoBlank();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            StringBuffer text =new StringBuffer();

            while((s = br.readLine())!=null){//使用readLine方法，一次读一行

                try{
                    if(s.contains("<TaskInformation>")
                            ||s.contains("<!Entity=")
                            ||s.contains("<Maintransformer>")
                            ||s.contains("<DistributionTransformer>")
                            ||s.contains("<Line>")){
                        text.setLength(0);  //清除数据
                    }else{

                        if (s.contains("</TaskInformation>")){


                            list.add(WriteFileLocal.write("TaskInformation"+str,text.toString(),path));   //写入文件
                            taskInformation=text.toString();
                            text.setLength(0);//清空数据

                        }else if(s.contains("</Maintransformer>")){

                            list.add(WriteFileLocal.write("Maintransformer"+str,text.toString(),path));  //写入文件
                            text.setLength(0);//清空数据

                        }else if(s.contains("</DistributionTransformer>")){

                            list.add(WriteFileLocal.write("DistributionTransformer"+str,text.toString(),path));   //写入文件
                            distributionTransformer =text.toString();
                            text.setLength(0);//清空数据

                        }else if(s.contains("</Line>")){

                            list.add(WriteFileLocal.write("Line"+str,text.toString(),path));   //写入文件
                            text.setLength(0);//清空数据
                        }

                    }

                    if(s.contains("<TaskInformation>")
                            ||s.contains("<!Entity=")
                            ||s.contains("<Maintransformer>")
                            ||s.contains("<DistributionTransformer>")
                            ||s.contains("<Line>")
                            ||s.contains("</TaskInformation>")
                            ||s.contains("</Maintransformer>")
                            ||s.contains("</DistributionTransformer>")
                            ||s.contains("</Line>")
                            ){}else{
                        text.append(s);
                        text.append("\r\n");
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            br.close();
            System.out.println(file.getPath());
            removeFile.remove(file.getPath(),new_path);//移动文件

        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }


    public String[] toWrite(String readFileName,String path,String new_path){
        //先按照月份创建文件夹
        File folder=new File(path+ TimeUtil.getMonth()+"/asdasd");
        File dir = folder.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(readFileName);
        CutFile cutFile = new CutFile();
       List<String> list= cutFile.txtReadAndWrite(file,path,new_path);

        String[] strArray = list.toArray(new String[list.size()]);


        return strArray;
    }


    public static void main(String[] args){//读取文件

//        //先按照月份创建文件
//        String path ="D:/asdasd/";
//        File folder=new File(path+ TimeUtil.getMonth()+"/asdasd");
//        File dir = folder.getParentFile();
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//
//        File file = new File("E:\\测试文件.txt");
//        ReadFile readFile = new ReadFile();
//        readFile.txtReadAndWrite(file,path);

        CutFile cutFile=new CutFile();
        String[] strings= cutFile.toWrite("E:\\20181029_HN_e1279500-db34-11e8-a38f-d9ad93d59b1f.txt","D:/asdasd/","F:\\app\\");


    }


}
