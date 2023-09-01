/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntn.service;

import com.ntn.pojo.Daugia;
import com.ntn.pojo.Donhang;
import com.ntn.pojo.Shipper;
import java.util.List;
import java.util.Map;

/**
 *
 * @author THANH NHAN
 */
public interface DauGiaService {

    boolean addDauGia(Map<String, String> params);

    boolean updateKqDaugia(int id);

    List<Object[]> tanSuat(Map<String, String> params);

    List<Shipper> getShipperByDauGia(int idShipper, int idDonhang);

    List<Object[]> quyTheoNam(Map<String, String> params);
}
