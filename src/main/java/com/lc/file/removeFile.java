package com.lc.file;

import java.io.File;
/**
 *  @描述：将文件移动到指定文件夹
 *    description：移动p文件
 ** @author LC
 *  创建时间：2018-10-28 上午11:02
 */
public class removeFile {


    public static void remove(String path,String new_path){

        try{
            File afile = new File(path);
            if (afile.renameTo(new File(new_path + afile.getName()))){
                System.out.println("File is moved successful!");
            }
            else {
                System.out.println("File is failed to move!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
