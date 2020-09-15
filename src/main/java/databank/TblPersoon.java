/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databank;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tblPersoon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblPersoon.findAll", query = "SELECT t FROM TblPersoon t"),
    @NamedQuery(name = "TblPersoon.findByGebruikersnaam", query = "SELECT t FROM TblPersoon t WHERE t.gebruikersnaam = :gebruikersnaam"),
    @NamedQuery(name = "TblPersoon.findByNaam", query = "SELECT t FROM TblPersoon t WHERE t.naam = :naam"),
    @NamedQuery(name = "TblPersoon.findByVoornaam", query = "SELECT t FROM TblPersoon t WHERE t.voornaam = :voornaam"),
    @NamedQuery(name = "TblPersoon.findByAdres", query = "SELECT t FROM TblPersoon t WHERE t.adres = :adres"),
    @NamedQuery(name = "TblPersoon.findByTelefoon", query = "SELECT t FROM TblPersoon t WHERE t.telefoon = :telefoon"),
    @NamedQuery(name = "TblPersoon.findByEMail", query = "SELECT t FROM TblPersoon t WHERE t.eMail = :eMail"),
    @NamedQuery(name = "TblPersoon.findByWachtwoord", query = "SELECT t FROM TblPersoon t WHERE t.wachtwoord = :wachtwoord")})
public class TblPersoon implements Serializable {

    @OneToMany(mappedBy = "gebruiker")
    private Collection<TblReservatie> tblReservatieCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "gebruikersnaam")
    private String gebruikersnaam;
    @Size(max = 50)
    @Column(name = "naam")
    private String naam;
    @Size(max = 50)
    @Column(name = "voornaam")
    private String voornaam;
    @Size(max = 100)
    @Column(name = "adres")
    private String adres;
    @Size(max = 10)
    @Column(name = "telefoon")
    private String telefoon;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "eMail")
    private String eMail;
    @Size(max = 150)
    @Column(name = "wachtwoord")
    private String wachtwoord;
    @JoinColumn(name = "soort", referencedColumnName = "soort")
    @ManyToOne
    private TblSoort soort;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "naam")
    private Collection<TblUitleen> tblUitleenCollection;

    public TblPersoon() {
    }

    public TblPersoon(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getTelefoon() {
        return telefoon;
    }

    public void setTelefoon(String telefoon) {
        this.telefoon = telefoon;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public TblSoort getSoort() {
        return soort;
    }

    public void setSoort(TblSoort soort) {
        this.soort = soort;
    }

    @XmlTransient
    public Collection<TblUitleen> getTblUitleenCollection() {
        return tblUitleenCollection;
    }

    public void setTblUitleenCollection(Collection<TblUitleen> tblUitleenCollection) {
        this.tblUitleenCollection = tblUitleenCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gebruikersnaam != null ? gebruikersnaam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblPersoon)) {
            return false;
        }
        TblPersoon other = (TblPersoon) object;
        if ((this.gebruikersnaam == null && other.gebruikersnaam != null) || (this.gebruikersnaam != null && !this.gebruikersnaam.equals(other.gebruikersnaam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "databank.TblPersoon[ gebruikersnaam=" + gebruikersnaam + " ]";
    }

    @XmlTransient
    public Collection<TblReservatie> getTblReservatieCollection() {
        return tblReservatieCollection;
    }

    public void setTblReservatieCollection(Collection<TblReservatie> tblReservatieCollection) {
        this.tblReservatieCollection = tblReservatieCollection;
    }
    
}
