package com.bitarts.parsian.service.implementation;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.util.DateUtil;
import com.bitarts.common.util.StringUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.dao.AsnadeSodorDAO;
import com.bitarts.parsian.dao.CheckDAO;
import com.bitarts.parsian.model.*;
import com.bitarts.parsian.model.asnadeSodor.*;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.DarkhastBazkharid;
import com.bitarts.parsian.model.check.Check;
import com.bitarts.parsian.model.constantItems.Constants;
import com.bitarts.parsian.model.constantItems.Tarh;
import com.bitarts.parsian.model.estelam.Estelam;
import com.bitarts.parsian.model.karmozd.KarmozdGhest;
import com.bitarts.parsian.model.management.Hesab;
import com.bitarts.parsian.model.pishnahad.Fish;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.model.pishnahad.Shakhs;
import com.bitarts.parsian.service.*;
import com.bitarts.parsian.service.calculations.BimeNaamehVaSarmayehGozari;
import com.bitarts.parsian.service.calculations.Constants.LifeTable;
import com.bitarts.parsian.service.calculations.MohasebateTeoriView;
import com.bitarts.parsian.service.calculations.MohasebateTeory;
import com.bitarts.parsian.service.calculations.Reports.TaghsitReport;
import com.bitarts.parsian.service.calculations.TaeenAndukhteBarMabnaayeHaghBimePardaakhti;
import com.bitarts.parsian.service.karmozd.IKarmozdService;
import com.bitarts.parsian.util.DPUtil;
import com.bitarts.parsian.util.OmrUtil;
import com.bitarts.parsian.viewModel.*;
import com.bitarts.parsian.viewModel.search.CredebitSearchForm;
import com.opensymphony.xwork2.ActionContext;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 5/18/11
 * Time: 2:28 PM
 */
public class AsnadeSodorService implements IAsnadeSodorService
{
    private AsnadeSodorDAO asnadeSodorDAO;
    private ISequenceService sequenceService;
    private IPishnehadService pishnehadService;
    private IKarmozdService karmozdService;
    private INamayandeService namayandeService;
    private static IConstantsService constantsService;
    private CheckDAO checkDAO;
    private ILoginService loginService;

    public INamayandeService getNamayandeService()
    {
        return namayandeService;
    }

    public void setNamayandeService(INamayandeService namayandeService)
    {
        this.namayandeService = namayandeService;
    }

    public ILoginService getLoginService()
    {
        return loginService;
    }

    public void setLoginService(ILoginService loginService)
    {
        this.loginService = loginService;
    }

    public IConstantsService getConstantsService()
    {
        return constantsService;
    }

    public void setConstantsService(IConstantsService constantsService)
    {
        this.constantsService = constantsService;
    }

    public void setCheckDAO(CheckDAO checkDAO)
    {
        this.checkDAO = checkDAO;
    }

    public void setPishnehadService(IPishnehadService pishnehadService)
    {
        this.pishnehadService = pishnehadService;
    }

    public void setSequenceService(ISequenceService sequenceService)
    {
        this.sequenceService = sequenceService;
    }

    public void setAsnadeSodorDAO(AsnadeSodorDAO asnadeSodorDAO)
    {
        this.asnadeSodorDAO = asnadeSodorDAO;
    }

    private boolean userHasRole(User user, String roleName)
    {
        boolean result = false;
        if (user != null)
        {
            Set<Role> roles = user.getRoles();
            for (Role role : roles)
            {
                if (role.getRoleName().equalsIgnoreCase(roleName))
                {
                    result = true;
                }
            }
        }
        return result;
    }

    public List<Credebit> findCredebit(String identifier, Credebit.CredebitType type)
    {
        return asnadeSodorDAO.findCredebit(identifier, type);
    }

    public List<Credebit> findCredebitbyStatusACHPayment(Credebit.ACHStatus status)
    {
        return asnadeSodorDAO.findCredebitbyStatusACHPayment(status);
    }

    public void refreshObject(Object object)
    {
        asnadeSodorDAO.refreshObject(object);
    }

    public PaginatedListImpl<GhestVam> aghsatVamReport(PaginatedListImpl<GhestVam> pgList, GhestVam gvSearch)
    {
        return asnadeSodorDAO.getAghsatVamReport(pgList, gvSearch);
    }

    public List<GhestBandi> listInvalidGhestBandiVams()
    {
        return asnadeSodorDAO.listInvalidGhestBandiVams();
    }


    public List<Credebit> findCredebitsByIdentifier(String shomarePishnehad)
    {
        return asnadeSodorDAO.findCredebitsByIdentifier(shomarePishnehad);
    }

    public List<CredebitBimenameVM> findCredebitsByIdentifier(String shomareBimenameFrom, String shomareBimenameTo, User user)
    {
        return asnadeSodorDAO.findCredebitsByIdentifier(shomareBimenameFrom, shomareBimenameTo, user);
    }

    public boolean updateCredebitByIdentifier(String shomarePishnehad, String shomareBimename)
    {
        List<Credebit> credebitList = asnadeSodorDAO.findCredebitsByIdentifier(shomarePishnehad);
        if (credebitList == null || credebitList.size() == 0) return false;

        for (Credebit c : credebitList)
        {
            c.setIdentifier(shomareBimename);
        }
        asnadeSodorDAO.saveOrUpdateAll(credebitList);
        return true;//return tedad ham mitonim bedim;
    }

    public List<Credebit> credebitsShenaseDarVosulNashode(Long namayandeId, Credebit.VaziyatVosoul vosolState)
    {
        return asnadeSodorDAO.credebitsShenaseDarVosulNashode(namayandeId, vosolState);
    }

    public void saveCredebit(Credebit credebit)
    {
        asnadeSodorDAO.saveCredebit(credebit);
    }

    public void saveCredebitObj(Credebit credebit)
    {
        asnadeSodorDAO.saveCredebitObj(credebit);
    }

    public void saveBankInfo(BankInfo bankInfo)
    {
        asnadeSodorDAO.saveBankInfo(bankInfo);
    }

    public void saveVagozari(Vagozari vagozari)
    {
        asnadeSodorDAO.saveVagozari(vagozari);
    }

    public void editSanad(Sanad sanad)
    {
        asnadeSodorDAO.saveOrUpdateSanad(sanad);
    }

    public PaginatedListImpl<Sanad> findAllSanadsPaginated()
    {
        return asnadeSodorDAO.findAllSanadsPaginated();
    }

    public PaginatedListImpl<KhateSanad> findAllKhateSanadsBySanadIdPaginated(Integer sanadId)
    {
        return asnadeSodorDAO.findAllKhateSanadsBySanadIdPaginated(sanadId);
    }

    public PaginatedListImpl<BankInfo> findAllBankInfosByCredebitId(Integer credebitId)
    {
        return asnadeSodorDAO.findAllBankInfosByCredebitId(credebitId);
    }

//    public PaginatedListImpl<KhateSanad> findAllKhateSanads(String shomareSanad, Sanad.NoeSanad noeSanad, Sanad.Vaziat vaziat, String createdDateAz, String createdDateTa, String amountSanad, Credebit.CredebitType bedehiType, Credebit.CredebitType etebarType, String shomareMoshtariEtebar, String shenaseEtebar, String shomareMoshtariBedehi, String shenaseBedehi, String amountEtebar, String amountBedehi, String shoBimenameBedehi, String shoBImenameEtebar)
//    {
//        return asnadeSodorDAO.findAllKhateSanads(shomareSanad, noeSanad, vaziat, createdDateAz, createdDateTa, amountSanad, bedehiType, etebarType, shomareMoshtariEtebar, shenaseEtebar, shomareMoshtariBedehi, shenaseBedehi, amountEtebar, amountBedehi, shoBimenameBedehi, shoBImenameEtebar);
//    }

    public PaginatedListImpl<ViewKhateSanad> findAllKhateSanads(User user, int page,String searchShode, String shomareSanad, Sanad.NoeSanad noeSanad, Sanad.Vaziat vaziat, String createdDateAz, String createdDateTa, String amountSanad, Credebit.CredebitType bedehiType, Credebit.CredebitType etebarType, String shomareMoshtariEtebar, String shenaseEtebar, String shomareMoshtariBedehi, String shenaseBedehi, String amountEtebar, String amountBedehi, String shoBimenameBedehi, String shoBimenameEtebar, String shomareSanadBank, Long namayandeId, int subSystemName, String shomareCheck, Long bazaryabSanamId, String shomareFish , String System)
    {
        return asnadeSodorDAO.findAllKhateSanads(page, searchShode, shomareSanad, noeSanad, vaziat, createdDateAz, createdDateTa, amountSanad, bedehiType, etebarType, shomareMoshtariEtebar, shenaseEtebar, shomareMoshtariBedehi, shenaseBedehi, amountEtebar, amountBedehi, shoBimenameBedehi, shoBimenameEtebar, shomareSanadBank, namayandeId, user, subSystemName, shomareCheck, bazaryabSanamId, shomareFish ,System);
    }

    public void saveAghsat(List<Ghest> ghestList)
    {
        asnadeSodorDAO.saveOrUpdateAllBedehi(ghestList);
    }

    public Sanad sabteSanad(User user, Credebit bedehiCredebit, Credebit etebarCredebit, Sanad.NoeSanad noeSanad, Sanad.Vaziat vaziat, boolean isAutomatic)
    {
        List<Credebit> dummyBedehi = new ArrayList<Credebit>();
        List<Credebit> dummyEtebar = new ArrayList<Credebit>();
        dummyBedehi.add(bedehiCredebit);
        dummyEtebar.add(etebarCredebit);
        return sabteSanad(dummyBedehi, dummyEtebar, noeSanad, vaziat, user, isAutomatic);
    }

    private List<Credebit> validationSabteSanad_validBedehi(List<Credebit> bedehiCredebitList, User user,boolean isAutomatic)
    {
        if (bedehiCredebitList != null && bedehiCredebitList.size() > 0)
        {
            List<Credebit> bedehiCredebitSet = new ArrayList<Credebit>();
            for (Credebit credebit : bedehiCredebitList)
            {
                credebit = asnadeSodorDAO.findCredebitById(credebit.getId());
                boolean valid = false;
                for (Credebit.CredebitType type : Credebit.bedehiTypes)
                {
                    if (credebit.getCredebitType().equals(type))
                    {
                        valid = true;
                    }
                }

                if (credebit.getRemainingAmount_long() <= 0 || credebit.getAmount_long() <= 0)
                    valid = false;

                if(credebit.getRemainingAmount_long()>credebit.getAmount_long()){
                    valid = false;
                }
//                if(credebit.getKhateSanadsBedehi()!= null && credebit.getAmount_long() == credebit.getRemainingAmount_long())
//                    valid = false;

                if (!valid)
                {
                  return null;
                }


                if(!OmrUtil.userHasRole(user, "ROLE_ADMIN") && !isAutomatic) {
                    PaginatedListImpl<Credebit> bedehiList = validationSabteSanadBedehiCredebitId(credebit.getId(), user);
                    if (user != null && (bedehiList == null || bedehiList.getFullListSize() == 0)) {
                        System.out.println("here is returning null");
                        return null;
                    }
                }

                bedehiCredebitSet.add(credebit);
            }
            return bedehiCredebitSet;
        }
        else
        {
            return null; //emkane sabte in sanad dar sistem vojood nadarad
        }
    }

    private List<Credebit> validationSabteSanad_validEtebar(List<Credebit> etebarCredebitList, User user)
    {
        List<Credebit> etebarCredebitSet = new ArrayList<Credebit>();
        for (Credebit credebit : etebarCredebitList)
        {
            credebit = asnadeSodorDAO.findCredebitById(credebit.getId());
            boolean valid = false;
            for (Credebit.CredebitType type : Credebit.etebarTypes)
            {
                if (credebit.getCredebitType().equals(type))
                {
                    valid = true;
                }
            }
            if (credebit.getRemainingAmount_long() <= 0 || credebit.getAmount_long() <= 0) valid = false;
            if (!valid)
            {
                return null;
            }


            PaginatedListImpl<Credebit> etebarList = validationSabteSanadEtebarCredebitId(credebit.getId(), user);
            if (user != null && (etebarList == null || etebarList.getFullListSize() == 0))
            {
                return null;
            }


            etebarCredebitSet.add(credebit);
        }
        return etebarCredebitSet;
    }

    private boolean validationSabteSanad_checkRcptNameBimeBargashti(List<Credebit> bedehiCredebitList, List<Credebit> etebarCredebitList)
    {
        List<Credebit> bedehiCredebitSet1 = new ArrayList<Credebit>();
        List<Credebit> etebarCredebitSet1 = new ArrayList<Credebit>();
        for (Credebit bedehi : bedehiCredebitList)
        {
            Credebit temp = new Credebit();
            temp.setRemainingAmount(bedehi.getRemainingAmount());
            temp.setCredebitType(bedehi.getCredebitType());
            temp.setRcptName(bedehi.getRcptName());
            bedehiCredebitSet1.add(temp);
        }

        for (Credebit etebar : etebarCredebitList)
        {
            Credebit temp = new Credebit();
            temp.setRemainingAmount(etebar.getRemainingAmount());
            temp.setCredebitType(etebar.getCredebitType());
            temp.setRcptName(etebar.getRcptName());
            etebarCredebitSet1.add(temp);
        }
        Iterator<Credebit> bedehiCredebitIterator = bedehiCredebitSet1.iterator();
        Iterator<Credebit> etebarCredebitIterator = etebarCredebitSet1.iterator();
        Credebit etebarCredebit = null;
        Credebit bedehiCredebit = null;
        if (bedehiCredebitIterator.hasNext()) bedehiCredebit = bedehiCredebitIterator.next();
        if (etebarCredebitIterator.hasNext()) etebarCredebit = etebarCredebitIterator.next();
        if (bedehiCredebit != null && etebarCredebit != null)
        {
            while (true)
            {
                double bedehi = Double.parseDouble(bedehiCredebit.getRemainingAmount().replace(",", "").trim());
                double etebar = Double.parseDouble(etebarCredebit.getRemainingAmount().replace(",", "").trim());
                if (etebar > bedehi)
                {
                    if (etebarCredebit.getCredebitType().equals(Credebit.CredebitType.VEHICLE_HAGHBIME_BARGASHTI))
                    {
                        if (!(bedehiCredebit.getCredebitType().equals(Credebit.CredebitType.PARDAKHTE_TANKHAH) || bedehiCredebit.getCredebitType().equals(Credebit.CredebitType.HESAB_FI_MA_BEYN_BEDEHI)) && !etebarCredebit.getRcptName().equals(bedehiCredebit.getRcptName()))
                        {
                            return false;
                        }
//                        if (etebarCredebit.getRcptName().equals(bedehiCredebit.getRcptName()) && !etebarCredebit.getAmount_long().equals(bedehiCredebit.getAmount_long())){
//                            return false;
//                        }
                    }

                    bedehiCredebit.setRemainingAmount("0");
                    etebarCredebit.setRemainingAmount(String.valueOf((int) (etebar - bedehi)));
                    if (bedehiCredebitIterator.hasNext())
                    {
                        bedehiCredebit = bedehiCredebitIterator.next();
                    }
                    else
                    {
                        break;
                    }
                }
                else if (etebar < bedehi)
                {
                    if (etebarCredebit.getCredebitType().equals(Credebit.CredebitType.VEHICLE_HAGHBIME_BARGASHTI))
                    {
                        if (!(bedehiCredebit.getCredebitType().equals(Credebit.CredebitType.PARDAKHTE_TANKHAH) || bedehiCredebit.getCredebitType().equals(Credebit.CredebitType.HESAB_FI_MA_BEYN_BEDEHI)) && !etebarCredebit.getRcptName().equals(bedehiCredebit.getRcptName()))
                        {
                            return false;
                        }
//                        if (etebarCredebit.getRcptName().equals(bedehiCredebit.getRcptName()) && !etebarCredebit.getAmount_long().equals(bedehiCredebit.getAmount_long())){
//                            return false;
//                        }
                    }
                    bedehiCredebit.setRemainingAmount(String.valueOf((int) (bedehi - etebar)));
                    etebarCredebit.setRemainingAmount("0");
                    if (etebarCredebitIterator.hasNext())
                    {
                        etebarCredebit = etebarCredebitIterator.next();
                    }
                    else
                    {
                        break;
                    }
                }
                else
                {
                    if (etebarCredebit.getCredebitType().equals(Credebit.CredebitType.VEHICLE_HAGHBIME_BARGASHTI))
                    {
                        if (!(bedehiCredebit.getCredebitType().equals(Credebit.CredebitType.PARDAKHTE_TANKHAH)|| bedehiCredebit.getCredebitType().equals(Credebit.CredebitType.HESAB_FI_MA_BEYN_BEDEHI)) && !etebarCredebit.getRcptName().equals(bedehiCredebit.getRcptName()))
                        {
                            return false;
                        }
//                        if (etebarCredebit.getRcptName().equals(bedehiCredebit.getRcptName()) && !etebarCredebit.getAmount_long().equals(bedehiCredebit.getAmount_long())){
//                            return false;
//                        }
                    }
                    bedehiCredebit.setRemainingAmount("0");
                    etebarCredebit.setRemainingAmount("0");
                    if (bedehiCredebitIterator.hasNext() && etebarCredebitIterator.hasNext())
                    {
                        bedehiCredebit = bedehiCredebitIterator.next();
                        etebarCredebit = etebarCredebitIterator.next();
                    }
                    else
                    {
                        break;
                    }
                }
            }

            while (true)
            {
                double bedehi = Double.parseDouble(bedehiCredebit.getRemainingAmount().replace(",", "").trim());
                double etebar = Double.parseDouble(etebarCredebit.getRemainingAmount().replace(",", "").trim());
                if (etebar > bedehi)
                {
                    if (etebarCredebit.getCredebitType().equals(Credebit.CredebitType.ELHAGHIE_BARGASHTI))
                    {
                        if (!(bedehiCredebit.getCredebitType().equals(Credebit.CredebitType.PARDAKHTE_TANKHAH)|| bedehiCredebit.getCredebitType().equals(Credebit.CredebitType.HESAB_FI_MA_BEYN_BEDEHI)) && !etebarCredebit.getRcptName().equals(bedehiCredebit.getRcptName()))
                        {
                            return false;
                        }
                    }

                    bedehiCredebit.setRemainingAmount("0");
                    etebarCredebit.setRemainingAmount(String.valueOf((int) (etebar - bedehi)));
                    if (bedehiCredebitIterator.hasNext())
                    {
                        bedehiCredebit = bedehiCredebitIterator.next();
                    }
                    else
                    {
                        break;
                    }
                }
                else if (etebar < bedehi)
                {
                    if (etebarCredebit.getCredebitType().equals(Credebit.CredebitType.ELHAGHIE_BARGASHTI))
                    {
                        if (!(bedehiCredebit.getCredebitType().equals(Credebit.CredebitType.PARDAKHTE_TANKHAH)|| bedehiCredebit.getCredebitType().equals(Credebit.CredebitType.HESAB_FI_MA_BEYN_BEDEHI)) && !etebarCredebit.getRcptName().equals(bedehiCredebit.getRcptName()))
                        {
                            return false;
                        }
                    }
                    bedehiCredebit.setRemainingAmount(String.valueOf((int) (bedehi - etebar)));
                    etebarCredebit.setRemainingAmount("0");
                    if (etebarCredebitIterator.hasNext())
                    {
                        etebarCredebit = etebarCredebitIterator.next();
                    }
                    else
                    {
                        break;
                    }
                }
                else
                {
                    if (etebarCredebit.getCredebitType().equals(Credebit.CredebitType.ELHAGHIE_BARGASHTI))
                    {
                        if (!(bedehiCredebit.getCredebitType().equals(Credebit.CredebitType.PARDAKHTE_TANKHAH)|| bedehiCredebit.getCredebitType().equals(Credebit.CredebitType.HESAB_FI_MA_BEYN_BEDEHI)) && !etebarCredebit.getRcptName().equals(bedehiCredebit.getRcptName()))
                        {
                            return false;
                        }
                    }
                    bedehiCredebit.setRemainingAmount("0");
                    etebarCredebit.setRemainingAmount("0");
                    if (bedehiCredebitIterator.hasNext() && etebarCredebitIterator.hasNext())
                    {
                        bedehiCredebit = bedehiCredebitIterator.next();
                        etebarCredebit = etebarCredebitIterator.next();
                    }
                    else
                    {
                        break;
                    }
                }
            }
        }
        return true;
    }

    @Transactional
    public Sanad sabteSanad(List<Credebit> bedehiCredebitList, List<Credebit> etebarCredebitList, Sanad.NoeSanad noeSanad, Sanad.Vaziat vaziat, User user, boolean isAutomatic)
    {
        // todo: code cleanup
        //validation
        Sanad errorSanad = new Sanad();
        //exist in valid bedehi list

        User tempUser = user;
        if (isAutomatic)
        {
//            tempUser = null;
        }
        List<Credebit> bedehiCredebitSet = new ArrayList<Credebit>();
        bedehiCredebitSet = validationSabteSanad_validBedehi(bedehiCredebitList, tempUser,isAutomatic);

        if (bedehiCredebitSet == null || bedehiCredebitSet.size() <= 0) //agar bedehi valid nabbod
        {
            errorSanad.setId(-1); //emkane sabte in sanad dar sistem vojood nadarad
            return errorSanad;
        }

        //exist in valid etebar list
        List<Credebit> etebarCredebitSet = new ArrayList<Credebit>();
        etebarCredebitSet = validationSabteSanad_validEtebar(etebarCredebitList, tempUser);
        if (etebarCredebitSet == null) ////agar etebar valid nabbod
        {
            errorSanad.setId(-1); //emkane sabte in sanad dar sistem vojood nadarad
            return errorSanad;
        }


        // bedehi.sarResid + 5 => etebar.createdDate , etebar.type == daryafteCheck
        boolean valid = true;
        for (Credebit etebarCredebit : etebarCredebitList) {
            etebarCredebit = findCretebitById(etebarCredebit.getId());
            if (etebarCredebit.getCredebitType().equals(Credebit.CredebitType.DARYAFTE_CHECK) && user.getDaftar().getId() == 1) {
                for (Credebit bedehiCredebit : bedehiCredebitList) {
                    bedehiCredebit = findCretebitById(bedehiCredebit.getId());
                    int mohlatSarresid = 3;
                    if (bedehiCredebit.getMohlatSarresid() != null && bedehiCredebit.getMohlatSarresid() > 3) {
                        mohlatSarresid = bedehiCredebit.getMohlatSarresid();
                    }
                   // int diff = Math.abs(DateUtil.getTimeDifferenceByDayCount(DateUtil.addDaysWithTatilat(bedehiCredebit.getSarresidDate(), mohlatSarresid), etebarCredebit.getSarresidDate()));
                    int diff = 0;
                    if(etebarCredebit.getCredebitType().equals(Credebit.CredebitType.DARYAFTE_CHECK)){
                        diff = DateUtil.getTimeDifferenceByDayCount(etebarCredebit.getSarresidDate(), bedehiCredebit.getSarresidDate());
                    }
                    else {
                        diff = Math.abs(DateUtil.getTimeDifferenceByDayCount(DateUtil.addDaysWithTatilat(bedehiCredebit.getSarresidDate(), mohlatSarresid), etebarCredebit.getSarresidDate()));
                    }
                    if (bedehiCredebit.getSarresidDate() != null && (diff > 3 && user.getDaftar().getId() == 1)) {
                        valid = false;
                        break;
                    }
                }
            }
        }
        if (!valid)
        {
            errorSanad.setId(-2);
            return errorSanad;
        }

        valid = validationSabteSanad_checkRcptNameBimeBargashti(bedehiCredebitSet, etebarCredebitSet);
        if (!valid)
        {
            errorSanad.setId(-3);
            return errorSanad;
        }

        for (Credebit bedehi : bedehiCredebitList)
        {
            bedehi = findCretebitById(bedehi.getId());
            if (bedehi.getIdentifier() != null && (bedehi.getIdentifier().startsWith("1119") || bedehi.getIdentifier().startsWith("1129")))
            {
                valid = false;
            }
        }

        if (!valid)
        {
            errorSanad.setId(-4);
            return errorSanad;
        }

        if (!isAutomatic)
        {
            if (etebarCredebitList != null)
            {
                if (etebarCredebitList.size() == 1)
                {
                    Credebit etebar = asnadeSodorDAO.findCredebitById(etebarCredebitList.get(0).getId());
                    if (etebar.getCredebitType().equals(Credebit.CredebitType.HESAB_FI_MA_BEYN))
                    {
                        for (Credebit bedehiCredebit : bedehiCredebitList)
                        {
                            Credebit bedehi = asnadeSodorDAO.findCredebitById(bedehiCredebit.getId());
                            if (bedehi.getNamayande() == null || (bedehi.getNamayande() != null && !(bedehi.getNamayande().getKodeNamayandeKargozar().equals("410510")
                                    || bedehi.getNamayande().getKodeNamayandeKargozar().equals("111125")
                                    || bedehi.getNamayande().getKodeNamayandeKargozar().equals("610620"))))
                            {
                                valid = false;
                            }
                        }
                    }
                }
            }

            if (!valid)
            {
                errorSanad.setId(-5);
                return errorSanad;
            }
        }


        if (!isAutomatic)
        {
            Long namayandeTemp = 0l;
            if (bedehiCredebitList != null)
            {
                for (Credebit bedehiCredebit : bedehiCredebitList)
                {
                    Credebit bedehi = asnadeSodorDAO.findCredebitById(bedehiCredebit.getId());
                    if (namayandeTemp == 0)
                    {
                        if (bedehi.getNamayande() != null)
                        {
                            namayandeTemp = bedehi.getNamayande().getId();
                        }

                        else if (bedehi.getNamayande() != null && namayandeTemp != bedehi.getNamayande().getId())
                        {
                            valid = false;
                        }
                    }
                }
            }
        }

        if (!valid)
        {
            errorSanad.setId(-6);
            return errorSanad;
        }

        if (!isAutomatic)
        {
            if (bedehiCredebitList != null)
            {
                for (Credebit bedehiCredebit : bedehiCredebitList)
                {
                    Credebit bedehi = asnadeSodorDAO.findCredebitById(bedehiCredebit.getId());
                    if (bedehi != null && bedehi.getCredebitType().equals(Credebit.CredebitType.PARDAKHTE_TANKHAH) && etebarCredebitList != null)
                    {
                        for (Credebit etebarCredebit : etebarCredebitList)
                        {
                            Credebit etebar = asnadeSodorDAO.findCredebitById(etebarCredebit.getId());
                            if (etebar != null && etebar.getCredebitType().equals(Credebit.CredebitType.PARDAKHT_SHENASEDAR))
                            {
                                valid = false;
                            }
                        }
                    }
                }
            }
        }

        if (!valid)
        {
            errorSanad.setId(-7);
            return errorSanad;
        }

        if (!isAutomatic)
        {
            if (etebarCredebitList != null)
            {
                for (Credebit etebar : etebarCredebitList)
                {
                    Credebit etebarCredebit = asnadeSodorDAO.findCredebitById(etebar.getId());
                    if (etebarCredebit.getCredebitType().equals(Credebit.CredebitType.PARDAKHT_SHENASEDAR))
                    {
                        if (etebarCredebit.getShomareMoshtari() == null || etebarCredebit.getShomareMoshtari().length() < 15)
                        {
                            valid = false;
                        }
                    }
                }
            }
        }

        if (!valid)
        {
            errorSanad.setId(-8);
            return errorSanad;
        }

        for (Credebit etebarCredebit : etebarCredebitList) {
            etebarCredebit = findCretebitById(etebarCredebit.getId());
            if (etebarCredebit.getCredebitType().equals(Credebit.CredebitType.DARYAFTE_CHECK) && user.getDaftar().getId()==1) {
                for (Credebit bedehiCredebit : bedehiCredebitList) {
                    bedehiCredebit = findCretebitById(bedehiCredebit.getId());
                    int mohlatSarresid = 0;
                    if (bedehiCredebit.getMohlatSarresid() != null && bedehiCredebit.getMohlatSarresid() > 0) {
                        mohlatSarresid = bedehiCredebit.getMohlatSarresid();
                    }
                    if (bedehiCredebit.getSarresidDate() != null && DateUtil.isGreaterThanOrEqual(DateUtil.getCurrentDate(), DateUtil.addDaysWithTatilat(bedehiCredebit.getSarresidDate(), mohlatSarresid))) {
                        // bedehi sarresid shode
                        int diff = Math.abs(DateUtil.getTimeDifferenceByDayCount(DateUtil.getCurrentDate(), etebarCredebit.getSarresidDate()));
                        if(diff > 10 && user.getDaftar().getId() == 1) {
                            valid = false;
                            break;
                        }
                    }
                }
            }
        }
        if (!valid) {
            errorSanad.setId(-9);
            return errorSanad;
        }
        //------------------- bedehiha baraye yek bazaryab---------------------------------------------------------//
        if (!isAutomatic)
        {
            Long frstBedebiidNum = 0l;
            Long bedehiNum = 0l;
            if (bedehiCredebitList  != null)
            {
                Credebit  firstbedehi = asnadeSodorDAO.findCredebitById(bedehiCredebitList.get(0).getId());
                if (frstBedebiidNum == 0)
                {
                    if (firstbedehi.getBazarYabSanam() != null)
                    {
                        frstBedebiidNum = firstbedehi.getBazarYabSanam().getCode();
                    }
                }

                for (int i = 1 ; i < bedehiCredebitList.size(); i++)  //baraye hameye bedehihaye mojood dar list khate sanade ijad shode
                {
                 Credebit bedehi = asnadeSodorDAO.findCredebitById(bedehiCredebitList.get(i).getId());
                    if(bedehi!= null)
                        System.out.println("bedehi is not null");
                    System.out.println("round "+i);
                    System.out.println(bedehi.getId());
                    System.out.println(bedehiNum);
                    if (bedehiNum == 0)
                    {
                        if (bedehi.getBazarYabSanam() != null)
                        {
                            bedehiNum =  bedehi.getBazarYabSanam().getCode();
                        }
                    }
                        if ( bedehiNum != frstBedebiidNum )  //agar bazaryabe yeki az bedehiha ba bazaryabe avalin bedehi barabar nabood
                        {
                            valid = false;
                            break;
                        }
                }
            }
        }

        if (!valid) //agar tamame bedehiha baraye yek bazaryab nemibashad
        {
            errorSanad.setId(-10);
            return errorSanad;
        }

      //-------------------------------------------------------------------------------------------------------------//

        Sanad sanad = new Sanad();
        Set<KhateSanad> khateSanadSet = new HashSet<KhateSanad>();

        //set vahede sodor sanad for web service call
        if (etebarCredebitSet != null && bedehiCredebitSet != null && etebarCredebitSet.size() == 1 && bedehiCredebitSet.size() == 1)
        {
            if (etebarCredebitSet.get(0).getCredebitType().equals(Credebit.CredebitType.ACH) || etebarCredebitSet.get(0).getCredebitType().equals(Credebit.CredebitType.CASH) ||
                    etebarCredebitSet.get(0).getCredebitType().equals(Credebit.CredebitType.AZ_MAHALLE_TABLIGHAT) || etebarCredebitSet.get(0).getCredebitType().equals(Credebit.CredebitType.HESAB_FI_MA_BEYN) ||
                    etebarCredebitSet.get(0).getCredebitType().equals(Credebit.CredebitType.AZ_MAHALLE_TABLIGHAT) || etebarCredebitSet.get(0).getCredebitType().equals(Credebit.CredebitType.HESAB_FI_MA_BEYN) ||
                    etebarCredebitSet.get(0).getCredebitType().equals(Credebit.CredebitType.DARYAFTE_FISH) || etebarCredebitSet.get(0).getCredebitType().equals(Credebit.CredebitType.POS) ||
                    etebarCredebitSet.get(0).getCredebitType().equals(Credebit.CredebitType.DARYAFTE_CHECK) || etebarCredebitSet.get(0).getCredebitType().equals(Credebit.CredebitType.DARYAFTE_ELECTRONICI) ||
                    etebarCredebitSet.get(0).getCredebitType().equals(Credebit.CredebitType.VEHICLE_DARYAFT_ELECTRONICI))
            {
                sanad.setVahedeSodor(etebarCredebitSet.get(0).getNamayande());
            }
            if (bedehiCredebitSet.get(0).getCredebitType().equals(Credebit.CredebitType.HESAB_FI_MA_BEYN_BEDEHI) || bedehiCredebitSet.get(0).getCredebitType().equals(Credebit.CredebitType.AZ_MAHALLE_TABLIGHAT_BEDEHI))
            {
                sanad.setVahedeSodor(etebarCredebitSet.get(0).getNamayande());
            }
        }


        Iterator<Credebit> etebarCredebitIterator = etebarCredebitSet.iterator();
        Iterator<Credebit> bedehiCredebitIterator = bedehiCredebitSet.iterator();
        Credebit etebarCredebit = null;
        Credebit bedehiCredebit = null;
        if (bedehiCredebitIterator.hasNext()) bedehiCredebit = bedehiCredebitIterator.next();
        if (etebarCredebitIterator.hasNext()) etebarCredebit = etebarCredebitIterator.next();
        if (bedehiCredebit != null && etebarCredebit != null && bedehiCredebit.getRemainingAmount_long() != 0 && etebarCredebit.getRemainingAmount_long() != 0)
        {
            while (true)
            {
                KhateSanad khateSanad = new KhateSanad();
                double bedehi = Double.parseDouble(bedehiCredebit.getRemainingAmount().replace(",", "").trim());
                double etebar = Double.parseDouble(etebarCredebit.getRemainingAmount().replace(",", "").trim());
                if (etebar > bedehi)
                {
                    khateSanad.setAmount(bedehiCredebit.getRemainingAmount());
                    bedehiCredebit.setRemainingAmount("0");
                    bedehiCredebit.setStatus(Credebit.Status.SANAD_KHORDE);
                    etebarCredebit.setRemainingAmount(String.valueOf((int) (etebar - bedehi)));
                    asnadeSodorDAO.updateCredebit(etebarCredebit);
                    asnadeSodorDAO.updateCredebit(bedehiCredebit);
                    khateSanad.setBedehiCredebit(bedehiCredebit);
                    khateSanad.setEtebarCredebit(etebarCredebit);
                    khateSanad.setSanad(sanad);
                    khateSanad.setEtebarRemaining(etebarCredebit.getRemainingAmount());
                    khateSanad.setBedehiRemaining(bedehiCredebit.getRemainingAmount());
                    khateSanadSet.add(khateSanad);
                    if (bedehiCredebitIterator.hasNext())
                    {
                        bedehiCredebit = bedehiCredebitIterator.next();
                    }
                    else
                    {
                        break;
                    }
                }
                else if (etebar < bedehi)
                {
                    khateSanad.setAmount(etebarCredebit.getRemainingAmount());
                    double remainingAmount= (bedehi - etebar);
                    bedehiCredebit.setRemainingAmount(String.valueOf((int) remainingAmount));
                    if(bedehiCredebit.getCredebitType().equals(Credebit.CredebitType.GHEST)|| bedehiCredebit.getCredebitType().equals(Credebit.CredebitType.GHEST_VAM))
                        bedehiCredebit.setShomareMoshtari(sequenceService.generateNextShomareMoshtari(bedehiCredebit.getPishnehad().getKarshenas().getMojtamaSodoor(),"9","120",(long)remainingAmount));
                    etebarCredebit.setRemainingAmount("0");
                    etebarCredebit.setStatus(Credebit.Status.SANAD_KHORDE);
                    asnadeSodorDAO.updateCredebit(etebarCredebit);
                    asnadeSodorDAO.updateCredebit(bedehiCredebit);
                    khateSanad.setBedehiCredebit(bedehiCredebit);
                    khateSanad.setEtebarCredebit(etebarCredebit);
                    khateSanad.setSanad(sanad);
                    khateSanad.setEtebarRemaining(etebarCredebit.getRemainingAmount());
                    khateSanad.setBedehiRemaining(bedehiCredebit.getRemainingAmount());
                    khateSanadSet.add(khateSanad);
                    if (etebarCredebitIterator.hasNext())
                    {
                        etebarCredebit = etebarCredebitIterator.next();
                    }
                    else
                    {
                        break;
                    }
                }
                else
                {
                    khateSanad.setAmount(etebarCredebit.getRemainingAmount());
                    bedehiCredebit.setRemainingAmount("0");
                    bedehiCredebit.setStatus(Credebit.Status.SANAD_KHORDE);
                    etebarCredebit.setRemainingAmount("0");
                    etebarCredebit.setStatus(Credebit.Status.SANAD_KHORDE);
                    asnadeSodorDAO.updateCredebit(etebarCredebit);
                    asnadeSodorDAO.updateCredebit(bedehiCredebit);
                    khateSanad.setBedehiCredebit(bedehiCredebit);
                    khateSanad.setEtebarCredebit(etebarCredebit);
                    khateSanad.setSanad(sanad);
                    khateSanad.setEtebarRemaining(etebarCredebit.getRemainingAmount());
                    khateSanad.setBedehiRemaining(bedehiCredebit.getRemainingAmount());
                    khateSanadSet.add(khateSanad);
                    if (bedehiCredebitIterator.hasNext() && etebarCredebitIterator.hasNext())
                    {
                        bedehiCredebit = bedehiCredebitIterator.next();
                        etebarCredebit = etebarCredebitIterator.next();
                    }
                    else
                    {
                        break;
                    }
                }
            }
        }

        sanad.setNoeSanad(noeSanad);
        sanad.setVaziat(vaziat);
        sanad.setCreatedDate(DateUtil.getCurrentDate());
        sanad.setCreatedTime(DateUtil.getCurrentTime());
        sanad.setShomare(sequenceService.nextShomareSanad());
        sanad.setKhateSanadSet(khateSanadSet);
        //B-H
        Integer daftar_id=user.getDaftar().getId();
//        Integer daftar_id=(Integer)ActionContext.getContext().getSession().get("daftar_id");
        Daftar daftar=findDaftarById(daftar_id);
        sanad.setDaftar(daftar);
        //// ta inja

        if (user != null)
        {
            sanad.setUser(user);
            if (userHasRole(user, "ROLE_NAMAYANDE") && userHasRole(user, "ROLE_KARBAR_MALI"))
            {
                if (user != null && user.getNamayandegi() != null)
                {
                    sanad.setNamayande(user.getNamayandegi());
                    sanad.setVahedeSodor(user.getNamayandegi());
                }
            }
        }


        asnadeSodorDAO.saveSanad(sanad);
        asnadeSodorDAO.saveAllKhateSanad(khateSanadSet);
        return sanad;

    }

    public void saveKhateSanad(KhateSanad khateSanad)
    {
        asnadeSodorDAO.saveKhateSanad(khateSanad);
    }

    @Transactional
    public List<Sanad> sabteSanadSemiAutomatic(User user, List<Credebit> bedehiCredebitList, List<Credebit> etebarCredebitList)
    {
        List<Sanad> resultingSanads = new ArrayList<Sanad>();
        Set<Credebit> bedehiCredebitSet = new HashSet<Credebit>();
        for (Credebit credebit : bedehiCredebitList)
        {
            credebit = asnadeSodorDAO.findCredebitById(credebit.getId());
            bedehiCredebitSet.add(credebit);
        }
        Set<Credebit> etebarCredebitSet = new HashSet<Credebit>();
        for (Credebit credebit : etebarCredebitList)
        {
            credebit = asnadeSodorDAO.findCredebitById(credebit.getId());
            etebarCredebitSet.add(credebit);
        }
        for (Credebit etebarCredebit : etebarCredebitSet)
        {
            for (Credebit bedehiCredebit : bedehiCredebitSet)
            {
                if (bedehiCredebit.getShomareMoshtari() != null && etebarCredebit.getShomareMoshtari() != null &&
                        bedehiCredebit.getShomareMoshtari().equalsIgnoreCase(etebarCredebit.getShomareMoshtari()) &&
                        bedehiCredebit.getRemainingAmount() != null && etebarCredebit.getRemainingAmount() != null &&
                        bedehiCredebit.getRemainingAmount_long() > 0 && etebarCredebit.getRemainingAmount_long() > 0)
                {
                    List<Credebit> bedehiList = new ArrayList<Credebit>();
                    List<Credebit> etebarList = new ArrayList<Credebit>();
                    bedehiList.add(bedehiCredebit);
                    etebarList.add(etebarCredebit);
                    Sanad sanad = sabteSanad(bedehiList, etebarList, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM, user, true);
                    resultingSanads.add(sanad);
                }
            }
        }
        asnadeSodorDAO.saveAllSanad(resultingSanads);
        return resultingSanads;
    }

    public PaginatedListImpl<Ghest> findAllBedehisByBimenameIdPaging(Integer id, PaginatedListImpl<Ghest> pgList)
    {
        return asnadeSodorDAO.findAllBedehisByBimenameIdPaging(id, pgList);
    }

    public List<KhateSanad> findAllKhateSanadByCredebitId(Integer id)
    {
        return asnadeSodorDAO.findAllKhateSanadByCredebitId(id);
    }

    public List<KhateSanad> findAllKhateSanadByEtebarCredebitId(Integer id)
    {
        return asnadeSodorDAO.findAllKhateSanadByEtebarCredebitId(id);
    }

    public List<Ghest> findAllGhests(Integer bimenameId)
    {
        return asnadeSodorDAO.findAllGhests(bimenameId);
    }

    public List<TaghsitReport> mohasebeyeAghsat(Pishnehad pishnehad, int saleBimei, String tarikhShorou, boolean daemha) throws BimeNaamehVaSarmayehGozari.CustomValidationException
    {
        Estelam estel = pishnehad.getEstelam();
        MohasebateTeory mohasebateTeory = new MohasebateTeory();
        List<TaghsitReport> taghsitReport = mohasebateTeory.taghsitReport(pishnehad, daemha, saleBimei);
        if (saleBimei >= taghsitReport.size()) return null;
        TaghsitReport tr = taghsitReport.get(saleBimei);
        MohasebateTeoriView mohasebateTeoriView = new MohasebateTeoriView();
        if (estel.getNahve_pardakht_hagh_bime().equalsIgnoreCase("mah"))
        {
            taghsitReport = mohasebateTeoriView.convertViewToCustomPeriod_saleBimei(tarikhShorou, tr, MohasebateTeoriView.PERIOD_COUNT_IN_MAHANEH, pishnehad);
        }
        else if (estel.getNahve_pardakht_hagh_bime().equalsIgnoreCase("3mah"))
        {
            taghsitReport = mohasebateTeoriView.convertViewToCustomPeriod_saleBimei(tarikhShorou, tr, MohasebateTeoriView.PERIOD_COUNT_IN_3MAHE, pishnehad);
        }
        else if (estel.getNahve_pardakht_hagh_bime().equalsIgnoreCase("6mah"))
        {
            taghsitReport = mohasebateTeoriView.convertViewToCustomPeriod_saleBimei(tarikhShorou, tr, MohasebateTeoriView.PERIOD_COUNT_IN_6MAHE, pishnehad);
        }
        else
        {
            taghsitReport = mohasebateTeoriView.convertViewToCustomPeriod_saleBimei(tarikhShorou, tr, MohasebateTeoriView.PERIOD_COUNT_IN_SAALANEH, pishnehad);
        }
        return taghsitReport;
    }

    //By Soleimani
    public List<TaghsitReport> namayesheAghsat(Pishnehad pishnehad, int saleBimei, String tarikhShorou)
    {
        GhestBandi ghestBandi = asnadeSodorDAO.findGhestBandi(pishnehad.getBimename().getId(), saleBimei);
        List<Ghest> ghestList = ghestBandi.getGhestList();
        ArrayList<TaghsitReport> taghsitReportList = new ArrayList<TaghsitReport>();
        for (Ghest ghest : ghestList)
        {
            TaghsitReport taghsitReport = new TaghsitReport();
            taghsitReport.setSaal((short) (saleBimei + 1));
            taghsitReport.setTarikh(ghest.getSarresidDate());
            taghsitReport.setKasrHazine(ghestBandi.getKasrHazine());

            taghsitReport.setKaarmozd(Double.parseDouble(ghest.getHazineKarmonz().replace(",", "")));
            if (ghest.getHazineBimegari() != null && !ghest.getHazineBimegari().equals(""))
            {
                taghsitReport.setHazineBimeGari(Double.parseDouble(ghest.getHazineBimegari().replace(",", "")));
            }
            if (ghest.getHazineEdari() != null && !ghest.getHazineEdari().equals(""))
            {
                taghsitReport.setHazineEdari(Double.parseDouble(ghest.getHazineEdari().replace(",", "")));
            }
            if (ghest.getHazineVosoul() != null && !ghest.getHazineVosoul().equals(""))
            {
                taghsitReport.setHazineVosul(Double.parseDouble(ghest.getHazineVosoul().replace(",", "")));
            }
            if (ghest.getHaghBimeFotYekja() != null && !ghest.getHaghBimeFotYekja().equals(""))
            {
                taghsitReport.setHaghBimeKhaalesFotYekja(Double.parseDouble(ghest.getHaghBimeFotYekja().replace(",", "")));
            }
            if (ghest.getSarmayeFotBeHarEllat() != null && !ghest.getSarmayeFotBeHarEllat().equals(""))
            {
                taghsitReport.setSarmaayeFotBehHarEllat(Double.parseDouble(ghest.getSarmayeFotBeHarEllat().replace(",", "")));
            }
            if (ghest.getSarmayeFotDarAsarHadeseh() != null && !ghest.getSarmayeFotDarAsarHadeseh().equals(""))
            {
                taghsitReport.setSarmaayeFotDarAsarHaadeseh(Double.parseDouble(ghest.getSarmayeFotDarAsarHadeseh().replace(",", "")));
            }
            if (ghest.getSarmayePoosheshEzafiAmraazKhaas() != null && !ghest.getSarmayePoosheshEzafiAmraazKhaas().equals(""))
            {
                taghsitReport.setSarmaayePusheshEzaafiAmraazKhaas(Double.parseDouble(ghest.getSarmayePoosheshEzafiAmraazKhaas().replace(",", "")));
            }
            if (ghest.getHaghBimePoosheshhayeEzafi() != null && !ghest.getHaghBimePoosheshhayeEzafi().equals(""))
            {
                taghsitReport.setHaghBimePusheshHaayeEzaafi(Double.parseDouble(ghest.getHaghBimePoosheshhayeEzafi().replace(",", "")));
            }
            if (ghest.getHaghBimePoosheshAmraz() != null && !ghest.getHaghBimePoosheshAmraz().equals(""))
            {
                taghsitReport.setHaghBimePoosheshAmraz(Double.parseDouble(ghest.getHaghBimePoosheshAmraz().replace(",", "")));
            }
            if (ghest.getHaghBimePoosheshHadese() != null && !ghest.getHaghBimePoosheshHadese().equals(""))
            {
                taghsitReport.setHaghBimePoosheshHadese(Double.parseDouble(ghest.getHaghBimePoosheshHadese().replace(",", "")));
            }
            if (ghest.getHaghBimePoosheshNaghsOzv() != null && !ghest.getHaghBimePoosheshNaghsOzv().equals(""))
            {
                taghsitReport.setHaghBimePoosheshNaghsOzv(Double.parseDouble(ghest.getHaghBimePoosheshNaghsOzv().replace(",", "")));
            }
            if (ghest.getHaghBimePoosheshMoafiat() != null && !ghest.getHaghBimePoosheshMoafiat().equals(""))
            {
                taghsitReport.setHaghBimePoosheshMoafiat(Double.parseDouble(ghest.getHaghBimePoosheshMoafiat().replace(",", "")));
            }
            if (ghest.getMaliat() != null && !ghest.getMaliat().equals(""))
            {
                taghsitReport.setMaliat(Double.parseDouble(ghest.getMaliat().replace(",", "")));
            }

            if (pishnehad.getBimename() == null)
            {
                if (pishnehad.getEstelam().getMablagh_seporde_ebtedaye_sal() != null && !pishnehad.getEstelam().getMablagh_seporde_ebtedaye_sal().equals(""))
                {
                    taghsitReport.setHaghBimeAvvalie(Double.parseDouble(pishnehad.getEstelam().getMablagh_seporde_ebtedaye_sal().replaceAll(",", "")));
                }
            }
            else
            {
                if (pishnehad.getBimename().getPishnehad().getEstelam().getMablagh_seporde_ebtedaye_sal() != null &&
                        !pishnehad.getBimename().getPishnehad().getEstelam().getMablagh_seporde_ebtedaye_sal().equals(""))
                {
                    taghsitReport.setHaghBimeAvvalie(Double.parseDouble(pishnehad.getBimename().getPishnehad().getEstelam().getMablagh_seporde_ebtedaye_sal().replace(",", "")));
                }
            }

            if (ghest.getCredebit().getAmount() != null && !ghest.getCredebit().getAmount().equals(""))
            {
                int amount = Integer.parseInt(ghest.getCredebit().getAmount().replace(",", ""));
//                if (pishnehad.getEstelam().getNahve_kasr_hagh_bime_poosheshhaye_ezafi().equals("az")) {
                taghsitReport.setHaghBimePardaakhtiSaal(OmrUtil.rondPardakhti(amount));
//                } else {
//                    taghsitReport.setHaghBimePardaakhtiSaal(OmrUtil.rondPardakhti(amount - (int)(taghsitReport.getMaliat()) - (int)((taghsitReport.getHaghBimePusheshHaayeEzaafi()))));
//                }
            }

            double haghBimePoosheshHaheEzafi = Math.round(taghsitReport.getHaghBimePusheshHaayeEzaafi());
            double haghBimePoosheshAmraz = Math.round(taghsitReport.getHaghBimePoosheshAmraz());
            double haghBimePoosheshHadese = Math.round(taghsitReport.getHaghBimePoosheshHadese());
            double haghBimePoosheshNaghseOzv = Math.round(taghsitReport.getHaghBimePoosheshNaghsOzv());
            double haghBimePoosheshMoafiat = Math.round(taghsitReport.getHaghBimePoosheshMoafiat());

            taghsitReport.setPoosheshEzafiDetailsCorrect(haghBimePoosheshHaheEzafi == haghBimePoosheshAmraz +
                    haghBimePoosheshHadese +
                    haghBimePoosheshNaghseOzv +
                    haghBimePoosheshMoafiat);


            taghsitReportList.add(taghsitReport);
        }
        return taghsitReportList;
    }

    /*haghbimekhalesfotyekja
    karmozd
    hazineBimeGari
    hazineEdari
    hazineVosoul
    zaribtabdilnahvepardakhthaghbime
    poosheshekhatarzelzele
    haghbimefotdarasarhadese
    gheramatazkaroftadegivanaghseozv
    majmuehaghebimeyepeyvandvaamraz
    haghbimeazkaroftadegi*/
    public static boolean[] getMaliatParam(String _date, Tarh tarh)
    {
        return getParam(_date, Constants.Keys.MALIATBARARZESHARZUDE, Constants.KeysParam.PARAMDARMALIAT, tarh);
    }

    /*haghbimekhalesfotyekja
    karmozd
    hazineBimeGari
    hazineEdari
    zaribtabdilnahvepardakhthaghbime
    poosheshekhatarzelzele
    haghbimefotdarasarhadese
    gheramatazkaroftadegivanaghseozv
    majmuehaghebimeyepeyvandvaamraz
    */
    public static boolean[] getMoafiatParam(String _date, Tarh tarh)
    {
        return getParam(_date, Constants.Keys.NERKHEHAGHEBIMEPUSHESHMOAFIAT, Constants.KeysParam.PARAMDARMOHASEBEHAGHBIME, tarh);
    }

    public static boolean[] getParam(String date, Constants.Keys key, Constants.KeysParam param, Tarh tarh)
    {
        String[] s = getConstantKey(date, new Constants(), key, param, tarh).split(",");
        boolean[] res = new boolean[s.length];
        for (int i = 0; i < s.length; i++)
        {
            res[i] = !s[i].trim().equals("0");
        }
        return res;
    }

    public static double[][] getLifeTable(String date, Tarh tarh)
    {
        double nerkhBahreFanni = AsnadeSodorService.getNerkhBahreFanni(null, tarh);
        String data = getConstantKey(date, new Constants(), Constants.Keys.JADVALMARGOMIR, Constants.KeysParam.TAMAMIMOALEFE, tarh);
        if (data == null || data.isEmpty()) return LifeTable.getLifeTable(nerkhBahreFanni);
        String[] row = data.split("\n");
        double[][] res = new double[row.length][];
        for (int i = 0; i < row.length; i++)
        {
            String[] col = row[i].split(",");
            res[i] = new double[col.length];
            for (int j = 0; j < col.length; j++)
            {
                try
                {
                    res[i][j] = Double.valueOf(col[j]);
                }
                catch (Exception ex)
                {
                    res[i][j] = 0.0;
                }
            }

        }
        return res;
    }

    public static double getMoafiatAzPardakht(String _date, Tarh tarh)
    {
        return Double.valueOf(getConstantKey(_date, new Constants(), Constants.Keys.NERKHEHAGHEBIMEPUSHESHMOAFIAT, Constants.KeysParam.MEGHDARNERKH, tarh).replace(",", "").trim());
    }

    public static double[] getDarsadKasriKarmozd(String date, Tarh tarh)
    {
        return getDarsadeKasri(date, Constants.Keys.HAZINEKARMOZD, tarh);
    }

    public static Constants.RaveshMohasebeKarmozd getRaveshMohasebeKarmozd(String _date, Tarh tarh)
    {
        String s = getConstantKey(_date, new Constants(), Constants.Keys.HAZINEKARMOZD, Constants.KeysParam.RaveshMohasebe, tarh).trim();
        if (s.toLowerCase().equals("fot"))
        {
            return Constants.RaveshMohasebeKarmozd.FOT;
        }
        else
        {
            return Constants.RaveshMohasebeKarmozd.FOT_AND_HAGHBIME;
        }
    }

    public static double[] getDarsadKasriBimeGari(String date, Tarh tarh)
    {
        return getDarsadeKasri(date, Constants.Keys.HAZINEBIMEGARI, tarh);
    }

    public static double[] getDarsadKasriVosul(String date, Tarh tarh)
    {
        return getDarsadeKasri(date, Constants.Keys.HAZINEVOSUL, tarh);
    }

    public static double[] getDarsadKasriEdari(String date, Tarh tarh)
    {
        return getDarsadeKasri(date, Constants.Keys.HAZINEEDARI, tarh);
    }

    public static double[] getDarsadeKasri(String date, Constants.Keys key, Tarh tarh)
    {
        String[] tmp = getConstantKey(date, new Constants(), key, Constants.KeysParam.DARSADGHABELKASR, tarh).split(",");
        double[] res = new double[tmp.length];
        for (int i = 0; i < tmp.length; i++)
        {
            res[i] = Double.valueOf(tmp[i]);
        }
        return res;
    }

    public static int getSalhayeEstehlakKarmozd(String date, Tarh tarh)
    {
        return Integer.valueOf(getConstantKey(date, new Constants(), Constants.Keys.HAZINEKARMOZD, Constants.KeysParam.TEDADSALESTEHLAK, tarh).replace(",", "").trim());
    }

    public static int getSalhayeEstehlakBimeGari(String date, Tarh tarh)
    {
        return Integer.valueOf(getConstantKey(date, new Constants(), Constants.Keys.HAZINEBIMEGARI, Constants.KeysParam.TEDADSALESTEHLAK, tarh).replace(",", "").trim());
    }

    public static int getSalhayeEstehlakVosul(String date, Tarh tarh)
    {
        return Integer.valueOf(getConstantKey(date, new Constants(), Constants.Keys.HAZINEVOSUL, Constants.KeysParam.TEDADSALESTEHLAK, tarh).replace(",", "").trim());
    }

    public static int getSalhayeEstehlakEdari(String date, Tarh tarh)
    {
        return Integer.valueOf(getConstantKey(date, new Constants(), Constants.Keys.HAZINEEDARI, Constants.KeysParam.TEDADSALESTEHLAK, tarh).replace(",", "").trim());
    }

    public static double getKarmozdYekja(String date, Tarh tarh)
    {
        return Double.valueOf(getConstantKey(date, new Constants(), Constants.Keys.HAZINEKARMOZD, Constants.KeysParam.MEGHDARHAZINE_YEKJA, tarh).replace(",", "").trim());
    }

    public static double getKarmozdGyekja(String date, Tarh tarh)
    {
        return Double.valueOf(getConstantKey(date, new Constants(), Constants.Keys.HAZINEKARMOZD, Constants.KeysParam.MEGHDARHAZINE_GYEKJA, tarh).replace(",", "").trim());
    }

    public static double getBimeGariYekja(String date, Tarh tarh)
    {
        return Double.valueOf(getConstantKey(date, new Constants(), Constants.Keys.HAZINEBIMEGARI, Constants.KeysParam.MEGHDARHAZINE_YEKJA, tarh).replace(",", "").trim());
    }

    public static double getBimeGariGyekja(String date, Tarh tarh)
    {
        return Double.valueOf(getConstantKey(date, new Constants(), Constants.Keys.HAZINEBIMEGARI, Constants.KeysParam.MEGHDARHAZINE_GYEKJA, tarh).replace(",", "").trim());
    }

    public static double getVosulYekja(String date, Tarh tarh)
    {
        return Double.valueOf(getConstantKey(date, new Constants(), Constants.Keys.HAZINEVOSUL, Constants.KeysParam.MEGHDARHAZINE_YEKJA, tarh).replace(",", "").trim());
    }

    public static double getVosulGyekja(String date, Tarh tarh)
    {
        return Double.valueOf(getConstantKey(date, new Constants(), Constants.Keys.HAZINEVOSUL, Constants.KeysParam.MEGHDARHAZINE_GYEKJA, tarh).replace(",", "").trim());
    }

    public static double getEdariYekja(String date, Tarh tarh)
    {
        return Double.valueOf(getConstantKey(date, new Constants(), Constants.Keys.HAZINEEDARI, Constants.KeysParam.MEGHDARHAZINE_YEKJA, tarh).replace(",", "").trim());
    }

    public static double getEdariGyekja(String date, Tarh tarh)
    {
        return Double.valueOf(getConstantKey(date, new Constants(), Constants.Keys.HAZINEEDARI, Constants.KeysParam.MEGHDARHAZINE_GYEKJA, tarh).replace(",", "").trim());
    }

    public static double getDarsadMaliat(String date, Tarh tarh)
    {
        try
        {
            return Double.valueOf(getConstantKey(date, new Constants(), Constants.Keys.MALIATBARARZESHARZUDE, Constants.KeysParam.MEGHDARNERKH, tarh).replace(",", "").trim());
        }
        catch (Exception ex)
        {
            System.out.println(ex.toString());
            return 0;
        }

    }

    public static double getNerkhPusheshKhatarZelzele(String _date, Tarh tarh)
    {
        return Double.valueOf(getConstantKey(_date, new Constants(), Constants.Keys.NERKHEPUSHESHFOTDARASAREZELZELE, Constants.KeysParam.MEGHDARNERKHKHATAR, tarh).replace(",", "").trim());
    }

    public static Constants.PayeyeMohasebeHazineEdari getPayeyeMohasebeHazineEdari(String _date, Tarh tarh)
    {
        String s = getConstantKey(_date, new Constants(), Constants.Keys.HAZINEEDARI, Constants.KeysParam.PayeyeMohasebeyeHazineEdari, tarh).trim();
        if (s.toLowerCase().equals("h"))
        {
            return Constants.PayeyeMohasebeHazineEdari.HagheBime;
        }
        else if (s.toLowerCase().equals("f"))
        {
            return Constants.PayeyeMohasebeHazineEdari.SarmayeFowt;
        }
        else
        {
            return Constants.PayeyeMohasebeHazineEdari.HagheBime;
        }
    }

    public static double getNerkhPusheshKhatarHadese(String _date, int tabagheKhatar, Tarh tarh)
    {
        return getNerkhPusheshKhatar(_date, tabagheKhatar, Constants.Keys.NERKHEPUSHESHEFOTDARASARHADESE, tarh);
    }

    public static double getNerkhPusheshKhatar(String date, int tabagheKhatar, Constants.Keys key, Tarh tarh)
    {
        switch (tabagheKhatar)
        {
            case 1:
                return Double.valueOf(getConstantKey(date, new Constants(), key, Constants.KeysParam.MEGHDARNERKHKHATARTABAGHE_1, tarh).replace(",", "").trim());
            case 2:
                return Double.valueOf(getConstantKey(date, new Constants(), key, Constants.KeysParam.MEGHDARNERKHKHATARTABAGHE_2, tarh).replace(",", "").trim());
            case 3:
                return Double.valueOf(getConstantKey(date, new Constants(), key, Constants.KeysParam.MEGHDARNERKHKHATARTABAGHE_3, tarh).replace(",", "").trim());
            case 4:
                return Double.valueOf(getConstantKey(date, new Constants(), key, Constants.KeysParam.MEGHDARNERKHKHATARTABAGHE_4, tarh).replace(",", "").trim());
            default:
                return Double.valueOf(getConstantKey(date, new Constants(), key, Constants.KeysParam.MEGHDARNERKHKHATARTABAGHE_5, tarh).replace(",", "").trim());

        }

    }

    public static long getHadeAksarSarmayeFotAmraz(String date, Tarh tarh)
    {
        return Long.valueOf(getConstantKey(date, new Constants(), Constants.Keys.SAGHFESARMAYEAMRAZ, Constants.KeysParam.MABLAGHSARMAYEAMRAZ, tarh).replace(",", "").trim());
    }

    public static long getHadeAksarSarmayeFotHadese(String date, Tarh tarh)
    {
        return Long.valueOf(getConstantKey(date, new Constants(), Constants.Keys.SAGHFESARMAYEFOTDARASARHADESE, Constants.KeysParam.MABLAGHSARMAYEFOTDARASARHADESE, tarh).replace(",", "").trim());
    }

    public static long getHadeAksarSarmayeFot(String date, Tarh tarh)
    {
        return Long.valueOf(getConstantKey(date, new Constants(), Constants.Keys.SAGHFESARMAYEFOTBEHARELLAT, Constants.KeysParam.MABLAGHSARMAYEFOT, tarh).replace(",", "").trim());
    }

    public static String getConstantKey(String date, Constants constant, Constants.Keys key, Constants.KeysParam param, Tarh tarh)
    {
        List<Constants> constants;
        try
        {
            constants = constantsService.findByNameConsiderEffect(key, param, DateUtil.getCurrentDate(), tarh);
        }
        catch (Exception ex)
        {
            constants = null;
        }
        String nerkhbahre = "";
        if (constants != null)
        {
            for (Constants constant1 : constants)
            {
                constant = constant1;
                if (constant != null)
                {
                    if (constant.getApplyStyle().equals(Constants.ConstantsApplyStyle.BOTH))
                    {
                        nerkhbahre = constant.getValue();
                        break;
                    }
                    else if (constant.getApplyStyle().equals(Constants.ConstantsApplyStyle.GHADIM))
                    {
                        if (DateUtil.isGreaterThan(constant.getDateEffect(), date))
                        {
                            nerkhbahre = constant.getValue();
                            break;
                        }
                    }
                    else if (constant.getApplyStyle().equals(Constants.ConstantsApplyStyle.JADID))
                    {
                        if (DateUtil.isGreaterThanOrEqual(date, constant.getDateEffect()))
                        {
                            nerkhbahre = constant.getValue();
                            break;
                        }
                    }
                }
            }
        }
        return nerkhbahre;
    }


    public List<Ghest> saveGhestbandi(Pishnehad pishnehad, int saleBimei, User creator) throws BimeNaamehVaSarmayehGozari.CustomValidationException
    {
        synchronized (AsnadeSodorService.class) {
            List<TaghsitReport> taghsitReport = mohasebeyeAghsat(pishnehad, saleBimei, pishnehad.getBimename().getTarikhShorou(), true);
            // moghe taghsite automatic ezafe taghsit nakonim
            boolean valid = true;
            if (creator == null) {
                valid = false;
                for (TaghsitReport tr : taghsitReport) {
                    if (DateUtil.isGreaterThanOrEqual(DateUtil.addDays(DateUtil.getCurrentDate(), 20), tr.getTarikh())) {
                        valid = true;
                    }
                }
            }
            if (valid) {
                return saveGhestbandi(pishnehad, saleBimei, taghsitReport, creator);
            } else {
                return null;
            }
        }
    }

//    public Long getMajmuAghsatSalChapp(Pishnehad pishnehad, String tarikhShorou, int saleBimei, boolean daemha,Long amountGhest)
//    {
//        if (pishnehad.getBimename() == null || pishnehad.getBimename().getId() == null || pishnehad.getBimename().getGhestBandiList().size() == 0)
//        {
//            Long amountG=amountGhest!=null?amountGhest:((Integer) getMablagheGhestTaghsitNashode(pishnehad, tarikhShorou, saleBimei, daemha)).longValue();
//            String nahve=pishnehad.getEstelam().getNahve_pardakht_hagh_bime();
//            int count=1;
//            if(nahve.equals("mah"))
//                count=12;
//            else if(nahve.equals("3mah"))
//                count=4;
//            else if(nahve.equals("6mah"))
//                count=2;
//            return amountG*count;
//        }
//        else
//        {
//            List<Ghest> ghestList = pishnehad.getBimename().getGhestBandiList().get(0).getGhestList();
//            String sepordeEbtedaSal = pishnehad.getEstelam().getMablagh_seporde_ebtedaye_sal();
//            return pishnehad.getBimename().getGhestBandiList().get(0).getMajmooeAmount() - Long.parseLong(sepordeEbtedaSal != null ? sepordeEbtedaSal.replaceAll(",", "").trim() : "0");
//        }
//    }

//    public Long getMablagheAghestChapp(Pishnehad pishnehad, String tarikhShorou, int saleBimei, boolean daemha)
//    {
//        if (pishnehad.getBimename() == null || pishnehad.getBimename().getId()==null || pishnehad.getBimename().getGhestBandiList().size() == 0)
//            return ((Integer)getMablagheGhestTaghsitNashode(pishnehad,tarikhShorou,saleBimei,daemha)).longValue();
//        else
//        {
//            List<Ghest> ghestList = pishnehad.getBimename().getGhestBandiList().get(0).getGhestList();
//            String sepordeEbtedaSal = pishnehad.getEstelam().getMablagh_seporde_ebtedaye_sal();
//            return ghestList.get(0).getCredebit().getAmount_long()-Long.parseLong(sepordeEbtedaSal!=null?sepordeEbtedaSal.replaceAll(",", "").trim():"0");
//        }
//    }

    public void getMabaleghForChapp(Pishnehad pishnehad, String tarikhShorou, int saleBimei, boolean daemha, PishnehadReport pr) throws BimeNaamehVaSarmayehGozari.CustomValidationException
    {
        Estelam estel = pishnehad.getEstelam();
        if (pishnehad.getBimename() == null || pishnehad.getBimename().getId() == null || pishnehad.getBimename().getGhestBandiList().size() == 0)
        {
            MohasebateTeory mohasebateTeory = new MohasebateTeory();
            List<TaghsitReport> taghsitReportList = mohasebateTeory.taghsitReport(pishnehad, daemha, saleBimei);
//            pr.setHaghBimeSal
//            (
//                NumberFormat.getInstance().format
//                (   (Double)
//                    taghsitReport.get(0).getHaghBimePardaakhtiSaal()
//                    - taghsitReport.get(0).getHaghBimeAvvalie()
//                    + taghsitReport.get(0).getMaliat()
//                    + taghsitReport.get(0).getHaghBimePusheshHaayeEzaafi()
//                )
//            );

            TaghsitReport tr = taghsitReportList.get(saleBimei);
            MohasebateTeoriView mohasebateTeoriView = new MohasebateTeoriView();
            int count = 1;
            if (estel.getNahve_pardakht_hagh_bime().equalsIgnoreCase("mah"))
            {
                count = MohasebateTeoriView.PERIOD_COUNT_IN_MAHANEH;
            }
            else if (estel.getNahve_pardakht_hagh_bime().equalsIgnoreCase("3mah"))
            {
                count = MohasebateTeoriView.PERIOD_COUNT_IN_3MAHE;
            }
            else if (estel.getNahve_pardakht_hagh_bime().equalsIgnoreCase("6mah"))
            {
                count = MohasebateTeoriView.PERIOD_COUNT_IN_6MAHE;
            }
//                else if (estel.getNahve_pardakht_hagh_bime().equalsIgnoreCase("sal"))
//                    count=MohasebateTeoriView.PERIOD_COUNT_IN_SAALANEH

            List<TaghsitReport> taghsitReport = mohasebateTeoriView.convertViewToCustomPeriod_saleBimei(tarikhShorou, tr, count, pishnehad);

            if (estel.getNahve_pardakht_hagh_bime().equalsIgnoreCase("yekja"))
            {
                if (estel.getNahve_kasr_hagh_bime_poosheshhaye_ezafi().equals("az"))
                {
                    pr.setHaghBimeSal("0");
                }
                else
                {
                    pr.setHaghBimeSal(NumberFormat.getInstance().format(OmrUtil.rondPardakhti(((Double) (taghsitReportList.get(0).getMaliat() + taghsitReportList.get(0).getHaghBimePusheshHaayeEzaafi())).intValue())));
                }
                pr.setMablaghGhest("0");
            }
            else
            {
                if (estel.getNahve_kasr_hagh_bime_poosheshhaye_ezafi().equals("az"))
                {
                    pr.setHaghBimeSal(NumberFormat.getInstance().format(Math.round((Double) (taghsitReportList.get(0).getHaghBimePardaakhtiSaal() - taghsitReportList.get(0).getHaghBimeAvvalie()))));
                }
                else
                {
                    pr.setHaghBimeSal
                            (
                                    NumberFormat.getInstance().format
                                            (OmrUtil.rondPardakhti
                                                    (
                                                            ((Double) (
                                                                    taghsitReportList.get(0).getHaghBimePardaakhtiSaal()
                                                                            - taghsitReportList.get(0).getHaghBimeAvvalie()
                                                                            + taghsitReportList.get(0).getMaliat()
                                                                            + taghsitReportList.get(0).getHaghBimePusheshHaayeEzaafi()
                                                            )
                                                            ).intValue()
                                                    )
                                            )
                            );
                }
                pr.setMablaghGhest(NumberFormat.getInstance().format((int) (taghsitReport.get(0).getHaghBimePardaakhtiSaal() - taghsitReport.get(0).getHaghBimeAvvalie())));
            }

        }
        else
        {
            List<Ghest> ghestList = pishnehad.getBimename().getGhestBandiList().get(0).getGhestList();
            String sepordeEbtedaSal = estel.getMablagh_seporde_ebtedaye_sal();
            if (estel.getNahve_pardakht_hagh_bime().equalsIgnoreCase("yekja") && !estel.getNahve_kasr_hagh_bime_poosheshhaye_ezafi().equals("az"))
            {
                pr.setMablaghGhest("0");
            }
            else
            {
                pr.setMablaghGhest(NumberFormat.getInstance().format(ghestList.get(0).getCredebit().getAmount_long() - Long.parseLong(sepordeEbtedaSal != null ? sepordeEbtedaSal.replaceAll(",", "").trim() : "0")));
            }
            pr.setHaghBimeSal(NumberFormat.getInstance().format(pishnehad.getBimename().getGhestBandiList().get(0).getMajmooeAmount() - Long.parseLong(sepordeEbtedaSal != null ? sepordeEbtedaSal.replaceAll(",", "").trim() : "0")));
        }
    }

    public int getMablagheGhestTaghsitNashode(Pishnehad pishnehad, String tarikhShorou, int saleBimei, boolean daemha) throws BimeNaamehVaSarmayehGozari.CustomValidationException
    {
        int ghestAmount;
        List<TaghsitReport> taghsitReport = mohasebeyeAghsat(pishnehad, saleBimei, tarikhShorou, daemha);
        if (pishnehad.getEstelam().getNahve_kasr_hagh_bime_poosheshhaye_ezafi().equals("az"))
        {
            ghestAmount = (int) taghsitReport.get(0).getHaghBimePardaakhtiSaal() - (int) taghsitReport.get(0).getHaghBimeAvvalie();
        }
        else
        {
            ghestAmount = (int) taghsitReport.get(0).getHaghBimePardaakhtiSaal() - (int) taghsitReport.get(0).getHaghBimeAvvalie();//+ (int) taghsitReport.get(0).getMaliat() + (int) taghsitReport.get(0).getHaghBimePusheshHaayeEzaafi();
        }
        return ghestAmount;
    }

    @Transactional
    public List<Ghest> saveGhestbandi(Pishnehad pishnehad, int saleBimei, List<TaghsitReport> taghsitReports, User creator)
    {
        // check ghestbandi nabashad
        pishnehad = pishnehadService.loadPishnehadById(pishnehad.getId());
        if (pishnehad.getBimename().getGhestBandiList() != null)
        {
            for (GhestBandi gb : pishnehad.getBimename().getGhestBandiList())
            {
                if (gb.getSaleBimeiInt() == saleBimei)
                {
                    return new ArrayList<Ghest>();
                }
            }
        }
        if (taghsitReports == null) return null;
        if (!(pishnehad.getBimename().getState().getId().equals(Constant.BIMENAME_INITIAL_STATE) || pishnehad.getBimename().getState().getId().equals(Constant.BIMENAME_LOCK_STATE)))
        {
            return null;
        }
        GhestBandi ghestBandi = new GhestBandi();
        ghestBandi.setBimename(pishnehad.getBimename());
        ghestBandi.setSaleBimei(String.valueOf(saleBimei));
        ghestBandi.setTarikheTaghsit(DateUtil.getCurrentDate());
        ghestBandi.setKasrHazine((int) Math.round(taghsitReports.get(0).getKasrHazine()));
        double sumAmraz = 0.0, sumHadese = 0.0, sumMoafiat = 0.0, sumNaghsOzv = 0.0, sumMaliat = 0.0, sumEzafi = 0.0, sumAmount = 0.0;
        for (TaghsitReport tr : taghsitReports)
        {
            sumAmraz += tr.getHaghBimePoosheshAmraz();
            sumHadese += tr.getHaghBimePoosheshHadese();
            sumMoafiat += tr.getHaghBimePoosheshMoafiat();
            sumNaghsOzv += tr.getHaghBimePoosheshNaghsOzv();
            sumMaliat += tr.getMaliat();
            sumEzafi += tr.getMajmooHaghBimePoosheshhayeEzafi();
            sumAmount += tr.getHaghBimePardaakhtiSaal();
        }
        ghestBandi.setHaghBimePoosheshAmraz(NumberFormat.getNumberInstance().format(Math.round(sumAmraz)));
        ghestBandi.setHaghBimePoosheshHadese(NumberFormat.getNumberInstance().format(Math.round(sumHadese)));
        ghestBandi.setHaghBimePoosheshMoafiat(NumberFormat.getNumberInstance().format(Math.round(sumMoafiat)));
        ghestBandi.setHaghBimePoosheshNaghsOzv(NumberFormat.getNumberInstance().format(Math.round(sumNaghsOzv)));
        ghestBandi.setMajmooeAmount(Math.round(sumAmount));
        ghestBandi.setMajmooeEzafi(Math.round(sumEzafi));
        ghestBandi.setMajmooeMaliat(Math.round(sumMaliat));
        ghestBandi.setType(GhestBandi.Type.G_BIMENAME);
        ghestBandi.setCreator(creator);
        List<Ghest> ghestList = new ArrayList<Ghest>();
        List<Credebit> credebitList = new ArrayList<Credebit>();
        int ghestAmount = 0;
        int i = 0;
        for (TaghsitReport report : taghsitReports)
        {
            Ghest ghest = new Ghest();
//            i++;
//            ghest.setNumber(i);
            ghest.setGhestBandi(ghestBandi);
            ghest.setSarresidDate(report.getTarikh());
            ghest.setHaghBimeFotYekja(NumberFormat.getNumberInstance().format(Math.round(report.getHaghBimeKhaalesFotYekja())));
            ghest.setHaghBimePoosheshhayeEzafi(NumberFormat.getNumberInstance().format(report.getHaghBimePusheshHaayeEzaafi()));
            ghest.setMaliat(NumberFormat.getNumberInstance().format(report.getMaliat()));
            ghest.setHazineBimegari(NumberFormat.getNumberInstance().format(Math.round(report.getHazineBimeGari())));
            ghest.setHazineEdari(NumberFormat.getNumberInstance().format(Math.round(report.getHazineEdari())));
            ghest.setHazineKarmonz(NumberFormat.getNumberInstance().format(Math.round(report.getKaarmozd())));
            ghest.setHazineVosoul(NumberFormat.getNumberInstance().format(Math.round(report.getHazineVosul())));
            ghest.setSarmayeFotBeHarEllat(NumberFormat.getNumberInstance().format(Math.round(report.getSarmaayeFotBehHarEllat())));
            ghest.setSarmayeFotDarAsarHadeseh(NumberFormat.getNumberInstance().format(Math.round(report.getSarmaayeFotDarAsarHaadeseh())));
            ghest.setSarmayePoosheshEzafiAmraazKhaas(NumberFormat.getNumberInstance().format(Math.round(report.getSarmaayePusheshEzaafiAmraazKhaas())));
            ghest.setHazineTaghsit(NumberFormat.getNumberInstance().format(report.getZaribNahveyePardakht()).substring(0, Math.min(5, NumberFormat.getNumberInstance().format(report.getZaribNahveyePardakht()).length())));
            ghest.setKarmozdAmount(NumberFormat.getNumberInstance().format(report.getKaarmozd()));
            ghest.setHaghBimePoosheshAmraz(NumberFormat.getNumberInstance().format(Math.round(report.getHaghBimePoosheshAmraz())));
            ghest.setHaghBimePoosheshHadese(NumberFormat.getNumberInstance().format(Math.round(report.getHaghBimePoosheshHadese())));
            ghest.setHaghBimePoosheshNaghsOzv(NumberFormat.getNumberInstance().format(Math.round(report.getHaghBimePoosheshNaghsOzv())));
            ghest.setHaghBimePoosheshMoafiat(NumberFormat.getNumberInstance().format(Math.round(report.getHaghBimePoosheshMoafiat())));
            ghestAmount = (int) report.getHaghBimePardaakhtiSaal();
            Credebit credebit = new Credebit(Integer.toString(ghestAmount), sequenceService.nextShenaseCredebit(), pishnehad.getBimename(), pishnehad, Credebit.CredebitType.GHEST);
            credebit.setGhest(ghest);
            credebit.setShomareMoshtari(sequenceService.generateNextShomareMoshtari(pishnehad.getKarshenas().getMojtamaSodoor(), "9", "120",credebit.getRemainingAmount_long()));
            ghest.setCredebit(credebit);
            boolean isYekja = pishnehad.getEstelam().getNahve_pardakht_hagh_bime().equals("yekja");
            ghest.setKarmozdVosuli(getKarmozdVosul(pishnehad.getBimename().getTarikhSodour(), pishnehad.getTarh(), new Integer(ghestAmount).longValue(), new Double(report.getMaliat()).longValue(), new Double(report.getMajmooHaghBimePoosheshhayeEzafi()).longValue(), isYekja, null));
            ghest.setKarmozdPoosheshEzafi(getKarmozdPoosheshEzafi(new Double(report.getHaghBimePusheshHaayeEzaafi()).longValue(), pishnehad.getTarh(), pishnehad.getBimename().getTarikhSodour()));
            ghestList.add(ghest);
            credebitList.add(credebit);
        }
        asnadeSodorDAO.saveGhestBandi(ghestBandi);
        asnadeSodorDAO.saveGhestList(ghestList);
        ghestBandi.setGhestList(ghestList);
        asnadeSodorDAO.updateBimename(ghestBandi.getBimename());
        refreshObject(ghestBandi.getBimename());
        OmrUtil.generateGhestNumber(ghestBandi);
        asnadeSodorDAO.saveGhestList(ghestBandi.getGhestList());
        asnadeSodorDAO.saveAllCredebit(credebitList);
        if (pishnehad.getBimename().getConverted() == null || pishnehad.getBimename().getConverted().isEmpty())
        {
            List<Credebit> fishs = pishnehad.getFoundAndHaveRemainingFishes();
            double fishRemValue = 0;
            for (Credebit c : fishs)
            {
                fishRemValue += c.getRemainingAmount_long();
            }
            if (fishRemValue > 0)
            {
                int numGhestsToSanad = (int) Math.ceil(fishRemValue / ghestAmount);
                if (numGhestsToSanad > credebitList.size()) numGhestsToSanad = credebitList.size();
                List<Credebit> ghests = credebitList.subList(0, numGhestsToSanad);
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                User user = loginService.findUserByUsername(username);
                sabteSanad(ghests, fishs, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM, user, true);
            }
        }
        Estelam estelam=pishnehad.getEstelam();
        final org.slf4j.Logger logger = LoggerFactory.getLogger(AsnadeSodorService.class);
        String header = String.format("gb_"+ghestBandi.getId()+"_created_with_estelam_info : estelam_id="+estelam.getId()+" | nahve_pardakht="+estelam.getNahve_pardakht_hagh_bime()+
                        " | hagh_bime_pardakhti="+estelam.getHagh_bime_pardakhti()+" | nerkh_afzayesh_salaneh_hagh_bime="+estelam.getNerkh_afzayesh_salaneh_hagh_bime()+
                        " | sarmaye_paye_fot="+estelam.getSarmaye_paye_fot_long().toString()+ " | ");
        logger.info(String.format(header));
        return ghestList;
    }

    public List<Integer> findPishnehadsForAutoBatchTaghsit(boolean manual)
    {
        return asnadeSodorDAO.findPishnehadsForAutoBatchTaghsit(manual);
    }

    public List<GhestBandi> findInvalidBimenames()
    {
        return asnadeSodorDAO.findInvalidBimenames();
    }

    public List<Credebit> temp_findQueuedCredebits() {
        return asnadeSodorDAO.temp_findQueuedCredebits();
    }

    public List<Integer> temp_findQueuedIds()
    {
        return asnadeSodorDAO.temp_findQueuedIds();
    }

    public long getKarmozdVosul(String tarikhSodurBimename, Tarh tarh, long ghestAmount, long maliat, long haghBimePoosheshhayeEzafi, boolean isYekja, Double hazineVosul)
    {
        Double vosul = hazineVosul;
        if (hazineVosul == null)
        {
            vosul = AsnadeSodorService.getVosulGyekja(tarikhSodurBimename, tarh);
        }
        if (isYekja)
        {
            vosul = AsnadeSodorService.getVosulYekja(tarikhSodurBimename, tarh);
        }
        if (vosul < 0.02) return 0;
        Double karmozd = (0.02 * (ghestAmount - maliat - haghBimePoosheshhayeEzafi));
        return karmozd.longValue();
    }

    public long getKarmozdPoosheshEzafi(long haghBimePoosheshhayeEzafi, Tarh tarh, String date)
    {
        double darsadMaliat = AsnadeSodorService.getDarsadMaliat(date, tarh);
        Double karmozd = (0.1 * (haghBimePoosheshhayeEzafi - (haghBimePoosheshhayeEzafi * darsadMaliat)));
        return karmozd.longValue();
    }

    public CashTurnoverReportVM getCashTurnoverMap(String fromDate, String toDate,Namayande namayande)
    {
        List<KhateSanad> khateSanadList = asnadeSodorDAO.getKhateSanadsOfVehicle(fromDate, toDate, namayande);
        Map<Credebit.CredebitType, CashTurnoverRowsVM> cashTurnoverMap = new HashMap<Credebit.CredebitType, CashTurnoverRowsVM>();

        CashTurnoverReportVM cashTurnoverReport = new CashTurnoverReportVM();
        cashTurnoverReport.setFromDate(fromDate);
        cashTurnoverReport.setToDate(toDate);
        cashTurnoverReport.setTotalDebit(0l);
        cashTurnoverReport.setTotalCredit(0l);
        cashTurnoverReport.setTotalCreditWithoutBargashti(0l);

        for (KhateSanad kh : khateSanadList)
        {
            List<KhateSanad> khateSanadsBedehi = new ArrayList<KhateSanad>();
            khateSanadsBedehi.add(kh);

            CashTurnoverRowsVM cashTurnoverRowsVM = new CashTurnoverRowsVM();
            Credebit.CredebitType bedehiType = kh.getBedehiCredebit().getCredebitType();
            if (!cashTurnoverMap.containsKey(bedehiType))
            {
                cashTurnoverRowsVM.setType(CashTurnoverRowsVM.Type.DEBIT);
                cashTurnoverRowsVM.setCredebitType(kh.getBedehiCredebit().getCredebitType());
                cashTurnoverRowsVM.setKhateSanadList(khateSanadsBedehi);
                cashTurnoverRowsVM.setTotalKhateSanadAmount(Long.parseLong(kh.getAmount().replaceAll(",", "").trim()));
            }
            else//if (cashTurnoverMap.containsKey(bedehiType))
            {
                cashTurnoverRowsVM = cashTurnoverMap.get(bedehiType);
                khateSanadsBedehi.addAll(cashTurnoverRowsVM.getKhateSanadList());
                cashTurnoverRowsVM.setKhateSanadList(khateSanadsBedehi);
                cashTurnoverRowsVM.setTotalKhateSanadAmount(cashTurnoverRowsVM.getTotalKhateSanadAmount() + Long.parseLong(kh.getAmount().replaceAll(",", "").trim()));
                cashTurnoverMap.remove(bedehiType);
            }
            cashTurnoverMap.put(bedehiType, cashTurnoverRowsVM);
            cashTurnoverReport.setTotalDebit(cashTurnoverReport.getTotalDebit() + Long.parseLong(kh.getAmount().replaceAll(",", "").trim()));

            List<KhateSanad> khateSanadsEtebar = new ArrayList<KhateSanad>();
            khateSanadsEtebar.add(kh);

            cashTurnoverRowsVM = new CashTurnoverRowsVM();
            Credebit.CredebitType etebarType = kh.getEtebarCredebit().getCredebitType();
            if (!cashTurnoverMap.containsKey(etebarType))
            {
                cashTurnoverRowsVM.setType(CashTurnoverRowsVM.Type.CREDIT);
                cashTurnoverRowsVM.setCredebitType(kh.getEtebarCredebit().getCredebitType());
                cashTurnoverRowsVM.setKhateSanadList(khateSanadsEtebar);
                cashTurnoverRowsVM.setTotalKhateSanadAmount(Long.parseLong(kh.getAmount().replaceAll(",", "").trim()));
            }
            else//if (cashTurnoverMap.containsKey(bedehiType))
            {
                cashTurnoverRowsVM = cashTurnoverMap.get(etebarType);
                khateSanadsEtebar.addAll(cashTurnoverRowsVM.getKhateSanadList());
                cashTurnoverRowsVM.setKhateSanadList(khateSanadsEtebar);
                cashTurnoverRowsVM.setTotalKhateSanadAmount(cashTurnoverRowsVM.getTotalKhateSanadAmount() + Long.parseLong(kh.getAmount().replaceAll(",", "").trim()));
                cashTurnoverMap.remove(etebarType);
            }
            cashTurnoverMap.put(etebarType, cashTurnoverRowsVM);
            cashTurnoverReport.setTotalCredit(cashTurnoverReport.getTotalCredit() + Long.parseLong(kh.getAmount().replaceAll(",", "").trim()));
            if (!etebarType.equals(Credebit.CredebitType.ELHAGHIE_BARGASHTI))
            {
                cashTurnoverReport.setTotalCreditWithoutBargashti(cashTurnoverReport.getTotalCreditWithoutBargashti() + Long.parseLong(kh.getAmount().replaceAll(",", "").trim()));
            }
        }
        cashTurnoverReport.setCashTurnoverRowsVMList(new ArrayList<CashTurnoverRowsVM>(cashTurnoverMap.values()));
        return cashTurnoverReport;
    }

    public void saveGhest(TaghsitReport report, GhestBandi ghestBandi)
    {
        Ghest ghest = new Ghest();
        ghest.setGhestBandi(ghestBandi);
        ghest.setSarresidDate(report.getTarikh());
        ghest.setHaghBimeFotYekja(NumberFormat.getNumberInstance().format(Math.round(report.getHaghBimeKhaalesFotYekja())));
        ghest.setHaghBimePoosheshhayeEzafi(NumberFormat.getNumberInstance().format(report.getHaghBimePusheshHaayeEzaafi()));
        ghest.setMaliat(NumberFormat.getNumberInstance().format(report.getMaliat()));
        ghest.setHazineBimegari(NumberFormat.getNumberInstance().format(Math.round(report.getHazineBimeGari())));
        ghest.setHazineEdari(NumberFormat.getNumberInstance().format(Math.round(report.getHazineEdari())));
        ghest.setHazineKarmonz(NumberFormat.getNumberInstance().format(Math.round(report.getKaarmozd())));
        ghest.setHazineVosoul(NumberFormat.getNumberInstance().format(Math.round(report.getHazineVosul())));
        ghest.setSarmayeFotBeHarEllat(NumberFormat.getNumberInstance().format(Math.round(report.getSarmaayeFotBehHarEllat())));
        ghest.setSarmayeFotDarAsarHadeseh(NumberFormat.getNumberInstance().format(Math.round(report.getSarmaayeFotDarAsarHaadeseh())));
        ghest.setSarmayePoosheshEzafiAmraazKhaas(NumberFormat.getNumberInstance().format(Math.round(report.getSarmaayePusheshEzaafiAmraazKhaas())));
        ghest.setHazineTaghsit(NumberFormat.getNumberInstance().format(report.getZaribNahveyePardakht()).substring(0, Math.min(5, NumberFormat.getNumberInstance().format(report.getZaribNahveyePardakht()).length())));
        ghest.setKarmozdAmount(NumberFormat.getNumberInstance().format(report.getKaarmozd()));
        ghest.setHaghBimePoosheshAmraz(NumberFormat.getNumberInstance().format(Math.round(report.getHaghBimePoosheshAmraz())));
        ghest.setHaghBimePoosheshHadese(NumberFormat.getNumberInstance().format(Math.round(report.getHaghBimePoosheshHadese())));
        ghest.setHaghBimePoosheshNaghsOzv(NumberFormat.getNumberInstance().format(Math.round(report.getHaghBimePoosheshNaghsOzv())));
        ghest.setHaghBimePoosheshMoafiat(NumberFormat.getNumberInstance().format(Math.round(report.getHaghBimePoosheshMoafiat())));
        int ghestAmount = OmrUtil.rondPardakhti((int) report.getHaghBimePardaakhtiSaal());
        Credebit credebit = new Credebit(Integer.toString(ghestAmount), sequenceService.nextShenaseCredebit(), ghestBandi.getBimename(), null, Credebit.CredebitType.GHEST);
        credebit.setGhest(ghest);
        credebit.setShomareMoshtari(sequenceService.generateNextShomareMoshtari(ghestBandi.getBimename().getPishnehad().getKarshenas().getMojtamaSodoor(), "9", "120",credebit.getRemainingAmount_long()));
        ghest.setCredebit(credebit);
        asnadeSodorDAO.save(ghest);
        asnadeSodorDAO.save(credebit);
    }

    public static double getNerkhBahreFanni(String date, Constants constant, Tarh tarh)
    {
        String nerkh = getConstantKey(date, constant, Constants.Keys.NERKHBAHREFANNI, Constants.KeysParam.MEGHDARNERKH, tarh);
        if (nerkh.trim().equals(""))
        {
            return 0.15;
        }

        String[] nerkhs = nerkh.split(",");
        if (date == null)
        {
            return Double.valueOf(nerkhs[0].trim());
        }

        int bimeYear = DateUtil.getBimeYear(DateUtil.getCurrentDate(), date);
        if (bimeYear == -1)
        {
            return Double.valueOf(nerkhs[0].trim());
        }

        if (bimeYear > nerkhs.length - 1)
        {
            bimeYear = nerkhs.length - 1;
        }

        return Double.valueOf(nerkhs[bimeYear]);
    }

    public static double getNerkhBahreFanniForYear(int year, Constants constant, Tarh tarh)
    {
        String nerkh = getConstantKey(DateUtil.getCurrentDate(), constant, Constants.Keys.NERKHBAHREFANNI, Constants.KeysParam.MEGHDARNERKH, tarh);
        if (nerkh.trim().equals(""))
        {
            return 0.15;
        }
        String[] nerkhs = nerkh.split(",");
        if (year > nerkhs.length)
        {
            year = nerkhs.length;
        }
        return Double.valueOf(nerkhs[year - 1].trim());
    }

    public void deleteGhestsById(Integer ghestId)
    {
        asnadeSodorDAO.deleteBedehisById(ghestId);
    }

    public void saveGhest(Ghest ghest)
    {
        asnadeSodorDAO.saveBedehi(ghest);
    }

    public void saveGhestList(List<Ghest> gList)
    {
        asnadeSodorDAO.saveGhestList(gList);
    }

    @Transactional
    public void deleteGhestBandiById(GhestBandi gb)
    {
        // delete sanads first
        HashSet<Integer> sanadIds = new HashSet<Integer>();
        for (Ghest g : gb.getGhestList())
        {
            for (KhateSanad ks : g.getCredebit().getKhateSanadsBedehi())
            {
                if (!sanadIds.contains(ks.getSanad().getId()))
                {
                    sanadIds.add(ks.getSanad().getId());
                }
            }
        }
        for (Integer sanadId : sanadIds)
        {
            deleteSanad(sanadId);
        }
        asnadeSodorDAO.deleteGhestBandei(gb);
    }

    public void hazfeGhest(int ghestId)
    {
        asnadeSodorDAO.deleteGhest(ghestId);
    }

    public GhestBandi findGhestBandi(Integer bimenameId, int saleBimei)
    {
        return asnadeSodorDAO.findGhestBandi(bimenameId, saleBimei);
    }

    public void saveDaryafteFishElectroniki(DaryafteFish daryafteFish)
    {
        asnadeSodorDAO.saveDaryafteFishElectroniki(daryafteFish);
    }

    public void saveDaryafteCheck(DaryafteCheck daryafteCheck)
    {
        asnadeSodorDAO.saveDaryafteCheck(daryafteCheck);
    }

    public PaginatedListImpl<Credebit> findAllCredebitsByFilterPaginated(CredebitSearchForm credebitSearchForm, User user, boolean sendToList)
    {
        return asnadeSodorDAO.findAllCredebitsByFilterPaginated(credebitSearchForm, user, sendToList);
    }

    public void createBedehiDasti(Ghest ghest)
    {
        ghest.getCredebit().setStatus(Credebit.Status.SANAD_NA_KHORDE);
        if (ghest.getCredebit().getCheck() != null)
        {
            Check check = checkDAO.findCheckById(ghest.getCredebit().getCheck().getId());
            int preAmount = Integer.valueOf(check.getAmountTajamoi());
            int addAmount = Integer.valueOf(ghest.getCredebit().getAmount());
            check.setAmountTajamoi(String.valueOf(preAmount + addAmount));
            checkDAO.updateCheck(check);
        }
        asnadeSodorDAO.saveBedehi(ghest);
    }

    public Ghest findBedehiById(Integer bedehiId)
    {
        return asnadeSodorDAO.findBedehiById(bedehiId);
    }

    public Credebit createEtebareDasti(Credebit credebit, User user)
    {
        // validate sabte etebar dasti
        // todo: checkhaye in method be method zir montaghel beshan va message manidar bargarde be karbar
        // todo: authorization sabthaye karbar
        if (!DPUtil.isCredebitValidForSabtDasti(credebit).equals("VALID"))
        {
            return null;
        }
        if (user != null)
        {
            if (userHasRole(user, "ROLE_KARBAR_MALI"))
            {
                if (userHasRole(user, "ROLE_NAMAYANDE"))
                {
                    credebit.setNamayande(user.getNamayandegi());
                    credebit.setVahedeSodor(user.getNamayandegi());
//                    credebit.setSubsystemName("VEHICLE");
                }
                else
                {
                    if (credebit.getCredebitType().equals(Credebit.CredebitType.AZ_MAHALLE_TABLIGHAT_MANUAL) ||
                        credebit.getCredebitType().equals(Credebit.CredebitType.KASR_AZ_HOGHUGH)||
                        credebit.getCredebitType().equals(Credebit.CredebitType.KASR_AZ_KARMOZD))
                    {
                       Namayande namayande = namayandeService.getNamayandeById(credebit.getNamayande().getId());
                       credebit.setVahedeSodor(namayande);
                    }
                    else
                    {
                    credebit.setSubsystemName("");
                    }
                }
            }
        }
        // b-h
        Integer daftar_id=user.getDaftar().getId();
//        Integer daftar_id=(Integer) ActionContext.getContext().getSession().get("daftar_id");
        if(daftar_id !=null) {
            Daftar daftar=asnadeSodorDAO.findDaftarById(daftar_id);
            credebit.setDaftar(daftar);
        }
        if(user !=null)
            credebit.setUser(user);
        //////////ta inja
        credebit.setStatus(Credebit.Status.SANAD_NA_KHORDE);
        credebit.setRemainingAmount(credebit.getAmount());
        credebit.setShenaseCredebit(sequenceService.nextShenaseCredebit());
        credebit.setVosoulState(Credebit.VaziyatVosoul.TAEED_NASHODE);

        if(credebit.getCredebitType().equals(Credebit.CredebitType.AZ_MAHALLE_TABLIGHAT)
                || credebit.getCredebitType().equals(Credebit.CredebitType.KASR_AZ_HOGHUGH)
                || credebit.getCredebitType().equals(Credebit.CredebitType.KASR_AZ_KARMOZD)
                || credebit.getCredebitType().equals(Credebit.CredebitType.AZ_MAHALE_HAZINE_BIME_DARAYIHA)
                || credebit.getCredebitType().equals(Credebit.CredebitType.ETEBAR_DARMAN_KHANEVADE)) {
            credebit.setVosoulState(Credebit.VaziyatVosoul.TAEED_VOSOUL);
            credebit.setVosoulDate(DateUtil.getCurrentDate());
        }

        if (credebit.getCreatedDate() == null || credebit.getCreatedDate().equalsIgnoreCase(""))
        {
            credebit.setCreatedDate(DateUtil.getCurrentDate());
            credebit.setCreatedTime(DateUtil.getCurrentTime());
        }
        if (credebit.getCredebitType().equals(Credebit.CredebitType.PARDAKHTE_CHECK))
        {
            Check check = checkDAO.findCheckById(credebit.getCheck().getId());
            Long addValue = Long.parseLong(StringUtil.removeNoneDigits(credebit.getAmount()));
            Long preValue = Long.parseLong(check.getAmountTajamoi() == null ? "0" : check.getAmountTajamoi());
            check.setAmountTajamoi(String.valueOf(addValue + preValue));
            if (credebit.getDaryafteCheck() != null)
            {
                credebit.getDaryafteCheck().setType(DaryafteCheck.Type.NORMAL);
                credebit.getDaryafteCheck().setStatus(DaryafteCheck.Status.NAZD_SANDOGH);
            }

            checkDAO.updateCheck(check);
            credebit.setCheck(check);
        }
        else if (credebit.getCredebitType().equals(Credebit.CredebitType.PARDAKHTE_TANKHAH) && userHasRole(user, "ROLE_PARDAKHTE_TANKHAH"))
        {
            asnadeSodorDAO.savePardakhteTankhah(credebit.getPardakhteTankhah());
        }
        else if (credebit.getCredebitType().equals(Credebit.CredebitType.DARYAFTE_FISH)
                || credebit.getCredebitType().equals(Credebit.CredebitType.DARYAFTE_FISH_NAMAYANDE)
                || credebit.getCredebitType().equals(Credebit.CredebitType.DARYAFTE_ELECTRONICI)
                || credebit.getCredebitType().equals(Credebit.CredebitType.PARDAKHT_SHENASEDAR))
        {

            credebit.setSarresidDate(credebit.getDateFish());
            credebit.getDaryafteFish().setTarikh(credebit.getDateFish());
            if (credebit.getCredebitType().equals(Credebit.CredebitType.PARDAKHT_SHENASEDAR))
            {
                if (credebit.getShomareMoshtari() != null && credebit.getShomareMoshtari().length() > 0)
                {
                    if (credebit.getShomareMoshtari().contains(","))
                    {
                        credebit.setShomareMoshtari(credebit.getShomareMoshtari().split(",")[0]);
                    }
                }
                else
                {
                    return null;
                }
                if(getCredebitByShomareMoshtari(credebit.getShomareMoshtari()) != null) {
                    // code moshtari duplicate
                    return null;
                }
            }

            credebit.getDaryafteFish().setShomareFish(credebit.getShomareMoshtari());
            credebit.getDaryafteFish().setShomareSanadBank(credebit.getDaryafteFish().getShomareFish());
            credebit.setAuthorityId(credebit.getDaryafteFish().getShomareFish());  //omr
            credebit.getDaryafteFish().setShomareFish(credebit.getDaryafteFish().getShomareFish()); //sanam
            asnadeSodorDAO.saveDaryafteFishElectroniki(credebit.getDaryafteFish());
        }
        else if (credebit.getCredebitType().equals(Credebit.CredebitType.DARYAFTE_CHECK) || credebit.getCredebitType().equals(Credebit.CredebitType.DARYAFTE_CHECK_NAMAYANDE))
        {
            credebit.getDaryafteCheck().setStatus(DaryafteCheck.Status.NAZD_SANDOGH);
            credebit.getDaryafteCheck().setTarikhSarresid(credebit.getSarresidDate());
            credebit.getDaryafteCheck().setSerial(credebit.getShomareCheckShomareFish());
            asnadeSodorDAO.saveDaryafteCheck(credebit.getDaryafteCheck());
        }
        else if (credebit.getCredebitType().equals(Credebit.CredebitType.HESAB_FI_MA_BEYN) || credebit.getCredebitType().equals(Credebit.CredebitType.AZ_MAHALLE_TABLIGHAT_MANUAL))
        {
            credebit.setVosoulState(Credebit.VaziyatVosoul.TAEED_VOSOUL);
            credebit.setVosoulDate(DateUtil.getCurrentDate());
        }
        asnadeSodorDAO.saveCredebit(credebit);
        return credebit;
    }
    public void wirayeshEtebareCheck(Credebit credebitForm) //wirayesh etebar dar safheye liste etebarat va bedehiha
    {
        try
        {
            Credebit etebar = asnadeSodorDAO.findCredebitById(credebitForm.getId());
            //etebar.setCredebitType(credebitForm.getCredebitType()); //noe etebar
//            Namayande namayandeEtebar = namayandeService.getNamayandeById(etebar.getNamayande().getId());
//            namayandeEtebar.setName(credebitForm.getNamayande().getName());
//            asnadeSodorDAO.saveOrUpdate(namayandeEtebar);
//            etebar.setNamayande(namayandeEtebar);  //namayande

            DaryafteCheck dryftChkEtebar = asnadeSodorDAO.findDaryafteCheckById(etebar.getDaryafteCheck().getId());
            dryftChkEtebar.setHesabBanki(credebitForm.getDaryafteCheck().getHesabBanki()); //shomare hesab
            dryftChkEtebar.setSerial(credebitForm.getDaryafteCheck().getSerial()); //shomare check
            dryftChkEtebar.setSeri(credebitForm.getDaryafteCheck().getSeri());  //seri
            dryftChkEtebar.setBranchName(credebitForm.getDaryafteCheck().getBranchName()); //name shobe
            dryftChkEtebar.setBranchCode(credebitForm.getDaryafteCheck().getBranchCode()); //code shobe
            dryftChkEtebar.setAccountOwnerName(credebitForm.getDaryafteCheck().getAccountOwnerName()); //name darande check
            dryftChkEtebar.setTarikhSarresid(credebitForm.getSarresidDate()); //tarikhe sarreside check
            asnadeSodorDAO.saveOrUpdate(dryftChkEtebar);
            etebar.setDaryafteCheck(dryftChkEtebar);

            etebar.setSarresidDate(credebitForm.getSarresidDate()); //tarikhe sarreside check
//            if (etebar.getCredebitType().equals(Credebit.CredebitType.DARYAFTE_CHECK) && etebar.getShomareMoshtari() != null) //agar check vagozar shode bood
//            {
//                etebar.setShomareMoshtari(null); //shenase pardakht
//            }
            etebar.setBankName(credebitForm.getBankName()); //name bank
            etebar.setRcptName(credebitForm.getRcptName()); //tarafe hesab
            etebar.setAmount(credebitForm.getAmount()); //mablagh
            etebar.setDescription(credebitForm.getDescription());  //tozihat

            asnadeSodorDAO.updateCredebit(etebar);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
    public GhestBandi findGhestBandiById(int ghestBandiId)
    {
        return asnadeSodorDAO.findGhestBandiById(ghestBandiId);
    }

    public Sanad findSanadById(Integer sanadId)
    {
        return asnadeSodorDAO.findSanadById(sanadId);
    }

    public void saveGhestBandi(GhestBandi ghestBandi)
    {
        asnadeSodorDAO.saveGhestBandi(ghestBandi);
    }

    public void updateGhestbandi(GhestBandi ghestBandi)
    {
        asnadeSodorDAO.updateGhestbandi(ghestBandi);
    }

    public void updateGhest(Ghest ghest)
    {
        asnadeSodorDAO.updateGhest(ghest);
    }

    public void saveCredebitList(List<Credebit> credebitList)
    {
        asnadeSodorDAO.saveCredebitList(credebitList);
    }

    public List<Credebit> findCredebitForTaeedFish(Fish fish)
    {
        return asnadeSodorDAO.findCredebitForTaeedFish(fish);
    }

    public PaginatedListImpl<Credebit> findAllEtebarCredebitsPaginated(User user)
    {
        return asnadeSodorDAO.findAllEtebarCredebitsByStatusPaginated(user, null);
    }

    public PaginatedListImpl<Credebit> validationSabteSanadEtebarCredebitId(Integer credebitId, User user)
    {
        return asnadeSodorDAO.findAllEtebarCredebitsByStatusPaginated(user, credebitId);
    }

    public Credebit findBedehiByCodeMoshtari(String codeMoshtari, Long amount)
    {
        return asnadeSodorDAO.findBedehiCredebitsByCodeMoshtari(codeMoshtari, amount);
    }

    public List<Credebit> findBedehiByBimenameAndAmount(String shomareBimename, Long amount)
    {
        return asnadeSodorDAO.findBedehiByBimenameAndAmount(shomareBimename, amount);
    }

    public List<Credebit> dev_findAllEtebarCredebitsByBankInfo() {
        return asnadeSodorDAO.getCredebitBankInfos();
//        List<Integer> ids = asnadeSodorDAO.dev_findAllEtebarCredebitsByBankInfo();
//        LinkedList<Credebit> returnValue = new LinkedList<Credebit>();
//        for(Integer id : ids) {
//            returnValue.add(findCretebitById(id));
//        }
//        return returnValue;
    }

    public Credebit findCredebitByCodeMoshtari(String codeMoshtari)
    {
        return asnadeSodorDAO.findCredebitByCodeMoshtari(codeMoshtari);
    }

    public boolean isRepetitiousCodeMoshtari(String codeMoshtari)
    {
        return asnadeSodorDAO.isAvailableCodeMoshtari(codeMoshtari);
    }

    public List<GhestBandi> ghestBandiList4SaleAval(Integer bimenameId)
    {
        return asnadeSodorDAO.ghestBandiList4SaleAval(bimenameId);
    }

    public PaginatedListImpl<Credebit> findAllCredebitsPaginated(User user, Credebit.CredebitMainType credebitNoe, String identifier, String shomareMoshtari, String rcptName, String sarresidDateFrom, String sarresidDateTo, String createdDateFrom, String createdDateTo, String amount, String paidReceivedAmount, String remainingAmount, Credebit.CredebitType credebitTypeFarsi, String shomareGharardad, Credebit.Status statusFarsi, Credebit.VaziyatVosoul vosoulStateFarsi, Long namayandeId, String bankName, String subsystem_field, String shomareCheck, String shomareFish)
    {
        return asnadeSodorDAO.findAllCredebitsPaginated(user, credebitNoe, identifier, shomareMoshtari, rcptName, sarresidDateFrom, sarresidDateTo, createdDateFrom, createdDateTo, amount, paidReceivedAmount, remainingAmount, credebitTypeFarsi, shomareGharardad, statusFarsi, vosoulStateFarsi, namayandeId, bankName, subsystem_field, shomareCheck, shomareFish);
    }

    public PaginatedListImpl<Credebit> findListCredebitShabaPaginated(User user, Credebit.CredebitMainType credebitNoe, String identifier, String shomareMoshtari, String rcptName, String sarresidDateFrom, String sarresidDateTo, String createdDateFrom, String createdDateTo, String amount, String paidReceivedAmount, String remainingAmount, String shomareGharardad, Credebit.Status statusFarsi, Credebit.VaziyatVosoul vosoulStateFarsi, Long namayandeId, String bankName, String subsystem_field)
    {
        return asnadeSodorDAO.findListCredebitShabaPaginated(user, credebitNoe, identifier, shomareMoshtari, rcptName, sarresidDateFrom, sarresidDateTo, createdDateFrom, createdDateTo, amount, paidReceivedAmount, remainingAmount, shomareGharardad, statusFarsi, vosoulStateFarsi, namayandeId, bankName, subsystem_field);
    }

    public PaginatedListImpl<BankInfo> findAllBankInfoPaginated(User user, String createdDateFrom, String createdDateTo, String tarikhVarizFrom, String tarikhVarizTo, String fullname, String codeMoshtari, String vosoulDesc)
    {
        return asnadeSodorDAO.findAllBankInfoPaginated(user, createdDateFrom, createdDateTo, tarikhVarizFrom, tarikhVarizTo, fullname, codeMoshtari, vosoulDesc);
    }

    public PaginatedListImpl<Credebit> findListGozareshVosouli(User user, Credebit.CredebitMainType credebitNoe, String identifier, String shomareMoshtari, String rcptName, String sarresidDateFrom, String sarresidDateTo, String createdDateFrom, String createdDateTo, String amount, String paidReceivedAmount, String remainingAmount, Credebit.CredebitType credebitTypeFarsi, String shomareGharardad, Credebit.Status statusFarsi, Credebit.VaziyatVosoul vosoulStateFarsi, Long namayandeId, String bankName, String subsystem_field)
    {
        return asnadeSodorDAO.findListGozareshVosouli(user, credebitNoe, identifier, shomareMoshtari, rcptName, sarresidDateFrom, sarresidDateTo, createdDateFrom, createdDateTo, amount, paidReceivedAmount, remainingAmount, credebitTypeFarsi, shomareGharardad, statusFarsi, vosoulStateFarsi, namayandeId, bankName, subsystem_field);
    }

    public Credebit.VaziyatVosoul getVaziyatVosulBedehi(Integer credebitId)
    {
        return asnadeSodorDAO.getVaziyatVosulBedehi(credebitId);
    }

    public PaginatedListImpl<Credebit> findAllBedehiCredebitsPaginated(int page, User user, Credebit.CredebitMainType credebitNoe, String identifier, String shomareMoshtari, String rcptName, String sarresidDateFrom, String sarresidDateTo, String createdDateFrom, String createdDateTo, String amount, String paidReceivedAmount, String remainingAmount, Credebit.CredebitType credebitTypeFarsi, String shomareGharardad, Credebit.Status statusFarsi, Credebit.VaziyatVosoul vosoulStateFarsi, Long namayandeId, Long vahedesodorId, boolean isSearch)
    {
        return asnadeSodorDAO.findAllBedehiCredebitsPaginated(page, user, credebitNoe, identifier, shomareMoshtari, rcptName, sarresidDateFrom, sarresidDateTo, createdDateFrom, createdDateTo, amount, paidReceivedAmount, remainingAmount, credebitTypeFarsi, shomareGharardad, statusFarsi, vosoulStateFarsi, namayandeId, vahedesodorId, isSearch);
    }

    public PaginatedListImpl<Credebit> findAllEtebaratVosulNashodeCredebitsPaginated(int page, User user, Credebit.CredebitMainType credebitNoe, String identifier, String shomareMoshtari, String rcptName, String sarresidDateFrom, String sarresidDateTo, String createdDateFrom, String createdDateTo, String amount, String paidReceivedAmount, String remainingAmount, String shomareGharardad, Credebit.Status statusFarsi, Credebit.VaziyatVosoul vosoulStateFarsi, Long namayandeId)
    {
        return asnadeSodorDAO.findAllEtebaratVosulNashodeCredebitsPaginated(page, user, credebitNoe, identifier, shomareMoshtari, rcptName, sarresidDateFrom, sarresidDateTo, createdDateFrom, createdDateTo, amount, paidReceivedAmount, remainingAmount, shomareGharardad, statusFarsi, vosoulStateFarsi, namayandeId);
    }

    public PaginatedListImpl<Credebit> findMoghayeratDarVosolNamayandePaginated(int page, User user, Credebit.CredebitMainType credebitNoe, String identifier, String shomareMoshtari, String rcptName, String sarresidDate, String createdDate, String amount, String paidReceivedAmount, String remainingAmount, String shomareGharardad, Credebit.Status statusFarsi, Credebit.VaziyatVosoul vosoulStateFarsi, Long namayandeId, String vosoulDesc)
    {
        return asnadeSodorDAO.findMoghayeratDarVosolNamayandePaginated(page, user, credebitNoe, identifier, shomareMoshtari, rcptName, sarresidDate, createdDate, amount, paidReceivedAmount, remainingAmount, shomareGharardad, statusFarsi, vosoulStateFarsi, namayandeId, vosoulDesc);
    }

    public PaginatedListImpl<CredebitVM> findBedehiVosulNashodeNamayandePaginated(int page, User user, Credebit.CredebitMainType credebitNoe, String identifier, String shomareMoshtari, String rcptName, String sarresidDateFrom, String sarresidDateTo, String createdDateFrom, String createdDateTo, String amount, String paidReceivedAmount, String remainingAmount, String shomareGharardad, Credebit.Status statusFarsi, Credebit.VaziyatVosoul vosoulStateFarsi, Long namayandeId, String vosoulDesc, boolean isSearch)
    {
        return asnadeSodorDAO.findBedehiVosulNashodeNamayandePaginated(page, user, credebitNoe, identifier, shomareMoshtari, rcptName, sarresidDateFrom, sarresidDateTo, createdDateFrom, createdDateTo, amount, paidReceivedAmount, remainingAmount, shomareGharardad, statusFarsi, vosoulStateFarsi, namayandeId, vosoulDesc, isSearch);
    }


    public PaginatedListImpl<Credebit> findAllBedehiCredebitsPaginated(User user)
    {
        return asnadeSodorDAO.findAllBedehiCredebitsByStatusPaginated(user, null);
    }

    public PaginatedListImpl<Credebit> validationSabteSanadBedehiCredebitId(Integer credebitId, User user)
    {
        return asnadeSodorDAO.findAllBedehiCredebitsByStatusPaginated(user, credebitId);
    }

    public List<DaryafteCheck> findAllDaryafteChecks()
    {
        return asnadeSodorDAO.findAllDaryafteChecks();
    }

    public List<Hesab> findAllHesabs()
    {
        return asnadeSodorDAO.findAllHesabs();
    }

    public DaryafteCheck findDaryafteCheckById(Integer id)
    {
        return asnadeSodorDAO.findDaryafteCheckById(id);
    }

    public void updateDaryafteCheck(DaryafteCheck theCheck)
    {
        asnadeSodorDAO.updateDaryafteCheck(theCheck);
    }

    public void updateBimename(Bimename bimename)
    {
        asnadeSodorDAO.updateBimename(bimename);
    }

    public void updateBimename(Collection<Bimename> bimename)
    {
        asnadeSodorDAO.saveOrUpdateAll(bimename);
    }

    public void saveMosharekatDarManafe(Mosharekat mosharekatDarManafe)
    {
        asnadeSodorDAO.saveMosharekatDarManafe(mosharekatDarManafe);
    }

    public void updateMosharekatDarManafe(Mosharekat mosharekat)
    {
        asnadeSodorDAO.updateMosharekatDarManafe(mosharekat);
    }

    public Credebit findCretebitById(Integer id)
    {
        return asnadeSodorDAO.findCredebitById(id);
    }

    public void updateCredebit(Credebit credebit)
    {
        asnadeSodorDAO.updateCredebit(credebit);
    }

    @Transactional
    public Mosharekat findMosharekatById(Integer id)
    {
        return asnadeSodorDAO.findMosharekatById(id);  //To change body of implemented methods use File | Settings | File Templates.
    }


    public List<Credebit> findEtebar(Credebit credebit)
    {
        return asnadeSodorDAO.findEtebar(credebit);
    }

    private long mohasebeyeMablagheElhagie(ViewElhaghie viewElhaghie)
    {
        long mablagheElhaghie = 0;
        List<Ghest> oldGhestList = viewElhaghie.getOldBedehiList();
        List<Ghest> newGhestList = viewElhaghie.getNewBedehiList();
        for (Ghest ghest : newGhestList)
        {
            mablagheElhaghie += Long.valueOf(StringUtil.removeNoneDigits(ghest.getCredebit().getAmount()));
        }
        for (Ghest ghest : oldGhestList)
        {
            mablagheElhaghie -= Long.valueOf(StringUtil.removeNoneDigits(ghest.getCredebit().getAmount()));
        }
        return mablagheElhaghie;
    }

    public List<Ghest> findFirstBedehi(int pishnehadId)
    {
// todo should be corrected
//        List<Ghest> ghestList = asnadeSodorDAO.findAllBedehisSorted(pishnehadId, Credebit.Status.SANAD_KHORDE);
//        List<Ghest> newGhestList = new ArrayList<Ghest>();
//        if(!(ghestList.size()>0)){
//            ghestList = asnadeSodorDAO.findAllBedehisSorted(pishnehadId, Credebit.Status.SANAD_NA_KHORDE);
//            if(ghestList.size()>0) newGhestList.add(ghestList.get(0));
//        }
//        return newGhestList;
        return null;
    }


    public double AmrazOrNaghsKhesaratProcesses(DarkhastBazkharid theDarkhast) throws BimeNaamehVaSarmayehGozari.CustomValidationException
    {
        String tarikhMabna= DateUtil.isGreaterThan(DateUtil.getCurrentDate(), theDarkhast.getKhesaratI().getElhaghiye().getTarikhAsar())? DateUtil.getCurrentDate() : theDarkhast.getKhesaratI().getElhaghiye().getTarikhAsar();

//        int accidentBimeYear = DateUtil.getBimeYear(theDarkhast.getKhesaratI().getAccidentDate(), theDarkhast.getBimename().getTarikhShorou());
        int saleMabnayeGhesthayeAti = DateUtil.getBimeYear(tarikhMabna, theDarkhast.getBimename().getTarikhShorou());

        double cashAmount = 0d;
        Pishnehad pishnehad = theDarkhast.getBimename().getPishnehad();
        LinkedList<Flow> flowsHazineha = new LinkedList<Flow>();
        for (GhestBandi gb : theDarkhast.getBimename().getGhestBandiListOfBimename())
        {
            if (gb.getSaleBimeiInt() >= saleMabnayeGhesthayeAti) // : agar aghsate ati bud. . .
            {
                List<TaghsitReport> taghsitReports = mohasebeyeAghsat(pishnehad, gb.getSaleBimeiInt(), theDarkhast.getBimename().getTarikhShorou(), true);

                List<Ghest> ghestList = new ArrayList<Ghest>();
                List<Credebit> credebitList = new ArrayList<Credebit>();

                List<Ghest> deletedGhestList = new ArrayList<Ghest>();
                List<Credebit> deletedCredebitList = new ArrayList<Credebit>();

                for (Ghest g : gb.getGhestList())
                {
                    //Aghsate ati pardakht nashode
                    if(g.getCredebit().getRemainingAmount_long().equals(g.getCredebit().getAmount_long()))
                    {
                        for (TaghsitReport report : taghsitReports)
                        {
                            if(g.getSarresidDate().equals(report.getTarikh()))
                            {
                                Ghest ghest = new Ghest();
                                ghest.setId(null);
                                ghest.setNumber(g.getNumber());
                                ghest.setGhestBandi(gb);
                                ghest.setSarresidDate(report.getTarikh());
                                ghest.setHaghBimeFotYekja(NumberFormat.getNumberInstance().format(Math.round(report.getHaghBimeKhaalesFotYekja())));
                                ghest.setHaghBimePoosheshhayeEzafi(NumberFormat.getNumberInstance().format(report.getHaghBimePusheshHaayeEzaafi()));
                                ghest.setMaliat(NumberFormat.getNumberInstance().format(report.getMaliat()));
                                ghest.setHazineBimegari(NumberFormat.getNumberInstance().format(Math.round(report.getHazineBimeGari())));
                                ghest.setHazineEdari(NumberFormat.getNumberInstance().format(Math.round(report.getHazineEdari())));
                                ghest.setHazineKarmonz(NumberFormat.getNumberInstance().format(Math.round(report.getKaarmozd())));
                                ghest.setHazineVosoul(NumberFormat.getNumberInstance().format(Math.round(report.getHazineVosul())));
                                ghest.setSarmayeFotBeHarEllat(NumberFormat.getNumberInstance().format(Math.round(report.getSarmaayeFotBehHarEllat())));
                                ghest.setSarmayeFotDarAsarHadeseh(NumberFormat.getNumberInstance().format(Math.round(report.getSarmaayeFotDarAsarHaadeseh())));
                                ghest.setSarmayePoosheshEzafiAmraazKhaas(NumberFormat.getNumberInstance().format(Math.round(report.getSarmaayePusheshEzaafiAmraazKhaas())));
                                ghest.setHazineTaghsit(NumberFormat.getNumberInstance().format(report.getZaribNahveyePardakht()).substring(0, Math.min(5, NumberFormat.getNumberInstance().format(report.getZaribNahveyePardakht()).length())));
                                ghest.setKarmozdAmount(NumberFormat.getNumberInstance().format(report.getKaarmozd()));
                                ghest.setHaghBimePoosheshAmraz(NumberFormat.getNumberInstance().format(Math.round(report.getHaghBimePoosheshAmraz())));
                                ghest.setHaghBimePoosheshHadese(NumberFormat.getNumberInstance().format(Math.round(report.getHaghBimePoosheshHadese())));
                                ghest.setHaghBimePoosheshNaghsOzv(NumberFormat.getNumberInstance().format(Math.round(report.getHaghBimePoosheshNaghsOzv())));
                                ghest.setHaghBimePoosheshMoafiat(NumberFormat.getNumberInstance().format(Math.round(report.getHaghBimePoosheshMoafiat())));
                                Long ghestAmount = (long) report.getHaghBimePardaakhtiSaal();
                                Credebit credebit = new Credebit(Long.toString(ghestAmount), sequenceService.nextShenaseCredebit(), pishnehad.getBimename(), pishnehad, Credebit.CredebitType.GHEST);
                                credebit.setAmount_long(ghestAmount);
                                credebit.setRemainingAmount_long(ghestAmount);
                                credebit.setGhest(ghest);
                                credebit.setShomareMoshtari(sequenceService.generateNextShomareMoshtari(pishnehad.getKarshenas().getMojtamaSodoor(), "9", "120",ghestAmount));
                                ghest.setCredebit(credebit);
                                ghest.setKarmozdVosuli(getKarmozdVosul(pishnehad.getBimename().getTarikhSodour(), pishnehad.getTarh(), ghestAmount.longValue(), new Double(report.getMaliat()).longValue(), new Double(report.getMajmooHaghBimePoosheshhayeEzafi()).longValue(), false, null));
                                ghest.setKarmozdPoosheshEzafi(getKarmozdPoosheshEzafi(new Double(report.getHaghBimePusheshHaayeEzaafi()).longValue(), pishnehad.getTarh(), pishnehad.getBimename().getTarikhSodour()));

                                ghestList.add(ghest);
                                credebitList.add(credebit);

                                deletedGhestList.add(g);
                                deletedCredebitList.add(g.getCredebit());
                            }
                        }

                    }
                    //aghsate atie pardakht shode
                    else if(g.getCredebit().getRemainingAmount_long()<(g.getCredebit().getAmount_long()))
                    {
                        if (theDarkhast.getKhesaratI().getKhesaratType().equals(Khesarat.KhesaratType.AMRAZ))
                        {
//                            Flow flow = new Flow(Double.parseDouble(g.getHaghBimePoosheshAmraz().replaceAll(",","").trim()), Flow.Type.HAZINEHA_MOSBAT, g.getSarresidDate());
//                            flowsHazineha.add(flow);
                            Credebit khonsaPosheshCredebit = new Credebit(g.getHaghBimePoosheshAmraz(), sequenceService.nextShenaseCredebit(),pishnehad.getBimename(), null, Credebit.CredebitType.TAGHIRAT_ESLAH_AGHSAT);
                            khonsaPosheshCredebit.setCredebitTypeDesc(Credebit.CredebitTypeDesc.KHONSA_KARDANE_POOSHESHHA);
                            khonsaPosheshCredebit.setDateFish(g.getSarresidDate());
                            asnadeSodorDAO.saveCredebit(khonsaPosheshCredebit);
                        }

                        if (theDarkhast.getKhesaratI().getKhesaratType().equals(Khesarat.KhesaratType.NAGHSOZV))
                        {
//                            Flow flow = new Flow(Double.parseDouble(g.getHaghBimePoosheshNaghsOzv().replaceAll(",", "").trim()), Flow.Type.HAZINEHA_MOSBAT, g.getSarresidDate());
//                            flowsHazineha.add(flow);
                            Credebit khonsaPosheshCredebit = new Credebit(g.getHaghBimePoosheshNaghsOzv(), sequenceService.nextShenaseCredebit(),pishnehad.getBimename(), null, Credebit.CredebitType.TAGHIRAT_ESLAH_AGHSAT);
                            khonsaPosheshCredebit.setCredebitTypeDesc(Credebit.CredebitTypeDesc.KHONSA_KARDANE_POOSHESHHA);
                            khonsaPosheshCredebit.setDateFish(g.getSarresidDate());
                            asnadeSodorDAO.saveCredebit(khonsaPosheshCredebit);
                        }
                    }
                }

                asnadeSodorDAO.deleteBedehiList(deletedCredebitList);
//                asnadeSodorDAO.deleteAll(deletedGhestList);
//                asnadeSodorDAO.deleteAll(deletedGhestList);

                asnadeSodorDAO.saveOrUpdateAll(ghestList);
                asnadeSodorDAO.saveOrUpdateAll(credebitList);
            }
            if (DateUtil.isGreaterThan(DateUtil.getCurrentDate(),theDarkhast.getKhesaratI().getElhaghiye().getTarikhAsar()))// : if tarikhAsar < tarikhSodur . . .
            {
                for(Ghest gh : gb.getGhestList())
                {
                    if(DateUtil.betweenEq(gh.getSarresidDate(),theDarkhast.getKhesaratI().getElhaghiye().getTarikhAsar(),DateUtil.getCurrentDate())) // : khonsa kardane amraz o naghse ghesthaye mabeyne tarikhAsar && tarikhSodor
                    {
                        if (theDarkhast.getKhesaratI().getKhesaratType().equals(Khesarat.KhesaratType.AMRAZ))
                        {
                            Flow flow = new Flow(Double.parseDouble(gh.getHaghBimePoosheshAmraz().replaceAll(",","").trim()), Flow.Type.HAZINEHA_MOSBAT, gh.getSarresidDate());
                            flowsHazineha.add(flow);
                        }

                        if (theDarkhast.getKhesaratI().getKhesaratType().equals(Khesarat.KhesaratType.NAGHSOZV))
                        {
                            Flow flow = new Flow(Double.parseDouble(gh.getHaghBimePoosheshNaghsOzv().replaceAll(",", "").trim()), Flow.Type.HAZINEHA_MOSBAT, gh.getSarresidDate());
                            flowsHazineha.add(flow);
                        }
                    }
                }
            }
        }

        CashFlow hesabHazineha = new CashFlow();
        hesabHazineha.setFlows(flowsHazineha);
        //todo: hamishe 15% doroste?
        cashAmount = hesabHazineha.tajmi(null, DateUtil.getCurrentDate() , CashFlow.SOUD);
        if (cashAmount != 0)
        {
            Credebit khonsaPosheshCredebit = new Credebit(Long.toString(Math.round(cashAmount)), sequenceService.nextShenaseCredebit(),
                    pishnehad.getBimename(), null, Credebit.CredebitType.TAGHIRAT_ESLAH_AGHSAT);
            khonsaPosheshCredebit.setCredebitTypeDesc(Credebit.CredebitTypeDesc.KHONSA_KARDANE_POOSHESHHA );
            khonsaPosheshCredebit.setDateFish(DateUtil.getCurrentDate());
            asnadeSodorDAO.saveCredebit(khonsaPosheshCredebit);
        }
        return cashAmount;
    }
    public long hazfHaghbimePoosheshEzafi(DarkhastBazkharid theDarkhast)
    {
        int accidentBimeYear = DateUtil.getBimeYear(theDarkhast.getKhesaratI().getAccidentDate(), theDarkhast.getBimename().getTarikhShorou());
        long returnValue = 0;
        for (GhestBandi gb : theDarkhast.getBimename().getGhestBandiList())
        {
            if (gb.getSaleBimeiInt() > accidentBimeYear)
            {
                for (Ghest g : gb.getGhestList())
                {
                    if (theDarkhast.getKhesaratI().getKhesaratType().equals(Khesarat.KhesaratType.AMRAZ))
                    {
                        // todo: majmoohaye ghestbandi update nemishavand, bayad taklife karmozd malum she ke bad az elhaghie khesarat karmozd che taghiri mikonad
                        returnValue += Long.parseLong(g.getHaghBimePoosheshAmraz().replaceAll(",", ""));
                        g.setHaghBimePoosheshAmraz("0");
                        g.setHaghBimePoosheshhayeEzafiLong(Long.parseLong(g.getHaghBimePoosheshAmraz().replaceAll(",", ""))
                                + Long.parseLong(g.getHaghBimePoosheshHadese().replaceAll(",", ""))
                                + Long.parseLong(g.getHaghBimePoosheshMoafiat().replaceAll(",", ""))
                                + Long.parseLong(g.getHaghBimePoosheshNaghsOzv().replaceAll(",", "")));
                        updateGhest(g);
                    }
                    if (theDarkhast.getKhesaratI().getKhesaratType().equals(Khesarat.KhesaratType.NAGHSOZV))
                    {
                        returnValue += Long.parseLong(g.getHaghBimePoosheshNaghsOzv().replaceAll(",", ""));
                        g.setHaghBimePoosheshNaghsOzv("0");
                        g.setHaghBimePoosheshhayeEzafiLong(Long.parseLong(g.getHaghBimePoosheshAmraz().replaceAll(",", ""))
                                + Long.parseLong(g.getHaghBimePoosheshHadese().replaceAll(",", ""))
                                + Long.parseLong(g.getHaghBimePoosheshMoafiat().replaceAll(",", ""))
                                + Long.parseLong(g.getHaghBimePoosheshNaghsOzv().replaceAll(",", "")));
                        updateGhest(g);
                    }
                }
            }
        }
        return returnValue;
    }

    public List<Credebit> findAllCredebits(boolean sanadKhorde)
    {
        return asnadeSodorDAO.findAllCredebitsBySanadKhordan(sanadKhorde);
    }

    public PaginatedListImpl<AghsatMoavagh> findAllAghsatMoavagh(AghsatMoavagh aghsatMoavagh)
    {
//        final PaginatedListImpl<Ghest> ghests = asnadeSodorDAO.findAllGhestMoavagh(aghsatMoavagh);
        final PaginatedListImpl<AghsatMoavagh> ghests = asnadeSodorDAO.findAllGhestMoavagh(aghsatMoavagh);
        PaginatedListImpl<AghsatMoavagh> aghsatMoavaghListPaginated = new PaginatedListImpl<AghsatMoavagh>();
        aghsatMoavaghListPaginated.setFullListSize(ghests.getFullListSize());
        aghsatMoavaghListPaginated.setObjectsPerPage(ghests.getObjectsPerPage());
        aghsatMoavaghListPaginated.setPageNumber(ghests.getPageNumber());
        List<AghsatMoavagh> aghsatMoavaghList = new ArrayList<AghsatMoavagh>();
//        List<Ghest> ghestList = ghests.getList();
        List<AghsatMoavagh> ghestList = ghests.getList();
//        for (Ghest g : ghestList) {
        for (AghsatMoavagh ag : ghestList)
        {
//            AghsatMoavagh ag = new AghsatMoavagh();
//            ag.setGhest(g);
//            if(g.getCredebit().getBimename() == null){
//                ag.setPishnehad(g.getCredebit().getPishnehad());
//            }else{
//                ag.setPishnehad(g.getCredebit().getBimename().getPishnehad());
//            }
//            ag.setMablaghGhest(NumberFormat.getNumberInstance().format(Long.parseLong(g.getCredebit().getAmount().replace(",", "").trim())));
//            ag.setTarikhSarresidGhest(g.getSarresidDate());
//            ag.setMandeGhest(NumberFormat.getNumberInstance().format(Long.parseLong(g.getCredebit().getRemainingAmount().replace(",", "").trim())));
//            final List<Ghest> _ghests2 = asnadeSodorService.findAllBedehisByPishnehadId(ag.getPishnehad().getId());
//            long tmp = 0;
//            long tmp2 = 0;
//            for (Ghest _g2 : _ghests2) {
//                Long val = Long.parseLong(_g2.getCredebit().getAmount().replace(",", "").trim());
//                tmp += val;
//                if(_g2.getGhestBandi().getSaleBimei().equals("0")){
//                    tmp2+=val;
//                }
//            }
            Bimename bimename = pishnehadService.findBimenameByShomare(ag.getShomareBimename());
//            long tmp = getJameSadere(ag.getPishnehad().getBimename().getId());
//            long tmp2 = getHagheBimeElamBeMaliSaleAvval(ag.getPishnehad().getBimename().getId());
            long tmp = getJameSadere(bimename.getId());
            long tmp2 = getHagheBimeElamBeMaliSaleAvval(bimename.getId());
            ag.setJamSadere(NumberFormat.getNumberInstance().format(tmp));
            ag.setHagheBimeElamBeMaliSaleAvval(NumberFormat.getNumberInstance().format(tmp2));
            aghsatMoavaghList.add(ag);
        }
        aghsatMoavaghListPaginated.setList(aghsatMoavaghList);
        return aghsatMoavaghListPaginated;
    }

    public long getJameSadere(Integer bimenameId)
    {
        return asnadeSodorDAO.getJameSadere(bimenameId);
    }

    public long getJameSadereForGhestbandi(Integer ghestbandiId)
    {
        return asnadeSodorDAO.getJameSadereForGhestbandi(ghestbandiId);
    }

    public long getJamePoosheshAsliSadere(Integer bimenameId)
    {
        return asnadeSodorDAO.getJamePoosheshAsliSadere(bimenameId);
    }

    public long getJamePoosheshhayeEzafiSadere(Integer bimenameId)
    {
        return asnadeSodorDAO.getJamePoosheshhayeEzafiSadere(bimenameId);
    }

    public long getHagheBimeElamBeMaliSaleAvval(Integer bimenameId)
    {
        return asnadeSodorDAO.getHagheBimeElamBeMaliSaleAvval(bimenameId);
    }

    public List<Ghest> findAllGhestForBimenameAndKarmozd(Bimename bimename)
    {
        return asnadeSodorDAO.findAllGhestForBimenameAndKarmozd(bimename);
    }

    public List<Ghest> findGhests(Integer ghestBandi, Credebit.CredebitType ghest)
    {
        return asnadeSodorDAO.findGhests(ghestBandi, ghest);
    }

    public static double getNerkhBahreFanni(String tarikhSodour, Tarh tarh)
    {
        return getNerkhBahreFanni(tarikhSodour, new Constants(), tarh);
    }

    public static double getNerkhPusheshKhatarNaghsOzv(String date, int tabagheKhatar, Tarh tarh)
    {
        switch (tabagheKhatar)
        {
            case 1:
                return Double.valueOf(getConstantKey(date, new Constants(), Constants.Keys.NERKHEPUSHESHENAGHSOZV, Constants.KeysParam.MEGHDARNERKHKHATARTABAGHE_1, tarh).replace(",", "").trim());
            case 2:
                return Double.valueOf(getConstantKey(date, new Constants(), Constants.Keys.NERKHEPUSHESHENAGHSOZV, Constants.KeysParam.MEGHDARNERKHKHATARTABAGHE_2, tarh).replace(",", "").trim());
            case 3:
                return Double.valueOf(getConstantKey(date, new Constants(), Constants.Keys.NERKHEPUSHESHENAGHSOZV, Constants.KeysParam.MEGHDARNERKHKHATARTABAGHE_3, tarh).replace(",", "").trim());
            case 4:
                return Double.valueOf(getConstantKey(date, new Constants(), Constants.Keys.NERKHEPUSHESHENAGHSOZV, Constants.KeysParam.MEGHDARNERKHKHATARTABAGHE_4, tarh).replace(",", "").trim());
            default:
                return Double.valueOf(getConstantKey(date, new Constants(), Constants.Keys.NERKHEPUSHESHENAGHSOZV, Constants.KeysParam.MEGHDARNERKHKHATARTABAGHE_5, tarh).replace(",", "").trim());

        }

    }

    public Credebit getCredebitByShenase(String shenase, String sub)
    {
        return asnadeSodorDAO.getCredebitByShenase(shenase, sub);
    }

    public List<Credebit> getCredebitByTypeForBimename(Credebit.CredebitType type, String shomareBimename)
    {
        return asnadeSodorDAO.getCredebitByTypeForBimename(type, shomareBimename);
    }

    public Credebit getCredebitByShomareMoshtari(String shenase, String sub)
    {
        return asnadeSodorDAO.getCredebitByShomareMoshtari(shenase, sub);
    }

    public int getRemainingByIdentifier(String identifer, String sub)
    {
        return asnadeSodorDAO.getRemainingByIdentifier(identifer, sub);
    }

    public Credebit getCredebitByUniqueCode(String uniqueCode, String sub)
    {
        return asnadeSodorDAO.getCredebitByUniqueCode(uniqueCode, sub);
    }

    public void reverseKhateSanadList(List<KhateSanad> khateSanadList)
    {
        for (KhateSanad khateSanad : khateSanadList)
        {
            List<KarmozdGhest> karmozdGhestList = khateSanad.getKarmozdGhestList();
            if (karmozdGhestList.size() != 0) return;
            Credebit etebarCredebit = khateSanad.getEtebarCredebit();
//            todo : Navid This is a temporary option. We should add amount of khatesanad to etebarCredebit later.
            etebarCredebit.setRemainingAmount(etebarCredebit.getAmount());
//            todo : Also This Line Needs Revision.
            etebarCredebit.setStatus(Credebit.Status.SANAD_NA_KHORDE);
            asnadeSodorDAO.updateCredebit(etebarCredebit);
            Sanad sanad = khateSanad.getSanad();
            for (KarmozdGhest karmozdGhest : karmozdGhestList)
            {
                if (karmozdGhest != null)
                {
                    asnadeSodorDAO.delete(karmozdGhest);
                }
            }
            if (sanad.getKhateSanadSet().size() == 1)
            {
                asnadeSodorDAO.delete(khateSanad);
                asnadeSodorDAO.delete(sanad);
            }
            else
            {
                asnadeSodorDAO.delete(khateSanad);
            }
        }
    }

    public void setAndukhteAndArzeshBazkharid(Bimename bimename, String date)
    {
        CashFlow cashFlow = asnadeSodorDAO.getCashFlowForBimename(bimename, date);
        long alalhesab = Math.round(cashFlow.tajmi(bimename, date, CashFlow.SOUD));
        bimename.setAndukhteyeGhatie(alalhesab + calcSoudMajmuMosharekat(bimename, date));
        bimename.setAndukhteyeAlalHesab(alalhesab);
        TaeenAndukhteBarMabnaayeHaghBimePardaakhti taeenAndukhteBarMabnaayeHaghBimePardaakhti = new TaeenAndukhteBarMabnaayeHaghBimePardaakhti();
        int currentBimeYear = DateUtil.getBimeYear(date, bimename.getTarikhShorou());
        long sumHazineBimegari = 0L;
        for (int i = currentBimeYear + 1; i < Short.parseShort(bimename.getPishnehad().getEstelam().getModat_bimename()); i++)
        {
            double bimegari = taeenAndukhteBarMabnaayeHaghBimePardaakhti.mohaasebeHazineBimeGari(i, bimename.getPishnehad().getEstelam().isYekja(),
                    bimename.getTarikhShorou(), bimename.getPishnehad().getTarh(), bimename.getFirstPishnehadWithElhaghie().getEstelam().getSarmaye_paye_fot_long(),
                    Long.parseLong(bimename.getPishnehad().getEstelam().getMablagh_seporde_ebtedaye_sal().replaceAll(",", "")));
            if (bimegari > 0)
            {
                sumHazineBimegari += bimegari;
            }
            else
            {
                break;
            }
        }
        double zaribBazkharid = 1.0;
        if (currentBimeYear == 0)
        {
            zaribBazkharid = 0.9;
        }
        else if (currentBimeYear == 1)
        {
            zaribBazkharid = 0.92;
        }
        else if (currentBimeYear == 2)
        {
            zaribBazkharid = 0.94;
        }
        else if (currentBimeYear == 3)
        {
            zaribBazkharid = 0.96;
        }
        else if (currentBimeYear == 4) zaribBazkharid = 0.98;
        bimename.setArzeshBazkharidAlalHesab(Math.round(zaribBazkharid * (bimename.getAndukhteyeAlalHesab() - sumHazineBimegari)));
        bimename.setArzeshBazkharidGhatie(Math.round(zaribBazkharid * (bimename.getAndukhteyeGhatie() - sumHazineBimegari)));
    }

    public CashFlow getBimenameCashFlow(Bimename bimename, String date)
    {
        // todo: inja ro ham edit kon
        return bimename.getCashFlow(date);
    }

    private Long calcSoudMajmuMosharekat(Bimename bimename, String date)
    {
        CashFlow cashflow = new CashFlow();
        ArrayList<Flow> flows = new ArrayList<Flow>();
        for (Credebit c : bimename.getCredebitList())
        {
            if (c.getCredebitType() == Credebit.CredebitType.MOSHAREKAT && DateUtil.isGreaterThanOrEqual(date, c.getDateFish()))
            {
                Flow f = new Flow(Long.parseLong(c.getAmount().replaceAll(",", "")), Flow.Type.MOSHAREKAT, c.getDateFish());
                flows.add(f);
            }
        }
        cashflow.setFlows(flows);
        return Math.round(cashflow.tajmi(bimename, date, CashFlow.SOUD));
    }

    public List<EtebarBedehiVM> findCredebitbySubSystemIdentiferforBadane(String tarikheSodorFrom, String tarikheSodorTo, String vahedSodorId, User user)
    {
        return asnadeSodorDAO.findCredebitbySubSystemIdentiferforBadane(tarikheSodorFrom, tarikheSodorTo, vahedSodorId, user);
    }

    public List<EtebarBedehiVM> findCredebitbySubSystemIdentiferforcales(String tarikheSodorFrom, String tarikheSodorTo, String vahedSodorId, User user)
    {
        return asnadeSodorDAO.findCredebitbySubSystemIdentiferforcales(tarikheSodorFrom, tarikheSodorTo, vahedSodorId, user);
    }

    public static double getMaxMajmooeSenVaModat(String date, Tarh tarh)
    {
        return Double.valueOf(getConstantKey(date, new Constants(), Constants.Keys.VALIDATION, Constants.KeysParam.MAXMAJMOOMODATVASEN, tarh));
    }

    public static double getMinSeneBimeShode(String date, Tarh tarh)
    {
        return Double.valueOf(getConstantKey(date, new Constants(), Constants.Keys.VALIDATION, Constants.KeysParam.MINSENEBIMESHODE, tarh));
    }

    public static double getMaxSeneBimeShode(String date, Tarh tarh)
    {
        return Double.valueOf(getConstantKey(date, new Constants(), Constants.Keys.VALIDATION, Constants.KeysParam.MAXSENEBIMESHODE, tarh));
    }

    public static String getDarsadMojazeAfzayeshSarmayeFot(String date, Tarh tarh)
    {
        return getConstantKey(date, new Constants(), Constants.Keys.VALIDATION, Constants.KeysParam.DARSAD_MOJAZ_AFZAYESH_SARMAYE_FOT, tarh);
    }

    public static String getMahdodeyeSarmayeFot(String date, Tarh tarh)
    {
        return getConstantKey(date, new Constants(), Constants.Keys.VALIDATION, Constants.KeysParam.MAHDODE_SARMAYE_PAYE_FOT, tarh);
    }

    public static String getPosheshFotDarAsarHadese(String date, Tarh tarh)
    {
        return getConstantKey(date, new Constants(), Constants.Keys.VALIDATION, Constants.KeysParam.POSHESHFOTDARASARHADESE, tarh);
    }

    public static String getPosheseNaghzOzv(String date, Tarh tarh)
    {
        return getConstantKey(date, new Constants(), Constants.Keys.VALIDATION, Constants.KeysParam.POSHESENAGHZOZV, tarh);
    }

    public static String getPoshesheAmrazeKhas(String date, Tarh tarh)
    {
        return getConstantKey(date, new Constants(), Constants.Keys.VALIDATION, Constants.KeysParam.POSHESHAMRAZEKHAS, tarh);
    }

    public static String getPoshesheMoafiyat(String date, Tarh tarh)
    {
        return getConstantKey(date, new Constants(), Constants.Keys.VALIDATION, Constants.KeysParam.POSHESHMOAFIYAT, tarh);
    }

    public static String getMinHaghBimePardakhtiMah(String date, Tarh tarh)
    {
        return getConstantKey(date, new Constants(), Constants.Keys.VALIDATION, Constants.KeysParam.MINHAGHBIMEPARDAKHTIMAH, tarh);
    }

    public static HashMap<String, Object> getValidationConstantsSet(Tarh tarh, String date)
    {
        HashMap<String, Object> validationMap = new HashMap<String, Object>();
        validationMap.put("DARSADMOJAZEAFZAYESHESARMAYEFOT", getDarsadMojazeAfzayeshSarmayeFot(date, tarh));
        validationMap.put("MAXMAJMOOMODATVASEN", getMaxMajmooeSenVaModat(date, tarh));
        validationMap.put("MINSENEBIMESHODE", getMinSeneBimeShode(date, tarh));
        validationMap.put("MAXSENEBIMESHODE", getMaxSeneBimeShode(date, tarh));
        validationMap.put("MAHDODEYEPAYESARMAYEFOT", getMahdodeyeSarmayeFot(date, tarh));
        validationMap.put("POSHESHFOTDARASARHADESE", getPosheshFotDarAsarHadese(date, tarh));
        validationMap.put("POSHESENAGHZOZV", getPosheseNaghzOzv(date, tarh));
        validationMap.put("POSHESHEAMRAZKHAS", getPoshesheAmrazeKhas(date, tarh));
        validationMap.put("POSHESHEMOAFIYAT", getPoshesheMoafiyat(date, tarh));
        validationMap.put("MINHAGHBIMEPARDAKHTIMAH", getMinHaghBimePardakhtiMah(date, tarh));
        return validationMap;
    }

    public static double getDarsadKarmozdHaghBime(String date, Tarh tarh)
    {
        return Double.valueOf(getConstantKey(date, new Constants(), Constants.Keys.HAZINEKARMOZD, Constants.KeysParam.DARSAD_KASRI_HAGH_BIME, tarh).replace(",", "").trim());
    }

    public String updateArzesheBimename(String date, double soud, Integer bimenameId)
    {
        return asnadeSodorDAO.updateArzesheBimename(date, soud, bimenameId);
    }

    public String updateArzesheBimenameTajmi(String date, double soud, List<Integer> bimenameIds)
    {
        return asnadeSodorDAO.updateArzesheBimenameTajmi(date, soud, bimenameIds);
    }

    public String updateMohasebeArzeshBazkharidi(String date, Integer bimenameId)
    {
        return asnadeSodorDAO.updateMohasebeArzeshBazkharidi(date, bimenameId);
    }

    public String updateMohasebeArzeshBazkharidiTajmi(String date, List<Integer> bimenameIds)
    {
        return asnadeSodorDAO.updateMohasebeArzeshBazkharidiTajmi(date, bimenameIds);
    }

    public String updateAndukhte(String date, double soud, Integer bimenameId) throws SQLException
    {
        return asnadeSodorDAO.updateAndukhte(date, soud, bimenameId);
    }

    public String updateAndukhteTajmi(String date, List<ZakhireRiaziVM> bimenameIds) throws SQLException
    {
        return asnadeSodorDAO.updateAndukhteTajmi(date, bimenameIds);
    }

    public String updateAndukhteTajmi(String date1, String date2, double soud, List<Integer> bimenameIds) throws SQLException
    {
        return asnadeSodorDAO.updateAndukhteTajmi(date1, date2, soud, bimenameIds);
    }

    public Credebit getCredebitByShomareMoshtari(String shomareMoshtari)
    {
        return asnadeSodorDAO.getCredebitByShomareMoshtari(shomareMoshtari);
    }

    public Credebit findEtebarCredebitsByCodeMoshtari(String codeMoshtari, String amount)
    {
        return asnadeSodorDAO.findEtebarCredebitsByCodeMoshtari(codeMoshtari, amount);
    }

    public Credebit findEtebarCredebitsByCodeMoshtariAndTypeOfSystem(String typeOfSystem, String codeMoshtari)
    {
        return asnadeSodorDAO.findEtebarCredebitsByCodeMoshtariAndTypeOfSystem(typeOfSystem, codeMoshtari);
    }

    public Credebit findBedehiCredebitsByCodeMoshtari(String codeMoshtari, String amount)
    {
        return asnadeSodorDAO.findBedehiCredebitsByCodeMoshtari(codeMoshtari, amount);
    }

    public List<Ghest> findGhestByRemainingAndSareresid()
    {
        return asnadeSodorDAO.findGhestByRemainingAndSareresid();
    }

    public List<Bimename> findBimenameByGhest(List<Ghest> ghestList)
    {
        return asnadeSodorDAO.findBimenameByGhest(ghestList);
    }

    public Shakhs findShakhsByBimename(Bimename bimename)
    {
        return asnadeSodorDAO.findShakhsByBimename(bimename);
    }

    public Map<Integer, List<String>> findInformationGhest10DayBeforeForSMS()
    {
        return asnadeSodorDAO.findInformationGhest10DayBeforeForSMS();
    }

    public Map<Integer, List<String>> findInformationGhest20DayAfterForSMS()
    {
        return asnadeSodorDAO.findInformationGhest20DayAfterForSMS();
    }

    public Long getRemainingAmountBedehiSanadNakhorde(Long namayandeId)
    {
        return asnadeSodorDAO.getRemainingAmountBedehiSanadNakhorde(namayandeId);
    }

    public Long getRemainingAmountEtebarSanadKhordeVosulNashode(Long namayandeId, String etebarType)
    {
        return asnadeSodorDAO.getRemainingAmountEtebarSanadKhordeVosulNashode(namayandeId, etebarType);
    }

    public PaginatedListImpl<Credebit> findAllCheck(String shomareHesab, String shomareCheck, String sarresidDate, String checkSerri, String rcptName, String branchName, String branchCode, String accountOwnerName, String amount, User user)
    {
        return asnadeSodorDAO.findAllCheck(shomareHesab, shomareCheck, sarresidDate, checkSerri, rcptName, branchName, branchCode, accountOwnerName, amount, user);
    }

    public PaginatedListImpl<Vagozari> findListVaghozariCredebitPaginated(User user)
    {
        return asnadeSodorDAO.findListVaghozariCredebitPaginated(user);
    }

    public Integer getVagozariPerCredebit(Integer vagozariId)
    {
        return asnadeSodorDAO.getVagozariPerCredebit(vagozariId);
    }

    public List<Credebit> findCheckByVagozari(Integer vagozariId)
    {
        return asnadeSodorDAO.findCheckByVagozari(vagozariId);
    }

    public Vagozari findVagozariById(Integer vagozariId)
    {
        return asnadeSodorDAO.findVagozari(vagozariId);
    }

    public PaginatedListImpl<BatchTaghsitVM> findBatchTaghsit(PaginatedListImpl<BatchTaghsitVM> pgList, BatchTaghsitVM searchObj)
    {
        return asnadeSodorDAO.findBatchTaghsit(pgList, searchObj);
    }

    public Integer countAghsatePardakhti(Integer bimenameId)
    {
        return asnadeSodorDAO.countAghsatePardakhti(bimenameId);
    }

    public List<GhestBandi> findGhestBandiListForPrintDaftarche(BatchTaghsitVM searchObj)
    {
        return asnadeSodorDAO.findGhestBandiListForPrintDaftarche(searchObj);
    }

    public void saveLogPrintDaftarche(LogPrintDaftarche log)
    {
        asnadeSodorDAO.save(log);
    }

    public List<LogPrintDaftarche> findAllLogPrinteDaftarcheByGhestBandiId(Integer id)
    {
        GhestBandi gb = findGhestBandiById(id);
        return asnadeSodorDAO.findByProperty(LogPrintDaftarche.class, "ghestBandi", gb);
    }

    public boolean validationDeleteEtebar(Integer credebitId, User user)
    {
        return asnadeSodorDAO.validationDeleteEtebar(credebitId, user);
    }

    public boolean validationDeleteBedehi(Integer credebitId)
    {
        return asnadeSodorDAO.validationDeleteBedehi(credebitId);
    }

    public void deleteCredebitById(Integer credebitId)
    {
        asnadeSodorDAO.deleteCredebitById(credebitId);
    }

    public void deleteDaryaftFishById(Integer daryaftFishId)
    {
        asnadeSodorDAO.deleteDaryaftFishById(daryaftFishId);
    }

    public boolean deleteSanad(Integer sanadId)
    {
        karmozdService.disjoinKarmozdsAndCreateTadilat(sanadId);
        return asnadeSodorDAO.deleteSanad(sanadId);
    }


    public Credebit findAnyCredebitByCodeMoshtari(String codeMoshtari)
    {
        return asnadeSodorDAO.findAnyCredebitByCodeMoshtari(codeMoshtari);
    }

    public Credebit findPardakhtShenaseDarCredebitByCodeMoshtari(String codeMoshtari)
    {
        return asnadeSodorDAO.findPardakhtShenaseDarCredebitByCodeMoshtari(codeMoshtari);
    }

    public Credebit executeCredebitFileUpload(Credebit credebit, BankInfo bankInfo, Long amountParamLong)
    {
        return asnadeSodorDAO.executeCredebitFileUpload(credebit, bankInfo, amountParamLong);
    }

    public int getTatilatFromTwoDate(String fromDate, String toDate)
    {
        return asnadeSodorDAO.getTatilatFromTwoDate(fromDate, toDate);
    }

    public Long getHaghBimePayePardakhtiSaalAvval(Integer bimenameId)
    {
        return asnadeSodorDAO.getHaghBimePayePardakhtiSaalAvval(bimenameId);
    }

    public Long getKoleHagheBimePardakhtiSaalAvval(Integer bimenameId)
    {
        return asnadeSodorDAO.getKoleHagheBimePardakhtiSaalAvval(bimenameId);
    }

    public String hqlToSql(String hql)
    {
        return asnadeSodorDAO.hqlToSql(hql);
    }

    public boolean isAvailableAuthorityId(String authority)
    {
        return asnadeSodorDAO.isAvailableAuthorityId(authority);
    }

    public List<CredebitBedehiVM> findAllBedehiCredebits(String bimegozarName, String sodorbimenameDateFrom, String sodorbimenameDateTo, String namayandeId, String pishShomareBimename, User user)
    {
        return asnadeSodorDAO.findAllBedehiCredebits(bimegozarName, sodorbimenameDateFrom, sodorbimenameDateTo, namayandeId, pishShomareBimename, user);
    }

    public void setKarmozdService(IKarmozdService karmozdService)
    {
        this.karmozdService = karmozdService;
    }
    public BazarYabSanam getBazarYabByCode(Long bazaryabCode)
    {
       return asnadeSodorDAO.getBazarYabByCode(bazaryabCode);
    }
    public void saveBAzarYabSanam(BazarYabSanam  bazarYabSanam)
    {
        asnadeSodorDAO.saveBAzarYabSanam(bazarYabSanam);
    }

    public Long getAmountCheckSanadKhordeVagozarNashode(Long namayandeId) {

        return asnadeSodorDAO.getAmountCheckSanadKhordeVagozarNashode(namayandeId);
    }
    public List<Integer> hazfeSanad(String created_date, Credebit.CredebitType type)
    {
        return asnadeSodorDAO.hazfeSanad(created_date, type);
    }
    //b-h
    public Credebit findCredebitDaryafteCheckByCodeMoshtari(String codemoshtari){
        return asnadeSodorDAO.findCredebitDaryafteCheckByCodeMoshtari(codemoshtari);
    }
    //b-h
    public List<vaziateBedehiVaEtebar> estelamVaziateBedehiVaEtebar(String  uniqueCode,String subsystemName){
        return asnadeSodorDAO.estelamVaziateBedehiVaEtebar(uniqueCode,subsystemName);
    }
    //b-h
    public List<sooratVaziatMali_new> findBedehiByShomareBimeName(String shomarebimename,User user){
       return asnadeSodorDAO.findBedehiByShomareBimeName(shomarebimename,user);
    }

    //b-h
    public Credebit findAllCredebitsByCodeMoshtari(String codemoshtari,User user){
        return asnadeSodorDAO.findAllCredebitsByCodeMoshtari(codemoshtari,user);
    }
    //b-h
    public boolean deleteSanadForBankExcelFile(Integer sanadId){
        return asnadeSodorDAO.deleteSanad(sanadId);
    }
    //b-h
    public PaginatedListImpl<Credebit> findOnlyDaftarParsianCredebits(User user,PaginatedListImpl<Credebit> paginatedList,String identifier,String rcptName,String sarresidDateFrom,String sarresidDateTo,String createdDateFrom,String createdDateTo,Long search_namayandegiId,Long search_vahedesodorId,Long bazaryabSanamId){
        return asnadeSodorDAO.findOnlyDaftarParsianCredebits(user,paginatedList,identifier,rcptName,sarresidDateFrom,sarresidDateTo,createdDateFrom,createdDateTo,search_namayandegiId,search_vahedesodorId,bazaryabSanamId);
    }

    //b-h
    public Credebit enteghalBedehiBeDaftarNamayande(Credebit Parsianbedehi,Daftar daftar,Integer noeEnteghal){
        return asnadeSodorDAO.enteghalBedehiBeDaftarNamayande(Parsianbedehi,daftar,noeEnteghal);
    }
    //b-h
    public Daftar findDaftarIdByNamayandeName(Long namayandeId){
        return asnadeSodorDAO.findDaftarIdByName(namayandeId);
    }
    //b-h
    public Daftar findDaftarById(Integer id){
        return asnadeSodorDAO.findDaftarById(id);
    }
    //b-h
    public Daftar findDaftarByCodeNamayande(Long namayandeId){
        return asnadeSodorDAO.findDaftarByCodeNamayande(namayandeId);
    }
    //b-h
    public int tedadDaftareNamayande(Long namayandeID){
        return asnadeSodorDAO.tedadDaftareNamayande(namayandeID);
    }
    //b-h
    public PaginatedListImpl<bedehiTasviyeNashode> listBedehiTasviyeNashodeNamayande(int page,User user,String identifier,String rcptName,String sarresidDateFrom,String sarresidDateTo,String createdDateFrom,String createdDateTo,String amount,String remainingAmount,Long search_namayandegiId,Long search_vahedesodorId,Long bazaryabSanamId,String bedehiColor, int reshte, int consortium,  boolean isSearch){
        return asnadeSodorDAO.findbedehiTasviyeNashodeNamayande(page, user, identifier, rcptName, sarresidDateFrom, sarresidDateTo, createdDateFrom, createdDateTo, amount, remainingAmount, search_namayandegiId, search_vahedesodorId,bazaryabSanamId,bedehiColor, reshte, consortium,isSearch);
    }
    //b-h
    public List<sooratVaziatMali_new> findbedehiNamayandeForGozaresh(Long namayandeId,User user){
        return asnadeSodorDAO.findbedehiNamayandeForGozaresh(namayandeId,user);
    }

    public PaginatedListImpl<Motalebat> listMotalebatNamayande (int page , User usr , int field,Long namayandeId, String beginDate , String endDate , boolean searchPage){
        return asnadeSodorDAO.FindMotalebatNamayande(page,usr,field , namayandeId ,beginDate , endDate, searchPage);
    }
    public PaginatedListImpl<Motalebat> listMotalebatSal (int page , User usr , int field, Long namayandeId , boolean searchPage){
        return asnadeSodorDAO.FindMotalebatSal(page,usr,field, namayandeId , searchPage);
    }
}