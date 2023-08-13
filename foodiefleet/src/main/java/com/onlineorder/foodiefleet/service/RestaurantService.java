package com.onlineorder.foodiefleet.service;

import com.onlineorder.foodiefleet.entity.Restaurant;
import com.onlineorder.foodiefleet.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    private RestaurantRepository restaurantRepository;
    @Autowired
    RestaurantService(RestaurantRepository addMenuRepository){
        this.restaurantRepository=addMenuRepository;
    }
    public Restaurant createRestaurantMenu(Restaurant restaurant) {
        //throw new RuntimeException(("Restaurant Menu has been created, throwing an excpeprion!"));

        return restaurantRepository.save(restaurant);
    }
    public Optional<Restaurant> getRestaurantMenu(long id){
        return restaurantRepository.findById(id);
    }
    public List<Restaurant> getAllMenu(){
        return restaurantRepository.findAll();
    }
    public List<Restaurant> searchRestaurants(String cuisineType, String city) {
        if (cuisineType != null && city != null)
            return restaurantRepository.findByCuisineTypeAndRestLocationCity(cuisineType, city);
        else if (cuisineType != null)
            return restaurantRepository.findByCuisineType(cuisineType);
        else if (city != null)
            return restaurantRepository.findByRestLocationCity(city);
        else
            return restaurantRepository.findAll();
    }

    //implement Put
    public Restaurant updateRestaurant(Long id, Restaurant restaurant){
        Restaurant fetchedRestaurant = restaurantRepository.findById(id).orElse(null);
        if(fetchedRestaurant  != null){
           restaurant.setId(id);
           return restaurantRepository.save(restaurant);
        }
        return null;
    }

    //patch
    public Restaurant partialUpdateRestaurant(Long id, Restaurant restaurant){
        Restaurant fetchedRestaurant = restaurantRepository.findById(id).orElse(null);
        if(fetchedRestaurant  != null){
            if(fetchedRestaurant.getId() != restaurant.getId())
                fetchedRestaurant.setId(id);
            else if(fetchedRestaurant.getRestaurantName()!=restaurant.getRestaurantName())
                fetchedRestaurant.setRestaurantName(restaurant.getRestaurantName());
            else if(fetchedRestaurant.getCuisineType() != restaurant.getCuisineType())
                fetchedRestaurant.setCuisineType(restaurant.getCuisineType());
            else if(fetchedRestaurant.getRestLocation().getStreet()!=restaurant.getRestLocation().getStreet())
                fetchedRestaurant.getRestLocation().setStreet(restaurant.getRestLocation().getStreet());
            else if(fetchedRestaurant.getRestaurantMenu().getMenuName()!=restaurant.getRestaurantMenu().getMenuName())
                fetchedRestaurant.getRestaurantMenu().setMenuName(restaurant.getRestaurantMenu().getMenuName());
            else if(fetchedRestaurant.getRestaurantMenu().getMenuSectionList().get(0).getFoodSectionName()!=restaurant.getRestaurantMenu().getMenuSectionList().get(0).getFoodSectionName())
                fetchedRestaurant.getRestaurantMenu().getMenuSectionList().get(0).setFoodSectionName(restaurant.getRestaurantMenu().getMenuSectionList().get(0).getFoodSectionName());
            if(fetchedRestaurant.getRestaurantMenu().getMenuSectionList().get(0).getFoodMenuList().get(0).getFoodPrice() != restaurant.getRestaurantMenu().getMenuSectionList().get(0).getFoodMenuList().get(0).getFoodPrice())
                fetchedRestaurant.getRestaurantMenu().getMenuSectionList().get(0).getFoodMenuList().get(0).setFoodPrice(restaurant.getRestaurantMenu().getMenuSectionList().get(0).getFoodMenuList().get(0).getFoodPrice());
            return restaurantRepository.save(fetchedRestaurant);
        }
        return null;
    }
    //delete Restaurant
    public Boolean deleteRestaurant(@PathVariable long id){
        Restaurant existsRestaurant = restaurantRepository.findById(id).orElse(null);
        if(existsRestaurant != null){
            restaurantRepository.delete(existsRestaurant);
            return true;
        }
           return false;
    }
}
