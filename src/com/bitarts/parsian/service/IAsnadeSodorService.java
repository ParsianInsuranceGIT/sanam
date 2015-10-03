package com.bitarts.parsian.service;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.parsian.model.*;
import com.bitarts.parsian.model.asnadeSodor.*;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.DarkhastBazkharid;
import com.bitarts.parsian.model.constantItems.Tarh;
import com.bitarts.parsian.model.management.Hesab;
import com.bitarts.parsian.model.pishnahad.Fish;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.model.pishnahad.Shakhs;
import com.bitarts.parsian.service.calculations.BimeNaamehVaSarmayehGozari;
import com.bitarts.parsian.service.calculations.Reports.TaghsitReport;
import com.bitarts.parsian.viewModel.*;
import com.bitarts.parsian.viewModel.search.CredebitSearchForm;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 5/18/11
 * Time: 2:25 PM
 */
public interface IAsnadeSodorService extends IBaseService {
    public static final String SERVICE_NAME = "asnadeSodorService";

    public void saveKhateSanad(KhateSanad khateSanad);

    public boolean updateCredebitByIdentifier(String shomarePishnehad, String  shomareBimename);

    public List<Credebit> credebitsShenaseDarVosulNashode(Long namayandeId, Credebit.VaziyatVosoul vosolState);

    public void saveCredebit(Credebit credebit);

    public void saveCredebitObj(Credebit credebit);

    public void saveBankInfo(BankInfo bankInfo);

    public void saveVagozari(Vagozari vagozari);

    public GhestBandi findGhestBandi(Integer bimenameId, int saleBimei);

    public void editSanad(Sanad sanad);

    public PaginatedListImpl<Sanad> findAllSanadsPaginated();

    public PaginatedListImpl<KhateSanad> findAllKhateSanadsBySanadIdPaginated(Integer sanadId);

    public PaginatedListImpl<BankInfo> findAllBankInfosByCredebitId(Integer credebitId);

    public long getKarmozdVosul(String tarikhSodurBimename, Tarh tarh, long ghestAmount, long maliat, long haghBimePoosheshhayeEzafi, boolean isYekja, Double hazineVosul);

    public long getKarmozdPoosheshEzafi(long haghBimePoosheshhayeEzafi, Tarh tarh, String date);

    public CashTurnoverReportVM getCashTurnoverMap(String fromDate, String toDate,Namayande namayande);

//    public PaginatedListImpl<KhateSanad> findAllKhateSanads(String shomareSanad,Sanad.NoeSanad noeSanad,Sanad.Vaziat vaziat, String createdDateAz, String createdDateTa, String amountSanad,Credebit.CredebitType bedehiType,Credebit.CredebitType etebarType,String shomareMoshtariEtebar,String shenaseEtebar,String shomareMoshtariBedehi,String shenaseBedehi,String amountEtebar,String amountBedehi,String shoBimenameBedehi,String shoBimenameEtebar);

    public PaginatedListImpl<ViewKhateSanad> findAllKhateSanads(User user, int page,String searchShode, String shomareSanad,Sanad.NoeSanad noeSanad,Sanad.Vaziat vaziat, String createdDateAz, String createdDateTa, String amountSanad,Credebit.CredebitType bedehiType,Credebit.CredebitType etebarType,String shomareMoshtariEtebar,String shenaseEtebar,String shomareMoshtariBedehi,String shenaseBedehi,String amountEtebar,String amountBedehi,String shoBimenameBedehi,String shoBimenameEtebar,String shomareSanadBank, Long namayandeId, int subSystemName, String shomareCheck, Long bazaryabSanamId, String shomareFish, String SystemName);

    public void saveAghsat(List<Ghest> ghestList);

    public Sanad sabteSanad(List<Credebit> bedehiCredebitList, List<Credebit> etebarCredebitList, Sanad.NoeSanad noeSanad, Sanad.Vaziat vaziat, User user, boolean isAutomatic);

    public Sanad sabteSanad(User user, Credebit bedehiCredebit, Credebit etebarCredebit, Sanad.NoeSanad noeSanad, Sanad.Vaziat vaziat, boolean isAutomatic);

    public List<Sanad> sabteSanadSemiAutomatic(User user, List<Credebit> bedehiCredebitList, List<Credebit> etebarCredebitList);

    List<Ghest> findFirstBedehi(int pishnehadId);

    public long hazfHaghbimePoosheshEzafi(DarkhastBazkharid theDarkhast);

    public double AmrazOrNaghsKhesaratProcesses(DarkhastBazkharid theDarkhast) throws BimeNaamehVaSarmayehGozari.CustomValidationException;

    List<Credebit> findAllCredebits(boolean sanadKhorde);

    public List<KhateSanad> findAllKhateSanadByCredebitId(Integer id);

    public List<KhateSanad> findAllKhateSanadByEtebarCredebitId(Integer id);

    public List<Ghest> findAllGhests(Integer bimenameId);

    public PaginatedListImpl<Ghest> findAllBedehisByBimenameIdPaging(Integer id, PaginatedListImpl<Ghest> pgList);

    List<TaghsitReport> mohasebeyeAghsat(Pishnehad pishnehad, int saleBimei, String tarikhShorou, boolean daemha) throws BimeNaamehVaSarmayehGozari.CustomValidationException;

    public List<TaghsitReport> namayesheAghsat(Pishnehad pishnehad, int saleBimei, String tarikhShorou);

    public void deleteGhestBandiById(GhestBandi gb);

    public void saveGhest(Ghest ghest);

    public void saveGhestList(List<Ghest> gList);

    public void deleteGhestsById(Integer ghestId);

    public List<Ghest> saveGhestbandi(Pishnehad pishnehad, int saleBimei, User creator) throws BimeNaamehVaSarmayehGozari.CustomValidationException ;

    public List<Ghest> saveGhestbandi(Pishnehad pishnehad, int saleBimei, List<TaghsitReport> taghsitReports, User creator);

    public void saveGhest(TaghsitReport report, GhestBandi ghestBandi);

//    public Long getMajmuAghsatSalChapp(Pishnehad pishnehad, String tarikhShorou, int saleBimei, boolean daemha, Long amountGhest);

//    public Long getMablagheAghestChapp(Pishnehad pishnehad, String tarikhShorou, int saleBimei, boolean daemha);

    public void getMabaleghForChapp(Pishnehad pishnehad, String tarikhShorou, int saleBimei, boolean daemha, PishnehadReport pr) throws BimeNaamehVaSarmayehGozari.CustomValidationException;

    public int getMablagheGhestTaghsitNashode(Pishnehad pishnehad, String tarikhShorou, int saleBimei, boolean daemha) throws BimeNaamehVaSarmayehGozari.CustomValidationException;

    void hazfeGhest(int ghestId);

    void saveDaryafteFishElectroniki(DaryafteFish daryafteFish);

    void saveDaryafteCheck(DaryafteCheck daryafteCheck);

    PaginatedListImpl<Credebit> findAllCredebitsByFilterPaginated(CredebitSearchForm credebitSearchForm,User user, boolean sendToList);

    void createBedehiDasti(Ghest ghest);

    Ghest findBedehiById(Integer bedehiId);

    public Credebit createEtebareDasti(Credebit credebit,User user);

    public void wirayeshEtebareCheck(Credebit credebit);

    public GhestBandi findGhestBandiById(int ghestBandiId);

    public Sanad findSanadById(Integer sanadId);

    public void saveGhestBandi(GhestBandi ghestBandi);

    public void updateGhestbandi(GhestBandi ghestBandi);

    public void updateGhest(Ghest ghest);

    public void saveCredebitList(List<Credebit> credebitList);

    public List<Credebit> findCredebitForTaeedFish(Fish fish);

    public PaginatedListImpl<Credebit> findAllEtebarCredebitsPaginated(User user);

    public Credebit findBedehiByCodeMoshtari(String codeMoshtari, Long amount);

    public List<Credebit> findBedehiByBimenameAndAmount(String shomareBimename, Long amount);

    public Credebit findCredebitByCodeMoshtari(String CodeMoshtari);

   public boolean isRepetitiousCodeMoshtari(String codeMoshtari);

    public List<GhestBandi> ghestBandiList4SaleAval(Integer bimenameId);

    public PaginatedListImpl<Credebit> findAllCredebitsPaginated(User user, Credebit.CredebitMainType credebitNoe,String identifier,String shomareMoshtari,String rcptName,String sarresidDateFrom,String sarresidDateTo,String createdDateFrom,String createdDateTo,String amount,String paidReceivedAmount,String remainingAmount,Credebit.CredebitType credebitTypeFarsi,String shomareGharardad, Credebit.Status statusFarsi,Credebit.VaziyatVosoul vosoulStateFarsi,Long namayandeId, String bankName, String subsystem_field, String shomareCheck, String shomareFish);

    public PaginatedListImpl<Credebit> findListCredebitShabaPaginated(User user,Credebit.CredebitMainType credebitNoe,String identifier,String shomareMoshtari,String rcptName,String sarresidDateFrom,String sarresidDateTo,String createdDateFrom,String createdDateTo,String amount,String paidReceivedAmount,String remainingAmount,String shomareGharardad, Credebit.Status statusFarsi,Credebit.VaziyatVosoul vosoulStateFarsi,Long namayandeId, String bankName, String subsystem_field);

    public PaginatedListImpl<BankInfo> findAllBankInfoPaginated(User user, String createdDateFrom, String createdDateTo, String tarikhVarizFrom, String tarikhVarizTo, String fullname, String codeMoshtari, String vosoulDesc);

    public PaginatedListImpl<Credebit> findListGozareshVosouli(User user,Credebit.CredebitMainType credebitNoe,String identifier,String shomareMoshtari,String rcptName,String sarresidDateFrom,String sarresidDateTo,String createdDateFrom,String createdDateTo,String amount,String paidReceivedAmount,String remainingAmount,Credebit.CredebitType credebitTypeFarsi,String shomareGharardad, Credebit.Status statusFarsi,Credebit.VaziyatVosoul vosoulStateFarsi,Long namayandeId, String bankName, String subsystem_field);

    public Credebit.VaziyatVosoul getVaziyatVosulBedehi(Integer credebitId);

    public PaginatedListImpl<Credebit> findAllBedehiCredebitsPaginated(int page, User user, Credebit.CredebitMainType credebitNoe,String identifier,String shomareMoshtari,String rcptName,String sarresidDateFrom,String sarresidDateTo,String createdDateFrom,String createdDateTo,String amount,String paidReceivedAmount,String remainingAmount,Credebit.CredebitType credebitTypeFarsi,String shomareGharardad, Credebit.Status statusFarsi,Credebit.VaziyatVosoul vosoulStateFarsi, Long namayandeId, Long vahedesodorId, boolean isSearch);

    public PaginatedListImpl<Credebit> findAllBedehiCredebitsPaginated(User user);

    public List<DaryafteCheck> findAllDaryafteChecks();

    public List<Hesab> findAllHesabs();

    DaryafteCheck findDaryafteCheckById(Integer id);

    void updateDaryafteCheck(DaryafteCheck theCheck);

    void updateBimename(Bimename bimename);

    void updateBimename(Collection<Bimename> bimename);

    void saveMosharekatDarManafe(Mosharekat mosharekatDarManafe);

    Credebit findCretebitById(Integer id);

    void updateCredebit(Credebit credebit);

    Mosharekat findMosharekatById(Integer id);

    List<Credebit> findEtebar(Credebit credebit);

    void updateMosharekatDarManafe(Mosharekat mosharekatDarManafe);

    PaginatedListImpl<AghsatMoavagh> findAllAghsatMoavagh(AghsatMoavagh aghsatMoavagh);

    public long getJameSadere(Integer bimenameId);

    public long getJameSadereForGhestbandi(Integer ghestbandiId);

    public long getJamePoosheshAsliSadere(Integer bimenameId);

    public long getJamePoosheshhayeEzafiSadere(Integer bimenameId);

    public long getHagheBimeElamBeMaliSaleAvval(Integer pishnehadId);

    List<Ghest> findAllGhestForBimenameAndKarmozd(Bimename bimename);

    List<Ghest> findGhests(Integer ghestBandi, Credebit.CredebitType ghest);

    Credebit getCredebitByShomareMoshtari(String shenase, String sub);

    Credebit getCredebitByShenase(String shenase, String sub);

    List<Credebit> getCredebitByTypeForBimename(Credebit.CredebitType type, String shomareBimename);

    int getRemainingByIdentifier(String identifer, String sub);

    Credebit getCredebitByUniqueCode(String uniqueCode, String sub);

    void reverseKhateSanadList(List<KhateSanad> khateSanadList);

    void setAndukhteAndArzeshBazkharid(Bimename bimename, String date);

    CashFlow getBimenameCashFlow(Bimename bimename, String date);

    public String updateArzesheBimename(String date, double soud,Integer bimenameId);

    public String updateArzesheBimenameTajmi(String date, double soud ,List<Integer> bimenameIds);

    public String updateMohasebeArzeshBazkharidi(String date, Integer bimenameId);

    public String updateMohasebeArzeshBazkharidiTajmi(String date ,List<Integer> bimenameIds);

    public String updateAndukhte(String date, double soud ,Integer bimenameId) throws SQLException;

    public String updateAndukhteTajmi(String date, List<ZakhireRiaziVM> bimenameIds) throws SQLException;

    public String updateAndukhteTajmi(String date1,String date2, double soud ,List<Integer> bimenameIds) throws SQLException;

    public Credebit getCredebitByShomareMoshtari(String shomareMoshtari);

    public Credebit findEtebarCredebitsByCodeMoshtari(String codeMoshtari,String amount);

    public Credebit findEtebarCredebitsByCodeMoshtariAndTypeOfSystem(String typeOfSystem,String codeMoshtari);

    public Credebit findBedehiCredebitsByCodeMoshtari(String codeMoshtari,String amount);

    public List<Ghest> findGhestByRemainingAndSareresid();

    public List<Bimename> findBimenameByGhest(List<Ghest> ghestList);

    public Shakhs findShakhsByBimename(Bimename bimename);

    public Map<Integer,List<String>> findInformationGhest10DayBeforeForSMS();

    public Map<Integer,List<String>> findInformationGhest20DayAfterForSMS();

    public Long getRemainingAmountBedehiSanadNakhorde(Long namayandeId);

    public Long getRemainingAmountEtebarSanadKhordeVosulNashode(Long namayandeId, String etebarType);

    public PaginatedListImpl<Credebit> findAllCheck(String shomareHesab,String shomareCheck,String sarresidDate,String checkSerri,String rcptName,String branchName,String branchCode,String accountOwnerName,String amount,User user);

    public PaginatedListImpl<Vagozari> findListVaghozariCredebitPaginated(User user);

    public Integer getVagozariPerCredebit(Integer vagozariId);

    public List<Credebit> findCheckByVagozari(Integer vagozariId);

    public Vagozari findVagozariById(Integer vagozariId);

    public PaginatedListImpl<BatchTaghsitVM> findBatchTaghsit(PaginatedListImpl<BatchTaghsitVM> pgList, BatchTaghsitVM searchObj);

    public Integer countAghsatePardakhti(Integer bimenameId);

    public List<GhestBandi> findGhestBandiListForPrintDaftarche(BatchTaghsitVM searchObj);

    public void saveLogPrintDaftarche(LogPrintDaftarche log);

    public List<LogPrintDaftarche> findAllLogPrinteDaftarcheByGhestBandiId(Integer id);

    public PaginatedListImpl<Credebit> findAllEtebaratVosulNashodeCredebitsPaginated(int page, User user, Credebit.CredebitMainType credebitNoe, String identifier, String shomareMoshtari, String rcptName, String sarresidDateFrom,String sarresidDateTo,String createdDateFrom,String createdDateTo, String amount, String paidReceivedAmount, String remainingAmount, String shomareGharardad, Credebit.Status statusFarsi, Credebit.VaziyatVosoul vosoulStateFarsi, Long namayandeId);

    public PaginatedListImpl<Credebit> findMoghayeratDarVosolNamayandePaginated(int page, User user, Credebit.CredebitMainType credebitNoe, String identifier, String shomareMoshtari, String rcptName, String sarresidDate, String createdDate, String amount, String paidReceivedAmount, String remainingAmount, String shomareGharardad, Credebit.Status statusFarsi, Credebit.VaziyatVosoul vosoulStateFarsi, Long namayandeId,String vosoulDesc);

    public PaginatedListImpl<CredebitVM> findBedehiVosulNashodeNamayandePaginated(int page, User user, Credebit.CredebitMainType credebitNoe, String identifier, String shomareMoshtari, String rcptName, String sarresidDateFrom , String sarresidDateTo , String createdDateFrom, String createdDateTo, String amount, String paidReceivedAmount, String remainingAmount, String shomareGharardad, Credebit.Status statusFarsi, Credebit.VaziyatVosoul vosoulStateFarsi, Long namayandeId,String vosoulDesc, boolean isSearch);

    public boolean validationDeleteEtebar(Integer credebitId, User user);

    public boolean validationDeleteBedehi(Integer credebitId);

    public void deleteCredebitById(Integer credebitId);

    public void deleteDaryaftFishById(Integer daryaftFishId);

    public boolean deleteSanad(Integer sanadId);

    public Credebit findAnyCredebitByCodeMoshtari(String codeMoshtari);

    public Credebit findPardakhtShenaseDarCredebitByCodeMoshtari(String codeMoshtari);

    public Credebit executeCredebitFileUpload(Credebit credebit, BankInfo bankInfo, Long amountParamLong);

    public int getTatilatFromTwoDate(String fromDate, String toDate);

    public List<Integer> findPishnehadsForAutoBatchTaghsit(boolean manual);

    public List<GhestBandi> findInvalidBimenames();

    public List<Integer> temp_findQueuedIds();

    public List<Credebit> temp_findQueuedCredebits();

    public Long getHaghBimePayePardakhtiSaalAvval(Integer bimenameId);

    public Long getKoleHagheBimePardakhtiSaalAvval(Integer bimenameId);

    public List<Credebit> findCredebit(String identifier, Credebit.CredebitType type);
    public List<Credebit>  findCredebitbyStatusACHPayment (Credebit.ACHStatus status);

    public List<Credebit> findCredebitsByIdentifier(String shomarePishnehad);

    public List<CredebitBimenameVM> findCredebitsByIdentifier(String shomareBimenameFrom,String shomareBimenameTo,User user);

    public void refreshObject(Object object);

    public PaginatedListImpl<GhestVam> aghsatVamReport(PaginatedListImpl<GhestVam> pgList, GhestVam gvSearch);

    public List<GhestBandi> listInvalidGhestBandiVams();

    public String hqlToSql(String hql);

    public boolean isAvailableAuthorityId(String authority);

    public List<EtebarBedehiVM> findCredebitbySubSystemIdentiferforBadane(String tarikheSodorFrom,String tarikheSodorTo,String vahedSodorId,User user);

    public List<EtebarBedehiVM> findCredebitbySubSystemIdentiferforcales(String tarikheSodorFrom,String tarikheSodorTo,String vahedSodorId,User user);

    public  List<CredebitBedehiVM> findAllBedehiCredebits( String bimegozarName, String sodorbimenameDateFrom, String sodorbimenameDateTo, String namayandeId,String pishShomareBimename,User user);

    public BazarYabSanam getBazarYabByCode(Long bazaryabCode);

    public void saveBAzarYabSanam(BazarYabSanam  bazarYabSanam);

    public List<Credebit> dev_findAllEtebarCredebitsByBankInfo();

    public Long getAmountCheckSanadKhordeVagozarNashode(Long namayandeId);

    public List<Integer> hazfeSanad(String created_date, Credebit.CredebitType type);

    //b-h
    public Credebit findCredebitDaryafteCheckByCodeMoshtari(String codemoshtari);
    //b-h
    public List<vaziateBedehiVaEtebar> estelamVaziateBedehiVaEtebar(String  uniqueCode,String subsystemName);

    //b-h
    public List<sooratVaziatMali_new> findBedehiByShomareBimeName(String shomarebimename,User user);

    //b-h
    public Credebit findAllCredebitsByCodeMoshtari(String codemoshtari,User user);
    //b-h
    public boolean deleteSanadForBankExcelFile(Integer sanadId);
    //b-h
    public PaginatedListImpl<Credebit> findOnlyDaftarParsianCredebits(User user,PaginatedListImpl<Credebit> paginatedList,String identifier,String rcptName,String sarresidDateFrom,String sarresidDateTo,String createdDateFrom,String createdDateTo,Long search_namayandegiId,Long search_vahedesodorId,Long bazaryabSanamId);
    //b-h
    public Credebit enteghalBedehiBeDaftarNamayande(Credebit Parsianbedehi,Daftar daftar,Integer noeEnteghal);
    public Daftar findDaftarIdByNamayandeName(Long namayandeId);
    public Daftar findDaftarById(Integer id);
    public Daftar findDaftarByCodeNamayande(Long namayandeId);
    public int tedadDaftareNamayande(Long namayandeID);
    public PaginatedListImpl<bedehiTasviyeNashode> listBedehiTasviyeNashodeNamayande(int page,User user,String identifier,String rcptName,String sarresidDateFrom,String sarresidDateTo,String createdDateFrom,String createdDateTo,String amount,String remainingAmount,Long search_namayandegiId,Long search_vahedesodorId,Long bazaryabSanamId,String bedehiColor, int reshte, int consortium, boolean isSearch);
    public List<sooratVaziatMali_new> findbedehiNamayandeForGozaresh(Long namayandeId,User user);
}
