package com.onlineshopping.project2restapi.service;

import com.onlineshopping.project2restapi.addDto.OrderAddDTO;
import com.onlineshopping.project2restapi.dto.OrderDTO;
import com.onlineshopping.project2restapi.exception.ErrorMessages;
import com.onlineshopping.project2restapi.exception.ResourceNotFoundException;
import com.onlineshopping.project2restapi.model.Customer;
import com.onlineshopping.project2restapi.model.Order;
import com.onlineshopping.project2restapi.model.Product;
import com.onlineshopping.project2restapi.repository.CustomerRepository;
import com.onlineshopping.project2restapi.repository.OrderRepository;
import com.onlineshopping.project2restapi.repository.ProductRepository;
import com.onlineshopping.project2restapi.updateDto.OrderUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderService(CustomerRepository customerRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public OrderDTO getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ORDER_NOT_FOUND + ":" + id))
                .viewAsOrderDTOAll(); // Calisiyor mu bilmiyorum
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(Order::viewAsOrderWithCustomerAndProducts).toList();
    }

    public OrderDTO createOrder(OrderAddDTO OrderAddDTO) {

        Customer customer = customerRepository.findById(OrderAddDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.CUSTOMER_NOT_FOUND + ":" + OrderAddDTO.getCustomerId()));

        Product product = productRepository.findById(OrderAddDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND + ":" + OrderAddDTO.getProductId()));

        Order order =
                new Order(OrderAddDTO.getDate(),OrderAddDTO.getCity(),OrderAddDTO.getStatus(),customer,product);

        return orderRepository.save(order).viewAsOrderDTOAll();
    }

    public OrderDTO updateOrder(Long id, OrderUpdateDTO orderUpdateDTO) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ORDER_NOT_FOUND + ":" + id));

        Customer customer = customerRepository.findById(orderUpdateDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.CUSTOMER_NOT_FOUND + ":" + orderUpdateDTO.getCustomerId()));

        Product product = productRepository.findById(orderUpdateDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND + ":" + orderUpdateDTO.getProductId()));

        Order orderToUpdate = new Order(id,orderUpdateDTO.getDate(),orderUpdateDTO.getCity(),orderUpdateDTO.getStatus(),customer,product);

        return orderRepository.save(orderToUpdate).viewAsOrderDTOAll();
    }


    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ORDER_NOT_FOUND + ":" + id));

        orderRepository.delete(order);
    }

    public List<OrderDTO> searchOrdersByStatusService(String status){
        return orderRepository.searchOrdersByStatus(status)
                .stream()
                .map(Order::viewAsOrderWithCustomerAndProducts)
                .toList();
    }

}
