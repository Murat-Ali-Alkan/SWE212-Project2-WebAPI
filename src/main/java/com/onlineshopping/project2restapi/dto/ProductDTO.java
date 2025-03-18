package com.onlineshopping.project2restapi.dto;

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
    private String name;
    private String supplier;
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
