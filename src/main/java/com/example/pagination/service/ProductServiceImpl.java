package com.example.pagination.service;

import com.example.pagination.dao.IProductDao;
import com.example.pagination.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;
//    @PostConstruct
//    public void initialiseDB(){
//        List<Product> products = IntStream
//                .rangeClosed(1,200)
//                .mapToObj(i->new Product(i,"Product"+i,new Random().nextInt(10),new Random().nextInt(100))
//                ).collect(Collectors.toList());
//        productDao.saveAll(products);
//    }
    @Override
    public List<Product> findAllProducts() {
        return productDao.findAll();
    }

    @Override
    public List<Product> findProductWithSorting(String field, String sortDirection) {
        if(sortDirection.equalsIgnoreCase("asc"))
            return productDao.findAll(Sort.by(Sort.Direction.ASC,field));
        else if(sortDirection.equalsIgnoreCase("desc"))
            return productDao.findAll(Sort.by(Sort.Direction.DESC,field));
        else
            return productDao.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    @Override
    public Page<Product> findProdcutWithPagination(int offset, int pageSize) {
        Page<Product> pages = productDao.findAll(PageRequest.of(offset, pageSize));
        return pages;
    }

    @Override
    public Page<Product> findProductWithPaginationAndSorting(int offset, int pageSize, String field, String order) {
        if(order.equalsIgnoreCase("asc"))
            return productDao.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(Sort.Direction.ASC,field)));
        else if(order.equalsIgnoreCase("desc"))
            return productDao.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(Sort.Direction.DESC,field)));
        else
            return productDao.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(Sort.Direction.ASC,field)));
    }


}
