package com.zhou.service;

import com.zhou.dto.SiteRanking;
import com.zhou.model.Site;

import java.util.List;


public interface SiteService{

      int findAllCount();

      List<Site> getAll();

      Site findById(Integer id);

      int insert(Site site);

      int update(Site site);

      int delete(Integer id);

      void delete(List<Integer> ids);

      List<Site> findByPage (Site site);

      /**
       * 查询按照分类归档的整合数据，
       * 格式：[{{Category}, [{article},{article}...]}, {{Category}, [{article}, {article}...]}]
       *
       * @return
       */
      List<SiteRanking> findSiteRanking();

}
