/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntn.service;

import com.ntn.pojo.Khuyenmai;
import java.util.List;
import java.util.Map;

/**
 *
 * @author THANH NHAN
 */
public interface KhuyenMaiService {

    List<Khuyenmai> getKhuyenMais(Map<String, String> params);

    Khuyenmai getKhuyenMaiById(int id);

    boolean addOrUpdateKhuyenMai(Khuyenmai km);

    Khuyenmai createKhuyenMai(Khuyenmai km);

    Khuyenmai updateKhuyenMai(int id, Khuyenmai km);

    void deleteKhuyenMai(int id);

    Long countPromotion();

}
