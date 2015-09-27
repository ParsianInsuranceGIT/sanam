package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.constantItems.ConstantForPishnehadForm;
import com.bitarts.parsian.model.constantItems.ConstantSoalateVaziateSalamati;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 5/5/11
 * Time: 4:52 PM
 */
public class PishnehadConstants {
    private List<ConstantForPishnehadForm> constantForPishnehadFormList;
    private List<ConstantSoalateVaziateSalamati> constantSoalateVaziateSalamatiSet;

    public List<ConstantForPishnehadForm> getConstantForPishnehadFormList() {
        return constantForPishnehadFormList;
    }

    public void setConstantForPishnehadFormList(List<ConstantForPishnehadForm> constantForPishnehadFormList) {
        this.constantForPishnehadFormList = constantForPishnehadFormList;
    }

    public List<ConstantSoalateVaziateSalamati> getConstantSoalateVaziateSalamatiSet() {
        return constantSoalateVaziateSalamatiSet;
    }

    public void setConstantSoalateVaziateSalamatiSet(List<ConstantSoalateVaziateSalamati> constantSoalateVaziateSalamatiSet) {
        this.constantSoalateVaziateSalamatiSet = constantSoalateVaziateSalamatiSet;
    }
}
