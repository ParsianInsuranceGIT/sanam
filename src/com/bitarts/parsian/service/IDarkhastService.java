package com.bitarts.parsian.service;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.parsian.model.State;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.bimename.*;
import com.bitarts.parsian.service.calculations.BimeNaamehVaSarmayehGozari;
import com.bitarts.parsian.viewModel.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/8/11
 * Time: 5:59 PM
 */
public interface IDarkhastService extends IBaseService  {
    public static final String SERVICE_NAME = "darkhastService";

    public PaginatedListImpl<Darkhast> searchDarkhasts(PaginatedListImpl pgList,User user, String darkhastType ,String shomareBimename, Long namayandeId, String karshenas, String azTarikheDarkhast, String taTarikheDarkhast,
                                          String bimeGozarName, String bimeGozarFamily, String bimeGozarKodeMelli, String bimeShodeName, String bimeShodeFamily, String bimeShodeKodeMelli,List<String> darkhastState);

    public PaginatedListImpl<DarkhastsVM> myDarkhasts(PaginatedListImpl<DarkhastsVM> pgList,DarkhastsVM searchVM);

    public List<DarkhastBazkharid> searchDarkhastBazkharidsPaginated(String shomareBime, String azTarikheDarkhast, String taTarikheDarkhast, Long namayandegiId, String nameBimeGozar,
                                                               String familyBimeGozar, String kodeMelliBimeGozar, String  nameBimeShode, String  familyBimeShode, String kodeMelliBimeShode, String karshenas);

    public List<DarkhastTaghirat> searchDarkhastTaghiratsPaginated(String shomareBime, String azTarikheDarkhast, String taTarikheDarkhast, Long namayandegiId, String nameBimeGozar,
                                                               String familyBimeGozar, String kodeMelliBimeGozar, String  nameBimeShode, String  familyBimeShode, String kodeMelliBimeShode, String karshenas);

    public int saveDarkhastBazkharid(DarkhastBazkharid darkhastBazkharid);

    public int saveDarkhastKhesarat(DarkhastBazkharid darkhastBazkharid);

    public int saveDarkhastBazkharidObj(DarkhastBazkharid darkhastBazkharid);

    public int saveDarkhastVam(DarkhastBazkharid darkhastBazkharid);

    public int saveDarkhastBardashtAzAndokhte(DarkhastBazkharid darkhastBazkharid);

    public int saveDarkhasteTasvieyePishAzMoedeVam(DarkhastBazkharid darkhastBazkharid);

    public void updateDarkhastBazkharid(DarkhastBazkharid darkhastBazkharid);

    public List<DarkhastBazkharid> findAllDarkhastBazkharid();

    public List<DarkhastBazkharid> findDarkhastKhesaratBeduneState();

    public void cleanChangeWithSerachField();

    public DarkhastBazkharid findDarkhastBazkharidById(Integer darkhastBazkharidId);

    public void transitDarkhast(User user, Integer darkhastId, Integer transitionId, String logmessage);

    public void transitKhesarat(User user, Long khesaratId, Integer transitionId, String logmessage);

    public void updateBimenameStateEbtal(Bimename bimename);

    public void updateZamaem(ZamaemDarkhast theZamime);

    public void saveZamaemDarkhast(ZamaemDarkhast zamaemDarkhast);

    public ZamaemDarkhast findZamaemDarkhastById(int zamaemId);

    public void saveElhaghiye(Elhaghiye elhaghiye);

    public void updateElhaghiye(Elhaghiye elhaghiye);

    public PaginatedListImpl<Elhaghiye> findAllElhaghiyes(PaginatedListImpl<Elhaghiye> pgList,Long userId);

    public PaginatedListImpl<Elhaghiye> searchForElhaghiye(ElhaghiyeSearch elhaghiyeSearch, PaginatedListImpl<Elhaghiye> pgList,Long userId);

    public Darkhast findDarkhastById(Integer id);

    public List<State> findByStateMachine(String system);

    public int saveDarkhastTaghirat(DarkhastTaghirat darkhastTaghirat, User user, String tarikhAsar);

    public List<DarkhastTaghirat> findAllDarkhastTaghir();

    DarkhastTaghirat findDarkhastTaghiratById(Integer id);

    void transitDarkhastTaghirat(User theUser, Integer id, Integer transitionId, String logmessage);

    void updateDarkhastTaghirat(DarkhastTaghirat theDarkhast);

    void saveDarkhasteElhaghieTaghyirat(Darkhast darkhast);

    public PaginatedListImpl<Darkhast> findAllDarkhastsUnFinished(User user,int pageNum);

    public PaginatedListImpl findAllDarkhastsUnFinished(User user,PaginatedListImpl pgList);

    @Transactional
    public void saveDarkhast(Darkhast darkhast);

    @Transactional(readOnly = true)
    public PaginatedListImpl<Darkhast>  loadDarkhasts(User user,int pageNum);

    @Transactional(readOnly = true)
    public PaginatedListImpl<DarkhastsVM>  loadDarkhasts(User user,PaginatedListImpl<DarkhastsVM> pgList);

    public PaginatedListImpl<Darkhast> searchHameyeDarkhastha(SearchDarkhasts sd,User user, int page);

    public List<User> findAllKarshenasForDarkhasts();

    public List<DarkhastBazkharid> findAllUnfinishedDarkhastBazkharid();

    public List<DarkhastTaghirat> findAllUnfinishedDarkhastTaghir();

    public int saveDarkhastTaghirKod(DarkhastBazkharid darkhast);

    void removeDarkhast(DarkhastBazkharid darkhastBazkharid);

    public int saveDarkhastTozih(DarkhastBazkharid darkhast);

    public PaginatedListImpl<BahreMandiVM> findBahreMandi(PaginatedListImpl<BahreMandiVM> pgList, BahreMandiVM searchVM);

    public List<DarkhastBazkharid> findTasviePishAzMoedWithSttMontazerPardakhtAfter30Day();

    public PaginatedListImpl<ElhaghieVM> elhaghieReport(PaginatedListImpl<ElhaghieVM> pgList, ElhaghieVM svm);

    List<ListBimenameTaghirVM> getListBimenamehayeTaghirKarde(DarkhastTaghirat darkhastTaghirat);

    public String emalElhaghiyeTaghirat(DarkhastTaghirat darkhastTaghirat, Elhaghiye elhaghiye, User user, boolean mohasebeMablaghElhaghie) throws BimeNaamehVaSarmayehGozari.CustomValidationException;

    public void deleteDarkhast(Darkhast darkhast);

    public void deleteDarkhastBazkharid(DarkhastBazkharid darkhastBazkharid);
}
