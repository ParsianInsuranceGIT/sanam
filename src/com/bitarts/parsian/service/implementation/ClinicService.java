package com.bitarts.parsian.service.implementation;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.parsian.dao.AzmayeshDAO;
import com.bitarts.parsian.dao.ClinicDAO;
import com.bitarts.parsian.model.*;
import com.bitarts.parsian.model.management.EmzaKonande;
import com.bitarts.parsian.model.pishnahad.Moarefiname;
import com.bitarts.parsian.service.IClinicService;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/8/11
 * Time: 4:20 PM
 */
public class ClinicService implements IClinicService {
    private ClinicDAO clinicDAO;
    private AzmayeshDAO azmayeshDAO;

    public String addNewClinic(Clinic clinic) {
        clinicDAO.saveClinic(clinic);
        if (clinic.getId() == null)
            return "DUPLICATE";
        else return "SUCCESS";
    }

    public Clinic getClinicById(Integer id) {
        return clinicDAO.getClinicById(id);
    }

    public List<Clinic> findAllClinics() {
        return clinicDAO.findAll();
    }

    public void updateClinic(Clinic clinic) {
        clinicDAO.updateClinic(clinic);
    }

    @Transactional
    public void removeClinic(Integer id) {
        clinicDAO.removeClinic(id);
    }

    public boolean addNewAzmayesh(Azmayesh azmayesh, Integer clinicId) {
        return azmayeshDAO.saveAzmayesh(azmayesh, clinicId);
    }

    public void addNewBankInfo(BankInfo bankInfo) {
        clinicDAO.addBankInfo(bankInfo);
    }

    public List<BankInfo> findAllBankInfos() {
        return clinicDAO.findAllBankInfos();
    }

    public Azmayesh getAzmayeshById(Integer id) {
        return clinicDAO.getAzmayeshById(id);
    }

    public void updateAzmayesh(Azmayesh azmayesh) {
        clinicDAO.updateAzmayesh(azmayesh);
    }

    public String addNewAzmayeshType(AzmayeshType azmayeshType) {
        clinicDAO.addAzmayeshType(azmayeshType);
        if (azmayeshType.getId() == null)
            return "DUPLICATE";
        else return "SUCCESS";

    }

    public List<AzmayeshType> findAllAzmayeshTypes() {
        return clinicDAO.findAllAzmayeshTypes();
    }

    public AzmayeshType getAzmayeshTypeById(int id) {
        return clinicDAO.getAzmayeshTypeById(id);
    }

    public String addNewAzmayeshName(AzmayeshName azmayeshName) {
        clinicDAO.addAzmayeshName(azmayeshName);
        if (azmayeshName.getId() == null)
            return "DUPLICATE";
        else return "SUCCESS";
    }

    public List<AzmayeshName> findAllAzmayeshNames() {
        return clinicDAO.findAllAzmayeshNames();
    }

    public AzmayeshName getAzmayeshNameById(Integer id) {
        return clinicDAO.getAzmayeshNameById(id);
    }

    public void removeAzmayesh(Integer id) {
        clinicDAO.removeAzmayesh(id);

    }

    public void removeAzmayeshTypeById(Integer azmayeshTypeId) {
        clinicDAO.removeAzmayeshTypeById(azmayeshTypeId);
    }

    public void removeAzmayeshNameById(Integer id) {
        clinicDAO.removeAzmayeshNameById(id);
    }

    public void updateAzmayeshType(AzmayeshType theAzmayeshType) {
        clinicDAO.updateAzmayeshType(theAzmayeshType);
    }

    public void updateAzmayeshName(AzmayeshName theAzmayeshName) {
        clinicDAO.updateAzmayeshName(theAzmayeshName);
    }

    public List<BankInfo> loadBankInfosBySerial(long serialId, Integer stateId)
    {
        return clinicDAO.loadBankInfosBySerial(serialId,stateId);
    }

    @Transactional
    public Set<BankInfo> addBankInfosBatch(List<BankInfo> bankInfosExcel, Bargozarandeh bargozarandeh) {
        Set<BankInfo> allWeNeed = new HashSet<BankInfo>();
        for (BankInfo bankInfo : bankInfosExcel) {
            if (bankInfo != null) {
                if (bankInfo.getShomareFish() != null) {
                    allWeNeed.add(bankInfo);
                    this.clinicDAO.addBankInfo(bankInfo, bargozarandeh);
                }
            }
        }
        return allWeNeed;
    }

    public void saveBankInfos(List<BankInfo> list)
    {
        clinicDAO.saveBankInfos(list);
    }

    public Bargozarandeh addNewBargozar(Bargozarandeh bargozar) {
        return clinicDAO.addBargozar(bargozar);
    }

    public void updateBargozar(Bargozarandeh bargozarandeh) {
        clinicDAO.updateBargozar(bargozarandeh);
    }

    public BankInfo getBankInfoById(Integer mainId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addEmzaKonande(EmzaKonande emzaKonande) {
        this.clinicDAO.addEmzaKonande(emzaKonande);
    }

    public EmzaKonande findEmzaKonandeById(Integer id) {
        return clinicDAO.findEmzaKonandeById(id);
    }

    public void updateEmzaKonande(EmzaKonande theEmzaKonande) {
        clinicDAO.updateEmzaKonande(theEmzaKonande);
    }

    public void removeEmzaKonandeById(Integer id) {
        this.clinicDAO.removeEmzaKonandeById(id);
    }

    public boolean doesBankInfoExist(String shomareFish, String kodeShobe, String tarikhFish) {
        return this.clinicDAO.doesBankInfoExist(shomareFish, kodeShobe, tarikhFish);
    }

    public void setClinicDAO(ClinicDAO clinicDAO) {
        this.clinicDAO = clinicDAO;
    }

    public ClinicDAO getClinicDAO() {
        return clinicDAO;
    }

    public AzmayeshDAO getAzmayeshDAO() {
        return azmayeshDAO;
    }

    public void setAzmayeshDAO(AzmayeshDAO azmayeshDAO) {
        this.azmayeshDAO = azmayeshDAO;
    }

    public List<Moarefiname> findMoarefiname(String azTarikhSodur, String taTarikhSodur, String kodeRahgiri, String kodeMoarefi, String nameBimeshode, String nameKhanevadegiBimeShode, String kodeMelli, int clinicSearch, int vaziatMoarefi) {
        return clinicDAO.findMoarefiname(azTarikhSodur, taTarikhSodur, kodeRahgiri, kodeMoarefi, nameBimeshode, nameKhanevadegiBimeShode, kodeMelli, clinicSearch, vaziatMoarefi);
    }

    public Moarefiname findMoarefinameById(Integer id) {
        return clinicDAO.findMoarefinameById(id);
    }

    public void updateMoarefiname(Moarefiname moarefiname) {
        clinicDAO.updateMoarefiname(moarefiname);
    }

    public PaginatedListImpl<Clinic> findAllClinics(PaginatedListImpl<Clinic> listClinics, String sname, String scityname, String saztarikh, String statarikh) {
        return clinicDAO.findAllClinics(listClinics, sname, scityname, saztarikh, statarikh);
    }

    public PaginatedListImpl<AzmayeshName> findAllAzmayeshNames(PaginatedListImpl<AzmayeshName> listAzmayeshNames, String sname, String snoe) {
        return clinicDAO.findAllAzmayeshNames(listAzmayeshNames, sname, snoe);
    }

    public PaginatedListImpl<AzmayeshType> findAllAzmayeshTypes(PaginatedListImpl<AzmayeshType> listAzmayeshTypes, String snoe) {
        return clinicDAO.findAllAzmayeshNames(listAzmayeshTypes, snoe);
    }

    public void addNewAzmayeshToAllClinic(Azmayesh azmayesh) {
        List<Clinic> clinicList = clinicDAO.findAll();
        for (Clinic clinic : clinicList) {
            addNewAzmayesh(azmayesh, clinic.getId());
        }
    }

    public void addAzemayeshToSomeClinics(Azmayesh azmayesh, Object[] clinicIds) {
        for (Object clinicId : clinicIds) {
            addNewAzmayesh(azmayesh, Integer.parseInt((String)clinicId));
        }
    }

    public BankInfo findBankInfoById(Integer bankInfoId) {
        return clinicDAO.findBankInfoById(bankInfoId);
    }
}

