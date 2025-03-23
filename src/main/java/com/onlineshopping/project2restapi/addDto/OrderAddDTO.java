package com.onlineshopping.project2restapi.addDto;

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
public class OrderAddDTO {
    @NotNull(message = "Date cannot be null")
    private LocalDateTime date;

    @NotBlank(message = "City cannot be blank or null")
    @Size(min = 3, max = 32, message = "City name must be between 3 and 32 characters")
    private String city;

    @NotBlank(message = "Status cannot be blank or null")
    @Size(min = 3, max = 32, message = "Status must be between 3 and 32 characters")
    private String status;

    @NotNull(message = "CustomerId information is required")
    private Long customerId;

    @NotNull(message = "ProductId information is required")
    private Long productId;
}
