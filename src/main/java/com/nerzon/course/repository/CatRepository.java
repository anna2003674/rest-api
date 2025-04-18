package com.nerzon.course.repository;

import com.nerzon.course.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Anna Teremizova
 */
@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {
}
