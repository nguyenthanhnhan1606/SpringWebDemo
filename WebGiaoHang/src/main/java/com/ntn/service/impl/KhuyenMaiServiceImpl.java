/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntn.pojo.Khuyenmai;
import com.ntn.repository.KhuyenMaiRepository;
import com.ntn.service.KhuyenMaiService;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author THANH NHAN
 */
@Service
public class KhuyenMaiServiceImpl implements KhuyenMaiService {

    @Autowired
    private KhuyenMaiRepository khuyenMaiRepo;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Khuyenmai> getKhuyenMais(Map<String, String> params) {
        return this.khuyenMaiRepo.getKhuyenMais(params);
    }

    @Override
    public Khuyenmai getKhuyenMaiById(int id) {
        return this.khuyenMaiRepo.getKhuyenMaiById(id);
    }

    @Override
    public Khuyenmai createKhuyenMai(Khuyenmai km) {
        return this.khuyenMaiRepo.createKhuyenMai(km);
    }

    @Override
    public Khuyenmai updateKhuyenMai(int id, Khuyenmai km) {
        return this.khuyenMaiRepo.updateKhuyenMai(id, km);
    }

    @Override
    public void deleteKhuyenMai(int id) {
        this.khuyenMaiRepo.deleteKhuyenMai(id);
    }

    @Override
    public boolean addOrUpdateKhuyenMai(Khuyenmai km) {

        if (!km.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(km.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                km.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(KhuyenMaiServiceImpl.class.getName()).log(Level.SEVERE, "Lỗi khi tải lên file: " + ex.getMessage(), ex);
                throw new RuntimeException("Đã xảy ra lỗi khi tải lên file. Vui lòng thử lại hoặc liên hệ hỗ trợ.");
            }
        }
        return this.khuyenMaiRepo.addOrUpdateKhuyenMai(km);
    }

    @Override
    public Long countPromotion() {
        return this.khuyenMaiRepo.countPromotion();
    }

    @Override
    public List<Khuyenmai> getKhuyenMaisExpires(Map<String, String> params) {
        return this.khuyenMaiRepo.getKhuyenMaisExpires(params);
    }

    @Override
    public void recycleBin(int id) {
        this.khuyenMaiRepo.recycleBin(id);
    }

    @Override
    public Long countPromotionExpires() {
        return this.khuyenMaiRepo.countPromotionExpires();
    }

}
