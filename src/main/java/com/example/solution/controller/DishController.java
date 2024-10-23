package com.example.solution.controller;

import com.example.solution.pojo.result.Result;
import com.example.solution.pojo.vo.DishFlavorVO;
import com.example.solution.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user/dish")
public class DishController {

    @Autowired
    private
    DishService dishService;

    @GetMapping("/list")
    public Result<List<DishFlavorVO>> getDishListById(Integer categoryId){
        List<DishFlavorVO> dishFlavorVO = dishService.method1(categoryId);

        return Result.success(dishFlavorVO);
    }

}
