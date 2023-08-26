/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntn.repository;

import com.ntn.pojo.Khuyenmai;
import java.util.List;
import java.util.Map;

/**
 *
 * @author THANH NHAN
 */
public interface KhuyenMaiRepository {

    List<Khuyenmai> getKhuyenMais(Map<String, String> params);

    List<Khuyenmai> getKhuyenMaisExpires(Map<String, String> params);

    Khuyenmai getKhuyenMaiById(int id);

    Khuyenmai createKhuyenMai(Khuyenmai km);

    Khuyenmai updateKhuyenMai(int id, Khuyenmai km);

    void deleteKhuyenMai(int id);

    void recycleBin(int id);

    boolean addOrUpdateKhuyenMai(Khuyenmai km);

    Long countPromotion();
    
    Long countPromotionExpires();

}
