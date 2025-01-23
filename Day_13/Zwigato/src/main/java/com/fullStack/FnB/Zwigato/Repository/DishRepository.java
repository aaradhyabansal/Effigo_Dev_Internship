package com.fullStack.FnB.Zwigato.Repository;

import com.fullStack.FnB.Zwigato.model.Dish;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    @Transactional
    @Modifying
    @Query("DELETE from Dish d where d.restaurant IS NULL")
    void deleteOrphanedDishes();

    @Query("SELECT DISTINCT r.name FROM Restaurant r JOIN r.dishes d WHERE d.id = :id")
    List<String> findRestaurantByDishId(Long id);

}
