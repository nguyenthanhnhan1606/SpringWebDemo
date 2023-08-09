/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author THANH NHAN
 */
@Entity
@Table(name = "binhluan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Binhluan.findAll", query = "SELECT b FROM Binhluan b"),
    @NamedQuery(name = "Binhluan.findById", query = "SELECT b FROM Binhluan b WHERE b.id = :id"),
    @NamedQuery(name = "Binhluan.findByNoidung", query = "SELECT b FROM Binhluan b WHERE b.noidung = :noidung"),
    @NamedQuery(name = "Binhluan.findByNgaybinhluan", query = "SELECT b FROM Binhluan b WHERE b.ngaybinhluan = :ngaybinhluan"),
    @NamedQuery(name = "Binhluan.findByDanhgia", query = "SELECT b FROM Binhluan b WHERE b.danhgia = :danhgia")})
public class Binhluan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 1000)
    @Column(name = "noidung")
    private String noidung;
    @Column(name = "ngaybinhluan")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaybinhluan;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "danhgia")
    private Double danhgia;
    @JoinColumn(name = "id_shipper", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Shipper idShipper;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User idUser;

    public Binhluan() {
    }

    public Binhluan(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public Date getNgaybinhluan() {
        return ngaybinhluan;
    }

    public void setNgaybinhluan(Date ngaybinhluan) {
        this.ngaybinhluan = ngaybinhluan;
    }

    public Double getDanhgia() {
        return danhgia;
    }

    public void setDanhgia(Double danhgia) {
        this.danhgia = danhgia;
    }

    public Shipper getIdShipper() {
        return idShipper;
    }

    public void setIdShipper(Shipper idShipper) {
        this.idShipper = idShipper;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Binhluan)) {
            return false;
        }
        Binhluan other = (Binhluan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntn.pojo.Binhluan[ id=" + id + " ]";
    }
    
}
