/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.repository.impl;

import com.ntn.pojo.Shipper;
import com.ntn.pojo.User;
import com.ntn.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author THANH NHAN
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private Environment env;
    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private BCryptPasswordEncoder passEncoder;

    //Lấy danh sách các user và shipper đã xóa để chọn làm 
    @Override
    public List<User> getUsers(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root<User> uRoot = q.from(User.class);
        Join<User, Shipper> shipperJoin = uRoot.join("shipper", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();
        Predicate q1 = b.equal(shipperJoin.get("trangthai"), "Đã xóa");
        Predicate q2 = b.isNull(shipperJoin.get("id"));

        predicates.add(b.or(q2, q1));
        predicates.add(b.equal(uRoot.get("userRole"), "ROLE_USER"));

        String search = params.get("search");
        if (search != null) {
            predicates.add(b.like(uRoot.get("ten"), "%" + search + "%"));
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

    //Lấy danh sách các user có đăng kí để thành  shipper
    @Override
    public List<User> getUserRegistShipper(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root<User> uRoot = q.from(User.class);
        Join<User, Shipper> shipperJoin = uRoot.join("shipper", JoinType.LEFT);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(b.equal(shipperJoin.get("trangthai"), "Chờ xác nhận"));
        predicates.add(b.equal(uRoot.get("userRole"), "ROLE_USER"));

        String search = params.get("search");
        if (search != null) {
            predicates.add(b.like(uRoot.get("ten"), "%" + search + "%"));
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
    public User getUsersByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User WHERE taikhoan=:un");
        q.setParameter("un", username);

        return (User) q.getSingleResult();

    }

    @Override
    public User getUsersById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(User.class, id);
    }

    //Xác nhận đăng kí shipper
    @Override
    public void updateRole(int id) {
        try {
            Session session = this.factory.getObject().getCurrentSession();
            User u = session.get(User.class, id);
            u.setUserRole("ROLE_SHIPPER");
            Shipper sp = u.getShipper();
            sp.setTrangthai("Đã xác nhận");
            session.update(u);
            session.update(sp);
        } catch (HibernateException ex) {
            Logger.getLogger(ShipperRepositoryImpl.class.getName()).log(Level.SEVERE, "Lỗi khi xác nhận shipper: " + ex.getMessage(), ex);
            throw new RuntimeException("Đã xảy ra lỗi khi xác nhận shipper. Vui lòng thử lại hoặc liên hệ hỗ trợ." + ex.getMessage());
        }
    }

    //Từ chối đăng ký shipper
    @Override
    public void refuseShipper(int id) {
        try {
            Session session = this.factory.getObject().getCurrentSession();
            User u = session.get(User.class, id);
            u.setUserRole("ROLE_USER");
            session.update(u);
            Shipper sp = u.getShipper();
            session.delete(sp);
        } catch (HibernateException ex) {
            Logger.getLogger(ShipperRepositoryImpl.class.getName()).log(Level.SEVERE, "Lỗi khi từ chối shipper: " + ex.getMessage(), ex);
            throw new RuntimeException("Đã xảy ra lỗi khi từ chối shipper. Vui lòng thử lại hoặc liên hệ hỗ trợ." + ex.getMessage());
        }
    }

    //đếm số user đã đăng kí chờ duyệt shipper
    @Override
    public Long countUser() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT Count(*) FROM Shipper WHERE trangthai='Chờ xác nhận'");

        return Long.parseLong(q.getSingleResult().toString());
    }

    //Đếm số user và
    @Override
    public Long countUserNew() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(s) "
                + "FROM Shipper s JOIN User u ON s.id=u.id "
                + "WHERE s.trangthai = 'Đã xóa' "
                + "AND u.id IS NULL ");

        return Long.parseLong(q.getSingleResult().toString());
    }

    @Override
    public boolean authUser(String username, String password) {
        User u = this.getUsersByUsername(username);
        return this.passEncoder.matches(password, u.getMatkhau());
    }

    @Override
    public User addUser(User user) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(user);
        return user;
    }

    @Override
    public void update(User u) {
        Session s = this.factory.getObject().getCurrentSession();
        s.update(u);
    }

    @Override
    public boolean checkUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User WHERE taikhoan = :un");
        q.setParameter("un", username);

        List<User> userList = q.getResultList();

        return userList.size() > 0;

    }
}
