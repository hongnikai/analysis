package com.lc.file2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadTXT {


    /**
     *      * 读取txt文件的内容
     *      * @param file 想要读取的文件对象
     *      * @return 返回文件内容
     *      
     */
    public static String txt2String(String path) {
        File file = new File(path);
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result.append(System.lineSeparator() + s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static void main(String[] args) {

    }

}