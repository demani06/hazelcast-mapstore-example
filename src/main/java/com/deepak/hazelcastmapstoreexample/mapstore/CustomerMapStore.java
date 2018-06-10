package com.deepak.hazelcastmapstoreexample.mapstore;

import com.deepak.hazelcastmapstoreexample.domain.Customer;
import com.hazelcast.core.MapStore;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class CustomerMapStore implements MapStore<Long, Customer> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void store(Long aLong, Customer customer) {
        System.out.println("Storing the object&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        em.persist(customer);
    }

    @Override
    public void storeAll(Map<Long, Customer> map) {
        map.forEach(this::store);
    }

    @Override
    public void delete(Long key) {
        em.remove(em.getReference(Customer.class, key));
    }

    @Override
    public void deleteAll(Collection<Long> collection) {
        em.createNamedQuery(Customer.DELETE_BY_KEYS)
                .setParameter("ids", collection)
                .executeUpdate();

    }

    @Override
    public Customer load(Long key) {
        return em.find(Customer.class, key);
    }

    @Override
    public Map<Long, Customer> loadAll(Collection<Long> collection) {
        System.out.println("loadll the object&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        return em.createNamedQuery(Customer.FIND_BY_KEYS, Customer.class)
                .setParameter("ids", collection)
                .getResultList()
                .stream()
                .collect(Collectors.toMap(Customer::getId, Function.identity()));
    }

    @Override
    public Iterable<Long> loadAllKeys() {
        return em.createNamedQuery(Customer.FIND_ALL_KEYS, Long.class).getResultList();
    }
}
