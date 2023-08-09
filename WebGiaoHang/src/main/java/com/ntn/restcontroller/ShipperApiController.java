/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.restcontroller;

import com.ntn.dto.ShipperDto;
import com.ntn.pojo.Shipper;
import com.ntn.service.ShipperService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/api/shippers")
    public ResponseEntity<List<Shipper>> getShipper(Map<String, String> params) {
        return new ResponseEntity<>(this.shipperService.getShippers(params), HttpStatus.OK);
    }

    @GetMapping(value = "/api/shippers/{id}",produces = "application/json")
    public ResponseEntity<Shipper> getShipperById(@PathVariable int id) {
        return new ResponseEntity<>(this.shipperService.getShipperById(id), HttpStatus.OK);
    }

    @PostMapping("/api/shippers/")
    public ResponseEntity<Shipper> createShipper(@RequestBody Shipper sp) {
        return new ResponseEntity<>(this.shipperService.createShipperNew(sp), HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/shippers/update/{id}")
    public ResponseEntity<ShipperDto> updateShipper(@PathVariable("id") int id, @RequestBody ShipperDto sp1) {
        return new ResponseEntity<>((this.shipperService.updateShipper(id, sp1)), HttpStatus.OK);
    }

    @DeleteMapping("/api/shippers/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void deleteShipper(@PathVariable int id) {
        this.shipperService.deleteShipper(id);
    }

}
