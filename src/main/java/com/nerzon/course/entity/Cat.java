package com.nerzon.course.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Anna Teremizova
 */
@Data
@Getter
@Setter
@AllArgsConstructor
public class Cat {
    private String name;
    private int age;
    private int weight;
}
