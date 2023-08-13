package com.onlineorder.foodiefleet.repository;

import com.onlineorder.foodiefleet.order.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
