package com.onlineorder.foodiefleet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.onlineorder.foodiefleet.delivery.DeliveryRegistration;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryRegistration, Long> {
}
