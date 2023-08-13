/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.restcontroller;

import com.ntn.pojo.Donhang;
import com.ntn.service.DonHangService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author THANH NHAN
 */
@RestController
@RequestMapping("/api")
public class DonHangApiController {

    @Autowired
    private DonHangService donHangSer;

    @GetMapping("/donhang")
    public ResponseEntity<List<Donhang>> getAll(){
        return new ResponseEntity<List<Donhang>>(this.donHangSer.getAlls(),HttpStatus.OK);
    }
    
    
    @PostMapping(value = "/donhang", produces = "application/json")
    public ResponseEntity<Donhang> add(@RequestBody Donhang dh) {
        boolean added = donHangSer.addOrUpdateDh(dh);
        if (added) {
            return ResponseEntity.ok(dh);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
