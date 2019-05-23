package com.lc.test;

import com.lc.file.AnalysisTtag;
import com.lc.file.WriteFileLocal;
import com.lc.util.StringUtil;
import com.lc.util.ToInterface;

import java.io.*;
import java.util.*;
@SuppressWarnings("all")
public class test01 {

    public  String aaa=null;

//    @Test
    public  void tres()  {

        aaa="asdasd";

    }


    public static void main(String[] args) throws IOException {


      String s=  "#16M00000013019474,41311046430000001,HN,CS,0100192589,2012000010873366,1I.7,A,20180925001500,11.0";
        System.out.println(s.split("#")[1].split(",")[7]);

    }

//    @Test
    public void aaaaa(){

        tres();
        asdasd();


    }




    public  void asdasd(){
        System.out.println(aaa);
    }


}
