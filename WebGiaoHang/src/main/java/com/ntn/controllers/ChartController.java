/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.controllers;

import com.ntn.service.DauGiaService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author THANH NHAN
 */
@Controller
public class ChartController {
    @Autowired
    private DauGiaService dauGiaSer;
    @RequestMapping("/admin/doanhthu")
    public String doanhThu(Model model,@RequestParam Map<String, String> params){
        model.addAttribute("chartDt",this.dauGiaSer.quyTheoNam(params));
        return "chartDoanhThu";
    }
    
    @RequestMapping("/admin/tansuat")
    public String tanSuat(Model model,@RequestParam Map<String, String> params){
        model.addAttribute("chart",this.dauGiaSer.tanSuat(params));
        return "chartTanSuat";
    }
}
