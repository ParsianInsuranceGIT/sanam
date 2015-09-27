package com.bitarts.parsian.util;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.asnadeSodor.Credebit;

/**
 * Created by IntelliJ IDEA.
 * User: n-tehranifar
 * Date: 2/27/13
 * Time: 1:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class DPUtil {

    public static String getBankName(String shomareHesab) {
        if (shomareHesab.equals("0201136462000")) {
            return Constant.CREDEBIT_BANK_TEJARAT_EL_MIRDAMAD;
        } else if (shomareHesab.equals("0200234164006")) {
            return Constant.CREDEBIT_BANK_PARSIAN_BOLVAR_KESHAVARZ;
        } else if (shomareHesab.equals("81011989")) {
            return Constant.CREDEBIT_BANK_PARSIAN_VANAK;
        } else if (shomareHesab.equals("17038494")) {
            return Constant.CREDEBIT_BANK_TEJARAT_SEPAHBOD_GHARANI;
        } else if (shomareHesab.equals("0185007111")) {
            return Constant.CREDEBIT_BANK_TEJARAT_SEPAHBOD_GHARANI_NEW;
        } else if (shomareHesab.equals("4757575763")) {
            return Constant.CREDEBIT_BANK_MELAT_VANAK;
        } else if (shomareHesab.equals("2177777733")) {
            return Constant.CREDEBIT_BANK_MELAT_ESKAN;
        }
        return "ERROR";
    }

    public static String isCredebitValidForSabtDasti(Credebit c) {
        if (c.getCredebitType().equals(Credebit.CredebitType.DARYAFTE_CHECK)) //agar noe etebar daryafte check ast
        {
            if (DateUtil.isGreaterThanOrEqual(DateUtil.getCurrentDate(), c.getSarresidDate())) //agar tarikhe jari bozorgtar ya mosavie tarikhe sarreside etebar ast
                return "SARRESID_BEFORE_TODAY"; //peyghame monaseb
        }
        return "VALID";
    }

}
