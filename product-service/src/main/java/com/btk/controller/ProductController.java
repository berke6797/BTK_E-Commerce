package com.btk.controller;

import com.btk.dto.request.ProductSaveRequestDto;
import com.btk.dto.request.ProductUpdateRequestDto;
import com.btk.dto.response.*;
import com.btk.service.ProductService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.io.FileNotFoundException;
import java.util.List;

import static com.btk.constant.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(PRODUCT)
public class ProductController {
    private final ProductService productService;

    @PostMapping(SAVE_PRODUCT + "/{token}")
    public ResponseEntity<ProductSaveResponseDto> save(@RequestBody @Valid ProductSaveRequestDto dto, @PathVariable String token) {
        return ResponseEntity.ok(productService.save(dto, token));
    }

    @PutMapping(UPDATE_PRODUCT + "/{token}")
    public ResponseEntity<ProductUpdateResponseDto> update(@RequestBody @Valid ProductUpdateRequestDto dto, @PathVariable String token) {
        return ResponseEntity.ok(productService.update(dto, token));
    }

    @DeleteMapping(DELETED_PRODUCT + "/{token}")
    public ResponseEntity<Boolean> delete(@PathVariable String productId, @PathVariable String token) {
        return ResponseEntity.ok(productService.delete(productId, token));
    }

    @GetMapping(PRODUCT_DETAILS + "/{productId}")
    @Operation(summary = "Ürünlerin detaylarını gösterebilmek.")
    public ResponseEntity<ProductDetailsResponseDto> productDetails(@PathVariable String productId) {
        return ResponseEntity.ok(productService.productDetails(productId));
    }

    @GetMapping(PRODUCT_SEARCH_WITH_CATEGORY_NAME + "/{categoryName}")
    @Operation(summary = "Ürünleri, kategori isimlerine göre aratabilmek.")
    public ResponseEntity<List<SearchProductResponseDto>> searchProductWithCategoryName(@PathVariable String categoryName) {
        return ResponseEntity.ok(productService.searchProductWithCategoryName(categoryName));
    }

    @GetMapping(PRODUCT_SEARCH_WITH_PRODUCT_NAME + "/{productName}")
    @Operation(summary = "Ürünleri, isimlerine göre aratabilmek.")
    public ResponseEntity<List<SearchProductResponseDto>> searchProductWithProductName(@PathVariable String productName) {
        return ResponseEntity.ok(productService.searchProductWithProductName(productName));
    }

    @GetMapping(PRODUCT_SEARCH_WITH_PRICE)
    @Operation(summary = "Ürünleri, istenilen fiyat ya da fiyat aralığında aratabilmek.")
    public ResponseEntity<List<SearchProductResponseDto>> searchProductWithProductPrice(@RequestParam(required = false) Double minPrice, @RequestParam(required = false) Double maxPrice) {
        return ResponseEntity.ok(productService.searchProductWithProductPrice(minPrice, maxPrice));
    }

    @Hidden
    @GetMapping(GET_PRICE_BY_PRODUCT_ID+"/{productId}")
    public ResponseEntity<Double> findPriceByProductId(@PathVariable String productId){
        return ResponseEntity.ok(productService.getPriceByProductId(productId));
    }
    @Hidden
    @GetMapping(FIND_PRODUCT_DESCRIPTIONS_BY_PRODUCT_ID+"/{productId}")
    public ResponseEntity<GetProductDescriptionsFromProductServiceResponseDto> findDescriptionsByProductId(@PathVariable String productId){
        return ResponseEntity.ok(productService.findDescriptionsByProductId(productId));
    }

    @Hidden
    @GetMapping(FIND_PRODUCT_LIST_WITH_DESCRIPTIONS_ACCORDING_TO_DATE)
    public ResponseEntity <List<GetProductDescriptionsFromProductServiceResponseDto>> findFilteredProductsList(@RequestParam(required = false) Long date1, @RequestParam(required = false) Long date2){
        return ResponseEntity.ok(productService.findFilteredProductsListWithDates(date1, date2));
    }

}
