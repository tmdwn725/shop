package com.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;
    private static final String senderEmail = "sjmoon@beintech.co.kr";
    private static int number;

    /**
     * 인증번호 생성
     * @param mail
     */
    public static void createNumber(String mail){
        number = (int)(Math.random() * (90000)) + 100000;// (int) Math.random() * (최댓값-최소값+1) + 최소값
    }

    /**
     * 메일 생성
     * @param mail
     * @return
     */
    public SimpleMailMessage CreateMail(String mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        try {
            message.setTo(senderEmail);
            message.setSubject("이메일 인증");
            String body = "";
            body += "<h3>요청하신 인증 번호입니다.</h3>";
            body += "<h1>"+ number + "</h1>";
            body += "<h3>요청하신 인증 번호입니다.</h3>";
            message.setText(body);

        } catch (Exception e){
            e.printStackTrace();
        }

        return message;
    }

    public int sendMail(String mail){
        SimpleMailMessage  message = CreateMail(mail);
        javaMailSender.send(message);

        return number;
    }
}
