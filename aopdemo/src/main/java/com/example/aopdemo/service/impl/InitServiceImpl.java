package com.example.aopdemo.service.impl;

import com.example.aopdemo.dao.ProductsDao;
import com.example.aopdemo.entity.Product;
import com.example.aopdemo.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitServiceImpl implements InitService {

    @Autowired
    private ProductsDao dao;

    @Override
    public void init() {
        Product product = new Product(1, "Iphone", "Steve Jobs");
        dao.save(product);
    }
}
