package com.example.pagination.service;

import com.example.pagination.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService {
    List<Product> findAllProducts();
    List<Product> findProductWithSorting(String field,String sortDirection);
    Page<Product> findProdcutWithPagination(int offset,int pageSize);
    Page<Product> findProductWithPaginationAndSorting(int offset,int pageSize,String field,String order);
    List<Product> searchByKeyword(String key);
}
