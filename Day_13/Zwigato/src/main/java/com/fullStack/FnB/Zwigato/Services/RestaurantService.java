package com.fullStack.FnB.Zwigato.Services;

import com.fullStack.FnB.Zwigato.Repository.RestaurantRepository;
import com.fullStack.FnB.Zwigato.model.Dish;
import com.fullStack.FnB.Zwigato.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
//    @Autowired
    private  RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAllRestaurants(){
        return restaurantRepository.findAll();
    }
    public void addRestaurant(String name){
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurantRepository.save(restaurant);
    }
    public Restaurant getRestaurantById(long id){
        return restaurantRepository.findById(id).orElse(null);
    }

    public void deleteRestaurant(long id){
        restaurantRepository.deleteById(id);
    }

    public void updateRestaurant(long id,String name){
        Restaurant oldRestaurant = restaurantRepository.findById(id).orElse(null);
        if(oldRestaurant != null){
            oldRestaurant.setName(name);
            restaurantRepository.save(oldRestaurant);
        }
    }
    public List<Dish> getDishesByRestaurant(long id){
        return restaurantRepository.getDishesByRestaurantId(id);
    }
}
