/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databank;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zenodotus
 */
@Entity
@Table(name = "tblInventarisatie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblInventarisatie.findAll", query = "SELECT t FROM TblInventarisatie t"),
    @NamedQuery(name = "TblInventarisatie.findById", query = "SELECT t FROM TblInventarisatie t WHERE t.id = :id"),
    @NamedQuery(name = "TblInventarisatie.findByAantal", query = "SELECT t FROM TblInventarisatie t WHERE t.aantal = :aantal"),
    @NamedQuery(name = "TblInventarisatie.findByDatum", query = "SELECT t FROM TblInventarisatie t WHERE t.datum = :datum")})
public class TblInventarisatie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aantal")
    private int aantal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datum")
    @Temporal(TemporalType.DATE)
    private Date datum;
    @Lob
    @Size(max = 65535)
    @Column(name = "opmerking")
    private String opmerking;
    @JoinColumn(name = "product", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblProduct product;

    public TblInventarisatie() {
    }

    public TblInventarisatie(Integer id) {
        this.id = id;
    }

    public TblInventarisatie(Integer id, int aantal, Date datum) {
        this.id = id;
        this.aantal = aantal;
        this.datum = datum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getOpmerking() {
        return opmerking;
    }

    public void setOpmerking(String opmerking) {
        this.opmerking = opmerking;
    }

    public TblProduct getProduct() {
        return product;
    }

    public void setProduct(TblProduct product) {
        this.product = product;
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
        if (!(object instanceof TblInventarisatie)) {
            return false;
        }
        TblInventarisatie other = (TblInventarisatie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "databank.TblInventarisatie[ id=" + id + " ]";
    }
    
}
