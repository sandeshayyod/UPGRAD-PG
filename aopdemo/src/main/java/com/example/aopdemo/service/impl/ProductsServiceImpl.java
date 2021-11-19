package com.example.aopdemo.service.impl;

import com.example.aopdemo.dao.ProductsDao;
import com.example.aopdemo.entity.Product;
import com.example.aopdemo.exception.ProductsDataAccessException;
import com.example.aopdemo.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsDao dao;

    @Override
    public Product getProductBasedOnId(int id) {
        return dao.findById(id).orElseThrow(ProductsDataAccessException::new);
    }

    @Override
    public List<Product> getAllProducts() {
        return dao.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return dao.save(product);
    }

    @Override
    public boolean deleteProduct(int id) {
        Product savedProduct = getProductBasedOnId(id);
        dao.delete(savedProduct);
        return true;
    }

    @Override
    public Product updateProduct(int id, Product product) {
        Product savedProduct = getProductBasedOnId(id);
        savedProduct.setProductName(product.getProductName());
        savedProduct.setProductOwner(product.getProductOwner());
        savedProduct = dao.save(savedProduct);
        return savedProduct;
    }
}
