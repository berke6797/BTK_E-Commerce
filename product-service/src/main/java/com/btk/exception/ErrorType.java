package com.btk.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    INTERNAL_ERROR(5100, "Sunucu Hatası", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4000, "Parametre Hatası", HttpStatus.BAD_REQUEST),
    USERNAME_DUPLICATE(4300, "Bu kullanıcı zaten kayıtlı", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND(4400, "Böyle bir ürün bulunamadı", HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_FOUND(4400, "Böyle bir kategori bulunamadı.", HttpStatus.BAD_REQUEST),
    BRAND_NOT_FOUND(4400, "Böyle bir marka bulunamadı.", HttpStatus.BAD_REQUEST),
    BRAND_AND_CATEGORY_NOT_FOUND(4400, "Böyle bir marka veya kategori bilgisi yoktur.", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(4600, "Token hatası", HttpStatus.BAD_REQUEST),
    NOT_AUTHORIZED(4610,"Bu işlemi yapabilmek için yetkiye sahip değilsiniz",HttpStatus.UNAUTHORIZED);


    private int code;
    private String message;
    HttpStatus httpStatus;
}
