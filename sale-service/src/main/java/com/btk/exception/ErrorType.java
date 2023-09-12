package com.btk.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    INTERNAL_ERROR(4000, "Sunucu Hatası", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4100, "Parametre Hatası", HttpStatus.BAD_REQUEST),
    USERNAME_DUPLICATE(4200, "Bu kullanıcı zaten kayıtlı", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(4300,"Geçersiz token bilgisi",HttpStatus.BAD_REQUEST),
    INVALID_ROLE(4400,"Bu işleme erişim yetkiniz bulunmamaktadır",HttpStatus.BAD_REQUEST),
    BALANCE_EXIST_ERROR(4500,"Daha önce cüzdanınız bulunmaktadır",HttpStatus.BAD_REQUEST),
    HAS_NOT_ACTIVE_BASKET(4600,"Aktif sepetiniz bulunmamaktadır",HttpStatus.BAD_REQUEST),
    HAS_NOT_PASSIVE_BASKET(4700,"Sepet geçmişi bulunmamaktadır",HttpStatus.BAD_REQUEST),
    INSUFFICIENT_BALANCE (4800,"Yetersiz bakiye",HttpStatus.BAD_REQUEST),

    ;

    private int code;
    private String message;
    HttpStatus httpStatus;
}
