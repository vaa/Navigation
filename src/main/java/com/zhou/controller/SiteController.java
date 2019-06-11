package com.zhou.controller;

import com.zhou.dto.QueryPage;
import com.zhou.dto.ResponseCode;
import com.zhou.dto.SiteRanking;
import com.zhou.exception.GlobalException;
import com.zhou.model.Site;
import com.zhou.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/site")
public class SiteController extends BaseController{

    @Autowired
    SiteService siteService;


    @RequestMapping("/findAllCount")
    public ResponseCode findAllCount(){
        return ResponseCode.success(siteService.findAllCount());
    }


    @GetMapping(value = "/findAll")
    public ResponseCode findAll() {
        return ResponseCode.success(siteService.findAllCount());
    }

    @PostMapping(value = "/findByPage")
    public ResponseCode findByPage(QueryPage queryPage, Site site) {
        return ResponseCode.success(super.selectByPageNumSize(queryPage, () -> siteService.findByPage(site)));
    }

    @GetMapping(value = "/findById")
    public ResponseCode findById(@RequestParam("id") Integer id) {
        return ResponseCode.success(siteService.findById(id));
    }

    @PostMapping(value = "/save")
    public ResponseCode save(@RequestBody Site site) {
        try {
            siteService.insert(site);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseCode update(@RequestBody Site site) {
        try {
            siteService.update(site);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @PostMapping(value = "/delete")
    public ResponseCode delete(@RequestBody List<Integer> ids) {
        try {
            siteService.delete(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @RequestMapping("/findSiteRanking")
    public List<SiteRanking> findSiteRanking() {
        return siteService.findSiteRanking();
    }


}
