package com.recipe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl {
    private final JavaMailSender mailSender;


    @Async
    public void mailSend(SimpleMailMessage message){
        mailSender.send(message);
    }
}
