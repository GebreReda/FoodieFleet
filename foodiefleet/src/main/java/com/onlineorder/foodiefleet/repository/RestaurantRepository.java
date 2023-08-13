package com.onlineorder.foodiefleet.repository;

import com.onlineorder.foodiefleet.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByCuisineType(String cuisineType);
    List<Restaurant> findByRestLocationCity(String city);
    List<Restaurant> findByCuisineTypeAndRestLocationCity(String cuisineType,String city);
 }
