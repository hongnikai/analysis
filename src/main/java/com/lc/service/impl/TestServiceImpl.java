package com.lc.service.impl;

import com.lc.dao.TestDao;
import com.lc.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "testService")
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;


    @Override
    public List<Map<String, String>> selectAllMessage_test() {
        return testDao.selectAllMessage_test();
    }
}
