package com.fullStack.FnB.Zwigato.Controller;

import com.fullStack.FnB.Zwigato.Services.RestaurantService;
import com.fullStack.FnB.Zwigato.model.Dish;
import com.fullStack.FnB.Zwigato.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
//    @Autowired
    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants(){
        return restaurantService.getAllRestaurants();
    }
    @PostMapping("/addrestaurant/{name}")
    public void     addRestaurant(@PathVariable String name){
        System.out.println(name + " added");
        restaurantService.addRestaurant(name);
    }
    @GetMapping("/getrestaurant/{id}")
    public Restaurant getRestaurant(@PathVariable Long id){
        return restaurantService.getRestaurantById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRestaurant(@PathVariable Long id){
        restaurantService.deleteRestaurant(id);
    }

    @PutMapping("/{id}/update/{name}")
    public void updateRestaurant(@PathVariable Long id, @PathVariable String name){
        restaurantService.updateRestaurant(id, name);
    }

    @GetMapping("/{id}/dishes")
    public List<Dish> getDishes(@PathVariable Long id){
        return restaurantService.getDishesByRestaurant(id);

    }
}
