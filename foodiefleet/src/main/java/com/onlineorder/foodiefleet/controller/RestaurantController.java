package com.onlineorder.foodiefleet.controller;


import com.onlineorder.foodiefleet.config.TrackExecutionTime;
import com.onlineorder.foodiefleet.entity.Restaurant;
import com.onlineorder.foodiefleet.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    private RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService addMenuService) {
        this.restaurantService = addMenuService;
    }

    @PostMapping("/addmenu")
    @TrackExecutionTime
    public Restaurant ceateAMenu(@RequestBody Restaurant restaurant) {
        return restaurantService.createRestaurantMenu(restaurant);
    }

    //get all menu
    @GetMapping()
    public List<Restaurant> findAllMenu() {
        return restaurantService.getAllMenu();
    }

    @GetMapping("/{id}")
    @TrackExecutionTime
    public Optional<Restaurant> findAMenu(@PathVariable long id) {
        return restaurantService.getRestaurantMenu(id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurants(@RequestParam(required = false) String cuisineType,
                                                              @RequestParam(required = false) String city) {
        List<Restaurant> restaurants = restaurantService.searchRestaurants(cuisineType, city);
        if (!restaurants.isEmpty()) {
            return new ResponseEntity<>(restaurants, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Put
    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable long id, @RequestBody Restaurant restaurant){
        Restaurant fetchedRestaurant = restaurantService.updateRestaurant(id, restaurant);
        if(fetchedRestaurant != null)
            return new ResponseEntity<>(fetchedRestaurant, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //patch
    @PatchMapping("/{id}")
    public ResponseEntity<Restaurant> partialUpdateRestaurant(@PathVariable long id, @RequestBody Restaurant restaurant){
        Restaurant fetchedRestaurant = restaurantService.partialUpdateRestaurant(id, restaurant);
        if(fetchedRestaurant!=null)
            return new ResponseEntity<>(fetchedRestaurant, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //delete restaurant
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable long id){
        boolean success = restaurantService.deleteRestaurant(id);
        if(success)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
