package com.btk.service;

import com.btk.dto.request.CategoryUpdateRequestDto;
import com.btk.entity.Category;
import com.btk.entity.enums.ERole;
import com.btk.exception.ErrorType;
import com.btk.exception.ProductManagerException;
import com.btk.repository.ICategoryRepository;
import com.btk.utility.JwtTokenProvider;
import com.btk.utility.ServiceManager;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryService extends ServiceManager<Category, String> {

    private final ICategoryRepository categoryRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public CategoryService(ICategoryRepository categoryRepository, JwtTokenProvider jwtTokenProvider) {
        super(categoryRepository);
        this.categoryRepository = categoryRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Transactional
    public Boolean save(String categoryName, String token) {
        List<String> roles = jwtTokenProvider.getRoleFromToken(token);
        roles.forEach(role -> {
            if (role.contains(ERole.ADMIN.toString())) {
                Category category = Category.builder()
                        .categoryName(categoryName).build();
                save(category);
            } else {
                throw new ProductManagerException(ErrorType.NOT_AUTHORIZED);
            }
        });
        return true;
    }

    @Transactional
    public Boolean updateCategory(CategoryUpdateRequestDto dto, String token) {
        List<String> roles = jwtTokenProvider.getRoleFromToken(token);
        roles.forEach(role -> {
            if (role.contains(ERole.ADMIN.toString())) {
                Optional<Category> category = findById(dto.getCategoryId());
                if (category.isEmpty()) throw new ProductManagerException(ErrorType.CATEGORY_NOT_FOUND);
                category.get().setCategoryName(dto.getCategoryName());
                update(category.get());
            } else {
                throw new ProductManagerException(ErrorType.NOT_AUTHORIZED);
            }
        });
        return true;
    }

    public Boolean existByCategoryId(String categoryId) {
        return categoryRepository.existsById(categoryId);
    }

    public List<Category> getCategoriesWithCategoryName(String categoryName) {
        return categoryRepository.findCategoriesByCategoryNameContainsIgnoreCase(categoryName);
    }
}
