package com.deepak.hazelcastmapstoreexample.service;

import com.deepak.hazelcastmapstoreexample.domain.Customer;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.mapreduce.aggregation.Aggregations;
import com.hazelcast.mapreduce.aggregation.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CustomerService {


    @Autowired
    private HazelcastInstance hazelcastInstance;


    @SuppressWarnings("unchecked")
    private IMap<Long, Customer> getSampleMap() {
        return hazelcastInstance.getMap("customersMap");
    }


    public void save(Customer customer) {
        getSampleMap().putIfAbsent(customer.getId(), customer);
    }

    public Customer getCustomer(long customerId){
        return getSampleMap().get(customerId);
    }

    public Long sum() {
        return getSampleMap().aggregate(Supplier.all(Customer::getId), Aggregations.longSum());
    }
}
