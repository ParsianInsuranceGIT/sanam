package com.bitarts.parsian.dao;

import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.model.pishnahad.SavabegheBimei;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/8/11
 * Time: 2:33 PM
 */
public class SavabegheBimeiDAO extends BaseDAO {
    public static String ID = "id";
    public static String noeBimeName = "noeBimeName";
    public static String nameBimeName = "nameBimeName";
    public static String sherkateBimeGar = "sherkateBimeGar";
    public static String natijeNahayi = "natijeNahayi";
    public static String sarmayeFout = "sarmayeFout";
    public static String sarmayeHayat = "sarmayeHayat";
    public static String sharheAdameSodor = "sharheAdameSodor";
    public static String tarikheKhateme = "tarikheKhateme";
    public static String pishnehad = "pishnehad";

    public void save(SavabegheBimei savabegheBimei){
        super.save(savabegheBimei);
    }

    public void update(SavabegheBimei savabegheBimei){
        super.update(savabegheBimei);
    }

    public void saveOrUpdateAll(List<SavabegheBimei> savabegheBimeiList){
        super.saveOrUpdateAll(savabegheBimeiList);
    }

    public void deleteItem(SavabegheBimei savabegheBimei) {
        super.delete(savabegheBimei);
    }

    public void deleteItems(List<SavabegheBimei> savabegheBimeiListToRemove) {
        super.deleteAll(savabegheBimeiListToRemove);
    }
}