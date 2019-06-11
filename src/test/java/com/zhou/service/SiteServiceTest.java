package com.zhou.service;

import com.zhou.controller.BaseController;
import com.zhou.dto.QueryPage;
import com.zhou.model.Site;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SiteServiceTest extends BaseController {

    @Autowired
    private SiteService siteService;

    @Test
    public void findByPageTest(){
        QueryPage queryPage=new QueryPage();
        queryPage.setPageCode(1);
        queryPage.setPageSize(5);
        System.out.println("*****"+super.selectByPageNumSize(queryPage, () -> siteService.findByPage(new Site())).toString());
    }

    @Test
    public void findArchivesTest(){

        System.out.println(siteService.findSiteRanking().toString());


    }

}
