/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.service.impl;

import com.ntn.pojo.Daugia;
import com.ntn.pojo.Donhang;
import com.ntn.pojo.Shipper;
import com.ntn.repository.DauGiaRepository;
import com.ntn.repository.DonHangRepository;
import com.ntn.repository.ShipperRepository;
import com.ntn.service.DauGiaService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author THANH NHAN
 */
@Service
public class DauGiaServiceImpl implements DauGiaService {

    @Autowired
    private DauGiaRepository dauGiaRepo;
    @Autowired
    private ShipperRepository shipperRepo;
    @Autowired
    private DonHangRepository donhangRepo;

    @Override
    public boolean addDauGia(Map<String, String> params) {
        Daugia dg = new Daugia();
        Shipper sp = shipperRepo.getShipperById(Integer.parseInt(params.get("idShipper")));
        Donhang dh = donhangRepo.getOrderById(Integer.parseInt(params.get("idDonhang")));
        dg.setIdShipper(sp);
        dg.setIdDonhang(dh);
        dg.setGia(Double.parseDouble( params.get("gia")));
        dg.setKetqua(Boolean.FALSE);
        
        dh.setTrangthai("Đang đấu giá");
        donhangRepo.addOrUpdateDh(dh);
        return this.dauGiaRepo.addDauGia(dg);
    }

    @Override
    public List<Shipper> getShipperByDauGia(int idShipper, int idDonhang) {
        return this.dauGiaRepo.getShipperByDauGia(idShipper, idDonhang);
    }

    @Override
    public boolean updateKqDaugia(int id) {
        return this.dauGiaRepo.updateKqDaugia(id);
    }

    @Override
    public List<Object[]> tanSuat(Map<String, String> params) {
        return this.dauGiaRepo.tanSuat(params);
    }

    @Override
    public List<Object[]> quyTheoNam(Map<String, String> params) {
        return this.dauGiaRepo.quyTheoNam(params);
    }

}
