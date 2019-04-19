package com.lc.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontProvider;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 *  @描述：将网页生成pdf
 *    description：标签必须成对儿出现，不能出现单标签
 ** @author LC
 */
@SuppressWarnings("all")
public class PDFKit {

    public static void main(String[] args) throws IOException
    {
        String html = PDFKit.readFileByUrl("http://localhost:8080/choujiang/pdf.html"); // 将html代码读取到html字符串中

        try {
            Document document = new Document();
            PdfWriter mPdfWriter = PdfWriter.getInstance(document, new FileOutputStream(new File("D:\\3.pdf")));
            document.open();
            ByteArrayInputStream bin = new ByteArrayInputStream(html.getBytes());
            XMLWorkerHelper.getInstance().parseXHtml(mPdfWriter, document, bin, null, new ChinaFontProvide());
            System.out.println("生成完毕");
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final class ChinaFontProvide implements FontProvider
    {

        @Override public boolean isRegistered(String s)
        {
            return false;
        }

        @Override public Font getFont(String arg0, String arg1, boolean arg2, float arg3, int arg4, BaseColor arg5)
        {
            BaseFont bfChinese = null;
            try
            {
                bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
            return FontChinese;
        }
    }


    public static String readFileByUrl(String urlStr) {
        String res=null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(3*1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //得到输入流
            InputStream inputStream = conn.getInputStream();
            res = readInputStream(inputStream);
        } catch (Exception e) {
            System.out.println("通过url地址获取文本内容失败 Exception："+ e);
//            log.error("通过url地址获取文本内容失败 Exception：" + e);
        }
        return res;
    }

    /**
     * 从输入流中获取字符串
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static String readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        //System.out.println(new String(bos.toByteArray(),"utf-8"));
        return new String(bos.toByteArray(),"utf-8");
    }

}
