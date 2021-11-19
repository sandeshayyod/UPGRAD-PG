package com.example.aopdemo.controller;

import com.example.aopdemo.dto.ProductDto;
import com.example.aopdemo.entity.Product;
import com.example.aopdemo.exception.ProductsDataAccessException;
import com.example.aopdemo.model.ErrorModel;
import com.example.aopdemo.service.ProductsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/aop-v1")
public class ProductsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProductsService service;

    @PreAuthorize("hasAnyAuthority('READ')")
    @GetMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> getProductById(@PathVariable(name = "id") int id) {
        Product product = service.getProductBasedOnId(id);
        ProductDto productDto = mapper.map(product, ProductDto.class);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('READ')")
    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products = service.getAllProducts();
        List<ProductDto> productDtos = products.stream()
                .map(product -> mapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('WRITE')")
    @PostMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        Product product = mapper.map(productDto, Product.class);
        service.saveProduct(product);
        return new ResponseEntity<>(productDto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('WRITE')")
    @PutMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> updateProduct(@PathVariable(name = "id") int id, @RequestBody ProductDto productDto) {
        Product product = mapper.map(productDto, Product.class);
        service.updateProduct(id, product);
        return new ResponseEntity<>(productDto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('WRITE')")
    @DeleteMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") int id) {
        service.deleteProduct(id);
        return new ResponseEntity<>("DELETED", HttpStatus.CREATED);
    }

    /**
     * This will override the global handler
     *
     * @return
     */
    @ExceptionHandler(ProductsDataAccessException.class)
    public ResponseEntity<ErrorModel> handleDataException() {
        return new ResponseEntity<>(ErrorModel.builder()
                .errorCode("PRODUCTS_BAD_REQUEST")
                .errorMessage("Product not found")
                .build(), HttpStatus.BAD_REQUEST
        );
    }

}
