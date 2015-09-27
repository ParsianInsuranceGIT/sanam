package com.bitarts.parsian.model.constantItems;

import com.bitarts.parsian.model.User;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 6/16/12
 * Time: 11:56 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tbl_consts")
public class Constants implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "constant_item_id")
    private Integer id;

    public static enum ConstantsApplyStyle {
        BOTH, JADID, GHADIM
    }

    public static enum PayeyeMohasebeHazineEdari {
        SarmayeFowt, HagheBime
    }

    public static enum RaveshMohasebeKarmozd {
        FOT, FOT_AND_HAGHBIME
    }

    public static enum Keys {
        HAZINEKARMOZD, HAZINEBIMEGARI, HAZINEVOSUL, HAZINEEDARI, NERKHBAHREFANNI, JADVALMARGOMIR, MALIATBARARZESHARZUDE, SAGHFESARMAYEFOTBEHARELLAT, SAGHFESARMAYEFOTDARASARHADESE,
        SAGHFESARMAYEAMRAZ, NERKHEPUSHESHEFOTDARASARHADESE, NERKHEHAGHEBIMEPUSHESHMOAFIAT, NERKHEPUSHESHFOTDARASAREZELZELE, SARMAYEFOTBEHARELLAT, NERKHEPUSHESHENAGHSOZV,
        VALIDATION
    }

    public static enum  KeysParam {
        MEGHDARHAZINE_YEKJA, MEGHDARHAZINE_GYEKJA, TEDADSALESTEHLAK, DARSADGHABELKASR, MEGHDARNERKH, PIADESAZIOEMALNERKH, TAMAMIMOALEFE, PARAMDARMALIAT,
        MABLAGHSARMAYEFOT, MABLAGHSARMAYEFOTDARASARHADESE, MABLAGHSARMAYEAMRAZ, MEGHDARNERKHKHATARTABAGHE_1, MEGHDARNERKHKHATARTABAGHE_2, MEGHDARNERKHKHATARTABAGHE_3, MEGHDARNERKHKHATARTABAGHE_4, MEGHDARNERKHKHATARTABAGHE_5, PARAMDARMOHASEBEHAGHBIME,
        MABLAGHSARMAYEFOTSENINMOKHTALEF, MEGHDARNERKHKHATAR, PayeyeMohasebeyeHazineEdari, RaveshMohasebe, MAXMAJMOOMODATVASEN, MINSENEBIMESHODE, MAXSENEBIMESHODE, DARSAD_KASRI_HAGH_BIME, DARSAD_MOJAZ_AFZAYESH_SARMAYE_FOT, MAHDODE_SARMAYE_PAYE_FOT,
        POSHESHFOTDARASARHADESE,POSHESENAGHZOZV,POSHESHAMRAZEKHAS,POSHESHMOAFIYAT,MINHAGHBIMEPARDAKHTIMAH
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private Tarh tarh;
    @Enumerated(EnumType.STRING)
    @Column(name = "c_name")
    private Keys name;
    @Enumerated(EnumType.STRING)
    @Column(name = "c_name2")
    private KeysParam name2;
    @Column(columnDefinition = "VARCHAR2(4000)", name = "c_value")
    private String value;
    @Enumerated(EnumType.STRING)
    @Column(name = "c_applyStyle")
    private ConstantsApplyStyle applyStyle;
    @Column(name = "c_dateEffect")
    private String dateEffect;
    @Column(name = "c_createdDate")
    private String createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Constants() {
    }

    public Constants(Keys name, String value, ConstantsApplyStyle applyStyle, String dateEffect, String createdDate, User user) {
        this.name = name;
        this.value = value;
        this.applyStyle = applyStyle;
        this.dateEffect = dateEffect;
        this.createdDate = createdDate;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Keys getName() {
        return name;
    }

    public void setName(Keys name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ConstantsApplyStyle getApplyStyle() {
        return applyStyle;
    }

    public void setApplyStyle(ConstantsApplyStyle applyStyle) {
        this.applyStyle = applyStyle;
    }

    public String getDateEffect() {
        return dateEffect;
    }

    public void setDateEffect(String dateEffect) {
        this.dateEffect = dateEffect;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public KeysParam getName2() {
        return name2;
    }

    public void setName2(KeysParam name2) {
        this.name2 = name2;
    }

    public String farsiStyle(ConstantsApplyStyle applyStyle) {
        switch (applyStyle) {
            case BOTH:
                return "در ارتباط با بیمه نامه های صادره جدید و قدیم";
            case GHADIM:
                return "در ارتباط با بیمه نامه های صادره قدیم";
            case JADID:
                return "در ارتباط با بیمه نامه های صادره جدید";
            default:
                return "";
        }
    }

    public String farsiName2(KeysParam name2) {
        switch (name2) {
            case MEGHDARHAZINE_YEKJA:
                return "مقدار هزینه یکجا";
            case MEGHDARHAZINE_GYEKJA:
                return "مقدار هزینه غیر یکجا";
            case TEDADSALESTEHLAK:
                return "تعداد سال هاي استهلاك طي مدت بيمه نامه";
            case DARSADGHABELKASR:
                return "درصد قابل كسر طي سال هاي بيمه اي";
            case MEGHDARNERKH:
                return "مقدار نرخ";
            case PIADESAZIOEMALNERKH:
                return "پياده سازي و اعمال نرخ هاي متفاوت در بازه هاي زماني مختلف";
            case TAMAMIMOALEFE:
                return "تمامي مولفه هاي جدول";
            case PARAMDARMALIAT:
                return "پارامترهاي به كار رفته در محاسبه ماليات";
            case MABLAGHSARMAYEFOT:
                return "مبلغ سرمايه فوت";
            case MABLAGHSARMAYEFOTDARASARHADESE:
                return "مبلغ سرمايه فوت در اثر حادثه";
            case MABLAGHSARMAYEAMRAZ:
                return "مبلغ سرمايه امراض";
            case MEGHDARNERKHKHATARTABAGHE_1:
                return "مقدار نرخ طبقه خطر 1";
            case MEGHDARNERKHKHATARTABAGHE_2:
                return "مقدار نرخ طبقه خطر 2";
            case MEGHDARNERKHKHATARTABAGHE_3:
                return "مقدار نرخ طبقه خطر 3";
            case MEGHDARNERKHKHATARTABAGHE_4:
                return "مقدار نرخ طبقه خطر 4";
            case MEGHDARNERKHKHATARTABAGHE_5:
                return "مقدار نرخ طبقه خطر 5";
            case PARAMDARMOHASEBEHAGHBIME:
                return "پارامترهاي به كار رفته در محاسبه حق بيمه";
            case MABLAGHSARMAYEFOTSENINMOKHTALEF:
                return "مبلغ سرمايه فوت براي سنين مختلف";
            case PayeyeMohasebeyeHazineEdari:
                return "محاسبه بر اساس پارامتر";
            case RaveshMohasebe:
                return "روش محاسبه";
            case MAXMAJMOOMODATVASEN:
                return "حداکثر مجموع سن و مدت بیمه نامه";
            case MINSENEBIMESHODE:
                return "حداقل سن بيمه شده";
            case MAXSENEBIMESHODE:
                return "حداكثر سن بيمه شده";
            case DARSAD_KASRI_HAGH_BIME:
                return "درصد کارمزد از حق بیمه صادره سال اول";
            case DARSAD_MOJAZ_AFZAYESH_SARMAYE_FOT:
                return "در صد مجاز افزایش سرمایه فوت";
            case MAHDODE_SARMAYE_PAYE_FOT:
                return "محدوده سرمایه فوت";
            case POSHESENAGHZOZV:
                return "وضعیت پوشش نقص عضو";
            case POSHESHAMRAZEKHAS:
                return "وضعیت پوشش امراض خاص";
            case POSHESHFOTDARASARHADESE:
                return "وضعیت پوشش فوت در اثر حادثه";
            case POSHESHMOAFIYAT:
                return "وضعیت پوشش معافیت";
            case MINHAGHBIMEPARDAKHTIMAH:
                return "حداقل حق بیمه پرداختی ماهانه";
            default:
                return "";
        }
    }

    public static List<KeysParam> valuesOfKey(Keys name) {
        List<KeysParam> list = new LinkedList<KeysParam>();
        switch (name) {
            case HAZINEKARMOZD:
                list.add(KeysParam.MEGHDARHAZINE_YEKJA);
                list.add(KeysParam.MEGHDARHAZINE_GYEKJA);
                list.add(KeysParam.TEDADSALESTEHLAK);
                list.add(KeysParam.DARSADGHABELKASR);
                list.add(KeysParam.RaveshMohasebe);
                list.add(KeysParam.DARSAD_KASRI_HAGH_BIME);
                break;
            case HAZINEBIMEGARI:
                list.add(KeysParam.MEGHDARHAZINE_YEKJA);
                list.add(KeysParam.MEGHDARHAZINE_GYEKJA);
                list.add(KeysParam.TEDADSALESTEHLAK);
                list.add(KeysParam.DARSADGHABELKASR);
                break;
            case HAZINEVOSUL:
                list.add(KeysParam.MEGHDARHAZINE_YEKJA);
                list.add(KeysParam.MEGHDARHAZINE_GYEKJA);
                list.add(KeysParam.TEDADSALESTEHLAK);
                list.add(KeysParam.DARSADGHABELKASR);
                break;
            case HAZINEEDARI:
                list.add(KeysParam.MEGHDARHAZINE_YEKJA);
                list.add(KeysParam.MEGHDARHAZINE_GYEKJA);
                list.add(KeysParam.TEDADSALESTEHLAK);
                list.add(KeysParam.DARSADGHABELKASR);
                list.add(KeysParam.PayeyeMohasebeyeHazineEdari);
                break;
            case NERKHBAHREFANNI:
                list.add(KeysParam.MEGHDARNERKH);
                //list.add(KeysParam.PIADESAZIOEMALNERKH);
                break;
            case JADVALMARGOMIR:
                list.add(KeysParam.TAMAMIMOALEFE);
                break;
            case MALIATBARARZESHARZUDE:
                list.add(KeysParam.MEGHDARNERKH);
                list.add(KeysParam.PARAMDARMALIAT);
                break;
            case SAGHFESARMAYEFOTBEHARELLAT:
                list.add(KeysParam.MABLAGHSARMAYEFOT);
                break;
            case SAGHFESARMAYEFOTDARASARHADESE:
                list.add(KeysParam.MABLAGHSARMAYEFOTDARASARHADESE);
                break;
            case SAGHFESARMAYEAMRAZ:
                list.add(KeysParam.MABLAGHSARMAYEAMRAZ);
                break;
            case NERKHEPUSHESHEFOTDARASARHADESE:
                list.add(KeysParam.MEGHDARNERKHKHATARTABAGHE_1);
                list.add(KeysParam.MEGHDARNERKHKHATARTABAGHE_2);
                list.add(KeysParam.MEGHDARNERKHKHATARTABAGHE_3);
                list.add(KeysParam.MEGHDARNERKHKHATARTABAGHE_4);
                list.add(KeysParam.MEGHDARNERKHKHATARTABAGHE_5);
                break;
            case NERKHEHAGHEBIMEPUSHESHMOAFIAT:
                list.add(KeysParam.MEGHDARNERKH);
                list.add(KeysParam.PARAMDARMOHASEBEHAGHBIME);
                break;
            case NERKHEPUSHESHFOTDARASAREZELZELE:
                list.add(KeysParam.MEGHDARNERKHKHATARTABAGHE_1);
                list.add(KeysParam.MEGHDARNERKHKHATARTABAGHE_2);
                list.add(KeysParam.MEGHDARNERKHKHATARTABAGHE_3);
                list.add(KeysParam.MEGHDARNERKHKHATARTABAGHE_4);
                list.add(KeysParam.MEGHDARNERKHKHATARTABAGHE_5);
                break;
            case SARMAYEFOTBEHARELLAT:
                list.add(KeysParam.MABLAGHSARMAYEFOTSENINMOKHTALEF);
                break;
            case VALIDATION:
                list.add(KeysParam.MAXMAJMOOMODATVASEN);
                list.add(KeysParam.MINSENEBIMESHODE);
                list.add(KeysParam.MAXSENEBIMESHODE);
                list.add(KeysParam.DARSAD_MOJAZ_AFZAYESH_SARMAYE_FOT);
                list.add(KeysParam.MAHDODE_SARMAYE_PAYE_FOT);
                list.add(KeysParam.POSHESHFOTDARASARHADESE);
                list.add(KeysParam.POSHESENAGHZOZV);
                list.add(KeysParam.POSHESHAMRAZEKHAS);
                list.add(KeysParam.POSHESHMOAFIYAT);
                list.add(KeysParam.MINHAGHBIMEPARDAKHTIMAH);
                break;
        }
        return list;
    }

    public String farsiName(Keys name) {
        switch (name) {
            case HAZINEKARMOZD:
                return "هزینه کارمزد";
            case HAZINEBIMEGARI:
                return "هزینه بیمه گری";
            case HAZINEVOSUL:
                return "هزینه وصول";
            case HAZINEEDARI:
                return "هزینه اداری";
            case NERKHBAHREFANNI:
                return "نرخ بهره فنی";
            case JADVALMARGOMIR:
                return "جدول مرگ و میر";
            case MALIATBARARZESHARZUDE:
                return "مالیات بر ارزش افزوده";
            case SAGHFESARMAYEFOTBEHARELLAT:
                return "سقف سرمایه فوت به هر علت";
            case SAGHFESARMAYEFOTDARASARHADESE:
                return "سقف سرمایه فوت در اثر حادثه";
            case SAGHFESARMAYEAMRAZ:
                return "سقف سرمایه امراض خاص";
            case NERKHEPUSHESHEFOTDARASARHADESE:
                return "نرخ پوشش فوت در اثر حادثه";
            case NERKHEHAGHEBIMEPUSHESHMOAFIAT:
                return "نرخ حق بیمه پوشش معافیت";
            case NERKHEPUSHESHFOTDARASAREZELZELE:
                return "نرخ پوشش فوت در اثر زلزله";
            case SARMAYEFOTBEHARELLAT:
                return "سرمایه فوت به هر علت";
            case VALIDATION:
                return "اعتبارسنجی";
            default:
                return "";
        }
    }

    public Tarh getTarh() {
        return tarh;
    }

    public void setTarh(Tarh tarh) {
        this.tarh = tarh;
    }
}
