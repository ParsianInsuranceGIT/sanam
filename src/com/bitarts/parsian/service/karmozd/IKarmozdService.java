package com.bitarts.parsian.service.karmozd;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.asnadeSodor.Ghest;
import com.bitarts.parsian.model.asnadeSodor.KhateSanad;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.estelam.Estelam;
import com.bitarts.parsian.model.karmozd.*;
import com.bitarts.parsian.viewModel.KarmozdNamayandeVM;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 5/18/11
 * Time: 2:25 PM
 */
public interface IKarmozdService {
    public static final String SERVICE_NAME = "karmozdService";

    public void saveKarmozdNamayande(KarmozdNamayande kn);

    public KarmozdNamayande findKarmozdNamayandeForNamayande(Long karmozdId , Long namayandeId);

    public List<KarmozdGhest> findKarmozdGhestByBimenameId(Integer id, KarmozdNamayande.State state,Karmozd.Type karmozdType);

    public long karmozdBimename(Bimename bimename, Estelam estelam);

    public List<KarmozdGhest> disjoinKarmozds(Integer sanadId);

    public void saveTadilat(KarmozdTadilat tadilat);

    public void disjoinKarmozdsAndCreateTadilat(Integer sanadId);

    public PaginatedListImpl<BlackList> loadAllBlackListPaging(PaginatedListImpl<BlackList> pgList);

    public  List<BlackList> loadAllBlackList(BlackList.Type type);

    public BlackList findBlackListByBimenameId(Integer id, BlackList.Type type);

    public void addToBlackList(Bimename bimename, BlackList.Type type);

    public List findKarmozdGhestByKarmozdNamayandeId(Long karmozdNamayandeId);

    public KarmozdNamayande findKarmozdNamayandeById(Long id);

    public PaginatedListImpl<KarmozdNamayande> findKarmozdNamayandeByKarmozdId(PaginatedListImpl<KarmozdNamayande> pgList,Long karmozdId, String state);

    public String getSerialKarmozd(String str);

    public PaginatedListImpl<KarmozdNamayande> searchKarmozdNamayanade(PaginatedListImpl<KarmozdNamayande> pgList,Long karmozdId, String state,String namayandeName,Long namayandeId);

    @Transactional(readOnly = false)
    public void saveOrUpdateKarmozdNamayande(KarmozdNamayande karmozdNamayande);

    @Transactional(readOnly = false)
    public void saveKarmozdNamayandeList(List<KarmozdNamayande> knList);

    public List findDistinctNamayandeOfGhestByKarmozdId(Long karmozdId);

    @Transactional(readOnly = true)
    public List<KhateSanad> findGhestsByTarikhVosoli(String azTarikhVosoli,String taTarikhVosoli);

    public List findKarmozdGhestsBazkharidOrEbtal(String azTarikh, String taTarikh);

    public List<KarmozdGhest> findKarmozdGhestForTaghirat(String azTarikh, String taTarikh);

    public List findKarmozdGhestsCodeMovaghat(String azTarikh, String taTarikh);

    public List findKarmozdGhestsChangeNamayande(String azTarikh, String taTarikh);

    public List<KarmozdGhest> findKarmozdGhestDoubleTaghirat(String azTarikh, String taTarikh);

    public void karmozdEbtalOrBazkharidProcess(List<KarmozdGhest> kgBzkhrdOrEbtlShde, Karmozd karmozdOb, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap);

    public void karmozdTaghiratProcess(List<KarmozdGhest> kgTaghirat, Karmozd karmozdOb, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap, String azTarikh, String taTarikh);

    public void karmozdDoubleTaghiratProcess(List<KarmozdGhest> kgDoubleTaghirat, Karmozd karmozdOb, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap, String azTarikh, String taTarikh);

    public void karmozdBeCodeMovaghatProcess(List<KarmozdGhest> kgCodeMovaghat, Karmozd karmozdOb, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap, String azTarikh, String taTarikh);

    public void karmozdTaghirCodeDaemProcess(List<KarmozdGhest> kgTaghirCode, Karmozd karmozdOb, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap, String azTarikh, String taTarikh);

    public List findGhestsYekja(String azTarikhVosoli,String taTarikhVosoli);

    public List<Ghest> findGhestsYekjaForVosuliOrPoshesh(String azTarikhVosoli, String taTarikhVosoli, Karmozd.Type type);

    public List<KhateSanad> findKhateSanadGYekjaForVosuliOrPoshesh(String azTarikhVosoli, String taTarikhVosoli, Karmozd.Type type);

    public void karmozdGheyreYekjaProcess(List<KhateSanad> khateSanadList,String azTarikhVosoli, String taTarikhVosoli, List<Ghest> followGhestList, Karmozd karmozdOb, List<BlackList> blackLists, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap);

    public void karmozdYekjaProcess(List<Ghest> ghestListYekja, String taTarikhVosoli,String azTarikhVosoli, List<Ghest> followGhestList, Karmozd karmozdOb, List<BlackList> blackLists, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap);

    public void karmozdYekjaProcessForVosulOrPooshesh(List<Ghest> ghestListYekja, String taTarikhVosoli, String azTarikhVosoli, List<Ghest> followGhestList, Karmozd karmozdOb, List<BlackList> blackLists, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap, Karmozd.Type type);

    public void karmozdProcessForVosulOrPooshesh(List<KhateSanad> khateSanadList, String azTarikhVosoli, String taTarikhVosoli, List<Ghest> followGhestList, Karmozd karmozdOb, List<BlackList> blackLists, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap, Karmozd.Type type);

//    public boolean createKarmozdProject(String azTarikhVosoli, String taTarikhVosoli, User user,Karmozd karmozd);

    public List findKarmozdGhestByKarmozdId(Long karmozdId);

    public List findKarmozdGhestByKarmozdIdForNamayande(Long karmozdId,Integer namayandeId);

    public PaginatedListImpl<KarmozdGhest> findKarmozdGhestByKarmozdId(PaginatedListImpl<KarmozdGhest> pgList,Long karmozdId,Namayande n);

    public PaginatedListImpl findAllPaymentByKarmozdId(PaginatedListImpl pgList, long karmozdId,Namayande n,Karmozd.Type type);

    public PaginatedListImpl<KarmozdGhest> searchKarmozdGhest(PaginatedListImpl<KarmozdGhest> pgList,Long karmozdId, String shomareMoshtari, String shenaseEtebar,String kodeNamayande,String shomareBimename);

    public Karmozd findById(Long id);

    public KarmozdGhest findKarmozdGhestById(Long id);

    public PaginatedListImpl<Karmozd> findAllKarmozds(PaginatedListImpl<Karmozd> pgList,User user);

    public PaginatedListImpl<Ghest> findGhestsByKarmozdId(PaginatedListImpl<Ghest> pgList,Long karmozdId);

    public List<Karmozd> findByNamayande(Integer namayandeId);

    public KarmozdYekja findByIdYekja(Long id);

    public List<KarmozdYekja> findByNamayandeYekja(Integer namayandeId);

    public KarmozdYekja findByBimenameYekja(Integer bimenameId);

    public boolean isThereNotFinal();

    @Transactional(readOnly = false)
    void save(Karmozd k);

    @Transactional(readOnly = false)
    public void update(Object o);

    @Transactional(readOnly = false)
    void saveYekja(KarmozdYekja k);

    @Transactional(readOnly = false)
    public void saveKarmozdGhest(KarmozdGhest kg);

    @Transactional(readOnly = false)
    public void saveKarmozdGhestMap(HashMap<Namayande, List<KarmozdGhest>> kgMap);

    @Transactional(readOnly = false)
    public void saveKarmozdGhestList(List<KarmozdGhest> kgList);

    @Transactional(readOnly = false)
    public void saveOrUpdateAll(Collection col);

    @Transactional(readOnly = false)
    void addKarmozedTadilatList(List<KarmozdTadilat> karmozdTadilatList);

    PaginatedListImpl<KarmozdTadilat> findKarmozedTadilat(PaginatedListImpl<KarmozdTadilat> pgList, Long karmozdId);

    void pardakhtKarmozdForKarmozdNamayande(List<KarmozdNamayande> karmozdNamayandeList);

    public List<KarmozdGhest> findKarmozdGhestsForSenior(String azTarikh, String taTarikh);

    public void createKarmozdFromTadilat(Karmozd karmozd, String azTarikh, String taTarikh, Karmozd.Type type, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap);

    public void processCalcKarmozdSeniors(List<KarmozdGhest> kgForSeniorKarmozd, Karmozd karmozdOb, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap);

    public PaginatedListImpl<KarmozdNamayandeVM> getReportTashvighiList(PaginatedListImpl<KarmozdNamayandeVM> pgList, KarmozdNamayandeVM knSVM);

    public String karmozdTashvighiStepOne(Karmozd karmozd, String azTarikh, String taTarikh);

    public void createKarmozdGhestTashvighi(Karmozd karmozd);

    public void setAmountTashvighiKarmozdNamayande(Karmozd karmozd);
}
