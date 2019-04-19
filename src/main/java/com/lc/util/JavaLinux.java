package com.lc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
/**
 *  @描述：java 操作linux
 *    description：
 ** @author LC
 */
@SuppressWarnings("all")
public class JavaLinux {

    public void gotoShell(){

            String[] cmd = { "/bin/sh", "-c", "netstat -anp " };
            InputStream in = null;
            String result = null;
            try {
                Process pro = Runtime.getRuntime().exec(cmd);
                pro.waitFor();
                in = pro.getInputStream();
                BufferedReader read = new BufferedReader(new InputStreamReader(in));
                while((result = read.readLine())!=null) {
                    System.out.println(result);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }



    public static void main(String[] args) {
        String[] cmd = { "/bin/sh", "-c", "netstat -anp " };
        InputStream in = null;
        String result = null;
        try {
            Process pro = Runtime.getRuntime().exec(cmd);
            pro.waitFor();
            in = pro.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in));
            while((result = read.readLine())!=null) {
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
