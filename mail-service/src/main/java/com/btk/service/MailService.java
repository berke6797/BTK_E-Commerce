package com.btk.service;

import com.btk.rabbitmq.model.RegisterMailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;
    public void createMailWithRabbitMq(RegisterMailModel model) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Aktivasyon kodu");
        simpleMailMessage.setFrom("ekazanci97@gmail.com");
        simpleMailMessage.setTo(model.getEmail());
        simpleMailMessage.setText("Aktivasyon kodunuz: " + model.getActivationCode()+"\n Giriş yapabilmek için önce hesabınızı aktif ediniz.");
        javaMailSender.send(simpleMailMessage);
    }

}
