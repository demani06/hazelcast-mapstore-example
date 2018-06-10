package com.deepak.hazelcastmapstoreexample.controller;


import com.deepak.hazelcastmapstoreexample.domain.Customer;
import com.deepak.hazelcastmapstoreexample.domain.CustomerAddress;
import com.deepak.hazelcastmapstoreexample.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.PUT, path = "/customer")
    public String addCustomer(){

        CustomerAddress customerAddress = new CustomerAddress(1, "Addr1","Addr2","3","4");

        Customer customer1= new Customer(1L, "Deepak", "Emani","Developer",customerAddress);
        Customer customer2= new Customer(2L, "Shubham", "Gill","Cricketer",customerAddress);
        Customer customer3= new Customer(3L, "Vasu", "Veerla","Manager",customerAddress);
        customerService.save(customer1);
        customerService.save(customer2);
        customerService.save(customer3);
        return "saved Customesr";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/customer")
    public Customer getCustomer(){
        return customerService.getCustomer(1);
    }

}
