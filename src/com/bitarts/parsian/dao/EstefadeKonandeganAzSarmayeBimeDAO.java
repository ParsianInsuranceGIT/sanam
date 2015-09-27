package com.bitarts.parsian.dao;

import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.model.pishnahad.EstefadeKonandeganAzSarmayeBime;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/13/11
 * Time: 5:57 PM
 */
public class EstefadeKonandeganAzSarmayeBimeDAO extends BaseDAO {
    public static String ID = "id";
    public static String name = "name";
    public static String pishnehad = "pishnehad";

    public void saveOrUpdateAll(List<EstefadeKonandeganAzSarmayeBime> estefadeKonandeganAzSarmayeBimeList) {
        super.saveOrUpdateAll(estefadeKonandeganAzSarmayeBimeList);
    }

    public void save(EstefadeKonandeganAzSarmayeBime estefadeKonandeganAzSarmayeBime) {
        super.save(estefadeKonandeganAzSarmayeBime);
    }

    public void deleteItem(EstefadeKonandeganAzSarmayeBime estefadeKonandeganAzSarmayeBime) {
        super.delete(estefadeKonandeganAzSarmayeBime);
    }

    public void deleteItems(List<EstefadeKonandeganAzSarmayeBime> estefadeKonandeganAzSarmayeBimeListToRemove) {
        super.deleteAll(estefadeKonandeganAzSarmayeBimeListToRemove);
    }
}
