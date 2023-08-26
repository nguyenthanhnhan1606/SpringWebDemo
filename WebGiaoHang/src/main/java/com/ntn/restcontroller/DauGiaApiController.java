/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.restcontroller;

import com.ntn.service.DauGiaService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author THANH NHAN
 */
@RestController
@RequestMapping("/api")
public class DauGiaApiController {

    @Autowired
    private DauGiaService dauGiaSer;

    @PostMapping(value = "/adddg",produces = "application/json")
    @CrossOrigin
    public ResponseEntity<String> addDauGia(@RequestParam Map<String, String> params) {
        boolean success = dauGiaSer.addDauGia(params);
        if (success) {
            return new ResponseEntity<>("Dau gia added successfully",HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().body("Failed to add dau gia");
        }
    }

}
