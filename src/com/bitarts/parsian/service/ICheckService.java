package com.bitarts.parsian.service;

import com.bitarts.parsian.model.check.Check;
import com.bitarts.parsian.model.check.DasteCheck;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron0
 * Date: Aug 30, 2011
 * Time: 12:32:31 PM
 */
public interface ICheckService extends IBaseService  {
    public static final String SERVICE_NAME = "checkService";

    public List<DasteCheck> loadDasteCheckHa();

    public void addDasteCheck(DasteCheck dasteCheck);

    public DasteCheck findDasteCheckById(Integer dasteCheckId);

    public void editCheck(Check check);

    public Check findCheckById(Integer checkId);

    public void changeCheckStatusToPrinted(Check check);

    public List<Check> findAllNormalCheck();

    public void saveOrUpdateCheck(Check check);
}
