/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.restcontroller;

import com.ntn.components.JwtService;
import com.ntn.pojo.User;
import com.ntn.service.UserService;
import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author THANH NHAN
 */
@RestController
public class UserApiController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userSer;

    @PostMapping("/api/login")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody User user) {
        if (this.userSer.authUser(user.getTaikhoan().trim(), user.getMatkhau()) == true) {
            String token = this.jwtService.generateTokenLogin(user.getTaikhoan().trim());

            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("error", HttpStatus.OK);
    }

    @PostMapping(value="/api/register",produces = "application/json")
    @CrossOrigin
    public ResponseEntity<User> addUser(@RequestParam Map<String, String> params, @RequestPart MultipartFile avatar) {
        User user = this.userSer.addUser(params, avatar);
        if(user != null)
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/api/current-user/", produces = "application/json")
    @CrossOrigin
    public ResponseEntity<User> details(Principal user) {
        User u = this.userSer.getUsersByUsername(user.getName());
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @PutMapping("/api/usertoshipper/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void userToShipper(@PathVariable int id) {
        this.userSer.updateRole(id);
    }

    @PutMapping("/api/refuse/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void refuseShipper(@PathVariable int id) {
        this.userSer.refuseShipper(id);
    }
}
