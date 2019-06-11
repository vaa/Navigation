package com.zhou.dao;

import com.zhou.mapper.AdminMapper;
import com.zhou.model.Admin;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminMapperTest {

    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void insertTest(){
        Admin admin =new Admin();
        admin.setUsername("zhouts");
        admin.setPassword("123456");
        admin.setNickName("骑着小驴");
        Assert.assertEquals(1,adminMapper.insert(admin));
    }

    @Test
    public void deleteTest(){
        Assert.assertEquals(1,adminMapper.delete(1));
    }

    @Test
    public void findByIdTest(){
        Admin admin=adminMapper.findById(3);
        Assert.assertNotNull(admin);
    }

    @Test
    public void getAllTest(){
        Assert.assertNotNull(adminMapper.getAll());
    }

    @Test
    public void updateTest(){
        Admin admin =new Admin();
        admin.setId(2);
        admin.setUsername("zzzzz");
        admin.setPassword("1234567890");
        admin.setNickName("小小");
        Assert.assertEquals(1,adminMapper.update(admin));
    }

    @Test
    public void selectByNameTest(){
        System.out.println(adminMapper.selectByUsername("admin").toString());
    }
}
