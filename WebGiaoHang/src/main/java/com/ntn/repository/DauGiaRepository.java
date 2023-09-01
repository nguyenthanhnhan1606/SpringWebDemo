/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntn.repository;

import com.ntn.pojo.Daugia;
import com.ntn.pojo.Shipper;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author THANH NHAN
 */
public interface DauGiaRepository {
    boolean addDauGia(Daugia dg);
    List<Shipper> getShipperByDauGia(int idShipper, int idDonhang);
    boolean updateKqDaugia(int id);
    List<Object[]> tanSuat(Map <String, String> params);
    List<Object[]> quyTheoNam(Map<String, String> params);
}
