package com.longhi.menu.controller;

import com.longhi.menu.food.Food;
import com.longhi.menu.food.FoodRepository;
import com.longhi.menu.food.FoodRequestDTO;
import com.longhi.menu.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<FoodResponseDTO> updateFood(@PathVariable Long id, @RequestBody FoodRequestDTO data) {
        Optional<Food> optionalFood = repository.findById(id);
        if (optionalFood.isPresent()) {
            Food existingFood = optionalFood.get();

            // Updates only fields that are not null in the DTO (Data transfer object)
            if (data.title() != null) {
                existingFood.setTitle(data.title());
            }
            if (data.image() != null) {
                existingFood.setImage(data.image());
            }
            if (data.price() != null) {
                existingFood.setPrice(data.price());
            }
            repository.save(existingFood);
            return ResponseEntity.ok(new FoodResponseDTO(existingFood));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        Optional<Food> optionalFood = repository.findById(id);
        if (optionalFood.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
