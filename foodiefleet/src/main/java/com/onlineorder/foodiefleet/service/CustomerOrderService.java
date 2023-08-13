package com.onlineorder.foodiefleet.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.onlineorder.foodiefleet.JacksonConfig.JacksonConfig;
import com.onlineorder.foodiefleet.order.Order;
import com.onlineorder.foodiefleet.repository.OrderRepository;
import com.onlineorder.foodiefleet.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CustomerOrderService {
    private OrderRepository orderRepository;
    @Autowired
    private JacksonConfig jacksonConfig;
    private RestaurantRepository restaurantRepository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    public CustomerOrderService(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }

    public Order placeOrder(Order order) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        Order order1 = new Order();
        order1.setOrderNumber(UUID.randomUUID().toString());
        order1.setUser(order.getUser());
        order1.setUserAccount(order.getUserAccount());
        order1.setDeliveryStatus("pending");
        order1.setOrderStatus(order.getOrderStatus());
        order1.setDateCreated(order.getDateCreated());
        order1.setMenuitemorder(order.getMenuitemorder());

        System.out.println("From Order Service"+order.toString());
       Order ord = orderRepository.save(order1);
       //if(checkBalance(ord)) {
           //get user email
           //String userEmail = order.getUser().getEmail();
           kafkaTemplate.send("foodiefleet", "We have successfully received your order!", "gebre15tsegay@gmail.com");
           //ProcessOrder(ord);
           //DeliverOrder(ord);
       //}
        //else{
           //kafkaTemplate.send("foodiefleet", "Your funds are insuffucient!");
            //throw new Exception("Insufficient funds");
       //}
       return order;
    }
    //check user available balance
//    public boolean checkBalance(Order order){
//        double usergBalance = order.getAccount().getAvailableBalance();
//        //double totalItemPrice = order.getMyorder().getPrice();
//        return (usergBalance - totalItemPrice >= 0) ? true : false;
//
//    }
    //start prcessing the order
    @Transactional
    public void ProcessOrder(Order order){
        double totalAmount = 0;
        //double itemPrice = order.getMyorder().getPrice();
        //double remainingBalance = order.getAccount().getAvailableBalance() - itemPrice;

        //totalAmount += itemPrice;
        //order.getAccount().setAvailableBalance(remainingBalance) ;
        //order.setOrderStatus("processed");
        orderRepository.save(order);
        System.out.println("Total Amount is: "+totalAmount);
        //use kafka for messaging
    }

    public void  DeliverOrder(Order order){
        if(order.getDeliveryStatus()=="pending"){
            order.setDeliveryStatus("Loaded");
            kafkaTemplate.send("foodiefleet","Your order is ready and loaded to a delivery vehicle");
            orderRepository.save(order);
        }
    }
}
