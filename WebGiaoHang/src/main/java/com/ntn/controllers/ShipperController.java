/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.controllers;

import com.ntn.pojo.Khuyenmai;
import com.ntn.pojo.Shipper;
import com.ntn.pojo.User;
import com.ntn.service.ShipperService;
import com.ntn.service.UserService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
public class ShipperController {
    @Autowired
    private Environment env;
    @Autowired
    private ShipperService shipperSer;

    @GetMapping("/admin/shippers")
    public String getShipper(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("shippers", this.shipperSer.getShippers(params));

        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.shipperSer.countShipper();
        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));

        return "shipper";
    }

    @GetMapping("/admin/addOrUpdateShipper")
    public String showAddForm(Model model) {
        model.addAttribute("shipper", new Shipper());
        return "addOrUpdateShipper";
    }

    @GetMapping("/admin/addOrUpdateShipper/{id}")
    public String showUpdateForm(Model model, @PathVariable int id) {
        model.addAttribute("shipper", this.shipperSer.getShipperById(id));
        return "addOrUpdateShipper";
    }

    @PostMapping("/admin/addOrUpdateShipper")
    public String add(@ModelAttribute(value = "shipper") Shipper sp) {
        if (shipperSer.addOrUpdateShipper(sp) == true) {
            return "redirect:/admin/shippers";
        }
        return "addOrUpdateShipper";
    }

}
