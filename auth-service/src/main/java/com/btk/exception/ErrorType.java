package com.btk.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    INTERNAL_ERROR(5100, "Sunucu Hatası", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4000, "Parametre Hatası", HttpStatus.BAD_REQUEST),
    LOGIN_ERROR(4100, "Kullancı adı veya şifre hatalı", HttpStatus.BAD_REQUEST),
    PASSWORD_ERROR(4200, "Şifreler aynı değil", HttpStatus.BAD_REQUEST),
    USERNAME_DUPLICATE(4300, "Bu kullanıcı zaten kayıtlı", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4400, "Böyle bir kullanıcı bulunamadı", HttpStatus.NOT_FOUND),
    ACTIVATE_CODE_ERROR(4500, "Hesap aktif değildir", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(4600,"Token hatası" ,  HttpStatus.BAD_REQUEST),
    NOT_AUTHORIZED(4610,"Bu işlemi yapabilmek için yetkiye sahip değilsiniz",HttpStatus.UNAUTHORIZED),
    TOKEN_NOT_CREATED(4700, "Token oluşturulamadı", HttpStatus.BAD_REQUEST),
    ADRESS_NOT_FOUND(4800, "Böyle bir adres bulunamadı", HttpStatus.NOT_FOUND),
    INVALID_ACTION(4900,"Kullanıcı istenilen statüye geçirilemedi.",HttpStatus.BAD_REQUEST),
    PASSWORD_DUPLICATE(5000,"Şifre son kullanılan şifreyle aynıdır.",HttpStatus.BAD_REQUEST),
    COMPANY_NOT_FOUND(5100, "Böyle bir şirket bulunamadı", HttpStatus.NOT_FOUND),
    DUPLICATE_USER(5200, "Bu kullanıcı zaten kayıtlı", HttpStatus.BAD_REQUEST),
    FOUNDER_EXIST_ERROR(5300,"Şirkete kayıtlı bir founder manager bulunmaktadır.", HttpStatus.BAD_REQUEST),
    COMPANY_SUBSCRIPTION_EXIST(5400,"Company'nin subscription işlemleri önceden yapılmıştır", HttpStatus.BAD_REQUEST);



    private int code;
    private String message;
    HttpStatus httpStatus;
}
