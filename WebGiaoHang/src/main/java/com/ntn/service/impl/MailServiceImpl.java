/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.service.impl;

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
import org.springframework.stereotype.Service;

/**
 *
 * @author THANH NHAN
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ShipperService shipperSer;
    @Autowired
    private DauGiaService dauGiaSer;
    @Autowired
    private DonHangService donHangSer;

    @Override
    public boolean sendMail(Map<String, String> emailRequest) {
        try {
            int idShipper = Integer.parseInt(emailRequest.get("shipperId"));
            int idDonhang = Integer.parseInt(emailRequest.get("orderId"));
            int idDaugia = Integer.parseInt(emailRequest.get("daugiaId"));

            dauGiaSer.updateKqDaugia(idDaugia);
            Shipper sp = shipperSer.getShipperById(idShipper);

            List<Shipper> listSp = this.dauGiaSer.getShipperByDauGia(idShipper, idDonhang);
            Donhang dh = donHangSer.getOrderById(idDonhang);
            dh.setTrangthai("Đang giao");
            dh.setIdShipper(sp);
            donHangSer.addOrUpdateDh(dh);
            SimpleMailMessage successMessage = new SimpleMailMessage();
            successMessage.setTo(sp.getUser().getEmail());
            successMessage.setSubject("Thông báo đấu giá");
            successMessage.setText("Bạn đã đấu giá THÀNH CÔNG đơn hàng #" + emailRequest.get("orderId"));
            javaMailSender.send(successMessage);

            for (Shipper shipper : listSp) {
                SimpleMailMessage failureMessage = new SimpleMailMessage();
                failureMessage.setTo(shipper.getUser().getEmail());
                failureMessage.setSubject("Thông báo đấu giá");
                failureMessage.setText("Bạn đã đấu giá THẤT BẠI đơn hàng #" + emailRequest.get("orderId"));
                System.out.println(shipper.getUser().getEmail());
                javaMailSender.send(failureMessage);
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
