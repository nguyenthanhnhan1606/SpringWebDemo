/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.repository.impl;

import com.ntn.pojo.Daugia;
import com.ntn.pojo.Donhang;
import com.ntn.pojo.Shipper;
import com.ntn.repository.DauGiaRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
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
    @Autowired
    private SimpleDateFormat f;

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

    @Override
    public List<Object[]> tanSuat(Map<String, String> params) {
        Session s = factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object> q = b.createQuery(Object.class);

        Root rDonHang = q.from(Donhang.class);
        Root rDauGia = q.from(Daugia.class);
        q.multiselect(b.count(rDauGia.get("id")), rDonHang.get("trangthai"), b.sum(rDauGia.get("gia")));
        Predicate p1 = b.equal(rDonHang.get("id"), rDauGia.get("idDonhang"));
        Predicate p2 = b.equal(rDauGia.get("ketqua"), true);
        List<Predicate> predicates = new ArrayList<>();
        if (params != null) {
            String fd = params.get("fromDate");
            if (fd != null && !fd.isEmpty()) {
                try {
                    predicates.add(b.greaterThanOrEqualTo(rDonHang.get("ngaytao"), f.parse(fd)));
                } catch (Exception ex) {
                    Logger.getLogger(DauGiaRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            String td = params.get("toDate");
            if (td != null && !td.isEmpty()) {
                try {
                    predicates.add(b.lessThanOrEqualTo(rDonHang.get("ngaytao"), f.parse(td)));
                } catch (Exception ex) {
                    Logger.getLogger(DauGiaRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        predicates.add(b.and(p1, p2));
        q.where(predicates.toArray(Predicate[]::new)).groupBy(rDonHang.get("trangthai"));
        Query query1 = s.createQuery(q);
        return query1.getResultList();
    }

    @Override
    public List<Object[]> quyTheoNam(Map<String, String> params) {
        Session s = factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root<Donhang> rDonHang = q.from(Donhang.class);
        Root<Daugia> rDauGia = q.from(Daugia.class);

        Expression<Integer> monthExpression = b.function("MONTH", Integer.class, rDonHang.get("ngaytao"));

        Expression<Integer> quarterExpression = b.<Integer>selectCase()
                .when(b.le(monthExpression, 3), 1)
                .when(b.le(monthExpression, 6), 2)
                .when(b.le(monthExpression, 9), 3)
                .otherwise(4);

        q.multiselect(
                quarterExpression,
                b.sum(rDauGia.get("gia"))
        );

        Predicate p1 = b.equal(rDonHang.get("id"), rDauGia.get("idDonhang"));
        Predicate p2 = b.equal(rDauGia.get("ketqua"), true);

        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {
            String nam = params.get("nam");
            if (nam != null && !nam.isEmpty()) {
                predicates.add(b.equal(b.function("YEAR", Integer.class, rDonHang.get("ngaytao")), Integer.parseInt(nam))); // Lọc theo năm
            }
        }

        predicates.add(b.and(p1, p2));
        q.where(predicates.toArray(new Predicate[0])).groupBy(quarterExpression).orderBy(b.asc(quarterExpression));

        return s.createQuery(q).getResultList();
    }

}
