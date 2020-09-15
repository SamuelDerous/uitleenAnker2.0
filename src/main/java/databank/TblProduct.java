/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databank;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author zenodotus
 */
@Entity
@Table(name = "tblProduct")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblProduct.findAll", query = "SELECT t FROM TblProduct t"),
    @NamedQuery(name = "TblProduct.findById", query = "SELECT t FROM TblProduct t WHERE t.id = :id"),
    @NamedQuery(name = "TblProduct.findByNaam", query = "SELECT t FROM TblProduct t WHERE t.naam = :naam"),
    @NamedQuery(name = "TblProduct.findByAankoopprijs", query = "SELECT t FROM TblProduct t WHERE t.aankoopprijs = :aankoopprijs"),
    @NamedQuery(name = "TblProduct.findByBreukprijs", query = "SELECT t FROM TblProduct t WHERE t.breukprijs = :breukprijs"),
    @NamedQuery(name = "TblProduct.findByAantal", query = "SELECT t FROM TblProduct t WHERE t.aantal = :aantal"),
    @NamedQuery(name = "TblProduct.findByWebsite", query = "SELECT t FROM TblProduct t WHERE t.website = :website"),
    @NamedQuery(name = "TblProduct.findByAankoopdatum", query = "SELECT t FROM TblProduct t WHERE t.aankoopdatum = :aankoopdatum")})

public class TblProduct implements Serializable {

    @Column(name = "uitleentermijn")
    private Integer uitleentermijn;
    @Size(max = 50)
    @Column(name = "plaats")
    private String plaats;
    @Column(name = "volledig")
    private Integer volledig;
    @Column(name = "controle")
    private Integer controle;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "spel")
    private Collection<TblUitleen> tblUitleenCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "naam")
    private String naam;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "aankoopprijs")
    private Double aankoopprijs;
    @Column(name = "breukprijs")
    private Double breukprijs;
    @Column(name = "aantal")
    private Integer aantal;
    @Lob
    @Size(max = 65535)
    @Column(name = "opmerking")
    private String opmerking;
    @Size(max = 150)
    @Column(name = "website")
    private String website;
    @Column(name = "Aankoopdatum")
    @Temporal(TemporalType.DATE)
    private Date aankoopdatum;
    @JoinColumn(name = "beschrijving", referencedColumnName = "soort")
    @ManyToOne
    private TblBeschrijving beschrijving;

    public TblProduct() {
    }

    public TblProduct(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Double getAankoopprijs() {
        return aankoopprijs;
    }

    public void setAankoopprijs(Double aankoopprijs) {
        this.aankoopprijs = aankoopprijs;
    }

    public Double getBreukprijs() {
        return breukprijs;
    }

    public void setBreukprijs(Double breukprijs) {
        this.breukprijs = breukprijs;
    }

    public Integer getAantal() {
        return aantal;
    }

    public void setAantal(Integer aantal) {
        this.aantal = aantal;
    }

    public String getOpmerking() {
        return opmerking;
    }

    public void setOpmerking(String opmerking) {
        this.opmerking = opmerking;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Date getAankoopdatum() {
        return aankoopdatum;
    }

    public void setAankoopdatum(Date aankoopdatum) {
        this.aankoopdatum = aankoopdatum;
    }

    public TblBeschrijving getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(TblBeschrijving beschrijving) {
        this.beschrijving = beschrijving;
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
        if (!(object instanceof TblProduct)) {
            return false;
        }
        TblProduct other = (TblProduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "databank.TblProduct[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<TblUitleen> getTblUitleenCollection() {
        return tblUitleenCollection;
    }

    public void setTblUitleenCollection(Collection<TblUitleen> tblUitleenCollection) {
        this.tblUitleenCollection = tblUitleenCollection;
    }

    public Integer getUitleentermijn() {
        return uitleentermijn;
    }

    public void setUitleentermijn(Integer uitleentermijn) {
        this.uitleentermijn = uitleentermijn;
    }

    public String getPlaats() {
        return plaats;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    public Integer getVolledig() {
        return volledig;
    }

    public void setVolledig(Integer volledig) {
        this.volledig = volledig;
    }

    public Integer getControle() {
        return controle;
    }

    public void setControle(Integer controle) {
        this.controle = controle;
    }
    
    
    
}
