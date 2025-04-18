package com.nerzon.course.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nerzon.course.entity.Cat;
import com.nerzon.course.repository.CatRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Anna Teremizova
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {
    private final CatRepository catRepository;
    private final ObjectMapper objectMapper;

    @PostMapping("/api/add")
    public void addCat(@RequestBody Cat cat){
        log.info("New row: " + catRepository.save(cat));

    }

    @SneakyThrows
    @GetMapping("/api/all")
    public List<Cat> getAll(){
        return catRepository.findAll();
    }

    @GetMapping("/api")
    public Cat getCat(@RequestParam int id){
        return catRepository.findById(id).orElseThrow();
    }

    @DeleteMapping("/api")
    public void deleteCat(@RequestParam int id){
        catRepository.deleteById(id);
    }

    @PutMapping("/api/add")
    public String changeCat(@RequestBody Cat cat) {
        if (!catRepository.existsById(cat.getId())) {
            return "No such row";
        }
        return catRepository.save(cat).toString();
    }


}
