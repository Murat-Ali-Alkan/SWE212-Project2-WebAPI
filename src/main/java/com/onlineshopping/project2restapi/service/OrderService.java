package com.onlineshopping.project2restapi.service;

import com.onlineshopping.project2restapi.dto.OrderDTO;
import com.onlineshopping.project2restapi.exception.ErrorMessages;
import com.onlineshopping.project2restapi.exception.ResourceNotFoundException;
import com.onlineshopping.project2restapi.model.Customer;
import com.onlineshopping.project2restapi.model.Order;
import com.onlineshopping.project2restapi.model.Product;
import com.onlineshopping.project2restapi.repository.CustomerRepository;
import com.onlineshopping.project2restapi.repository.OrderRepository;
import com.onlineshopping.project2restapi.repository.ProductRepository;
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
        return orderRepository.findAll().stream().map(Order::viewAsOrderDTO).toList();
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {

        Customer customer = customerRepository.findById(orderDTO.getCustomer().getId())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.CUSTOMER_NOT_FOUND + ":" + orderDTO.getCustomer().getId()));

        Product product = productRepository.findById(orderDTO.getProduct().getId())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND + ":" + orderDTO.getProduct().getId()));

        Order order =
                new Order(orderDTO.getDate(),orderDTO.getCity(),orderDTO.getStatus(),customer,product);

        return orderRepository.save(order).viewAsOrderDTOAll();
    }

    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ORDER_NOT_FOUND + ":" + id));

        Customer customer = customerRepository.findById(orderDTO.getCustomer().getId())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.CUSTOMER_NOT_FOUND + ":" + orderDTO.getCustomer().getId()));

        Product product = productRepository.findById(orderDTO.getProduct().getId())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND + ":" + orderDTO.getProduct().getId()));

        Order orderToUpdate = new Order(id,orderDTO.getDate(),orderDTO.getCity(),orderDTO.getStatus(),customer,product);

        return orderRepository.save(orderToUpdate).viewAsOrderDTOAll();
    }


    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ORDER_NOT_FOUND + ":" + id));

        orderRepository.delete(order);
    }

}
