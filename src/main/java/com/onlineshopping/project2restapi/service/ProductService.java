package com.onlineshopping.project2restapi.service;

import com.onlineshopping.project2restapi.addDto.ProductAddDTO;
import com.onlineshopping.project2restapi.dto.ProductDTO;
import com.onlineshopping.project2restapi.exception.DuplicateSupplierNameException;
import com.onlineshopping.project2restapi.exception.ErrorMessages;
import com.onlineshopping.project2restapi.exception.ResourceNotFoundException;
import com.onlineshopping.project2restapi.model.Product;
import com.onlineshopping.project2restapi.repository.ProductRepository;
import com.onlineshopping.project2restapi.updateDto.ProductUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO getProductById(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND + ":" + id))
                .viewAsProductDTO();

    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(Product::viewAsProductDTO).toList();
    }


    public ProductDTO createProduct(ProductAddDTO productAddDTO) {

        Product product = new Product(productAddDTO.getName(),productAddDTO.getSupplier(),productAddDTO.getPrice());

        if(productRepository.checkDuplicateSupplierNamePair(productAddDTO.getSupplier(),productAddDTO.getName())){
            throw new DuplicateSupplierNameException("A product with Supplier '" + productAddDTO.getSupplier()
                    + "' and Product name '" + productAddDTO.getName() + "' already exists");
        }

        return productRepository.save(product).viewAsProductDTO();

    }

    public ProductDTO updateProduct(Long id, ProductUpdateDTO productUpdateDTO) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {

            Product productToUpdate = new Product(id,productUpdateDTO.getName(),productUpdateDTO.getSupplier(),productUpdateDTO.getPrice());


            return productRepository.save(productToUpdate).viewAsProductDTO();
        }

        throw new ResourceNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND + ":" + id);

    }

    public void deleteProduct(Long id) {
        productRepository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND + ":" + id));

        productRepository.deleteById(id);
    }

    public List<ProductDTO> searchProductsByNameService(String name){
        List<Product> repoProducts = productRepository.searchProductsByName(name);
        return repoProducts
                .stream()
                .map(Product::viewAsProductDTO)
                .toList();
    }
}
