package com.wangcl.service;

import com.wangcl.mapper.VisitorMapper;
import com.wangcl.redis.HashRedisServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: zhangocean
 * @Date: 2019/5/6 14:30
 * Describe:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    HashRedisServiceImpl hashRedisService;
    @Autowired
    VisitorMapper visitorMapper;

    @Test
    public void redisTest(){
    }

}
