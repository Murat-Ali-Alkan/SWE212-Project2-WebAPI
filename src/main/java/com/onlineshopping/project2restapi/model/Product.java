package com.onlineshopping.project2restapi.model;

import com.onlineshopping.project2restapi.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=16, nullable=false)
    private String name;

    @Column(length=16, nullable=false)
    private String supplier;

    @Column(precision = 10, scale = 2 ,nullable = false)
    private BigDecimal price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;


    public ProductDTO viewAsProductDTO(){
        return new ProductDTO(id, name, supplier, price);
    }

    public Product(String name, String supplier, BigDecimal price) {
        this.name = name;
        this.supplier = supplier;
        this.price = price;
    }

    public Product(Long id, String name, String supplier, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.supplier = supplier;
        this.price = price;
    }
}