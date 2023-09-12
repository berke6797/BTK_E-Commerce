package com.btk.manager;

import com.btk.dto.response.GetProductDescriptionsFromProductServiceResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(url = "http://localhost:9092/api/v1/product",name = "sale-product")
public interface IProductManager {
    @GetMapping("/get-by-product-id/{productId}")
     ResponseEntity<Double> findPriceByProductId(@PathVariable String productId);
    @GetMapping("/find-product-descriptions-by-product-id/{productId}")
     ResponseEntity<GetProductDescriptionsFromProductServiceResponseDto> findDescriptionsByProductId(@PathVariable String productId);

    //@GetMapping("/find-product-descriptions-according-to-dates/{productId}")
     //ResponseEntity<GetProductDescriptionsFromProductServiceResponseDto> findFilteredProductsList(@PathVariable String productId);
}
