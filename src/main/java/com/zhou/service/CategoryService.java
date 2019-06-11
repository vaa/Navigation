package com.zhou.service;

import com.zhou.model.Category;

import java.util.List;


public interface CategoryService {

    int findAllCount();

    List<Category> getAll();

    int insert(Category category);

    int update(Category category);

    void delete(List<Integer> ids);

    Category findById(int id);

    List<Category> findByPage(Category category);

    List<Category> findCategoryRanking();
}
