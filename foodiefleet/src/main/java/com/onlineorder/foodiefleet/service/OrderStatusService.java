package com.onlineorder.foodiefleet.service;

import com.onlineorder.foodiefleet.delivery.DeliveryRegistration;
import com.onlineorder.foodiefleet.order.MenuItemOrder;
import com.onlineorder.foodiefleet.order.Order;
import com.onlineorder.foodiefleet.order.OrderTrigger;
import com.onlineorder.foodiefleet.repository.DeliveryRepository;
import com.onlineorder.foodiefleet.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderStatusService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;
    public void orderTrigger(OrderTrigger orderTrigger) throws Exception {
        double totalAmount = 0;
        boolean flag=false;

        List<Order> orderList = orderRepository.findAll();
        for(Order ord: orderList){
            String orderNumber = ord.getOrderNumber().trim();
            if(orderNumber.equals(orderTrigger.getOrderNumber().trim())){
                if(orderTrigger.isProcessed() && !ord.getOrderStatus().isProcessed()){
                    processData(ord);
                    ord.getOrderStatus().setProcessed(true);
                }

                if(orderTrigger.isPicked() && !ord.getOrderStatus().isPicked()){
                   //selected delivery is
                    DeliveryRegistration dr = deliveryProc(orderTrigger);
                    if(dr != null){
                        displayDeliveryMan(dr);
                    }
                    kafkaTemplate.send("foodiefleet","Your order has been picked and will be deivered shortly to your place!!");
                    ord.getOrderStatus().setPicked(true);
                }

                if(orderTrigger.isDelivered() && !ord.getOrderStatus().isDelivered()){
                    kafkaTemplate.send("foodiefleet","Your order has been delived to you. Shop again!");
                    ord.getOrderStatus().setDelivered(true);
                }
                orderRepository.save(ord);
                display(ord);

                flag = true;
                break;
            }
        }
        if(!flag)
            System.out.println("Cannot find order number!");
    }

    private void processData(Order order) throws Exception {
        List<MenuItemOrder> itemlist = new ArrayList<>(order.getMenuitemorder());
        String firstName = order.getUser().getFirstName();
        String lastName = order.getUser().getLastName();
        String email = order.getUser().getEmail();

        double totalPrice=0;
        for(MenuItemOrder mio: itemlist)
            totalPrice += mio.getQuantity() * mio.getPrice();

        System.out.println("Total Price is: "+totalPrice);
        //java 8
        totalPrice = order.getMenuitemorder().stream()
                .map(e->e.getPrice()*e.getQuantity())
                .collect(Collectors.summingDouble(Double::doubleValue));

        System.out.println("Total Price is: "+totalPrice);

        double userBalance = order.getUserAccount().getAvailableBalance();

        if(totalPrice > userBalance){
            //send insuffient funds exception
            kafkaTemplate.send("foodiefleet","Insufficient funds!");
            throw new RuntimeException("Insuffient funds exception!");
        }
        else{
            double remainingBalance = order.getUserAccount().getAvailableBalance()-totalPrice;
            System.out.println("Original balance is : "+order.getUserAccount().getAvailableBalance());
            order.getUserAccount().setAvailableBalance(remainingBalance);
            System.out.println("Remaining balance is : "+remainingBalance);
            kafkaTemplate.send("foodiefleet","Hi "+ firstName +" your order has been prossessed!","email");
            //assign a delivery vehicle
        }
        orderRepository.save(order);
    }
    private DeliveryRegistration deliveryProc(OrderTrigger ot){
        List<DeliveryRegistration> allDelReg = deliveryRepository.findAll();
        for(DeliveryRegistration delReg: allDelReg){
            if(delReg.getDeliveryNumber().trim().equals(ot.getOrderNumber().trim())){
                System.out.println("Delivery Man has been spotted");
                return delReg;
            }
        }
        System.out.println("Delivery man has not been spotted");
        return null;
    }

    private void display(Order ord){

        System.out.println(ord.getOrderStatus().isDelivered());
        System.out.println(ord.getOrderStatus().isPicked());
        System.out.println(ord.getOrderNumber());
        //user data
        System.out.println(ord.getUser().getFirstName());
        System.out.println(ord.getUser().getLastName());
        System.out.println(ord.getUser().getEmail());
        System.out.println(ord.getUser().getRole());
        //account
        System.out.println(ord.getUserAccount().getAccountNumber());
        System.out.println(ord.getUserAccount().getAccountName());
        System.out.println(ord.getUserAccount().getAvailableBalance());
        //order number
        System.out.println(ord.getOrderNumber());
        System.out.println(ord.getDateCreated());
        //menu item order
        System.out.println(ord.getMenuitemorder().get(0).getItemName());
        System.out.println(ord.getMenuitemorder().get(0).getQuantity());
        System.out.println(ord.getMenuitemorder().get(0).getPrice());

        System.out.println(ord.getMenuitemorder().get(1).getItemName());
        System.out.println(ord.getMenuitemorder().get(1).getQuantity());
        System.out.println(ord.getMenuitemorder().get(1).getPrice());

        //
        System.out.println(ord.getOrderStatus().isProcessed());
        System.out.println(ord.getOrderStatus().isDelivered());
        System.out.println(ord.getOrderStatus().isPicked());
    }

    public void  displayDeliveryMan(DeliveryRegistration dr){
        System.out.println(dr.getDeliveryNumber());
        System.out.println(dr.getFirstName());
        System.out.println(dr.getLastName());
        System.out.println(dr.getEmail());
        System.out.println(dr.getDeliveryaccount().getAccountName());
        System.out.println(dr.getDeliveryaccount().getAvailableBalance());
        System.out.println(dr.getDeliveryaccount().getAccountNumber());

        System.out.println(dr.getDeliveryLocation().getState());
        System.out.println(dr.getDeliveryLocation().getCity());
        System.out.println(dr.getDeliveryLocation().getZip());
        System.out.println(dr.getDeliveryLocation().getStreet());
    }
}
