package com.zhou.mapper;

import com.zhou.model.Category;

import java.util.List;

public interface CategoryMapper {

    int findAllCount();

    List<Category> getAll();

    int insert(Category category);

    int delete(Integer id);

    int update(Category category);

    Category findById(int id);

    List<Category> select(Category category);

    List<Category> selectCategoryRanking();
}