package com.bitarts.parsian.service.implementation;

import com.bitarts.common.util.DateUtil;
import com.bitarts.common.util.StringUtil;
import com.bitarts.parsian.model.BazarYabSanam;
import com.bitarts.parsian.model.Daftar;
import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.asnadeSodor.*;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.constantItems.Constants;
import com.bitarts.parsian.model.estelam.Estelam;
import com.bitarts.parsian.service.*;
import com.bitarts.parsian.service.calculations.BimeNaamehVaSarmayehGozari;
import com.bitarts.parsian.service.calculations.Constants.NSPConstants;
import com.bitarts.parsian.service.calculations.Reports.FRs;
import com.bitarts.parsian.util.DPUtil;
import com.bitarts.parsian.viewModel.vaziateBedehiVaEtebar;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/8/11
 * Time: 4:20 PM
 */
@WebService(serviceName = "BitartsWebService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public class BitartsWebService implements IWebService {

    private IAsnadeSodorService asnadeSodorService;
    private IPishnehadService pishnehadService;
    private ISequenceService sequenceService;
    private INamayandeService namayandeService;
    private ILoginService loginService;

    @WebMethod(operationName = "createCredit")
    public synchronized String createCredit(Integer amount, String description, String rcptName, String sarresidDate, String subsystemName, String type, String identifier, String codeNamayande , String codeVahedeSodor, String uniqueCode, Integer mohlatSarResid, Long bazaryabCode, String bazaryabName) //etebar
    {
        // return values
        // 0,[shenase jadid ijad shode]
        // 1,[shenase tekrari mojod]
        // 2,[exception text]
        try {
            if (!(type.equals("GHEST") || type.equals("VEHICLE_HAGHBIME")
                 || type.equals("VEHICLE_HAGHBIME_BARGASHTI") || type.equals("DARMAN_HAGHBIME")
                    || type.equals("VEHICLE_KHESARAT") || type.equals("ETEBAR_DARMAN_KHANEVADE") || type.equals("DARMAN_ELHAGHIYE_BARGASHTI"))) {
                return "2, Invalid type";
            }
       /*     if(amount<=0){
                return "2, amount is not valid";
            }*/
            BazarYabSanam bazarYabSanam = null;

            String returnValue = sequenceService.nextShenaseCredebit();
            Credebit credebit = new Credebit(amount, description, rcptName, sarresidDate, subsystemName, type, identifier, returnValue, "");
            Namayande namayande = namayandeService.getNamayandeByKodeKargozar(codeNamayande);
            Namayande vahedeSodor = namayandeService.getNamayandeByKodeKargozar(codeVahedeSodor);
            bazarYabSanam = asnadeSodorService.getBazarYabByCode(bazaryabCode);
            if (bazarYabSanam == null)  //bazaryab vojood nadarad
            {
                bazarYabSanam = new BazarYabSanam(bazaryabCode , bazaryabName,vahedeSodor);
                asnadeSodorService.saveBAzarYabSanam(bazarYabSanam);
            }
            else if(bazarYabSanam.getName() == null || !bazarYabSanam.getName().equals(bazaryabName)) // if name is new update bazaryab
            {
                bazarYabSanam.setName(bazaryabName);
                asnadeSodorService.saveBAzarYabSanam(bazarYabSanam);
            }
            credebit.setBazarYabSanam(bazarYabSanam);

            credebit.setNamayande(namayande);
            credebit.setVahedeSodor(vahedeSodor);
            credebit.setUniqueCode(uniqueCode);
            credebit.setMohlatSarresid(mohlatSarResid);
            if(type.equals("VEHICLE_HAGHBIME_BARGASHTI") || type.equals("VEHICLE_KHESARAT")) {
                credebit.setVosoulState(Credebit.VaziyatVosoul.TAEED_VOSOUL);
                credebit.setVosoulDate(DateUtil.getCurrentDate());
            }
            //b-h
            Daftar daftarParsian=asnadeSodorService.findDaftarById(1);
            credebit.setDaftar(daftarParsian);
            Credebit bedehiNamayande=null;
            int tedadDaftar=asnadeSodorService.tedadDaftareNamayande(vahedeSodor.getId());
//            Boolean IsBedehi=false;
//            if(type.equals())
            if(tedadDaftar>1 ){
                bedehiNamayande=new Credebit(credebit);
                Daftar daftarNamayande=asnadeSodorService.findDaftarByCodeNamayande(vahedeSodor.getId());
                bedehiNamayande.setDaftar(daftarNamayande);
                bedehiNamayande.setDaftarParsian_credebit(credebit);
            }
            /// ta inja
            Credebit checkCredebit = asnadeSodorService.getCredebitByUniqueCode(uniqueCode, subsystemName);
            if (checkCredebit != null) {
                return "1," + checkCredebit.getShenaseCredebit();
            } else {
                asnadeSodorService.saveCredebit(credebit);
                //b-h
                if(bedehiNamayande !=null)
                    asnadeSodorService.saveCredebit(bedehiNamayande);
                return "0," + returnValue;
            }
        } catch(Exception ex) {
            return "2, " + ex.toString();
        }

    }

    @WebMethod(operationName = "createDebit")
    public synchronized String createDebit(Integer amount, String description, String rcptName, String sarresidDate, String subsystemName, String type, String identifier, String codeNamayande, String codeVahedeSodor, String uniqueCode, Integer mohlatSarResid, Long bazaryabCode, String bazaryabName)  //bedehi
    {
        return createCredit(amount, description, rcptName, sarresidDate, subsystemName, type, identifier, codeNamayande, codeVahedeSodor, uniqueCode, mohlatSarResid, bazaryabCode, bazaryabName );
    }
    @WebMethod(operationName = "amountOfShenasePardakhtSanam")
    public synchronized String amountOfShenasePardakhtSanam(String  input)
    {
        Credebit etebar = new Credebit();
        JSONObject output = new JSONObject();
        try
        {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(input);
            String shomareMoshtari = (String) obj.get("CustomerID");
            long typeOfSystem = Long.parseLong((String) obj.get("Type"));
            String mobileNumber = (String) obj.get("mobileNumber");
            String language = (String) obj.get("language");
            etebar = typeOfSystem == 2 ? asnadeSodorService.findPardakhtShenaseDarCredebitByCodeMoshtari(shomareMoshtari):
                    null;
            if (!shomareMoshtari.equalsIgnoreCase("") && (typeOfSystem == 1 || typeOfSystem == 2))
            {
//                if (typeOfSystem == 2)
//                {
//                    output.put("amount", "0");
//                    output.put("errorCode", "5");
//                    output.put("description", "پرداخت های غیر عمر غیر فعال اند");
//                }
                if (etebar != null && etebar.getRemainingAmount_long() == 0l && etebar.getVosoulState().equals(Credebit.VaziyatVosoul.TAEED_VOSOUL))
                {
                    output.put("amount", "0");
                    output.put("errorCode", "1");
                    output.put("description", "این شناسه قبلا پرداخت شده است");
                }

                else if (etebar == null)
                {
                    output.put("amount", "0");
                    output.put("errorCode", "2");
                    output.put("description", "شناسه پرداخت نامعتبر است");
                }
                else
//                if (etebar != null && etebar.getRemainingAmount_long() != 0l)
                {
                    output.put("amount", etebar.getRemainingAmount_long());
                    output.put("errorCode", "0");
                    output.put("description", "یال" + etebar.getRemainingAmount_long() + "مبلغ قابل پرداخت:");
                }
            }
            else
            {
                output.put("amount", "0");
                output.put("errorCode", "4");
                output.put("description", "ورودی نا معتبر است");
            }

        }
        catch (ParseException e)
        {
            output.put("amount", "0");
            output.put("errorCode", "4");
            output.put("description", "ورودی نا معتبر است");
            e.printStackTrace();
            //To change body of catch statement use File | Settings | File Templates.

        }
        return output.toString();

    }

    @WebMethod(operationName = "amountOfShenasePardakht")
    public synchronized String amountOfShenasePardakht(String  input) {
        Credebit etebar = new Credebit();
        JSONObject output = new JSONObject();
        try{
            JSONParser parser = new JSONParser();
            JSONObject obj =(JSONObject) parser.parse(input);
            String shomareMoshtari=(String) obj.get("customerCode");
            long typeOfSystem=Long.parseLong((String)obj.get("typeOfSystem"));
            String mobileNumber=(String) obj.get("mobileNumber");
            String language=(String) obj.get("language");
            etebar = typeOfSystem == 1 ? asnadeSodorService.findEtebarCredebitsByCodeMoshtariAndTypeOfSystem("OMR", shomareMoshtari):
                                         null;
            if(!shomareMoshtari.equalsIgnoreCase("") && (typeOfSystem==1 ||typeOfSystem==2) )  {
                if( typeOfSystem==2)  {
                    output.put("amount","0") ;
                    output.put("errorCode","5");
                    output.put("description","پرداخت های غیر عمر غیر فعال اند");
                }
                if(etebar!=null && etebar.getRemainingAmount_long()==0l)  {
                    output.put("amount","0") ;
                    output.put("errorCode","1");
                    output.put("description","این شناسه قبلا پرداخت شده است") ;
                } else if(etebar!=null && etebar.getRemainingAmount_long()!=0l)  {
                    output.put("amount",etebar.getRemainingAmount_long()) ;
                    output.put("errorCode","0");
                    output.put("description","یال"+etebar.getRemainingAmount_long()+"مبلغ قابل پرداخت:") ;
                }else if(etebar==null) {
                    output.put("amount","0") ;
                    output.put("errorCode","2");
                    output.put("description","شناسه پرداخت نامعتبر است") ;
                }
            }else  {
                output.put("amount","0") ;
                output.put("errorCode","4");
                output.put("description","ورودی نا معتبر است") ;
            }

        }    catch (ParseException e) {
            output.put("amount","0") ;
            output.put("errorCode","4");
            output.put("description","ورودی نا معتبر است") ;
            e.printStackTrace();
            //To change body of catch statement use File | Settings | File Templates.

        }
        return output.toString();
    }

    @WebMethod(operationName = "getGhestForUSSD")
    public String getGhestForUSSD(String shomareBimename)
    {
        Bimename bimename =pishnehadService.findBimenameByShomare(shomareBimename);
        if(bimename==null)return "N/A";
        List<Credebit> credebitList = new ArrayList<Credebit>();
        for(Credebit credebit : bimename.getCredebitList())
        {
            if(credebit.getRemainingAmount_long()>0)
            {
                credebitList.add(credebit);
            }
        }
        Collections.sort
        (
           credebitList,
           new Comparator<Credebit>()
           {
                public int compare(Credebit cr1, Credebit cr2)
                {
                    return cr1.getGhest().getSarresidDate().compareTo(cr2.getGhest().getSarresidDate());
                }
           }
        );
        String ghestListStr="";
        for(Credebit credebit : credebitList)
        {
            if(credebit.getCredebitType().equals(Credebit.CredebitType.GHEST)||credebit.getCredebitType().equals(Credebit.CredebitType.GHEST_VAM))
            {
                ghestListStr += credebit.getGhest().getSarresidDate()+",";
                ghestListStr += credebit.getAmount_long().toString()+",";
                ghestListStr += credebit.getRemainingAmount_long().toString()+",";
                ghestListStr += credebit.getShomareMoshtari()+",";
                ghestListStr += credebit.getCredebitType().equals(Credebit.CredebitType.GHEST)?"LIFE":"LOAN"+"|";
            }
        }
        return ghestListStr;
    }

    @WebMethod(operationName = "getGhestForUSSDSanam")
    public String getGhestForUSSDSanam(String codeNamayande)
    {
        Namayande namayande = namayandeService.getNamayandeByKodeKargozar(codeNamayande);
        if(namayande==null)return "error";
        List<Credebit> credebitList = new ArrayList<Credebit>();
        credebitList.addAll(asnadeSodorService.credebitsShenaseDarVosulNashode(namayande.getId(), Credebit.VaziyatVosoul.TAEED_NASHODE));

//        Collections.sort
//        (
//           credebitList,
//           new Comparator<Credebit>()
//           {
//                public int compare(Credebit cr1, Credebit cr2)
//                {
//                    return cr1.getGhest().getSarresidDate().compareTo(cr2.getGhest().getSarresidDate());
//                }
//           }
//        );
        String credebitListStr="";
        for(Credebit credebit : credebitList)
        {
//            if(credebit.getCredebitType().equals(Credebit.CredebitType.GHEST)||credebit.getCredebitType().equals(Credebit.CredebitType.GHEST_VAM))
//            {
                credebitListStr += credebit.getSarresidDate()+",";
                credebitListStr += credebit.getAmount_long().toString()+",";
                credebitListStr += credebit.getRemainingAmount_long().toString()+",";
                credebitListStr += credebit.getShomareMoshtari()+",";
                credebitListStr += "PARDAKHT_SHENASEDAR"+"|";
//            }
        }
        return credebitListStr;
    }

    @WebMethod(operationName = "payByACH")
    public synchronized String payByACH(String subsystemName, String codeMoshtariEtebar, String shenaseEtebar, Integer amount, String description, String rcptName, String identifier, String achTrackId, String codeNamayande, String codeVahedeSodor, String uniqueCode) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = null;
            if (username != null)
            {
                user = loginService.findUserByUsername(username);
            }
            Credebit credebitBedehi = asnadeSodorService.getCredebitByShenase(shenaseEtebar, subsystemName);
            if(credebitBedehi.getRemainingAmount_long() <= 0)
                return codeMoshtariEtebar;
            if (identifier == null || (identifier != null && identifier.length() == 0))
                identifier = credebitBedehi.getIdentifier();
            Credebit credebitACH = new Credebit(amount, description, rcptName, null, subsystemName, "ACH", identifier, sequenceService.nextShenaseCredebit(), null);
            credebitACH.setRahgiriACH(achTrackId);
            credebitACH.setShomareMoshtari(codeMoshtariEtebar);
            Namayande namayande = namayandeService.getNamayandeByKodeKargozar(codeNamayande);
            credebitACH.setNamayande(namayande);
            Namayande vahedeSodor = namayandeService.getNamayandeByKodeKargozar(codeVahedeSodor);
            credebitACH.setVahedeSodor(vahedeSodor);
            credebitACH.setVahedeSodor(namayande);
            credebitACH.setUniqueCode(uniqueCode);
            Credebit checkCredebit = asnadeSodorService.getCredebitByUniqueCode(uniqueCode, subsystemName);
            if(checkCredebit != null)
                return checkCredebit.getShenaseCredebit();
            else {
                asnadeSodorService.saveCredebit(credebitACH);
            }
            Sanad sanad = asnadeSodorService.sabteSanad(user, credebitBedehi, credebitACH, Sanad.NoeSanad.PARDAKHT, Sanad.Vaziat.DAEM, true);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "ERROR";
        }
        return codeMoshtariEtebar;
    }

    @WebMethod(operationName = "payByCash")
    public synchronized String payByCash(String subsystemName, String payerName, Integer amount, String vosulDate, String description, String codeMoshtariEtebar, String shenaseBedehi, String codeNamayande, String codeVahedeSodor, String uniqueCode) {
        try {
            Credebit credebitBedehi = asnadeSodorService.getCredebitByShenase(shenaseBedehi, subsystemName);
            if (credebitBedehi.getRemainingAmount_long() <= 0)
                return codeMoshtariEtebar;
            Credebit credebit = new Credebit(amount, description, payerName, null, subsystemName, "CASH", credebitBedehi.getIdentifier(), sequenceService.nextShenaseCredebit(), codeMoshtariEtebar);
            credebit.setDateFish(vosulDate);
            credebit.setShomareMoshtari(codeMoshtariEtebar);
            Namayande namayande = namayandeService.getNamayandeByKodeKargozar(codeNamayande);
            credebit.setNamayande(namayande);
            Namayande vahedeSodor = namayandeService.getNamayandeByKodeKargozar(codeVahedeSodor);
            credebit.setVahedeSodor(vahedeSodor);
            credebit.setUniqueCode(uniqueCode);
            Credebit checkCredebit = asnadeSodorService.getCredebitByUniqueCode(uniqueCode, subsystemName);
            if(checkCredebit != null)
                return checkCredebit.getShenaseCredebit();
            else {
                asnadeSodorService.saveCredebit(credebit);
            }
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = null;
            if (username != null)
            {
                user = loginService.findUserByUsername(username);
            }
            Sanad sanad = asnadeSodorService.sabteSanad(user, credebitBedehi, credebit, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM, true);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "ERROR";
        }
        return codeMoshtariEtebar;
    }

    @WebMethod(operationName = "payByAzMahaleTablighat")
    public synchronized String payByAzMahaleTablighat(String subsystemName, String payeeName, Integer amount, String vosulDate, String description, String shenaseBedehi, String codeNamayande, String codeVahedeSodor, String uniqueCode) {
        try {
            Credebit credebitBedehi = asnadeSodorService.getCredebitByShenase(shenaseBedehi, subsystemName);

            List<KhateSanad> allKhateSanadByCredebit = asnadeSodorService.findAllKhateSanadByCredebitId(credebitBedehi.getId());
            if (allKhateSanadByCredebit != null && allKhateSanadByCredebit.size() > 0){
                for(KhateSanad khateSanad : allKhateSanadByCredebit){
                    if (khateSanad != null && khateSanad.getEtebarCredebit() != null){
                        Credebit etebarCredebit = asnadeSodorService.findCretebitById(khateSanad.getEtebarCredebit().getId());
                        if (etebarCredebit.getCredebitType().equals("AZ_MAHALLE_TABLIGHAT")){
                            return "BA-ERROR-002: Duplicated Credebit type AZ_MAHALLE_TABLIGHAT";
                        }
                    }
                }
            }

            if (credebitBedehi.getRemainingAmount_long() <= 0)
                return shenaseBedehi;
            Credebit credebit = new Credebit(amount, description, payeeName, null, subsystemName, "AZ_MAHALLE_TABLIGHAT", credebitBedehi.getIdentifier(), sequenceService.nextShenaseCredebit(), "");
            credebit.setDateFish(vosulDate);
            Namayande namayande = namayandeService.getNamayandeByKodeKargozar(codeNamayande);
            credebit.setNamayande(namayande);
            Namayande vahedeSodor = namayandeService.getNamayandeByKodeKargozar(codeVahedeSodor);
            credebit.setVahedeSodor(vahedeSodor);
            credebit.setUniqueCode(uniqueCode);
            Credebit checkCredebit = asnadeSodorService.getCredebitByUniqueCode(uniqueCode, subsystemName);
            if(checkCredebit != null)
                return checkCredebit.getShenaseCredebit();
            else {
                asnadeSodorService.saveCredebit(credebit);
            }

            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = null;
            if (username != null)
            {
                user = loginService.findUserByUsername(username);
            }

            //az mahale tablighat digar vojod nadarad
            //Sanad sanad = asnadeSodorService.sabteSanad(user, credebitBedehi, credebit, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM, true);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "ERROR";
        }
        return shenaseBedehi;
    }

    // todo: use @WebParam to generate meaningful nemes instead of arg0, arg1
    @WebMethod(operationName = "payByHesabFiMaBeyn")
    public synchronized String payByHesabFiMaBeyn(String subsystemName, String payerName, Integer amount, String vosulDate, String description, String codeMoshtariEtebar, String shenaseBedehi, String shomareGharardad, String codeNamayande, String codeVahedeSodor, String uniqueCode) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = null;
            if (username != null)
            {
                user = loginService.findUserByUsername(username);
            }
            Credebit credebitBedehi = asnadeSodorService.getCredebitByShenase(shenaseBedehi, subsystemName);
            if (credebitBedehi.getRemainingAmount_long() <= 0)
                return codeMoshtariEtebar;
            Credebit credebit = new Credebit(amount, description, payerName, null, subsystemName, "HESAB_FI_MA_BEYN", credebitBedehi.getIdentifier(), sequenceService.nextShenaseCredebit(), codeMoshtariEtebar);
            credebit.setDateFish(vosulDate);
            credebit.setShomareMoshtari(codeMoshtariEtebar);
            credebit.setShomareGharardad(shomareGharardad);
            Namayande namayande = namayandeService.getNamayandeByKodeKargozar(codeNamayande);
            credebit.setNamayande(namayande);
            Namayande vahedeSodor = namayandeService.getNamayandeByKodeKargozar(codeVahedeSodor);
            credebit.setVahedeSodor(vahedeSodor);
            credebit.setUniqueCode(uniqueCode);
            Credebit checkCredebit = asnadeSodorService.getCredebitByUniqueCode(uniqueCode, subsystemName);
            if(checkCredebit != null)
                return checkCredebit.getShenaseCredebit();
            else {
                asnadeSodorService.saveCredebit(credebit);
            }
            Sanad sanad = asnadeSodorService.sabteSanad(user, credebitBedehi, credebit, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM, true);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "ERROR";
        }
        return codeMoshtariEtebar;
    }

    @WebMethod(operationName = "payByHesabFiMaBeynForElhaghie")
    public synchronized String payByHesabFiMaBeynForElhaghie(String subsystemName, String payerName, Integer amount, String vosulDate, String description, String codeMoshtariEtebar, String shenaseEtebar, String shomareGharardad, String codeNamayande, String codeVahedeSodor, String uniqueCode) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = null;
            if (username != null)
            {
                user = loginService.findUserByUsername(username);
            }
            Credebit credebitEtebar = asnadeSodorService.getCredebitByShenase(shenaseEtebar, subsystemName);
            if (credebitEtebar.getRemainingAmount_long() <= 0)
                return codeMoshtariEtebar;
            Credebit credebit = new Credebit(amount, description, payerName, null, subsystemName, "HESAB_FI_MA_BEYN_BEDEHI", credebitEtebar.getIdentifier(), sequenceService.nextShenaseCredebit(), codeMoshtariEtebar);
            credebit.setDateFish(vosulDate);
            credebit.setShomareMoshtari(codeMoshtariEtebar);
            credebit.setShomareGharardad(shomareGharardad);
            Namayande namayande = namayandeService.getNamayandeByKodeKargozar(codeNamayande);
            credebit.setNamayande(namayande);
            Namayande vahedeSodor = namayandeService.getNamayandeByKodeKargozar(codeVahedeSodor);
            credebit.setVahedeSodor(vahedeSodor);
            credebit.setUniqueCode(uniqueCode);
            Credebit checkCredebit = asnadeSodorService.getCredebitByUniqueCode(uniqueCode, subsystemName);
            if(checkCredebit != null)
                return checkCredebit.getShenaseCredebit();
            else {
                asnadeSodorService.saveCredebit(credebit);
            }
            Sanad sanad = asnadeSodorService.sabteSanad(user, credebit, credebitEtebar, Sanad.NoeSanad.PARDAKHT, Sanad.Vaziat.DAEM, true);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "ERROR";
        }
        return codeMoshtariEtebar;
    }

    @WebMethod(operationName = "payByAzMahaleTablighatForElhaghie")
    public synchronized String payByAzMahaleTablighatForElhaghie(String subsystemName, String payerName, Integer amount, String vosulDate, String description, String codeMoshtariEtebar, String shenaseEtebar, String shomareGharardad, String codeNamayande, String codeVahedeSodor, String uniqueCode) {
        try {
            Credebit credebitEtebar = asnadeSodorService.getCredebitByShenase(shenaseEtebar, subsystemName);

            List<KhateSanad> allKhateSanadByCredebit = asnadeSodorService.findAllKhateSanadByEtebarCredebitId(credebitEtebar.getId());
            if (allKhateSanadByCredebit != null && allKhateSanadByCredebit.size() > 0){
                for(KhateSanad khateSanad : allKhateSanadByCredebit){
                    if (khateSanad != null && khateSanad.getBedehiCredebit() != null){
                        Credebit bedehiCredebit = asnadeSodorService.findCretebitById(khateSanad.getBedehiCredebit().getId());
                        if (bedehiCredebit.getCredebitType().equals("AZ_MAHALLE_TABLIGHAT_BEDEHI")){
                            return "BA-ERROR-003: Duplicated Credebit type AZ_MAHALLE_TABLIGHAT_BEDEHI";
                        }
                    }
                }
            }

            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = null;
            if (username != null)
            {
                user = loginService.findUserByUsername(username);
            }

            if (credebitEtebar.getRemainingAmount_long() <= 0)
                return codeMoshtariEtebar;
            Credebit credebit = new Credebit(amount, description, payerName, null, subsystemName, "AZ_MAHALLE_TABLIGHAT_BEDEHI", credebitEtebar.getIdentifier(), sequenceService.nextShenaseCredebit(), codeMoshtariEtebar);
            credebit.setDateFish(vosulDate);
            credebit.setShomareGharardad(shomareGharardad);
            Namayande namayande = namayandeService.getNamayandeByKodeKargozar(codeNamayande);
            credebit.setNamayande(namayande);
            Namayande vahedeSodor = namayandeService.getNamayandeByKodeKargozar(codeVahedeSodor);
            credebit.setVahedeSodor(vahedeSodor);
            credebit.setUniqueCode(uniqueCode);
            Credebit checkCredebit = asnadeSodorService.getCredebitByUniqueCode(uniqueCode, subsystemName);
            if(checkCredebit != null)
                return checkCredebit.getShenaseCredebit();
            else {
                asnadeSodorService.saveCredebit(credebit);
            }
            Sanad sanad = asnadeSodorService.sabteSanad(user, credebit, credebitEtebar, Sanad.NoeSanad.PARDAKHT, Sanad.Vaziat.DAEM, true);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "ERROR";
        }
        return codeMoshtariEtebar;
    }

    @WebMethod(operationName = "payByFish")
    public synchronized String payByFish(String subsystemName, String payerName, String shomareHesab, String shomareFish, Integer amount, String vosulDate, String vosulTime, String description, String codeMoshtariEtebar, String shenaseBedehi, String codeNamayande, String codeVahedeSodor, String uniqueCode) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = null;
            if (username != null)
            {
                user = loginService.findUserByUsername(username);
            }
            Credebit credebitBedehi = asnadeSodorService.getCredebitByShenase(shenaseBedehi, subsystemName);
            if (credebitBedehi.getRemainingAmount_long() <= 0)
                return codeMoshtariEtebar;
            String bankName = DPUtil.getBankName(shomareHesab);
            if(bankName.equals("ERROR"))
                return "ERROR";
            Credebit credebit = new Credebit(amount, description, payerName, null, subsystemName, "DARYAFTE_FISH", credebitBedehi.getIdentifier(), sequenceService.nextShenaseCredebit(), codeMoshtariEtebar);
            DaryafteFish darFish = new DaryafteFish();
            darFish.setBank(bankName);
            darFish.setTozihat(description);
            darFish.setShomareFish(shomareFish);
            darFish.setTarikh(vosulDate);
            darFish.setTime(vosulTime);
            credebit.setDaryafteFish(darFish);
            asnadeSodorService.saveDaryafteFishElectroniki(darFish);
            credebit.setTimeFish(darFish.getTime());
            credebit.setDateFish(darFish.getTarikh());
            credebit.setBankName(bankName);
            credebit.setShomareMoshtari(codeMoshtariEtebar);
            Namayande namayande = namayandeService.getNamayandeByKodeKargozar(codeNamayande);
            credebit.setNamayande(namayande);
            Namayande vahedeSodor = namayandeService.getNamayandeByKodeKargozar(codeVahedeSodor);
            credebit.setVahedeSodor(vahedeSodor);
            credebit.setUniqueCode(uniqueCode);
            Credebit checkCredebit = asnadeSodorService.getCredebitByUniqueCode(uniqueCode, subsystemName);
            if(checkCredebit != null)
                return checkCredebit.getShenaseCredebit();
            else {
                asnadeSodorService.saveCredebit(credebit);
            }
            Sanad sanad = asnadeSodorService.sabteSanad(user, credebitBedehi, credebit, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM, true);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "ERROR";
        }
        return codeMoshtariEtebar;
    }

    @WebMethod(operationName = "payByPOS")
    public synchronized String payByPOS(String subsystemName, String payerName, String terminalNo, String transactionSerial, String referenceCode, String traceCode, Integer amount, String vosulDate, String vosulTime, String description, String codeMoshtariEtebar, String shenaseBedehi, String codeNamayande, String codeVahedeSodor, String uniqueCode) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = null;
            if (username != null)
            {
                user = loginService.findUserByUsername(username);
            }
            Credebit credebitBedehi = asnadeSodorService.getCredebitByShenase(shenaseBedehi, subsystemName);
            if (credebitBedehi.getRemainingAmount_long() <= 0)
                return codeMoshtariEtebar;
            Credebit credebit = new Credebit(amount, description, payerName, null, subsystemName, "POS", credebitBedehi.getIdentifier(), sequenceService.nextShenaseCredebit(), codeMoshtariEtebar);
            credebit.setDateFish(vosulDate);
            credebit.setTimeFish(vosulTime);
            credebit.setTerminalNoPOS(terminalNo);
            credebit.setTransSerialPOS(transactionSerial);
            credebit.setMarjaNoPOS(referenceCode);
            credebit.setCodePeygiriPOS(traceCode);
            credebit.setShomareMoshtari(codeMoshtariEtebar);
            Namayande namayande = namayandeService.getNamayandeByKodeKargozar(codeNamayande);
            credebit.setNamayande(namayande);
            Namayande vahedeSodor = namayandeService.getNamayandeByKodeKargozar(codeVahedeSodor);
            credebit.setVahedeSodor(vahedeSodor);
            credebit.setUniqueCode(uniqueCode);
            Credebit checkCredebit = asnadeSodorService.getCredebitByUniqueCode(uniqueCode, subsystemName);
            if(checkCredebit != null)
                return checkCredebit.getShenaseCredebit();
            else {
                asnadeSodorService.saveCredebit(credebit);
            }
            Sanad sanad = asnadeSodorService.sabteSanad(user, credebitBedehi, credebit, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM, true);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "ERROR";
        }
        return codeMoshtariEtebar;
    }

    @WebMethod(operationName = "payByCheck")
    public synchronized String payByCheck(String subsystemName, String payerName, String serialNo, String branchName, String accountOwnerName, String sarresidDate, String checkType, String seri, String bankName, String branchCode, String bankAccountNumber, Integer amount, String vosulDate, String description, String codeMoshtariEtebar, String shenaseBedehi, String codeNamayande, String codeVahedeSodor, String uniqueCode) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = null;
            if (username != null)
            {
                user = loginService.findUserByUsername(username);
            }
            Credebit credebitBedehi = asnadeSodorService.getCredebitByShenase(shenaseBedehi, subsystemName);
            if (credebitBedehi.getRemainingAmount_long() <= 0)
                return codeMoshtariEtebar;
            Credebit credebit = new Credebit(amount, description, payerName, null, subsystemName, "DARYAFTE_CHECK", credebitBedehi.getIdentifier(), sequenceService.nextShenaseCredebit(), codeMoshtariEtebar);
            credebit.setDateFish(vosulDate);
            DaryafteCheck darCheck = new DaryafteCheck();
            darCheck.setSerial(serialNo);
            darCheck.setBranchName(branchName);
            darCheck.setAccountOwnerName(accountOwnerName);
            darCheck.setTarikhSarresid(sarresidDate);
            darCheck.setType(DaryafteCheck.Type.valueOf(checkType));
            darCheck.setSeri(seri);
            darCheck.setBankName(bankName);
            darCheck.setBranchCode(branchCode);
            darCheck.setHesabBanki(bankAccountNumber);
            darCheck.setStatus(DaryafteCheck.Status.VOSUL);
            credebit.setDaryafteCheck(darCheck);
            credebit.setShomareMoshtari(codeMoshtariEtebar);
            Namayande namayande = namayandeService.getNamayandeByKodeKargozar(codeNamayande);
            credebit.setNamayande(namayande);
            Namayande vahedeSodor = namayandeService.getNamayandeByKodeKargozar(codeVahedeSodor);
            credebit.setVahedeSodor(vahedeSodor);
            asnadeSodorService.saveDaryafteCheck(darCheck);
            credebit.setUniqueCode(uniqueCode);
            Credebit checkCredebit = asnadeSodorService.getCredebitByUniqueCode(uniqueCode, subsystemName);
            if(checkCredebit != null)
                return checkCredebit.getShenaseCredebit();
            else {
                asnadeSodorService.saveCredebit(credebit);
            }
            Sanad sanad = asnadeSodorService.sabteSanad(user, credebitBedehi, credebit, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM, true);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "ERROR";
        }
        return codeMoshtariEtebar;
    }

    @WebMethod(operationName = "payByInternet")
    public synchronized String payByInternet(String subsystemName, String codeMoshtariBedehi, String shenaseBedehi, Integer amount, String vosulDate, String vosulTime, String description, String rcptName, String identifier, String authId, String codeNamayande, String codeVahedeSodor, String uniqueCode) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = null;
            if (username != null)
            {
                user = loginService.findUserByUsername(username);
            }
            Credebit credebitBedehi = asnadeSodorService.getCredebitByShenase(shenaseBedehi, subsystemName);
            if (credebitBedehi.getRemainingAmount_long() <= 0)
                return codeMoshtariBedehi;
            if (identifier == null || (identifier != null && identifier.length() == 0))
                identifier = credebitBedehi.getIdentifier();
            Credebit credebitInterneti = new Credebit(amount, description, rcptName, null, subsystemName, "DARYAFTE_ELECTRONICI", identifier, sequenceService.nextShenaseCredebit(), null);
            credebitInterneti.setAuthorityId(authId);
            credebitInterneti.setCredebitTypeDesc(Credebit.CredebitTypeDesc.SODOR);
            credebitInterneti.setDateFish(vosulDate);
            credebitInterneti.setTimeFish(vosulTime);
            credebitInterneti.setShomareMoshtari(codeMoshtariBedehi);
            Namayande namayande = namayandeService.getNamayandeByKodeKargozar(codeNamayande);
            credebitInterneti.setNamayande(namayande);
            Namayande vahedeSodor = namayandeService.getNamayandeByKodeKargozar(codeVahedeSodor);
            credebitInterneti.setVahedeSodor(vahedeSodor);
            credebitInterneti.setUniqueCode(uniqueCode);
            Credebit checkCredebit = asnadeSodorService.getCredebitByUniqueCode(uniqueCode, subsystemName);
            if(checkCredebit != null)
                return checkCredebit.getShenaseCredebit();
            else {
                asnadeSodorService.saveCredebit(credebitInterneti);
            }
            Sanad sanad = asnadeSodorService.sabteSanad(user, credebitBedehi, credebitInterneti, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM, true);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "ERROR";
        }
        return codeMoshtariBedehi;
    }

    @WebMethod(operationName = "queryRemainingWithIdentifier")
    public Integer queryRemainingWithIdentifier(String identifier, String subsystemName) {
        return asnadeSodorService.getRemainingByIdentifier(identifier, subsystemName);
    }

    @WebMethod(operationName = "queryRemainingWithTraceCode")
    public Integer queryRemainingWithTraceCode(String traceCode, String subsystemName) {
        Credebit credebit = asnadeSodorService.getCredebitByShomareMoshtari(traceCode, subsystemName);
        return credebit.getRemainingAmount_long().intValue();
    }

    @WebMethod(operationName = "queryRemainingWithShenase")
    public Integer queryRemainingWithShenase(String shenase, String subsystemName) {
        Credebit credebit = asnadeSodorService.getCredebitByShenase(shenase, subsystemName);
        return credebit.getRemainingAmount_long().intValue();
    }

    @WebMethod(operationName = "generateCustomerNumber")
    public String generateCustomerNumber(String namayandeCode, String typeSystem, String format) {
        Namayande namayande = namayandeService.getNamayandeByKodeKargozar(namayandeCode);
        if (namayande == null || namayande.getIssuanceCode() == null) {
            return "Invalid namayandeCode";
        }

        if (!typeSystem.equals("8") && !typeSystem.equals("7")&& !typeSystem.equals("6")) {
            return "Invalid typeSystem";
        }
        if (format.equals("241") || format.equals("242") || format.equals("230") || format.equals("220"))
            return sequenceService.generateNextShomareMoshtari(namayande, typeSystem, format,null);
        else
            return "Invalid format; Valid formats that are expected :  241(MELAT-ESKAN) || 242(MELAT-VANAK) || 230(TEJARAT-E) || 220(TEJARAT)";
    }

    @WebMethod(operationName = "sanadRegister")
    public synchronized String sanadRegister(String customerNumber, String format, String description, Integer amount, String rcptName, String sarresidDate, String subsystemName, String identifier, String time, String date, String codeErja, String pin, String codeNamayande, String codeVahedeSodor, String uniqueCodeEtebar, String uniqueCodeBedehi) {
        // return values
        // 0,[sanad registered]
        // 1,[invalidate data & repetitious customerNumber]
        // 2,[not unique CodeEtebar]
        if (pin.equals("acdebbj46@!156Qw")) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = null;
            if (username != null)
            {
                user = loginService.findUserByUsername(username);
            }
            boolean isValidCstmrNum = sequenceService.isShomareMoshtariValid(customerNumber, format);
            if
                    (
                    (customerNumber != null && !customerNumber.isEmpty()) && (format != null && !format.isEmpty()) && (amount != null && amount > 0) && (rcptName != null && !rcptName.isEmpty()) &&
                            (sarresidDate != null && !sarresidDate.isEmpty()) && (subsystemName != null && !subsystemName.isEmpty()) && (identifier != null && !identifier.isEmpty()) && (time != null && !time.isEmpty()) &&
                            (date != null && !date.isEmpty()) && (codeErja != null && !codeErja.isEmpty())
                    ) {
                if (date.matches("\\d{4}/\\d{2}/\\d{2}")) {
                    if (sarresidDate.matches("\\d{4}/\\d{2}/\\d{2}")) {
                        if (time.matches("([0-1]\\d|2[0-3]):([0-5]\\d)(:([0-5]\\d))?")) {
                            if (isValidCstmrNum) {
                                boolean isRepetitious = asnadeSodorService.isRepetitiousCodeMoshtari(customerNumber);
                                if (isRepetitious) {
                                    return "1, The customerNumber is repetitious";
                                } else {
                                    String shenaseEtebar = sequenceService.nextShenaseCredebit();
                                    Credebit etebar = new Credebit(format, amount, description, rcptName, "", subsystemName, "VEHICLE_DARYAFT_ELECTRONICI", identifier, shenaseEtebar, customerNumber, time, date, codeErja);
                                    etebar.setCredebitTypeDesc(Credebit.CredebitTypeDesc.EHSAN);
                                    Namayande namayande = namayandeService.getNamayandeByKodeKargozar(codeNamayande);
                                    etebar.setNamayande(namayande);
                                    Namayande vahedeSodor = namayandeService.getNamayandeByKodeKargozar(codeVahedeSodor);
                                    etebar.setVahedeSodor(vahedeSodor);
                                    etebar.setUniqueCode(uniqueCodeEtebar);
                                    etebar.setVosoulState(Credebit.VaziyatVosoul.TAEED_VOSOUL);
                                    etebar.setVosoulDate(DateUtil.getCurrentDate());
                                    //b-h
                                    Daftar daftar=asnadeSodorService.findDaftarById(new Integer(1));
                                    etebar.setDaftar(daftar);
                                    /// ta inja
                                    Credebit checkCredebit = asnadeSodorService.getCredebitByUniqueCode(uniqueCodeEtebar, subsystemName);
                                    if(checkCredebit != null)
                                        return "2, "+checkCredebit.getShenaseCredebit();
                                    else {
                                        asnadeSodorService.saveCredebit(etebar);
                                    }

                                    String shenaseBedehi = sequenceService.nextShenaseCredebit();
                                    Credebit bedehi = new Credebit(format, amount, description, rcptName, sarresidDate, subsystemName, "VEHICLE_HAGHBIME_ELECTRONICI", identifier, shenaseBedehi, customerNumber, null, null, codeErja);
                                    bedehi.setNamayande(namayande);
                                    bedehi.setVahedeSodor(vahedeSodor);
                                    bedehi.setUniqueCode(uniqueCodeBedehi);
                                    bedehi.setVosoulState(Credebit.VaziyatVosoul.TAEED_VOSOUL);
                                    bedehi.setVosoulDate(DateUtil.getCurrentDate());
                                    //b-h
                                    bedehi.setDaftar(daftar);
                                    ///ta inja
                                    checkCredebit = asnadeSodorService.getCredebitByUniqueCode(uniqueCodeBedehi, subsystemName);
                                    if(checkCredebit != null)
                                        return "2, "+checkCredebit.getShenaseCredebit();
                                    else {
                                        asnadeSodorService.saveCredebit(bedehi);
                                    }

                                    Sanad sanad = asnadeSodorService.sabteSanad(user, bedehi, etebar, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM, true);
//                                    return sanad.getShomare();
                                    return "1,"+customerNumber;
                                }
                            } else
                                return "2, Invalid customerNumber";
                        } else {
                            return "2, Invalid time format, 23:59:59 expected";
                        }
                    } else {
                        return "2, Invalid sarresidDate format, yyyy/mm/dd expected";
                    }
                } else {
                    return "2, Invalid date format, yyyy/mm/dd expected";
                }
            } else
                return "2, Invalid parameters, not null values are expected";
        } else {
            return "2, Invalid pin";
        }
    }

    @WebMethod(operationName = "updateShomareBimename")
    public boolean updateShomareBimename(String shomarePishnehad, String shomareBimename, String pin) {
        if (pin.equals("acdebbj46@!156Qw")) {
            return asnadeSodorService.updateCredebitByIdentifier(shomarePishnehad, shomareBimename);
        } else {
            return false;
        }

    }

    @WebMethod(operationName = "getNamayandehAuthorized")
    public String getNamayandehAuthorized(Long namayandeId) {
        Namayande namayande = namayandeService.getNamayandeByKodeKargozar(namayandeId.toString());

        if (namayande != null){
            if (namayande.getKodeNamayandeKargozar().equals("100100") ||
                    namayande.getKodeNamayandeKargozar().equals("610620") ||
                    namayande.getKodeNamayandeKargozar().equals("111125"))
                return "1";
            long bedehiSanadNakhorde = asnadeSodorService.getRemainingAmountBedehiSanadNakhorde(namayande.getId());

            if (bedehiSanadNakhorde > 0){
                    String str = "0;";
                    str += "شما به دلیل وجود مجموع بدهی سند نخورده به مبلغ";
                    str += " " + bedehiSanadNakhorde + " ";
                    str += "ریال اجازه ورود به سیستم را ندارید";
                    return str;
                }

            long etebarMojazVousolNashodAmountVar = 0;
//            System.out.println("etebarMojazVousolNashodAmountVar"+etebarMojazVousolNashodAmountVar);
            long etebarSanadKhordeVosulNashode = asnadeSodorService.getRemainingAmountEtebarSanadKhordeVosulNashode(namayande.getId(), "CASH");
            if  (namayande != null && namayande.getEtebarMojazVosolNashodAmount() != null){
                etebarMojazVousolNashodAmountVar = namayande.getEtebarMojazVosolNashodAmount();
//            System.out.println(etebarMojazVousolNashodAmountVar);
//            System.out.println(etebarSanadKhordeVosulNashode);
            }
            else {
                Properties prop = new Properties();
                try {
                    prop.load(getClass().getClassLoader().getResourceAsStream("com/bitarts/parsian/config/appConfig.properties"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String etebarMojazVousolNashodAmountVarStr = prop.getProperty("WebService.NamayandehAuthorized.EtebarMojazVousolNashodAmount");
                etebarMojazVousolNashodAmountVar = Long.parseLong(etebarMojazVousolNashodAmountVarStr);
            }
            if (namayande != null && etebarSanadKhordeVosulNashode > etebarMojazVousolNashodAmountVar)
                if (etebarSanadKhordeVosulNashode > 0){
//                    System.out.println("we are in here!!!!");
                    String str = "0;";
                    str += "شما به دلیل وجود اعتبارهای وصول نشده به مبلغ";
                    str += " " + etebarSanadKhordeVosulNashode + " ";
                    str += "ریال اجازه ورود به سیستم را ندارید";
                    return str;

                }

            long etebarMojazVousolNashodAmountVarCheck = 0;
            long etebarSanadKhordeVosulNashodeCheck = asnadeSodorService.getRemainingAmountEtebarSanadKhordeVosulNashode(namayande.getId(), "CHECK");
            if (namayande != null && namayande.getEtebarMojazVosolNashodAmountCheck() != null)
                etebarMojazVousolNashodAmountVarCheck = namayande.getEtebarMojazVosolNashodAmountCheck();
            else {
                Properties prop = new Properties();
                try {
                    prop.load(getClass().getClassLoader().getResourceAsStream("com/bitarts/parsian/config/appConfig.properties"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String etebarMojazVousolNashodAmountVarStr = prop.getProperty("WebService.NamayandehAuthorized.EtebarMojazVousolNashodAmountCheck");
                etebarMojazVousolNashodAmountVarCheck = Long.parseLong(etebarMojazVousolNashodAmountVarStr);
            }
            if (namayande != null && etebarSanadKhordeVosulNashodeCheck > etebarMojazVousolNashodAmountVarCheck)
                if (etebarSanadKhordeVosulNashodeCheck > 0) {
                    String str = "0;";
                    str += "شما به دلیل وجود چک های وصول نشده به مبلغ";
                    str += " " + etebarSanadKhordeVosulNashodeCheck + " ";
                    str += "ریال اجازه ورود به سیستم را ندارید";
                    return str;

                }

            long checkSanadKhordeVagozarNashode = asnadeSodorService.getAmountCheckSanadKhordeVagozarNashode(namayande.getId());
            if (checkSanadKhordeVagozarNashode > 0) {
                String str = "0;";
                str += "شما به دلیل وجود چک سند خورده و واگذار نشده به مبلغ";
                str += " " + checkSanadKhordeVagozarNashode + " ";
                str += "ریال اجازه ورود به سیستم را ندارید";
                return str;
            }

            return "1";
        }
        return "0";
    }

    private String fillEstelamOb(Estelam estelam, String birthDate, String nahvePardakht, long haghBimeAvalie, long haghBimePardakhti, int nerkhHaghBime, long sarmayeFot, int nerkhSarmayeFot, int durationCycle)
    {
        String nahvePardakhtStr = null;
        String errMsg=null;

        if (nahvePardakht.equals("0"))
        {
            nahvePardakhtStr = "yekja";
        }
        else if (nahvePardakht.equals("1"))
        {
            nahvePardakhtStr = "mah";
        }
        else if (nahvePardakht.equals("3"))
        {
            nahvePardakhtStr = "3mah";
        }
        else if (nahvePardakht.equals("6"))
        {
            nahvePardakhtStr = "6mah";
        }
        else if (nahvePardakht.equals("12"))
        {
            nahvePardakhtStr = "sal";
        }
        else
        {
            errMsg="Invalid nahvePardakht; Valid nahvePardakht argument that are expected : 0=[yekja] || 1=[mahane] || 3=[3-mahe] || 6=[6-mahe] || 12=[salane]/n";
        }
        if(nerkhHaghBime!=0 && nerkhHaghBime!=5 && nerkhHaghBime != 10 && nerkhHaghBime != 15 && nerkhHaghBime != 20)
        {
            errMsg+="Invalid nerkhHaghBime; Valid nerkhHaghBime argument that are expected : 0 || 5 || 10 || 15 || 20/n";
        }
        if(nerkhSarmayeFot != 0 && nerkhSarmayeFot != 5 && nerkhSarmayeFot != 10 && nerkhSarmayeFot != 15 && nerkhSarmayeFot != 20)
        {
            errMsg+="Invalid nerkhSarmayeFot; Valid nerkhSarmayeFot argument that are expected : 0 || 5 || 10 || 15 || 20/n";
        }
        if(!birthDate.matches("\\d{4}/\\d{2}/\\d{2}"))
        {
            errMsg+="Invalid birthDate, yyyy/mm/dd expected./n";
        }

        estelam.setNahve_pardakht_hagh_bime(nahvePardakhtStr);
        estelam.setSen_bime_shode(String.valueOf((int) Math.ceil(DateUtil.getTimeDifferenceByDayCount(DateUtil.getCurrentDate(), birthDate) / 365)));
        estelam.setSarmaye_paye_fot_long(sarmayeFot);
        estelam.setHagh_bime_pardakhti(String.valueOf(haghBimePardakhti));
        estelam.setMablagh_seporde_ebtedaye_sal(String.valueOf(haghBimeAvalie));
        estelam.setNerkh_afzayesh_salaneh_hagh_bime(String.valueOf(nerkhHaghBime));
        estelam.setNerkh_afzayesh_salaneh_sarmaye_fot(String.valueOf(nerkhSarmayeFot));
        estelam.setModat_bimename(String.valueOf(durationCycle));
        return errMsg;
    }

    @WebMethod(operationName = "estelamCalc")
    public String estelamCalc(String birthDate, String nahvePardakht, long haghBimeAvalie, long haghBimePardakhti, int nerkhHaghBime, long sarmayeFot, int nerkhSarmayeFot, int durationCycle)
    {
        Estelam estelam = new Estelam();
        String estelamReturnStr="";
        String errMsg = fillEstelamOb(estelam,birthDate,nahvePardakht,haghBimeAvalie,haghBimePardakhti,nerkhHaghBime,sarmayeFot,nerkhSarmayeFot,durationCycle);
        if(errMsg!=null)
            return errMsg;

        boolean pooshesh_moafiat_b = false;
        boolean pooshesh_fot_dar_asar_zelzele_b = false;
        boolean pooshesh_naghs_ozv_b = false;
        String naghsDarad = "false";

        String _date = DateUtil.getCurrentDate();
        double nerkhBahreFanni = AsnadeSodorService.getNerkhBahreFanni(_date, null);
        long hadeAksarSarFot = AsnadeSodorService.getHadeAksarSarmayeFot(_date, null);
        long hadeAksarSarmayeFotHadese = AsnadeSodorService.getHadeAksarSarmayeFot(_date, null);
        long hadeAksarAmraz = AsnadeSodorService.getHadeAksarSarmayeFotAmraz(_date, null);
        double nerkhPusheshZelzele = AsnadeSodorService.getNerkhPusheshKhatarZelzele(_date, null);
        double darsadMaliat = AsnadeSodorService.getDarsadMaliat(_date, null);
        double karmozdGyekja = AsnadeSodorService.getKarmozdGyekja(_date, null);
        double karmozdyekja = AsnadeSodorService.getKarmozdYekja(_date, null);
        double bimegariGyekja = AsnadeSodorService.getBimeGariGyekja(_date, null);
        double bimegariyekja = AsnadeSodorService.getBimeGariYekja(_date, null);
        double edariGyekja = AsnadeSodorService.getEdariGyekja(_date, null);
        double vosulGyekja = AsnadeSodorService.getVosulGyekja(_date, null);
        double[][] lifeTable = AsnadeSodorService.getLifeTable(_date, null);
        Constants.PayeyeMohasebeHazineEdari payeyeMohasebat = AsnadeSodorService.getPayeyeMohasebeHazineEdari(_date, null);

        if (estelam.getPooshesh_fot_dar_asar_haadese() != null && estelam.getPooshesh_fot_dar_asar_haadese().equals("yes"))
        {

        }
        else
        {
            estelam.setSarmaye_paye_fot_dar_asar_hadese("0");
        }

        if (estelam.getPooshesh_naghs_ozv() != null && estelam.getPooshesh_naghs_ozv().equals("yes"))
        {
            pooshesh_naghs_ozv_b = true;
        }
        else
        {
            pooshesh_naghs_ozv_b = false;
            estelam.setSarmaye_pooshesh_naghs_ozv("0");
        }
        if (estelam.getPooshesh_fot_dar_asar_zelzele() != null && estelam.getPooshesh_fot_dar_asar_zelzele().equals("yes"))
        {
            pooshesh_fot_dar_asar_zelzele_b = true;
        }
        else
        {
            pooshesh_fot_dar_asar_zelzele_b = false;
        }
        if (estelam.getPooshesh_amraz_khas() != null && estelam.getPooshesh_amraz_khas().equals("yes"))
        {

        }
        else
        {
            estelam.setSarmaye_pooshesh_amraz_khas("0");
        }
        if (estelam.getPooshesh_moafiat() != null && estelam.getPooshesh_moafiat().equals("yes"))
        {
            pooshesh_moafiat_b = true;
        }
        else
        {
            pooshesh_moafiat_b = false;
        }
        NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME nahve = null;
        if (estelam.getNahve_pardakht_hagh_bime().equals("yekja"))
        {
            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.YEKJA;
        }
        else if (estelam.getNahve_pardakht_hagh_bime().equals("mah"))
        {
            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.MAAHAANEH;
        }
        else if (estelam.getNahve_pardakht_hagh_bime().equals("3mah"))
        {
            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SE_MAAHE;
        }
        else if (estelam.getNahve_pardakht_hagh_bime().equals("6mah"))
        {
            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SHISH_MAAHE;
        }
        else if (estelam.getNahve_pardakht_hagh_bime().equals("sal"))
        {
            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SAALAANEH;
        }
        NSPConstants.NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH nahveDaryaft = null;
        if (estelam.getNahveye_daryafte_andukhte_entehaye_modat_bimename() != null)
        {
            if (estelam.getNahveye_daryafte_andukhte_entehaye_modat_bimename().equals("yekja"))
            {
                nahveDaryaft = NSPConstants.NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH.YEKJA;
            }
            else if (estelam.getNahveye_daryafte_andukhte_entehaye_modat_bimename().equals("mos_modat"))
            {
                nahveDaryaft = NSPConstants.NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH.MOSTAMERI_MODAT_MOAYAN;
            }
            else if (estelam.getNahveye_daryafte_andukhte_entehaye_modat_bimename().equals("mos_omr"))
            {
                nahveDaryaft = NSPConstants.NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH.MOSTAMERI_MADAMOL_OMR;
            }
        }
        NSPConstants.NAHVE_DARYAAFT_MOSTAMERI nahveDaryaftMostamari = null;
        if (estelam.getNahve_daryaft_mostamari() != null && estelam.getNahve_daryaft_mostamari().equals("sal"))
        {
            nahveDaryaftMostamari = NSPConstants.NAHVE_DARYAAFT_MOSTAMERI.SAALAANEH;
        }
        else if (estelam.getNahve_daryaft_mostamari() != null && estelam.getNahve_daryaft_mostamari().equals("mah"))
        {
            nahveDaryaftMostamari = NSPConstants.NAHVE_DARYAAFT_MOSTAMERI.MAAHAANEH;
        }
        NSPConstants.NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI nahveKasrHaghBimePoosheshhayeEzafi = null;
        if (estelam.getNahve_kasr_hagh_bime_poosheshhaye_ezafi() != null && estelam.getNahve_kasr_hagh_bime_poosheshhaye_ezafi().equals("mazad"))
        {
            nahveKasrHaghBimePoosheshhayeEzafi = NSPConstants.NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.MAZAD_BAR_HAGH_BIME_PAYE;
        }
        else if (estelam.getNahve_kasr_hagh_bime_poosheshhaye_ezafi() != null && estelam.getNahve_kasr_hagh_bime_poosheshhaye_ezafi().equals("az"))
        {
            nahveKasrHaghBimePoosheshhayeEzafi = NSPConstants.NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.AZ_HAGH_BIME_PAYE;
        }

        if (estelam.getSen_bime_shode() == null) estelam.setSen_bime_shode("0");
        String zamanAsarElhaghie=null;
        if (zamanAsarElhaghie != null)
        {
            if (zamanAsarElhaghie.equals("1"))
            {
                int bimeYear = DateUtil.getBimeYear(DateUtil.getCurrentDate(), estelam.getPishnehad().getBimename().getTarikhShorou());
                estelam.setSen_bime_shode(String.valueOf(Integer.parseInt(estelam.getSen_bime_shode()) + bimeYear));
            }
            else if (zamanAsarElhaghie.equals("2"))
            {
                int bimeYear = DateUtil.getBimeYear(DateUtil.getCurrentDate(), estelam.getPishnehad().getBimename().getTarikhShorou());
                estelam.setSen_bime_shode(String.valueOf(Integer.parseInt(estelam.getSen_bime_shode()) + bimeYear - 1));
            }
        }
        if (Integer.parseInt(estelam.getSen_bime_shode()) < 0) estelam.setSen_bime_shode("0");
        if (estelam.getDarsad_ezafe_nerkh_pezeshki() == null || estelam.getDarsad_ezafe_nerkh_pezeshki().isEmpty())
            estelam.setDarsad_ezafe_nerkh_pezeshki("0");
        estelam.setDarsad_ezafe_nerkh_pezeshki(estelam.getDarsad_ezafe_nerkh_pezeshki().replaceAll(",", "").replaceAll(" ", ""));
        if (estelam.getSarmaye_paye_fot_dar_asar_hadese() == null) estelam.setSarmaye_paye_fot_dar_asar_hadese("0");
        if (estelam.getSarmaye_paye_fot() == null) estelam.setSarmaye_paye_fot("0");
        if (estelam.getSarmaye_pooshesh_amraz_khas() == null) estelam.setSarmaye_pooshesh_amraz_khas("0");
        if (estelam.getSarmaye_pooshesh_naghs_ozv() == null) estelam.setSarmaye_pooshesh_naghs_ozv("0");
        if (estelam.getNerkh_afzayesh_salaneh_hagh_bime() == null) estelam.setNerkh_afzayesh_salaneh_hagh_bime("0");
        if (estelam.getNerkh_afzayesh_salaneh_sarmaye_fot() == null) estelam.setNerkh_afzayesh_salaneh_sarmaye_fot("0");
        if (estelam.getHagh_bime_pardakhti() == null) estelam.setHagh_bime_pardakhti("0");
        if (estelam.getMablagh_seporde_ebtedaye_sal() == null) estelam.setMablagh_seporde_ebtedaye_sal("0");
        if (estelam.getNerkh_afzayesh_salaneh_mostamari() == null) estelam.setNerkh_afzayesh_salaneh_mostamari("0");
        if (estelam.getModdat_zaman_daryaft_mostamari() == null) estelam.setModdat_zaman_daryaft_mostamari("0");
        if (estelam.getTabaghe_khatar() == null || estelam.getTabaghe_khatar().equalsIgnoreCase("null") || estelam.getTabaghe_khatar().isEmpty() || estelam.getTabaghe_khatar().equalsIgnoreCase("undefined"))
            estelam.setTabaghe_khatar("1");
        if (estelam.getTabaghe_khatar_naghsozv() == null || estelam.getTabaghe_khatar_naghsozv().equalsIgnoreCase("null") || estelam.getTabaghe_khatar_naghsozv().isEmpty() || estelam.getTabaghe_khatar_naghsozv().equalsIgnoreCase("undefined"))
            estelam.setTabaghe_khatar_naghsozv("1");
//        if(estelam.getDarsad_takhfif_bimegari() == null) estelam.setDarsad_takhfif_bimegari("0");
//        if(estelam.getDarsad_takhfif_edari() == null) darsad_takhfif_edari = "0";
//        if(estelam.getDarsad_takhfif_karmozd_hagh() == null) darsad_takhfif_karmozd_hagh = "0";
        if (estelam.getDarsad_takhfif_karmozd_karmozd() == null) estelam.setDarsad_takhfif_karmozd_karmozd("0");
        if (estelam.getDarsad_takhfif_vosool() == null) estelam.setDarsad_takhfif_vosool("0");

        BimeNaamehVaSarmayehGozari bimeNaamehVaSarmayehGozari = new BimeNaamehVaSarmayehGozari();
        boolean correctCalculation = true;
        try
        {

            List<FRs> fRsList = bimeNaamehVaSarmayehGozari.finalReports(
                    //Sene_Bime_Shode,Darsad_Ezafe_Nerkh_Pezeshki,Modat_Bime_Naameh,
                    Short.parseShort(estelam.getSen_bime_shode()), Double.parseDouble(estelam.getDarsad_ezafe_nerkh_pezeshki()), Short.parseShort(estelam.getModat_bimename()),
                    //Sarmaye_Paaye_Fot_Rial, Sarmaye_Paaye_Fot_Dar_Asar_Hadese_Rial, Sarmaye_Pushesh_Amraaz_Khaas_Rial,
                    Long.parseLong(StringUtil.removeNoneDigits(estelam.getSarmaye_paye_fot())), Long.parseLong(StringUtil.removeNoneDigits(estelam.getSarmaye_paye_fot_dar_asar_hadese())), Long.parseLong(StringUtil.removeNoneDigits(estelam.getSarmaye_pooshesh_amraz_khas())),
                    //pushesh_Moaafiyat, Pushesh_Naghs_Ozv,  Sarmaye_Pushesh_Naghs_Ozv,
                    pooshesh_moafiat_b, pooshesh_naghs_ozv_b, Long.parseLong(StringUtil.removeNoneDigits(estelam.getSarmaye_pooshesh_naghs_ozv()))
                    //Nerkh_Afzayesh_Saalaaneh_Hagh_Bime,   Nerkh_Afzayesh_Saalaaneh_Sarmaye_Fot,   Nahve_Pardaakht_Hagh_Bime,
                    , Double.parseDouble(estelam.getNerkh_afzayesh_salaneh_hagh_bime()), Double.parseDouble(estelam.getNerkh_afzayesh_salaneh_sarmaye_fot()), nahve,
                    //Hagh_Bime_Pardaakhti_Rial,  Mablagh_Seporde_Ebtedaye_Saal_Rial,   Nahve_Daryaft_Andukhte_Entehaye_Modat_Bime_Naameh,
                    Long.parseLong(StringUtil.removeNoneDigits(estelam.getHagh_bime_pardakhti())), Long.parseLong(StringUtil.removeNoneDigits(estelam.getMablagh_seporde_ebtedaye_sal())), nahveDaryaft,
                    //Nerkh_Afzayesh_Saalaaneh_Mostamari,  Nahve_Daryaft_Mostamri,      Modat_Zaman_Daryaft_Mostamari,
                    Double.parseDouble(estelam.getNerkh_afzayesh_salaneh_mostamari()), nahveDaryaftMostamari, Short.parseShort(estelam.getModdat_zaman_daryaft_mostamari()),
                    //Tabaghe_Khatar, Pushesh_Fot_Dar_Asar_Zelzele, Bimeh_Gari,  Edari,
                    Byte.parseByte(estelam.getTabaghe_khatar()), Byte.parseByte(estelam.getTabaghe_khatar_naghsozv()), pooshesh_fot_dar_asar_zelzele_b,
                    0.0//Double.parseDouble(estelam.getDarsad_takhfif_bimegari()),
                    , 0.0// Double.parseDouble(estelam.getDarsad_takhfif_edari()),
                    //Kaarmozd_Az_Mahal_Hagh_Bimeh,  Kaarmozd_Az_Mahal_Kaarmozd,  Hazineh_Vosul
                    , 0.0//Double.parseDouble(estelam.getDarsad_takhfif_karmozd_hagh())
                    , Double.parseDouble(estelam.getDarsad_takhfif_karmozd_karmozd()), Double.parseDouble(estelam.getDarsad_takhfif_vosool())
                    , nerkhBahreFanni, hadeAksarSarFot, hadeAksarSarmayeFotHadese, hadeAksarAmraz, nerkhPusheshZelzele, karmozdGyekja, karmozdyekja, bimegariGyekja, bimegariyekja, edariGyekja, vosulGyekja,
                    lifeTable, _date, estelam.getPishnehad() == null ? null : estelam.getPishnehad().getTarh(), nahveKasrHaghBimePoosheshhayeEzafi, payeyeMohasebat);
            if (fRsList != null)
            {
                if (Integer.parseInt(estelam.getDarsad_ezafe_nerkh_pezeshki()) != 0)
                {
                    estelam.setSen_bime_shode(String.valueOf(fRsList.get(0).getSenneBimeShodePasAzEmaleEzafeNerkh()));
                }
//                for(FRs fRs : fRsList)
//                {
//                    estelamReturnStr += String.valueOf(fRs.getSarmaayeFotBehHarEllat())+":";
//                    estelamReturnStr += String.valueOf(fRs.getArzeshBazKharidBaaSudTazmini15Darsad())+":";
//                    estelamReturnStr += String.valueOf(fRs.getPishbiniArzeshBazKharidBaaSud20Darsad())+":";
//                    estelamReturnStr += String.valueOf(fRs.getPishbiniArzeshBazKharidBaaSud22Darsad())+"::";
//                }
                 estelamReturnStr += String.valueOf(Math.round(fRsList.get(fRsList.size() - 1).getSarmaayeFotBehHarEllat()))+":"+
                                     String.valueOf(Math.round(fRsList.get(fRsList.size() - 1).getArzeshBazKharidBaaSudTazmini15Darsad()))+":"+
                                     String.valueOf(Math.round(fRsList.get(fRsList.size() - 1).getPishbiniArzeshBazKharidBaaSud20Darsad()))+":"+
                                     String.valueOf(Math.round(fRsList.get(fRsList.size() - 1).getPishbiniArzeshBazKharidBaaSud22Darsad()));
            }
        }
        catch (BimeNaamehVaSarmayehGozari.CustomValidationException e)
        {
            correctCalculation = false;
        }
        if (pooshesh_naghs_ozv_b)
        {
            naghsDarad = "true";
        }

        return estelamReturnStr;
    }

    public void setAsnadeSodorService(IAsnadeSodorService asnadeSodorService) {
        this.asnadeSodorService = asnadeSodorService;
    }

    public void setSequenceService(ISequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }

    public void setNamayandeService(INamayandeService namayandeService) {
        this.namayandeService = namayandeService;
    }

    public ILoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    public IPishnehadService getPishnehadService()
    {
        return pishnehadService;
    }

    public void setPishnehadService(IPishnehadService pishnehadService)
    {
        this.pishnehadService = pishnehadService;
    }

    //b-h
    @WebMethod(operationName = "estelamVaziateBedehiVaEtebar")
    public synchronized  String estelamVaziateBedehiVaEtebar(String input){
        JSONObject output=new JSONObject();
        try{
            JSONParser parser=new JSONParser();
            JSONObject jsonObject=(JSONObject)parser.parse(input);
            String uniqueCode=(String)jsonObject.get("uniqueCode") ;
            String subsystemName=(String)jsonObject.get("subsystemName");
            List<vaziateBedehiVaEtebar> result=asnadeSodorService.estelamVaziateBedehiVaEtebar(uniqueCode,subsystemName);

            if(result.isEmpty()){
                output.put("errorCode","-1");
                output.put("description", ":unique code is not valid for this subsystem name");

            }
            else {
                if( (result.get(0).getVaziateSanad()).equals(Sanad.Vaziat.DAEM)) {
                    output.put("errorCode","1");
                    output.put("description", "sanad daem");
                    output.put("sanad_created_date",result.get(0).getSanad_CreatedDate());
                }
                else{
                    output.put("errorCode","0");
                    output.put("description", "sanad movaghat");
                }


            }
        }
        catch (ParseException e){
           e.printStackTrace();
        }
        return output.toString();
    }

    //b-h
//    @WebMethod(operationName = "getBedehiByShomareBimeName")
//    public synchronized String getBedehiByShomareBimeName(String shomarebimename){
//       List result=asnadeSodorService.findBedehiByShomareBimeName(shomarebimename);
//       Iterator iterator=result.iterator();
//        Object[] tuple = (Object[]) iterator.next();
//        String bedehi =  tuple[7].toString();
//        String etebar =  tuple[12].toString();
//
//        System.out.println(bedehi);
//        System.out.println(etebar);
//        return "1";
//    }
}
