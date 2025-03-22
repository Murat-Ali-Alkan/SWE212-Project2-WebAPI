package com.onlineshopping.project2restapi.controller;

import com.onlineshopping.project2restapi.addDto.ProductAddDTO;
import com.onlineshopping.project2restapi.dto.CustomerDTO;
import com.onlineshopping.project2restapi.dto.ProductDTO;
import com.onlineshopping.project2restapi.service.ProductService;
import com.onlineshopping.project2restapi.updateDto.ProductUpdateDTO;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final static Logger logger = LogManager.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        logger.info("Get all products");
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long id) {

        //if (id == null || id == 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        logger.info("Get product by id ",id);
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductAddDTO productAddDTO) {
        //if(productDTO== null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        logger.info("Add product ",productAddDTO.toString());
        return new ResponseEntity<>(productService.createProduct(productAddDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") Long id, @Valid @RequestBody ProductUpdateDTO productUpdateDTO) {
        //if (id == null || id == 0 || productDTO == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        logger.info("Update product ",id);
        return new ResponseEntity<>(productService.updateProduct(id, productUpdateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        //if (id == null || id == 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        logger.info("Delete product ",id);
        productService.deleteProduct(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<ProductDTO> searchProductsByNameController(@RequestParam String name){
        return productService.searchProductsByNameService(name);
    }

}
