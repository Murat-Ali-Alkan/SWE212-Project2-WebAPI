package com.onlineshopping.project2restapi.model;

import com.onlineshopping.project2restapi.dto.CustomerDTO;
import com.onlineshopping.project2restapi.dto.OrderDTO;
import com.onlineshopping.project2restapi.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(name="city", nullable = false, length = 32)
    private String city;

    @Column(name = "status", nullable = false, length = 32)
    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Calisiyor mu bilmiyorum
    public OrderDTO viewAsOrderDTOAll() {
        CustomerDTO customerDTO = customer.viewAsCustomerDTO();

        ProductDTO productDTO = product.viewAsProductDTO();

        return new OrderDTO(id,date,city,status,customerDTO,productDTO);
    }

    public OrderDTO viewAsOrderDTO() {
        return new OrderDTO(id,date,city,status);
    }

    public OrderDTO viewAsOrderWithCustomerAndProducts() {
        CustomerDTO customerDTO = new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getPhone()
        );
        ProductDTO productDTO = new ProductDTO(
                product.getId(),
                product.getName(),
                product.getSupplier(),
                product.getPrice()
        );

        return new OrderDTO(id,date,city,status,customerDTO,productDTO);
    }


    public Order(LocalDateTime date, String city, String status, Customer customer, Product product) {
        this.date = date;
        this.city = city;
        this.status = status;
        this.customer = customer;
        this.product = product;
    }

    public Order(Long id, LocalDateTime date, String city, String status) {
        this.id = id;
        this.date = date;
        this.city = city;
        this.status = status;
    }
}
