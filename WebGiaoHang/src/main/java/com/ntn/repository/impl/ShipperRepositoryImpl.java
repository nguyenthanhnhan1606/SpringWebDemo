/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.repository.impl;

import com.ntn.pojo.Shipper;
import com.ntn.pojo.User;
import com.ntn.repository.ShipperRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author THANH NHAN
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class ShipperRepositoryImpl implements ShipperRepository {

    @Autowired
    private Environment env;
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Shipper> getShippers(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Shipper> q = b.createQuery(Shipper.class);
        Root<Shipper> sRoot = q.from(Shipper.class);
        Join<Shipper, User> userJoin = sRoot.join("user");
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(sRoot.get("trangthai"), "Đã xác nhận"));
       

        String search = params.get("search");
        if (search != null) {
            predicates.add(b.like(userJoin.get("ten"), "%" + search + "%"));
        }

        q.where(predicates.toArray(Predicate[]::new));

        Query query = session.createQuery(q);

        if (params != null) {
            String page = params.get("page");
            if (page != null && !page.isEmpty()) {
                int p = Integer.parseInt(page);
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));

                query.setMaxResults(pageSize);
                query.setFirstResult((p - 1) * pageSize);
            }
        }

        return query.getResultList();

    }

    @Override
    public boolean addOrUpdateShipper(Shipper sp) {
        Session session = this.factory.getObject().getCurrentSession();
        User u = sp.getUser();
        try {
            if (sp.getId() == null) {
                session.save(u);
            } else {
                session.update(u);
            }
            return true;
        } catch (HibernateException ex) {
            Logger.getLogger(ShipperRepositoryImpl.class.getName()).log(Level.SEVERE, "Lỗi khi adorupdate: " + ex.getMessage(), ex);
            throw new RuntimeException("Đã xảy ra lỗi khi tải lên them shipper. Vui lòng thử lại hoặc liên hệ hỗ trợ." + ex.getMessage());
        }
    }

    @Override
    public Shipper getShipperById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        Shipper sp = session.get(Shipper.class, id);
        return sp;
    }

    @Override
    public Shipper createShipper(Shipper sp) {
        Session session = this.factory.getObject().getCurrentSession();
        session.save(sp);
        return sp;
    }

    @Override
    public Shipper updateShipper(int id, Shipper sp1) {
        Session session = this.factory.getObject().getCurrentSession();
        Shipper sp = session.get(Shipper.class, id);
        sp.setTrangthai(sp1.getTrangthai());
        session.update(sp);
        return sp;
    }

    @Override
    public void deleteShipper(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        Shipper sp = session.get(Shipper.class, id);
        User u = sp.getUser();
        u.setUserRole("ROLE_USER");
        session.save(u);
        session.delete(sp);
    }

    @Override
    public Long countShipper() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT Count(*) FROM Shipper");

        return Long.parseLong(q.getSingleResult().toString());
    }

}
