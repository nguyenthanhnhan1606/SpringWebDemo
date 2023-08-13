/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.repository.impl;

import com.ntn.pojo.Donhang;
import com.ntn.repository.DonHangRepository;
import java.util.List;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author THANH NHAN
 */
@Repository
@Transactional
public class DonHangRepositoryImpl implements DonHangRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public boolean addOrUpdateDh(Donhang dh) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (dh.getId() == null) {
                s.save(dh);
            } else {
                s.update(dh);
            }
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

    @Override
    public List<Donhang> getAlls() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Donhang");
        return q.getResultList();
    }

}
