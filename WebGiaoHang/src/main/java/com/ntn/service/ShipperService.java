/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntn.service;

import com.ntn.dto.ShipperDto;
import com.ntn.pojo.Shipper;
import com.ntn.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author THANH NHAN
 */
public interface ShipperService {

    List<Shipper> getShippers(Map<String, String> params);

    Shipper getShipperById(int id);

    public boolean addOrUpdateShipper(Shipper sp);

    ShipperDto updateShipper(int id, ShipperDto sp1);

    Shipper createShipperNew(Shipper sp);

    boolean createShipper(User u);

    void deleteShipper(int id);

    void recycleBin(int id);

    Long countShipper();
}
