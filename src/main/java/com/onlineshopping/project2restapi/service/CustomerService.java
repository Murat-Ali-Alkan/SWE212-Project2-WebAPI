package com.onlineshopping.project2restapi.service;

import com.onlineshopping.project2restapi.dto.CustomerDTO;
import com.onlineshopping.project2restapi.exception.ErrorMessages;
import com.onlineshopping.project2restapi.exception.ResourceAlreadyExistsException;
import com.onlineshopping.project2restapi.exception.ResourceNotFoundException;
import com.onlineshopping.project2restapi.model.Customer;
import com.onlineshopping.project2restapi.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDTO getCustomerById(Long id){
       return customerRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(ErrorMessages.CUSTOMER_NOT_FOUND + ":"+id))
                .viewAsCustomerDTO();
    }

    public List<CustomerDTO> getAllCustomers(){
        return customerRepository.findAll().stream().map(Customer::viewAsCustomerDTO).toList();
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO){

        // Customer'i eklemden once regexle kontrol yapılabilir

        Customer customer = new Customer(customerDTO.getName(),customerDTO.getAddress(),customerDTO.getPhone());

        return customerRepository.save(customer).viewAsCustomerDTO();
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO){
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            Customer customerToUpdate = new Customer(id,customerDTO.getName(),customerDTO.getAddress(),customerDTO.getPhone());

            // Customer'i update etmeden once regexle kontrol yapılabilir

            return customerRepository.save(customerToUpdate).viewAsCustomerDTO();
        }
        else{
            throw new ResourceNotFoundException(ErrorMessages.CUSTOMER_NOT_FOUND + ":" + id);
        }
    }

    public void deleteCustomer(Long id){

        customerRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(ErrorMessages.CUSTOMER_NOT_FOUND + ":" + id));

        customerRepository.deleteById(id);
    }

}
