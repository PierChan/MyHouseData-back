package com.back.myhousedata.controller;

import com.back.myhousedata.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/mail")
public class MailController {

    @Autowired
    public MailService mailService;

    @PostMapping
    public ResponseEntity<?> sendMail() {
        mailService.sendMail("pierre.chanfrault@gmail.com");
        return ResponseEntity.ok().build();
    }
}
