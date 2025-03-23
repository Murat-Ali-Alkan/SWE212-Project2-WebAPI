package com.onlineshopping.project2restapi.addDto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductAddDTO {
    @NotBlank(message = "Product name cannot be blank or null")
    @Size(min = 2, max = 16, message = "Product name must be between 2 and 16 characters")
    private String name;

    @NotBlank(message = "Supplier name cannot be blank or null")
    @Size(min = 2, max = 16, message = "Supplier name must be between 2 and 16 characters")
    private String supplier;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0.01")
    private BigDecimal price;

}
