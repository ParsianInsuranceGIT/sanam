package com.bitarts.parsian.service.implementation;

import com.bitarts.parsian.dao.CheckDAO;
import com.bitarts.parsian.model.check.Check;
import com.bitarts.parsian.model.check.DasteCheck;
import com.bitarts.parsian.service.ICheckService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron0
 * Date: Aug 30, 2011
 * Time: 12:33:56 PM
 */
public class CheckService implements ICheckService {
    private CheckDAO checkDAO;

    public void setCheckDAO(CheckDAO checkDAO) {
        this.checkDAO = checkDAO;
    }

    public List<DasteCheck> loadDasteCheckHa() {
        return checkDAO.findAllDasteCheck();
    }

    public void addDasteCheck(DasteCheck dasteCheck) {
        List<Check> checkList = new ArrayList<Check>();
       for(long i = Long.parseLong(dasteCheck.getAzShomare()); i <= Long.parseLong(dasteCheck.getTaShomare()) ; i++){
           Check check = new Check();
           check.setStatus(Check.Status.NORMAL);
           check.setDasteCheck(dasteCheck);
           check.setShomare(String.valueOf(i));
           check.setAmountTajamoi("0");
           checkList.add(check);
       }
        checkDAO.saveDasteCheck(dasteCheck);
        checkDAO.saveCheckList(checkList);
    }

    public DasteCheck findDasteCheckById(Integer dasteCheckId) {
        return checkDAO.findDasteCheckById(dasteCheckId);
    }

    public void editCheck(Check check) {
        Check olCheck = checkDAO.findCheckById(check.getId());
        olCheck.setDarVajhe(check.getDarVajhe());
        olCheck.setTarikh(check.getTarikh());
        checkDAO.updateCheck(olCheck);
    }

    public Check findCheckById(Integer checkId) {
        return checkDAO.findCheckById(checkId);
    }

    public void changeCheckStatusToPrinted(Check check) {
        
        check.setStatus(Check.Status.PRINT_SHODE);
        checkDAO.updateCheck(check);
    }

    public List<Check> findAllNormalCheck() {
        return checkDAO.findAllCheckByStatus(Check.Status.NORMAL);
    }

    public void saveOrUpdateCheck(Check check)
    {
        checkDAO.saveOrUpdate(check);
    }

}
