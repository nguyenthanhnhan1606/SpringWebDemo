/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.service;

import com.ntn.pojo.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author THANH NHAN
 */
public interface UserService extends UserDetailsService {

    List<User> getUsers(Map<String, String> params);

    List<User> getUserRegistShipper(Map<String, String> params);

    User getUsersByUsername(String username);

    User getUsersById(int id);

}
