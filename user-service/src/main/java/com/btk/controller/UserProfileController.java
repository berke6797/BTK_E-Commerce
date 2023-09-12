package com.btk.controller;

import com.btk.dto.request.UserChangePasswordRequestDto;
import com.btk.dto.response.ForgotPasswordUserResponseDto;
import com.btk.dto.response.NewCreateUserResponseDto;
import com.btk.dto.response.UserProfileResponseDto;
import com.btk.service.UserProfileService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.btk.constant.ApiUrls.*;

@RestController
@RequestMapping(USER_PROFILE)
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;

    @Hidden
    @PostMapping(CREATE_USER)
    public ResponseEntity<Boolean> createVisitorUser(@RequestBody NewCreateUserResponseDto dto) {
        return ResponseEntity.ok(userProfileService.createUser(dto));
    }
    @Hidden
    @PostMapping("/create-site-manager")
    public ResponseEntity<Boolean> createSiteManager(@RequestBody NewCreateUserResponseDto dto) {
        return ResponseEntity.ok(userProfileService.createSiteManager(dto));
    }

    @Hidden
    @PutMapping(ACTIVATE_STATUS + "/{authId}")
    public ResponseEntity<Boolean> activateStatus(@PathVariable Long authId) {
        return ResponseEntity.ok(userProfileService.activateStatus(authId));
    }

    @Hidden
    @PutMapping(FORGOT_PASSWORD)
    public ResponseEntity<Boolean> forgotPassword(@RequestBody ForgotPasswordUserResponseDto dto) {
        return ResponseEntity.ok(userProfileService.forgotPassword(dto));
    }

    @PutMapping(PASS_CHANGE)
    @Operation(summary = "Kullanıcının giriş yaptıktan sonra şifresini değiştirebilmesi.")
    public ResponseEntity<String> changePassword(@Valid @RequestBody UserChangePasswordRequestDto dto) {
        return ResponseEntity.ok(userProfileService.changePassword(dto));
    }

    @Hidden
    @GetMapping(FIND_BY_AUTH_ID+"/{authId}")
    public ResponseEntity<String> findByAuthId(@PathVariable Long authId) {
        return ResponseEntity.ok(userProfileService.findByAuthId(authId));
    }

}
