package com.bitarts.parsian.service.implementation;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.dao.EstelamDAO;
import com.bitarts.parsian.model.estelam.Estelam;
import com.bitarts.parsian.model.estelam.UserComment;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.service.IEstelamService;
import com.bitarts.parsian.service.calculations.Constants.NSPConstants;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/8/11
 * Time: 4:20 PM
 */
public class EstelamService implements IEstelamService {
    private EstelamDAO estelamDAO;

    public void setEstelamDAO(EstelamDAO estelamDAO) {
        this.estelamDAO = estelamDAO;
    }

    public void addNewEstelam(Estelam estelam) {
        int ageBimeE = mohasebeyeSenneBimeie(estelam);
        estelam.setSeneBimeie(String.valueOf(ageBimeE));
        estelamDAO.save(estelam);
    }

    public Estelam getEstelamById(Integer id) {
        return estelamDAO.findEstelamById(id);
    }

    public void updateEstelam(Estelam estelam) {
        int ageBimeE = mohasebeyeSenneBimeie(estelam);
        estelam.setSeneBimeie(String.valueOf(ageBimeE));
        estelamDAO.updateEstelam(estelam);
    }

    public void saveComment(UserComment userComment) {
        estelamDAO.saveUserComment(userComment);
    }

    public List<UserComment> findUserComments() {
        return estelamDAO.findAllUserComments();
    }

    public Estelam adapte(Estelam estelam) {
        if (!(estelam.getPooshesh_fot_dar_asar_haadese() != null && estelam.getPooshesh_fot_dar_asar_haadese().equals("yes"))) {
            estelam.setSarmaye_paye_fot_dar_asar_hadese("0");
        }
        if (!(estelam.getPooshesh_naghs_ozv() != null && estelam.getPooshesh_naghs_ozv().equals("yes"))) {
            estelam.setSarmaye_pooshesh_naghs_ozv("0");
        }
        if (!(estelam.getPooshesh_amraz_khas() != null && estelam.getPooshesh_amraz_khas().equals("yes"))) {
            estelam.setSarmaye_pooshesh_amraz_khas("0");
        }
        if (estelam.getSen_bime_shode() == null) estelam.setSen_bime_shode("0");
        if (estelam.getDarsad_ezafe_nerkh_pezeshki() == null || estelam.getDarsad_ezafe_nerkh_pezeshki().isEmpty()) estelam.setDarsad_ezafe_nerkh_pezeshki("0");
        if (estelam.getSarmaye_paye_fot_dar_asar_hadese() == null) estelam.setSarmaye_paye_fot_dar_asar_hadese("0");
        if (estelam.getSarmaye_paye_fot() == null) estelam.setSarmaye_paye_fot("0");
        if (estelam.getSarmaye_pooshesh_amraz_khas() == null) estelam.setSarmaye_pooshesh_amraz_khas("0");
        if (estelam.getSarmaye_pooshesh_naghs_ozv() == null) estelam.setSarmaye_pooshesh_naghs_ozv("0");
        if (estelam.getNerkh_afzayesh_salaneh_hagh_bime() == null) estelam.setNerkh_afzayesh_salaneh_hagh_bime("0");
        if (estelam.getNerkh_afzayesh_salaneh_sarmaye_fot() == null) estelam.setNerkh_afzayesh_salaneh_sarmaye_fot("0");
        if (estelam.getHagh_bime_pardakhti() == null) estelam.setHagh_bime_pardakhti("0");
        if (estelam.getMablagh_seporde_ebtedaye_sal() == null) estelam.setMablagh_seporde_ebtedaye_sal("0");
        if (estelam.getNerkh_afzayesh_salaneh_mostamari() == null) estelam.setNerkh_afzayesh_salaneh_mostamari("0");
        if (estelam.getModdat_zaman_daryaft_mostamari() == null) estelam.setModdat_zaman_daryaft_mostamari("0");
        if (estelam.getTabaghe_khatar() == null || estelam.getTabaghe_khatar().equals("undefined")) estelam.setTabaghe_khatar("1");
        if (estelam.getTabaghe_khatar_naghsozv() == null || estelam.getTabaghe_khatar_naghsozv().equals("undefined")) estelam.setTabaghe_khatar_naghsozv("1");
        if (estelam.getDarsad_takhfif_karmozd_karmozd() == null) estelam.setDarsad_takhfif_karmozd_karmozd("0");
        if (estelam.getDarsad_takhfif_vosool() == null) estelam.setDarsad_takhfif_vosool("0");

        return estelam;
    }

    public int mohasebeyeSenneBimeie(Estelam estelam) {
        double value = 0;
        short age = 0;
        double[][] lifeTable;
        if (estelam.getPishnehad() == null || estelam.getPishnehad().getBimename() == null)
        {
            lifeTable = AsnadeSodorService.getLifeTable(DateUtil.getCurrentDate(), estelam.getPishnehad() == null ? null : estelam.getPishnehad().getTarh());
        }
        else
        {
            lifeTable = AsnadeSodorService.getLifeTable(estelam.getPishnehad().getBimename().getTarikhSodour(), estelam.getPishnehad() == null ? null : estelam.getPishnehad().getTarh());
        }
        short seneAsliBimeshode = Short.parseShort(estelam.getSen_bime_shode());
        int ezafeNerkhPezeshki = Integer.parseInt(estelam.getDarsad_ezafe_nerkh_pezeshki());
        if (!estelam.getDarsad_ezafe_nerkh_pezeshki().equals("0")) {
            for (int rowReader = 0; rowReader < lifeTable.length; rowReader++) {
                if (seneAsliBimeshode == lifeTable[rowReader][0]) {
                    value = lifeTable[rowReader][8] * (1.0 + ezafeNerkhPezeshki);
                    break;
                }
            }
            double minus = 1.0;
            for (int rowReader = 0; rowReader < lifeTable.length; rowReader++) {
                if (Math.abs(lifeTable[rowReader][8] - value) < minus && lifeTable[rowReader][8] <= value) {
                    minus = Math.abs(lifeTable[rowReader][8] - value);
                    age = (short) (lifeTable[rowReader][0] + 1);
                }
            }
            return age;
        } else {
            return seneAsliBimeshode;
        }
    }
}
