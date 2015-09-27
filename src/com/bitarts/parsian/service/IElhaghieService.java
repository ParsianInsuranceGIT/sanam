package com.bitarts.parsian.service;

import com.bitarts.parsian.model.bimename.Elhaghiye;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 5/13/12
 * Time: 12:18 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IElhaghieService extends IBaseService  {
    public static final String SERVICE_NAME = "elhaghieService";

    public Elhaghiye findById(Integer id);

    public List<Elhaghiye> findByBimename(Integer bimename, Elhaghiye.ElhaghiyeType type);

    void sabteElhaghie(Elhaghiye elhaghie);
}
