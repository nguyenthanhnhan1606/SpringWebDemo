/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntn.service;

import com.ntn.pojo.Donhang;
import java.util.List;

/**
 *
 * @author THANH NHAN
 */
public interface DonHangService {

    List<Donhang> getAlls();

    boolean addOrUpdateDh(Donhang dh);
}
