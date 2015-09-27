package com.bitarts.parsian.service.implementation;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.parsian.dao.NamayandeDAO;
import com.bitarts.parsian.model.BazarYabSanam;
import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.SeniorSubset;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.service.INamayandeService;
import com.bitarts.parsian.viewModel.GStructureNamayande;
import com.bitarts.parsian.viewModel.MaliNamayande;
import com.bitarts.parsian.viewModel.RankReport;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ramtinb
 * Date: 4/10/12
 * Time: 11:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class NamayandeService implements INamayandeService {

    private NamayandeDAO namayandeDAO;

    public NamayandeDAO getNamayandeDAO() {
        return namayandeDAO;
    }

    public void setNamayandeDAO(NamayandeDAO namayandeDAO) {
        this.namayandeDAO = namayandeDAO;
    }

    public void saveOrUpdateSeniorSubset(SeniorSubset seniorSubset)
    {
        namayandeDAO.saveOrUpdate(seniorSubset);
    }

    public void saveOrUpdateSeniorSubsetList(List<SeniorSubset> seniorSubsetList)
    {
        namayandeDAO.saveOrUpdateAll(seniorSubsetList);
    }

    public PaginatedListImpl<SeniorSubset> findAllSeniorSubset(PaginatedListImpl<SeniorSubset> pgList)
    {
        return namayandeDAO.findSeniorSubsets(null, pgList);
    }

    public PaginatedListImpl<SeniorSubset> findAllSeniorSubset(SeniorSubset seniorSubset,PaginatedListImpl<SeniorSubset> pgList)
    {
        return namayandeDAO.findSeniorSubsets(seniorSubset, pgList);
    }

    public void addNewNamayande(Namayande namayande) {
        namayandeDAO.addNewNamayande(namayande);
    }

    public void editNamayande(Namayande namayande) {
        namayandeDAO.editNamayande(namayande);
    }

    public Namayande getNamayandeById(Long id) {
        return namayandeDAO.getNamayandeById(id);
    }

    public BazarYabSanam getBazaryabSanamById(Long id)
    {
        return namayandeDAO.getBazaryabSanamById(id);
    }
    public List<BazarYabSanam> getBazaryabSanam(String name, String code, User user)
    {
        return  namayandeDAO.getBazaryabSanam(name, code, user);
    }

    public PaginatedListImpl<Namayande> findAllNamayande(PaginatedListImpl paginatedList) {
        return namayandeDAO.findAllNamayande(paginatedList);
    }

    public PaginatedListImpl<Namayande> findAllSenior(PaginatedListImpl paginatedList)
    {
        return namayandeDAO.findAllSenior(paginatedList);
    }

    public List<Namayande> getAllNamayande() {
        return namayandeDAO.getAllNamayande();
    }

    public PaginatedListImpl<MaliNamayande> findMaliNamayandeHaList(String sarresidDateFrom,String sarresidDateTo,Long namayandeId) {
        return namayandeDAO.findMaliNamayandeHaList(sarresidDateFrom,sarresidDateTo, namayandeId);
    }

    public void removeNamayande(Long id) {
        namayandeDAO.removeNamayande(id);
    }

    public Namayande getVahedSodur(Long id) {
        return namayandeDAO.getNamayandeById(id);
    }

    public List<Namayande> getAllNamayandeByType(String type) {
        return namayandeDAO.getAllNamayandeByType(type);
    }

    public List<Namayande> getAllVahedSodurs() {
        return getAllNamayandeByType("4");
    }

    public List<Namayande> searchNamayande(RankReport rankReport) {
        return namayandeDAO.searchNamayande(rankReport);
    }

    public List<Namayande> getAllNamayandegi() {
        return getAllNamayandeByType("1");
    }

    public List<GStructureNamayande> makeGozareshMoghayese(Integer filter, String azTarikh, String taTarikh) {
        return namayandeDAO.makeGozareshMoghayese(filter, azTarikh, taTarikh);
    }

    public List<Namayande> doSearch(String name, String code) {
        return namayandeDAO.doSearch(name, code);
    }

    public List<BazarYabSanam> doSearchBazSan(String name, String code)
    {
        return namayandeDAO.doSearchBazSan(name, code);
    }

    public PaginatedListImpl<Namayande> findAllNamayande(PaginatedListImpl<Namayande> namayandeList, String nname, String ncode, String ntype, Long vSodurId, Long ostanId, Long shahrId) {
        return namayandeDAO.findAllNamayande(namayandeList, nname, ncode, ntype, vSodurId, ostanId, shahrId);
    }

    public Namayande getNamayandeByKodeKargozar(String kodeKargozar)
    {
        return namayandeDAO.getNamayandeByKodeKargozar(kodeKargozar);
    }

    public int findNamayandeOfVahedSodor(Long namayandeId)
    {
        return namayandeDAO.findNamayandeOfVahedSodor(namayandeId);
    }

    public List<Namayande> getChildNamayande(Long namayandeId){
        return namayandeDAO.getChildNamayande(namayandeId);
    }

    public List<Namayande> getChildNamayande(String name, String code, User user){
        return namayandeDAO.getChildNamayande(name,code,user);
    }
}
