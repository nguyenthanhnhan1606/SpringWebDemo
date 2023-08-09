/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.service.impl;

import com.ntn.pojo.User;
import com.ntn.repository.UserRepository;
import com.ntn.service.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author THANH NHAN
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

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
        return this.userRepo.getUsersByUsername(username);
    }

    @Override
    public User getUsersById(int id) {
        return this.userRepo.getUsersById(id);
    }

    @Override
    public List<User> getUserRegistShipper(Map<String, String> params) {
        return this.userRepo.getUserRegistShipper(params);
    }

}
