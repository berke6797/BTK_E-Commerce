package com.btk.service;

import com.btk.entity.Brand;
import com.btk.entity.Category;
import com.btk.repository.IBrandRepository;
import com.btk.utility.ServiceManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BrandService extends ServiceManager<Brand, String> {

    private final IBrandRepository brandRepository;

    public BrandService(IBrandRepository brandRepository) {
        super(brandRepository);
        this.brandRepository = brandRepository;
    }

    @Transactional
    public Boolean save(String brandName) {
        Brand brand = Brand.builder()
                .brandName(brandName).build();
        save(brand);
        return true;
    }

    @Transactional(readOnly = true)
    public Boolean existByBrandId(String brandId) {
        return brandRepository.existsById(brandId);
    }
}
