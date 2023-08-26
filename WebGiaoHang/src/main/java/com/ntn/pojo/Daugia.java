/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author THANH NHAN
 */
@Entity
@Table(name = "daugia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Daugia.findAll", query = "SELECT d FROM Daugia d"),
    @NamedQuery(name = "Daugia.findById", query = "SELECT d FROM Daugia d WHERE d.id = :id"),
    @NamedQuery(name = "Daugia.findByGia", query = "SELECT d FROM Daugia d WHERE d.gia = :gia"),
    @NamedQuery(name = "Daugia.findByKetqua", query = "SELECT d FROM Daugia d WHERE d.ketqua = :ketqua")})
public class Daugia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gia")
    private double gia;
    @Column(name = "ketqua")
    private Boolean ketqua;
    @JoinColumn(name = "id_donhang", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Donhang idDonhang;
    @JoinColumn(name = "id_shipper", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Shipper idShipper;

    public Daugia() {
    }

    public Daugia(Integer id) {
        this.id = id;
    }

    public Daugia(Integer id, double gia) {
        this.id = id;
        this.gia = gia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public Boolean getKetqua() {
        return ketqua;
    }

    public void setKetqua(Boolean ketqua) {
        this.ketqua = ketqua;
    }

    public Donhang getIdDonhang() {
        return idDonhang;
    }

    public void setIdDonhang(Donhang idDonhang) {
        this.idDonhang = idDonhang;
    }

    public Shipper getIdShipper() {
        return idShipper;
    }

    public void setIdShipper(Shipper idShipper) {
        this.idShipper = idShipper;
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
        if (!(object instanceof Daugia)) {
            return false;
        }
        Daugia other = (Daugia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntn.pojo.Daugia[ id=" + id + " ]";
    }
    
}
