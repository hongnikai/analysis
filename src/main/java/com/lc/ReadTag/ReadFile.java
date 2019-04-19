package com.lc.ReadTag;

import java.io.*;

public class ReadFile {



    public String ReadfileReturnString(String filePath){

        File file = new File(filePath);
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            StringBuffer text =new StringBuffer();
            String s = null;
            while((s = br.readLine())!=null) {//使用readLine方法，一次读一行
                s+="\r\n";
                result.append(s);
            }
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }


    public static void main(String[] args) {
        ReadFile readDTag = new ReadFile();
        System.out.println(readDTag.ReadfileReturnString("E:\\测试文件.txt"));

    }

}
