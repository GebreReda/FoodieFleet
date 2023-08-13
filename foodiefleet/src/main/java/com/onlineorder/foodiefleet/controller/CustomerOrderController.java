package com.onlineorder.foodiefleet.controller;

import com.onlineorder.foodiefleet.order.Order;
import com.onlineorder.foodiefleet.service.CustomerOrderService;
import com.onlineorder.foodiefleet.service.RestaurantService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class CustomerOrderController {
    private CustomerOrderService customerOrderService;
    private RestaurantService restaurantService;
    public CustomerOrderController(CustomerOrderService customerOrderService){
        this.customerOrderService = customerOrderService;
    }

    @PostMapping()
    public Order postOrder(@RequestBody Order order) throws Exception {
        return customerOrderService.placeOrder(order);
    }
}
