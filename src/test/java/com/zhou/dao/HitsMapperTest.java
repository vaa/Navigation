package com.zhou.dao;

import com.zhou.mapper.HitsMapper;
import com.zhou.model.Hits;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HitsMapperTest {

    @Autowired
    private HitsMapper hitsMapper;

    @Test
    public void insertTest(){
        Hits hits =new Hits();
        hits.setUserId(1);
        hits.setSiteId(2);
        hits.setUserHits(1);
        hitsMapper.insert(hits);
    }

    @Test
    public void deleteTest(){
        Assert.assertEquals(1,hitsMapper.delete(1,2));
    }

    @Test
    public void findByIdTest(){
        Hits hits=hitsMapper.findById(1,2);
        Assert.assertNotNull(hits);
    }

    @Test
    public void getAllTest(){
        Assert.assertNotNull(hitsMapper.getAll(1));
    }

    @Test
    public void updateTest(){
        Hits hits =new Hits();
        hits.setUserId(1);
        hits.setSiteId(2);
        hits.setUserHits(3);
        Assert.assertEquals(1,hitsMapper.update(hits));
    }
}
