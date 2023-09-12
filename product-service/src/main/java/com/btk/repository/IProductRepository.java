package com.btk.repository;

import com.btk.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA/Mongo yapısı gereği RepositoryPattern uygular.
 * Spring geliştiricilerin, projeler çok kapsamlı olmadığı sürece
 * veya özel bir veri tabanı yönetimi ve soyutlaması gerekmediği sürece RepositoryPattern uygulamaya gerek duyulmaz.
 */
@Repository
public interface IProductRepository extends MongoRepository<Product, String> {

    List<Product> findProductByCategoryIdsContainsIgnoreCase(List<String> categoryIds);
    List<Product> findProductByProductNameContainsIgnoreCase(String productName);
    List<Product> findByPriceGreaterThanEqual(Double minPrice);
    List<Product> findByPriceLessThanEqual(Double maxPrice);
    List<Product> findProductByPriceBetween(Double minPrice,Double maxPrice);
    List<Product> findAllByCreatedDateBetween(Long date1,Long date2);

}
