package com.btk.repository;

import com.btk.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoryRepository extends MongoRepository<Category, String> {

    boolean existsById(String categoryId);

    Optional<Category> findCategoryByCategoryNameContainingIgnoreCase(String categoryName);
    List<Category> findCategoriesByCategoryNameContainsIgnoreCase(String categoryName);
}
