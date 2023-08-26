/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.restcontroller;

import com.ntn.dto.ShipperDto;
import com.ntn.pojo.Shipper;
import com.ntn.pojo.User;
import com.ntn.service.ShipperService;
import com.ntn.service.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author THANH NHAN
 */
@RestController
public class ShipperApiController {

    @Autowired
    private ShipperService shipperService;
    @Autowired
    private UserService userSer;

    @GetMapping("/api/shippers")
    @CrossOrigin
    public ResponseEntity<List<Shipper>> getShipper(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.shipperService.getShippers(params), HttpStatus.OK);
    }

    @GetMapping(value = "/api/shippers/{id}", produces = "application/json")
    @CrossOrigin
    public ResponseEntity<Shipper> getShipperById(@PathVariable int id) {
        return new ResponseEntity<>(this.shipperService.getShipperById(id), HttpStatus.OK);
    }

    @PostMapping("/api/shippers")
    @CrossOrigin
    public ResponseEntity<Shipper> createShipper(@RequestParam Map<String, String> params) {
        User u = userSer.getUsersById(Integer.parseInt(params.get("idUser")));
        u.setCmnd(params.get("cmnd"));
        Shipper sp1 = shipperService.getShipperById(u.getId());
        if(sp1 != null)
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        userSer.update(u);
        Shipper sp = new Shipper();
        sp.setId(u.getId());
        sp.setTrangthai("Chờ xác nhận");
        sp.setUser(u);
        return new ResponseEntity<>(this.shipperService.createShipperNew(sp), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/shippers/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShipper(@PathVariable int id) {
        this.shipperService.deleteShipper(id);
    }

    @PutMapping("/api/shippers/recyclebin/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void recycleBin(@PathVariable int id) {
        this.shipperService.recycleBin(id);
    }

}
