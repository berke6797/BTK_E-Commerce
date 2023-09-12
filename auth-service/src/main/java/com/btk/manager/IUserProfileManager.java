package com.btk.manager;

import com.btk.dto.request.ForgotPasswordUserRequestDto;
import com.btk.dto.request.NewCreateUserRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-userprofile", url = "http://localhost:9091/api/v1/user-profile")
public interface IUserProfileManager {
    @PostMapping("/create-user")
    ResponseEntity<Boolean> createUser(@RequestBody NewCreateUserRequestDto dto);
    @PostMapping("/create-site-manager")
    ResponseEntity<Boolean> createSiteManager(@RequestBody NewCreateUserRequestDto dto);
    @PutMapping("/activate-status/{authId}")
    ResponseEntity<Boolean> activateStatus(@PathVariable Long authId);
    @PutMapping("/forgot-password")
    ResponseEntity<Boolean> forgotPassword(@RequestBody ForgotPasswordUserRequestDto dto);
}
