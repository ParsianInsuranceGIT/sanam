package com.bitarts.parsian.service;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.parsian.model.*;
import com.bitarts.parsian.model.management.EmzaKonande;
import com.bitarts.parsian.model.pishnahad.Moarefiname;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/8/11
 * Time: 4:18 PM
 */
public interface IClinicService extends IBaseService  {
    public static final String SERVICE_NAME = "clinicService";

    public String addNewClinic(Clinic clinic);

    public Clinic getClinicById(Integer id);

    public List<Clinic> findAllClinics();

    void updateClinic(Clinic clinic);

    void removeClinic(Integer Id);

    boolean addNewAzmayesh(Azmayesh azmayesh, Integer clinicId);

    void addNewBankInfo(BankInfo bankInfo);

    List<BankInfo> findAllBankInfos();

    Azmayesh getAzmayeshById(Integer id);

    void updateAzmayesh(Azmayesh azmayesh);

    String addNewAzmayeshType(AzmayeshType azmayeshType);

    List<AzmayeshType> findAllAzmayeshTypes();

    AzmayeshType getAzmayeshTypeById(int id);

    String addNewAzmayeshName(AzmayeshName azmayeshName);

    List<AzmayeshName> findAllAzmayeshNames();

    AzmayeshName getAzmayeshNameById(Integer id);

    void removeAzmayesh(Integer id);

    void removeAzmayeshTypeById(Integer azmayeshTypeId);

    void removeAzmayeshNameById(Integer id);

    void updateAzmayeshType(AzmayeshType theAzmayeshType);

    void updateAzmayeshName(AzmayeshName theAzmayeshName);

    Set<BankInfo> addBankInfosBatch(List<BankInfo> bankInfosExcel, Bargozarandeh bargozarandeh);

    public List<BankInfo> loadBankInfosBySerial(long serialId, Integer stateId);

    public void saveBankInfos(List<BankInfo> list);

    Bargozarandeh addNewBargozar(Bargozarandeh bargozar);

    void updateBargozar(Bargozarandeh bargozarandeh);

    BankInfo getBankInfoById(Integer mainId);

    void addEmzaKonande(EmzaKonande emzaKonande);

    EmzaKonande findEmzaKonandeById(Integer id);

    void updateEmzaKonande(EmzaKonande theEmzaKonande);

    void removeEmzaKonandeById(Integer id);

    boolean doesBankInfoExist(String shomareFish, String kodeShobe, String tarikhFish);

    List<Moarefiname> findMoarefiname(String azTarikhSodur, String taTarikhSodur, String kodeRahgiri, String kodeMoarefi, String nameBimeshode, String nameKhanevadegiBimeShode, String kodeMelli, int clinicSearch, int vaziatMoarefi);

    Moarefiname findMoarefinameById(Integer id);

    void updateMoarefiname(Moarefiname moarefiname);

    PaginatedListImpl<Clinic> findAllClinics(PaginatedListImpl<Clinic> listClinics, String sname, String scityname, String saztarikh, String statarikh);

    PaginatedListImpl<AzmayeshName> findAllAzmayeshNames(PaginatedListImpl<AzmayeshName> listAzmayeshNames, String sname, String snoe);

    PaginatedListImpl<AzmayeshType> findAllAzmayeshTypes(PaginatedListImpl<AzmayeshType> listAzmayeshTypes, String snoe);

    void addNewAzmayeshToAllClinic(Azmayesh azmayesh);

    void addAzemayeshToSomeClinics(Azmayesh azmayesh, Object[] clinicIds);

    public BankInfo findBankInfoById(Integer bankInfoId);
}
