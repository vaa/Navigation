package com.zhou.service.impl;

import com.zhou.mapper.HitsMapper;
import com.zhou.model.Hits;
import com.zhou.service.HitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HitsServiceImpl implements HitsService {

    @Autowired
    private HitsMapper hitsMapper;

    public List<Hits> getAll(Integer userId) {
        return hitsMapper.getAll(userId);
    }

    public Hits findById(Integer userId, Integer siteId) {
        return hitsMapper.findById(userId, siteId);
    }

    public int insert(Hits hits) {
        return hitsMapper.insert(hits);
    }

    public int update(Hits hits) {
        return hitsMapper.update(hits);
    }

    public int delete(Integer userId, Integer siteId) {
        return hitsMapper.delete(userId, siteId);
    }
}

