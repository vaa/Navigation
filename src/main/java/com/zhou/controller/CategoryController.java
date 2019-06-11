package com.zhou.controller;

import com.zhou.dto.QueryPage;
import com.zhou.dto.ResponseCode;
import com.zhou.exception.GlobalException;
import com.zhou.model.Category;
import com.zhou.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController{

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/findAllCount")
    public ResponseCode findAllCount(){
        return ResponseCode.success(categoryService.findAllCount());
    }

    @RequestMapping(value = "/findAll")
    public ResponseCode select() {
        return ResponseCode.success(categoryService.getAll());
    }

    @PostMapping(value = "/findByPage")
    public ResponseCode findByPage(QueryPage queryPage, Category category) {
        return ResponseCode.success(super.selectByPageNumSize(queryPage, () -> categoryService.findByPage(category)));
    }

    @GetMapping(value = "/findById")
    public ResponseCode findById(@RequestParam("id") int id) {
        return ResponseCode.success(categoryService.findById(id));
    }

    @PostMapping(value = "/save")
    public ResponseCode save(@RequestBody Category category) {
        try {
            categoryService.insert(category);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseCode update(@RequestBody Category category) {
        try {
            categoryService.update(category);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @PostMapping(value = "/delete")
    public ResponseCode delete(@RequestBody List<Integer> ids) {
        try {
            categoryService.delete(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @RequestMapping(value = "/findCategoryRanking")
    public ResponseCode findCategoryRanking() {
            return ResponseCode.success( categoryService.findCategoryRanking());
    }

}
