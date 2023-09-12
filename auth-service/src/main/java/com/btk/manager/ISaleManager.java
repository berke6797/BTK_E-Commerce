package com.btk.manager;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-sale", url = "http://localhost:9093/api/v1/balance")
public interface ISaleManager {
    @PostMapping("/create-balance/{authId}")
    ResponseEntity<Boolean> createBalance(@PathVariable Long authId);
}
