package com.onlineorder.foodiefleet.service;

import com.onlineorder.foodiefleet.order.User;
import com.onlineorder.foodiefleet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public User createUser(User user){
        return userRepository.save(user);
    }
    public void deleteUser(User user){
       userRepository.delete(user);
    }
}
