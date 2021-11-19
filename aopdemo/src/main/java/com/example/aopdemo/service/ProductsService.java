package com.example.aopdemo.service;

import com.example.aopdemo.entity.Product;

import java.util.List;

public interface ProductsService {

    Product getProductBasedOnId(int id);

    List<Product> getAllProducts();

    Product saveProduct(Product product);

    boolean deleteProduct(int id);

    Product updateProduct(int id, Product product);
}
