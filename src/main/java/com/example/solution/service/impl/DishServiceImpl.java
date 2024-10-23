package com.example.solution.service.impl;

import com.example.solution.mapper.DishMapper;
import com.example.solution.pojo.entity.DishFlavor;
import com.example.solution.pojo.item.DishesAndFlavorsItem;
import com.example.solution.pojo.vo.DishFlavorVO;
import com.example.solution.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    /**
     * 方法 1：批量查询
     * @param categoryId
     * @return
     */
    public List<DishFlavorVO> method1(Integer categoryId) {
        //1. 获取所有的dishes
        List<DishFlavorVO> dishFlavorVOS = dishMapper.getDishes(categoryId);
        //1.1 获取所有的dish_id
        List<Long> dishIds = dishFlavorVOS.stream().map(DishFlavorVO::getId).collect(Collectors.toList());

        //2.获得所有的dish_flavor
        List<DishFlavor> dishFlavors = dishMapper.selectFlavorsByDishIdList(dishIds);

        //3 聚合dish和dish_flavor
        Map<Long, List<DishFlavor>> flavorsMap = dishFlavors.stream().collect(Collectors.groupingBy(DishFlavor::getDishId));
        for (DishFlavorVO dishFlavorVO : dishFlavorVOS) {
            dishFlavorVO.setFlavors(flavorsMap.get(dishFlavorVO.getId()));
        }

        return dishFlavorVOS;
    }

    /**
     * 方法 2：联表查询
     * @param categoryId
     * @return
     */
    public List<DishFlavorVO> method2(Integer categoryId){
        List<DishFlavorVO> dishFlavorVOS = new ArrayList<>();

        //1. 联表查询
        List<DishesAndFlavorsItem> dishes = dishMapper.getDishesAndFlavors(categoryId);
        //2. 组合dish和dish_flavor
        Map<Long, List<DishesAndFlavorsItem>> collect = dishes.stream().collect(Collectors.groupingBy(DishesAndFlavorsItem::getFDishId));
        for (Map.Entry<Long, List<DishesAndFlavorsItem>> entry : collect.entrySet()) {
            List<DishFlavor> dishFlavors = new ArrayList<>();
            Long dishId = entry.getKey();
            List<DishesAndFlavorsItem> dishesAndFlavorsItems = entry.getValue();
            for (DishesAndFlavorsItem dishesAndFlavorsItem : dishesAndFlavorsItems) {
                DishFlavor dishFlavor = new DishFlavor();

                dishFlavor.setId(dishesAndFlavorsItem.getFId());
                dishFlavor.setValue(dishesAndFlavorsItem.getFValue());
                dishFlavor.setName(dishesAndFlavorsItem.getFName());
                dishFlavor.setDishId(dishesAndFlavorsItem.getFDishId());

                dishFlavors.add(dishFlavor);
            }
            DishFlavorVO dishFlavorVO = new DishFlavorVO();
            dishFlavorVO.setFlavors(dishFlavors);
            dishFlavorVO.setId(dishId);

        }
        return dishFlavorVOS;
    }
}
