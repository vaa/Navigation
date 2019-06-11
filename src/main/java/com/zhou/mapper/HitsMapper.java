package com.zhou.mapper;

import com.zhou.model.Hits;

import java.util.List;

public interface HitsMapper {

    List<Hits> getAll(Integer userId);

    Hits findById(Integer userId,Integer siteId);

    int insert(Hits hits);

    int update(Hits hits);

    int delete(Integer userId,Integer siteId);

}