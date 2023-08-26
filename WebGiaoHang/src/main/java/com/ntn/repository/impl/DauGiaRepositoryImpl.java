/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.repository.impl;

import com.ntn.pojo.Daugia;
import com.ntn.pojo.Donhang;
import com.ntn.pojo.Shipper;
import com.ntn.repository.DauGiaRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class DauGiaRepositoryImpl implements DauGiaRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public boolean addDauGia(Daugia dg) {
        try {
            Session s = this.factory.getObject().getCurrentSession();
            s.save(dg);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

    @Override
    public List<Shipper> getShipperByDauGia(int idShipper, int idDonhang) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Shipper> criteriaQuery = criteriaBuilder.createQuery(Shipper.class);

        Root<Shipper> shipperRoot = criteriaQuery.from(Shipper.class);
        Join<Shipper, Daugia> daugiaJoin = shipperRoot.join("daugiaSet");
        Join<Daugia, Donhang> orderJoin = daugiaJoin.join("idDonhang");

        criteriaQuery.select(shipperRoot);
        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(orderJoin.get("id"), idDonhang),
                        criteriaBuilder.notEqual(shipperRoot.get("id"), idShipper)
                )
        );

        Query query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public boolean updateKqDaugia(int id) {
        try {
            Session s = this.factory.getObject().getCurrentSession();
            Daugia dg = s.get(Daugia.class, id);
            dg.setKetqua(true);
            s.update(dg);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

}
