package com.btk.controller;

import com.btk.dto.request.ActivateRequestDto;
import com.btk.dto.request.LoginRequestDto;
import com.btk.dto.request.RegisterUserRequestDto;
import com.btk.dto.response.LoginResponseDto;
import com.btk.dto.response.ToAuthPasswordChangeDto;
import com.btk.service.AuthService;
import io.swagger.v3.oas.annotations.Hidden;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.io.FileNotFoundException;

import static com.btk.constant.ApiUrls.*;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(REGISTER_USER)
    @Operation(summary = "Kullanıcı kayıt olurken kullanılıyor.")
    public ResponseEntity<String> registerUser(@RequestBody @Valid RegisterUserRequestDto dto) {
        return ResponseEntity.ok(authService.registerUser(dto));
    }

    @PostMapping(REGISTER_SITE_MANAGER)
    @Operation(summary = "Admin Site-Manager kayıt ederken kullanılıyor.")
    public ResponseEntity<String> registerSiteManager(@RequestBody @Valid RegisterUserRequestDto dto, @PathVariable String token) {
        return ResponseEntity.ok(authService.registerSiteManager(dto, token));
    }

    @PostMapping(ACTIVATE_STATUS)
    @Operation(summary = "Kullanıcı kayıt olduğunda, 'PENDING' olan hesap durumunu 'ACTIVE'e dönüştürebilmesi.")
    public ResponseEntity<String> activateStatus(@RequestBody @Valid ActivateRequestDto dto) {
        return ResponseEntity.ok(authService.activateStatus(dto));
    }

    @PostMapping(LOGIN)
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

    @PutMapping(FORGOT_PASSWORD + "/{email}")
    @Operation(summary = "Kullanıcı şifresini unuttuğunda kullanacağı şifre değiştirebilmesi.")
    public ResponseEntity<String> forgotPassword(@PathVariable @Valid String email) {
        return ResponseEntity.ok(authService.forgotPassword(email));
    }

    @Hidden
    @PutMapping(PASSWORD_CHANGE)
    ResponseEntity<Boolean> changePassword(@RequestBody ToAuthPasswordChangeDto dto) {
        return ResponseEntity.ok(authService.changePassword(dto));
    }

    @Hidden
    @GetMapping("/export/{format}")
    public String exportReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return authService.exportReport(format);
    }
}