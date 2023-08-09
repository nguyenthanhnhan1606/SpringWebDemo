/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author THANH NHAN
 */
@Getter
@Setter
public class KhuyenMaiDto {
    private Integer id;
    private String loaikhuyenmai;
    private String mota;
    private Date ngaybd;
    private Date ngaykt;
    private String image;
    private boolean active;
}
