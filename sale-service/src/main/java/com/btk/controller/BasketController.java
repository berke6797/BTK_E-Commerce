package com.btk.controller;

import com.btk.dto.request.AddProductToBasketRequestDto;
import com.btk.dto.request.DeleteProductFromBasketRequestDto;
import com.btk.dto.request.TotalPriceRequestDto;
import com.btk.dto.request.UpdateBasketRequestDto;
import com.btk.dto.response.GetProductDescriptionsFromProductServiceResponseDto;
import com.btk.service.BasketService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.btk.constant.ApiUrls.*;

@RestController
@RequestMapping(BASKET)
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;

    @Operation(summary = "Kullanıcının sepete ürün ekleyebilmesi.")
    @PostMapping(ADD_PRODUCT_TO_BASKET+"/{token}")
    public ResponseEntity<Boolean> addProductToBasket(@PathVariable String token, @RequestBody @Valid AddProductToBasketRequestDto dto) {
        return ResponseEntity.ok(basketService.addProductToBasket(token, dto));
    }
    @Operation(summary = "Kullanıcının sepetteki ürünlerin toplam fiyatını anlık olarak görüntüleyebilmesi.")
    @PostMapping(TOTAL_PRICE_IN_BASKET+"/{token}")
    public ResponseEntity<Double> totalPriceInBasket(@PathVariable String token, @RequestBody TotalPriceRequestDto dto) {
        return ResponseEntity.ok(basketService.totalPriceInBasket(token, dto));
    }
    @Operation(summary = "Kullanıcının sepet içeriğini görüntüleyebilmesi.")
    @GetMapping(FIND_ALL+"/{token}")
    public ResponseEntity<List<GetProductDescriptionsFromProductServiceResponseDto>> findBasketForUser(@PathVariable String token) {
        return ResponseEntity.ok(basketService.findBasketForUser(token));
    }
    @Operation(summary = "Kullanıcının sepetteki ürünleri güncelleyebilmesi.")
    @PostMapping(UPDATE_BASKET+"/{token}")
    public ResponseEntity<List<GetProductDescriptionsFromProductServiceResponseDto>> updateBasket(@PathVariable String token, @RequestBody UpdateBasketRequestDto dto) {
        return ResponseEntity.ok(basketService.updateBasket(token, dto));
    }
    @Operation(summary = "Kullanıcının sepetteki ürünleri çıkarabilmesi.")
    @PostMapping(DELETE_PRODUCT_FROM_BASKET+"/{token}")
    public ResponseEntity<List<GetProductDescriptionsFromProductServiceResponseDto>> deleteProductFromBasket(@PathVariable String token, @RequestBody DeleteProductFromBasketRequestDto dto) {
        return ResponseEntity.ok(basketService.deleteProductFromBasket(token, dto));
    }
}
