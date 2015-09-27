package com.bitarts.parsian.service;

import com.bitarts.parsian.model.constantItems.Constants;
import com.bitarts.parsian.model.constantItems.Tarh;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 5/13/12
 * Time: 12:18 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IConstantsService extends IBaseService  {
    public static final String SERVICE_NAME = "constantsService";

    public Constants findById(Integer id);

    public List<Constants> findByName(Constants.Keys name, Constants.KeysParam name2);

    public List<Constants> findByNameConsiderEffect(Constants.Keys name, Constants.KeysParam name2, String date, Tarh tarh);

    public List<Constants> findByNameConsiderEffectByApplyStyle(Constants.Keys name, Constants.KeysParam name2, String date, Constants.ConstantsApplyStyle applyStyle);

    public void saveOrUpdate(Constants c);

    public List<Constants> findAll();

    public List<Tarh> listAllTarhs();

    public void saveOrUpdateTarh(Tarh tarh);

    public Tarh findTarhById(Long id);

}
