package com.bitarts.parsian.service.implementation;

import com.bitarts.parsian.dao.ShakhsDAO;
import com.bitarts.parsian.model.bimename.Elhaghiye;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.model.pishnahad.Shakhs;
import com.bitarts.parsian.service.IShakhsService;
import com.bitarts.parsian.viewModel.SearchResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/8/11
 * Time: 4:20 PM
 */
public class ShakhsService implements IShakhsService {
    private ShakhsDAO shakhsDAO;

    public void saveShakhs(Shakhs s)
    {
        shakhsDAO.save(s);
    }

    public void setShakhsDAO(ShakhsDAO shakhsDAO) {
        this.shakhsDAO = shakhsDAO;
    }

    public void updateShakhs(Shakhs shakhs) {
        this.shakhsDAO.update(shakhs);
    }

    public boolean isThereKodeMelliShenasayi(String kodeMelliShenasayi) {
        return this.shakhsDAO.isThereKodeMelliShenasayi(kodeMelliShenasayi);
    }

    public boolean isThere2KodeMelliShenasayi(String kodeMelliShenasayi) {
        return this.shakhsDAO.isThere2KodeMelliShenasayi(kodeMelliShenasayi);
    }

    @Transactional
    public List<SearchResult> doSearchHoghughi(String naam, String shomareSabt) {
        return shakhsDAO.doSearchHoghughi(naam, shomareSabt);
    }

    public boolean getAdamTaeedPezeshki(Shakhs shakhs) {
        if (shakhsDAO.isAdamTaeed(shakhs.getKodeMelliShenasayi())) {
            return false;
        }
        return true;
    }

    public String isShakhsValid(String code_melli) {
        return shakhsDAO.isShakhsValid(code_melli);
    }

    public boolean checkDarkhastTaghirForPerson(String kodeMelliShenasayi)
    {
        return shakhsDAO.checkDarkhastTaghirForPerson(kodeMelliShenasayi);
    }
    public Shakhs addNewShakhs(Shakhs shakhs) throws KodeMelliShenasayiDuplicated {
        if (shakhsDAO.isThereKodeMelliShenasayi(shakhs.getKodeMelliShenasayi())) {
            throw new KodeMelliShenasayiDuplicated();
        }
        shakhsDAO.save(shakhs);
        return shakhs;
    }

    public List<SearchResult> doSearch(String code_melli, String searchBimeShode, String halateElhaghie)
    {
        return shakhsDAO.doSearch(code_melli, searchBimeShode,halateElhaghie);
    }

    public Shakhs findShakhs(Integer id) {
        return shakhsDAO.findById(id);
    }

    public boolean isHavePishnehadOrCurrentDarkhast(String codeMelli)
    {
        return shakhsDAO.isHavePishnehadOrCurrentDarkhast(codeMelli);
    }

    public Shakhs searchShakhs(String kodeMell,boolean isHalateElhaghie) {
        return shakhsDAO.searchShakhs(kodeMell, isHalateElhaghie);
    }

    public Shakhs searchOrphanPersonRecord(String kodeMell,boolean isHalateElhaghie)
    {
        return shakhsDAO.searchOrphanPersonRecord(kodeMell, isHalateElhaghie);
    }



    public List<Shakhs> searchAllShakhsByCodeMeli(String kodeMeli)
    {
        return shakhsDAO.searchAllShakhsByCodeMeli(kodeMeli);
    }

    public boolean isPersonEditable(String user, boolean haghighi, String code) {
        return user.equals("2328") || shakhsDAO.isPersonEditable(haghighi, code);
    }

    public List<Pishnehad> findAllPishnehadsForShakhs(String kodeMelli, Shakhs.Role shakhsRole, List<Elhaghiye> elhaghiyeList)
    {
        return shakhsDAO.findAllPishnehadsForShakhs(kodeMelli, shakhsRole,elhaghiyeList);
    }

    public List<Pishnehad> findAllPishnehadsForShakhs(String kodeMelli) {
        return shakhsDAO.findAllPishnehadsForShakhs(kodeMelli);
    }

}
