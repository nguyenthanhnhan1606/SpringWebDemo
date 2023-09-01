/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntn.service;

import com.ntn.pojo.Donhang;
import java.util.List;
import java.util.Map;

/**
 *
 * @author THANH NHAN
 */
public interface DonHangService {

    List<Donhang> getAlls(int id, Map<String, String> params);

    List<Donhang> getAllOrder(Map<String, String> params);

    List<Donhang> getOrderByStatus(Map<String, String> params);
    List<Donhang> getOrderByStatusNew(Map<String, String> params);
    List<Donhang> getOrderByStatusDG(Map<String, String> params);

    Donhang getOrderById(int id);

    List<Donhang> getDonHangsByShipperId(int id, Map<String, String> params);

    List<Donhang> getDonHangSuccessByShipperId(int id, Map<String, String> params);

    boolean addOrUpdateDh(Donhang dh);

    Long countOrder();

    Long countOrderNew();

    Long countOrderDG();

}
