package com.zhou.service.impl;

import com.zhou.dto.SiteRanking;
import com.zhou.exception.GlobalException;
import com.zhou.mapper.SiteMapper;
import com.zhou.model.Category;
import com.zhou.model.Site;
import com.zhou.service.CategoryService;
import com.zhou.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SiteServiceImpl implements SiteService {

    @Autowired
    private SiteMapper siteMapper;

    @Autowired
    private CategoryService categoryService;

    public int findAllCount() {
        return siteMapper.findAllCount();
    }

    public List<Site> getAll(){
        return siteMapper.getAll();
    }

    public Site findById(Integer id){
        return siteMapper.findById(id);
    }

    public int insert(Site site){
        return siteMapper.insert(site);
    }

    public int update(Site site){
        return siteMapper.update(site);
    }

    public int delete(Integer id){
        return siteMapper.delete(id);
    }

    public void delete(List<Integer> ids) {
        if (!ids.isEmpty()) {
            try {
                ids.forEach(id -> {
                    siteMapper.delete(id);
                    //删除与该分类与文章关联的信息
                    //！！！！！！！！
                });
            } catch (Exception e) {
                throw new GlobalException(e.getMessage());
            }
        }
    }

    public List<Site> findByPage (Site site){
        //return siteMapper.select(site);
        List<Site> list = siteMapper.select(site);
        findInit(list);
        return list;
    }

    /**
     * 封装网站分类数据
     *
     * @param list
     */
    private void findInit(List<Site> list) {
        if (!list.isEmpty()) {
            list.forEach(site -> {
                Category category =categoryService.findById(site.getCategoryId());
                if(category!=null){
                    site.setCategory(category.getName());
                }
            });
        }
    }

    public List<SiteRanking> findSiteRanking() {
        List<SiteRanking> siteRankingList = new ArrayList<SiteRanking>();
        try {
            List<Category> categorys = categoryService.findCategoryRanking();
            categorys.forEach(category -> {
                List<Site> siteList = siteMapper.findByCategoryId(category.getId());
                SiteRanking siteRankings = new SiteRanking(category, siteList);
                siteRankingList.add(siteRankings);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return siteRankingList;
    }


}

