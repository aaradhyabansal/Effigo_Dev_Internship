package com.fullStack.FnB.Zwigato.Services;

import com.fullStack.FnB.Zwigato.Repository.DishRepository;
import com.fullStack.FnB.Zwigato.model.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DishService {
//    @Autowired
    private DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public void addDish(Dish dish,long id) {
        dishRepository.save(dish);
    }

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public Optional<Dish> getDishById(long id) {
        return dishRepository.findById(id);
    }

    public void updateDish(long id, Dish dish) {
        Dish oldDish=dishRepository.findById(id).orElse(null);
        if(oldDish!=null) {
            oldDish.setName(dish.getName());
            oldDish.setDescription(dish.getDescription());
            oldDish.setPrice(dish.getPrice());
            dishRepository.save(oldDish);
        }
    }
    public void deleteDish(long id) {
        dishRepository.deleteById(id);
    }
    public void deleteOrphanedDishes() {
        dishRepository.deleteOrphanedDishes();
    }
    public List<String> getDishesServedInRestaurant(long id) {
        System.out.println(dishRepository.findRestaurantByDishId(id));
        return dishRepository.findRestaurantByDishId(id);
    }
    public void patchUpdateDish(long id, Map<String, Object> updates) {
        Optional<Dish> optionalDish = dishRepository.findById(id);

        if (optionalDish.isPresent()) {
            Dish dish = optionalDish.get();

            for (Map.Entry<String, Object> entry : updates.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                switch (key) {
                    case "name":
                        dish.setName((String) value);
                        break;
                    case "description":
                        dish.setDescription((String) value);
                        break;
                    case "price":
                        if (value instanceof Number) {
                            dish.setPrice(((Number) value).intValue());
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid field: " + key);
                }
            }

            dishRepository.save(dish);
        } else {
            throw new RuntimeException("Dish not found with id: " + id);
        }
    }
}
