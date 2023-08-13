/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.controllers;

import com.ntn.pojo.Shipper;
import com.ntn.pojo.User;
import com.ntn.service.ShipperService;
import com.ntn.service.UserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author THANH NHAN
 */
@Controller
public class UserController {

    @Autowired
    private UserService userSer;
    @Autowired
    private ShipperService shipperSer;

    @GetMapping("/admin/listUser")
    public String getUsers(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("userss", this.userSer.getUsers(params));
        return "listUser";
    }

    @GetMapping("/admin/addShipper/{id}")
    public String getUsers(Model model, @PathVariable int id) {
        model.addAttribute("userUpdate", this.userSer.getUsersById(id));
        return "addShipper";
    }

    @PostMapping("/admin/addShipper")
    public String getUsers(@ModelAttribute(value = "userUpdate") User u) {
        if (shipperSer.createShipper(u)) {
            return "redirect:/admin/shippers";
        }
        return "addShipper";
    }

    @GetMapping("/admin/listUserRegisterShipper")
    public String getUsersRegisterShipper(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("userss", this.userSer.getUserRegistShipper(params));
        model.addAttribute("flagShipper",1);    
        return "listUser";
    }

}
