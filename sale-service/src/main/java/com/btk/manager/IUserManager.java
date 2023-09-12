package com.btk.manager;

import com.btk.dto.response.UserProfileResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:9091/api/v1/user-profile",name = "sale-userprofile")
public interface IUserManager {
    @GetMapping("/find-by-auth-id/{authId}")
    public ResponseEntity<String> findByAuthId(@PathVariable Long authId);

}
