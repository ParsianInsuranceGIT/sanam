package com.bitarts.parsian.service.implementation;

import com.bitarts.parsian.dao.ConstantsDao;
import com.bitarts.parsian.model.constantItems.Constants;
import com.bitarts.parsian.model.constantItems.Tarh;
import com.bitarts.parsian.service.IConstantsService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 5/13/12
 * Time: 12:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConstantsService implements IConstantsService {
    private ConstantsDao constantsDAO;

    public Constants findById(Integer id) {
        return constantsDAO.findById(id);
    }

    public List<Constants> findByName(Constants.Keys name, Constants.KeysParam name2) {
        return constantsDAO.findByName(name, name2);
    }

    public List<Constants> findByNameConsiderEffect(Constants.Keys name, Constants.KeysParam name2, String date, Tarh tarh) {
        return constantsDAO.findByNameConsiderEffect(name, name2, date, tarh);
    }

    public List<Constants> findByNameConsiderEffectByApplyStyle(Constants.Keys name, Constants.KeysParam name2, String date, Constants.ConstantsApplyStyle applyStyle) {
        return constantsDAO.findByNameConsiderEffectByApplyStyle(name, name2, date, applyStyle);
    }

    public void saveOrUpdate(Constants c) {
        constantsDAO.saveOrUpdate(c);
    }

    public List<Constants> findAll() {
        return constantsDAO.findAll();
    }

    public ConstantsDao getConstantsDAO() {
        return constantsDAO;
    }

    public void setConstantsDAO(ConstantsDao constantsDAO) {
        this.constantsDAO = constantsDAO;
    }

    public List<Tarh> listAllTarhs() {
        return constantsDAO.listAllTarhs();
    }

    public void saveOrUpdateTarh(Tarh tarh) {
        constantsDAO.saveOrUpdateTarh(tarh);
    }

    public Tarh findTarhById(Long id) {
        return constantsDAO.findTarhById(id);
    }

}
