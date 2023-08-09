/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.repository;

import com.ntn.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author THANH NHAN
 */
public interface UserRepository {

    List<User> getUsers(Map<String, String> params);

    List<User> getUserRegistShipper(Map<String, String> params);

    User getUsersByUsername(String username);

    User getUsersById(int id);

}
