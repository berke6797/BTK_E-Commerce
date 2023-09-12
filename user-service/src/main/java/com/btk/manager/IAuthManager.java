package com.btk.manager;

import com.btk.dto.request.ToAuthPasswordChangeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://localhost:9090/api/v1/auth", name = "userprofile-auth")
public interface IAuthManager {
    @PutMapping("/password-change")
    ResponseEntity<Boolean> changePassword(@RequestBody ToAuthPasswordChangeDto dto);

}
