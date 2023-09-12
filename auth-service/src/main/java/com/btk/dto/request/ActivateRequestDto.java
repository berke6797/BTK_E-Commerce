package com.btk.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivateRequestDto {
    @Email(message = "Lütfen geçerli bir email giriniz.")
    String email;
    @NotBlank(message = "Aktivasyon kodunu boş bırakmayınız.")
    String activationCode;
}
