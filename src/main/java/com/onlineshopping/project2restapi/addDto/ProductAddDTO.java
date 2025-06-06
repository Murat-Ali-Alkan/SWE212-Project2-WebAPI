package com.onlineshopping.project2restapi.addDto;

import jakarta.validation.constraints.*;
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
    @Digits(integer = 8, fraction = 2, message = "Amount must have up to 8 integer digits and 2 fractional digits")
    private BigDecimal price;


}
