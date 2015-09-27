package com.bitarts.parsian.dao;

import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.model.pishnahad.VaziateSalamatiBimeShode;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/8/11
 * Time: 2:03 PM
 */
public class VaziateSalamatiBimeShodeDAO extends BaseDAO {
    //    private Integer id;
//    private String bimari_tanafosi;
//    private String bimari_ghalb;
//    private String bimari_khuni;
//    private String bimari_govaareshi;
//    private String bimari_asaab;
//    private String bimari_daakheli;
//    private String bimari_koliye;
//    private String bimari_gush;
//    private String bimari_pusti;
//    private String bimari_ostekhaani;
//    private String bimari_ghodad;
//    private String bimari_ofuni;
//    private String amal_jaraahi_ya_bastari;
//    private String aareze_digar;
//    private String naghse_ozv;
//    private String az_kaar_oftaadegi;
//    private String shesh_maah_kaahesh_vazn;
//    private String bastegaan_daraje_aval_bimaari;
//    private String daaru_bimaari_tulaani_modat;
//    private String haal_haazer_saalem;
//    private String sigaar;
//    private String meghdar_masraf_sigaar;
//    private String modat_masraf_sigaar;
//    private String alcol;
//    private String meghdar_masraf_alcol;
//    private String modat_masraf_alcol;
//    private String mavaad_mokhader;
//    private String meghdar_masraf_mokhader;
//    private String modat_masraf_mokhader;
//    private String baardaari;
//    private String bimarihaaye_zanaane;
//    private String shoru_zamaan_bimaari;
//    private String daaruye_masrafi;
//    private String modat_masraf;
//    private String darmaan_anjaam_shode;
//    private String vazeeyat_feli_bime_shode;
//    private String tarikh_jarraahi;
    public void save(VaziateSalamatiBimeShode vaziateSalamatiBimeShode){
        super.save(vaziateSalamatiBimeShode);
    }

    public void update(VaziateSalamatiBimeShode vaziateSalamatiBimeShode){
        super.update(vaziateSalamatiBimeShode);
    }

    public void saveOrUpdateAll(List<VaziateSalamatiBimeShode> vaziateSalamatiBimeShodeList){
        super.saveOrUpdateAll(vaziateSalamatiBimeShodeList);
    }

    public void deleteItem(VaziateSalamatiBimeShode vaziateSalamatiBimeShode) {
        super.delete(vaziateSalamatiBimeShode);
    }

    public void deleteItems(List<VaziateSalamatiBimeShode> vaziateSalamatiBimeShodeListToRemove) {
        super.deleteAll(vaziateSalamatiBimeShodeListToRemove);
    }
}
