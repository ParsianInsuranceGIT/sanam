package com.bitarts.parsian.dao;

import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.model.pishnahad.BimeGozar;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 2/24/11
 * Time: 7:06 PM
 */
public class BimeGozarDAO extends BaseDAO {
    public static String ID = "id";
    public static String daramadeMahiane = "daramadeMahiane";
    public static String nesbatBabimeShode = "nesbatBabimeShode";
    public static String shakhs = "shakhs";

    public void save(BimeGozar bimeGozar) {
        super.save(bimeGozar);
    }

    public void update(BimeGozar bimeGozar) {

        getSession().merge(bimeGozar);
        super.update(bimeGozar);

    }

    public BimeGozar findById(Integer id) {
        return (BimeGozar) super.findById(BimeGozar.class, id);
    }
}
