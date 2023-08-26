/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.restcontroller;

import com.ntn.pojo.Donhang;
import com.ntn.pojo.Shipper;
import com.ntn.service.DauGiaService;
import com.ntn.service.DonHangService;
import com.ntn.service.MailService;
import com.ntn.service.ShipperService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author THANH NHAN
 */
@RestController
public class EmailApiController {

    @Autowired
    private MailService mailSer;

    @PostMapping("/api/send-email")
    @CrossOrigin
    public String sendEmail(@RequestParam Map<String, String> emailRequest) {
        if (mailSer.sendMail(emailRequest)) {
            return "Email sent successfully!";
        }
        return "Failed to send email: ";
    }
}