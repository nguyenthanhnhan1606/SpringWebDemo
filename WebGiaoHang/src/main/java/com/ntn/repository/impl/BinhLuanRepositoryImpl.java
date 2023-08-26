/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.repository.impl;

import com.ntn.pojo.Binhluan;
import com.ntn.pojo.Shipper;
import com.ntn.repository.BinhLuanRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.transaction.Transactional;
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
public class BinhLuanRepositoryImpl implements BinhLuanRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Binhluan> getBinhLuanByIdShipper(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Binhluan> q = b.createQuery(Binhluan.class);
        Root<Binhluan> root = q.from(Binhluan.class);

        q.where(b.and(b.equal(root.get("idShipper").get("id"), id)),
                b.notEqual(root.get("noidung"), ""));

        return s.createQuery(q).getResultList();

    }

    @Override
    public boolean add(Binhluan bl) {
        try {
            Session s = this.factory.getObject().getCurrentSession();
            s.save(bl);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public double ratingOfShip(int id) {
    Session session = this.factory.getObject().getCurrentSession();
    CriteriaBuilder b = session.getCriteriaBuilder();
    CriteriaQuery<Double> q = b.createQuery(Double.class);
    Root<Binhluan> bRoot = q.from(Binhluan.class);

    List<Predicate> predicates = new ArrayList<>();
    predicates.add(b.equal(bRoot.get("idShipper").get("id"), id));
    predicates.add(b.notEqual(bRoot.get("danhgia"), 0));

    q.select(b.avg(bRoot.get("danhgia")))
        .where(predicates.toArray(new Predicate[0]));

    List<Double> results = session.createQuery(q).getResultList();

    if (results != null && !results.isEmpty()) {
        double sum = 0;
        for (Double result : results) {
            sum += result;
        }
        return sum / results.size();
    }

    return 0.0; // Trả về 0 nếu không có kết quả
}


}
