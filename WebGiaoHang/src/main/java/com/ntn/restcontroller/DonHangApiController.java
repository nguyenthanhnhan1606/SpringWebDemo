/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.restcontroller;

import com.ntn.pojo.Donhang;
import com.ntn.pojo.User;
import com.ntn.service.DonHangService;
import com.ntn.service.UserService;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author THANH NHAN
 */
@RestController
@RequestMapping("/api")
public class DonHangApiController {

    @Autowired
    private DonHangService donHangSer;
    @Autowired
    private UserService userSer;

    @GetMapping("/donhang/daugia/{id}")
    @CrossOrigin
    public ResponseEntity<List<Donhang>> getDonHangByDauGia(@PathVariable int id, @RequestParam Map<String, String> params) {
        return new ResponseEntity<List<Donhang>>(this.donHangSer.getDonHangsByShipperId(id, params), HttpStatus.OK);
    }

    @GetMapping("/donhang/daugiasuccess/{id}")
    @CrossOrigin
    public ResponseEntity<List<Donhang>> getDonHangByDauGiaSuccess(@PathVariable int id, @RequestParam Map<String, String> params) {
        return new ResponseEntity<List<Donhang>>(this.donHangSer.getDonHangSuccessByShipperId(id, params), HttpStatus.OK);
    }

    @GetMapping("/donhang")
    @CrossOrigin
    public ResponseEntity<List<Donhang>> getAll(@RequestParam Map<String, String> params) {
        return new ResponseEntity<List<Donhang>>(this.donHangSer.getOrderByStatus(params), HttpStatus.OK);
    }

    @GetMapping("/donhang/{id}")
    @CrossOrigin
    public ResponseEntity<List<Donhang>> getAllById(@PathVariable int id, @RequestParam Map<String, String> params) {
        return new ResponseEntity<List<Donhang>>(this.donHangSer.getAlls(id, params), HttpStatus.OK);
    }

    @PostMapping(value = "/donhang", produces = "application/json")
    @CrossOrigin
    public ResponseEntity<String> addOrder(@RequestParam("file") MultipartFile file,
            @RequestParam("noigui") String noigui,
            @RequestParam("noinhan") String noinhan,
            @RequestParam("mota") String mota,
            @RequestParam("ghichu") String ghichu,
            @RequestParam("trangthai") String trangthai,
            @RequestParam("giatridh") Double giatridh,
            @RequestParam("idUser") int idUser) {
        Donhang donhang = new Donhang(); // Handle error and return an error response
        User u = this.userSer.getUsersById(idUser);
        donhang.setFile(file);
        donhang.setNoigui(noigui);
        donhang.setNoinhan(noinhan);
        donhang.setNgaytao(new Date());
        donhang.setMota(mota);
        donhang.setGhichu(ghichu);
        donhang.setGiatridh(giatridh);
        donhang.setIdUser(u);
        donhang.setTrangthai(trangthai);
        boolean added = this.donHangSer.addOrUpdateDh(donhang);
        if (added) {
            return ResponseEntity.ok("Order added successfully");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding order");

    }

    @PutMapping("/donhang/update/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public boolean updateStatusDh(@PathVariable int id, @RequestParam Map<String, String> params) {
        Donhang dh = this.donHangSer.getOrderById(id);
        dh.setTrangthai(params.get("trangthai"));
        return this.donHangSer.addOrUpdateDh(dh);
    }

}
