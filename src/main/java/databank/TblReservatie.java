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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zenodotus
 */
@Entity
@Table(name = "tblReservatie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblReservatie.findAll", query = "SELECT t FROM TblReservatie t"),
    @NamedQuery(name = "TblReservatie.findById", query = "SELECT t FROM TblReservatie t WHERE t.id = :id"),
    @NamedQuery(name = "TblReservatie.findByReservatieDatum", query = "SELECT t FROM TblReservatie t WHERE t.reservatieDatum = :reservatieDatum"),
    @NamedQuery(name = "TblReservatie.findByAantal", query = "SELECT t FROM TblReservatie t WHERE t.aantal = :aantal")})
public class TblReservatie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "reservatieDatum")
    @Temporal(TemporalType.DATE)
    private Date reservatieDatum;
    @Column(name = "aantal")
    private Integer aantal;
    @JoinColumn(name = "product", referencedColumnName = "id")
    @ManyToOne
    private TblProduct product;
    @JoinColumn(name = "gebruiker", referencedColumnName = "gebruikersnaam")
    @ManyToOne
    private TblPersoon gebruiker;

    public TblReservatie() {
    }

    public TblReservatie(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getReservatieDatum() {
        return reservatieDatum;
    }

    public void setReservatieDatum(Date reservatieDatum) {
        this.reservatieDatum = reservatieDatum;
    }

    public Integer getAantal() {
        return aantal;
    }

    public void setAantal(Integer aantal) {
        this.aantal = aantal;
    }

    public TblProduct getProduct() {
        return product;
    }

    public void setProduct(TblProduct product) {
        this.product = product;
    }

    public TblPersoon getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(TblPersoon gebruiker) {
        this.gebruiker = gebruiker;
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
        if (!(object instanceof TblReservatie)) {
            return false;
        }
        TblReservatie other = (TblReservatie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "databank.TblReservatie[ id=" + id + " ]";
    }
    
}
