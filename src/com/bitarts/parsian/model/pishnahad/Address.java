package com.bitarts.parsian.model.pishnahad;

import com.bitarts.parsian.model.constantItems.City;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 2/24/11
 * Time: 7:06 PM
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tbl_Address")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "ostaan_manzel", nullable = true)
    private City ostaanManzel;
    @ManyToOne
    @JoinColumn(name = "shahr_manzel", nullable = true)
    private City shahrManzel;
    @Column(name = "neshani_manzel")
    private String neshaniManzel;
    @Column(name = "kode_posti_manzel")
    private String kodePostiManzel;
    @Column(name = "code_telephone_manzel")
    private String codeTelephoneManzel;
    @Column(name = "telephone_manzel")
    private String telephoneManzel;
    @Column(name = "telephone_hamrah")
    private String telephoneHamrah;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ostaan_mahalle_kar")
    private City ostaanMahalleKar;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shahr_mahalle_kar")
    private City shahrMahalleKar;
    @Column(name = "neshani_mahalle_kar")
    private String neshaniMahalleKar;
    @Column(name = "kode_posti_mahalle_kar")
    private String kodePostiMahallekar;
    @Column(name = "code_tlphon_mahl_kar")
    private String codeTelephoneMahalleKar;
    @Column(name = "telephone_mahale_kar")
    private String telephoneMahalleKar;
    @Column(name = "poste_electronic")
    private String posteElectronic;


    public Address(City ostaanManzel, City shahrManzel, String neshaniManzel, String kodePostiManzel, String codeTelephoneManzel, String telephoneManzel, String telephoneHamrah, City ostaanMahalleKar, City shahrMahalleKar, String neshaniMahalleKar, String kodePostiMahallekar, String codeTelephoneMahalleKar, String telephoneMahalleKar, String posteElectronic) {
        this.ostaanManzel = ostaanManzel;
        this.shahrManzel = shahrManzel;
        this.neshaniManzel = neshaniManzel;
        this.kodePostiManzel = kodePostiManzel;
        this.codeTelephoneManzel = codeTelephoneManzel;
        this.telephoneManzel = telephoneManzel;
        this.telephoneHamrah = telephoneHamrah;
        this.ostaanMahalleKar = ostaanMahalleKar;
        this.shahrMahalleKar = shahrMahalleKar;
        this.neshaniMahalleKar = neshaniMahalleKar;
        this.kodePostiMahallekar = kodePostiMahallekar;
        this.codeTelephoneMahalleKar = codeTelephoneMahalleKar;
        this.telephoneMahalleKar = telephoneMahalleKar;
        this.posteElectronic = posteElectronic;
    }

    public Address() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public City getOstaanManzel() {
        return ostaanManzel;
    }

    public void setOstaanManzel(City ostaanManzel) {
        this.ostaanManzel = ostaanManzel;
    }

    public City getShahrManzel() {
        return shahrManzel;
    }

    public void setShahrManzel(City shahrManzel) {
        this.shahrManzel = shahrManzel;
    }

    public String getNeshaniManzel() {
        return neshaniManzel;
    }

    public void setNeshaniManzel(String neshaniManzel) {
        this.neshaniManzel = neshaniManzel;
    }

    public String getKodePostiManzel() {
        return kodePostiManzel;
    }

    public void setKodePostiManzel(String kodePostiManzel) {
        this.kodePostiManzel = kodePostiManzel;
    }

    public String getCodeTelephoneManzel() {
        return codeTelephoneManzel;
    }

    public void setCodeTelephoneManzel(String codeTelephoneManzel) {
        this.codeTelephoneManzel = codeTelephoneManzel;
    }

    public String getTelephoneManzel() {
        return telephoneManzel;
    }

    public void setTelephoneManzel(String telephoneManzel) {
        this.telephoneManzel = telephoneManzel;
    }

    public String getTelephoneHamrah() {
        return telephoneHamrah;
    }

    public void setTelephoneHamrah(String telephoneHamrah) {
        this.telephoneHamrah = telephoneHamrah;
    }

    public City getOstaanMahalleKar() {
        return ostaanMahalleKar;
    }

    public void setOstaanMahalleKar(City ostaanMahalleKar) {
        this.ostaanMahalleKar = ostaanMahalleKar;
    }

    public City getShahrMahalleKar() {
        return shahrMahalleKar;
    }

    public void setShahrMahalleKar(City shahrMahalleKar) {
        this.shahrMahalleKar = shahrMahalleKar;
    }

    public String getNeshaniMahalleKar() {
        return neshaniMahalleKar;
    }

    public void setNeshaniMahalleKar(String neshaniMahalleKar) {
        this.neshaniMahalleKar = neshaniMahalleKar;
    }

    public String getKodePostiMahallekar() {
        return kodePostiMahallekar;
    }

    public void setKodePostiMahallekar(String kodePostiMahallekar) {
        this.kodePostiMahallekar = kodePostiMahallekar;
    }

    public String getCodeTelephoneMahalleKar() {
        return codeTelephoneMahalleKar;
    }

    public void setCodeTelephoneMahalleKar(String codeTelephoneMahalleKar) {
        this.codeTelephoneMahalleKar = codeTelephoneMahalleKar;
    }

    public String getTelephoneMahalleKar() {
        return telephoneMahalleKar;
    }

    public void setTelephoneMahalleKar(String telephoneMahalleKar) {
        this.telephoneMahalleKar = telephoneMahalleKar;
    }

    public String getPosteElectronic() {
        return posteElectronic;
    }

    public void setPosteElectronic(String posteElectronic) {
        this.posteElectronic = posteElectronic;
    }

    public String getTelphoneMobile() {
        return codeTelephoneManzel + telephoneManzel + "/" + telephoneHamrah;
    }
}
