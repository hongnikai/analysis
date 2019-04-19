package com.lc.file;

import com.lc.util.TimeUtil;

import java.io.*;
/**
 *  @描述：本地文件写入
 *    description：
 ** @author LC
 *  创建时间：2018-10-28 上午11:02
 */
@SuppressWarnings("all")
public class WriteFileLocal {

    private static String filenameTemp;

    public static void setFilenameTemp(String filenameTemp) {
        WriteFileLocal.filenameTemp = filenameTemp;
    }

    public static String getFilenameTemp() {
        return filenameTemp;
    }

    //    public static void main(String[] args) throws IOException {
//        WriteFileLocal writeFileLocal = new WriteFileLocal();
//        writeFileLocal.creatTxtFile("测试文件",message);
//        WriteFileLocal.writeTxtFile("你好");
//    }


    public static String write(String fileName,String message,String path) throws IOException {
        WriteFileLocal writeFileLocal = new WriteFileLocal();
        writeFileLocal.creatTxtFile(fileName,path); //创建文件
        WriteFileLocal.writeTxtFile(message);   //写入内容
        return writeFileLocal.creatTxtFile(fileName,path);
    }




    /**
     * 创建文件
     *
     * @throws IOException
     */
    public String creatTxtFile(String name,String path) throws IOException {
        boolean flag = false;
        filenameTemp = path+"/"+TimeUtil.getMonth()+"/"+ name + ".txt";

//        FileWriter fw = new FileWriter(new File(filenameTemp));
        File filename = new File(filenameTemp);
        if (!filename.exists()) {   //不存在
            filename.createNewFile();
            flag = true;
        }
//        else{
//            System.out.println("哈哈");
//            fw.write(message + "\n");//把转换后的字符串输出到新的文件
//        }
        return filenameTemp;
    }

    /**
     * 写文件
     *
     * @param newStr
     *            新内容
     * @throws IOException
     */
    public static boolean writeTxtFile(String newStr) throws IOException {
        // 先读取原有文件内容，然后进行写入操作
        boolean flag = false;

        String filein = newStr+ "\r\n";
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            // 文件路径
//            File folder=new File();
//            Boolean aa=folder.mkdir();// true

            File file = new File(filenameTemp);
            // 将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();

            // 保存该文件原有的内容
            for (int j = 1; (temp = br.readLine()) != null; j++) {
                buf = buf.append(temp);
                // System.getProperty("line.separator")
                // 行与行之间的分隔符 相当于“\n”
                buf = buf.append(System.getProperty("line.separator"));
            }
            buf.append(filein);

            fos = new FileOutputStream(file,true);
            pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            flag = true;
        } catch (IOException e1) {
            // TODO 自动生成 catch 块
            throw e1;
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return flag;
    }

    public static String getPath(String path){

        String realpath = path+ TimeUtil.getMonth();

        return "";
    }


}
