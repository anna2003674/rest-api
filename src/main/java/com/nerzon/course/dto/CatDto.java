package com.nerzon.course.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author Anna Teremizova
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CatDto {
    String name;
    int weight;
    int age;
}
