package com.rookies.ecomerce_services.repository;

import com.rookies.ecomerce_services.entity.Customer;
import com.rookies.ecomerce_services.entity.ReceiverAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceiverAddressRepository extends JpaRepository<ReceiverAddress, Long> {
    public List<ReceiverAddress> findByCustomer(Customer customer);

    public  List<ReceiverAddress> findAllByCustomer(Customer customer);

    public Optional<ReceiverAddress> findByCustomerAndIsDefaultTrue(Customer customer);


}
