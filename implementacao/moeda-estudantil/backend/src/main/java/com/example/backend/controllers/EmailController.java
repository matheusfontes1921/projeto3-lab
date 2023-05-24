package com.example.backend.controllers;

import com.example.backend.model.dto.EmailDto;
import com.example.backend.model.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/enviar-email")
    public String enviarEmail(@RequestBody EmailDto emailDto) {
        emailService.enviarEmail(emailDto.destinatario(), emailDto.assunto(), emailDto.conteudo());
        return "E-mail enviado com sucesso!";
    }
}
