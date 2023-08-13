/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntn.repository;

import com.ntn.dto.ShipperDto;
import com.ntn.pojo.Khuyenmai;
import com.ntn.pojo.Shipper;
import com.ntn.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author THANH NHAN
 */
public interface ShipperRepository {

    List<Shipper> getShippers(Map<String, String> params);

    Shipper getShipperById(int id);

    public boolean addOrUpdateShipper(Shipper sp);

    Shipper updateShipper(int id, Shipper sp1);

    Shipper createShipper(Shipper sp);

    void deleteShipper(int id);

    void recycleBin(int id);

    Long countShipper();
}
