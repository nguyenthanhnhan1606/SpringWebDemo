/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntn.repository;

import com.ntn.pojo.Binhluan;
import java.util.List;

/**
 *
 * @author THANH NHAN
 */
public interface BinhLuanRepository {
    List<Binhluan> getBinhLuanByIdShipper(int id);
    double ratingOfShip(int id);
    
    boolean add(Binhluan bl);
}
