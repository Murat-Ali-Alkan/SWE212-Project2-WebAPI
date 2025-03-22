package com.onlineshopping.project2restapi.repository;

import com.onlineshopping.project2restapi.dto.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import com.onlineshopping.project2restapi.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query(value = "SELECT * FROM customers c WHERE lower(c.name) like lower(concat('%' , :name , '%')) " , nativeQuery = true)
    List<CustomerDTO> searchCustomersByName(@Param("name") String name);

    boolean existsByPhone(String phone);
}
