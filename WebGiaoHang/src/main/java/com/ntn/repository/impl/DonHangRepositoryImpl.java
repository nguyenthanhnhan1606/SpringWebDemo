/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.repository.impl;

import com.ntn.pojo.Daugia;
import com.ntn.pojo.Donhang;
import com.ntn.pojo.Shipper;
import com.ntn.pojo.User;
import com.ntn.repository.DonHangRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import org.springframework.core.env.Environment;
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
    private Environment env;
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
    public List<Donhang> getAlls(int id, Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Donhang> q = b.createQuery(Donhang.class);
        Root<Donhang> dRoot = q.from(Donhang.class);

        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(b.equal(dRoot.get("trangthai"), "Đã xác nhận"));
        predicates.add(b.equal(dRoot.get("idUser").get("id"), id));
        if (params != null) {
            String search = params.get("search");
            if (search != null) {
                Predicate p1 = b.like(dRoot.get("noigui"), "%" + search + "%");
                Predicate p2 = b.like(dRoot.get("noinhan"), "%" + search + "%");

                try {
                    int idValue = Integer.parseInt(search);
                    Predicate p3 = b.equal(dRoot.get("id"), idValue);
                    predicates.add(b.or(p1, p2, p3));
                } catch (NumberFormatException e) {
                    predicates.add(b.or(p1, p2));
                }

            }
            String trangthai = params.get("trangthai");
            if (trangthai != null) {
                predicates.add(b.like(dRoot.get("trangthai"), trangthai));
            }
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
    public List<Donhang> getOrderByStatus(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Donhang> q = b.createQuery(Donhang.class);
        Root<Donhang> dRoot = q.from(Donhang.class);

        List<Predicate> predicates = new ArrayList<>();
        String trangthai = params.get("trangthai");
        if (trangthai != null) {
            predicates.add(b.like(dRoot.get("trangthai"), trangthai));
        }
        String search = params.get("search");
        if (search != null) {
            Predicate p1 = b.like(dRoot.get("noigui"), "%" + search + "%");
            Predicate p2 = b.like(dRoot.get("noinhan"), "%" + search + "%");

            try {
                int idValue = Integer.parseInt(search);
                Predicate p3 = b.equal(dRoot.get("id"), idValue);
                predicates.add(b.or(p1, p2, p3));
            } catch (NumberFormatException e) {
                predicates.add(b.or(p1, p2));
            }

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
    public Long countOrder() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT Count(*) FROM Donhang");

        return Long.parseLong(q.getSingleResult().toString());
    }

    @Override
    public Donhang getOrderById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Donhang dh = s.get(Donhang.class, id);
        return dh;
    }

    @Override
    public List<Donhang> getDonHangsByShipperId(int id, Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Donhang> q = b.createQuery(Donhang.class);
        Root<Donhang> dRoot = q.from(Donhang.class);
        Join<Daugia, Donhang> daugiaToDonhangJoin = dRoot.join("daugiaSet");

        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(b.equal(dRoot.get("trangthai"), "Đã xác nhận"));
        predicates.add(b.equal(daugiaToDonhangJoin.get("idShipper").get("id"), id));
        String trangthai = params.get("trangthai");
        if (trangthai != null) {
            predicates.add(b.equal(dRoot.get("trangthai"), trangthai));
        }
        String search = params.get("search");
        if (search != null) {
            Predicate p1 = b.like(dRoot.get("noigui"), "%" + search + "%");
            Predicate p2 = b.like(dRoot.get("noinhan"), "%" + search + "%");

            try {
                int idValue = Integer.parseInt(search);
                Predicate p3 = b.equal(dRoot.get("id"), idValue);
                predicates.add(b.or(p1, p2, p3));
            } catch (NumberFormatException e) {
                predicates.add(b.or(p1, p2));
            }

        }

        q.where(predicates.toArray(Predicate[]::new));

        q.groupBy(dRoot.get("id"));
        return session.createQuery(q).getResultList();

    }

    @Override
    public List<Donhang> getDonHangSuccessByShipperId(int id, Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Donhang> q = b.createQuery(Donhang.class);
        Root<Donhang> dRoot = q.from(Donhang.class);
        Join<Daugia, Donhang> daugiaToDonhangJoin = dRoot.join("daugiaSet");

        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(b.equal(dRoot.get("trangthai"), "Đã xác nhận"));
        predicates.add(b.equal(daugiaToDonhangJoin.get("idShipper").get("id"), id));
        predicates.add(b.equal(daugiaToDonhangJoin.get("ketqua"), true));
        predicates.add(b.equal(dRoot.get("trangthai"), "Đang giao"));
        if (params != null) {
            String search = params.get("search");
            if (search != null) {
                Predicate p1 = b.like(dRoot.get("noigui"), "%" + search + "%");
                Predicate p2 = b.like(dRoot.get("noinhan"), "%" + search + "%");
                predicates.add(b.or(p1, p2));
            }

        }

        q.where(predicates.toArray(Predicate[]::new));

        return session.createQuery(q).getResultList();

    }

}
