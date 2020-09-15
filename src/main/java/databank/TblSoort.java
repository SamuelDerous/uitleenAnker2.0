/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databank;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author zenodotus
 */
@Entity
@Table(name = "tblSoort")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblSoort.findAll", query = "SELECT t FROM TblSoort t"),
    @NamedQuery(name = "TblSoort.findBySoort", query = "SELECT t FROM TblSoort t WHERE t.soort = :soort")})
public class TblSoort implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "soort")
    private String soort;
    @OneToMany(mappedBy = "soort")
    private Collection<TblPersoon> tblPersoonCollection;

    public TblSoort() {
    }

    public TblSoort(String soort) {
        this.soort = soort;
    }

    public String getSoort() {
        return soort;
    }

    public void setSoort(String soort) {
        this.soort = soort;
    }

    @XmlTransient
    public Collection<TblPersoon> getTblPersoonCollection() {
        return tblPersoonCollection;
    }

    public void setTblPersoonCollection(Collection<TblPersoon> tblPersoonCollection) {
        this.tblPersoonCollection = tblPersoonCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (soort != null ? soort.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblSoort)) {
            return false;
        }
        TblSoort other = (TblSoort) object;
        if ((this.soort == null && other.soort != null) || (this.soort != null && !this.soort.equals(other.soort))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "databank.TblSoort[ soort=" + soort + " ]";
    }
    
}
