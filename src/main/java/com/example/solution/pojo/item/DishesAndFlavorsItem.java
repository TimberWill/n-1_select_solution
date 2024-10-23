package com.example.solution.pojo.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishesAndFlavorsItem {
    private Long id;

    private String name;

    private Integer categoryId;

    private Long fDishId;

    private Long fId;

    private String fName;

    private String fValue;
}
