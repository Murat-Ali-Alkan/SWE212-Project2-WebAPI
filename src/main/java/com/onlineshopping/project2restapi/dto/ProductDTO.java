package com.onlineshopping.project2restapi.dto;

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
public class ProductDTO {
    private Long id;
    @NotBlank(message = "Product name cannot be blank or null")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Supplier name cannot be blank or null")
    private String supplier;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0.01")
    private BigDecimal price;


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProductDTO productDTO = (ProductDTO) obj;
        return Objects.equals(id,productDTO.getId()) && Objects.equals(name, productDTO.getName())
                && Objects.equals(supplier,productDTO.getSupplier()) && Objects.equals(price, productDTO.getPrice());
    }
}
