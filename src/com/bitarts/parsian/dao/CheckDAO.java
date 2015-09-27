package com.bitarts.parsian.dao;

import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.model.check.Check;
import com.bitarts.parsian.model.check.DasteCheck;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron0
 * Date: Aug 30, 2011
 * Time: 12:22:28 PM
 */
public class CheckDAO extends BaseDAO {
    public static final String ID = "id";
    public static final String STATUS = "status";

    public void saveCheck(Check check){
        super.save(check);
    }
    public void saveDasteCheck(DasteCheck dasteCheck){
        super.save(dasteCheck);
    }
    public void saveCheckList(List<Check> checkList) {
        super.saveOrUpdateAll(checkList);
    }
    public Check findCheckById(Integer checkId){
        return (Check) super.findById(Check.class, checkId);
    }
    public DasteCheck findDasteCheckById(Integer dasteCheckId){
        return (DasteCheck) super.findById(DasteCheck.class, dasteCheckId);
    }
    public void updateCheck(Check check){
        super.update(check);
    }

    public List<DasteCheck> findAllDasteCheck() {
        return super.findAll(DasteCheck.class);
    }

    public List<Check> findAllCheckByStatus(Check.Status status) {
        return super.findByProperty(Check.class, STATUS, status);
    }
}
