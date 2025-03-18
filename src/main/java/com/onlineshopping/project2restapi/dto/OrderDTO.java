package com.onlineshopping.project2restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private Long id;
    private LocalDateTime date;
    private String city;
    private String status;
    private CustomerDTO customer;
    private ProductDTO product;

    public OrderDTO(Long id, LocalDateTime date, String city, String status) {
        this.id = id;
        this.date = date;
        this.city = city;
        this.status = status;
    }
}
