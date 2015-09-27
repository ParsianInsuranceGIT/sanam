package com.bitarts.parsian.dao;

import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.model.pishnahad.VaziateSalamatiKhanevadeyeBimeShode;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/8/11
 * Time: 2:21 PM
 */
public class VaziateSalamatiKhanevadeyeBimeShodeDAO extends BaseDAO {
    //    private Integer id;
//    private String pedar_sen;
//    private String pedar_vazeeyat_Salamat;
//    private String pedar_ellat_fot;
//    private String maadar_sen;
//    private String maadar_vazeeyat_Salamat;
//    private String maadar_vazeeyat_ellat_fot;
//    private String khaahar_sen;
//    private String khaahar_vazeeyat_Salamat;
//    private String khaahar_ellat_fot;
//    private String baraadar_sen;
//    private String baraadar_vazeeyat_Salamat;
//    private String baraadar_ellat_fot;
    public void save(VaziateSalamatiKhanevadeyeBimeShode vaziateSalamatiKhanevadeyeBimeShode){
        super.save(vaziateSalamatiKhanevadeyeBimeShode);
    }

    public void update(VaziateSalamatiKhanevadeyeBimeShode vaziateSalamatiKhanevadeyeBimeShode){
        super.update(vaziateSalamatiKhanevadeyeBimeShode);
    }

    public void deleteItem(VaziateSalamatiKhanevadeyeBimeShode vaziateSalamatiKhanevadeyeBimeShode) {
        super.delete(vaziateSalamatiKhanevadeyeBimeShode);
    }

    public void saveOrUpdateAll(List<VaziateSalamatiKhanevadeyeBimeShode> vaziateSalamatiKhanevadeyeBimeShodeList){
        for(VaziateSalamatiKhanevadeyeBimeShode v : vaziateSalamatiKhanevadeyeBimeShodeList) {
            if(v.getSharheEllateFout() != null)
                v.setSharheEllateFout(v.getSharheEllateFout().replaceAll("\r\n"," "));
        }
        super.saveOrUpdateAll(vaziateSalamatiKhanevadeyeBimeShodeList);
    }
}
