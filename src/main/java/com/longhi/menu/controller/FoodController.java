package com.longhi.menu.controller;

import com.longhi.menu.food.Food;
import com.longhi.menu.food.FoodRepository;
import com.longhi.menu.food.FoodRequestDTO;
import com.longhi.menu.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController {

    @Autowired
    private FoodRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveFood(@RequestBody FoodRequestDTO data) {
        Food foodData = new Food(data);
        repository.save(foodData);
        return;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<FoodResponseDTO> getAll() {
        return repository.findAll().stream().map(FoodResponseDTO::new).toList();
    }
}
