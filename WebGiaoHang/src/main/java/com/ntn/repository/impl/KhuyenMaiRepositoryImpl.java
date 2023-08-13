/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntn.repository.impl;

import com.ntn.pojo.Khuyenmai;
import com.ntn.repository.KhuyenMaiRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
public class KhuyenMaiRepositoryImpl implements KhuyenMaiRepository {

    @Autowired
    private Environment env;
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Khuyenmai> getKhuyenMais(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Khuyenmai> q = b.createQuery(Khuyenmai.class);
        Root<Khuyenmai> root = q.from(Khuyenmai.class);
        q.select(root);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(b.greaterThanOrEqualTo(root.get("ngaykt"), new Date()));
        predicates.add(b.equal(root.get("active"), 1));

        if (params != null) {
            String search = params.get("search");
            if (search != null) {
                predicates.add(b.like(root.get("loaikhuyenmai"), "%" + search + "%"));
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
    public List<Khuyenmai> getKhuyenMaisExpires(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Khuyenmai> q = b.createQuery(Khuyenmai.class);
        Root<Khuyenmai> root = q.from(Khuyenmai.class);
        q.select(root);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(b.lessThanOrEqualTo(root.get("ngaykt"), new Date()));
        predicates.add(b.equal(root.get("active"), 1));

        if (params != null) {
            String search = params.get("search");
            if (search != null) {
                predicates.add(b.like(root.get("loaikhuyenmai"), "%" + search + "%"));
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
    public Khuyenmai getKhuyenMaiById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        Khuyenmai km = session.get(Khuyenmai.class, id);
        return km;
    }

    @Override
    public boolean addOrUpdateKhuyenMai(Khuyenmai km) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            if (km.getId() == null) {
                km.setActive(true);
                session.save(km);

            } else {
                session.update(km);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public Khuyenmai createKhuyenMai(Khuyenmai km) {
        Session session = this.factory.getObject().getCurrentSession();
        session.save(km);
        return km;
    }

    @Override
    public Khuyenmai updateKhuyenMai(int id, Khuyenmai km) {
        Session session = this.factory.getObject().getCurrentSession();
        Khuyenmai km1 = session.get(Khuyenmai.class, id);
        km1.setActive(km.getActive());
        km1.setLoaikhuyenmai(km.getLoaikhuyenmai());
        km1.setMota(km.getMota());
        km1.setNgaybd(km.getNgaybd());
        km1.setNgaykt(km.getNgaykt());
        km1.setImage(km.getImage());
        session.update(km1);
        return km1;

    }

    @Override
    public void deleteKhuyenMai(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        session.delete(session.get(Khuyenmai.class, id));
    }

    @Override
    public Long countPromotion() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT Count(*) FROM Khuyenmai");

        return Long.parseLong(q.getSingleResult().toString());
    }

    @Override
    public void recycleBin(int id) {
        try {
            Session session = this.factory.getObject().getCurrentSession();
            Khuyenmai km = session.get(Khuyenmai.class, id);
            km.setActive(false);
            session.update(km);
        } catch (HibernateException ex) {
            Logger.getLogger(ShipperRepositoryImpl.class.getName()).log(Level.SEVERE, "Lỗi khi xóa khuyến mãi này: " + ex.getMessage(), ex);
            throw new RuntimeException("Đã xảy ra lỗi khi xóa khuyến mãi. Vui lòng thử lại hoặc liên hệ hỗ trợ." + ex.getMessage());
        }
    }

}
