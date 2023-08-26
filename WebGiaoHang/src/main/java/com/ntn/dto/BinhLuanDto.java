/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.dto;

/**
 *
 * @author THANH NHAN
 */
import com.ntn.pojo.Shipper;
import com.ntn.pojo.User;
import java.util.Date;

import java.util.Date;

import java.util.Date;

public class BinhLuanDto {

    private Integer id;
    private String noidung;
    private Date ngaybinhluan;
    private Double danhgia;
    private Shipper idShipper;
    private User idUser;

    public BinhLuanDto() {
    }

    // Các phương thức getter và setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public Date getNgaybinhluan() {
        return ngaybinhluan;
    }

    public void setNgaybinhluan(Date ngaybinhluan) {
        this.ngaybinhluan = ngaybinhluan;
    }

    public Double getDanhgia() {
        return danhgia;
    }

    public void setDanhgia(Double danhgia) {
        this.danhgia = danhgia;
    }

    public Shipper getIdShipper() {
        return idShipper;
    }

    public void setIdShipper(Shipper idShipper) {
        this.idShipper = idShipper;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }
}
