package com.example.demo;


import com.example.demo.dao.LoginDao;
import com.example.demo.entities.LoginEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date:2022/2/12
 * Author:ybc
 * Description:
 */
@SpringBootTest
public class MyBatisPlusTest {

    @Autowired
    private LoginDao loginDao;


    @Test
    public void testSelectList(){
        //通过条件构造器查询一个list集合，若没有条件，则可以设置null为参数
        List<LoginEntity> list = loginDao.selectList(null);
        list.forEach(System.out::println);
    }

  /*  @Test
    public void insert(LoginEntity loginEntity) {
        LoginEntity user = new LoginEntity();
        user.setUserID(3);
        user.setUserName("c");
        user.setPassword("123");
        int result = loginDao.insert(user);
        System.out.println("result:"+result);
    }
*/
    @Test
    public void time(){
        long date2 = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentTime2 = dateFormat.format(date2);
        System.out.println(currentTime2);
    }

}

