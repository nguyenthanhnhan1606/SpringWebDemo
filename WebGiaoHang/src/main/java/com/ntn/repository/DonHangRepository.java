/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.repository;

import com.ntn.dto.DonHangDto;
import com.ntn.pojo.Donhang;
import java.util.List;
import java.util.Map;

/**
 *
 * @author THANH NHAN
 */
public interface DonHangRepository {

    List<Donhang> getAlls(int id, Map<String, String> params);

    List<Donhang> getOrderByStatus(Map<String, String> params);

    List<Donhang> getDonHangsByShipperId(int id, Map<String, String> params);

    List<Donhang> getDonHangSuccessByShipperId(int id, Map<String, String> params);

    Donhang getOrderById(int id);

    boolean addOrUpdateDh(Donhang dh);

    Long countOrder();
}
