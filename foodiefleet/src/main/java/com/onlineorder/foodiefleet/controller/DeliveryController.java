package com.onlineorder.foodiefleet.controller;

import com.onlineorder.foodiefleet.delivery.DeliveryRegistration;
import com.onlineorder.foodiefleet.service.DeliveryRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/delivery")
public class DeliveryController {
    private DeliveryRegService deliveryService;
    @Autowired
    public DeliveryController(DeliveryRegService deliveryService){
        this.deliveryService=deliveryService;
    }
    @PostMapping
    public void saveDeliveryMen(@RequestBody DeliveryRegistration delivery){
        deliveryService.addDeliveryMen(delivery);
    }
}
