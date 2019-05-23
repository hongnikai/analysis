package com.lc.file;

import com.lc.util.TimeUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 *  @描述：读取文件解析并写入
 *    description：读取固定路径下的p文件
 ** @author LC
 *  创建时间：2018-10-28 上午11:02
 */
@SuppressWarnings("all")
public class ReadFile2 {

    public static String taskInformation=null;
    public String distributionTransformer =null;
    public static String fileNameMaintransformer = null;
    private static String  T_filenameTemp;
    private static String  M_filenameTemp;
    private static String  D_filenameTemp;
    private static String  L_filenameTemp;
    private boolean mark=true;
    public String taskId=null;


//    ReadToHbase readToHbase=new ReadToHbase();

    public String txtReadAndWrite(File file,String path,String new_path) throws IOException {
        StringBuilder result = new StringBuilder();

        String str=TimeUtil.getSystemTimeFormartNoBlank();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            StringBuffer text =new StringBuffer();

            while((s = br.readLine())!=null){
                //使用readLine方法，一次读一行
//                    if(s.contains("<TaskInformation>")){
//                        text.setLength(0);//清除数据
//                    //创建T文件
//                        T_filenameTemp = path+"/"+TimeUtil.getMonth()+"/"+ "TaskInformation"+TimeUtil.getSystemTimeFormartNoBlank()+".txt";
//                        File t_file =new File(T_filenameTemp);
//                        if (!t_file.exists()) {   //不存在
//                            t_file.createNewFile();
//                        }
//                    }else{}
//                    if(s.contains("<Maintransformer>")){
//                    text.setLength(0);//清除数据
//                    //创建M文件
//                        M_filenameTemp = path+"/"+TimeUtil.getMonth()+"/"+ "Maintransformer"+TimeUtil.getSystemTimeFormartNoBlank()+".txt";
//                    File t_file =new File(M_filenameTemp);
//                    if (!t_file.exists()) {   //不存在
//                        t_file.createNewFile();
//                    }
//                    }else{}
//                    if(s.contains("<DistributionTransformer>")){
//                    text.setLength(0);//清除数据
//                    //创建D文件
//                        D_filenameTemp = path+"/"+TimeUtil.getMonth()+"/"+ "DistributionTransformer"+TimeUtil.getSystemTimeFormartNoBlank()+".txt";
//                    File t_file =new File(D_filenameTemp);
//                    if (!t_file.exists()) {   //不存在
//                        t_file.createNewFile();
//                    }
//                    }else{}
//                    if(s.contains("<Line>")){
//                    text.setLength(0);//清除数据
//                    //创建L文件
//                        L_filenameTemp = path+"/"+TimeUtil.getMonth()+"/"+ "Line"+TimeUtil.getSystemTimeFormartNoBlank()+".txt";
//                    File t_file =new File(L_filenameTemp);
//                    if (!t_file.exists()) {   //不存在
//                        t_file.createNewFile();
//                    }
//                    }else{}

                    //创建文件之后开始写入数据
                    if(s.contains("<TaskInformation>")){
                        //创建T文件
                        T_filenameTemp = path+"/"+TimeUtil.getMonth()+"/"+ "TaskInformation"+TimeUtil.getSystemTimeFormartNoBlank()+".txt";

                        File t_file =new File(T_filenameTemp);
                        if (!t_file.exists()) {   //不存在
                            t_file.createNewFile();
                        }
                        break;
                    }

            }
            while((s = br.readLine())!=null){
                    if(s.contains("</TaskInformation>")){
                        break;
                    }
                    if(s.contains("#")){
                        taskInformation = s;
                      taskId = AnalysisTtag.computeTtag(s);
                    }
                    AnalysisTtag.writeTtext(T_filenameTemp,s);
            }
            while((s = br.readLine())!=null){
//                创建M文件
                if (s.contains("<Maintransformer>")){
                    M_filenameTemp = path+"/"+TimeUtil.getMonth()+"/"+ "Maintransformer"+TimeUtil.getSystemTimeFormartNoBlank()+".txt";
                    File t_file =new File(M_filenameTemp);
                    if (!t_file.exists()) {   //不存在
                        t_file.createNewFile();
                    }
                }else{
                    if(s.contains("</Maintransformer>")){
                        break;
                    }
                    AnalysisTtag.writeTtext(M_filenameTemp,s);
                }
            }
            ReadMFile readMFile = new ReadMFile();
            readMFile.readMFile(taskInformation,M_filenameTemp,taskId);
            while((s = br.readLine())!=null){
//                创建D文件  返回D文件路径
                if (s.contains("<DistributionTransformer>")){
                    D_filenameTemp = path+"/"+TimeUtil.getMonth()+"/"+ "DistributionTransformer"+TimeUtil.getSystemTimeFormartNoBlank()+".txt";
                    File t_file =new File(D_filenameTemp);
                    if (!t_file.exists()) {   //不存在
                        t_file.createNewFile();
                    }
                }else{
                    if(s.contains("</DistributionTransformer>")){
                        break;
                    }
                    AnalysisTtag.writeTtext(D_filenameTemp,s);
                }
            }
            //读取生成D文件，
            ReadDFile readDFile =  new ReadDFile();
            readDFile.readDFile(taskInformation,D_filenameTemp,taskId);


            while ((s = br.readLine())!=null){
//                创建L文件  返回L文件路径
                if (s.contains("<Line>")){
                    L_filenameTemp = path+"/"+TimeUtil.getMonth()+"/"+ "Line"+TimeUtil.getSystemTimeFormartNoBlank()+".txt";
                    File t_file =new File(L_filenameTemp);
                    if (!t_file.exists()) {   //不存在
                        t_file.createNewFile();
                    }
                }else{
                    if(s.contains("</Line>")){
                        break;
                    }
                    AnalysisTtag.writeTtext(L_filenameTemp,s);
                }
            }
            br.close();
            removeFile.remove(file.getPath(),new_path);//移动文件

        }catch(Exception e){
            e.printStackTrace();
        }

//        String task_Id = taskInformation.split("#")[1].split(",")[0];
//        task_Id+=(","+taskInformation.split("#")[1].split(",")[2]);

        return taskId;
    }

    /**
     ** @author LC
     * readFileName 读取文件的全路径（包括文件名）
     * path         生成 解析四个文件的路径(不包括文件名)
     * new_path     将解析文件挪走路径（不包括文件名）
     * return       taskId
     */
    public String toWrite(String readFileName,String path,String new_path) throws IOException {
        //先按照月份创建文件
        File folder=new File(path+ TimeUtil.getMonth()+"/asdasd");
        File dir = folder.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(readFileName);
        ReadFile2 readFile = new ReadFile2();
        String taskId = readFile.txtReadAndWrite(file,path,new_path);

        //以下为解析D 标签过程
        ReadDFile readDFile = new ReadDFile();
        readDFile.readDFile(taskInformation,D_filenameTemp,taskId);

        //以下为解析M 标签过程
        ReadMFile readMFile = new ReadMFile();
        readMFile.readMFile(taskInformation,M_filenameTemp,taskId);

        return taskId;
    }


    public static void main(String[] args) throws IOException {//读取文件

        ReadFile2 read=new ReadFile2();
        String taskId_planTime = read.toWrite("E:\\测试文件.txt","D:/asdasd/","E:\\");




    }




}
