package com.onlineorder.foodiefleet.service;

import com.onlineorder.foodiefleet.delivery.DeliveryRegistration;
import com.onlineorder.foodiefleet.repository.DeliveryRepository;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeliveryRegService {
    private DeliveryRepository deliveryRepository;
    @Autowired
    public DeliveryRegService(DeliveryRepository deliveryRepository){
        this.deliveryRepository=deliveryRepository;
    }


    public void addDeliveryMen(DeliveryRegistration delivery){
       delivery.setDeliveryNumber(UUID.randomUUID().toString());
        deliveryRepository.save(delivery);
    }
}
