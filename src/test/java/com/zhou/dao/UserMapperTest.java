package com.zhou.dao;

import com.zhou.mapper.UserMapper;
import com.zhou.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insertTest(){
        User user =new User();
        user.setUsername("zhou");
        user.setPassword("123456");
        user.setNickName("小驴");
        userMapper.insert(user);
    }

    @Test
    public void deleteTest(){
        Assert.assertEquals(1,userMapper.delete(1));
    }

    @Test
    public void findByIdTest(){
        User user=userMapper.findById(1);
        Assert.assertNotNull(user);
    }

    @Test
    public void getAllTest(){
        Assert.assertNotNull(userMapper.getAll());
    }

    @Test
    public void updateTest(){
        User user =new User();
        user.setId(1);
        user.setUsername("zzzz");
        user.setPassword("1234567890");
        user.setNickName("小子");
        Assert.assertEquals(1,userMapper.update(user));
    }
}
