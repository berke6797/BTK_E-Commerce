package com.btk.repository;

import com.btk.entity.Brand;
import com.btk.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBrandRepository extends MongoRepository<Brand, String> {

    boolean existsById(String brandId);
}
