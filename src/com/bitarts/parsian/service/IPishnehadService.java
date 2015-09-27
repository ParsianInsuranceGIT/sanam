package com.bitarts.parsian.service;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.parsian.dao.BimenameDAO;
import com.bitarts.parsian.model.*;
import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.asnadeSodor.Ghest;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.DasteSerial;
import com.bitarts.parsian.model.bimename.Gharardad;
import com.bitarts.parsian.model.bimename.Serial;
import com.bitarts.parsian.model.constantItems.City;
import com.bitarts.parsian.model.pishnahad.*;
import com.bitarts.parsian.model.transitionwise.PezeshkSabtNazar;
import com.bitarts.parsian.viewModel.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/8/11
 * Time: 5:59 PM
 */
public interface IPishnehadService extends IBaseService  {
    public static final String SERVICE_NAME = "pishnehadService";

    public void saveOrUpdate(Object object);

    public boolean isMoreThanOnePishnehadByShakhs(String codeMeli);

    public void saveNewPishnehad(Pishnehad pishnehad);

    public void updatePishnehad(Pishnehad newPishnehad);

    public Set<Pishnehad> findAll();

    public DasteSerial lastDasteSerial();

    public void saveOrUpdateDasteSerials(DasteSerial dasteSerial);

    public boolean isAvailableSerial(Long shomareSerial);

    public Long minOrMaxSerialByIdDaste(String minOrmax,Integer idDaste);

    public List<Serial> findSerialsEstefadeNashode(Long serialFirst,Long serialLast);

    public DasteSerial findDasteSerialById(Integer id);

    public List<Serial> findSerialByShomareSerial(Long[] shomareSerials);

    public void saveOrUpdateSerials(List<Serial> serials);

    public PaginatedListImpl<DasteSerial> findAllDasteSerials(PaginatedListImpl paginatedList,String vaziateDaste,Long serialFirst,Long serialLast,Long namayandegi,String bimenameType,User user);

    public PaginatedListImpl<SerialsVM> findAllDasteSerials(PaginatedListImpl pgList, SerialsVM svmOb);

//    public PaginatedListImpl<Pishnehad> findAllowedPishnehads(User user, PaginatedListImpl<Pishnehad> paginatedList);

    public PaginatedListImpl<PishnehadsVM> findAllowedPishnehads(User user, PaginatedListImpl<PishnehadsVM> paginatedList);

    public PaginatedListImpl findAllowedToViewPishnehads(User user, PaginatedListImpl paginatedList);

    public boolean hasPermissionToViewAllPishnehad(User user, Integer pishnehadId,String tab);

    public Pishnehad findById(Integer pishnehadId);

    public Pishnehad loadPishnehadById(Integer id);

    public boolean hasPermissionToViewPisnehad(User user, Integer pishnehadId);

    List<Transition> findAllowedTransitionsForId(Integer id);

    public void transitPishnehad(Long userId, String pishnehadId, String transitionId, String logmessage);

    int addNewNazarPezeshk(PezeshkSabtNazar pezeshkSabtNazar);

    PezeshkSabtNazar findPezeshkSabtNazarById(int pznId);

    void updatePezeshkSabtNazar(PezeshkSabtNazar pzn);

    int addNewNazarRaeis(PezeshkSabtNazar pezeshkSabtNazar);

    int saveBimename(Bimename bimename);

    int saveNaghsPishnehad(NaghsPishnehad naghsPishnehad);

    void updateNaghsPishnehad(NaghsPishnehad naghsPishnehad);

    NaghsPishnehad findNaghsPishnehadById(Integer naghsId);

    void removeNaghsPishnehad(Integer naghsId);

    int saveFish(Fish fish);

    public Fish findFish(Integer fishId);

    public List<Fish> findFish(String shomare, String bankName, String shobe, String time, String date);

    public void saveMoarefiname(Moarefiname moarefiname);

    public Moarefiname findMoarefiname(Integer id);

    void updateFish(Fish theFish);

    int saveZamaem(Zamaem zamaem);

    Zamaem findZamaemById(int zamId);

    public ZamaemKhesarat findZamaemKhesaratById(int zamId);

    void updateZamaem(Zamaem theZamime);

    public int saveZamaemKhesarat(ZamaemKhesarat zamaemKhesarat);

    public void updateZamaemKhesarat(ZamaemKhesarat zamaemKhesarat);

    void removeNazarPezeshk(Integer id);

    List<Credebit> searchForFish(BankInfo bankInfo);

    public void updateSelfPishnehadOnly(Pishnehad pishnehad);

    Bimename findBimenameById(int bimenameId);

//    PaginatedListImpl<Bimename> findAllBimenamePaginated(User user, PaginatedListImpl<Bimename> pgList);

    PaginatedListImpl findAllBimenamePaginated(User user, PaginatedListImpl pgList);

    List<Bimename> findAllBimenamePaginatedForBimeGozar(User user);

    Transition findTransitionById(int id);

    PaginatedListImpl<Bimename> findAllBimenamePaginatedForMosharekat(User user, PaginatedListImpl<Bimename> pgList, String tarikhShoro, String tarikhPayan);

    public PaginatedListImpl findAllBimenameForMosharekat(String tarikhShoro, String tarikhPayan, PaginatedListImpl pgList);

    public List<Bimename> findAllBimenameForMosharekat(String tarikhShoro, String tarikhPayan);

    public String executeMosharekat(String tarikhShoro, String tarikhPayan, double i);

    public void updateBimeGozar(BimeGozar b);

    public void updateBimeShode(BimeShode b);

    void updateBimename(Bimename bimename);

    public Pishnehad savePishnehadForElhaghie(Pishnehad newPishnehad, Pishnehad oldPishnehad, PishnehadDuplicationRules mafad, Gharardad gharardad, User user, boolean shouldCreateShakhsBimeGozar, boolean shouldCreateShakhsBimeShode);

    public List loadAllBimenameByFilterForZakhire(String azTarikheSodoreBimename, String taTarikheSodoreBimename, String shomareBimename, boolean isExport, boolean onlyId);

    public List<Bimename> findByBimenameBazkharidShode(BimenameForGozaresh bimenamehSearch);

    public List<Bimename> findByBimenameSarresidShode(BimenameForGozaresh bimenamehSearch);

    public List findAllBimenameByFilterForZakhireTafkikGhest(String tarikhMabna,String azTarikheSodoreBimename, String taTarikheSodoreBimename, String shomareBimename, boolean isExport, boolean onlyId);

    public List<Bimename> loadAllBimenameByFilter(String azTarikheSodoreBimename, String taTarikheSodoreBimename, String shomareBimename);

    public PaginatedListImpl makeGozareshZakhireRiazi(PaginatedListImpl pgList,ZakhireRiazi zr);

    public List<Ghest> loadAllBimenameGhestsByFilter(String azTarikheSodoreBimename, String taTarikheSodoreBimename);

    void removeMoarefiname(Integer id);

    public boolean hasPermission(User user, Integer stateId);

    void saveSharayeteJadid(SharayeteJadid sharayeteJadid);

    void saveElameHesab(ElameHesab elameHesab);

    void updateElameHesab(ElameHesab elameHesab);

    List<Namayande> findAllNamayandes();

    List<State> findAllSatetsForPishnehadSystem();

    List<User> findAllKarshenasForPishnehads(String tab);

    List<User> findAllKarshenasForPishnehads(Long sarmayePayeFot);

    public List<User> findAllKarshenasKhesarat();

    User findKarshenasForNezaratAssignment(boolean nazer, boolean masool, boolean raees, User user);

    public List<User> findAllKarshenasForPishnehadsByCity(City city);

    List<BazarYab> findAllBazaryabs();

    public List<User> findBazaryabForNamayande(Namayande namayande);

    BazarYab findBazayabById(Integer id);

    List<PishnehadEstelam> searchPishnehadForBordoyeSodour(PishnehadSearch pishnehadSearch);

    public PaginatedListImpl<PishnehadEstelam> searchPishnehadForBordoyeSodourPaginated(PaginatedListImpl pgList, PishnehadSearch pishnehadSearch);

//    PaginatedListImpl<Pishnehad> findPishnehadsByPishnehadSearch(PishnehadSearch pishnehadSearch, User user, PaginatedListImpl<Pishnehad> paginatedList);

    PaginatedListImpl findPishnehadsByPishnehadSearch(PishnehadSearch pishnehadSearch, User user, PaginatedListImpl paginatedList);

//    PaginatedListImpl<Pishnehad> findPishnehadsToViewByPishnehadSearch(PishnehadSearch pishnehadSearch, User user, PaginatedListImpl<Pishnehad> paginatedList);

    PaginatedListImpl findPishnehadsToViewByPishnehadSearch(PishnehadSearch pishnehadSearch, User user, PaginatedListImpl paginatedList);

    void ebtalMoarefiname(Moarefiname theMoarefiname);

    void deletePishnehad(Integer id);

    void deleteFish(Integer id);

    public BimenameDAO getBimenameDAO();

    public void updateBimename(List<Bimename> bimenameha);

    public List<Credebit> findForMabaleghePardakhti(Bimename bimename, String azTarikh, String taTarikh);

    List<Bimename> findAllBimenameForNamayande(Long nId, String azDate, String taDate);

    List<Bimename> findAllBimenameForNamayande(Long id);

    List<Ghest> searchGhest(String shomare, String saleBimei, String bimeGozar, String bimeShode, String shomareMoshtari, String azTarikhSodur, String taTarikhSodur, String azSarresid, String taSarresid, String azPardakht, String taPardakht, String azMablagh, String taMablagh, String azSanad, String taSanad, State state, User user);

    List<Bimename> searchBimename(String bimeGozar, String bimeShode, String shomare, String azTarikhSodur, String taTarikhSodur, String azSarresid, String taSarresid, State state, Integer barresiPezeshki, String nahvePardakhtS, User user);

//    PaginatedListImpl<Bimename> searchBimenamePaginated(PaginatedListImpl<Bimename> pgList, String bimeGozarName,String bimeGozarFamily,String bimeGozarKodeMelli, String bimeShodeName,String bimeShodeFamily,String bimeShodeKodeMelli, String shomare, String azTarikhSodur, String taTarikhSodur, String azSarresid, String taSarresid, State state, Integer subsetState, Integer barresiPezeshki, String nahvePardakhtS, User user, String sarmayeFowt,String codeRahgiri, Integer namayandegiId,Integer vahedSodurId,String noe_tarh,String noeGharardad, String KarbareSabtKonande, Long groupId , Boolean isCodeMovaghat);

    PaginatedListImpl searchBimenamePaginated(PaginatedListImpl pgList, String bimeGozarName,String bimeGozarFamily,String bimeGozarKodeMelli, String bimeShodeName,String bimeShodeFamily,String bimeShodeKodeMelli, String shomare, String azTarikhSodur, String taTarikhSodur, String azSarresid, String taSarresid, State state, Integer subsetState, Integer barresiPezeshki, String nahvePardakhtS, User user, String sarmayeFowt,String codeRahgiri, Long namayandegiId,Long vahedSodurId,String noe_tarh,String noeGharardad, String KarbareSabtKonande, Long groupId , Boolean isCodeMovaghat);

    List<GStructure> makeGozareshTedadVaziat(Role role, String azTarikh, String taTarikh);

    Map<String, Double> gozareshNemudarData(String azTarikh, String taTarikh, boolean b);

    List<GStructureTafkikVaziat> makeGozareshTedadTafkik(String azTarikh, String taTarikh, User role);

    List<GStructureTafkikVaziat> makeGozareshTedadTafkikVaziat(State state);

    List<GStructureTransitionLog> makeGozareshTransitionLog(User user, String azTarikh, String taTarikh, String azVaziat, String beVaziat);

    List<Fish> makeGozareshPardakhtElectronic(String azTarikh, String taTarikh);

    List<Fish> makeGozareshSadereVarizi(String azTarikh, String taTarikh);

    Bimename findBimenameByShomare(String shomareBimename);

    PaginatedListImpl<Pishnehad> findAllByGharardadId(Long id);

    PaginatedListImpl<Bimename> findAllBimenameByGharardadId(Long id);

    Pishnehad findByRadif(String templatePishnehadRadif);

    void createAllPishnehadsMergeByTemplate(Pishnehad templatePishnehad, List<Pishnehad> pishnehads, PishnehadDuplicationRules mafad, Gharardad gharardad);

    List<Bimename> findAllBimenameByGharardadIdNonePaginated(Long id);

    public PaginatedListImpl<Pishnehad> gozareshAndukhteSaalAvaal(Long gharardadId);

}
