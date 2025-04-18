package com.onlineshopping.project2restapi.controller;

import com.onlineshopping.project2restapi.addDto.CustomerAddDTO;
import com.onlineshopping.project2restapi.dto.CustomerDTO;
import com.onlineshopping.project2restapi.service.CustomerService;
import com.onlineshopping.project2restapi.updateDto.CustomerUpdateDTO;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final static Logger logger = LogManager.getLogger(CustomerController.class);
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        logger.info("Get all customers");
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("id") Long id) {

        //if (id == null || id == 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        logger.info("Get customer by id ",id);
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CustomerDTO> addCustomer(@Valid @RequestBody CustomerAddDTO customerAddDTO) {
        //if(customerDTO== null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        logger.info("Add customer ",customerAddDTO.toString());
        return new ResponseEntity<>(customerService.createCustomer(customerAddDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") Long id, @Valid @RequestBody CustomerUpdateDTO customerUpdateDTO) {
        //if (id == null || id == 0 || customerDTO == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        logger.info("Update customer ",id);
        return new ResponseEntity<>(customerService.updateCustomer(id, customerUpdateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id) {
        //if (id == null || id == 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        logger.info("Delete customer ",id);
        customerService.deleteCustomer(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<CustomerDTO> searchCustomersByName(@RequestParam String name){
        return customerService.searchCustomersByNameService(name);
    }
}
