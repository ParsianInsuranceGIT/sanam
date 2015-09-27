package com.bitarts.parsian.dao;

import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.model.pishnahad.Foroshande;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/8/11
 * Time: 2:38 PM
 */
public class ForoshandeDAO extends BaseDAO {
    public static String ID = "id";
    public static String pishnahaad_dahande_mishenaasid = "pishnahaad_dahande_mishenaasid";
    public static String modat_shenaakht = "modat_shenaakht";
    public static String molaahezaat_az_salaamat_feli = "molaahezaat_az_salaamat_feli";
    public static String tozihe_molaahezaat = "tozihe_molaahezaat";
    public static String etelaat_kaamel = "etelaat_kaamel";
    public static String sehat_emzaa = "sehat_emzaa";

    public void save(Foroshande foroshande) {
        super.save(foroshande);
    }

    public void update(Foroshande foroshande) {
        super.saveOrUpdate(foroshande);
    }

    public Foroshande findById(Integer id) {
        return (Foroshande) super.findById(Foroshande.class, id);
    }
}
