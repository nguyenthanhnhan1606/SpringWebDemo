/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.restcontroller;

import com.ntn.dto.BinhLuanDto;
import com.ntn.pojo.Binhluan;
import com.ntn.pojo.Donhang;
import com.ntn.pojo.Shipper;
import com.ntn.pojo.User;
import com.ntn.service.BinhLuanService;
import com.ntn.service.ShipperService;
import com.ntn.service.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author THANH NHAN
 */
@RestController
@RequestMapping("/api")
public class BinhLuanApiController {

    @Autowired
    private BinhLuanService binhLuanSer;
    @Autowired
    private ShipperService shipperSer;
    @Autowired
    private UserService userSer;

    @GetMapping("/binhluan/{id}")
    @CrossOrigin
    public ResponseEntity<List<BinhLuanDto>> getAll(@PathVariable int id) {
        return new ResponseEntity<List<BinhLuanDto>>(this.binhLuanSer.getBinhLuanByIdShipper(id), HttpStatus.OK);
    }

    @GetMapping("/danhgia/{id}")
    @CrossOrigin
    public double getRating(@PathVariable int id) {
        return this.binhLuanSer.ratingOfShip(id);
    }

    @PostMapping("/binhluan")
    @CrossOrigin
    public ResponseEntity<String> addComment(@RequestParam Map<String, String> params) {
        try {
            User user = userSer.getUsersById(Integer.parseInt(params.get("idUser")));
            Shipper shipper = shipperSer.getShipperById(Integer.parseInt(params.get("idShipper")));

            BinhLuanDto binhLuanDto = new BinhLuanDto();
            binhLuanDto.setDanhgia(Double.parseDouble(params.get("danhgia")));
            binhLuanDto.setNoidung(params.get("noidung"));
            binhLuanDto.setIdUser(user);
            binhLuanDto.setIdShipper(shipper);

            boolean added = binhLuanSer.add(binhLuanDto);

            if (added) {
                return ResponseEntity.ok("Bình luận đã được thêm thành công");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Có lỗi xảy ra khi thêm bình luận");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Có lỗi xảy ra khi thêm bình luận" + e.getMessage());
        }
    }
}
