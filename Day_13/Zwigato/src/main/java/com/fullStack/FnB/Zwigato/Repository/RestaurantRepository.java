package com.fullStack.FnB.Zwigato.Repository;

import com.fullStack.FnB.Zwigato.model.Dish;
import com.fullStack.FnB.Zwigato.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query("SELECT r.dishes FROM Restaurant r WHERE r.id = :id")
    List<Dish> getDishesByRestaurantId(Long id);
}
