package com.lc.file;

import java.io.*;

/**
 *  @描述：解析T标签
 *    description：读取固定路径下的p文件
 ** @author LC
 *  创建时间：2018-12-07 下午15：08
 */
@SuppressWarnings("all")
public class AnalysisTtag {


    static String taskId = null;

    /**
     *  @描述：返回taskId
     *    description：读取固定路径下的p文件
     ** @author LC
     *  创建时间：2018-12-07 下午15：08
     */
    public static String computeTtag(String taskInformation){
        taskId = taskInformation.split("#")[1].split(",")[0];
        return taskId;
    }


    public static boolean writeTtext(String T_filenameTemp,String message) throws IOException {
        // 先读取原有文件内容，然后进行写入操作
        boolean flag = false;

        String filein = message+ "\r\n";
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

            File file = new File(T_filenameTemp);
            // 将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();

            // 保存该文件原有的内容
            for (int j = 1; (temp = br.readLine()) != null; j++) {
//                buf = buf.append(temp);
                // System.getProperty("line.separator")
                // 行与行之间的分隔符 相当于“\n”
//                buf = buf.append(System.getProperty("line.separator"));
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


}
