package com.btk.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {
    /**
     * CircuitBreaker --> Bu bir devre kesicidir. Gateway'e gelen isteklerde bir sorun olduğunda servisler için bir mesaj döner.
     *                       Hataları tespit ederek devam etmemesini sağlar.
     */
    @GetMapping("/auth-service")
    public ResponseEntity<String> authServiceFallback(){
        return ResponseEntity.ok("Auth service 'e eriştiniz.. ");
    }

    @GetMapping("/user-service")
    public ResponseEntity<String> userProfileServiceFallback(){
        return ResponseEntity.ok("User service şu an hizmet verememektedir .. ");
    }
    @GetMapping("/product-service")
    public ResponseEntity<String> productServiceFallback(){
        return ResponseEntity.ok("Product service hizmet verememektedir .. ");
    }


    @GetMapping("/brand-service")
    public ResponseEntity<String> brandServiceFallback(){
        return ResponseEntity.ok("Brand service hizmet verememektedir .. ");
    }
    @GetMapping("/category-service")
    public ResponseEntity<String> categoryServiceFallback(){
        return ResponseEntity.ok("Category service hizmet verememektedir .. ");
    }
    @GetMapping("/basket-service")
    public ResponseEntity<String> basketServiceFallback(){
        return ResponseEntity.ok("Basket service hizmet verememektedir .. ");
    }
    @GetMapping("/balance-service")
    public ResponseEntity<String> balanceServiceFallback(){
        return ResponseEntity.ok("Balance service hizmet verememektedir .. ");
    }
    @GetMapping("/order-service")
    public ResponseEntity<String> orderServiceFallback(){
        return ResponseEntity.ok("Order service hizmet verememektedir .. ");
    }


}
