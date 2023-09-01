/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.controllers;

import com.ntn.service.DonHangService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author THANH NHAN
 */
@Controller
public class OrderController {

    @Autowired
    private DonHangService donHangSer;
    @Autowired
    private Environment env;

    @GetMapping("/admin/donhangmoi")
    public String getOrderNew(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("orders", this.donHangSer.getOrderByStatusNew(params));

        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.donHangSer.countOrderNew();
        model.addAttribute("newFlag", 2);
        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "order";
    }

    @GetMapping("/admin/donhangdg")
    public String getOrderDg(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("orders", this.donHangSer.getOrderByStatusDG(params));

        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.donHangSer.countOrderDG();
        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "order";
    }

    @GetMapping("/admin/donhang")
    public String getAllOrder(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("orders", this.donHangSer.getAllOrder(params));
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.donHangSer.countOrder();
        model.addAttribute("newFlag", 1);

        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "order";
    }
}
