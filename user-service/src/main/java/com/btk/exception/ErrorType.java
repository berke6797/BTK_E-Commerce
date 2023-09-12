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
    USERNAME_NOT_CREATED(4100, "Kullanıcı oluşturulamadı", HttpStatus.BAD_REQUEST),
    AUTHORIZATION_ERROR(4200,"You're not authorized to do this.", HttpStatus.BAD_REQUEST),

    USER_NOT_FOUND(4400, "Böyle bir kullanıcı bulunamadı", HttpStatus.NOT_FOUND),
    INVALID_TOKEN(4600,"Token hatası" ,  HttpStatus.BAD_REQUEST),
    FOLLOW_ALREADY_EXIST(4700, "Böyle bir takip isteği daha önce oluşturulmuştur.", HttpStatus.BAD_REQUEST),
    USER_NOT_FOLLOW(4800, "Kullanıcı kendisini takip edemez.", HttpStatus.BAD_REQUEST),
    PASSWORD_ERROR(4900, "Girdiğiniz şifre ile eski şifreniz uyuşmamaktadır.", HttpStatus.BAD_REQUEST),
    INVALID_ACTION(5000,"Kullanıcı istenilen statüye geçirilemedi.",HttpStatus.BAD_REQUEST),
    USER_ALREADY_DELETED(5200,"Kullanıcı zaten silinmiştir.",HttpStatus.BAD_REQUEST),
    DIFFERENT_COMPANY(5300,"Personel başka bir şirkette çalışmaktadır." , HttpStatus.BAD_REQUEST),
    NOT_PERSONEL(5400,"Bu işlemi yapabilmek için personel olmanız gerekmektedir.",HttpStatus.NOT_FOUND),
    ADVANCEREQUEST_BIGGER_THAN_WAGE(5500,"İstediğiniz avans maaşınızdan yüksek!!",HttpStatus.NOT_FOUND),
    REMAININGMONEY_LESSERTHANZERO(5600,"Bu ay avans alma kotanızı doldurdunuz!!",HttpStatus.NOT_FOUND),
    INVALID_WAGEDATE(5700,"Maaş alma günü geçersiz.",HttpStatus.NOT_FOUND),
    ADVANCE_NOT_FOUND(5800,"Böyle bir advance bulunamamıştır.",HttpStatus.NOT_FOUND),
    ADVANCE_NOT_PENDING(5900,"Advance pending durumda değildir.",HttpStatus.BAD_REQUEST),
    INVALID_WAGE(6000,"Maaş miktarı avansı karşılamaya yeterli değildir.",HttpStatus.BAD_REQUEST),
    CANNOT_REQUEST_MORE_LEAVE(6100, "Mevcut izin gün sayınızdan fazla izin talep edemezsiniz veya bekleyen izin günleriniz bulunmaktadır!!", HttpStatus.BAD_REQUEST),
    NOT_FOUND_DAY_OFF_PERMISSION(6200,"İzin bulunamadı",HttpStatus.NOT_FOUND),
    PERMISSION_STATUS_NOT_PENDING(6300,"İzin durumu Pending değil",HttpStatus.NOT_FOUND),
    NOT_MANAGER(6400,"Manager Değilsiniz",HttpStatus.BAD_REQUEST),
    PERSONNEL_NOT_FOUND(6500,"Personel bulunamadı",HttpStatus.NOT_FOUND);
    private int code;
    private String message;
    HttpStatus httpStatus;
}
