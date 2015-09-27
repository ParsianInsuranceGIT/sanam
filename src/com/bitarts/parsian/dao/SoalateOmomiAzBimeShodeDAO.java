package com.bitarts.parsian.dao;

import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.model.pishnahad.SoalateOmomiAzBimeShode;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/8/11
 * Time: 1:53 PM
 */
public class SoalateOmomiAzBimeShodeDAO extends BaseDAO {
    public static String ID = "id";
    public static String ghadBimeShode = "ghadBimeShode";
    public static String vaznBimeShode = "vaznBimeShode";
    public static String khedmatNezamVazife = "khedmatNezamVazife";
    public static String moaafiyatPezeshki = "moaafiyatPezeshki";
    public static String faaliyatJanbi = "faaliyatJanbi";
    public static String tozihFaaliyatJanbi = "tozihFaaliyatJanbi";

    public void save(SoalateOmomiAzBimeShode soalateOmomiAzBimeShode) {
        super.save(soalateOmomiAzBimeShode);
    }

    public void update(SoalateOmomiAzBimeShode soalateOmomiAzBimeShode) {
        super.saveOrUpdate(soalateOmomiAzBimeShode);
    }

    public SoalateOmomiAzBimeShode findById(Integer soalateOmomiAzBimeShode) {
        return (SoalateOmomiAzBimeShode) super.findById(SoalateOmomiAzBimeShode.class, soalateOmomiAzBimeShode);
    }
}
