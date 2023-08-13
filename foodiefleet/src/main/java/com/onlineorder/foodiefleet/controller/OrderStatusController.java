package com.onlineorder.foodiefleet.controller;

import com.onlineorder.foodiefleet.order.OrderTrigger;
import com.onlineorder.foodiefleet.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class OrderStatusController {
    private OrderStatusService orderStatusService;
    @Autowired
    public OrderStatusController(OrderStatusService orderStatusService){
        this.orderStatusService=orderStatusService;
    }

    @PostMapping
    public void orderTrigger(@RequestBody OrderTrigger orderTrigger) throws Exception {
        orderStatusService.orderTrigger(orderTrigger);
    }

//    @PostMapping
//    public ResponseEntity<OrderTrigger> orderTrigger(@RequestBody OrderTrigger orderTrigger){
//        OrderTrigger orderTrigger1 = orderStatusService.orderTrigger(orderTrigger);
//        if(orderTrigger1!=null)
//            return new ResponseEntity<>(orderTrigger1, HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
}

