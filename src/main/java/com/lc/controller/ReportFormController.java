package com.lc.controller;


import com.lc.service.TestService;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@Scope(value="prototype")
@SuppressWarnings("all")
public class ReportFormController {

    @Autowired
    private TestService testService;

    MongoClient client = new MongoClient("192.168.248.136", 27017);


    @RequestMapping("/test")
    public Object test(){
        return testService.selectAllMessage_test();
    }

    @RequestMapping("mongoTest")
    public void mongoTest(){

//        System.out.println("测试mongo客户端连接");
//        MongoDatabase sang = client.getDatabase("lc");
//        MongoCollection<Document> c = sang.getCollection("c1");
//        FindIterable<Document> documents = c.find();
//        MongoCursor<Document> iterator = documents.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }

    }


}
