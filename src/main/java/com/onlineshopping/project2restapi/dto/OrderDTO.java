package com.onlineshopping.project2restapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private Long id;
    @NotNull(message = "Date cannot be null")
    private LocalDateTime date;

    @NotBlank(message = "City cannot be blank or null")
    @Size(min = 3, max = 32, message = "City name must be between 3 and 32 characters")
    private String city;

    @NotBlank(message = "Status cannot be blank or null")
    @Size(min = 3, max = 32, message = "Status must be between 3 and 32 characters")
    private String status;

    @NotNull(message = "Customer information is required")
    private CustomerDTO customer;

    @NotNull(message = "Product information is required")
    private ProductDTO product;

    public OrderDTO(Long id, LocalDateTime date, String city, String status) {
        this.id = id;
        this.date = date;
        this.city = city;
        this.status = status;
    }


}
