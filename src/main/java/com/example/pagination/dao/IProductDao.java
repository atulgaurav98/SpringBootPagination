package com.example.pagination.dao;

import com.example.pagination.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductDao extends JpaRepository<Product,Integer> {
}
