package com.bitarts.parsian.service;

import com.bitarts.parsian.model.asnadeSodor.CredebitForLogGhest;
import com.bitarts.parsian.model.asnadeSodor.LogGhest;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 5/25/11
 * Time: 2:51 PM
 */
public interface ILogGhestService extends IBaseService {


    public void save(LogGhest logGhest);

    public void saveAllCFLG(List<CredebitForLogGhest> cflgList);
}
