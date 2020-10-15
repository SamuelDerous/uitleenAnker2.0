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
@Table(name = "tblUitleen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblUitleen.findAll", query = "SELECT t FROM TblUitleen t"),
    @NamedQuery(name = "TblUitleen.findById", query = "SELECT t FROM TblUitleen t WHERE t.id = :id"),
    @NamedQuery(name = "TblUitleen.findByUitleendatum", query = "SELECT t FROM TblUitleen t WHERE t.uitleendatum = :uitleendatum"),
    @NamedQuery(name = "TblUitleen.findByTerugbrengdatum", query = "SELECT t FROM TblUitleen t WHERE t.terugbrengdatum = :terugbrengdatum"),
    @NamedQuery(name = "TblUitleen.findByBoete", query = "SELECT t FROM TblUitleen t WHERE t.boete = :boete"),
    @NamedQuery(name = "TblUitleen.findByHuurprijs", query = "SELECT t FROM TblUitleen t WHERE t.huurprijs = :huurprijs")})
public class TblUitleen implements Serializable {

    @Column(name = "aantal")
    private Integer aantal;
    @Column(name = "teruggebracht")
    @Temporal(TemporalType.DATE)
    private Date teruggebracht;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "uitleendatum")
    @Temporal(TemporalType.DATE)
    private Date uitleendatum;
    @Column(name = "terugbrengdatum")
    @Temporal(TemporalType.DATE)
    private Date terugbrengdatum;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "boete")
    private Double boete;
    @Lob
    @Size(max = 65535)
    @Column(name = "opmerking")
    private String opmerking;
    @Column(name = "huurprijs")
    private Double huurprijs;
    @JoinColumn(name = "spel", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblProduct spel;
    @JoinColumn(name = "naam", referencedColumnName = "gebruikersnaam")
    @ManyToOne(optional = false)
    private TblPersoon naam;
    @Column(name = "mails")
    private int mails;
    @Column(name="controle")
    private Integer controle;
    @Column(name="volledig")
    private Integer volledig;


    public TblUitleen() {
    }

    public TblUitleen(Integer id) {
        this.id = id;
    }

    public TblUitleen(Integer id, Date uitleendatum) {
        this.id = id;
        this.uitleendatum = uitleendatum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUitleendatum() {
        return uitleendatum;
    }

    public void setUitleendatum(Date uitleendatum) {
        this.uitleendatum = uitleendatum;
    }

    public Date getTerugbrengdatum() {
        return terugbrengdatum;
    }

    public void setTerugbrengdatum(Date terugbrengdatum) {
        this.terugbrengdatum = terugbrengdatum;
    }

    public Double getBoete() {
        return boete;
    }

    public void setBoete(Double boete) {
        this.boete = boete;
    }

    public String getOpmerking() {
        return opmerking;
    }

    public void setOpmerking(String opmerking) {
        this.opmerking = opmerking;
    }

    public Double getHuurprijs() {
        return huurprijs;
    }

    public void setHuurprijs(Double huurprijs) {
        this.huurprijs = huurprijs;
    }

    public TblProduct getSpel() {
        return spel;
    }

    public void setSpel(TblProduct spel) {
        this.spel = spel;
    }

    public TblPersoon getNaam() {
        return naam;
    }

    public void setNaam(TblPersoon naam) {
        this.naam = naam;
    }

    public int getMails() {
        return mails;
    }

    public void setMails(int mails) {
        this.mails = mails;
    }

    public Integer getControle() {
        return controle;
    }

    public void setControle(Integer controle) {
        this.controle = controle;
    }

    public Integer getVolledig() {
        return volledig;
    }

    public void setVolledig(Integer volledig) {
        this.volledig = volledig;
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
        if (!(object instanceof TblUitleen)) {
            return false;
        }
        TblUitleen other = (TblUitleen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "databank.TblUitleen[ id=" + id + " ]";
    }

    public Integer getAantal() {
        return aantal;
    }

    public void setAantal(Integer aantal) {
        this.aantal = aantal;
    }

    public Date getTeruggebracht() {
        return teruggebracht;
    }

    public void setTeruggebracht(Date teruggebracht) {
        this.teruggebracht = teruggebracht;
    }
    
}
