package com.deepak.hazelcastmapstoreexample.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
public class CustomerAddress implements Serializable {

    @Id
    private long addressId;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
}
