/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntn.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author THANH NHAN
 */
@Entity
@Table(name = "donhang")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Donhang.findAll", query = "SELECT d FROM Donhang d"),
    @NamedQuery(name = "Donhang.findById", query = "SELECT d FROM Donhang d WHERE d.id = :id"),
    @NamedQuery(name = "Donhang.findByNoigui", query = "SELECT d FROM Donhang d WHERE d.noigui = :noigui"),
    @NamedQuery(name = "Donhang.findByNoinhan", query = "SELECT d FROM Donhang d WHERE d.noinhan = :noinhan"),
    @NamedQuery(name = "Donhang.findByNgaytao", query = "SELECT d FROM Donhang d WHERE d.ngaytao = :ngaytao"),
    @NamedQuery(name = "Donhang.findByMota", query = "SELECT d FROM Donhang d WHERE d.mota = :mota"),
    @NamedQuery(name = "Donhang.findByGhichu", query = "SELECT d FROM Donhang d WHERE d.ghichu = :ghichu"),
    @NamedQuery(name = "Donhang.findByGiatridh", query = "SELECT d FROM Donhang d WHERE d.giatridh = :giatridh"),
    @NamedQuery(name = "Donhang.findByTrangthai", query = "SELECT d FROM Donhang d WHERE d.trangthai = :trangthai"),
    @NamedQuery(name = "Donhang.findByImage", query = "SELECT d FROM Donhang d WHERE d.image = :image")})
public class Donhang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "noigui")
    private String noigui;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "noinhan")
    private String noinhan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ngaytao")
    @Temporal(TemporalType.DATE)
    private Date ngaytao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "mota")
    private String mota;
    @Size(max = 100)
    @Column(name = "ghichu")
    private String ghichu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "giatridh")
    private double giatridh;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "trangthai")
    private String trangthai;
    @Size(max = 200)
    @Column(name = "image")
    private String image;
    @JoinColumn(name = "id_khuyenmai", referencedColumnName = "id")
    @ManyToOne
    private Khuyenmai idKhuyenmai;
    @JoinColumn(name = "id_shipper", referencedColumnName = "id")
    @ManyToOne
    private Shipper idShipper;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User idUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDonhang")
    private Set<Daugia> daugiaSet;

    public Donhang() {
    }

    public Donhang(Integer id) {
        this.id = id;
    }

    public Donhang(Integer id, String noigui, String noinhan, Date ngaytao, String mota, double giatridh, String trangthai) {
        this.id = id;
        this.noigui = noigui;
        this.noinhan = noinhan;
        this.ngaytao = ngaytao;
        this.mota = mota;
        this.giatridh = giatridh;
        this.trangthai = trangthai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoigui() {
        return noigui;
    }

    public void setNoigui(String noigui) {
        this.noigui = noigui;
    }

    public String getNoinhan() {
        return noinhan;
    }

    public void setNoinhan(String noinhan) {
        this.noinhan = noinhan;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public double getGiatridh() {
        return giatridh;
    }

    public void setGiatridh(double giatridh) {
        this.giatridh = giatridh;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Khuyenmai getIdKhuyenmai() {
        return idKhuyenmai;
    }

    public void setIdKhuyenmai(Khuyenmai idKhuyenmai) {
        this.idKhuyenmai = idKhuyenmai;
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

    @XmlTransient
    public Set<Daugia> getDaugiaSet() {
        return daugiaSet;
    }

    public void setDaugiaSet(Set<Daugia> daugiaSet) {
        this.daugiaSet = daugiaSet;
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
        if (!(object instanceof Donhang)) {
            return false;
        }
        Donhang other = (Donhang) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntn.pojo.Donhang[ id=" + id + " ]";
    }
    
}
