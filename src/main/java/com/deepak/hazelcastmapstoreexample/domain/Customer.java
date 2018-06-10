package com.deepak.hazelcastmapstoreexample.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = Customer.FIND_ALL_KEYS, query = "SELECT customer.id FROM Customer customer"),
        @NamedQuery(name = Customer.FIND_BY_KEYS, query = "SELECT customer FROM Customer customer WHERE customer.id IN :ids"),
        @NamedQuery(name = Customer.DELETE_BY_KEYS, query = "DELETE FROM Customer customer WHERE customer.id in :ids")
})
@Data
@AllArgsConstructor
public class Customer implements Serializable {

    public static final String FIND_ALL_KEYS = "Customer.findAllKeys";
    public static final String FIND_BY_KEYS = "Customer.findByKeys";
    public static final String DELETE_BY_KEYS = "Customer.deleteByKeys";

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String description;

    private CustomerAddress customerAddress;

    private Customer() {}



    public Customer(String firstName, String lastName, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
    }
}
