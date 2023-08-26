/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntn.service;

import com.ntn.dto.BinhLuanDto;
import com.ntn.pojo.Binhluan;
import java.util.List;

/**
 *
 * @author THANH NHAN
 */
public interface BinhLuanService {

    public List<BinhLuanDto> getBinhLuanByIdShipper(int id);

    boolean add(BinhLuanDto bld);

    double ratingOfShip(int id);

}
