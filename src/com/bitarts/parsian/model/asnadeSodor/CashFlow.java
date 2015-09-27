package com.bitarts.parsian.model.asnadeSodor;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.bimename.Bimename;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 9/27/11
 * Time: 3:29 PM
 */

public class CashFlow {
    private List<Flow> flows;
    public static final double SOUD = 1.15;
    public static final double SOUD_18 = 1.18;

    public CashFlow(Collection<Flow> flows) {
        ArrayList<Flow> f = new ArrayList<Flow>();
        f.addAll(flows);
        this.flows = f;
    }

    public CashFlow() {

    }

    private double tajmi(String date, double soud) {
        double finalAmount = 0;
        for (Flow flow : flows) {
            int difffDate = DateUtil.getTimeDifferenceByDayCount(date, flow.getDate());
            double amounttVal = flow.getAmount();
            if (flow.getType() == Flow.Type.GHEST || flow.getType() == Flow.Type.GHEST_VAM || flow.getType() == Flow.Type.HAZINEHA_MANFI) {
                amounttVal *= -1;
            }
            double difffperyear = difffDate / 365.0;
            double difffperyearenchanted = Math.pow(soud, difffperyear);
            double amounttEnchanted = amounttVal * difffperyearenchanted;
            finalAmount += amounttEnchanted;
        }
        return finalAmount;
    }

    public double tajmi(Bimename bimename, String date, double soud) {
        if (bimename == null) {
            return tajmi(date, soud);
        } else {
            if(bimename.getPishnehad().getTarh() != null && bimename.getPishnehad().getTarh().getHijdahDarsad()) {
                double finalAmount = 0;
                String tarikhPayan18Darsad = DateUtil.addYear(bimename.getTarikhShorou(), 5);
                boolean shouldCalc15 = true;
                String tarikhMabnaCurrent = tarikhPayan18Darsad;
                if(DateUtil.isGreaterThan(tarikhPayan18Darsad, date)) {
                    shouldCalc15 = false;
                    tarikhMabnaCurrent = date;
                }
                ArrayList<Flow> newFlows = new ArrayList<Flow>();
                for(Flow f : flows) {
                    if(DateUtil.isGreaterThan(tarikhPayan18Darsad, f.getDate())) {
                        int difffDate = DateUtil.getTimeDifferenceByDayCount(tarikhMabnaCurrent, f.getDate());
                        double amounttVal = f.getAmount();
                        if (f.getType() == Flow.Type.GHEST || f.getType() == Flow.Type.GHEST_VAM || f.getType() == Flow.Type.HAZINEHA_MANFI) {
                            amounttVal *= -1;
                        }
                        double difffperyear = difffDate / 365.0;
                        double difffperyearenchanted = Math.pow(SOUD_18, difffperyear);
                        double amounttEnchanted = amounttVal * difffperyearenchanted;
                        Flow newF = new Flow(amounttEnchanted, f.getType(), tarikhPayan18Darsad);
                        newFlows.add(newF);
                        finalAmount += amounttEnchanted;
                    } else {
                        newFlows.add(f);
                    }
                }
                if(shouldCalc15) {
                    setFlows(newFlows);
                    return tajmi(date, soud);
                } else {
                    return finalAmount;
                }

            } else {
                return tajmi(date, soud);
            }
        }
    }


    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(List<Flow> flows) {
        this.flows = flows;
    }

}
