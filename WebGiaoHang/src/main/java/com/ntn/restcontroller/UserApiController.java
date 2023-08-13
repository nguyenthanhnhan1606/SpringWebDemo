/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.restcontroller;

import com.ntn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author THANH NHAN
 */
@RestController
public class UserApiController {
    @Autowired
    private UserService userSer;
    
    @PutMapping("/api/usertoshipper/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void userToShipper(@PathVariable int id){
        this.userSer.updateRole(id);
    }
    
    @PutMapping("/api/refuse/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void refuseShipper(@PathVariable int id){
        this.userSer.refuseShipper(id);
    }
}
