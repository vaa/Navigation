package com.zhou.service.impl;

import com.zhou.exception.GlobalException;
import com.zhou.mapper.CategoryMapper;
import com.zhou.model.Category;
import com.zhou.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public int findAllCount() {
        return categoryMapper.findAllCount();
    }

    public List<Category> getAll() {
        return categoryMapper.getAll();
    }

    public int insert(Category category) {
        return categoryMapper.insert(category);
    }

    public int update(Category category) {
        return categoryMapper.update(category);
    }

    public void delete(List<Integer> ids) {
        if (!ids.isEmpty()) {
            try {
                ids.forEach(id -> {
                    categoryMapper.delete(id);
                    //删除与该分类关联的信息
                    //！！！！！！！！
                });
            } catch (Exception e) {
                throw new GlobalException(e.getMessage());
            }
        }
    }

    public Category findById ( int id){
        return categoryMapper.findById(id);
    }

    public List<Category> findByPage (Category category){
        return categoryMapper.select(category);
    }

    public List<Category> findCategoryRanking() {
        return categoryMapper.selectCategoryRanking();
    }

}
