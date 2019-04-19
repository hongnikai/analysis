package com.lc.file;

import com.lc.util.TimeUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *  @描述：切割p文件
 *    description：读取固定路径下的p文件
 ** @author LC
 *  创建时间：2018-10-31 下午14：59
 */
@SuppressWarnings("all")
public class SlicePfile {

        public String slicePfileInToFour(File file,String path,String new_path){

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
                                WriteFileLocal.write("TaskInformation"+str,text.toString(),path);   //写入文件
                                text.setLength(0);//清空数据

                            }else if(s.contains("</Maintransformer>")){

                                WriteFileLocal.write("Maintransformer"+str,text.toString(),path);   //写入文件
                                text.setLength(0);//清空数据

                            }else if(s.contains("</DistributionTransformer>")){

                                WriteFileLocal.write("DistributionTransformer"+str,text.toString(),path);   //写入文件

                                text.setLength(0);//清空数据

                            }else if(s.contains("</Line>")){

                                WriteFileLocal.write("Line"+str,text.toString(),path);   //写入文件
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
                removeFile.remove(file.getPath(),new_path);//移动文件

            }catch(Exception e){
                e.printStackTrace();
            }
            return result.toString();

        }


    public void toWrite(String readFileName,String path,String new_path){

        //先按照月份创建文件
        File folder=new File(path+ TimeUtil.getMonth()+"/asdasd");
        File dir = folder.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File("E:\\测试文件.txt");
        SlicePfile slicePfile= new SlicePfile();

        slicePfile.slicePfileInToFour(file,path,new_path);


    }



    public static void main(String[] args){//读取文件
        //先按照月份创建文件
        String path ="D:/asdasd/";
        File folder=new File(path+ TimeUtil.getMonth()+"/asdasd");
        File dir = folder.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File("E:\\测试文件.txt");
        SlicePfile slicePfile= new SlicePfile();

//        slicePfile.slicePfileInToFour(file,path,new_path);

    }




}
