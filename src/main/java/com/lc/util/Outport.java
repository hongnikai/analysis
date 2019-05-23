package com.lc.util;

import org.apache.poi.hssf.record.formula.functions.T;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("all")
public class Outport {




    public static void main(String[] args) throws IOException {

        String string ="@deviceId,proviceCode,cityCode,tgno,meterid,name,datatype,dataDate,v\n" +
                "#16M00000013019471,HN,CS,0100192589,2012000010873366,长沙,P,20180092501500,10.5\n" +
                "#16M00000013019471,HN,CS,0100192589,2012000010873366,长沙,P,20180925003000,11.4\n" +
                "#16M00000013019472,HN,CS,0100192589,2012000010873366,长沙,Q,20180925001500,11.1\n" +
                "#16M00000013019472,HN,CS,0100192589,2012000010873366,长沙,Q,20180925003000,11.2\n" +
                "#16M00000013019473,HN,CS,0100192589,2012000010873366,长沙,IA,20180925001500,11.0\n" +
                "#16M00000013019473,HN,CS,0100192589,2012000010873366,长沙,IA,20180925003000,11.8\n";

        //创建Excel对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建工作表单
        HSSFSheet sheet = workbook.createSheet("对象报表");

        String[] str=string.split("#");


        String[] title = str[0].split("@")[1].split(",");//{deviceId,proviceCode,cityCode,tgno,meterid,name,datatype,dataDate,v} 类似这种类型
        //创建HSSFRow对象 （行）
        HSSFRow row = sheet.createRow(0);
        System.out.println(title);
        for (int i=0;i<title.length;i++){
        //创建HSSFCell对象  （单元格）
        HSSFCell cell=row.createCell(i);
        //自定义300 * 60
        sheet.setColumnWidth(cell.getColumnIndex(), 100 * 40);
        //设置单元格的值
        cell.setCellValue(title[i]);

        }

        String data="";   //excle表格数据内容

        //创建HSSFRow对象 （行）
        HSSFRow row2 = sheet.createRow(1);
        //创建HSSFCell对象  （单元格）
        HSSFCell cell=row2.createCell(0);

//        sheet.shiftRows(8, sheet.getLastRowNum(), 1,true,false);
//
//        sheet.createRow(8);

        cell.setCellValue("安徽山东爱说大话");



        //输出Excel文件
        FileOutputStream output=new FileOutputStream("D:\\workbook.xls");
        workbook.write(output);
        output.flush();



    }


}
