package com.example.solution.pojo.vo;

import com.example.solution.pojo.entity.DishFlavor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishFlavorVO {

    private Long id;

    private String name;

    private Integer categoryId;

    private List<DishFlavor> flavors = new ArrayList<>();

}
