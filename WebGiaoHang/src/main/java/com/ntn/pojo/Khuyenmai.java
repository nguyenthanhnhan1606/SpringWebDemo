/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author THANH NHAN
 */
@Entity
@Table(name = "khuyenmai")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Khuyenmai.findAll", query = "SELECT k FROM Khuyenmai k"),
    @NamedQuery(name = "Khuyenmai.findById", query = "SELECT k FROM Khuyenmai k WHERE k.id = :id"),
    @NamedQuery(name = "Khuyenmai.findByLoaikhuyenmai", query = "SELECT k FROM Khuyenmai k WHERE k.loaikhuyenmai = :loaikhuyenmai"),
    @NamedQuery(name = "Khuyenmai.findByMota", query = "SELECT k FROM Khuyenmai k WHERE k.mota = :mota"),
    @NamedQuery(name = "Khuyenmai.findByNgaybd", query = "SELECT k FROM Khuyenmai k WHERE k.ngaybd = :ngaybd"),
    @NamedQuery(name = "Khuyenmai.findByNgaykt", query = "SELECT k FROM Khuyenmai k WHERE k.ngaykt = :ngaykt"),
    @NamedQuery(name = "Khuyenmai.findByImage", query = "SELECT k FROM Khuyenmai k WHERE k.image = :image"),
    @NamedQuery(name = "Khuyenmai.findByActive", query = "SELECT k FROM Khuyenmai k WHERE k.active = :active")})
public class Khuyenmai implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull(message = "{khuyenmai.loai.notNull}")
    @Size(min = 1, max = 100, message = "{khuyenmai.loai.lenErr}")
    @Column(name = "loaikhuyenmai")
    private String loaikhuyenmai;
    @Size(max = 100)
    @Column(name = "mota")
    private String mota;
    @Basic(optional = false)
    @NotNull(message = "{khuyenmai.ngaybd.notNull}")
    @Column(name = "ngaybd")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaybd;
    @Basic(optional = false)
    @NotNull(message = "{khuyenmai.ngaykt.notNull}")
    @Column(name = "ngaykt")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaykt;
    @Size(max = 200)
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @JsonIgnore
    @OneToMany(mappedBy = "idKhuyenmai")
    private Set<Donhang> donhangSet;
    
    @Transient
    private MultipartFile file;

    public Khuyenmai() {
    }

    public Khuyenmai(Integer id) {
        this.id = id;
    }

    public Khuyenmai(Integer id, String loaikhuyenmai, Date ngaybd, Date ngaykt, boolean active) {
        this.id = id;
        this.loaikhuyenmai = loaikhuyenmai;
        this.ngaybd = ngaybd;
        this.ngaykt = ngaykt;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoaikhuyenmai() {
        return loaikhuyenmai;
    }

    public void setLoaikhuyenmai(String loaikhuyenmai) {
        this.loaikhuyenmai = loaikhuyenmai;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public Date getNgaybd() {
        return ngaybd;
    }

    public void setNgaybd(Date ngaybd) {
        this.ngaybd = ngaybd;
    }

    public Date getNgaykt() {
        return ngaykt;
    }

    public void setNgaykt(Date ngaykt) {
        this.ngaykt = ngaykt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @XmlTransient
    public Set<Donhang> getDonhangSet() {
        return donhangSet;
    }

    public void setDonhangSet(Set<Donhang> donhangSet) {
        this.donhangSet = donhangSet;
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
        if (!(object instanceof Khuyenmai)) {
            return false;
        }
        Khuyenmai other = (Khuyenmai) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntn.pojo.Khuyenmai[ id=" + id + " ]";
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
