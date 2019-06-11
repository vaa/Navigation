package com.zhou.dao;

import com.zhou.mapper.CategoryMapper;
import com.zhou.model.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryMapperTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void insertTest(){
        Category category=new Category();
        category.setName("教育");
        categoryMapper.insert(category);
    }

    @Test
    public void getAllTest(){
        Assert.assertNotNull(categoryMapper.getAll());
    }

    @Test
    public void findByPageTest(){
        System.out.println("*****"+categoryMapper.select(new Category()).toString());
    }

    @Test
    public void deleteTest(){
        categoryMapper.delete(1);
    }


}
