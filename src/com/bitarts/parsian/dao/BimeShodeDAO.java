package com.bitarts.parsian.dao;

import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.model.pishnahad.BimeShode;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 2/24/11
 * Time: 7:06 PM
 */
public class BimeShodeDAO extends BaseDAO {
    public static String ID = "id";
    public static String shakhs = "shakhs";

    public void save(BimeShode bimeShode) {
        super.save(bimeShode);
    }

    public void update(BimeShode bimeShode) {
        super.update(bimeShode);
    }

    public void saveOrUpdate(BimeShode bimeShode) {
        super.saveOrUpdate(bimeShode);
    }

    public BimeShode findById(Integer bimeShode) {
        return (BimeShode) super.findById(BimeShode.class, bimeShode);
    }
}
