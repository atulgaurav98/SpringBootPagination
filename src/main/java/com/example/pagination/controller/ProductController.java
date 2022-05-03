package com.example.pagination.controller;

import com.example.pagination.model.ApiResponse;
import com.example.pagination.model.Product;
import com.example.pagination.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public ApiResponse<List<Product>> getProducts(){
        List<Product> products = productService.findAllProducts();
        return new ApiResponse<>(products.size(),products);
    }

    @GetMapping(value = "/{field}/{order}")
    public ApiResponse<List<Product>> getSortedProductsOnBasisOfFieldAndOrder(@PathVariable String field,@PathVariable String order){
        List<Product> products = productService.findProductWithSorting(field,order);
        return new ApiResponse<>(products.size(),products);
    }

    @GetMapping(value = "/pagination/{offset}/{pageSize}")
    public ApiResponse<List<Product>> getPaginatedProducts(@PathVariable int offset, @PathVariable int pageSize){
        System.out.println("Offset is : "+offset+ " \n page size is : "+pageSize);
        Page<Product> products = productService.findProdcutWithPagination(offset,pageSize);
        List<Product> prods = new ArrayList<>();
        products.stream().map(
                p->prods.add(p)
        ).collect(Collectors.toList());
        return new ApiResponse<>(prods.size(),prods);
    }

    @GetMapping(value = "/pagination/{offset}/{pageSize}/{field}/{order}")
    public ApiResponse<List<Product>> getPaginatedProductsAndSorting(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field,@PathVariable String order){
        System.out.println("Offset is : "+offset+ " \n page size is : "+pageSize);
        Page<Product> products = productService.findProductWithPaginationAndSorting(offset, pageSize, field, order);
        List<Product> prods = new ArrayList<>();
        products.stream().map(
                p->prods.add(p)
        ).collect(Collectors.toList());
        return new ApiResponse<>(prods.size(),prods);
    }

    @GetMapping(value = "/search/{key}")
    public ApiResponse<List<Product>> getProductBySearchKey(@PathVariable String key){
        List<Product> products = productService.searchByKeyword(key);
        return new ApiResponse<>(products.size(),products);
    }
}
