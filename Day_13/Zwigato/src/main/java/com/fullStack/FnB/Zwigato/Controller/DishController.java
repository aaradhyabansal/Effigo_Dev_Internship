package com.fullStack.FnB.Zwigato.Controller;

import com.fullStack.FnB.Zwigato.Services.DishService;
import com.fullStack.FnB.Zwigato.model.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dishes")
public class DishController {
//    @Autowired
    private DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public List<Dish> getAllDishes() {
        return dishService.getAllDishes();
    }

    @PostMapping("/addnewdish/{id}")
    public void addDish( @PathVariable long id,@RequestBody Dish dish) {
        dishService.addDish(dish,id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDish(@PathVariable long id) {
        dishService.deleteDish(id);
    }

    @GetMapping("/{id}")
    public Optional<Dish> getDishById(@PathVariable long id) {
        return dishService.getDishById(id);
    }

    @PutMapping("/update/{id}")
    public void updateDish(@PathVariable long id, @RequestBody Dish dish) {
        dishService.updateDish(id,dish);
    }

    @DeleteMapping("/refresh")
    public void deleteOrphanDishes() {
        dishService.deleteOrphanedDishes();
    }

    @GetMapping("/{id}/restaurant")
    public List<String> getDishInRestaurant(@PathVariable long id) {
        return dishService.getDishesServedInRestaurant(id);
    }

}
