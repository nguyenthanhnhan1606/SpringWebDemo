/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.service;

import java.util.Map;

/**
 *
 * @author THANH NHAN
 */
public interface MailService {
    boolean sendMail(Map<String, String> emailRequest);
     boolean sendMailConfirm(Map<String, String> emailRequest);
}
