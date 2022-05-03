package com.example.pagination.dao;

import com.example.pagination.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductDao extends JpaRepository<Product,Integer> {
    @Query(value = "SELECT * FROM product_table p where " +
            "p.id like %?1% OR " +
            "p.name like %?1% OR " +
            "p.quantity like %?1% OR " +
            "p.price like %?1%",nativeQuery = true)
    List<Product> findAllByKey(String key);
}
