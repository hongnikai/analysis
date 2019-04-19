package com.lc.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
/**
 *  @描述：调用外部接口工具类
 *    description：
 ** @author LC
 */
@SuppressWarnings("all")
public class ToInterfaceReturnString {





    public static String interfaceUtil(String path, Object data, String requestMethod) {
        Map<String,String> map =new HashMap<String, String>();
        StringBuffer sb = new StringBuffer();

        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // 打开和url之间的连接
            PrintWriter out = null;
            conn.setRequestMethod(requestMethod); // 请求方式
// 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
//设置传到另一个接口的格式为json
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
// 设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
// 最常用的Http请求无非是get和post，get请求可以获取静态页面，也可以把参数放在URL字串后面，传递给servlet，
// post与get的 不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。
            conn.setDoOutput(true);
            conn.setDoInput(true);
// allowUserInteraction 如果为 true，则在允许用户交互（例如弹出一个验证对话框）的上下文中对此 URL 进行检查。
            conn.setAllowUserInteraction(false);
// 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
// 发送请求参数即数据
            map.put("asdasd","asdasd");
            out.print(map);
// 缓冲数据
            out.flush();
            out.close();
// 获取URLConnection对象对应的输入流
            InputStream is = conn.getInputStream();
// 构造一个字符流缓存
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = "";
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
// 关闭流
            is.close();
// 断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
// 固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
            conn.disconnect();
            System.out.println("调用app后台接口完整结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


}
