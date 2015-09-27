package com.bitarts.parsian.model.utility;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.bimename.Elhaghiye;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: n-tehranifar
 * Date: 5/9/13
 * Time: 10:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class ElhaghiyeSaleBimeIntComparator implements Comparator<Elhaghiye> {

        public int compare(Elhaghiye o1, Elhaghiye o2) {
            if (o1.getId().equals(o2.getId())) {
                return 0;
            } else if (o1.getSaleBimeiAsar() > o2.getSaleBimeiAsar()) {
                return 1;
            } else if(o1.getSaleBimeiAsar() == o2.getSaleBimeiAsar()) {
                if(DateUtil.isGreaterThan(o1.getCreatedDate(), o2.getCreatedDate())) {
                    return 1;
                } else if(DateUtil.isGreaterThan(o2.getCreatedDate(), o1.getCreatedDate())) {
                    return -1;
                } else {
                    if(o1.getId() > o2.getId())
                        return 1;
                    else if(o1.getId() < o2.getId())
                        return -1;
                    else
                        return 0;
                }
            } else {
                return -1;
            }
        }
    }
