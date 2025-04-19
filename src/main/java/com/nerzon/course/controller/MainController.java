package com.nerzon.course.controller;

import com.nerzon.course.dto.CatDto;
import com.nerzon.course.entity.Cat;
import com.nerzon.course.repository.CatRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Anna Teremizova
 */
@Slf4j
@Tag(name = "Cats", description = "Методы управления котиками")
@RestController
@RequiredArgsConstructor
public class MainController {
    private final CatRepository catRepository;

    @Operation(
            summary = "Добавление котика",
            description = "Создаёт нового кота на основе DTO и сохраняет в базу данных"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Кот успешно добавлен"),
            @ApiResponse(responseCode = "400", description = "Неверные данные", content = @Content)
    })
    @PostMapping("/api/add")
    public void addCat(@RequestBody CatDto catDto){
        log.info(
                "New row: " + catRepository.save(Cat.builder()
                .age(catDto.getAge())
                .weight(catDto.getWeight())
                .name(catDto.getName())
                .build())
        );
    }

    @Operation(
            summary = "Получить всех котов",
            description = "Возвращает список всех котиков из базы данных"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение списка котов",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cat.class)))
    })
    @SneakyThrows
    @GetMapping("/api/all")
    public List<Cat> getAll(){
        return catRepository.findAll();
    }

    @Operation(
            summary = "Получить кота по ID",
            description = "Ищет и возвращает кота по указанному ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Кот найден",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cat.class))),
            @ApiResponse(responseCode = "404", description = "Кот не найден", content = @Content)
    })
    @GetMapping("/api")
    public Cat getCat(@RequestParam int id){
        return catRepository.findById(id).orElseThrow();
    }

    @Operation(
            summary = "Удалить кота",
            description = "Удаляет кота по указанному ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Кот удалён"),
            @ApiResponse(responseCode = "404", description = "Кот не найден", content = @Content)
    })
    @DeleteMapping("/api")
    public void deleteCat(@RequestParam int id){
        catRepository.deleteById(id);
    }

    @Operation(
            summary = "Изменить кота",
            description = "Обновляет данные кота, если он существует"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Кот успешно обновлён",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cat.class))),
            @ApiResponse(responseCode = "404", description = "Кот не найден", content = @Content)
    })
    @PutMapping("/api/add")
    public String changeCat(@RequestBody Cat cat) {
        if (!catRepository.existsById(cat.getId())) {
            return "No such row";
        }
        return catRepository.save(cat).toString();
    }
}
