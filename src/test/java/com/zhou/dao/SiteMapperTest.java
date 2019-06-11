package com.zhou.dao;

import com.zhou.mapper.SiteMapper;
import com.zhou.model.Site;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SiteMapperTest {

    @Autowired
    private SiteMapper siteMapper;

    @Test
    public void insertTest(){
        Site site =new Site();
        site.setName("百度");
        site.setUrl("www.baidu.com");
        site.setCategoryId(1);
        site.setDescribe("wqe");
        siteMapper.insert(site);
    }

    @Test
    public void deleteTest(){
        Assert.assertEquals(1,siteMapper.delete(2));
    }


    @Test
    public void findByIdTest(){
        siteMapper.findById(1);
    }

    @Test
    public void getAllTest(){
        siteMapper.getAll();
    }

    @Test
    public void updateTest(){
        Site site =new Site();
        site.setId(3);
        site.setName("百蛇");
        site.setUrl("www.baishe.com");
        site.setCategoryId(1);
        Assert.assertEquals(1,siteMapper.update(site));
    }
}
