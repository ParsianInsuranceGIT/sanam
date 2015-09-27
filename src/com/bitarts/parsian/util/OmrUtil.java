package com.bitarts.parsian.util;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.Role;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.asnadeSodor.Ghest;
import com.bitarts.parsian.model.asnadeSodor.GhestBandi;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.DarkhastTaghirat;
import com.bitarts.parsian.model.pishnahad.Shakhs;
import com.bitarts.parsian.service.calculations.Reports.TaghsitReport;
import com.bitarts.parsian.viewModel.PishnehadFieldChanges;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: n-tehranifar
 * Date: 2/27/13
 * Time: 1:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class OmrUtil {

    public static String calculateTarikhAsar(int saleBimei, String tarikhShorou) {
        return DateUtil.addYear(tarikhShorou, saleBimei);
    }

    public static int rondPardakhti(int amount) {
        return (int)(Math.round((float)amount / 1000.00))*1000;
    }
    public static int rondPardakhtiDahRial(int amount) {
        return (int)(Math.round((float)amount / 100.00))*100;
    }

    public static boolean isElhaghieTaghiratMali(List<PishnehadFieldChanges> pishnehadFieldChangesList) {
        boolean option_changes = false;
        for (PishnehadFieldChanges p : pishnehadFieldChangesList)
            if (p.isOption()) {
                option_changes = true;
                break;
            }
        return option_changes;
    }

    public static long majmooeAghsat(List<TaghsitReport> taghsitReports) {
        long returnValue = 0;
        for(TaghsitReport tr : taghsitReports) {
            returnValue += tr.getGhestAmount();
        }
        return returnValue;
    }

    public static boolean doesMinuteAndSecondMatch(String timeFromFish, String timeFromBank) {
        String timeSubstr = timeFromBank;
        if (timeSubstr.length() > 5)
            timeSubstr = timeFromBank.substring(3);
        return timeSubstr.equals(timeFromFish);
    }

    public static boolean userHasRole(User user, String roleName) {
        boolean result = false;
        if (user != null){
            Set<Role> roles = user.getRoles();
            for (Role role : roles) {
                if (role.getRoleName().equalsIgnoreCase(roleName)) {
                    result = true;
                }
            }
        }
        return result;
    }

    public static int getToGhestbandiYear(Bimename bimename) {
        int toGhestBandi = 0;
        for (GhestBandi gb : bimename.getGhestBandiList()) {
            if (gb.getSaleBimeiInt() != null && gb.getSaleBimeiInt() > toGhestBandi)
                toGhestBandi = gb.getSaleBimeiInt();
        }
        return toGhestBandi + 1;
    }
    public static String  getCredebitTypeFarsi(Credebit.CredebitType TypeEtebar)   {

        Credebit credebit=new Credebit();
        credebit.setCredebitType(TypeEtebar);

        return credebit.getCredebitTypeFarsi();
    }

    public static boolean isElhaghieTaghirShakhsMali(List<PishnehadFieldChanges> changes) {
        for(PishnehadFieldChanges change : changes)
        {
            if(change.getSubject().equals("����� ���� ���� ���"))
                return true;
        }
        return false;
    }

    public static boolean isElhaghieTaghirShakhs(List<PishnehadFieldChanges> changes, Shakhs.Role role) {
        String toSearch = "";
        if(role == Shakhs.Role.BIMEGOZAR) {
            toSearch = "BG";
        } else if(role == Shakhs.Role.BIMESHODE) {
            toSearch = "BS";
        }
        for (PishnehadFieldChanges change : changes) {
            if (change.getCategory()!=null && change.getCategory().equals(toSearch))
                return true;
        }
        return false;
    }

    public static String isElhaghieValid(List<PishnehadFieldChanges> changes, DarkhastTaghirat dt) {
        String returnValue = "VALID";
        boolean isEditBimeShode = isElhaghieTaghirShakhs(changes, Shakhs.Role.BIMESHODE);
        boolean isEditBimeGozar = isElhaghieTaghirShakhs(changes, Shakhs.Role.BIMEGOZAR);
        if(!(isEditBimeGozar && !isEditBimeShode && dt.getNewPishnehad().getBimeGozar().getShakhs().getChangeWithSerach()!=null))
        {
                if(isEditBimeGozar || isEditBimeShode) {
                boolean taghirDigar = false;
                for (PishnehadFieldChanges change : changes) {
                    if (change.getCategory() == null || (!change.getCategory().equals("DARAMAD") && !change.getCategory().equals("BG") && !change.getCategory().equals("BS") &&
                            !change.getCategory().equals("NESBAT") && !change.getCategory().equals("AD"))) {
                        taghirDigar = true;
                        break;
                    }
                }
                if(taghirDigar) {
                    returnValue = "لطفا تغييرات خارج از تغييرات مربوط به بيمه گذار و بيمه شده  را در درخواست الحاقيه ديگري ثبت نمائيد";
                }
            }
        }
        return returnValue;

    }
    public static String getVosoulStateFarsi(Credebit.VaziyatVosoul vosoulState) {
        Credebit credebit=new Credebit();
        credebit.setVosoulState(vosoulState);

        return credebit.getVosoulStateFarsi();

      }

    public static void generateGhestNumber(GhestBandi gb)
    {
        List<GhestBandi> gbList=gb.getBimename().getGhestBandiList();
        int i = 0;
        for (GhestBandi tempGB : gbList)
        {
            if (tempGB.getType().equals(GhestBandi.Type.G_BIMENAME) && tempGB.getSaleBimeiInt() < gb.getSaleBimeiInt())
            {
                i += gb.getGhestList().size();
            }
        }
        Collections.sort(gb.getGhestList());

        for(Ghest g : gb.getGhestList())
        {
            i++;
            g.setNumber(i);
        }
    }
    public static String getShomareHesabTejarat(String shomareMoshtari)
    {
        if(shomareMoshtari.substring(7, 9).equals("10"))return "0185007111";
        else return "17038494";

    }

}
