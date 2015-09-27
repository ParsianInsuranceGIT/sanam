package com.bitarts.parsian.service;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.parsian.model.BazarYabSanam;
import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.SeniorSubset;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.viewModel.GStructureNamayande;
import com.bitarts.parsian.viewModel.MaliNamayande;
import com.bitarts.parsian.viewModel.RankReport;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ramtinb
 * Date: 4/10/12
 * Time: 11:22 AM
 * To change this template use File | Settings | File Templates.
 */
public interface INamayandeService extends IBaseService  {
    public static final String SERVICE_NAME = "namayandeService";

    public int findNamayandeOfVahedSodor(Long namayandeId);

    public PaginatedListImpl<SeniorSubset> findAllSeniorSubset(PaginatedListImpl<SeniorSubset> pgList);

    public void saveOrUpdateSeniorSubset(SeniorSubset seniorSubset);

    public void saveOrUpdateSeniorSubsetList(List<SeniorSubset> seniorSubsetList);

    public PaginatedListImpl<SeniorSubset> findAllSeniorSubset(SeniorSubset seniorSubset,PaginatedListImpl<SeniorSubset> pgList);

    public void addNewNamayande(Namayande namayande);

    public void editNamayande(Namayande namayande);

    public Namayande getNamayandeById(Long id);

    public BazarYabSanam getBazaryabSanamById(Long id);

    public List<BazarYabSanam> getBazaryabSanam(String name, String code, User user);

    public PaginatedListImpl<Namayande> findAllNamayande(PaginatedListImpl paginatedList);

    public PaginatedListImpl<Namayande> findAllSenior(PaginatedListImpl paginatedList);

    public List<Namayande> getAllNamayande();

    public PaginatedListImpl<MaliNamayande> findMaliNamayandeHaList(String sarresidDateFrom,String sarresidDateTo,Long namayandeId);

    public void removeNamayande(Long id);

    public List<Namayande> getAllNamayandeByType(String type);

    public List<Namayande> getAllVahedSodurs();

    List<Namayande> searchNamayande(RankReport rankReport);

    public List<Namayande> getAllNamayandegi();

    public List<GStructureNamayande> makeGozareshMoghayese(Integer filter, String azTarikh, String taTarikh);

    public List<Namayande> doSearch(String name, String code);

    public List<BazarYabSanam> doSearchBazSan(String name, String code);

    public PaginatedListImpl<Namayande> findAllNamayande(PaginatedListImpl<Namayande> namayandeList, String nname, String ncode, String ntype, Long vSodurId, Long ostanId, Long shahrId);

    public Namayande getNamayandeByKodeKargozar(String kodeKargozar);

    public List<Namayande> getChildNamayande(Long namayandeId);

    public List<Namayande> getChildNamayande(String name, String code, User user);
}
