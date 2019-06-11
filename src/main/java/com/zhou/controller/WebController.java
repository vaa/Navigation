package com.zhou.controller;

import com.zhou.model.Site;
import com.zhou.service.CategoryService;
import com.zhou.service.SiteService;
import com.zhou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class WebController {

    @Autowired
    SiteService siteService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @GetMapping(value = {"", "/", "/index"})
    public String index() {
        return "site/index";
    }

    @GetMapping("/user")
    public String user() {
        return "site/page/user";
    }

    @GetMapping("/login")
    public String login() {
        return "site/login";
    }

    @GetMapping("/register")
    public String register() {
        return "site/page/register";
    }

    @GetMapping("/site")
    public String site() {
        return "site/page/site";
    }

    @GetMapping("/me")
    public String me() {
        return "site/page/me";
    }

    @GetMapping("/password")
    public String password(HttpSession session) {
        return "site/page/password";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/hits")
    public String url(@RequestParam String url, @RequestParam Integer id) {
        if(url!=null&&id!=null){
            Site site=siteService.findById(id);
            if (site!=null){
                site.setHits(site.getHits()+1);
                siteService.update(site);
                return "redirect:http://"+url;
            }else{
                return "index";
            }
        }else{
            return "index";
        }
    }



}
