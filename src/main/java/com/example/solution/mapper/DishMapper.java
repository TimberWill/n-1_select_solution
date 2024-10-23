package com.example.solution.mapper;

import com.example.solution.pojo.entity.Dish;
import com.example.solution.pojo.entity.DishFlavor;
import com.example.solution.pojo.item.DishesAndFlavorsItem;
import com.example.solution.pojo.vo.DishFlavorVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishMapper {

    List<Dish> getDishByCategoryId(Integer categoryId);

    List<DishesAndFlavorsItem> getDishesAndFlavors(Integer categoryId);

    List<DishFlavorVO> getDishes(Integer categoryId);

    List<DishFlavor> selectFlavorsByDishIdList(List<Long> dishIds);
}
