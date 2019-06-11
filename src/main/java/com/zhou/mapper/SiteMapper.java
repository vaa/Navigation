package com.zhou.mapper;

import com.zhou.model.Site;

import java.util.List;

public interface SiteMapper{

    int findAllCount();

    List<Site> getAll();

    Site findById(Integer id);

    int insert(Site site);

    int update(Site site);

    int delete(Integer id);

    List<Site> select(Site site);

    List<Site> findByCategoryId(Integer id);
}