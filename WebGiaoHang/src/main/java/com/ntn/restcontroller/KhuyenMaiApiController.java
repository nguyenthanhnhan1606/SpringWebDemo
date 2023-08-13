/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.restcontroller;

import com.ntn.pojo.Khuyenmai;
import com.ntn.service.KhuyenMaiService;
import java.util.List;
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
public class KhuyenMaiApiController {

    @Autowired
    private KhuyenMaiService khuyenMaiSer;

    @GetMapping("/api/khuyenmais")
    public ResponseEntity<List<Khuyenmai>> getKhuyenMais() {
        return new ResponseEntity<>(this.khuyenMaiSer.getKhuyenMais(null), HttpStatus.OK);
    }

    @GetMapping(value = "/api/khuyenmais/{id}",produces = "application/json")
    public ResponseEntity<Khuyenmai> getKhuyenMaiById(@PathVariable int id) {
        return new ResponseEntity<>(this.khuyenMaiSer.getKhuyenMaiById(id), HttpStatus.OK);
    }

    @PostMapping("/api/khuyenmais")
    public ResponseEntity<Khuyenmai> createKhuyenMai(@RequestBody Khuyenmai km) {
        return new ResponseEntity<>(this.khuyenMaiSer.createKhuyenMai(km), HttpStatus.CREATED);
    }

    @PutMapping("/api/khuyenmais/update/{id}")
    public ResponseEntity<Khuyenmai> updateShipper(@PathVariable("id") int id, @RequestBody Khuyenmai km) {
        return new ResponseEntity<>((this.khuyenMaiSer.updateKhuyenMai(id, km)), HttpStatus.OK);
    }
    
    @DeleteMapping("/api/khuyenmais/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteKhuyenMai(@PathVariable int id){
        this.khuyenMaiSer.deleteKhuyenMai(id);
    }
    
    @PutMapping("/api/khuyenmais/recycle/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void recycleKhuyenMai(@PathVariable int id){
        this.khuyenMaiSer.recycleBin(id);
    }
}
