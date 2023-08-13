/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 *
 * @author THANH NHAN
 */
public class DonHangDto {

    private int id;
    private String noigui;
    private String noinhan;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date ngaytao;
    private String mota;
    private String ghichu;
    private String image;

    private double giatridh;
    private String trangthai;
    private int idUser;
    private int idKhuyenmai;
    private int idShipper;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the noigui
     */
    public String getNoigui() {
        return noigui;
    }

    /**
     * @param noigui the noigui to set
     */
    public void setNoigui(String noigui) {
        this.noigui = noigui;
    }

    /**
     * @return the noinhan
     */
    public String getNoinhan() {
        return noinhan;
    }

    /**
     * @param noinhan the noinhan to set
     */
    public void setNoinhan(String noinhan) {
        this.noinhan = noinhan;
    }

    /**
     * @return the ngaytao
     */
    public Date getNgaytao() {
        return ngaytao;
    }

    /**
     * @param ngaytao the ngaytao to set
     */
    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    /**
     * @return the mota
     */
    public String getMota() {
        return mota;
    }

    /**
     * @param mota the mota to set
     */
    public void setMota(String mota) {
        this.mota = mota;
    }

    /**
     * @return the giatridh
     */
    public double getGiatridh() {
        return giatridh;
    }

    /**
     * @param giatridh the giatridh to set
     */
    public void setGiatridh(double giatridh) {
        this.giatridh = giatridh;
    }

    /**
     * @return the trangthai
     */
    public String getTrangthai() {
        return trangthai;
    }

    /**
     * @param trangthai the trangthai to set
     */
    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    /**
     * @return the idUser
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * @param idUser the idUser to set
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /**
     * @return the idKhuyenmai
     */
    public int getIdKhuyenmai() {
        return idKhuyenmai;
    }

    /**
     * @param idKhuyenmai the idKhuyenmai to set
     */
    public void setIdKhuyenmai(int idKhuyenmai) {
        this.idKhuyenmai = idKhuyenmai;
    }

    /**
     * @return the idShipper
     */
    public int getIdShipper() {
        return idShipper;
    }

    /**
     * @param idShipper the idShipper to set
     */
    public void setIdShipper(int idShipper) {
        this.idShipper = idShipper;
    }

    /**
     * @return the ghichu
     */
    public String getGhichu() {
        return ghichu;
    }

    /**
     * @param ghichu the ghichu to set
     */
    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }
}
