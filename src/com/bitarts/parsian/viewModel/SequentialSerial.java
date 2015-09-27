package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.action.SerialsAction;
import com.bitarts.parsian.model.Sequence;
import com.bitarts.parsian.model.bimename.Serial;
import com.bitarts.parsian.service.calculations.Reports.CountSequentialSerials;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: a-khezri
 * Date: 2/26/13
 * Time: 3:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class SequentialSerial
{
    private List<CountSequentialSerials> counts;
    private List<Serial> fSerials;
    private List<Serial> lSerials;

    private Long totalCount;
    private Long fromSerial;
    private Long toSerial;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getFromSerial() {
        return fromSerial;
    }

    public void setFromSerial(Long fromSerial) {
        this.fromSerial = fromSerial;
    }

    public Long getToSerial() {
        return toSerial;
    }

    public void setToSerial(Long toSerial) {
        this.toSerial = toSerial;
    }

    public List<CountSequentialSerials> getCounts() {
        return counts;
    }

    public void setCounts(List<CountSequentialSerials> counts) {
        this.counts = counts;
    }

    public List<Serial> getFSerials() {
        return fSerials;
    }

    public void setFSerials(List<Serial> fSerials) {
        this.fSerials = fSerials;
    }

    public List<Serial> getLSerials() {
        return lSerials;
    }

    public void setLSerials(List<Serial> lSerials) {
        this.lSerials = lSerials;
    }
}

