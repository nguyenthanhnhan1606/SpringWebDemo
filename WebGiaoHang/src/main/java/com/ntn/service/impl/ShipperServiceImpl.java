/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntn.dto.ShipperDto;
import com.ntn.pojo.Shipper;
import com.ntn.pojo.User;
import com.ntn.repository.ShipperRepository;
import com.ntn.repository.UserRepository;
import com.ntn.service.ShipperService;
import java.io.IOException;
import java.util.ArrayList;
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
public class ShipperServiceImpl implements ShipperService {
    
    @Autowired
    private ShipperRepository shipperRepo;
    
    @Autowired
    private Cloudinary cloudinary;
    
    @Autowired
    private UserRepository userRepo;
    
    public ShipperDto toDto(Shipper sp) {
        ShipperDto spd = new ShipperDto();
        spd.setId(sp.getId());
        spd.setTrangthai(sp.getTrangthai());
        return spd;
    }
    
    public List<ShipperDto> toDsDto(List<Shipper> list) {
        List<ShipperDto> listDto = new ArrayList<>();
        for (Shipper s : list) {
            listDto.add(toDto(s));
        }
        return listDto;
    }
    
    public Shipper toEntity(ShipperDto sp) {
        Shipper spd = new Shipper();
        spd.setId(sp.getId());
        spd.setTrangthai(sp.getTrangthai());
        return spd;
    }
    
    @Override
    public List<Shipper> getShippers(Map<String, String> params) {
        return this.shipperRepo.getShippers(params);
    }
    
    @Override
    public Shipper getShipperById(int id) {
        return this.shipperRepo.getShipperById(id);
    }
    
    @Override
    public boolean addOrUpdateShipper(Shipper sp) {
        if (!sp.getUser().getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(sp.getUser().getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                sp.getUser().setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(KhuyenMaiServiceImpl.class.getName()).log(Level.SEVERE, "Lỗi khi tải lên file: " + ex.getMessage(), ex);
                throw new RuntimeException("Đã xảy ra lỗi khi tải lên file. Vui lòng thử lại hoặc liên hệ hỗ trợ.");
            }
        }
        return this.shipperRepo.addOrUpdateShipper(sp);
    }
    
    @Override
    public Shipper createShipperNew(Shipper sp) {
        return this.shipperRepo.createShipper(sp);
    }
    
    
    @Override
    public void deleteShipper(int id) {
        this.shipperRepo.deleteShipper(id);
    }
    
    @Override
    public Long countShipper() {
        return this.shipperRepo.countShipper();
    }
    
    public boolean createShipper(User u) {
        try {
            Shipper sp1 = shipperRepo.getShipperById(u.getId());
            if (sp1 == null) {
                Shipper sp = new Shipper();
                u.setUserRole("ROLE_SHIPPER");
                sp.setId(u.getId());
                sp.setTrangthai("Đã xác nhận");
                sp.setUser(u);
                if (shipperRepo.createShipper(sp) != null) {
                    if (addOrUpdateShipper(sp)) {
                        return true;
                    }
                }
            } else {
                u.setUserRole("ROLE_SHIPPER");
                sp1.setTrangthai("Đã xác nhận");
                sp1.setUser(u);
                if (addOrUpdateShipper(sp1)) {
                    return true;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(KhuyenMaiServiceImpl.class.getName()).log(Level.SEVERE, "Lỗi khi tải lên file: " + ex.getMessage(), ex);
            throw new RuntimeException("Đã xảy ra lỗi khi tải lên file. Vui lòng thử lại hoặc liên hệ hỗ trợ." + ex.getMessage());
        }
        return false;
    }
    
    @Override
    public void recycleBin(int id) {
        this.shipperRepo.recycleBin(id);
    }
    
}
