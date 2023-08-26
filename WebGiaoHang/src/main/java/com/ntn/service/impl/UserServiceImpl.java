/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntn.pojo.User;
import com.ntn.repository.UserRepository;
import com.ntn.service.UserService;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author THANH NHAN
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepo.getUsersByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Tài khoản không tồn tại!!");
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole()));
        return new org.springframework.security.core.userdetails.User(
                user.getTaikhoan(), user.getMatkhau(), authorities);
    }

    @Override
    public List<User> getUsers(Map<String, String> params) {
        return this.userRepo.getUsers(params);
    }

    @Override
    public User getUsersByUsername(String username) {
        if (username != null) {
            return this.userRepo.getUsersByUsername(username);
        }
        return null;
    }

    @Override
    public User getUsersById(int id) {
        return this.userRepo.getUsersById(id);
    }

    @Override
    public List<User> getUserRegistShipper(Map<String, String> params) {
        return this.userRepo.getUserRegistShipper(params);
    }

    @Override
    public void updateRole(int id) {
        this.userRepo.updateRole(id);
    }

    @Override
    public void refuseShipper(int id) {
        this.userRepo.refuseShipper(id);
    }

    @Override
    public Long countUser() {
        return this.userRepo.countUser();
    }

    @Override
    public Long countUserNew() {
        return this.userRepo.countUserNew();
    }

    @Override
    public boolean authUser(String username, String password) {
        return this.userRepo.authUser(username, password);
    }

    @Override
    public User addUser(Map<String, String> params, MultipartFile avatar) {
        if (this.userRepo.checkUsername(params.get("taikhoan").trim())) {
            return null;
        } else {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            User u = new User();
            u.setTen(params.get("ten"));
            u.setTaikhoan(params.get("taikhoan").trim());
            u.setMatkhau(this.passwordEncoder.encode(params.get("matkhau")));
            u.setCmnd(params.get("cmnd"));
            u.setEmail(params.get("email"));
            u.setActive((short) 1);
            u.setSdt(params.get("sdt"));
            try {
                u.setNgaysinh(dateFormatter.parse(params.get("ngaysinh")));
            } catch (ParseException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            u.setGioitinh(params.get("gioitinh"));
            u.setUserRole("ROLE_USER");
            if (avatar != null && !avatar.isEmpty()) {
                try {
                    Map res = this.cloudinary.uploader().upload(avatar.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                    u.setAvatar(res.get("secure_url").toString());
                } catch (IOException ex) {
                    Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            this.userRepo.addUser(u);
            return u;
        }
    }

    @Override
    public void update(User u) {
        this.userRepo.update(u);
    }

}
