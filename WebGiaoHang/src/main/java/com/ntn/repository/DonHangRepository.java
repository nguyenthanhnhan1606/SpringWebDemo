/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.repository;

import com.ntn.dto.DonHangDto;
import com.ntn.pojo.Donhang;
import java.util.List;

/**
 *
 * @author THANH NHAN
 */
public interface DonHangRepository {
    List<Donhang> getAlls();
    boolean addOrUpdateDh(Donhang dh);
}
