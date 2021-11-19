package com.example.aopdemo.dao;

import com.example.aopdemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class for CRUD operations
 */
public interface ProductsDao extends JpaRepository<Product, Integer> {

}
