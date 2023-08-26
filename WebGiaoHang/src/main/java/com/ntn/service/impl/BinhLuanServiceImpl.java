/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.service.impl;

import com.ntn.dto.BinhLuanDto;
import com.ntn.pojo.Binhluan;
import com.ntn.repository.BinhLuanRepository;
import com.ntn.service.BinhLuanService;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author THANH NHAN
 */
@Service
public class BinhLuanServiceImpl implements BinhLuanService {

    @Autowired
    private BinhLuanRepository binhLuanRepo;
    @Autowired
    private ModelMapper modelMapper;

    private BinhLuanDto convertToDto(Binhluan binhluan) {
        BinhLuanDto binhLuanDto = modelMapper.map(binhluan, BinhLuanDto.class);
        return binhLuanDto;
    }

    @Override
    public List<BinhLuanDto> getBinhLuanByIdShipper(int id) {
        List<Binhluan> binhLuanList = binhLuanRepo.getBinhLuanByIdShipper(id);
        return binhLuanList.stream()
                .map(binhluan -> convertToDto(binhluan))
                .collect(Collectors.toList());
    }

    @Override
    public boolean add(BinhLuanDto bld) {
        try {
            Binhluan newComment = new Binhluan();
            newComment.setNoidung(bld.getNoidung());
            newComment.setNgaybinhluan(new Date());
            newComment.setIdUser(bld.getIdUser());
            newComment.setIdShipper(bld.getIdShipper());
            newComment.setDanhgia(bld.getDanhgia());
            
            binhLuanRepo.add(newComment);

            return true; // Trả về true nếu thêm thành công
        } catch (Exception e) {
            return false; // Trả về false nếu có lỗi xảy ra
        }

    }

    @Override
    public double ratingOfShip(int id) {
        return this.binhLuanRepo.ratingOfShip(id);
    }
}
